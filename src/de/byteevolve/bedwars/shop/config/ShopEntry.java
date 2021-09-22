package de.byteevolve.bedwars.shop.config;

import de.byteevolve.bedwars.configuration.ConfigEntry;
import de.byteevolve.bedwars.configuration.config.ConfigSections;
import org.bukkit.Material;

public enum ShopEntry implements ConfigEntry {
    TAB1(ConfigSections.SHOP_CONTAINER, "TAB_1", "&aBlocks", "&aBlocks", "WOOD", "WOOD", 0, 0),
    TAB2(ConfigSections.SHOP_CONTAINER, "TAB_2", "&aTools", "&aTools", Material.DIAMOND_SWORD.name(), Material.DIAMOND_SWORD.name(), 0, 0),
    TAB3(ConfigSections.SHOP_CONTAINER, "TAB_3", "&aExplosives", "&aExplosives", Material.TNT.name(), Material.TNT.name(), 0, 0),
    TAB4(ConfigSections.SHOP_CONTAINER, "TAB_4", "&aArmor", "&aArmor", Material.IRON_CHESTPLATE.name(), Material.IRON_CHESTPLATE.name(), 0, 0),
    TAB5(ConfigSections.SHOP_CONTAINER, "TAB_5", "&aBuckets", "&aBuckets", Material.BUCKET.name(), Material.BUCKET.name(), 0, 0),
    TAB6(ConfigSections.SHOP_CONTAINER, "TAB_6", "&aProjectiles", "&aProjectiles", Material.BOW.name(), Material.BOW.name(), 0, 0),
    TAB7(ConfigSections.SHOP_CONTAINER, "TAB_7", "&aUtility", "&aUtility", Material.ENDER_PEARL.name(), Material.ENDER_PEARL.name(), 0, 0),

    UTILITY1(ConfigSections.SHOP_UTILITY, "UTILITY_1", "ENDER_PEARL", "ENDER_PEARL", "&cENDER_PEARL", "&cENDER_PEARL", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    UTILITY2(ConfigSections.SHOP_UTILITY, "UTILITY_2", "VINE", "VINE", "&cVINE", "&cVINE", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    UTILITY3(ConfigSections.SHOP_UTILITY, "UTILITY_3", "SANDSTONE", "STONE", "&cSANDSTONE", "&cSTONE", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    UTILITY4(ConfigSections.SHOP_UTILITY, "UTILITY_4", "SANDSTONE", "WOOD", "&cSANDSTONE", "&cWOOD", 0, 0, 10, 10, "HARD_CLAY", "HARD_CLAY"),
    UTILITY5(ConfigSections.SHOP_UTILITY, "UTILITY_5", "SANDSTONE", "SANDSTONE", "&cSANDSTONE", "&cSANDSTONE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),

    TOOLS1(ConfigSections.SHOP_TOOLS, "TOOLS_1", "WOOD_AXE", "WOOD_AXE", "&cWOOD_AXE", "&cWOOD_AXE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    TOOLS2(ConfigSections.SHOP_TOOLS, "TOOLS_2", "WOOD_PICKAXE", "WOOD_PICKAXE", "&cWOOD_PICKAXE", "&cWOOD_PICKAXE", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    TOOLS3(ConfigSections.SHOP_TOOLS, "TOOLS_3", "SHEARS", "SHEARS", "SHEARS", "&cSHEARS", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    TOOLS4(ConfigSections.SHOP_TOOLS, "TOOLS_4", "STONE_AXE", "STONE_AXE", "&cSTONE_AXE", "&cSTONE_AXE", 0, 0, 10, 10, "HARD_CLAY", "HARD_CLAY"),
    TOOLS5(ConfigSections.SHOP_TOOLS, "TOOLS_5", "STONE_PICKAXE", "STONE_PICKAXE", "&cSTONE_PICKAXE", "&cSTONE_PICKAXE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    TOOLS6(ConfigSections.SHOP_TOOLS, "TOOLS_4", "IRON_AXE", "IRON_AXE", "IRON_AXE", "&cIRON_AXE", 0, 0, 10, 10, "HARD_CLAY", "HARD_CLAY"),
    TOOLS7(ConfigSections.SHOP_TOOLS, "TOOLS_5", "IRON_PICKAXE", "IRON_PICKAXE", "&cIRON_PICKAXE", "&cIRON_PICKAXE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),

    BLOCK1(ConfigSections.SHOP_BLOCKS, "BLOCK_1", "SANDSTONE", "SANDSTONE", "&cSANDSTONE", "&cSANDSTONE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    BLOCK2(ConfigSections.SHOP_BLOCKS, "BLOCK_2", "WOOL", "WOOL", "&cWOOL", "&cWOOL", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BLOCK3(ConfigSections.SHOP_BLOCKS, "BLOCK_3", "SANDSTONE", "STONE", "&cSANDSTONE", "&cSTONE", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BLOCK4(ConfigSections.SHOP_BLOCKS, "BLOCK_4", "SANDSTONE", "WOOD", "&cSANDSTONE", "&cWOOD", 0, 0, 10, 10, "HARD_CLAY", "HARD_CLAY"),
    BLOCK5(ConfigSections.SHOP_BLOCKS, "BLOCK_5", "SANDSTONE", "SANDSTONE", "&cSANDSTONE", "&cSANDSTONE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT");


    private String path;
    private String defvalue;
    private String currency;
    private String defcurrency;
    private String defname, name;
    private Object value;
    private int price;
    private int defsub, sub;
    private int defprice;
    private ConfigSections sections;
    private String defmaterial, material;


    ShopEntry(ConfigSections configSections, String path, Object value, String defvalue, String defcurrency, String currency, int defsub, int sub) {
        this.path = path;
        this.sections = configSections;
        this.defvalue = defvalue;
        this.material = currency;
        this.value = value;
        this.sub = sub;
        this.defsub = sub;
        this.defmaterial = defcurrency;
    }

    ShopEntry(ConfigSections configSections, String path, Object value, String defvalue, String defName, String name, int defSub, int sub, int defprice, int price, String defcurrency, String currency) {
        this.path = path;
        this.sections = configSections;
        this.defvalue = defvalue;
        this.price = price;
        this.currency = currency;
        this.value = value;
        this.name = name;
        this.defname = defName;
        this.sub = sub;
        this.defsub = defSub;
        this.defcurrency = defcurrency;
        this.defprice = defprice;
    }

    public int getSub() {
        return sub;
    }

    public int getDefSub() {
        return defsub;
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

    public String getDefMaterial() {
        return defmaterial;
    }

    public String getMaterial() {
        return material;
    }

    public String getDescription() {
        return null;
    }

    public String getDefname() {
        return defname;
    }

    public String getName() {
        name = name.replace("&", "§");
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
