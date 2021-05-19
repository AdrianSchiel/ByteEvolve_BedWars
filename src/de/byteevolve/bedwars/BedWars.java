package de.byteevolve.bedwars;


import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.ArenaHandler;
import de.byteevolve.bedwars.database.MySQL;
import de.byteevolve.bedwars.location.LocationHandler;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BedWars extends JavaPlugin {

    private static BedWars instance;
    private MySQL mySQL;
    private LocationHandler locationHandler;
    private ArenaHandler arenaHandler;
    public static int players, teams;

    @Override
    public void onEnable() {
        instance = this;
        this.mySQL = new MySQL("localhost", "root", "", "bedwars", 3306);
        this.locationHandler = new LocationHandler();
        this.arenaHandler = new ArenaHandler();

        Arena arena = new Arena("Test");
        arena.update();
        new BukkitRunnable(){

            @Override
            public void run() {
                arena.setPlayers(10);
                arena.setDisplayname("&aLOL");
                arena.update();
            }
        }.runTaskLaterAsynchronously(this, 100);

    }

    public LocationHandler getLocationHandler() {
        return locationHandler;
    }

    public static BedWars getInstance() {
        return instance;
    }

    public ArenaHandler getArenaHandler() {
        return arenaHandler;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
