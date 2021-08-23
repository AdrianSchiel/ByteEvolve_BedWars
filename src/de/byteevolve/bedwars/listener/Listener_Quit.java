
package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.game.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class Listener_Quit implements Listener {
    public Listener_Quit() {
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        switch (BedWars.getInstance().getGameHandler().getGameState()) {
            case LOBBY:
                (new BukkitRunnable() {
                    public void run() {
                        BedWars.getInstance().getGameHandler().manageGameStart();
                    }
                }).runTaskLaterAsynchronously(BedWars.getInstance(), 3L);
                break;
            case INGAME:
                if (BedWars.getInstance().getGameHandler().isPlayerInTeam(player) != null) {
                    Team team = BedWars.getInstance().getGameHandler().isPlayerInTeam(player);
                    String prefix = BedWars.getInstance().getPrefix();
                    event.setQuitMessage(prefix + "§8Der Spieler " + team.getTeam().getColor() + player.getName() + " §8hat das Spiel §cverlassen");
                }
        }

    }
}
