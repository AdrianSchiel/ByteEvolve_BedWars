package de.byteevolve.bedwars;


import de.byteevolve.bedwars.database.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWars extends JavaPlugin {

    private static BedWars instance;
    private MySQL mySQL;

    @Override
    public void onEnable() {
        instance = this;
        this.mySQL = new MySQL("localhost", "root", "", "bedwars", 3306);
    }

    public static BedWars getInstance() {
        return instance;
    }
}
