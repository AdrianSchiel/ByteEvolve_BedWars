
package de.byteevolve.bedwars.shop;

import de.byteevolve.bedwars.configuration.config.ConfigSections;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.shop.config.ShopEntry;

import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class ShopHandler {
    public ShopHandler() {
    }


    private Inventory setupShop() {
        Inventory inventory = Bukkit.createInventory(null, 9 * 6, "§aShop");
        for (int i = 0; i < 8; i++) {
            ItemStack itemStack = new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(8).setName("§r").build();
            switch (i) {
                case 1:
                    itemStack = new ItemBuilder(Material.valueOf(ShopEntry.TAB1.getValue().toString().split(",")[1]), 1).setName(ShopEntry.TAB1.getValue().toString().split(",")[0].replace("&", "§")).build();
                    break;
                case 2:
                    itemStack = new ItemBuilder(Material.valueOf(ShopEntry.TAB2.getValue().toString().split(",")[1]), 1).setName(ShopEntry.TAB2.getValue().toString().split(",")[0].replace("&", "§")).build();
                    break;
                case 3:
                    itemStack = new ItemBuilder(Material.valueOf(ShopEntry.TAB3.getValue().toString().split(",")[1]), 1).setName(ShopEntry.TAB3.getValue().toString().split(",")[0].replace("&", "§")).build();
                    break;
                case 4:
                    itemStack = new ItemBuilder(Material.valueOf(ShopEntry.TAB4.getValue().toString().split(",")[1]), 1).setName(ShopEntry.TAB4.getValue().toString().split(",")[0].replace("&", "§")).build();
                    break;
                case 5:
                    itemStack = new ItemBuilder(Material.valueOf(ShopEntry.TAB5.getValue().toString().split(",")[1]), 1).setName(ShopEntry.TAB5.getValue().toString().split(",")[0].replace("&", "§")).build();
                    break;
                case 6:
                    itemStack = new ItemBuilder(Material.valueOf(ShopEntry.TAB6.getValue().toString().split(",")[1]), 1).setName(ShopEntry.TAB6.getValue().toString().split(",")[0].replace("&", "§")).build();
                    break;
                case 7:
                    itemStack = new ItemBuilder(Material.valueOf(ShopEntry.TAB7.getValue().toString().split(",")[1]), 1).setName(ShopEntry.TAB7.getValue().toString().split(",")[0].replace("&", "§")).build();
                    break;
                default:
                    itemStack = new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(8).setName("§r").build();
                    break;
            }
            inventory.setItem(i, itemStack);
        }
        for (int i = 45; i < 9 * 6; i++) {
            ItemStack itemStack = new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(8).setName("§r").build();
            inventory.setItem(i, itemStack);
        }

        return inventory;
    }

    public String getPrice(int price, String currency) {
        ChatColor chatColor;
        if (currency.toUpperCase(Locale.ROOT).equalsIgnoreCase("IRON_INGOT")) {
            chatColor = ChatColor.DARK_GRAY;
        } else if (currency.toUpperCase(Locale.ROOT).equalsIgnoreCase("GOLD_INGOT")) {
            chatColor = ChatColor.GOLD;
        } else if (currency.toUpperCase(Locale.ROOT).equalsIgnoreCase("EMERALD")) {
            chatColor = ChatColor.GREEN;
        } else if (currency.toUpperCase(Locale.ROOT).equalsIgnoreCase("DIAMOND")) {
            chatColor = ChatColor.BLUE;
        } else {
            chatColor = ChatColor.RED;
        }

        return "§7§l" + price + " " + chatColor + currency;
    }

    public void openBlockTab(Player player) {
        Inventory inventory = setupShop();
        int slot = 18;

        for (int i = 0; i < ConfigSections.SHOP_BLOCKS.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry) ConfigSections.SHOP_BLOCKS.getShopEntries().get(i);
            if (isSecret(shopEntry)) {
                if (slot % 9 == 0) {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                } else {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                }

            } else {
                if (!shopEntry.getValue().toString().split(",")[0].equalsIgnoreCase("NONE")) {
                    Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
                    int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[3]);
                    int subid = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                    String currency = shopEntry.getValue().toString().split(",")[4];
                    if (slot % 9 == 0) {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());
                    } else {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());

                    }
                }
            }
        }

        player.openInventory(inventory);
    }

    public void openUtilityTab(Player player) {
        Inventory inventory = setupShop();
        int slot = 18;

        for (int i = 0; i < ConfigSections.SHOP_UTILITY.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry) ConfigSections.SHOP_UTILITY.getShopEntries().get(i);
            if (isSecret(shopEntry)) {
                if (slot % 9 == 0) {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                } else {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                }

            } else {
                if (!shopEntry.getValue().toString().split(",")[0].equalsIgnoreCase("NONE")) {
                    Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
                    int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[3]);
                    int subid = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                    String currency = shopEntry.getValue().toString().split(",")[4];
                    if (slot % 9 == 0) {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());
                    } else {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());

                    }
                }
            }
        }

        player.openInventory(inventory);
    }

    public void openToolTab(Player player) {
        Inventory inventory = setupShop();
        int slot = 18;

        for (int i = 0; i < ConfigSections.SHOP_TOOLS.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry) ConfigSections.SHOP_TOOLS.getShopEntries().get(i);
            if (isSecret(shopEntry)) {
                if (slot % 9 == 0) {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                } else {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                }

            } else {
                if (!shopEntry.getValue().toString().split(",")[0].equalsIgnoreCase("NONE")) {
                    Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
                    int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[3]);
                    int subid = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                    String currency = shopEntry.getValue().toString().split(",")[4];
                    if (slot % 9 == 0) {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());
                    } else {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());

                    }
                }
            }
        }

        player.openInventory(inventory);
    }

    public void openArmorTab(Player player) {
        Inventory inventory = setupShop();
        int slot = 18;

        for (int i = 0; i < ConfigSections.SHOP_ARMOR.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry) ConfigSections.SHOP_ARMOR.getShopEntries().get(i);
            if (isSecret(shopEntry)) {
                if (slot % 9 == 0) {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                } else {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                }

            } else {
                if (!shopEntry.getValue().toString().split(",")[0].equalsIgnoreCase("NONE")) {
                    Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
                    int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[3]);
                    int subid = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                    String currency = shopEntry.getValue().toString().split(",")[4];
                    if (slot % 9 == 0) {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());
                    } else {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());

                    }
                }
            }
        }

        player.openInventory(inventory);
    }

    public void openBucketTab(Player player) {
        Inventory inventory = setupShop();
        int slot = 18;

        for (int i = 0; i < ConfigSections.SHOP_BUCKETS.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry) ConfigSections.SHOP_BUCKETS.getShopEntries().get(i);
            if (isSecret(shopEntry)) {
                if (slot % 9 == 0) {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                } else {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                }

            } else {
                if (!shopEntry.getValue().toString().split(",")[0].equalsIgnoreCase("NONE")) {
                    Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
                    int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[3]);
                    int subid = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                    String currency = shopEntry.getValue().toString().split(",")[4];
                    if (slot % 9 == 0) {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());
                    } else {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());

                    }
                }
            }
        }

        player.openInventory(inventory);
    }

    public void openProjectileTab(Player player) {
        Inventory inventory = setupShop();
        int slot = 18;

        for (int i = 0; i < ConfigSections.SHOP_PROJECTILES.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry) ConfigSections.SHOP_PROJECTILES.getShopEntries().get(i);
            if (isSecret(shopEntry)) {
                if (slot % 9 == 0) {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                } else {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                }

            } else {
                if (!shopEntry.getValue().toString().split(",")[0].equalsIgnoreCase("NONE")) {
                    Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
                    int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[3]);
                    int subid = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                    String currency = shopEntry.getValue().toString().split(",")[4];
                    if (slot % 9 == 0) {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());
                    } else {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());

                    }
                }
            }
        }

        player.openInventory(inventory);
    }

    public void openExplosiveTab(Player player) {
        Inventory inventory = setupShop();
        int slot = 18;

        for (int i = 0; i < ConfigSections.SHOP_EXPLOSIVES.getShopEntries().size(); ++i) {
            ShopEntry shopEntry = (ShopEntry) ConfigSections.SHOP_EXPLOSIVES.getShopEntries().get(i);
            if (isSecret(shopEntry)) {
                if (slot % 9 == 0) {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                } else {
                    ++slot;
                    inventory.setItem(slot, checkforspecials(shopEntry));
                }

            } else {
                if (!shopEntry.getValue().toString().split(",")[0].equalsIgnoreCase("NONE")) {
                    Material material = Material.valueOf(shopEntry.getValue().toString().split(",")[0]);
                    int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[3]);
                    int subid = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                    String currency = shopEntry.getValue().toString().split(",")[4];
                    if (slot % 9 == 0) {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());
                    } else {
                        ++slot;
                        inventory.setItem(slot, (new ItemBuilder(material, 1)).setName(shopEntry.getValue().toString().split(",")[1].replace("&", "§")).setSubId(subid).addLore(this.getPrice(price, currency)).build());

                    }
                }
            }
        }

        player.openInventory(inventory);
    }

    private boolean isSecret(ShopEntry entry) {
        if (entry.getValue().toString().contains("SECRET"))
            return true;
        else
            return false;
    }

    private ItemStack checkforspecials(ShopEntry shopEntry) {
        String name = shopEntry.getValue().toString();
        if (name.contains("SECRET")) {
            if (name.contains("STICK")) {
                int price = Integer.parseInt(shopEntry.getValue().toString().split(",")[2]);
                String currency = shopEntry.getValue().toString().split(",")[3];
                return new ItemBuilder(Material.STICK, 1).setName(name.split(",")[1].replace("&", "§")).addEnchant(Enchantment.KNOCKBACK, 1).addLore(this.getPrice(price, currency)).build();
            } else
                return null;
        }
        return null;
    }
}
