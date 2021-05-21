package de.byteevolve.bedwars.configuration;

import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.configuration.config.ConfigSections;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigHandler {


    public ConfigHandler() {
        loadConfiguration();
    }

    private void loadConfiguration() {
        File file = new File("plugins/BedWars/config.yml");
        if(!file.exists()){
            new File("plugins/BedWars/").mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        YamlConfiguration configuration = new YamlConfiguration().loadConfiguration(file);
        configuration.options().copyDefaults(true);

        String header = getHeader();
        if(!header.equals(configuration.options().header())){
            configuration.options().header(header);
        }

        for(ConfigEntries entry : ConfigEntries.values()){
            if(!configuration.contains(entry.getSection().getName() + "." + entry.getPath())){
                configuration.set(entry.getSection().getName() + "." + entry.getPath(), entry.getValue());
            }else{
                entry.setValue(configuration.get(entry.getSection().getName() + "." + entry.getPath()));
            }
        }

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getHeader(){
        String header = "Das ist der Header der Configuration, hier gibt es alle Beschreibungen";

        for(ConfigSections section : ConfigSections.values()){
            String desc = "\n----" + section.getName() + "----\n Beschreibung: " + section.getDescription() + "\n";

            for(ConfigEntries entrie : section.getEntries()){
                desc = desc + "\r\n " + entrie.getPath() + ": \n" + entrie.getDescription() + "\n Default-Value: "
                        + entrie.getDefaultValue() + "\r\n";
            }
            header = header + desc;
        }

        return header;
    }
}
