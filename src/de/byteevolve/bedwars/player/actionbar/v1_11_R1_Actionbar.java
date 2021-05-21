package de.byteevolve.bedwars.player.actionbar;

import net.minecraft.server.v1_11_R1.IChatBaseComponent;
import net.minecraft.server.v1_11_R1.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_11_R1_Actionbar implements BWActionbar {

    @Override
    public void send(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte)2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }


}
