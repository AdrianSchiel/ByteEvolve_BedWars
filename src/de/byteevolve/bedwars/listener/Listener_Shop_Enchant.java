package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listener_Shop_Enchant implements Listener {
    @EventHandler
    public void onCLick(InventoryClickEvent event) {
        if (event.getInventory() != null) {
            if (event.getCurrentItem() != null) {
                if (event.getCurrentItem().hasItemMeta()) {
                    if (event.getCurrentItem().getItemMeta().hasLore()) {
                        Player player = (Player) event.getWhoClicked();
                        Team team = BedWars.getInstance().getGameHandler().getTeam(player);
                        int price = Integer.parseInt(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[0]));
                        Material material = Material.valueOf(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getLore().get(0).split(" ")[1]));
                        ItemStack currency = new ItemBuilder(material, 1).setName("§8§l" + material.name()).build();


                        if (event.getWhoClicked().getInventory().containsAtLeast(currency, price)) {
                            if (event.getCurrentItem().getItemMeta().getLore().contains("Sharpness")) {
                                if (team.getSharpness() == 5) {
                                    player.sendMessage(BedWars.getInstance().getPrefix() + "§8You are at the maximum sharpness level");
                                } else
                                    team.setSharpness(team.getSharpness() + 1);
                                event.setCancelled(true);
                                enchantforTeam(team, Enchantment.DAMAGE_ALL, "SWORD");
                            }
                        } else if (event.getCurrentItem().getItemMeta().getLore().contains("Efficiency")) {
                            if (team.getEfficiency() == 5) {
                                player.sendMessage(BedWars.getInstance().getPrefix() + "§8You are at the maximum efficiency level");
                            } else
                                team.setEfficiency(team.getEfficiency() + 1);
                            event.setCancelled(true);
                            enchantforTeam(team, Enchantment.DIG_SPEED, "PICKAXE");

                        }
                    }
                }
            }

        }
    }

    private void enchantforTeam(Team team, Enchantment enchantment, String type) {
        for (Player player1 : team.getMembers()) {
            for (ItemStack itemStack : player1.getInventory().getContents()) {
                if (itemStack != null) {
                    if (itemStack.getType() == Material.valueOf("WOOD_" + type) || itemStack.getType() == Material.valueOf("STONE_" + type) || itemStack.getType() == Material.valueOf("GOLDEN_" + type) ||
                            itemStack.getType() == Material.valueOf("IRON_" + type) || itemStack.getType() == Material.valueOf("DIAMOND_" + type)) {
                        ItemMeta itemMeta = itemStack.getItemMeta();
                        if (itemMeta.hasEnchant(enchantment)) {
                            itemMeta.removeEnchant(enchantment);
                            itemMeta.addEnchant(enchantment, team.getEfficiency(), true);
                        } else
                            itemMeta.addEnchant(enchantment, team.getEfficiency(), true);
                        itemStack.setItemMeta(itemMeta);
                    }

                }
            }
        }
    }
}
