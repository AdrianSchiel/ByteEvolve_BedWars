package de.byteevolve.bedwars;


import de.byteevolve.bedwars.arena.ArenaHandler;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.bstats.Metrics;
import de.byteevolve.bedwars.commands.Command_Arena;
import de.byteevolve.bedwars.configuration.ConfigHandler;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.database.MySQL;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.itembuilder.unbreakable.*;
import de.byteevolve.bedwars.listener.*;
import de.byteevolve.bedwars.location.LocationHandler;
import de.byteevolve.bedwars.player.actionbar.*;
import de.byteevolve.bedwars.player.respawn.*;
import de.byteevolve.bedwars.player.scoreboard.*;
import de.byteevolve.bedwars.shop.config.ShopConfigHandler;
import de.byteevolve.bedwars.shop.npc.Npc;
import de.byteevolve.bedwars.spawner.config.SpawnerConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class BedWars extends JavaPlugin {

    private static BedWars instance;
    private MySQL mySQL;
    private LocationHandler locationHandler;
    private ArenaHandler arenaHandler;
    private BWActionbar actionbar;
    private BWRespawn respawn;
    private Unbreakable unbreakable;
    private ConfigHandler configHandler;
    private GameHandler gameHandler;
    private ShopConfigHandler shopConfigHandler;
    private SpawnerConfigHandler spawnerConfigHandler;
    private BWScoreboard scoreboard;
    private String prefix, noPerm, mustAPlayer, playerNotOnline;
    private HashMap<Teams, Npc> teamNpc;

    @Override
    public void onEnable() {
        instance = this;
        this.configHandler = new ConfigHandler();
        this.mySQL = new MySQL(ConfigEntries.MYSQL_HOST.getValue().toString(), ConfigEntries.MYSQL_USERNAME.getValue().toString(), ConfigEntries.MYSQL_PASSWORD.getValue().toString(), ConfigEntries.MYSQL_DATABASE.getValue().toString(), 3306);
        this.spawnerConfigHandler = new SpawnerConfigHandler();
        this.prefix = ConfigEntries.PREFIX.getAsString();
        this.noPerm = this.prefix + ConfigEntries.NOPERM.getAsString();
        this.mustAPlayer = this.prefix + ConfigEntries.MUSTAPLAYER.getAsString();
        this.playerNotOnline = this.prefix + ConfigEntries.PLAYERNOTONLINE.getAsString();
        this.shopConfigHandler = new ShopConfigHandler();
        this.locationHandler = new LocationHandler();
        this.arenaHandler = new ArenaHandler();
        this.gameHandler = new GameHandler();
        this.teamNpc = new HashMap<>();
        new Metrics(this, 13065);

        getCommand("arena").setExecutor(new Command_Arena());

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Listener_Arena(), this);
        pluginManager.registerEvents(new Listener_Join(), this);
        pluginManager.registerEvents(new Listener_Team(), this);
        pluginManager.registerEvents(new Listener_Voting(), this);
        pluginManager.registerEvents(new Listener_GameSetup(), this);
        pluginManager.registerEvents(new Listener_Quit(), this);
        pluginManager.registerEvents(new Listener_Shop(), this);
        pluginManager.registerEvents(new Listener_Npc(), this);
        pluginManager.registerEvents(new Listener_Bed_Break(), this);
        pluginManager.registerEvents(new Listener_Player_Death(), this);
        pluginManager.registerEvents(new Listener_Shop_Enchant(), this);
        pluginManager.registerEvents(new Listener_Map_Protection(), this);
        loadVersions();

    }

    private void loadVersions() {
        String version = "N/A";

        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            switch (version) {
                case "v1_8_R3":
                    this.actionbar = new v1_8_R3_Actionbar();
                    this.respawn = new v1_8_R3_Respawn();
                    this.unbreakable = new v1_8_R3_Unbreakable();
                    this.scoreboard = new v1_8_R3_Scoreboard();
                    break;
                case "v1_9_R2":
                    this.actionbar = new v1_9_R2_Actionbar();
                    this.respawn = new v1_9_R2_Respawn();
                    this.unbreakable = new v1_9_R2_Unbreakable();
                    this.scoreboard = new v1_9_R2_Scoreboard();
                    break;
                case "v1_10_R1":
                    this.actionbar = new v1_10_R1_Actionbar();
                    this.respawn = new v1_10_R1_Respawn();
                    this.unbreakable = new v1_10_R1_Unbreakable();
                    this.scoreboard = new v1_10_R1_Scoreboard();
                    break;
                case "v1_11_R1":
                    this.actionbar = new v1_11_R1_Actionbar();
                    this.respawn = new v1_11_R1_Respawn();
                    this.unbreakable = new v1_11_R1_Unbreakable();
                    this.scoreboard = new v1_11_R1_Scoreboard();
                    break;
                case "v1_12_R1":
                    this.actionbar = new v1_12_R1_Actionbar();
                    this.respawn = new v1_12_R1_Respawn();
                    this.unbreakable = new v1_12_R1_Unbreakable();
                    this.scoreboard = new v1_12_R1_Scoreboard();
                    break;
                case "v1_13_R2":
                    this.actionbar = new v1_13_R2_Actionbar();
                    this.respawn = new v1_13_R2_Respawn();
                    this.unbreakable = new v1_13_R2_Unbreakable();
                    this.scoreboard = new v1_13_R2_Scoreboard();
                    break;
                case "v1_14_R1":
                    this.actionbar = new v1_14_R1_Actionbar();
                    this.respawn = new v1_14_R1_Respawn();
                    this.unbreakable = new v1_14_R1_Unbreakable();
                    this.scoreboard = new v1_14_R1_Scoreboard();
                    break;
                case "v1_15_R1":
                    this.actionbar = new v1_15_R1_Actionbar();
                    this.respawn = new v1_15_R1_Respawn();
                    this.unbreakable = new v1_15_R1_Unbreakable();
                    this.scoreboard = new v1_15_R1_Scoreboard();
                    break;
                case "v1_16_R3_Respawn":
                    this.actionbar = new v1_16_R3_Actionbar();
                    this.respawn = new v1_16_R3_Respawn();
                    this.unbreakable = new v1_16_R3_Unbreakable();
                    this.scoreboard = new v1_16_R3_Scoreboard();
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public GameHandler getGameHandler() {
        return gameHandler;
    }

    @Override
    public void onDisable() {
        for (Location location : gameHandler.getBlocks()) {
            location.getBlock().setType(Material.AIR);
        }
        super.onDisable();
    }

    public String getPrefix() {
        return prefix;
    }

    public String getNoPerm() {
        return noPerm;
    }

    public String getMustAPlayer() {
        return mustAPlayer;
    }

    public String getPlayerNotOnline() {
        return playerNotOnline;
    }

    public BWActionbar getActionbar() {
        return actionbar;
    }

    public BWRespawn getRespawn() {
        return respawn;
    }

    public HashMap<Teams, Npc> getTeamNpc() {
        return teamNpc;
    }

    public Unbreakable getUnbreakable() {
        return unbreakable;
    }

    public LocationHandler getLocationHandler() {
        return locationHandler;
    }

    public static BedWars getInstance() {
        return instance;
    }

    public ArenaHandler getArenaHandler() {
        return arenaHandler;
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public BWScoreboard getScoreboard() {
        return scoreboard;
    }
}
