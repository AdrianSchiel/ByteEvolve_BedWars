package de.byteevolve.bedwars.listener;

import com.mojang.authlib.BaseUserAuthentication;
import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.EndTime;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.game.Team;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.material.Bed;

public class Listener_Player_Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).isBed() == false) {
            if (BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).getMembers().size() == 1) {
                Team team = BedWars.getInstance().getGameHandler().getTeam(event.getEntity());
                BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).getMembers().remove(event.getEntity());
                BedWars.getInstance().getGameHandler().getTeams().remove(team);
            }
            event.getEntity().spigot().respawn();
            event.getEntity().setGameMode(GameMode.SPECTATOR);
            if (isOver()) {
                Location location = BedWars.getInstance().getLocationHandler().getLocByName(BedWars.getInstance().getGameHandler().getArena().getName() + "lobby").getAsLocation();
                new EndTime(BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).getTeam(), location);
            }

        } else {
            event.getEntity().spigot().respawn();
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (BedWars.getInstance().getGameHandler().getTeam(event.getPlayer()) == null) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        } else {
            Teams teams = BedWars.getInstance().getGameHandler().getTeam(event.getPlayer()).getTeam();
            Location location = BedWars.getInstance().getLocationHandler().getLocByName(BedWars.getInstance().getGameHandler().getArena().getName() + "team" + teams.getId() + "spawn").getAsLocation();
            event.getPlayer().teleport(location);

        }
    }

    private boolean isOver() {
        if (BedWars.getInstance().getGameHandler().getTeams().size() == 1) {
            BedWars.getInstance().getGameHandler().setDone();
            return true;
        } else
        return false;
    }
}
