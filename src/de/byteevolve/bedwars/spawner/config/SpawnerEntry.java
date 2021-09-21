package de.byteevolve.bedwars.spawner.config;

import de.byteevolve.bedwars.configuration.ConfigEntry;
import de.byteevolve.bedwars.configuration.config.ConfigSections;

public enum SpawnerEntry implements ConfigEntry {
    CURRENCY1(ConfigSections.SHOP_CURRENCY, "CURRENCY_1", "HARD_CLAY", "HARD_CLAY", 40, 20 * 10),
    CURRENCY2(ConfigSections.SHOP_CURRENCY, "CURRENCY_2", "IRON_INGOT", "IRON_INGOT", 20 * 10, 20 * 10),
    CURRENCY3(ConfigSections.SHOP_CURRENCY, "CURRENCY_3", "GOLD_INGOT", "GOLD_INGOT", 20 * 30, 20 * 30);

    private String path;
    private String defvalue;
    private Object value;
    private int time;
    private int deftime;
    private ConfigSections sections;

    SpawnerEntry(ConfigSections configSections, String path, Object value, String defvalue, int defTime, int time) {
        this.path = path;
        this.sections = configSections;
        this.defvalue = defvalue;
        this.time = time;
        this.value = value;
        this.deftime = defTime;
    }

    public int getDeftime() {
        return this.deftime;
    }

    public int getTime() {
        return this.time;
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
