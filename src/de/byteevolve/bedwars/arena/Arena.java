package de.byteevolve.bedwars.arena;

import de.byteevolve.bedwars.BedWars;

import java.util.ArrayList;
import java.util.List;

public class Arena {

    private int players, teams;
    private String name, displayname, spawn, spawnspec, bronzeraw, goldraw, ironraw, bedsraw, shopsraw, spawnsraw;
    private int finished;

    public Arena(String name){
        this.name = name;
    }

    public void update(){
        if(getSpawnspec() == null) setSpawnspec("");
        if(getSpawn() == null) setSpawn("");
        if(getBronzeraw() == null) setBronzeraw("");
        if(getIronraw() == null) setIronraw("");
        if(getGoldraw() == null) setGoldraw("");
        if(getBedsraw() == null) setBedsraw("");
        if(getDisplayname() == null) setDisplayname("");
        if(getShopsraw() == null) setShopsraw("");
        if(getSpawnsraw() == null) setSpawnsraw("");
        if (BedWars.getInstance().getArenaHandler().existArenaByName(getName())) {
            BedWars.getInstance().getMySQL().update("UPDATE bw_arena SET " +
                    "DISPLAYNAME='" +getDisplayname() +"', SPAWN='" + getSpawn()+ "',"+
                    "SPAWNSPEC='" + getSpawnspec()+"', BRONZE='" + getBronzeraw()+"',"+
                    "GOLD='" +getGoldraw()+ "', IRON='" +getIronraw() +"', BEDS='" +getBedsraw() +"',SHOPS='" + getShopsraw()+ "',TEAMS='" +getTeams()+"', SPAWNS='"+
                    getSpawnsraw()+"',PLAYERS='" + getPlayers()+ "', FINISHED='" + getFinished() +"' WHERE NAME='" + getName()+"';");
            BedWars.getInstance().getArenaHandler().loadArenas();
        } else {
            BedWars.getInstance().getMySQL().update("INSERT INTO bw_arena VALUES('" +getName() +"', '" + getDisplayname()+"','" +
                    getSpawn() +"', '" +getSpawnspec() +"', '" + getBronzeraw() +"', '" + getGoldraw()+"','" + getIronraw()+"'," +
                    "'" + getBedsraw() +"','"  +getShopsraw() +"','" +getSpawnsraw() +"', '" +getTeams() +"','" +getPlayers() +"', '1');");
            BedWars.getInstance().getArenaHandler().loadArenas();
        }
    }

    public String getBronzeraw() {
        return bronzeraw;
    }

    public String getSpawnsraw() {
        return spawnsraw;
    }

    public void setSpawnsraw(String spawns) {
        this.spawnsraw = spawns;
    }

    public void setShopsraw(String shopsraw) {
        this.shopsraw = shopsraw;
    }

    public String getShopsraw() {
        return shopsraw;
    }

    public void setBronzeraw(String bronzeraw) {
        this.bronzeraw = bronzeraw;
    }

    public String getGoldraw() {
        return goldraw;
    }

    public void setGoldraw(String goldraw) {
        this.goldraw = goldraw;
    }

    public String getIronraw() {
        return ironraw;
    }

    public void setIronraw(String ironraw) {
        this.ironraw = ironraw;
    }

    public String getBedsraw() {
        return bedsraw;
    }

    public void setBedsraw(String bedsraw) {
        this.bedsraw = bedsraw;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public int getTeams() {
        return teams;
    }

    public void setTeams(int teams) {
        this.teams = teams;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayname() {
        return displayname.replace("&", "§");
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getSpawn() {
        return spawn;
    }

    public void setSpawn(String spawn) {
        this.spawn = spawn;
    }

    public String getSpawnspec() {
        return spawnspec;
    }

    public void setSpawnspec(String spawnspec) {
        this.spawnspec = spawnspec;
    }

    public List<String> getSpawns() {
        List<String> spawns = new ArrayList<>();
        if(this.getSpawnsraw() == null) return spawns;
        for(String s : this.getSpawnsraw().split(",")){
            spawns.add(s);
        }
        return spawns;
    }

    public List<String> getBronze() {
        List<String> bronze = new ArrayList<>();
        if(this.getBronzeraw() == null) return bronze;
        for(String s : this.getBronzeraw().split(",")){
            bronze.add(s);
        }
        return bronze;
    }

    public List<String> getGold() {
        List<String> gold = new ArrayList<>();
        if(this.getGoldraw() == null) return gold;
        for(String s : this.getGoldraw().split(",")){
            gold.add(s);
        }
        return gold;
    }

    public List<String> getIron() {
        List<String> iron = new ArrayList<>();
        if(this.getIronraw() == null) return iron;
        for(String s : this.getIronraw().split(",")){
            iron.add(s);
        }
        return iron;
    }


    public List<String> getBeds() {
        List<String> beds = new ArrayList<>();
        for(String s : this.bedsraw.split(",")){
            beds.add(s);
        }
        return beds;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }
}
