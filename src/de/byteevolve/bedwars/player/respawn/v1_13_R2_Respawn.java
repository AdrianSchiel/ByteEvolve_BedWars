package de.byteevolve.bedwars.player.respawn;

import de.byteevolve.bedwars.BedWars;
import net.minecraft.server.v1_13_R2.PacketPlayInClientCommand;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_13_R2_Respawn implements BWRespawn {

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
