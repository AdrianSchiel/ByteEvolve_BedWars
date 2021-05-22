package de.byteevolve.bedwars.configuration.config;


import de.byteevolve.bedwars.configuration.ConfigEntry;

public enum ConfigEntries implements ConfigEntry {

    //DEFAULT
    PREFIX(ConfigSections.MESSAGES, "prefix", "&8[&aBed&2Wars&8] ", "&8[&aBed&2Wars&8] ", "Text vor jeder Nachricht."),
    NOPERM(ConfigSections.MESSAGES, "noperm", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "&7Du bist nicht berechtigt dieses Kommando zu verwenden.", "Nachricht wenn ein Spieler nicht die benötigten Rechte hat."),
    MUSTAPLAYER(ConfigSections.MESSAGES, "mustaplayer", "&7Du musst ein &aSpieler &7sein!", "&7Du musst ein &aSpieler &7sein!", "Nachricht an Entities, welche keine Spieler sind."),
    PLAYERNOTONLINE(ConfigSections.MESSAGES, "playernotonline", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "&7Der Angegebene Spieler konnte nicht gefunden werden.", "Nachricht wenn ein Spieler nicht online ist."),

    //ARENA COMMAND
    PLAYERCREATESARENA(ConfigSections.MESSAGES, "playercreatesarena", "&cDu erstellst schon eine Arena", "&cDu erstellst schon eine Arena", "Nachricht wenn ein Spieler eine Arena erstellen will, aber schon eine erstellt"),
    ARENAEXISTS(ConfigSections.MESSAGES, "arenaexists", "&7Die Arena:&a %ARENANAME% &7gibt es schon.", "&7Die Arena:&a %ARENANAME% &7gibt es schon.", "Nachricht wenn ein Spieler eine Arena erstellen will, welche schon vorhanden ist. \n%ARENANAME% = Platzhalter für den Arenanamen"),
    PLAYERCREATEARENA(ConfigSections.MESSAGES, "playercreatearena", "&7Du erstellst nun die Arena:&a %ARENANAME%&7. (&2/arena edit&7)", "&7Du erstellst nun die Arena:&a %ARENANAME%&7. (&2/arena edit&7)", "Nachricht wenn ein Spieler eine Arena erstellt. \n%ARENANAME% = Platzhalter für den Arenaname"),
    ARENANOTEXISTS(ConfigSections.MESSAGES, "arenanotexists",  "&7Die Arena:&a %ARENANAME% &7gibt es nicht.", "&7Die Arena:&a %ARENANAME% &7gibt es nicht.", "Nachricht wenn es eine Arena nicht gibt. \n%ARENANAME% = Platzhalter für den Arenanamen"),
    PLAYERNOTCREATESARENA(ConfigSections.MESSAGES, "playernotcreatesarena", "&7Du erstellst gerade keine Arena. (&2/arena create Name&7)", "&7Du erstellst gerade keine Arena. (&2/arena create Name&7)", "Nachricht wenn ein Spieler eine Arena mit /arena edit bearbeiten will, aber keine erstellt."),
    ARENAHELP(ConfigSections.MESSAGES, "arenahelp", "&7Nutze &c/arena &7<&ccreate&7,&cedit&7> &7<&cName&7> &7<&cTeams&7> &7<&cPlayers&7> &7<&cDisplayname&7>", "&7Nutze &c/arena &7<&ccreate&7,&cedit&7> &7<&cName&7> &7<&cTeams&7> &7<&cPlayers&7> &7<&cDisplayname&7>", "Hilfe Nachricht zum erstellen einer Arena"),
    NOARENAEXISTS(ConfigSections.MESSAGES, "noarenaexists","&cEs wurde noch keine Arena erstellt.", "&cEs wurde noch keine Arena erstellt.", "Nachricht an einen Spieler, wenn keine Arena gefunden wurde."),
    ARENATOMANYTEAMS(ConfigSections.MESSAGES, "arenatomanyteams","&cEine Arena kann maximal 8 Teams haben.", "&cEine Arena kann maximal 8 Teams haben.", "Nachricht an einen Spieler, wenn er eine Arena mit mehr als 8 Teams erstellen will."),
    ARENATOFEWTEAMS(ConfigSections.MESSAGES, "arenatofewteams","&cEine Arena braucht mindestens 2 Teams.", "&cEine Arena braucht mindestens 2 Teams.", "Nachricht an einen Spieler, wenn er eine Arena mit weniger als 2 Teams erstellen will."),
    ARENAPLAYERMUSTSTANDONBED(ConfigSections.MESSAGES, "arenaplayermuststandonbed", "&cBitte stelle dich auf ein Bed.", "&cBitte stelle dich auf ein Bed.", "Nachricht an einen Spieler, wenn er nicht auf einem Bed steht."),

