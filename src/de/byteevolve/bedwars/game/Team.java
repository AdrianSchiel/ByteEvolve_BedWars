package de.byteevolve.bedwars.game;

import de.byteevolve.bedwars.arena.Teams;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private Teams team;
    private List<Player> members;
    private boolean bed;
    private List<Player> hasAxe, hasPickaxe, hasSword, hasShears;
    private int sharpness, efficiency;

    public Team(Teams team) {
        this.team = team;
        this.members = new ArrayList<>();
        this.hasAxe = new ArrayList<>();
        this.hasSword = new ArrayList<>();
        this.hasPickaxe = new ArrayList<>();
        this.hasShears = new ArrayList<>();

    }

    public List<Player> getHasAxe() {
        return hasAxe;
    }

    public List<Player> getHasPickaxe() {
        return hasPickaxe;
    }

    public List<Player> getHasShears() {
        return hasShears;
    }

    public List<Player> getHasSword() {
        return hasSword;
    }

    public void setAxe(Player player) {
        hasAxe.add(player);
    }

    public void setPickaxe(Player player) {
        hasPickaxe.add(player);
    }

    public void setShears(Player player) {
        hasShears.add(player);
    }

    public void setSword(Player player) {
        hasSword.add(player);
    }

    public int getEfficiency() {
        return efficiency;
    }

    public int getSharpness() {
        return sharpness;
    }

    public void setEfficiency(int efficiency) {
        this.efficiency = efficiency;
    }

    public void setSharpness(int sharpness) {
        this.sharpness = sharpness;
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
