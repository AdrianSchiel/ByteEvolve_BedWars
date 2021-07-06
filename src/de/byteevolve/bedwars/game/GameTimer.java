package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {

    private int count = 30;

    @Override
    public void run() {
        count--;
        for(Player player : Bukkit.getOnlinePlayers()){
            player.setLevel(count);
        }

        GameHandler gameHandler = BedWars.getInstance().getGameHandler();

        if(this.count == 10){
            gameHandler.loadResults();
            for(Player player : Bukkit.getOnlinePlayers()){
                player.getInventory().setItem(0, new ItemStack(Material.AIR, 1));
                player.getInventory().setItem(1, new ItemStack(Material.AIR, 1));
                player.getInventory().setItem(4, new ItemStack(Material.AIR, 1));
                if(gameHandler.isPlayerInTeam(player) == null){
                    for(Team team : gameHandler.getTeams()){
                        if(!(team.getMembers().size() >= ConfigEntries.PLAYERSPERTEAM.getAsInt())){
                            team.getMembers().add(player);
                            player.closeInventory();
                            player.sendMessage(BedWars.getInstance().getPrefix() + "§7Du bist nun in Team: §a" + team.getTeam().getColor() + team.getTeam().name());
                            break;
                        }
                    }
                }
            }

        }

        if(this.count == 0){
            gameHandler.teleportPlayers();
            for(Player player : Bukkit.getOnlinePlayers()){
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
                this.cancel();
            }
        }


    }
}
