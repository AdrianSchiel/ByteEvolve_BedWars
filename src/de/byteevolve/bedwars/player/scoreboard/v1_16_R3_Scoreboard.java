package de.byteevolve.bedwars.player.scoreboard;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.player.stats.PlayerStats;
import de.byteevolve.bedwars.player.stats.PlayerStatsType;
import net.minecraft.server.v1_16_R3.*;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_16_R3_Scoreboard implements BWScoreboard {

    @Override
    public void sendScoreboard(Player player) {
        Scoreboard sb = new Scoreboard();
        ScoreboardObjective obj = sb.registerObjective(ConfigEntries.SCOREBOARDNAME.getAsString(), IScoreboardCriteria.DUMMY, new ChatMessage(ConfigEntries.SCOREBOARDNAME.getAsString()), IScoreboardCriteria.EnumScoreboardHealthDisplay.INTEGER);
        PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
        obj.setDisplayName(new ChatMessage(ConfigEntries.SCOREBOARDNAME.getAsString()));

        PlayerStats playerStats = new PlayerStats(player.getUniqueId().toString());

        String map;
        if(BedWars.getInstance().getGameHandler().getArena() != null) {
            map = BedWars.getInstance().getGameHandler().getArena().getDisplayname().replaceAll("&", "§");
        } else map = "-";

        PacketPlayOutScoreboardObjective removePacket = new PacketPlayOutScoreboardObjective(obj, 1);
        sendPacket(removePacket, player);
        sendPacket(createpacket, player);
        sendPacket(display, player);

        int i = 16;
        for(String line : ConfigEntries.SCOREBOARD.getAsString().split("\n")){
            line = line.replaceAll("%MAP%", map);
            line = line.replaceAll("%KILLS%", String.valueOf(playerStats.get(PlayerStatsType.KILLS)));
            line = line.replaceAll("%DEATHS%", String.valueOf(playerStats.get(PlayerStatsType.DEATHS)));
            line = line.replaceAll("%RANK%", String.valueOf(playerStats.getRank()));
            line = line.replaceAll("%KD%", String.valueOf(playerStats.getKD()));

            PacketPlayOutScoreboardScore packet= new PacketPlayOutScoreboardScore(ScoreboardServer.Action.CHANGE,ConfigEntries.SCOREBOARDNAME.getAsString(),line,i);
            sendPacket(packet, player);

            i--;
        }
    }
    public void sendPacket(Packet<?> packet, Player player) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }

}
