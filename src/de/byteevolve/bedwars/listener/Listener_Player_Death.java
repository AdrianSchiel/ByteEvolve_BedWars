package de.byteevolve.bedwars.listener;

import com.mojang.authlib.BaseUserAuthentication;
import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.game.EndTime;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.shop.config.ShopEntry;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
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

        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (BedWars.getInstance().getGameHandler().getTeam(event.getPlayer()) == null) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        } else {
            Team team = BedWars.getInstance().getGameHandler().getTeam(event.getPlayer());
            Teams teams = team.getTeam();

            Location location = BedWars.getInstance().getLocationHandler().getLocByName(BedWars.getInstance().getGameHandler().getArena().getName() + "team" + teams.getId() + "spawn").getAsLocation();
            event.getPlayer().teleport(location);
            if (team.getHasAxe().contains(event.getPlayer())) {
                ItemStack itemStack = new ItemBuilder(Material.WOOD_AXE, 1).setName("§CWooden Axe").build();
                event.getPlayer().getInventory().addItem(itemStack);
            } else if (team.getHasPickaxe().contains(event.getPlayer())) {
                ItemStack itemStack = new ItemBuilder(Material.WOOD_PICKAXE, 1).setName("§CWooden Pickaxe").build();
                event.getPlayer().getInventory().addItem(itemStack);
            } else if (team.getHasSword().contains(event.getPlayer())) {
                ItemStack itemStack = new ItemBuilder(Material.WOOD_SWORD, 1).setName("§CWooden Sword").build();
                event.getPlayer().getInventory().addItem(itemStack);
            } else if (team.getHasShears().contains(event.getPlayer())) {
                ItemStack itemStack = new ItemBuilder(Material.SHEARS, 1).setName("§cShears").build();
                event.getPlayer().getInventory().addItem(itemStack);
            }
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
