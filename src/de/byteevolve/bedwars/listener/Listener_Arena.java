package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.ArenaHandler;
import de.byteevolve.bedwars.arena.ArenaMaterials;
import de.byteevolve.bedwars.location.Loc;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
                    arena.setSpawn(arena.getName() + "spec");
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
                if(displayName.equalsIgnoreCase("§aBRONZE Spawn setzen")){
                    List<String> list = arena.getBronze();
                    int i = 0;
                    if(!list.isEmpty()){
                        String last = list.get(list.size() -1);
                        last = last.replaceAll(arena.getName() + "bronze", "");
                        i = Integer.parseInt(last);
                        i++;
                    }
                    Loc loc = new Loc(arena.getName() + "bronze" + i);
                    loc.setFromLocation(player.getLocation());
                    loc.update();
                    bedWars.getLocationHandler().loadLocs();
                    if(arena.getBronzeraw() == null) arena.setBronzeraw("");
                    arena.setBronzeraw(arena.getBronzeraw() + arena.getName() + "bronze" + i + ",");
                    if (arena.getFinished() == 1) {
                        arena.update();
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                        player.closeInventory();
                        if (arenaHandler.getArenaEditList().containsKey(player)) {
                            arenaHandler.getArenaEditList().remove(player);
                        }
                    } else {
                        player.sendMessage(bedWars.getPrefix() + "§7Du hast eine Bronze-Location gesetzt.");
                        player.closeInventory();
                    }
                }else if(displayName.equalsIgnoreCase("§7BRONZE")){
                    String name = itemMeta.getLore().get(0).replaceAll("§7Name:", "");
                    if(arena.getBronzeraw().contains(name)){
                        Loc loc = new Loc(name);
                        loc.delete();
                        bedWars.getLocationHandler().loadLocs();
                        arena.setBronzeraw(arena.getBronzeraw().replace(name + ",", ""));
                        if (arena.getFinished() == 1) {
                            arena.update();
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast die Arena: §a" + arena.getDisplayname() + "§7 geupdatet.");
                            player.closeInventory();
                            if (arenaHandler.getArenaEditList().containsKey(player)) {
                                arenaHandler.getArenaEditList().remove(player);
                            }
                        } else {
                            player.sendMessage(bedWars.getPrefix() + "§7Du hast den Bronze-Spawn:§a " + name +" §cgelöscht.");
                            player.closeInventory();
                        }
                    }
                }
            }
        }

    }

}
