package de.byteevolve.bedwars.arena;

import org.bukkit.Color;

public enum Teams {

    RED(0, 14),
    BLUE(1, 11),
    GREEN(2, 13),
    YELLOW(3, 4),
    AQUA(4, 3),
    WHITE(5, 0),
    PINK(6, 6),
    GRAY(7, 7);

    private final int id, woolid;

    public int getId() {
        return id;
    }
    public int getWoolid() {
        return woolid;
    }

    Teams(int id, int woolid) {
    this.id = id;
    this.woolid = woolid;
    }

    public static Teams fromID(int id){
        for(Teams team : Teams.values()){
            if(team.getId() == id) return team;
        }
        return null;
    }

}
