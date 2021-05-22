package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.arena.Teams;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private Teams team;
    private List<Player> members;
    private boolean bed;

    public Team(Teams team) {
        this.team = team;
        this.members = new ArrayList<>();
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public List<Player> getMembers() {
        return members;
    }

    public void setMembers(List<Player> members) {
        this.members = members;
    }

    public boolean isBed() {
        return bed;
    }

    public void setBed(boolean bed) {
        this.bed = bed;
    }
}
