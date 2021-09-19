package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.Team;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.material.Bed;

public class Listener_Player_Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).isBed() == false) {
            BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).getMembers().remove(event.getEntity());
            event.getEntity().spigot().respawn();
            event.getEntity().setGameMode(GameMode.SPECTATOR);
        }
    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent event){
        if (BedWars.getInstance().getGameHandler().getTeam(event.getPlayer()) == null) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        } else {
            Teams teams = BedWars.getInstance().getGameHandler().getTeam(event.getPlayer()).getTeam();
            Location location = BedWars.getInstance().getLocationHandler().getLocByName(BedWars.getInstance().getGameHandler().getArena().getName() + "team" + teams.getId() + "spawn").getAsLocation();
            event.getPlayer().teleport(location);

        }
    }
}
