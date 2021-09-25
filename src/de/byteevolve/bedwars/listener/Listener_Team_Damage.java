package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Listener_Team_Damage implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        if(event.getEntity() instanceof Player && event.getDamager() instanceof  Player){
            if(BedWars.getInstance().getGameHandler().getTeam((Player) event.getEntity()) == BedWars.getInstance().getGameHandler().getTeam((Player) event.getDamager())){
                event.setCancelled(true);
            }
        }
    }
}
