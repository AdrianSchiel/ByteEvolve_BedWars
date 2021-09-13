//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package de.byteevolve.bedwars.shop.config;

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

        new YamlConfiguration();
        YamlConfiguration configuration = YamlConfiguration.loadConfiguration(file);
        configuration.options().copyDefaults(true);
        String header = this.getHeader();
        if (!header.equals(configuration.options().header())) {
            configuration.options().header(header);
        }

        ShopEntry[] var4 = ShopEntry.values();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            ShopEntry entry = var4[var6];
            String var10001 = entry.getSection().getName();
            if (!configuration.contains(var10001 + "." + entry.getPath())) {
                var10001 = entry.getSection().getName() + "." + entry.getPath();
                Object var10002 = entry.getValue();
                if(entry.getDefprice() == 0){
                    configuration.set(var10001, var10002 + "," + entry.getCurrency());
                }
            } else {
                String var10 = entry.getSection().getName();
                entry.setValue(configuration.get(var10 + "." + entry.getPath()));
            }
        }

        try {
            configuration.save(file);
        } catch (IOException var8) {
            var8.printStackTrace();
        }

    }

    public String getHeader() {
        String header = "Das ist der Header der Configuration, hier gibt es alle Beschreibungen";
        ConfigSections[] var2 = ConfigSections.values();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            ConfigSections section = var2[var4];
            if (section.toString().contains("SHOP_")) {
                String var10000 = section.getName();
                String desc = "\n----" + var10000 + "----\n Beschreibung: " + section.getDescription() + "\n";

                ShopEntry entrie;
                for(Iterator var7 = section.getShopEntries().iterator(); var7.hasNext(); desc = desc + "\r\n " + entrie.getPath() + ": \n Default-Value: " + entrie.getDefaultValue() + ":" + entrie.getDefprice() + "," + entrie.getDefcurrency() + "\r\n") {
                    entrie = (ShopEntry)var7.next();
                }

                header = header + desc;
            }
        }

        return header;
    }
}
