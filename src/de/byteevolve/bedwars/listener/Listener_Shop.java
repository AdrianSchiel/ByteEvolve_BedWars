
package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.itembuilder.LeatherBuilder;
import de.byteevolve.bedwars.shop.ShopHandler;
import de.byteevolve.bedwars.shop.config.ShopEntry;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class Listener_Shop implements Listener {
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
                    Material material = Material.valueOf(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
                    int defpric = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0]));
                    if (event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasLore()) {
                        GameHandler gameHandler = BedWars.getInstance().getGameHandler();
                        int price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0]));
                        int count = 0;
                        if (!event.isShiftClick()) {
                            if (event.getAction().equals(InventoryAction.MOVE_TO_OTHER_INVENTORY)) {
                                price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0])) * 64;
                                count = 64;
                            } else if (event.getAction().equals(InventoryAction.PICKUP_HALF)) {
                                price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0])) * 16;
                                count = 16;
                            } else
                                count = 1;
                        } else {
                            if (event.isLeftClick()) {
                                int aftercount = 0;
                                for (ItemStack item : player.getInventory().getContents()) {
                                    if (item != null) {
                                        if (item.hasItemMeta()) {
                                            if (item.isSimilar(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build())) {
                                                if (item.getAmount() >= defpric) {
                                                    int round = (int) Math.floor(item.getAmount() / defpric);
                                                    aftercount = aftercount + round;
                                                }
                                            }
                                        }
                                    }
                                }
                                count = aftercount;
                                System.out.println(count);
                                price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0])) * aftercount;
                            } else {
                                count = 32;
                                price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0])) * 32;
                            }
                        }
                        if (count == 0) {
                            event.setCancelled(true);
                        } else {
                            switch (event.getCurrentItem().getType()) {
                                case STAINED_GLASS_PANE:
                                    event.setCancelled(true);
                                    break;
                                case CHAINMAIL_BOOTS:
                                    BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setSword((Player) event.getWhoClicked());
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().setBoots(new ItemBuilder(Material.CHAINMAIL_BOOTS, 1).setName(team.getColor() + "Chainmail boots").build());
                                        player.getInventory().setLeggings(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1).setName(team.getColor() + "Chainmail leggings").build());
                                        player.getInventory().setChestplate(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1).setName(team.getColor() + "Chainmail chestplate").build());
                                        player.getInventory().setHelmet(new ItemBuilder(Material.CHAINMAIL_HELMET, 1).setName(team.getColor() + "Chainmail helmet").build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                case DIAMOND_BOOTS:
                                    BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setSword((Player) event.getWhoClicked());
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS, 1).setName(team.getColor() + "Diamond boots").build());
                                        player.getInventory().setLeggings(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1).setName(team.getColor() + "Diamond leggings").build());
                                        player.getInventory().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1).setName(team.getColor() + "Diamond chestplate").build());
                                        player.getInventory().setHelmet(new ItemBuilder(Material.DIAMOND_HELMET, 1).setName(team.getColor() + "Diamond helmet").build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                case GOLD_BOOTS:
                                    BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setSword((Player) event.getWhoClicked());
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().setBoots(new ItemBuilder(Material.GOLD_BOOTS, 1).setName(team.getColor() + "Gold boots").build());
                                        player.getInventory().setLeggings(new ItemBuilder(Material.GOLD_LEGGINGS, 1).setName(team.getColor() + "Gold leggings").build());
                                        player.getInventory().setChestplate(new ItemBuilder(Material.GOLD_CHESTPLATE, 1).setName(team.getColor() + "Gold chestplate").build());
                                        player.getInventory().setHelmet(new ItemBuilder(Material.GOLD_HELMET, 1).setName(team.getColor() + "Gold helmet").build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                case IRON_BOOTS:
                                    BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setSword((Player) event.getWhoClicked());
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().setBoots(new ItemBuilder(Material.IRON_BOOTS, 1).setName(team.getColor() + "Iron boots").build());
                                        player.getInventory().setLeggings(new ItemBuilder(Material.IRON_LEGGINGS, 1).setName(team.getColor() + "Iron leggings").build());
                                        player.getInventory().setChestplate(new ItemBuilder(Material.IRON_CHESTPLATE, 1).setName(team.getColor() + "Iron chestplate").build());
                                        player.getInventory().setHelmet(new ItemBuilder(Material.IRON_HELMET, 1).setName(team.getColor() + "Iron helmet").build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                case LEATHER_BOOTS:
                                    BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setSword((Player) event.getWhoClicked());
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().setBoots(new LeatherBuilder(Material.LEATHER_BOOTS, team.getColor() + "Leather boots").setCount(1).setColorbyString(team.getColor()).build());
                                        player.getInventory().setLeggings(new LeatherBuilder(Material.LEATHER_LEGGINGS, team.getColor() + "Leather leggings").setCount(1).setColorbyString(team.getColor()).build());
                                        player.getInventory().setChestplate(new LeatherBuilder(Material.LEATHER_CHESTPLATE, team.getColor() + "Leather chestplate").setCount(1).setColorbyString(team.getColor()).build());
                                        player.getInventory().setHelmet(new LeatherBuilder(Material.LEATHER_HELMET, team.getColor() + "Leather helmet").setCount(1).setColorbyString(team.getColor()).build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                case DIAMOND_AXE:
                                case GOLD_AXE:
                                case IRON_AXE:
                                case STONE_AXE:
                                case WOOD_AXE:
                                    BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setAxe((Player) event.getWhoClicked());
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).build());
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
                                        player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).build());
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
                                        player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                case SHEARS:
                                    BedWars.getInstance().getGameHandler().getTeam((Player) event.getWhoClicked()).setShears((Player) event.getWhoClicked());
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                case ENCHANTED_BOOK:
                                    Team teams = gameHandler.getTeam(player);
                                    if (event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.DAMAGE_ALL)) {
                                        if (teams.getSharpness() != 5)
                                            player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    } else if (event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.DIG_SPEED)) {
                                        if (teams.getEfficiency() != 5)
                                            player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }

                                    event.setCancelled(true);
                                    break;
                                case STAINED_CLAY:
                                case STAINED_GLASS:
                                case WOOL:
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).setSubId(team.getWoolid()).build());
                                        player.getInventory().removeItem(new ItemBuilder(material, price).setName("§8§l" + material.name()).build());
                                    }
                                    event.setCancelled(true);
                                    break;
                                default:
                                    if (event.getWhoClicked().getInventory().containsAtLeast(new ItemBuilder(material, 1).setName("§8§l" + material.name()).build(), price)) {
                                        Teams team = gameHandler.getTeam(player).getTeam();
                                        if (event.getCurrentItem().getItemMeta().hasEnchants()) {
                                            for (Enchantment enchantment : event.getCurrentItem().getEnchantments().keySet()) {
                                                player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).addEnchant(enchantment, event.getCurrentItem().getEnchantmentLevel(enchantment)).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).build());

                                            }
                                        } else
                                            player.getInventory().addItem(new ItemBuilder(event.getCurrentItem().getType(), count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).build());
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
}