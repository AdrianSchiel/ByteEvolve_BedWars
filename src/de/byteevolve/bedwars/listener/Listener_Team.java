package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.game.GameState;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Listener_Team implements Listener {

    @EventHandler
    public void onInterract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (event.getItem() != null && event.getItem().getItemMeta() != null && event.getItem().getItemMeta().getDisplayName() != null) {
            if (item.getType().equals(Material.BED)
                    && item.getItemMeta().getDisplayName().equalsIgnoreCase("§aTeamauswahl")) {
                event.setCancelled(true);
                if (BedWars.getInstance().getGameHandler().getGameState() == GameState.LOBBY) {
                    new PlayerHandler(event.getPlayer()).openTeamSelection();
                }
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getInventory().getTitle().equalsIgnoreCase("§aTeamauswahl")) {
            event.setCancelled(true);
            ItemStack itemStack = event.getCurrentItem();
            ItemMeta itemMeta = itemStack.getItemMeta();
            int id = Integer.valueOf(itemMeta.getLore().get(0).replaceAll("§7ID:", ""));
            Team team = BedWars.getInstance().getGameHandler().getTeams().get(id);
            if (!(team.getMembers().size() >= ConfigEntries.PLAYERSPERTEAM.getAsInt())) {
                if (!team.getMembers().contains(player)) {
                    for (Team teams : BedWars.getInstance().getGameHandler().getTeams()) {
                        if (teams.getMembers().contains(player)) teams.getMembers().remove(player);
                    }
                    team.getMembers().add(player);
                    player.closeInventory();
                    player.sendMessage(BedWars.getInstance().getPrefix() + "§7Du bist nun in Team: §a" + team.getTeam().getColor() + team.getTeam().name());
                } else {
                    player.closeInventory();
                    player.sendMessage(BedWars.getInstance().getPrefix() + "§cDu bist schon in dem Team.");
                }
            } else {
                player.closeInventory();
                player.sendMessage(BedWars.getInstance().getPrefix() + "§cDas Team ist schon voll");
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (BedWars.getInstance().getGameHandler().getGameState().equals(GameState.LOBBY) || BedWars.getInstance().getGameHandler().getGameState().equals(GameState.ENDING))
            event.setCancelled(true);
    }
}
