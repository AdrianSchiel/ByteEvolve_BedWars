//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.byteevolve.bedwars.shop.config;

import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.configuration.config.ConfigSections;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.bukkit.configuration.file.YamlConfiguration;

public class ShopConfigHandler {
    public ShopConfigHandler() {
        this.loadConfiguration();
    }

    private void loadConfiguration() {
        File file = new File("plugins/BedWars/shop.yml");
        if (!file.exists()) {
            (new File("plugins/BedWars/")).mkdirs();

            try {
                file.createNewFile();
            } catch (IOException var9) {
                var9.printStackTrace();
            }
        }

        YamlConfiguration configuration = new YamlConfiguration().loadConfiguration(file);
        configuration.options().copyDefaults(true);

        String header = getHeader();
        if (!header.equals(configuration.options().header())) {
            configuration.options().header(header);
        }

        for (ShopEntry entry : ShopEntry.values()) {
            if (!configuration.contains(entry.getSection().getName() + "." + entry.getPath())) {
                configuration.set(entry.getSection().getName() + "." + entry.getPath(), entry.getDefaultValue() + "," + entry.getDefprice() + "," + entry.getDefcurrency());
            } else {
                entry.setValue(configuration.get(entry.getSection().getName() + "." + entry.getPath()));
            }
        }

        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getHeader() {
        String header = "Das ist der Header der Configuration, hier gibt es alle Beschreibungen";
        for (ConfigSections section : ConfigSections.values()) {
            if (section.getName().contains("tab")) {
                String desc = "\n----" + section.getName() + "----\n Beschreibung: " + section.getDescription() + "\n";

                for (ConfigEntries entrie : section.getEntries()) {
                    desc = desc + "\r\n " + entrie.getPath() + ": \n" + entrie.getDescription() + "\n Default-Value: "
                            + entrie.getDefaultValue() + "\r\n";
                }
                header = header + desc;
            }
        }
        return header;
    }

}
