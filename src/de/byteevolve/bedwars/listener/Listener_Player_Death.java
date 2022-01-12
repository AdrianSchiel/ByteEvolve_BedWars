package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.game.EndTime;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import de.byteevolve.bedwars.player.stats.PlayerStats;
import de.byteevolve.bedwars.player.stats.PlayerStatsType;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class Listener_Player_Death implements Listener {
    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).isBed() == false) {
            if (BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).getMembers().size() == 1) {
                Team team = BedWars.getInstance().getGameHandler().getTeam(event.getEntity());
                BedWars.getInstance().getGameHandler().getTeam(event.getEntity()).getMembers().remove(event.getEntity());
                BedWars.getInstance().getGameHandler().getTeams().remove(team);
            }
            if (BedWars.getInstance().getGameHandler().getKills().get(event.getEntity()) == null)
                BedWars.getInstance().getGameHandler().getKills().put(event.getEntity().getKiller(), 1);
            else
                BedWars.getInstance().getGameHandler().getKills().put(event.getEntity().getKiller(), BedWars.getInstance().getGameHandler().getKills().get(event.getEntity().getKiller()) + 1);
            if (event.getEntity().getKiller() != null && event.getEntity().getKiller() instanceof Player)
                event.setDeathMessage(BedWars.getInstance().getPrefix() + "§8The player §a" + event.getEntity().getName() + " §8was killed by the player §a" + event.getEntity().getKiller().getName());
            else
                event.setDeathMessage(BedWars.getInstance().getPrefix() + "§8The player §a" + event.getEntity().getName() + " §8died!");
            event.getDrops().clear();
            event.getEntity().spigot().respawn();
            event.getEntity().setGameMode(GameMode.SPECTATOR);
            if (isOver()) {
                Location location = BedWars.getInstance().getLocationHandler().getLocByName(BedWars.getInstance().getGameHandler().getArena().getName() + "lobby").getAsLocation();
                GameHandler gameHandler = BedWars.getInstance().getGameHandler();
                for (Team team : gameHandler.getTeams()) {
                    if (!team.getMembers().isEmpty()) {
                        new EndTime(team.getTeam(), location);

                    }
                }
            }

        } else {
            event.getDrops().clear();
            if (event.getEntity().getKiller() != null && event.getEntity().getKiller() instanceof Player)
                event.setDeathMessage(BedWars.getInstance().getPrefix() + "§8The player §a" + event.getEntity().getName() + " §8was killed by the player §a" + event.getEntity().getKiller().getName());
            else
                event.setDeathMessage(BedWars.getInstance().getPrefix() + "§8The player §a" + event.getEntity().getName() + " §8died!");
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        if (BedWars.getInstance().getGameHandler().getTeam(event.getPlayer()) == null) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
            new PlayerStats(event.getPlayer().getUniqueId().toString()).add(PlayerStatsType.DEATHS, 1);
        } else {
            Team team = BedWars.getInstance().getGameHandler().getTeam(event.getPlayer());

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
            BedWars.getInstance().getGameHandler().spawnNpcs();
            new BukkitRunnable() {
                int i = 0;

                @Override
                public void run() {
                    i++;
                    if (i == 1) {
                        Location location = BedWars.getInstance().getLocationHandler().getLocByName(BedWars.getInstance().getGameHandler().getArena().getSpawns().get(team.getTeam().getId())).getAsLocation();
                        event.getPlayer().teleport(location);
                        this.cancel();
                    }
                }
            }.runTaskTimer(BedWars.getInstance(), 0, 20);
        }
    }

    private boolean isOver() {
        if (BedWars.getInstance().getGameHandler().getTeams().size() == 1) {
            BedWars.getInstance().getGameHandler().setDone();
            System.out.println("DONE");
            return true;
        } else {
            System.out.println(BedWars.getInstance().getGameHandler().getTeams().size());
            return false;
        }
    }
}
