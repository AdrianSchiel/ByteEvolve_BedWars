package de.byteevolve.bedwars.player.scoreboard;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.game.GameState;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.player.stats.PlayerStats;
import de.byteevolve.bedwars.player.stats.PlayerStatsType;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_8_R3_Scoreboard implements BWScoreboard {

    @Override
    public void sendScoreboard(Player player) {
        Scoreboard sb = new Scoreboard();
        ScoreboardObjective obj = sb.registerObjective(ConfigEntries.SCOREBOARDNAME.getAsString(), IScoreboardCriteria.b);
        PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
        obj.setDisplayName("BedWars");

        PlayerStats playerStats = new PlayerStats(player.getUniqueId().toString());
        if (BedWars.getInstance().getGameHandler().getGameState().equals(GameState.INGAME)) {
            PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
            sendPacket(removePacket, player);
            sendPacket(createpacket, player);
            sendPacket(display, player);
            int i = 1;
            GameHandler gameHandler = BedWars.getInstance().getGameHandler();
            for (Team team : gameHandler.getTeams()) {
                String bed;
                Teams teams = team.getTeam();
                if (team.isBed()) {
                    bed = "§a❤";
                } else
                    bed = "§c❤";
                ScoreboardScore score = new ScoreboardScore(sb, obj, teams.getColor() + teams.name() + " §8| " + bed);
                score.setScore(team.getMembers().size());
                PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(score);
                sendPacket(packet, player);
                i++;
            }
            ScoreboardScore scores = new ScoreboardScore(sb, obj, "§0§8§l§M-------------------");
            ScoreboardScore scores2 = new ScoreboardScore(sb, obj, "§1§8§l§M-------------------");
            scores.setScore(0);
            scores2.setScore(i + 1);
            PacketPlayOutScoreboardScore packet2 = new PacketPlayOutScoreboardScore(scores);
            sendPacket(packet2, player);
            PacketPlayOutScoreboardScore packet3 = new PacketPlayOutScoreboardScore(scores2);
            sendPacket(packet3, player);

        } else {
            String map;
            if (BedWars.getInstance().getGameHandler().getArena() != null) {
                map = BedWars.getInstance().getGameHandler().getArena().getDisplayname().replaceAll("&", "§");
            } else map = "-";

            PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
            sendPacket(removePacket, player);
            sendPacket(createpacket, player);
            sendPacket(display, player);

            int i = 16;
            for (String line : ConfigEntries.SCOREBOARD.getAsString().split("\n")) {
                line = line.replaceAll("%MAP%", map);
                line = line.replaceAll("%KILLS%", String.valueOf(playerStats.get(PlayerStatsType.KILLS)));
                line = line.replaceAll("%DEATHS%", String.valueOf(playerStats.get(PlayerStatsType.DEATHS)));
                line = line.replaceAll("%RANK%", String.valueOf(playerStats.getRank()));
                line = line.replaceAll("%BEDS%", String.valueOf(playerStats.get(PlayerStatsType.BEDS)));
                line = line.replaceAll("%KD%", String.valueOf(playerStats.getKD()));


                ScoreboardScore score = new ScoreboardScore(sb, obj, line);
                score.setScore(i);

                PacketPlayOutScoreboardScore packet = new PacketPlayOutScoreboardScore(score);
                sendPacket(packet, player);

                i--;
            }
        }
    }

    public void sendPacket(Packet<?> packet, Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
