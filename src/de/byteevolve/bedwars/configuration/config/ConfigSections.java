
package de.byteevolve.bedwars.configuration.config;

import de.byteevolve.bedwars.configuration.ConfigEntry;
import de.byteevolve.bedwars.configuration.ConfigSection;
import de.byteevolve.bedwars.shop.config.ShopEntry;

import java.util.ArrayList;
import java.util.List;

public enum ConfigSections implements ConfigSection {
    MESSAGES("messages", "Alle Einstellungen zu den Chat Nachrichten."),
    SETTINGS("settings", "Alle anderen Einstellungen."),
    TEAMSTATES("teamstates", "Alle TeamStates Einstellungen"),
    SCOREBOARD("scoreboard", "Alle Scoreboard Einstellungen"),
    SHOP_UTILITY("utility tab", "Alle Utility Items"),
    SHOP_BLOCKS("blocks tab", "Alle Blöcke"),
    SHOP_TOOLS("tools tab", "Alle Werkzeuge"),
    SHOP_TABS("tab names", "Namen der Tabs"),
    SHOP_CURRENCY("currency tab","Alle Währungen"),
    SHOP_PROJECTILES("projectile tab", "Alle Projektile Items"),
    SHOP_BUCKETS("bucket tab", "Alle Eimer"),
    SHOP_EXPLOSIVES("explosives tab", "Alle Explosiven Items"),
    SHOP_ARMOR("armor tab", "Alle Rüstungen"),
    MYSQL("mysql", "Hier musst du deine Datenbank Einstellungen treffen");

    private String name, desc;

    ConfigSections(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public List<ShopEntry> getShopEntries() {
        List<ShopEntry> tmp = new ArrayList<>();
        for (ShopEntry entry : ShopEntry.values()) {
            if (entry.getSection().getName().contains("tab")) {
                if (entry.getSection().name.equalsIgnoreCase(this.name))
                    tmp.add(entry);
            }
        }
        return tmp;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<ConfigEntries> getEntries() {
        List<ConfigEntries> tmp = new ArrayList<>();
        for (ConfigEntries entry : ConfigEntries.values()) {
            if (entry.getSection().getName().contains("tab")) {
                if (entry.getSection().equals(this))
                    tmp.add(entry);
            }
        }
        return tmp;
    }

    @Override
    public ConfigEntry getEntry(String name) {
        for (ConfigEntries entry : ConfigEntries.values())
            if (entry.getPath().equals(name)) return entry;

        return null;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
