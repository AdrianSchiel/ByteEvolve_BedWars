package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.location.Loc;
import de.byteevolve.bedwars.location.LocationHandler;
import jdk.tools.jaotc.Main;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.material.Bed;

public class Listener_Bed_Break implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getBlock().getType().equals(Material.BED_BLOCK)) {
            for (String string : BedWars.getInstance().getGameHandler().getArena().getBeds()) {
                Loc loc = BedWars.getInstance().getLocationHandler().getLocByName(string);
                if (event.getBlock().getLocation().equals(loc.getAsLocation())) {
                    System.out.println(Integer.parseInt(string.split("team")[0].substring(0)));
                    int id = Integer.parseInt(string.split("team")[0].substring(0));
                    // Teams teams = Teams.fromID(id);
                    Team team = BedWars.getInstance().getGameHandler().getTeams().get(id);
                    team.setBed(false);

                }

            }
        }
    }
}
