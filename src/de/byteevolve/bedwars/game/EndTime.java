package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class EndTime {
    Teams team;
    Location location;

    public EndTime(Teams team, Location location) {
        this.team = team;
        this.location = location;
        start();
    }

    public void start() {
        new BukkitRunnable() {
            int i = 0;
            @Override
            public void run() {
                i++;
                if (i == 1) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.teleport(location);
                        player.setGameMode(GameMode.ADVENTURE);
                        player.getInventory().clear();
                    }
                    Bukkit.getServer().broadcastMessage(BedWars.getInstance().getPrefix() + "§8The team " + team.getColor() + team.name() + " §8 has won the game! GG to all the participants");

                }
                if (i == 20) {
                    //   BedWars.getInstance().getGameHandler().deleteOldMap();
                    Bukkit.shutdown();
                    this.cancel();
                }
            }
        }.runTaskTimer(BedWars.getInstance(), 0, 20);
    }
}
