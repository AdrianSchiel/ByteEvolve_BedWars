package de.byteevolve.bedwars.configuration;

import de.byteevolve.bedwars.configuration.config.ConfigSections;

public interface ConfigEntry {

    String getPath();
    Object getValue();
    Object getDefaultValue();
    void setValue(Object value);
    ConfigSections getSection();
    String getDescription();


}
