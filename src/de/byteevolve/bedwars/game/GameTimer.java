package de.byteevolve.bedwars.game;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameTimer extends BukkitRunnable {

    private int count = 60;

    @Override
    public void run() {
        count--;
        for(Player player : Bukkit.getOnlinePlayers()){
            player.setLevel(count);
        }


    }
}
