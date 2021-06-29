package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import jdk.internal.org.objectweb.asm.tree.InnerClassNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

public class GameHandler {

    private GameState gameState;
    private Arena arena;
    private MapVote mapVote;
    private List<Team> teams;
    private Map<Player, VoteType> goldVoting, webVoting;
    private VoteType gold, web;
    private BukkitTask gameTimer;

    public GameHandler() {
        this.gameState = GameState.LOBBY;
        this.teams = new ArrayList<>();
        this.goldVoting = new HashMap<>();
        this.webVoting = new HashMap<>();

        loadTeams();
        checkMapVote();

    }

    public void manageGameStart(){
            int players = Bukkit.getOnlinePlayers().size();
            int neededPlayers = (ConfigEntries.PLAYERSPERTEAM.getAsInt() * ConfigEntries.TEAMS.getAsInt()) / 2;

            if (neededPlayers == 1) neededPlayers = 2;

            if (players >= neededPlayers) {
                if (gameTimer == null) {
                    this.gameTimer = new GameTimer().runTaskTimerAsynchronously(BedWars.getInstance(), 0, 20);
                }
            } else {
                if(gameTimer != null) {
                    this.gameTimer.cancel();
                    this.gameTimer = null;
                }
                Bukkit.broadcastMessage("Zu wenige Spieler");
            }
    }

    public void loadResults(){
        Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "GOLD:" + getGoldVotingResults().toString());
        Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "WEB:" + getWebVotingResults().toString());
        Bukkit.broadcastMessage(BedWars.getInstance().getPrefix() + "ARENA:" + getMapVoteResult().getDisplayname());
    }


    private Arena getMapVoteResult(){
        if(mapVote != null){
            Arena most = null; int arenavotes = 0;
            for(Arena arena : mapVote.getVotes().keySet()){
                if(mapVote.getVotes().get(arena) > arenavotes){
                    arenavotes = mapVote.getVotes().get(arena);
                    most = arena;
                }
            }
            this.arena = most;
            this.mapVote = null;
            return most;


        }else{
            if(this.arena != null){
                return this.arena;
            }else{
                return null;
            }
        }
    }


    private VoteType getWebVotingResults(){
        int webFor=0, webAgainst=0;
        for(Player player : getWebVoting().keySet()){
            switch (getWebVoting().get(player)){
                case AGAINST:
                    webAgainst++;
                    break;
                case FOR:
                    webFor++;
                    break;
            }
        }

        if(webFor > webAgainst){
            this.web = VoteType.FOR;
            return VoteType.FOR;
        }else{
            this.web = VoteType.AGAINST;
            return VoteType.AGAINST;
        }
    }

    private VoteType getGoldVotingResults(){
        int goldFor=0, goldAgainst=0;
        for(Player player : getGoldVoting().keySet()){
            switch (getGoldVoting().get(player)){
                case AGAINST:
                    goldAgainst++;
                    break;
                case FOR:
                    goldFor++;
                    break;
            }
        }

        if(goldFor > goldAgainst){
            this.gold = VoteType.FOR;
            return VoteType.FOR;
        }else{
            this.gold = VoteType.AGAINST;
            return VoteType.AGAINST;
        }
    }


    private void loadTeams() {
        this.teams.clear();
        for (int i = 0; i < ConfigEntries.TEAMS.getAsInt() ; i++) {
            Team team = new Team(Teams.fromID(i));
            team.setBed(true);
            this.teams.add(team);
        }
    }

    private void checkMapVote() {
        this.mapVote = new MapVote();
        for(Arena arena : BedWars.getInstance().getArenaHandler().getArenas()){
            if(arena.getTeams() == ConfigEntries.TEAMS.getAsInt()
            && arena.getPlayers() == ConfigEntries.PLAYERSPERTEAM.getAsInt()){
                this.mapVote.getVotes().put(arena, 0);
            }
        }

        if(this.mapVote.getVotes().size() > 3) {
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
            for (Arena arena : arenas){
                this.mapVote.getVotes().put(arena, 0);
            }
        }

        if(this.mapVote.getVotes().size() == 1){
            this.arena = (Arena) mapVote.getVotes().keySet().toArray()[0];
            this.mapVote = null;
            return;
        }
        if(this.mapVote.getVotes().isEmpty()) this.mapVote = null;
    }

    public Map<Player, VoteType> getGoldVoting() {
        return goldVoting;
    }

    public void setGoldVoting(Map<Player, VoteType> goldVoting) {
        this.goldVoting = goldVoting;
    }

    public Map<Player, VoteType> getWebVoting() {
        return webVoting;
    }

    public void setWebVoting(Map<Player, VoteType> webVoting) {
        this.webVoting = webVoting;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public MapVote getMapVote() {
        return mapVote;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setArena(Arena arena) {
        this.arena = arena;
    }

    public GameState getGameState() {
        return gameState;
    }

    public Arena getArena() {
        return arena;
    }

}
