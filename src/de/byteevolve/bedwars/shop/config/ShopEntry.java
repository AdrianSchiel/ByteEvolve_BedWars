package de.byteevolve.bedwars.shop.config;

import de.byteevolve.bedwars.configuration.ConfigEntry;
import de.byteevolve.bedwars.configuration.config.ConfigSections;
import org.bukkit.Material;

public enum ShopEntry implements ConfigEntry {
    TAB1(ConfigSections.SHOP_CONTAINER,"TAB_1","§aBlocks","§aBlocks",0,0,"WOOD","WOOD"),
    TAB2(ConfigSections.SHOP_CONTAINER,"TAB_2","§aTools","§aTools",0,0,Material.DIAMOND_SWORD.name(),Material.DIAMOND_SWORD.name()),
    TAB3(ConfigSections.SHOP_CONTAINER,"TAB_3","§aExplosives","§aExplosives",0,0,Material.TNT.name(),Material.TNT.name()),
    TAB4(ConfigSections.SHOP_CONTAINER,"TAB_4","§aArmor","§aArmor",0,0,Material.IRON_CHESTPLATE.name(),Material.IRON_CHESTPLATE.name()),
    TAB5(ConfigSections.SHOP_CONTAINER,"TAB_5","§aBuckets","§aBuckets",0,0,Material.BUCKET.name(),Material.BUCKET.name()),
    TAB6(ConfigSections.SHOP_CONTAINER,"TAB_6","§aProjectiles","§aProjectiles",0,0,Material.BOW.name(),Material.BOW.name()),
    TAB7(ConfigSections.SHOP_CONTAINER,"TAB_7","§aUtility","§aUtility",0,0,Material.ENDER_PEARL.name(),Material.ENDER_PEARL.name()),

    UTILITY1(ConfigSections.SHOP_UTILITY, "UTILITY_1", "ENDER_PEARL", "ENDER_PEARL", 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    UTILITY2(ConfigSections.SHOP_UTILITY, "UTILITY_2", "VINE", "VINE", 10, 10, "IRON_INGOT", "IRON_INGOT"),
    UTILITY3(ConfigSections.SHOP_UTILITY, "UTILITY_3", "SANDSTONE", "STONE", 10, 10, "IRON_INGOT", "IRON_INGOT"),
    UTILITY4(ConfigSections.SHOP_UTILITY, "UTILITY_4", "SANDSTONE", "WOOD", 10, 10, "HARD_CLAY", "HARD_CLAY"),
    UTILITY5(ConfigSections.SHOP_UTILITY, "UTILITY_5", "SANDSTONE", "SANDSTONE", 10, 10, "GOLD_INGOT", "GOLD_INGOT"),

    TOOLS1(ConfigSections.SHOP_TOOLS, "TOOLS_1", "WOOD_AXE", "WOOD_AXE", 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    TOOLS2(ConfigSections.SHOP_TOOLS, "TOOLS_2", "WOOD_PICKAXE", "WOOD_PICKAXE", 10, 10, "IRON_INGOT", "IRON_INGOT"),
    TOOLS3(ConfigSections.SHOP_TOOLS, "TOOLS_3", "SHEARS", "SHEARS", 10, 10, "IRON_INGOT", "IRON_INGOT"),
    TOOLS4(ConfigSections.SHOP_TOOLS, "TOOLS_4", "STONE_AXE", "STONE_AXE", 10, 10, "HARD_CLAY", "HARD_CLAY"),
    TOOLS5(ConfigSections.SHOP_TOOLS, "TOOLS_5", "STONE_PICKAXE", "STONE_PICKAXE", 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    TOOLS6(ConfigSections.SHOP_TOOLS, "TOOLS_4", "IRON_AXE", "IRON_AXE", 10, 10, "HARD_CLAY", "HARD_CLAY"),
    TOOLS7(ConfigSections.SHOP_TOOLS, "TOOLS_5", "IRON_PICKAXE", "IRON_PICKAXE", 10, 10, "GOLD_INGOT", "GOLD_INGOT"),

    BLOCK1(ConfigSections.SHOP_BLOCKS, "BLOCK_1", "SANDSTONE", "SANDSTONE", 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    BLOCK2(ConfigSections.SHOP_BLOCKS, "BLOCK_2", "WOOL", "WOOL", 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BLOCK3(ConfigSections.SHOP_BLOCKS, "BLOCK_3", "SANDSTONE", "STONE", 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BLOCK4(ConfigSections.SHOP_BLOCKS, "BLOCK_4", "SANDSTONE", "WOOD", 10, 10, "HARD_CLAY", "HARD_CLAY"),
    BLOCK5(ConfigSections.SHOP_BLOCKS, "BLOCK_5", "SANDSTONE", "SANDSTONE", 10, 10, "GOLD_INGOT", "GOLD_INGOT");


    private String path;
    private String defvalue;
    private String currency;
    private String defcurrency;
    private Object value;
    private int price;
    private int defprice;
    private ConfigSections sections;

    ShopEntry(ConfigSections configSections, String path, Object value, String defvalue, int defprice, int price, String defcurrency, String currency) {
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
