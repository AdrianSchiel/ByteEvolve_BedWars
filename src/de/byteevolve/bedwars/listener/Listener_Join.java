
package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.location.Loc;
import de.byteevolve.bedwars.location.LocationHandler;
import de.byteevolve.bedwars.player.PlayerHandler;

import java.util.Iterator;

import de.byteevolve.bedwars.shop.npc.Npc;
import de.byteevolve.bedwars.shop.npc.PacketReader;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener_Join implements Listener {
    public Listener_Join() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String prefix = BedWars.getInstance().getPrefix();
        new PacketReader(player).inject();
        if (BedWars.getInstance().getArenaHandler().getArenas().isEmpty()) {
            player.sendMessage(prefix + ConfigEntries.NOARENAEXISTS.getAsString());
            event.setJoinMessage(null);
        } else {
            GameHandler gameHandler = BedWars.getInstance().getGameHandler();
            Arena arena = BedWars.getInstance().getGameHandler().getArena();
            String arenaname = gameHandler.getArena().getName();
            switch (gameHandler.getGameState()) {
                case LOBBY:
                    player.setGameMode(GameMode.ADVENTURE);
                    PlayerHandler playerHandler = new PlayerHandler(player);
                    playerHandler.setJoinEquip();
                    event.getPlayer().teleport(BedWars.getInstance().getLocationHandler().getLocByName(arena.getName() + "lobby").getAsLocation());
                    BedWars.getInstance().getGameHandler().manageGameStart();
                    event.setJoinMessage(prefix + "§8Der Spieler §a" + player.getName() + "§8 hat das Spiel §abetreten");
                    break;
                case INGAME:
                    if (gameHandler.hasPlayed(player)) {
                        player.setGameMode(GameMode.SURVIVAL);
                        Team team = gameHandler.getTeam(player);
                        event.setJoinMessage(prefix + "§8Der Spieler " + team.getTeam().getColor() + player.getName() + " §8ist dem Spiel wieder  §abeigetreten");
                        LocationHandler locationHandler = BedWars.getInstance().getLocationHandler();
                        Loc loc = locationHandler.getLocByName(arenaname + "team" + team.getTeam().getId() + "spawn");
                        player.teleport(loc.getAsLocation());
                    } else {

                        for (Player player1 : Bukkit.getOnlinePlayers()) {
                            if (player.getGameMode().equals(GameMode.SURVIVAL)) {
                                player.hidePlayer(player1);
                            }
                        }

                        player.setGameMode(GameMode.SPECTATOR);
                    }
                    break;
                default:
                    event.setJoinMessage(null);
                    break;
            }

        }
    }
}
