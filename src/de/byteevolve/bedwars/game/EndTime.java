package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.player.stats.PlayerStats;
import de.byteevolve.bedwars.player.stats.PlayerStatsType;
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
                        player.getInventory().setArmorContents(null);
                        GameHandler gameHandler = BedWars.getInstance().getGameHandler();
                        gameHandler.setGameState(GameState.ENDING);
                        player.sendTitle("§8The team " + team.getColor() + team.name() + " §8 has won the game!", "§8GG to all the participants");
                        player.sendMessage("<>---------------<>");
                        player.sendMessage("§8Kills: §a" + (gameHandler.getKills().containsKey(player) ? gameHandler.getKills().get(player) : 0));
                        player.sendMessage("§8Beds destroyed: §a" + (gameHandler.getBeds().containsKey(player) ? gameHandler.getBeds().get(player) : 0));
                        player.sendMessage("<>---------------<>");
                        PlayerStats playerStats = new PlayerStats(player.getUniqueId().toString());
                        playerStats.add(PlayerStatsType.KILLS, (gameHandler.getKills().containsKey(player) ? gameHandler.getKills().get(player) : 0));
                        playerStats.add(PlayerStatsType.BEDS, (gameHandler.getBeds().containsKey(player) ? gameHandler.getBeds().get(player) : 0));
                        BedWars.getInstance().getScoreboard().sendScoreboard(player);
                    }

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
