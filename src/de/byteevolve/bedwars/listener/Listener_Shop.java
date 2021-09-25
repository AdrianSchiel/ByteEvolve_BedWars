
package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.shop.ShopHandler;
import de.byteevolve.bedwars.shop.config.ShopEntry;
import net.md_5.bungee.api.chat.ClickEvent;
import net.minecraft.server.v1_9_R1.SoundEffectType;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Listener_Shop implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand) {
            (new ShopHandler()).openBlockTab(event.getPlayer());
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory() != null && event.getCurrentItem() != null && event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            if (event.getInventory().getName().equalsIgnoreCase("§aShop")) {
                if (event.getSlot() <= 8) {
                    if (event.getCurrentItem().hasItemMeta()) {
                        String name = event.getCurrentItem().getItemMeta().getDisplayName();
                        if (name.equalsIgnoreCase(ShopEntry.TAB1.getValue().toString().split(",")[0].replace("&", "§"))) {
                            new ShopHandler().openBlockTab(player);
                            event.setCancelled(true);
                        }
                        if (name.equalsIgnoreCase(ShopEntry.TAB2.getValue().toString().split(",")[0].replace("&", "§"))) {
                            new ShopHandler().openToolTab(player);
                            event.setCancelled(true);
                        }
                        if (name.equalsIgnoreCase(ShopEntry.TAB3.getValue().toString().split(",")[0].replace("&", "§"))) {
                            new ShopHandler().openExplosiveTab(player);
                            event.setCancelled(true);
                        }
                        if (name.equalsIgnoreCase(ShopEntry.TAB4.getValue().toString().split(",")[0].replace("&", "§"))) {
                            new ShopHandler().openArmorTab(player);
                            event.setCancelled(true);
                        }
                        if (name.equalsIgnoreCase(ShopEntry.TAB5.getValue().toString().split(",")[0].replace("&", "§"))) {
                            new ShopHandler().openBucketTab(player);
                            event.setCancelled(true);
                        }
                        if (name.equalsIgnoreCase(ShopEntry.TAB6.getValue().toString().split(",")[0].replace("&", "§"))) {
                            new ShopHandler().openProjectileTab(player);
                            event.setCancelled(true);
                        }
                        if (name.equalsIgnoreCase(ShopEntry.TAB7.getValue().toString().split(",")[0].replace("&", "§"))) {
                            new ShopHandler().openUtilityTab(player);
                            event.setCancelled(true);
                        } else
                            event.setCancelled(true);
                    }
                } else {
                    if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
                        GameHandler gameHandler = BedWars.getInstance().getGameHandler();
                        int price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0]));
                        int count = 1;
                        if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                            price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0])) * 64;
                            count = 64;
                        }
                        Material material = Material.valueOf(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
                        ItemStack itemStack = new ItemBuilder(material, 1).setName("§8§l" + material.name()).build();
                        switch (event.getCurrentItem().getType()) {
                            case DIAMOND_AXE:
                            case GOLD_AXE:
                            case IRON_AXE:
                            case STONE_AXE:
                            case WOOD_AXE:
                                BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setAxe((Player) event.getWhoClicked());
                                if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                    Teams team = gameHandler.getTeam(player).getTeam();
                                    player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getType()).build());
                                    player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                }
                                event.setCancelled(true);
                                break;
                            case DIAMOND_SWORD:
                            case GOLD_SWORD:
                            case IRON_SWORD:
                            case STONE_SWORD:
                            case WOOD_SWORD:
                                BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setSword((Player) event.getWhoClicked());
                                if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                    Teams team = gameHandler.getTeam(player).getTeam();
                                    player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getType()).build());
                                    player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                }
                                event.setCancelled(true);
                                break;
                            case DIAMOND_PICKAXE:
                            case GOLD_PICKAXE:
                            case IRON_PICKAXE:
                            case STONE_PICKAXE:
                            case WOOD_PICKAXE:
                                BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setPickaxe((Player) event.getWhoClicked());
                                if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                    Teams team = gameHandler.getTeam(player).getTeam();
                                    player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getType()).build());
                                    player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                }
                                event.setCancelled(true);
                                break;
                            case SHEARS:
                                BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setShears((Player) event.getWhoClicked());
                                if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                    Teams team = gameHandler.getTeam(player).getTeam();
                                    player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getType()).build());
                                    player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                }
                                event.setCancelled(true);
                                break;
                            case ENCHANTED_BOOK:
                                player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                event.setCancelled(true);
                                break;
                            case STAINED_CLAY:
                            case STAINED_GLASS:
                            case WOOL:
                                if (event.getWhoClicked().getInventory().containsAtLeast(itemStack, price)) {
                                    Teams team = gameHandler.getTeam(player).getTeam();
                                    player.getInventory().addItem(new ItemBuilder(material, count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).setSubId(team.getWoolid()).build());
                                    player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                }
                                event.setCancelled(true);
                                break;
                            default:
                                if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                    Teams team = gameHandler.getTeam(player).getTeam();
                                    player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getType()).build());
                                    player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                }
                                event.setCancelled(true);
                                break;
                        }

                    }
                }
            }
        }

    }
}