    //BUILD COMMAND
    BUILDON(ConfigSections.MESSAGES, "buildon", "&7Du bist nun im &aBuild-Modus&7.","&7Du bist nun im &aBuild-Modus&7.", "Nachricht an einen Spieler, welcher in den Build Modus geht."),
    BUILDOFF(ConfigSections.MESSAGES, "buildoff", "&7Du bist nun &cnicht &7mehr im &aBuild-Modus&7.","&7Du bist nun &cnicht &7mehr im &aBuild-Modus&7.", "Nachricht an einen Spieler, welcher aus dem Build Modus geht."),

    //STATS COMMAND
    STATS(ConfigSections.MESSAGES, "stats", "&m&l&7-------- &aStats&7 -&a %PLAYER% &m&l&7-------- \n &7Kills:&a %KILLS% \n &7Tode:&a %DEAHTS% \n &7Rekord:&a %HIGHSCORE% \n &7Punkte:&a %POINTS% \n &7KD:&a %KD% \n &7Rang:&a #%RANK% \n &7&m&l-------------------------------------", "&m&l&7-------- &aStats&7 -&a %PLAYER% &m&l&7-------- \n &7Kills:&a %KILLS% \n &7Tode:&a %DEAHTS% \n &7Rekord:&a %HIGHSCORE% \n &7Punkte:&a %POINTS% \n &7KD:&a %KD% \n &7Rang:&a #%RANK% \n &7&m&l-------------------------------------", "Nachrichten welche bei dem Command /stats kommen. \n %PLAYER% = Spielername \n %KILLS% = Kills von dem Spieler \n %DEAHTS% = Tode von dem Spieler \n %POINTS% = Punkte von dem Spieler \n %HIGHSCORE% = Rekord von dem Spieler \n %KD% = KD von dem Spieler \n %RANK% = Rang von dem Spieler"),

    //SCOREBOARD
    SCOREBOARD(ConfigSections.SCOREBOARD, "scoreboard", "&8&M&l------------&2\n&6✦ &8▎ &7Map\n &8➥ &7%MAP%&a\n&8  \n&c✪ &8▎ &7Rekord\n &8➥ &7%RECORD%&c\n&d\n&c❤ &8▎ &7Kills\n &8➥ &7%KILLS%\n&1\n&c✿ &8▎ &7Rang\n &8➥ &7#%RANK%\n&8&M&l------------&3",
            "&8&M&l------------&2\n&6✦ &8▎ &7Map\n &8➥ &7%MAP%&a\n&8  \n&c✪ &8▎ &7Rekord\n &8➥ &7%RECORD%&c\n&d\n&c❤ &8▎ &7Kills\n &8➥ &7%KILLS%\n&1\n&c✿ &8▎ &7Rang\n &8➥ &7#%RANK%\n&8&M&l------------&3",
            "Scoreboard Einstellungen:\n %MAP% = Aktuelle Map/Arena \n %RECORD% = Highscore des Spielers \n %KILLS% = Alltime Kills eines Spielers \n %DEATHS% = Alltime Tode eines Spielers \n %RANK% = Rank eines Spielers \n %KD% = Kills/Deaths eines Spielers"),
    SCOREBOARDNAME(ConfigSections.SCOREBOARD, "name","&aGun&2Game", "&aGun&2Game", "Displayname des Scoreboards"),

    //SETTINGS
    ANTICROPTRAMPLE(ConfigSections.SETTINGS, "anticroptrample", true, true, "Einstellung um kein Pflanzen zu zertramplen"),

    //MYSQL
    MYSQL_HOST(ConfigSections.MYSQL, "host", "localhost", "localhost", "Hostname deiner MySQl Datenbank"),
    MYSQL_PASSWORD(ConfigSections.MYSQL, "password", "password", "password", "Passwort deiner MySQl Datenbank"),
    MYSQL_DATABASE(ConfigSections.MYSQL, "database", "gungame", "gungame", "Zu benutzende Datenbank von MySQl"),
    MYSQL_PORT(ConfigSections.MYSQL, "port", 3306, 3306, "Port deiner MySQl Datenbank"),
    MYSQL_USERNAME(ConfigSections.MYSQL, "username", "root", "root", "Username deiner MySQl Datenbank");



    private ConfigSections section;
    private String path,desc;
    private Object value, defaultValue;
    ConfigEntries(ConfigSections section, String path, Object value, Object defaultValue, String desc){
        this.section = section;
        this.path = path;
        this.value = value;
        this.defaultValue = defaultValue;
        this.desc = desc;
    }

    public int getAsInt(){
        return (int) getValue();
    }

    public String getAsString(){
        return getValue().toString().replaceAll("&", "§");
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object getDefaultValue() {
        return this.defaultValue;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public ConfigSections getSection() {
        return this.section;
    }

    @Override
    public String getDescription() {
        return this.desc;
    }
}
