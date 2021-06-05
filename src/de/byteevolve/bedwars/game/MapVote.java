package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class MapVote {

    private Map<Arena, Integer> votes;
    private Map<Player, Arena> playerVotes;

    public MapVote() {
        this.votes = new HashMap<>();
        this.playerVotes = new HashMap<>();
    }

    public Map<Player, Arena> getPlayerVotes() {
        return playerVotes;
    }

    public boolean hasVoted(Player player){
        if(this.playerVotes.containsKey(player)) return true;

        return false;
    }

    public Map<Arena, Integer> getVotes() {
        return votes;
    }
}
