package de.byteevolve.bedwars.database;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.logger.LogTypes;
import de.byteevolve.bedwars.logger.Logger;
import org.bukkit.Bukkit;

import java.sql.*;


public class MySQL {

    private String host;
    private String username;
    private String password;
    private String database;
    private int port;
    private Connection connection;

    public MySQL(String host, String username, String password, String database, int port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.database = database;
        this.port = port;

        connect();

        if(this.connection == null){
            Bukkit.getPluginManager().disablePlugin(BedWars.getInstance());
            return;
        }
        createTables();
    }

    private void createTables() {
        update("CREATE TABLE IF NOT EXISTS `bw_locs` (`LID` int(11) NOT NULL AUTO_INCREMENT,`NAME` text NOT NULL,`X` double NOT NULL,`Y` double NOT NULL," +
                "  `Z` double NOT NULL,`YAW` float NOT NULL, `PITCH` float NOT NULL,`WORLD` text NOT NULL, PRIMARY KEY (LID));");
        update("CREATE TABLE IF NOT EXISTS `bw_stats` (`UUID` text NOT NULL,`NAME` text NOT NULL,`KILLS` int(11) NOT NULL," +
                "`DEATHS` int(11) NOT NULL,`PLAYEDGAMES` int(11) NOT NULL,`BEDS` int(11) NOT NULL, `POINTS` int(11) NOT NULL);");
        update("CREATE TABLE IF NOT EXISTS `bw_arena` (`NAME` TEXT NOT NULL, `DISPLAYNAME` TEXT NOT NULL , `SPAWN` TEXT NOT NULL ," +
                " `SPAWNSPEC` TEXT NOT NULL , `BRONZE` TEXT NOT NULL , `GOLD` TEXT NOT NULL,`IRON` TEXT NOT NULL,`BEDS` TEXT NOT NULL," +
                "`SHOPS` TEXT NOT NULL,`SPAWNS` TEXT NOT NULL,`TEAMS` INT NOT NULL, `PLAYERS` INT NOT NULL, `FINISHED` INT NOT NULL);");
    }

    private void connect() {
        if (!isConnected()) {
            try {
                this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
                Logger.log(LogTypes.SUCCESS, "Erfolgreich zur MySQL-Datenbank verbunden.");
            } catch (SQLException e) {
                Logger.log(LogTypes.ERROR, "Konnte nicht zur MySQL-Datenbank verbinden.");
            }
        }
    }

    public void close() {
        if (isConnected()) {
            try {
                this.connection.close();
                Logger.log(LogTypes.SUCCESS, "Erfolgreich die MySQL-Datenbank geschlossen.");
            } catch (SQLException e) {
                Logger.log(LogTypes.ERROR, "Konnte nicht die MySQL-Datenbank schließen.");
                e.printStackTrace();
            }
        }
    }

    public void update(String qry) {
        try {
            Statement statement = this.connection.createStatement();
            statement.executeUpdate(qry);
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResult(String qry) {
        ResultSet resultSet = null;

        try {
            Statement statement = this.connection.createStatement();
            resultSet = statement.executeQuery(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public boolean isConnected() {
        return (this.connection != null);
    }
}


