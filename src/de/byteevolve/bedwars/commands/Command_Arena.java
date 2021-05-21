package de.byteevolve.bedwars.commands;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Arena implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage(BedWars.getInstance().getMustAPlayer());
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("GunGame.arena")) {
            player.sendMessage(BedWars.getInstance().getNoPerm());
            return true;
        }

        if(args.length >= 5){
            if(args[0].equalsIgnoreCase("create")){
                int players = Integer.parseInt(args[3]);
                int teams = Integer.parseInt(args[2]);
                String dpn = "";
                for (int i = 2; i < args.length; i++) {
                    dpn = dpn + args[i] + " ";
                }
                if(BedWars.getInstance().getArenaHandler().getArenaPlayerCreate().containsKey(player)){
                    player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.PLAYERCREATESARENA.getAsString());
                    return true;
                }

                if(BedWars.getInstance().getArenaHandler().existArenaByName(args[1]) || BedWars.getInstance().getArenaHandler().existArenaPlayerCreateByName(args[1])){
                    player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.ARENAEXISTS.getAsString().replaceAll("%ARENANAME%", args[1]));
                    return true;
                }

                Arena arena = new Arena(args[1]);
                arena.setDisplayname(dpn);
                arena.setTeams(teams);
                arena.setPlayers(players);
                BedWars.getInstance().getArenaHandler().getArenaPlayerCreate().put(player, arena);
                player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.PLAYERCREATEARENA.getAsString().replaceAll("%ARENANAME%", args[1]));
                return true;
            }else player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.ARENAHELP.getAsString());
        }

        if(args.length == 2) {
            switch (args[0].toLowerCase()) {
                case "edit":
                    if (BedWars.getInstance().getArenaHandler().existArenaByName(args[1])) {
                        BedWars.getInstance().getArenaHandler().getArenaEditList().put(player, BedWars.getInstance().getArenaHandler().getArenaByName(args[1]));
                        new PlayerHandler(player).openArenaEditMainInv(BedWars.getInstance().getArenaHandler().getArenaByName(args[1]));
                    } else
                        player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.ARENANOTEXISTS.getAsString().replaceAll("%ARENANAME%", args[1]));
                    break;
                case "delete":
                    break;
            }
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("edit")){
                if(BedWars.getInstance().getArenaHandler().getArenaPlayerCreate().containsKey(player))
                    new PlayerHandler(player).openArenaEditMainInv(BedWars.getInstance().getArenaHandler().getArenaPlayerCreate().get(player));
                else player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.PLAYERNOTCREATESARENA.getAsString());
            }else if(args[0].equalsIgnoreCase("list")){
                if(BedWars.getInstance().getArenaHandler().getArenas().isEmpty())
                    player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.NOARENAEXISTS.getAsString());

                for(Arena arena : BedWars.getInstance().getArenaHandler().getArenas()){
                    player.sendMessage(ConfigEntries.PREFIX.getAsString() + "§a" + arena.getDisplayname().replaceAll("&", "§") + "§7»");
                }
            }else player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.ARENAHELP.getAsString());
        }else player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.ARENAHELP.getAsString());




        return true;
    }


}
