package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
        }


    }
}
