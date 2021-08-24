
package de.byteevolve.bedwars.shop;

import de.byteevolve.bedwars.configuration.config.ConfigSections;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.shop.config.ShopEntry;
import java.util.Locale;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class ShopHandler {
    public ShopHandler() {
    }

    public String getPrice(int price, String currency) {
        ChatColor chatColor;
        if (currency.toUpperCase(Locale.ROOT).equalsIgnoreCase("IRON")) {
            chatColor = ChatColor.DARK_GRAY;
        } else if (currency.toUpperCase(Locale.ROOT).equalsIgnoreCase("GOLD")) {
            chatColor = ChatColor.GOLD;
        } else {
            chatColor = ChatColor.RED;
        }

        return "§7§l" + price + " " + chatColor + currency;
    }

    public void openBlockTab(Player player) {
        Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, "§5Blocks");
        int slot = 18;

        for(int i = 0; i < ConfigSections.SHOP_BLOCKS.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry)ConfigSections.SHOP_BLOCKS.getShopEntries().get(i);
            Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
            int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[1]);
            String currency = shopEntry.getValue().toString().split(",")[2];
            if (slot % 9 == 0) {
                ++slot;
                inventory.setItem(slot, (new ItemBuilder(material, 1)).addLore(this.getPrice(price, currency)).build());
            } else {
                ++slot;
                inventory.setItem(slot, (new ItemBuilder(material, 1)).addLore(this.getPrice(price, currency)).build());
            }
        }

        player.openInventory(inventory);
    }

    public void openUtilityTab(Player player) {
        Inventory inventory = Bukkit.createInventory((InventoryHolder)null, 54, "§5Utility");
        int slot = 18;

        for(int i = 0; i < ConfigSections.SHOP_UTILITY.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry)ConfigSections.SHOP_UTILITY.getShopEntries().get(i);
            Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
            int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[1]);
            String currency = shopEntry.getValue().toString().split(",")[2];
            if (slot % 9 == 0) {
                ++slot;
                inventory.setItem(slot, (new ItemBuilder(material, 1)).addLore(this.getPrice(price, currency)).build());
            } else {
                ++slot;
                inventory.setItem(slot, (new ItemBuilder(material, 1)).addLore(this.getPrice(price, currency)).build());
            }
        }

        player.openInventory(inventory);
    }
}
