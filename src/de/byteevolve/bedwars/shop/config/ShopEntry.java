package de.byteevolve.bedwars.shop.config;

import de.byteevolve.bedwars.configuration.ConfigEntry;
import de.byteevolve.bedwars.configuration.config.ConfigSections;
import org.bukkit.Material;

public enum ShopEntry implements ConfigEntry {
    TAB1(ConfigSections.SHOP_CONTAINER, "TAB_1", "&aBlocks", "&aBlocks", Material.WOOD.name(), Material.WOOD.name(), 0, 0),
    TAB2(ConfigSections.SHOP_CONTAINER, "TAB_2", "&aTools", "&aTools", Material.DIAMOND_SWORD.name(), Material.DIAMOND_SWORD.name(), 0, 0),
    TAB3(ConfigSections.SHOP_CONTAINER, "TAB_3", "&aExplosives", "&aExplosives", Material.TNT.name(), Material.TNT.name(), 0, 0),
    TAB4(ConfigSections.SHOP_CONTAINER, "TAB_4", "&aArmor", "&aArmor", Material.IRON_CHESTPLATE.name(), Material.IRON_CHESTPLATE.name(), 0, 0),
    TAB5(ConfigSections.SHOP_CONTAINER, "TAB_5", "&aBuckets", "&aBuckets", Material.BUCKET.name(), Material.BUCKET.name(), 0, 0),
    TAB6(ConfigSections.SHOP_CONTAINER, "TAB_6", "&aProjectiles", "&aProjectiles", Material.BOW.name(), Material.BOW.name(), 0, 0),
    TAB7(ConfigSections.SHOP_CONTAINER, "TAB_7", "&aUtility", "&aUtility", Material.ENDER_PEARL.name(), Material.ENDER_PEARL.name(), 0, 0),

