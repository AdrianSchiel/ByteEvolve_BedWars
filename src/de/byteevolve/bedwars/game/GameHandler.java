package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;

import java.util.ArrayList;
import java.util.List;

public class GameHandler {

    private GameState gameState;
    private Arena arena;
    private MapVote mapVote;
    private List<Team> teams;

    public GameHandler() {
        this.gameState = GameState.LOBBY;
        this.teams = new ArrayList<>();
        loadTeams();
        checkMapVote();
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
        if(!(this.mapVote.getVotes().size() > 1)) this.mapVote = null;
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