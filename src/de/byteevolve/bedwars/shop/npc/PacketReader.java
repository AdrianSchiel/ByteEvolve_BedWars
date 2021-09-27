package de.byteevolve.bedwars.shop.npc;

import de.byteevolve.bedwars.shop.ShopHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayInCustomPayload;
import net.minecraft.server.v1_8_R3.PacketPlayOutKickDisconnect;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

public class PacketReader {
    Channel channel;
    Player player;

    public PacketReader(Player player) {
        this.player = player;
    }

    public void inject() {
        CraftPlayer cPlayer = (CraftPlayer) this.player;
        channel = cPlayer.getHandle().playerConnection.networkManager.channel;
        channel.pipeline().addAfter("decoder", "PacketInjector", new MessageToMessageDecoder<Packet<?>>() {
            @Override
            protected void decode(ChannelHandlerContext arg0, Packet<?> packet, List<Object> arg2) throws Exception {
                arg2.add(packet);
                readPacket(packet);
            }
        });
    }

    public void readPacket(Packet<?> packet) {
        try {
            if (packet.getClass().getSimpleName().equalsIgnoreCase("PacketPlayInUseEntity")) {
                int id = (Integer) getValue(packet, "a");
                if (id >= 0 + 2000 && id <= 10 + 2000) {
                    if (getValue(packet, "action").toString().equalsIgnoreCase("ATTACK")) {
                        new ShopHandler().openBlockTab(player);
                    } else if (getValue(packet, "action").toString().equalsIgnoreCase("INTERACT")) {
                        new ShopHandler().openBlockTab(player);
                    }
                }
            }
        } catch (
                Exception exception) {
        }

    }

    public Object getValue(Object obj, String name) {
        try {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
        }
        return null;
    }
}
