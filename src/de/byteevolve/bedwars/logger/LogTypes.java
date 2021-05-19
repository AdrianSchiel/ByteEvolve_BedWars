package de.byteevolve.bedwars.logger;
public enum LogTypes{
    INFO("§7[§fINFO§7] "),
    ERROR("§7[§4ERROR§7] "),
    WARNING("§7[§cWARNING§7] "),
    SUCCESS("§7[§aERFOLG§7] ");

    private final String prefix;

    public String getPrefix() {
        return prefix;
    }

    LogTypes(String prefix) {
        this.prefix = prefix;
    }
}