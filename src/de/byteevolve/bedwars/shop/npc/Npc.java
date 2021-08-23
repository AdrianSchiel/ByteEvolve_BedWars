package de.byteevolve.bedwars.shop.npc;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Teams;
import it.unimi.dsi.fastutil.Hash;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.util.CraftChatMessage;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Npc extends Reflactions {
    int entityid;
    Location location;
    String name;
    GameProfile gameProfile;
    HashMap<Teams, Npc> teamNpc = BedWars.getInstance().getTeamNpc();
    Teams teams;

    public Npc(String name, Location location, int id, Teams teams) {
        this.name = name;
        entityid = id + 2000;
        gameProfile = new GameProfile(UUID.randomUUID(), name);
        this.location = location;
        this.teams = teams;
    }

    public Location getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void changeSkin(Player player) {
        GameProfile profile = ((CraftPlayer) player).getHandle().getProfile();
        Property property = profile.getProperties().get("textures").stream().findFirst().orElse(null);
        gameProfile.getProperties().put("texture", new Property("textures", property.getValue(), property.getSignature()));
    }

    public void spawn(Player player) {
        PacketPlayOutNamedEntitySpawn packetPlayOutNamedEntitySpawn = new PacketPlayOutNamedEntitySpawn();
        setValue(packetPlayOutNamedEntitySpawn, "a", entityid);
        setValue(packetPlayOutNamedEntitySpawn, "b", gameProfile.getId());
        setValue(packetPlayOutNamedEntitySpawn, "c", getFixLocation(location.getX()));
        setValue(packetPlayOutNamedEntitySpawn, "d", getFixLocation(location.getY()));
        setValue(packetPlayOutNamedEntitySpawn, "e", getFixLocation(location.getZ()));
        setValue(packetPlayOutNamedEntitySpawn, "f", getFixRotation(location.getYaw()));
        setValue(packetPlayOutNamedEntitySpawn, "g", getFixRotation(location.getPitch()));
        setValue(packetPlayOutNamedEntitySpawn, "h", 0);
        DataWatcher dataWatcher = new DataWatcher(null);
        dataWatcher.a(10, (byte) 127);
        dataWatcher.a(6, (float) 20);
        setValue(packetPlayOutNamedEntitySpawn, "i", dataWatcher);
        addToTablist(player);
        sendPacket(packetPlayOutNamedEntitySpawn, player);
        headRotation(location.getYaw(), location.getPitch());
        removefromTab(teams);
    }

    public void addToTablist(Player player) {
        PacketPlayOutPlayerInfo packetPlayOutPlayerInfo = new PacketPlayOutPlayerInfo();
        PacketPlayOutPlayerInfo.PlayerInfoData data = packetPlayOutPlayerInfo.new PlayerInfoData(gameProfile, 1, WorldSettings.EnumGamemode.NOT_SET, CraftChatMessage.fromString("")[0]);
        List<PacketPlayOutPlayerInfo.PlayerInfoData> players = (List<PacketPlayOutPlayerInfo.PlayerInfoData>) getValue(packetPlayOutPlayerInfo, "b");
        players.add(data);
        setValue(packetPlayOutPlayerInfo, "a", PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER);
        setValue(packetPlayOutPlayerInfo, "b", players);
        sendPacket(packetPlayOutPlayerInfo, player);

    }

    public void headRotation(float yaw, float pitch) {
        PacketPlayOutEntity.PacketPlayOutEntityLook packet = new PacketPlayOutEntity.PacketPlayOutEntityLook(entityid, getFixRotation(yaw), getFixRotation(pitch), true);
        PacketPlayOutEntityHeadRotation packetHead = new PacketPlayOutEntityHeadRotation();
        setValue(packetHead, "a", entityid);
        setValue(packetHead, "b", getFixRotation(yaw));


        sendPacket(packet);
        sendPacket(packetHead);
    }

    public int getFixLocation(double pos) {
        return (int) MathHelper.floor(pos * 32.0D);
    }

    public byte getFixRotation(float yawpitch) {
        return (byte) ((int) (yawpitch * 256.0F / 360.0F));
    }

    public void teleport(Location location) {
        PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport();
        setValue(packet, "a", entityid);
        setValue(packet, "b", getFixLocation(location.getX()));
        setValue(packet, "c", getFixLocation(location.getY()));
        setValue(packet, "d", getFixLocation(location.getZ()));
        setValue(packet, "e", getFixRotation(location.getYaw()));
        setValue(packet, "f", getFixRotation(location.getPitch()));

        sendPacket(packet);
        headRotation(location.getYaw(), location.getPitch());
        this.location = location.clone();
    }

    public void removePlayer() {
        GameProfile gameProfile = this.gameProfile;
        MinecraftServer server = MinecraftServer.getServer();
        WorldServer world = server.getWorldServer(0);
        PlayerInteractManager manager = new PlayerInteractManager(world);
        EntityPlayer player = new EntityPlayer(server, world, gameProfile,
                manager);
        PacketPlayOutPlayerInfo packet = new PacketPlayOutPlayerInfo(
                PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, player);

        for (Player p : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer) p).getHandle().playerConnection
                    .sendPacket(packet);

        }
    }

    private void removefromTab(Teams teams) {
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                this.i++;
                if (i == 10) {
                    teamNpc.get(teams).removePlayer();
                    this.cancel();
                }
            }
        }.runTaskTimer(BedWars.getInstance(), 0, 1);
    }
}
