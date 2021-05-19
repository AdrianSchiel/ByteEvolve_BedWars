package de.byteevolve.bedwars.logger;

import org.bukkit.Bukkit;

public class Logger {

    public static void log(LogTypes type, String msg){
        Bukkit.getConsoleSender().sendMessage(type.getPrefix()  + msg );
    }
}

