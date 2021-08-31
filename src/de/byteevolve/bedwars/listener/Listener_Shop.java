
package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.shop.ShopHandler;
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
                        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
                            case "§aBlocks":
                                new ShopHandler().openBlockTab(player);
                                event.setCancelled(true);
                                break;
                            case "§aUtility":
                                new ShopHandler().openUtilityTab(player);
                                event.setCancelled(true);
                                break;
                            case  "§aTools":
                                new ShopHandler().openToolTab(player);
                                event.setCancelled(true);
                            case "§aArmor":
                                new ShopHandler().openArmorTab(player);
                                event.setCancelled(true);
                                break;
                            case "§aBuckets":
                                new ShopHandler().openBucketTab(player);
                                event.setCancelled(true);
                                break;
                            case  "§aProjectiles":
                                new ShopHandler().openProjectileTab(player);
                                event.setCancelled(true);
                                break;
                            case  "§aExplosives":
                                new ShopHandler().openExplosiveTab(player);
                                event.setCancelled(true);
                                break;
                            default:
                                event.setCancelled(true);
                                break;
                        }
                    }
                } else {
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
                        case STAINED_CLAY:
                        case STAINED_GLASS:
                        case WOOL:
                            if (event.getWhoClicked().getInventory().containsAtLeast(itemStack, price)) {
                                Teams team = gameHandler.getTeam(player).getTeam();
                                player.getInventory().addItem(new ItemBuilder(material, count).setName(team.getColor() + event.getCurrentItem().getItemMeta().getDisplayName()).setSubId(team.getWoolid()).build());
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