    UTILITY1(ConfigSections.SHOP_UTILITY, "UTILITY_1", "ENDER_PEARL", "ENDER_PEARL", "&cENDER_PEARL", "&cENDER_PEARL", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    UTILITY2(ConfigSections.SHOP_UTILITY, "UTILITY_2", "VINE", "VINE", "&cVINE", "&cVINE", 0, 0, 10, 10, "HARD_CLAY", "HARD_CLAY"),
    UTILITY3(ConfigSections.SHOP_UTILITY, "UTILITY_3", "POTION", "POTION", "&cHEALTH POTION", "&cHEALTH POTION", 8261, 8261, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    UTILITY4(ConfigSections.SHOP_UTILITY, "UTILITY_4", "POTION", "POTION", "&cHEALTH POTION 2", "&cHEALTH POTION 2", 8229, 8229, 20, 20, "IRON_INGOT", "IRON_INGOT"),
    UTILITY5(ConfigSections.SHOP_UTILITY, "UTILITY_5", "POTION", "POTION", "&cSTRENGTH POTION", "&cSTRENGTH POTION", 8201, 8201, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),

    TOOLS1(ConfigSections.SHOP_TOOLS, "TOOLS_1", "WOOD_AXE", "WOOD_AXE", "&cWOOD_AXE", "&cWOOD_AXE", 0, 0, 10, 10, "CLAY_BRICK", "CLAY_BRICK"),
    TOOLS2(ConfigSections.SHOP_TOOLS, "TOOLS_2", "WOOD_PICKAXE", "WOOD_PICKAXE", "&cWOOD_PICKAXE", "&cWOOD_PICKAXE", 0, 0, 10, 10, "CLAY_BRICK", "CLAY_BRICK"),
    TOOLS3(ConfigSections.SHOP_TOOLS, "TOOLS_3", "SHEARS", "SHEARS", "SHEARS", "&cSHEARS", 0, 0, 6, 6, "IRON_INGOT", "IRON_INGOT"),
    TOOLS4(ConfigSections.SHOP_TOOLS, "TOOLS_4", "STONE_AXE", "STONE_AXE", "&cSTONE_AXE", "&cSTONE_AXE", 0, 0, 20, 20, "IRON_INGOT", "IRON_INGOT"),
    TOOLS5(ConfigSections.SHOP_TOOLS, "TOOLS_5", "STONE_PICKAXE", "STONE_PICKAXE", "&cSTONE_PICKAXE", "&cSTONE_PICKAXE", 0, 0, 20, 20, "IRON_INGOT", "IRON_INGOT"),
    TOOLS6(ConfigSections.SHOP_TOOLS, "TOOLS_6", "IRON_AXE", "IRON_AXE", "IRON_AXE", "&cIRON_AXE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    TOOLS7(ConfigSections.SHOP_TOOLS, "TOOLS_7", "IRON_PICKAXE", "IRON_PICKAXE", "&cIRON_PICKAXE", "&cIRON_PICKAXE", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    TOOLS8(ConfigSections.SHOP_TOOLS, "TOOLS_8", "DIAMOND_AXE", "DIAMOND_AXE", "§cDIAMOND_AXE", "&cDIAMOND_AXE", 0, 0, 30, 30, "GOLD_INGOT", "GOLD_INGOT"),
    TOOLS9(ConfigSections.SHOP_TOOLS, "TOOLS_9", "DIAMOND_PICKAXE", "DIAMOND_PICKAXE", "&cDIAMOND_PICKAXE", "&cDIAMOND_PICKAXE", 0, 0, 30, 30, "GOLD_INGOT", "GOLD_INGOT"),

    BLOCK1(ConfigSections.SHOP_BLOCKS, "BLOCK_1", "SANDSTONE", "SANDSTONE", "&cSANDSTONE", "&cSANDSTONE", 0, 0, 1, 1, "CLAY_BRICK", "CLAY_BRICK"),
    BLOCK2(ConfigSections.SHOP_BLOCKS, "BLOCK_2", "WOOL", "WOOL", "&cWOOL", "&cWOOL", 0, 0, 5, 5, "CLAY_BRICK", "CLAY_BRICK"),
    BLOCK3(ConfigSections.SHOP_BLOCKS, "BLOCK_3", "ENDER_STONE", "ENDER_STONE", "&cENDER STONE", "&cENDER STONE", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BLOCK4(ConfigSections.SHOP_BLOCKS, "BLOCK_4", "BRICK", "BRICK", "&cBRICK", "&cBRICK", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BLOCK5(ConfigSections.SHOP_BLOCKS, "BLOCK_5", "OBSIDIAN", "OBSIDIAN", "&cOBSIDIAN", "&cOBSIDIAN", 0, 0, 20, 20, "GOLD_INGOT", "GOLD_INGOT"),

    PROJECTILE1(ConfigSections.SHOP_PROJECTILES, "PROJECTILES_1", "ARROW", "ARROW", "&cARROW", "&cARROW", 0, 0, 1, 1, "GOLD_INGOT", "GOLD_INGOT"),
    PROJECTILE2(ConfigSections.SHOP_PROJECTILES, "PROJECTILES_2", "BOW", "BOW", "&cBOW", "&cBOW", 0, 0, 10, 10, "GOLD_INGOT", "GOLD_INGOT"),
    PROJECTILE3(ConfigSections.SHOP_PROJECTILES, "PROJECTILES_3", "SNOW_BALL", "SNOW_BALL", "&cSNOWBALL", "&cSNOWBALL", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    PROJECTILE4(ConfigSections.SHOP_PROJECTILES, "PROJECTILES_4", "EGG", "EGG", "&cEGG", "&cEGG", 0, 0, 10, 10, "HARD_CLAY", "HARD_CLAY"),

    EXPLOSIVE1(ConfigSections.SHOP_EXPLOSIVES, "EXPLOSIVES_1", "TNT", "TNT", "&cTNT", "&cTNT", 0, 0, 5, 5, "GOLD_INGOT", "GOLD_INGOT"),
    EXPLOSIVE2(ConfigSections.SHOP_EXPLOSIVES, "EXPLOSIVES_2", "FLINT_AND_STEEL", "FLINT_AND_STEEL", "&cFLINT AND STEEL", "&cFLINT AND STEEL", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    EXPLOSIVE3(ConfigSections.SHOP_EXPLOSIVES, "EXPLOSIVES_3", "NONE", "NONE", "&cNONE", "&cNONE", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    EXPLOSIVE4(ConfigSections.SHOP_EXPLOSIVES, "EXPLOSIVES_4", "NONE", "NONE", "&cNONE", "&cNONE", 0, 0, 10, 10, "HARD_CLAY", "HARD_CLAY"),

    BUCKETS1(ConfigSections.SHOP_BUCKETS, "BUCKETS_1", "WATER_BUCKET", "WATER_BUCKET", "&cWATER_BUCKET", "&cWATER_BUCKET", 0, 0, 8, 8, "IRON_INGOT", "IRON_INGOT"),
    BUCKETS2(ConfigSections.SHOP_BUCKETS, "BUCKETS_2", "LAVA_BUCKET", "LAVA_BUCKET", "&cLAVA_BUCKET", "&cLAVA_BUCKET", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BUCKETS3(ConfigSections.SHOP_BUCKETS, "BUCKETS_3", "SECRET_Sharpness", "SECRET_Sharpness", "§cSharpness enchantment", "§cSharpness enchantment", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BUCKETS4(ConfigSections.SHOP_BUCKETS, "BUCKETS_4", "SECRET_Efficiency", "SECRET_Efficiency", "§cEfficiency enchantment", "§cEfficiency enchantment", 0, 0, 10, 10, "IRON_INGOT", "IRON_INGOT"),
    BUCKETS5(ConfigSections.SHOP_BUCKETS, "BUCKETS_5", "SECRET_STICK", "SECRET_STICK", "§cKnockback Stick", "§cKnockback Stick", 0, 0, 10, 10, "CLAY_BRICK", "CLAY_BRICK"),

    ARMOR1(ConfigSections.SHOP_ARMOR, "ARMOR_1", "LEATHER_BOOTS", "LEATHER_BOOTS", "&cLEATHER ARMOR", "&cLEATHER ARMOR", 0, 0, 10, 10, "CLAY_BRICK", "CLAY_BRICK"),
    ARMOR2(ConfigSections.SHOP_ARMOR, "ARMOR_2", "GOLD_BOOTS", "GOLD_BOOTS", "&cGOLD ARMOR", "&cGOLD ARMOR", 0, 0, 20, 20, "IRON_INGOT", "IRON_INGOT"),
    ARMOR3(ConfigSections.SHOP_ARMOR, "ARMOR_3", "IRON_BOOTS", "IRON_BOOTS", "&cIRON ARMOR", "&cIRON ARMOR", 0, 0, 20, 20, "GOLD_INGOT", "GOLD_INGOT"),
    ARMOR4(ConfigSections.SHOP_ARMOR, "ARMOR_4", "DIAMOND_BOOTS", "DIAMOND_BOOTS", "&cDIAMOND ARMOR", "&cDIAMOND ARMOR", 0, 0, 40, 40, "GOLD_INGOT", "GOLD_INGOT");


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
