
package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.shop.ShopHandler;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;

public class Listener_Shop implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {
        if (event.getRightClicked() instanceof ArmorStand) {
            (new ShopHandler()).openBlockTab(event.getPlayer());
        }

    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getInventory() != null && event.getCurrentItem() != null && event.getWhoClicked() instanceof Player) {
            Player player = (Player)event.getWhoClicked();
            if (event.getInventory().getName().equalsIgnoreCase("§5Blocks")) {
                GameHandler gameHandler = BedWars.getInstance().getGameHandler();
                switch(event.getCurrentItem().getType()) {
                    case WOOL:
                        Teams team = gameHandler.getTeam(player).getTeam();
                        player.getInventory().addItem(new ItemBuilder(Material.WOOL, 1).setName(team.getColor() + "Wool").setSubId(team.getWoolid()).build());
                        event.setCancelled(true);
                }
            }
        }

    }
}
