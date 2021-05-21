package de.byteevolve.bedwars.player.respawn;

import de.byteevolve.bedwars.BedWars;
import net.minecraft.server.v1_11_R1.PacketPlayInClientCommand;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_11_R1_Respawn implements BWRespawn {

    @Override
    public void respawn(Player player) {
        final CraftPlayer craftPlayer = (CraftPlayer) player;
        BedWars.getInstance().getServer().getScheduler()
                .scheduleSyncDelayedTask(BedWars.getInstance(), new Runnable() {
                    public void run() {
                        if (player.isDead()) {
                            craftPlayer.getHandle().playerConnection
                                    .a(new PacketPlayInClientCommand(
                                            PacketPlayInClientCommand.EnumClientCommand.PERFORM_RESPAWN));
                        }
                    }
                });
    }

}
