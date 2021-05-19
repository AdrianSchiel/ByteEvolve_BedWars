package de.byteevolve.bedwars.arena;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.player.stats.PlayerStats;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArenaHandler {

    private List<Arena> arenas;
    private Map<Player, Arena> arenaPlayerCreate, arenaEditList;

    public ArenaHandler(){
        this.arenas = new ArrayList<>();
        this.arenaEditList = new HashMap<>();
        this.arenaPlayerCreate = new HashMap<>();

        loadArenas(1, 2);
    }

    public boolean loadArenas(int players, int teams) {
        this.arenas.clear();
        ResultSet resultSet = BedWars.getInstance().getMySQL().getResult("SELECT * FROM `bw_arena` WHERE bw_arena.PLAYERS="+ players+" AND bw_arena.TEAMS=" +teams + ";");
        try {
            while (resultSet.next()) {
                Arena arena = new Arena(resultSet.getString("NAME"));
                arena.setDisplayname(resultSet.getString("DISPLAYNAME"));
                arena.setSpawn(resultSet.getString("SPAWN"));
                arena.setSpawnspec(resultSet.getString("SPAWNSPEC"));
                arena.setGoldraw(resultSet.getString("GOLD"));
                arena.setBronzeraw(resultSet.getString("BRONZE"));
                arena.setIronraw(resultSet.getString("IRON"));
                arena.setBedsraw(resultSet.getString("BEDS"));
                arena.setFinished(resultSet.getInt(("FINISHED")));
                arena.setPlayers(resultSet.getInt(("PLAYERS")));
                arena.setTeams(resultSet.getInt(("TEAMS")));
                this.arenas.add(arena);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Gibt eine Arena anhand ihres Namens in der Arena Liste wieder {@link Arena}
     * @param name Der abzufragende Name
     * @return Gesuchte Arena
     */
    public Arena getArenaByName(String name){
        for(Arena arena : arenas){
            if(arena.getName().equalsIgnoreCase(name)) return arena;
        }
        return null;
    }

    /**
     * Fragt ab ob es eine Arena mit einem bestimmten Namen gibt, welcher ein Spieler erstellt.
     * @param name Der abzufragende Name
     * @return Wahrheitswert, ob diese Arena gerade von einem Spieler erstellt wird.
     */
    public boolean existArenaPlayerCreateByName(String name) {
        for (Player player : this.arenaPlayerCreate.keySet()){
            if(this.arenaPlayerCreate.get(player).getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    /**
     * Gibt die Map ArenaPlayerCreate wieder
     * @return Map<Player, Arena>
     */
    public Map<Player, Arena> getArenaPlayerCreate() {
        return arenaPlayerCreate;
    }

    /**
     * Fragt ab ob es eine Arena mit einem bestimmten Namen gibt.
     * @param name Der abzufragende Name
     * @return Wahrheitswert, ob diese Arena vorhanden ist.
     */
    public boolean existArenaByName(String name){
        for(Arena arena : this.arenas){
            if(arena.getName().equalsIgnoreCase(name)) return true;
        }
        return false;
    }

    public Map<Player, Arena> getArenaEditList() {
        return arenaEditList;
    }

}
