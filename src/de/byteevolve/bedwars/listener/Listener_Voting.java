package de.byteevolve.bedwars.listener;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.game.GameState;
import de.byteevolve.bedwars.game.MapVote;
import de.byteevolve.bedwars.game.VoteType;
import de.byteevolve.bedwars.player.PlayerHandler;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class Listener_Voting implements Listener {

    @EventHandler
    public void onInterract(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (event.getItem() != null && event.getItem().getItemMeta() != null) {
            if (item.getType().equals(Material.PAPER)
                    && item.getItemMeta().getDisplayName().equalsIgnoreCase("§7« §aVoting §7»")) {
                event.setCancelled(true);
                if (BedWars.getInstance().getGameHandler().getGameState() == GameState.LOBBY) {
                    new PlayerHandler(event.getPlayer()).openVoting();
                }
            }
        }
    }

    @EventHandler
    public void onVoteMenu(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("§7« §aVoting §7»")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§7« §aMap§2Voting §7»")) {
                new PlayerHandler(player).openMapVote();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§7« §aGold§2Voting §7»")) {
                new PlayerHandler(player).openGoldVote();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§7« §aWeb§2Voting §7»")) {
                new PlayerHandler(player).openWebVote();
            }
        }
    }

    @EventHandler
    public void onGoldVote(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("§7« §aGold§2Voting §7»")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            BedWars bedWars = BedWars.getInstance();
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aFür Gold Stimmen")) {
                bedWars.getGameHandler().getGoldVoting().put(player, VoteType.FOR);
                player.sendMessage(BedWars.getInstance().getPrefix() + " §8Du hast für Gold gestimmt!");
                player.closeInventory();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGegen Gold Stimmen")) {
                bedWars.getGameHandler().getGoldVoting().put(player, VoteType.AGAINST);
                player.sendMessage(BedWars.getInstance().getPrefix() + " §8Du hast gegen Gold gestimmt!");
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onWebVote(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("§7« §aWeb§2Voting §7»")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            BedWars bedWars = BedWars.getInstance();
            if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§aFür Webs Stimmen")) {
                bedWars.getGameHandler().getWebVoting().put(player, VoteType.FOR);
                player.sendMessage(BedWars.getInstance().getPrefix() + " §8Du hast für Webs gestimmt!");
                player.closeInventory();
            } else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§cGegen Webs Stimmen")) {
                bedWars.getGameHandler().getWebVoting().put(player, VoteType.AGAINST);
                player.sendMessage(BedWars.getInstance().getPrefix() + " §8Du hast gegen Webs gestimmt!");
                player.closeInventory();
            }
        }
    }

    @EventHandler
    public void onMapVote(InventoryClickEvent event) {
        if (event.getInventory().getTitle().equals("§7« §aMap§2Voting §7»")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            BedWars bedWars = BedWars.getInstance();
            if (event.getCurrentItem().getType().equals(Material.PAPER)) {
                if (event.getCurrentItem().getItemMeta().hasLore() &&
                        event.getCurrentItem().getItemMeta().getLore().size() == 2) {
                    if (event.getCurrentItem().getItemMeta().getLore().get(1).startsWith("§aName§7 » §2")) {
                        String name = event.getCurrentItem().getItemMeta().getLore().get(1).replaceAll("§aName§7 » §2", "");
                        Arena arena = null;
                        arena = bedWars.getArenaHandler().getArenaByName(name);
                        MapVote mapVote = bedWars.getGameHandler().getMapVote();
                        if (arena != null) {
                            if (mapVote.getVotes().containsKey(arena)) {
                                if (mapVote.hasVoted(player)) {
                                    if (mapVote.getPlayerVotes().get(player).equals(arena)) {
                                        player.closeInventory();
                                        player.sendMessage("Du hast die Map schon gevotet");
                                    } else {
                                        int old = mapVote.getVotes().get(arena);
                                        mapVote.getVotes().put(arena, old + 1);
                                        int playerold = mapVote.getVotes().get(mapVote.getPlayerVotes().get(player));
                                        mapVote.getVotes().put(mapVote.getPlayerVotes().get(player), playerold - 1);
                                        mapVote.getPlayerVotes().remove(player);
                                        mapVote.getPlayerVotes().put(player, arena);
                                        player.sendMessage("Du hast für Arena: " + arena.getName() + " gevotet.");
                                        player.closeInventory();
                                    }
                                } else {
                                    int old = mapVote.getVotes().get(arena);
                                    mapVote.getVotes().put(arena, old + 1);
                                    mapVote.getPlayerVotes().put(player, arena);
                                    player.sendMessage("Du hast für Arena: " + arena.getName() + " gevotet.");
                                    player.closeInventory();
                                }

                            } else {
                                player.sendMessage("Du kannst die Map nicht voten");
                                player.closeInventory();
                            }
                        } else {
                            player.sendMessage("Die Map gibt es nicht");
                            player.closeInventory();
                        }
                    }
                }
            } else if (event.getCurrentItem().getType().equals(Material.BARRIER)) {
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§7« §cVote Löschen §7»")) {
                    if (bedWars.getGameHandler().getMapVote().hasVoted(player)) {
                        Arena arena = bedWars.getGameHandler().getMapVote().getPlayerVotes().get(player);
                        int current = bedWars.getGameHandler().getMapVote().getVotes().get(arena);
                        bedWars.getGameHandler().getMapVote().getVotes().put(arena, current - 1);
                        bedWars.getGameHandler().getMapVote().getPlayerVotes().remove(player);
                        player.closeInventory();
                        player.sendMessage("Du hast deinen Vote gelöscht");
                    } else {
                        player.sendMessage("Du hast noch nicht gevotet");
                        player.closeInventory();
                    }
                }
            }
        }
    }

}
