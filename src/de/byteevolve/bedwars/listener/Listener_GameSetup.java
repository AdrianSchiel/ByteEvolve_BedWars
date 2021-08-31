
package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.game.GameState;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Listener_GameSetup implements Listener {
    public Listener_GameSetup() {
    }

    @EventHandler
    public void onInterract(PlayerInteractEvent event) {
        if(event.getItem() != null && event.getItem().getItemMeta()!= null){
            ItemStack item = event.getItem();
            if (item.getType().equals(Material.BLAZE_POWDER) && item.getItemMeta().getDisplayName().equalsIgnoreCase("§7« §aGame§2Setup §7»") && event.getPlayer().hasPermission("BedWars.GameSetup")) {
                event.setCancelled(true);
                if (BedWars.getInstance().getGameHandler().getGameState() == GameState.LOBBY) {
                    (new PlayerHandler(event.getPlayer())).openGameSetup();
                }
            }
        }

    }

    @EventHandler
    public void onGameMenu(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("§7« §aGame§2Setup §7»")) {
            Player player = (Player)event.getWhoClicked();
            if (player.hasPermission("BedWars.GameSetup")) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§7« §aForce§2Map §7»")) {
                    (new PlayerHandler(player)).openForceMap();
                    event.setCancelled(true);
                } else if (event.getCurrentItem().getType().equals(Material.GOLD_NUGGET) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7« §aRunde §2starten §7»")) {
                    BedWars.getInstance().getGameHandler().getGameTImer().cancel();
                    BedWars.getInstance().getGameHandler().manageFastStart();
                    event.setCancelled(true);
                }
            }
        }

    }
}
