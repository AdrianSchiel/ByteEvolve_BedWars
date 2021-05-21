package de.byteevolve.bedwars.configuration;

import de.byteevolve.bedwars.configuration.config.ConfigEntries;

import java.util.List;

public interface ConfigSection {

    String getName();
    List<ConfigEntries> getEntries();
    ConfigEntry getEntry(String name);
    String getDescription();

}
