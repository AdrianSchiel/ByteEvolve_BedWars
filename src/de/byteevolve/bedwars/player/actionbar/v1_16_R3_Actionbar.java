package de.byteevolve.bedwars.player.actionbar;

import net.minecraft.server.v1_16_R3.ChatMessageType;
import net.minecraft.server.v1_16_R3.IChatBaseComponent;
import net.minecraft.server.v1_16_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class v1_16_R3_Actionbar implements BWActionbar {

    @Override
    public void send(Player player, String message) {
        PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), ChatMessageType.GAME_INFO, player.getUniqueId());
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}
