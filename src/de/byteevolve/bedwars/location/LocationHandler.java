package de.byteevolve.bedwars.location;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.logger.LogTypes;
import de.byteevolve.bedwars.logger.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationHandler {

    private List<Loc> locations;

    public LocationHandler() {
        this.locations = new ArrayList<>();
        if(this.loadLocs()) Logger.log(LogTypes.SUCCESS, "Es wurden alle Locations geladen");
        else Logger.log(LogTypes.ERROR, "Es gab einen Fehler beim Laden aller Locations");
    }

    public List<Loc> getLocations() {
        return locations;
    }

    public Loc getLocByName(String name){
        for(Loc loc : getLocations()){
            if(loc.getName().equalsIgnoreCase(name)) return loc;
        }
        return null;
    }

    public boolean existByName(String name){
        for(Loc loc : getLocations()){
            if(loc.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public List<Loc> getLocList(String name){
        List<Loc> locs = new ArrayList<>();
        for(Loc loc : getLocations()) {
            if (loc.getName().contains(name)) {
                locs.add(loc);
            }
        }
        return locs;
    }

    public boolean loadLocs(){
        getLocations().clear();
        ResultSet resultSet = BedWars.getInstance().getMySQL().getResult("SELECT * FROM bw_locs;");
        try {
            while (resultSet.next()) {
                Loc temp = new Loc(resultSet.getString("NAME"));
                temp.setY(resultSet.getDouble("Y"));
                temp.setX(resultSet.getDouble("X"));
                temp.setZ(resultSet.getDouble("Z"));
                temp.setYaw(resultSet.getFloat("YAW"));
                temp.setPitch(resultSet.getFloat("PITCH"));
                temp.setWorld(resultSet.getString("WORLD"));
                getLocations().add(temp);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
