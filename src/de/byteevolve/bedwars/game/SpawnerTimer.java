package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.location.Loc;
import de.byteevolve.bedwars.location.LocationHandler;
import de.byteevolve.bedwars.shop.config.ShopEntry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class SpawnerTimer {
    public SpawnerTimer() {
        GameHandler gameHandler = BedWars.getInstance().getGameHandler();
        String arena = gameHandler.getArena().getName();
        startSpawner1(arena);
        startSpawner2(arena);
        startSpawner3(arena);
    }


    private void startSpawner1(String arena) {
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (BedWars.getInstance().getGameHandler().getGameState().equals(GameState.INGAME)) {
                    i++;
                    LocationHandler locationHandler = BedWars.getInstance().getLocationHandler();
                    List<Loc> loc = locationHandler.getLocList(arena + "bronze");
                    for (Loc locs : loc) {
                        Material material = Material.valueOf(ShopEntry.CURRENCY1.getValue().toString().split(",")[0]);
                        Bukkit.getWorld("world").dropItem(locs.getAsLocation(), new ItemBuilder(material, 1).setName("§8§l"+material.name()).build());
                    }
                }else
                    this.cancel();
            }
        }.runTaskTimer(BedWars.getInstance(), 0, 40);
    }

    private void startSpawner2(String arena) {
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (BedWars.getInstance().getGameHandler().getGameState().equals(GameState.INGAME)) {
                    i++;
                    LocationHandler locationHandler = BedWars.getInstance().getLocationHandler();
                    List<Loc> loc = locationHandler.getLocList(arena + "iron");
                    for (Loc locs : loc) {
                        Material material = Material.valueOf(ShopEntry.CURRENCY2.getValue().toString().split(",")[0]);
                        Bukkit.getWorld("world").dropItem(locs.getAsLocation(), new ItemBuilder(material, 1).setName("§8§l"+material.name()).build());
                    }
                }else
                    this.cancel();
            }
        }.runTaskTimer(BedWars.getInstance(), 0, 20*10);
    }

    private void startSpawner3(String arena) {
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if (BedWars.getInstance().getGameHandler().getGameState().equals(GameState.INGAME)) {
                    i++;
                    LocationHandler locationHandler = BedWars.getInstance().getLocationHandler();
                    List<Loc> loc = locationHandler.getLocList(arena + "gold");
                    for (Loc locs : loc) {
                        Material material = Material.valueOf(ShopEntry.CURRENCY3.getValue().toString().split(",")[0]);
                        Bukkit.getWorld("world").dropItem(locs.getAsLocation(), new ItemBuilder(material, 1).setName("§8§l"+material.name()).build());
                    }
                }else
                    this.cancel();
            }
        }.runTaskTimer(BedWars.getInstance(), 0, 20 * 30);
    }
}
