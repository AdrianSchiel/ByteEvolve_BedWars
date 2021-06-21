package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener_Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(BedWars.getInstance().getArenaHandler().getArenas().isEmpty()){
            player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.NOARENAEXISTS.getAsString());
            return;
        }
        switch (BedWars.getInstance().getGameHandler().getGameState()){
            case LOBBY:
                PlayerHandler playerHandler = new PlayerHandler(player);
                playerHandler.setJoinEquip();
                BedWars.getInstance().getGameHandler().manageGameStart();
                break;
        }
    }


}
