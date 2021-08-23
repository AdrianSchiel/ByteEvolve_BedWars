package de.byteevolve.bedwars.game;


import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class FastTimer extends BukkitRunnable {
    private int count = 11;

    public FastTimer() {
    }

    public void run() {
        --this.count;
        Iterator var1 = Bukkit.getOnlinePlayers().iterator();

        while (var1.hasNext()) {
            Player player = (Player) var1.next();
            player.setLevel(this.count);
        }

        GameHandler gameHandler = BedWars.getInstance().getGameHandler();
        Player player;
        Iterator var7;
        if (this.count == 10) {
            gameHandler.loadResults();
            var7 = Bukkit.getOnlinePlayers().iterator();

            label46:
            while (true) {
                while (true) {
                    do {
                        if (!var7.hasNext()) {
                            break label46;
                        }

                        player = (Player) var7.next();
                        player.getInventory().setItem(0, new ItemStack(Material.AIR, 1));
                        player.getInventory().setItem(1, new ItemStack(Material.AIR, 1));
                        player.getInventory().setItem(4, new ItemStack(Material.AIR, 1));
                    } while (gameHandler.isPlayerInTeam(player) != null);

                    Iterator var4 = gameHandler.getTeams().iterator();

                    while (var4.hasNext()) {
                        Team team = (Team) var4.next();
                        if (team.getMembers().size() < ConfigEntries.PLAYERSPERTEAM.getAsInt()) {
                            team.getMembers().add(player);
                            player.closeInventory();
                            String var10001 = BedWars.getInstance().getPrefix();
                            player.sendMessage(var10001 + "§7Du bist nun in Team: §a" + team.getTeam().getColor() + team.getTeam().name());
                            break;
                        }
                    }
                }
            }
        }

        if (this.count == 0) {
            gameHandler.teleportPlayers();
            var7 = Bukkit.getOnlinePlayers().iterator();

            while (var7.hasNext()) {
                player = (Player) var7.next();
                player.getInventory().clear();
                player.getInventory().setArmorContents((ItemStack[]) null);
                player.setGameMode(GameMode.SURVIVAL);
                gameHandler.setGameState(GameState.INGAME);
            }

            this.cancel();
        }

    }
}
