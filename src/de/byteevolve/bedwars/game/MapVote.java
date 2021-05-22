package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.arena.Arena;

import java.util.HashMap;
import java.util.Map;

public class MapVote {

    private Map<Arena, Integer> votes;

    public MapVote() {
        this.votes = new HashMap<>();
    }

    public Map<Arena, Integer> getVotes() {
        return votes;
    }
}
