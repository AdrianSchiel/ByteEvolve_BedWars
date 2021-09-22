package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.location.Loc;
import de.byteevolve.bedwars.location.LocationHandler;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Bed;

public class Listener_Bed_Break implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.BED_BLOCK)) {
            for (Loc loc : BedWars.getInstance().getLocationHandler().getLocList("bed")) {
                loc.setYaw(0);
                loc.setPitch(0);
                if (event.getBlock().getLocation().toString().contains(loc.getAsLocation().toString())) {
                    int id = Integer.parseInt(loc.getName().split("team")[1].split("bed")[0]);
                    Teams teams = Teams.fromID(id);
                    Team team = BedWars.getInstance().getGameHandler().getTeams().get(id);
                    if (team.isBed() == true) {
                        if (!team.getMembers().contains(event.getPlayer())) {
                            Bukkit.getServer().broadcastMessage(BedWars.getInstance().getPrefix() + "§8The bed from team " + teams.getColor() + teams.name() + " §8was destroyed!");
                            team.setBed(false);
                        } else {
                            event.getPlayer().sendMessage(BedWars.getInstance().getPrefix() + "§8You cant destroy your own bed!");
                            event.setCancelled(true);
                        }
                    }
                }

            }
        }
    }
}
