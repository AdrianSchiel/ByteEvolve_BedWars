package de.byteevolve.bedwars.arena;

import org.bukkit.Color;

public enum Teams {

    RED(0, 14, "§c"),
    BLUE(1, 11, "§9"),
    GREEN(2, 13, "§a"),
    YELLOW(3, 4, "§e"),
    AQUA(4, 3, "§b"),
    WHITE(5, 0, "§f"),
    PINK(6, 6, "§d"),
    GRAY(7, 7, "§7");

    private final int id, woolid;
    private String color;

    public int getId() {
        return id;
    }
    public int getWoolid() {
        return woolid;
    }

    public String getColor() {
        return color;
    }

    Teams(int id, int woolid, String color) {
    this.id = id;
    this.woolid = woolid;
    this.color = color;
    }

    public static Teams fromID(int id){
        for(Teams team : Teams.values()){
            if(team.getId() == id) return team;
        }
        return null;
    }

}
