
package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import de.byteevolve.bedwars.location.Loc;
import de.byteevolve.bedwars.location.LocationHandler;
import de.byteevolve.bedwars.shop.npc.Npc;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.material.Bed;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameHandler {
    private GameState gameState;
    private Arena arena;
    private MapVote mapVote;
    private List<Team> teams;
    private Map<Player, VoteType> goldVoting;
    private Map<Player, VoteType> webVoting;
    private VoteType gold;
    private VoteType web;
    private BukkitTask gameTimer;
    private BukkitTask fastTimer;
    private List<Location> blocks;
    private boolean isDone;

    public GameHandler() {
        this.gameState = GameState.LOBBY;
        this.teams = new ArrayList();
        this.goldVoting = new HashMap();
        this.webVoting = new HashMap();
        this.isDone = false;
        this.blocks = new ArrayList<>();
        if (!BedWars.getInstance().getArenaHandler().getArenas().isEmpty()) {
            this.arena = BedWars.getInstance().getArenaHandler().getArenas().get(0);
        }
        this.loadTeams();
        this.checkMapVote();
    }

    public void loadWorld(){
        try {
            File sourceDirectory = new File(arena.getName());
            File destinationDirectory = new File("world");
            FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteOldMap() {
        World world = Bukkit.getWorld("world");
        Bukkit.unloadWorld(world, true);
        File worldFolder = new File("world");
        try {
            FileUtils.deleteDirectory(worldFolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Location> getBlocks() {
        return blocks;
    }

    public void setDone() {
        isDone = true;
    }

    public void manageGameStart() {
        int players = Bukkit.getOnlinePlayers().size();
        int neededPlayers = ConfigEntries.PLAYERSPERTEAM.getAsInt() * ConfigEntries.TEAMS.getAsInt() / 2;
        if (neededPlayers == 1) {
            neededPlayers = 2;
        }

        if (players >= neededPlayers) {
            if (this.gameTimer == null) {
                this.gameTimer = (new GameTimer()).runTaskTimer(BedWars.getInstance(), 0L, 20L);
            }
        } else {
            if (this.gameTimer != null) {
                this.gameTimer.cancel();
                this.gameTimer = null;
            }

            Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "§8Es sind zu wenige Spieler auf dem Servver!");
        }

    }

    public void manageFastStart() {
        int players = Bukkit.getOnlinePlayers().size();
        int neededPlayers = ConfigEntries.PLAYERSPERTEAM.getAsInt() * ConfigEntries.TEAMS.getAsInt() / 2;
        if (neededPlayers == 1) {
            neededPlayers = 2;
        }

        if (players >= neededPlayers) {
            if (this.fastTimer == null) {
                this.fastTimer = (new FastTimer()).runTaskTimer(BedWars.getInstance(), 0L, 20L);
            }
        } else {
            if (this.fastTimer != null) {
                this.fastTimer.cancel();
                this.fastTimer = null;
            }

            Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "§8Es sind zu wenige Spieler auf dem Servver!");
        }

    }

    public void loadResults() {
        Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "§8GOLD: §a" + getGoldVotingResults().toString());
        Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "§8WEB: §a" + getWebVotingResults().toString());
        System.out.println(getMapVoteResult());
        Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "§8ARENA: §a" + getMapVoteResult().getDisplayname());
    }

    public void teleportPlayers() {
        Arena arena = this.arena;
        for (Team team : this.teams) {
            for (Player member : team.getMembers()) {
                member.teleport(BedWars.getInstance().getLocationHandler().getLocByName(arena.getSpawns().get(team.getTeam().getId())).getAsLocation());
            }
        }
    }

    private Arena getMapVoteResult() {
        if (mapVote != null) {
            Arena most = null;
            int arenavotes = -1;
            for (Arena arena : mapVote.getVotes().keySet()) {
                System.out.println(arena.getName());
                if (mapVote.getVotes().get(arena) > arenavotes) {
                    arenavotes = mapVote.getVotes().get(arena);
                    most = arena;
                }
            }
            this.arena = most;
            this.mapVote = null;
            return most;


        } else {
            if (this.arena != null) {
                return this.arena;
            } else {
                return null;
            }
        }
    }

    private VoteType getWebVotingResults() {
        int webFor = 0;
        int webAgainst = 0;
        Iterator var3 = this.getWebVoting().keySet().iterator();

        while (var3.hasNext()) {
            Player player = (Player) var3.next();
            switch ((VoteType) this.getWebVoting().get(player)) {
                case AGAINST:
                    ++webAgainst;
                    break;
                case FOR:
                    ++webFor;
            }
        }

        if (webFor > webAgainst) {
            this.web = VoteType.FOR;
            return VoteType.FOR;
        } else {
            this.web = VoteType.AGAINST;
            return VoteType.AGAINST;
        }
    }

    public VoteType getGoldVotingResults() {
        int goldFor = 0;
        int goldAgainst = 0;
        Iterator var3 = this.getGoldVoting().keySet().iterator();

        while (var3.hasNext()) {
            Player player = (Player) var3.next();
            switch ((VoteType) this.getGoldVoting().get(player)) {
                case AGAINST:
                    ++goldAgainst;
                    break;
                case FOR:
                    ++goldFor;
            }
        }

        if (goldFor > goldAgainst) {
            this.gold = VoteType.FOR;
            return VoteType.FOR;
        } else {
            this.gold = VoteType.AGAINST;
            return VoteType.AGAINST;
        }
    }

    private void loadTeams() {
        this.teams.clear();

        for (int i = 0; i < ConfigEntries.TEAMS.getAsInt(); ++i) {
            Team team = new Team(Teams.fromID(i));
            team.setBed(true);
            this.teams.add(team);
        }

    }

    private void checkMapVote() {
        this.mapVote = new MapVote();
        for (Arena arena : BedWars.getInstance().getArenaHandler().getArenas()) {
            if (arena.getTeams() == ConfigEntries.TEAMS.getAsInt()
                    && arena.getPlayers() == ConfigEntries.PLAYERSPERTEAM.getAsInt()) {
                this.mapVote.getVotes().put(arena, 0);
            }
        }

        if (this.mapVote.getVotes().size() > 3) {
            List<Arena> arenas = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                Arena arena = (Arena) this.mapVote.getVotes().keySet().toArray()[ThreadLocalRandom.current().nextInt(0, this.mapVote.getVotes().size())];
                if (!arenas.contains(arena)) {
                    arenas.add(arena);
                } else {
                    i = i - 1;
                }
            }
            this.mapVote.getVotes().clear();
            for (Arena arena : arenas) {
                this.mapVote.getVotes().put(arena, 0);
            }
        }

        if (this.mapVote.getVotes().size() == 1) {
            this.arena = (Arena) mapVote.getVotes().keySet().toArray()[0];
            this.mapVote = null;
            return;
        }
        if (this.mapVote.getVotes().isEmpty()) this.mapVote = null;
    }

    public Team isPlayerInTeam(Player player) {
        Iterator var2 = this.teams.iterator();

        Team team;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            team = (Team) var2.next();
        } while (!team.getMembers().contains(player));

        return team;
    }

    public boolean hasPlayed(Player player) {
        for (Team teams : getTeams()) {
            for (Player player1 : teams.getMembers()) {
                if (player1.getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                    return true;
                }
            }
        }

        return false;
    }

    public BukkitTask getGameTImer() {
        return this.gameTimer;
    }

    public void spawnNpcs() {
        HashMap<Teams, Npc> teamNpc = BedWars.getInstance().getTeamNpc();
        if (teamNpc.get(getTeams().get(0)) == null) {
            for (int i = 0; i < getTeams().size(); i++) {
                Team teams = getTeams().get(i);
                for (Player player : teams.getMembers()) {
                    Arena arena = getArena();
                    Loc loc = new LocationHandler().getLocByName(arena.getName() + "team" + teams.getTeam().getId() + "shop");
                    Location location = loc.getAsLocation();
                    Npc npc = new Npc(teams.getTeam().getColor() + "Shopkeeper", location, 1 + i, teams.getTeam());
                    npc.changeSkin(player);
                    npc.spawn(player);
                    teamNpc.put(teams.getTeam(), npc);
                }
            }
        }
        new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                i++;
                if (i == 10) {
                    for (Team teams : getTeams()) {
                        Npc npc = teamNpc.get(teams.getTeam());
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            npc.spawn(player);
                        }
                        i = 0;
                    }
                }
                if (BedWars.getInstance().getGameHandler().getGameState().equals(GameState.ENDING))
                    this.cancel();

            }
        }.runTaskTimer(BedWars.getInstance(), 0, 20);
    }

    public Team getTeam(Player player) {
        for (Team teams : getTeams()) {
            for (Player player1 : teams.getMembers()) {
                if (player1.getUniqueId().toString().equalsIgnoreCase(player.getUniqueId().toString())) {
                    return teams;
                }
            }
        }

        return null;
    }

    public Map<Player, VoteType> getGoldVoting() {
        return this.goldVoting;
    }

    public void setGoldVoting(Map<Player, VoteType> goldVoting) {
        this.goldVoting = goldVoting;
    }

    public Map<Player, VoteType> getWebVoting() {
        return this.webVoting;
    }

    public void setWebVoting(Map<Player, VoteType> webVoting) {
        this.webVoting = webVoting;
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public MapVote getMapVote() {
        return this.mapVote;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public GameState getGameState() {
        return this.gameState;
    }

    public Arena getArena() {
        return this.arena;
    }
}
