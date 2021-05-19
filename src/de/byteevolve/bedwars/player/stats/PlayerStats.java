package de.byteevolve.bedwars.player.stats;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.database.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class PlayerStats {

    private String uuid;

    public PlayerStats(String uuid) {
        this.uuid = uuid;
        this.create();
    }

    private boolean exists() {
        try {
            BedWars bedWars = BedWars.getInstance();
            MySQL mySQL = bedWars.getMySQL();
            ResultSet resultSet = mySQL.getResult("SELECT * FROM bw_stats WHERE bw_stats.UUID='" + getUUID() +"';");
            if (resultSet.next()) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void create() {
        if(!exists()) {
                BedWars bedWars = BedWars.getInstance();
                MySQL mySQL = bedWars.getMySQL();
                mySQL.update("INSERT INTO bw_stats VALUES('" +getUUID() +"', '"+ getName() +"',"
                        + "'0', '0', '0', '0', '0');");
        }
    }


    public double getKD() {
        double deaths = get(PlayerStatsType.DEATHS);
        double kills = get(PlayerStatsType.KILLS);
        double kd = kills / deaths;
        return Math.round(kd * 100.0D) / 100.0D;
    }

    public Integer get(PlayerStatsType type) {

        try {
            BedWars bedWars = BedWars.getInstance();
            MySQL mySQL = bedWars.getMySQL();
            ResultSet resultSet = mySQL.getResult("SELECT " +type.toString() +" FROM bw_stats WHERE bw_stats.UUID='" + getUUID() +"';");
            if(resultSet.next()) return resultSet.getInt(type.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }
    public void add(PlayerStatsType type, int value) {
        set(type ,get(type) + value);
    }

    public void remove(PlayerStatsType type, int value) {
        if(get(type)- value <= 0){
            set(type,0);
        }else set(type ,get(type) - value);
    }

    public void set(PlayerStatsType type, int value) {
        if(exists()) {
                BedWars bedWars = BedWars.getInstance();
                MySQL mySQL = bedWars.getMySQL();
                mySQL.update("UPDATE bw_stats SET " + type.toString() + "='" + value+ "' WHERE UUID='" + getUUID() + "';");
        }else create();
    }


    private String getName() {
        return Bukkit.getOfflinePlayer(UUID.fromString(uuid)).getName();
    }
    private String getUUID() {
        return this.uuid;
    }

    public Integer getRank() {
        HashMap<Integer, String> rang = new HashMap<>();
        try {
            ResultSet rs = BedWars.getInstance().getMySQL().getResult("SELECT UUID FROM bw_stats ORDER BY POINTS DESC LIMIT 1000");
            int in = 0;
            try {
                while (rs.next()) {
                    in++;
                    if (rs.getString("UUID").equalsIgnoreCase(uuid))
                        return Integer.valueOf(in);
                    if (in == 1000)
                        return Integer.valueOf(-1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (Exception exception) {}
        return null;
    }


}
