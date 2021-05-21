package de.byteevolve.bedwars.configuration.config;


import de.byteevolve.bedwars.configuration.ConfigEntry;
import de.byteevolve.bedwars.configuration.ConfigSection;

import java.util.ArrayList;
import java.util.List;

public enum ConfigSections implements ConfigSection {

    MESSAGES("messages", "Alle Einstellungen zu den Chat Nachrichten."),
    SETTINGS("settings", "Alle anderen Einstellungen."),
    TEAMSTATES("teamstates", "Alle TeamStates Einstellungen"),
    SCOREBOARD("scoreboard", "Alle Scoreboard Einstellungen"),
    MYSQL("mysql", "Hier musst du deine Datenbank Einstellungen treffen");

    private String name, desc;

    ConfigSections(String name, String desc){
        this.name = name;
        this.desc = desc;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<ConfigEntries> getEntries() {
        List<ConfigEntries> tmp = new ArrayList<>();
        for(ConfigEntries entry : ConfigEntries.values()){
            if(entry.getSection().equals(this))
                tmp.add(entry);
        }
        return tmp;
    }

    @Override
    public ConfigEntry getEntry(String name) {
        for(ConfigEntries entry : ConfigEntries.values())
            if(entry.getPath().equals(name)) return entry;

            return null;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
