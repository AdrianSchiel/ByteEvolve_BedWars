package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.ArenaHandler;
import de.byteevolve.bedwars.arena.ArenaMaterials;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.location.Loc;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Listener_Arena implements Listener {


    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getInventory().getTitle().startsWith("§8Arena: §a")){
            Player player = (Player) event.getWhoClicked();
            BedWars bedWars = BedWars.getInstance();
            ArenaHandler arenaHandler = bedWars.getArenaHandler();
            Arena arena = null;
            if (arenaHandler.getArenaEditList().containsKey(player)) {
                arena = arenaHandler.getArenaEditList().get(player);
            } else if (arenaHandler.getArenaPlayerCreate().containsKey(player)) {
                arena = arenaHandler.getArenaPlayerCreate().get(player);
            }
            if (arena != null) {
                event.setCancelled(true);
                ItemStack item = event.getCurrentItem();
                ItemMeta itemMeta = item.getItemMeta();
                String displayName = itemMeta.getDisplayName();
                if(displayName.equalsIgnoreCase("§7Lobby setzen")){
                    Loc loc = new Loc(arena.getName() + "lobby");
                    loc.setFromLocation(player.getLocation());
                    loc.update();
                    bedWars.getLocationHandler().loadLocs();
                    arena.setSpawn(arena.getName() + "lobby");
                    if (arena.getFinished() == 1) {
                        arena.update();
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                        player.closeInventory();
                        if (arenaHandler.getArenaEditList().containsKey(player)) {
                            arenaHandler.getArenaEditList().remove(player);
                        }
                    } else {
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast die Lobby-Location gesetzt.");
                        player.closeInventory();
                    }

                }else if(displayName.equalsIgnoreCase("§7Spectator Spawn setzten")){
                    Loc loc = new Loc(arena.getName() + "spec");
                    loc.setFromLocation(player.getLocation());
                    loc.update();
                    bedWars.getLocationHandler().loadLocs();
                    arena.setSpawnspec(arena.getName() + "spec");
                    if (arena.getFinished() == 1) {
                        arena.update();
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                        player.closeInventory();
                        if (arenaHandler.getArenaEditList().containsKey(player)) {
                            arenaHandler.getArenaEditList().remove(player);
                        }
                    } else {
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast die Spectator-Location gesetzt.");
                        player.closeInventory();
                    }

                }else if(displayName.equalsIgnoreCase("§7Bronze Spawns")){
                    new PlayerHandler(player).openArenaMaterialsInv(arena, ArenaMaterials.BRONZE);
                }else if(displayName.equalsIgnoreCase("§7Iron Spawns")){
                    new PlayerHandler(player).openArenaMaterialsInv(arena, ArenaMaterials.IRON);
                }else if(displayName.equalsIgnoreCase("§7Gold Spawns")){
                    new PlayerHandler(player).openArenaMaterialsInv(arena, ArenaMaterials.GOLD);
                }else if(displayName.equalsIgnoreCase("§7Teams")){
                    new PlayerHandler(player).openArenaTeamHubInv(arena);
                }else if(displayName.equalsIgnoreCase("§aBeenden")){
                    if (arena.getFinished() == 0) {
                        System.out.println(arena.getSpawn());
                        System.out.println(arena.getSpawnspec());
                        System.out.println(arena.getBronzeraw());
                        System.out.println(arena.getIronraw());
                        System.out.println(arena.getGoldraw());
                        System.out.println(arena.getBedsraw());
                        System.out.println(arena.getSpawnsraw());
                        System.out.println(arena.getShopsraw());

                        if (arena.getSpawn() != null && arena.getSpawnspec() != null && arena.getBronzeraw() != null
                                && arena.getGoldraw() != null && arena.getIronraw() != null && arena.getBedsraw() != null
                                && arena.getShopsraw() != null && arena.getSpawnsraw() != null) {
                            arena.setFinished(1);
                            arena.update();
                            if (arenaHandler.getArenaPlayerCreate().containsKey(player)) {
                                arenaHandler.getArenaPlayerCreate().remove(player);
                            }
                            player.sendMessage(bedWars.getPrefix() + "§7Die Arena:§a" + arena.getDisplayname().replaceAll("&", "§") + " §7wurde beendet.");
                            player.closeInventory();
                            if (arenaHandler.getArenaEditList().containsKey(player)) {
                                arenaHandler.getArenaEditList().remove(player);
                            }
                        } else {
                            player.sendMessage(bedWars.getPrefix() + "§cDie Arena ist noch nicht fertig.");
                            player.closeInventory();
                        }
                    } else {
                        player.sendMessage(bedWars.getPrefix() + "§cDie Arena ist schon beendet.");
                        player.closeInventory();

                    }
                }
            }
        }else if(event.getInventory().getTitle().startsWith("§8Materials: §a")){
            Player player = (Player) event.getWhoClicked();
            BedWars bedWars = BedWars.getInstance();
            ArenaHandler arenaHandler = bedWars.getArenaHandler();
            Arena arena = null;
            if (arenaHandler.getArenaEditList().containsKey(player)) {
                arena = arenaHandler.getArenaEditList().get(player);
            } else if (arenaHandler.getArenaPlayerCreate().containsKey(player)) {
                arena = arenaHandler.getArenaPlayerCreate().get(player);
            }
            if (arena != null) {
                event.setCancelled(true);
                ItemStack item = event.getCurrentItem();
                ItemMeta itemMeta = item.getItemMeta();
                String displayName = itemMeta.getDisplayName();
                if(displayName.equalsIgnoreCase("§aSpawn setzen")){
                    ArenaMaterials arenaMaterial = ArenaMaterials.valueOf(itemMeta.getLore().get(0).replaceAll("§aType: ", ""));
                    List<String> list = new ArrayList<>();
                    switch (arenaMaterial){
                        case BRONZE:
                            list = arena.getBronze();
                            break;
                        case IRON:
                            list = arena.getIron();
                            break;
                        case GOLD:
                            list = arena.getGold();
                            break;
                    }
                    int i = 0;
                    if(!list.isEmpty()){
                        String last = list.get(list.size() -1);
                        last = last.replaceAll(arena.getName() + arenaMaterial.toString().toLowerCase(), "");
                        i = Integer.parseInt(last);
                        i++;
                    }
                    Loc loc = new Loc(arena.getName() + arenaMaterial.toString().toLowerCase() + i);
                    loc.setFromLocation(player.getLocation());
                    loc.update();
                    bedWars.getLocationHandler().loadLocs();
                    switch (arenaMaterial){
                        case BRONZE:
                            if(arena.getBronzeraw() == null) arena.setBronzeraw("");
                            arena.setBronzeraw(arena.getBronzeraw() + arena.getName() + arenaMaterial.toString().toLowerCase() + i + ",");
                            break;
                        case IRON:
                            if(arena.getIronraw() == null) arena.setIronraw("");
                            arena.setIronraw(arena.getIronraw() + arena.getName() + arenaMaterial.toString().toLowerCase() + i + ",");
                            break;
                        case GOLD:
                            if(arena.getGoldraw() == null) arena.setGoldraw("");
                            arena.setGoldraw(arena.getGoldraw() + arena.getName() + arenaMaterial.toString().toLowerCase() + i + ",");
                            break;
                    }
                    if (arena.getFinished() == 1) {
                        arena.update();
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                        player.closeInventory();
                        if (arenaHandler.getArenaEditList().containsKey(player)) {
                            arenaHandler.getArenaEditList().remove(player);
                        }
                    } else {
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast eine " + arenaMaterial.toString().toLowerCase()+"-Location gesetzt.");
                        player.closeInventory();
                    }
                }else if(displayName.equalsIgnoreCase("§7Material")){
                    String name = itemMeta.getLore().get(0).replaceAll("§7Name:", "");
                    ArenaMaterials arenaMaterial = ArenaMaterials.valueOf(itemMeta.getLore().get(1).replaceAll("§7Type: ", ""));
                    String raw = "";
                    switch (arenaMaterial){
                        case BRONZE:
                            raw = arena.getBronzeraw();
                            break;
                        case IRON:
                            raw = arena.getIronraw();
                            break;
                        case GOLD:
                            raw = arena.getGoldraw();
                            break;
                    }
                    if(raw.contains(name)){
                        Loc loc = new Loc(name);
                        loc.delete();
                        bedWars.getLocationHandler().loadLocs();
                        switch (arenaMaterial){
                            case BRONZE:
                                arena.setBronzeraw(arena.getBronzeraw().replace(name + ",", ""));
                                break;
                            case IRON:
                                arena.setIronraw(arena.getIronraw().replace(name + ",", ""));
                                break;
                            case GOLD:
                                arena.setGoldraw(arena.getGoldraw().replace(name + ",", ""));
                                break;
                        }
                        if (arena.getFinished() == 1) {
                            arena.update();
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                            player.closeInventory();
                            if (arenaHandler.getArenaEditList().containsKey(player)) {
                                arenaHandler.getArenaEditList().remove(player);
                            }
                        } else {
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast den " +arenaMaterial.name().toLowerCase() +"-Spawn:§a " + name +" §cgelöscht.");
                            player.closeInventory();
                        }
                    }
                }
            }
        }else if(event.getInventory().getTitle().startsWith("§8Teams: §a")){
            Player player = (Player) event.getWhoClicked();
            BedWars bedWars = BedWars.getInstance();
            ArenaHandler arenaHandler = bedWars.getArenaHandler();
            Arena arena = null;
            if (arenaHandler.getArenaEditList().containsKey(player)) {
                arena = arenaHandler.getArenaEditList().get(player);
            } else if (arenaHandler.getArenaPlayerCreate().containsKey(player)) {
                arena = arenaHandler.getArenaPlayerCreate().get(player);
            }
            if (arena != null) {
                event.setCancelled(true);
                ItemStack item = event.getCurrentItem();
                ItemMeta itemMeta = item.getItemMeta();
                String displayName = itemMeta.getDisplayName();
                if (displayName.startsWith("§7Team #")) {
                    int id = Integer.valueOf(displayName.replaceAll("§7Team #", ""));
                    new PlayerHandler(player).openArenaTeam(arena, Teams.fromID(id));
                }
            }
        }else if(event.getInventory().getTitle().startsWith("§8Team: §a")){
            Player player = (Player) event.getWhoClicked();
            BedWars bedWars = BedWars.getInstance();
            ArenaHandler arenaHandler = bedWars.getArenaHandler();
            Arena arena = null;
            if (arenaHandler.getArenaEditList().containsKey(player)) {
                arena = arenaHandler.getArenaEditList().get(player);
            } else if (arenaHandler.getArenaPlayerCreate().containsKey(player)) {
                arena = arenaHandler.getArenaPlayerCreate().get(player);
            }
            if (arena != null) {
                event.setCancelled(true);
                ItemStack item = event.getCurrentItem();
                ItemMeta itemMeta = item.getItemMeta();
                String displayName = itemMeta.getDisplayName();
                int teamid = Integer.valueOf(event.getInventory().getTitle().replaceAll("§8Team: §a", ""));
                if(displayName.equalsIgnoreCase("§7Bed Front")){
                    if(player.getLocation().getBlock().getType() == Material.BED_BLOCK){
                        String name = arena.getName() + "team" + teamid + "bedfront";
                        Loc loc = new Loc(name);
                        loc.setFromLocation(player.getLocation());
                        loc.update();
                        bedWars.getLocationHandler().loadLocs();
                        if(arena.getBedsraw() == null) arena.setBedsraw("");
                        if(!arena.getBedsraw().contains(name)){
                            arena.setBedsraw(arena.getBedsraw() + name + ",");
                        }
                        if (arena.getFinished() == 1) {
                            arena.update();
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                            player.closeInventory();
                            if (arenaHandler.getArenaEditList().containsKey(player)) {
                                arenaHandler.getArenaEditList().remove(player);
                            }
                        } else {
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast den vorderen BedBlock von Team: §a" + teamid +" §7gesetzt.");
                            player.closeInventory();
                        }

                    }else{
                        player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.ARENAPLAYERMUSTSTANDONBED.getAsString());
                        player.closeInventory();
                    }
                }else if(displayName.equalsIgnoreCase("§7Bed Back")){
                    if(player.getLocation().getBlock().getType() == Material.BED_BLOCK){
                        String name = arena.getName() + "team" + teamid + "bedback";
                        Loc loc = new Loc(name);
                        loc.setFromLocation(player.getLocation());
                        loc.update();
                        bedWars.getLocationHandler().loadLocs();
                        if(arena.getBedsraw() == null) arena.setBedsraw("");
                        if(!arena.getBedsraw().contains(name)){
                            arena.setBedsraw(arena.getBedsraw() + name + ",");
                        }
                        if (arena.getFinished() == 1) {
                            arena.update();
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                            player.closeInventory();
                            if (arenaHandler.getArenaEditList().containsKey(player)) {
                                arenaHandler.getArenaEditList().remove(player);
                            }
                        } else {
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast den hinteren BedBlock von Team: §a" + teamid +" §7gesetzt.");
                            player.closeInventory();
                        }

                    }else{
                        player.sendMessage(BedWars.getInstance().getPrefix() + ConfigEntries.ARENAPLAYERMUSTSTANDONBED.getAsString());
                        player.closeInventory();
                    }
                }else if(displayName.equalsIgnoreCase("§7Shop")){
                        String name = arena.getName() + "team" + teamid + "shop";
                        Loc loc = new Loc(name);
                        loc.setFromLocation(player.getLocation());
                        loc.update();
                        bedWars.getLocationHandler().loadLocs();
                        if(arena.getShopsraw() == null) arena.setShopsraw("");
                        if(!arena.getShopsraw().contains(name)){
                            arena.setShopsraw(arena.getShopsraw() + name + ",");
                        }
                        if (arena.getFinished() == 1) {
                            arena.update();
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                            player.closeInventory();
                            if (arenaHandler.getArenaEditList().containsKey(player)) {
                                arenaHandler.getArenaEditList().remove(player);
                            }
                        } else {
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast den Shop von Team: §a" + teamid +" §7gesetzt.");
                            player.closeInventory();
                        }
                }else if(displayName.equalsIgnoreCase("§7Spawn")){
                    String name = arena.getName() + "team" + teamid + "spawn";
                    Loc loc = new Loc(name);
                    loc.setFromLocation(player.getLocation());
                    loc.update();
                    bedWars.getLocationHandler().loadLocs();
                    if(arena.getSpawnsraw() == null) arena.setSpawnsraw("");
                    if(!arena.getSpawnsraw().contains(name)){
                        arena.setSpawnsraw(arena.getSpawnsraw() + name + ",");
                    }
                    if (arena.getFinished() == 1) {
                        arena.update();
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                        player.closeInventory();
                        if (arenaHandler.getArenaEditList().containsKey(player)) {
                            arenaHandler.getArenaEditList().remove(player);
                        }
                    } else {
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast den Spawn von Team: §a" + teamid +" §7gesetzt.");
                        player.closeInventory();
                    }
                }
            }
        }

    }

}
