package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Listener_Quit implements Listener {


    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        switch (BedWars.getInstance().getGameHandler().getGameState()){
            case LOBBY:
                new BukkitRunnable(){
                    @Override
                    public void run() {
                        BedWars.getInstance().getGameHandler().manageGameStart();
                    }
                }.runTaskLaterAsynchronously(BedWars.getInstance(), 3);
                break;
        }
    }

}
