package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.game.GameState;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listener_Map_Protection implements Listener {
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (BedWars.getInstance().getGameHandler().getBlocks().contains(event.getBlock().getLocation()) || event.getBlock().getType().equals(Material.BED_BLOCK)) {
            BedWars.getInstance().getGameHandler().getBlocks().remove(event.getBlock().getLocation());
        } else
            event.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        BedWars.getInstance().getGameHandler().getBlocks().add(event.getBlock().getLocation());
    }

    @EventHandler
    public void onDestroy(PlayerInteractEvent event){
        if(event.getAction() == Action.PHYSICAL && event.getClickedBlock().getType() == Material.SOIL)
            event.setCancelled(true);
    }
    @EventHandler
    public void onUpdate(BlockPhysicsEvent event){
        if(BedWars.getInstance().getGameHandler().getGameState() == GameState.LOBBY){
            if(event.getChangedType() == Material.BED_BLOCK || event.getBlock().getType() == Material.BED_BLOCK)
                event.setCancelled(true);
        }
    }
}
