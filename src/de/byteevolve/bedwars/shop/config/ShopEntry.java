//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.byteevolve.bedwars.shop.config;

import de.byteevolve.bedwars.configuration.ConfigEntry;
import de.byteevolve.bedwars.configuration.config.ConfigSections;

public enum ShopEntry implements ConfigEntry {
    BLOCK1(ConfigSections.SHOP_BLOCKS, "BLOCK_1", "SANDSTONE", "SANDSTONE", 10, 10, "GOLD", "GOLD"),
    BLOCK2(ConfigSections.SHOP_BLOCKS, "BLOCK_2", "SANDSTONE", "WOOL", 10, 10, "IRON", "IRON"),
    BLOCK3(ConfigSections.SHOP_BLOCKS, "BLOCK_3", "SANDSTONE", "STONE", 10, 10, "IRON", "IRON"),
    BLOCK4(ConfigSections.SHOP_BLOCKS, "BLOCK_4", "SANDSTONE", "WOOD", 10, 10, "HARDEND_CLAY", "HARDEND_CLAY"),
    BLOCK5(ConfigSections.SHOP_BLOCKS, "BLOCK_5", "SANDSTONE", "SANDSTONE", 10, 10, "GOLD", "GOLD");

    private String path;
    private String defvalue;
    private String currency;
    private String defcurrency;
    private Object value;
    private int price;
    private int defprice;
    private ConfigSections sections;

    private ShopEntry(ConfigSections configSections, String path, Object value, String defvalue, int defprice, int price, String defcurrency, String currency) {
        this.path = path;
        this.sections = configSections;
        this.defvalue = defvalue;
        this.price = price;
        this.currency = currency;
        this.value = value;
        this.defcurrency = defcurrency;
        this.defprice = defprice;
    }

    public int getDefprice() {
        return this.defprice;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getDefcurrency() {
        return this.defcurrency;
    }

    public int getPrice() {
        return this.price;
    }

    public String getPath() {
        return this.path;
    }

    public Object getValue() {
        return this.value;
    }

    public Object getDefaultValue() {
        return this.defvalue;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ConfigSections getSection() {
        return this.sections;
    }

    public String getDescription() {
        return null;
    }
}
