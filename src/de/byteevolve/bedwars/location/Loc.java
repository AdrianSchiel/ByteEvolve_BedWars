package de.byteevolve.bedwars.location;

import de.byteevolve.bedwars.BedWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Loc {

    private String name, world;
    private double x,y,z;
    private float yaw, pitch;

    public Loc(String name) {
        this.name = name;
    }

    public boolean exists(){
        return BedWars.getInstance().getLocationHandler().existByName(this.name);
    }

    private void save() {
        BedWars.getInstance().getMySQL().update("INSERT INTO bw_locs VALUES " +
                "(NULL, '" +getName()+"', '" +getX()+"', '"+ getY() +"', '" +getZ()+"', '"
                +getYaw() +"', '" +getPitch()+ "', '" +getWorld() + "')");
    }

    public void setFromLocation(Location location){
        setWorld(location.getWorld().getName());
        setPitch(location.getPitch());
        setYaw(location.getYaw());
        setY(location.getY());
        setX(location.getX());
        setZ(location.getZ());
    }

    public boolean delete(){
        if (BedWars.getInstance().getLocationHandler().existByName(this.name)) {
            BedWars.getInstance().getMySQL().update("DELETE FROM bw_locs WHERE NAME='" +getName()+ "';");
            return true;
        }
        return false;
    }

    public void update() {
        if (BedWars.getInstance().getLocationHandler().existByName(this.name)) {
            BedWars.getInstance().getMySQL().update("UPDATE bw_locs SET bw_locs.NAME='" + getName() + "'," +
                    "bw_locs.Y='" +getY()+"', bw_locs.Z='" + getZ()+ "', bw_locs.YAW='" + getYaw()+ "'," +
                    "bw_locs.PITCH='" +getPitch()+ "', bw_locs.WORLD='" +getWorld()+ "' WHERE bw_locs.NAME='" +getName() +"';");
            BedWars.getInstance().getLocationHandler().loadLocs();
        } else {
            BedWars.getInstance().getMySQL().update("INSERT INTO bw_locs VALUES " +
                    "(NULL, '" +getName()+"', '" +getX()+"', '"+ getY() +"', '" +getZ()+"', '"
                    +getYaw() +"', '" +getPitch()+ "', '" +getWorld() + "')");

            BedWars.getInstance().getLocationHandler().loadLocs();
        }


    }


    public Location getAsLocation(){
        if(exists()){
            return new Location(Bukkit.getWorld(getWorld()), getX(), getY(),getZ(), getYaw(),getPitch());
        }else return null;
    }

    public String getName() {
        return name;
    }

    public Loc setName(String name) {
        this.name = name;
        return this;
    }

    public String getWorld() {
        return world;
    }

    public Loc setWorld(String world) {
        this.world = world;
        return this;
    }

    public double getX() {
        return x;
    }

    public Loc setX(double x) {
        this.x = x;
        return this;
    }

    public double getY() {
        return y;
    }

    public Loc setY(double y) {
        this.y = y;
        return this;
    }

    public double getZ() {
        return z;
    }

    public Loc setZ(double z) {
        this.z = z;
        return this;
    }

    public float getYaw() {
        return yaw;
    }

    public Loc setYaw(float yaw) {
        this.yaw = yaw;
        return this;
    }

    public float getPitch() {
        return pitch;
    }

    public Loc setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }
}
