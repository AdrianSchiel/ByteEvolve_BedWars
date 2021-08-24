package de.byteevolve.bedwars.listener;


import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.shop.npc.Npc;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.List;

public class Listener_Npc implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Vector pos1 = new Location(player.getWorld(),player.getLocation().getX()+5,player.getLocation().getY()+2,player.getLocation().getZ()+5).toVector();
        Vector pos2 = new Location(player.getWorld(),player.getLocation().getX()-5,player.getLocation().getY(),player.getLocation().getZ()-5).toVector();

        HashMap<Teams, Npc> teamNpc = BedWars.getInstance().getTeamNpc();
        for (Npc npc : teamNpc.values()) {

            Location npcLocation = npc.getLocation();
            final Vector npcVector = npcLocation.toVector();

            if (npcVector.isInAABB(pos2, pos1)) {
                npc.teleport(npcLocation.setDirection(player.getLocation().subtract(npcLocation).toVector()));
            }
        }
    }
}
