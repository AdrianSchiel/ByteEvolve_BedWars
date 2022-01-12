package de.byteevolve.bedwars.player;

import de.byteevolve.bedwars.BedWars;
import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.arena.ArenaMaterials;
import de.byteevolve.bedwars.arena.Teams;
import de.byteevolve.bedwars.configuration.config.ConfigEntries;
import de.byteevolve.bedwars.game.GameHandler;
import de.byteevolve.bedwars.game.Team;
import de.byteevolve.bedwars.game.VoteType;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
public class PlayerHandler {

    private final Player player;

    public PlayerHandler(Player player) {
        this.player = player;
    }

    public void setJoinEquip(){
        this.player.setMaxHealth(20);
        this.player.setHealth(20);
        this.player.setFoodLevel(20);
        this.player.getInventory().clear();
        this.player.getInventory().setItem(0, new ItemBuilder(Material.BED, 1).setName("§aTeamauswahl").build());
        this.player.getInventory().setItem(8, new ItemBuilder(Material.SLIME_BALL, 1).setName("§aStats").build());

        if(this.player.hasPermission("BedWars.GameSetup")){
            this.player.getInventory().setItem(1, new ItemBuilder(Material.BLAZE_POWDER, 1).setName("§7« §aGame§2Setup §7»").build());
        }

        this.player.getInventory().setItem(4, new ItemBuilder(Material.PAPER, 1).setName("§7« §aVoting §7»").build());

    }

    public void openForceMap(){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§7« §aForce§2Map §7»");

        for (int i = 0; i < BedWars.getInstance().getArenaHandler().getArenas().size() ; i++) {
            if(i < 6*9) {
                Arena arena = BedWars.getInstance().getArenaHandler().getArenas().get(i);
                if (arena.getTeams() == ConfigEntries.TEAMS.getAsInt()
                        && arena.getPlayers() == ConfigEntries.PLAYERSPERTEAM.getAsInt()) {
                    inventory.setItem(i, new ItemBuilder(Material.PAPER, 1)
                            .setName(arena.getDisplayname().replaceAll("&", "§"))
                            .addLore("§aName§7 » §2" + arena.getName()).build());
                }
            }
        }
        this.player.openInventory(inventory);
    }


    public void openGameSetup(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§7« §aGame§2Setup §7»");

        inventory.setItem(0, new ItemBuilder(Material.GOLD_NUGGET, 1).setName("§7« §aRunde §2starten §7»").build());
        inventory.setItem(1, new ItemBuilder(Material.PAPER, 1).setName("§7« §aForce§2Map §7»").build());

        this.player.openInventory(inventory);
    }

    public void openMapVote(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§7« §aMap§2Voting §7»");

        inventory.setItem(3, new ItemBuilder(Material.BARRIER, 1).setName("§7« §cVote Löschen §7»").build());

        GameHandler gameHandler = BedWars.getInstance().getGameHandler();
        if(gameHandler.getMapVote() != null) {
            if (gameHandler.getMapVote().getVotes().size() < 3) {
                for (int i = 0; i < gameHandler.getMapVote().getVotes().size(); i++) {
                    Arena arena = (Arena) gameHandler.getMapVote().getVotes().keySet().toArray()[i];
                    boolean glow = false;
                    if(gameHandler.getMapVote().hasVoted(player) && gameHandler.getMapVote().getPlayerVotes().get(player).equals(arena)) glow = true;
                    inventory.setItem(i, new ItemBuilder(Material.PAPER, 1).setGlow(glow)
                            .setName(arena.getDisplayname().replaceAll("&", "§"))
                            .addLore("§a" + gameHandler.getMapVote().getVotes().get(arena) + " §2Votes")
                            .addLore("§aName§7 » §2" + arena.getName()).build());
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    Arena arena = (Arena) gameHandler.getMapVote().getVotes().keySet().toArray()[i];
                    boolean glow = false;
                    if(gameHandler.getMapVote().hasVoted(player) && gameHandler.getMapVote().getPlayerVotes().get(player).equals(arena)) glow = true;
                    inventory.setItem(i, new ItemBuilder(Material.PAPER, 1).setGlow(glow)
                            .setName(arena.getDisplayname().replaceAll("&", "§"))
                            .addLore("§a" + gameHandler.getMapVote().getVotes().get(arena) + " §2Votes")
                            .addLore("§aName§7 » §2" + arena.getName()).build());
                }
            }
        }

        this.player.openInventory(inventory);
    }

    public void openVoting(){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.BREWING, "§7« §aVoting §7»");

        if(BedWars.getInstance().getGameHandler().getMapVote() != null) {
            inventory.setItem(1, new ItemBuilder(Material.PAPER, 1).setName("§7« §aMap§2Voting §7»").build());
        }
        inventory.setItem(0, new ItemBuilder(Material.GOLD_INGOT, 1).setName("§7« §aGold§2Voting §7»").build());
        inventory.setItem(2, new ItemBuilder(Material.WEB, 1).setName("§7« §aWeb§2Voting §7»").build());

        player.openInventory(inventory);
    }

    public void openTeamSelection(){
        Inventory inventory = Bukkit.createInventory(null, 1*9, "§aTeamauswahl");

        for(Team team : BedWars.getInstance().getGameHandler().getTeams()){
            ItemBuilder itemBuilder = new ItemBuilder(Material.WOOL, 1);
            itemBuilder.setSubId(team.getTeam().getWoolid());
            itemBuilder.setName(team.getTeam().getColor() + team.getTeam().name());
            itemBuilder.addLore("§7ID:" + team.getTeam().getId());
            for(Player player : team.getMembers()){
                itemBuilder.addLore("§7»" + team.getTeam().getColor() + player.getName());
            }
            inventory.addItem(itemBuilder.build());
        }

        player.openInventory(inventory);
    }

    public void openArenaEditMainInv(Arena arena){
        Inventory inventory = Bukkit.createInventory(null, 3*9, "§8Arena: §a" + arena.getName());
        player.openInventory(inventory);

        for (int i = 0; i < inventory.getSize() ; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
        }
        inventory.setItem(10, new ItemBuilder(Material.MAGMA_CREAM, 1).setName("§7Lobby setzen").build());
        inventory.setItem(11, new ItemBuilder(Material.COMPASS, 1).setName("§7Spectator Spawn setzten").build());
        inventory.setItem(12, new ItemBuilder(Material.ARMOR_STAND, 1).setName("§7Teams").build());

        inventory.setItem(14, new ItemBuilder(Material.STAINED_CLAY, 1).setName("§7Bronze Spawns").build());
        inventory.setItem(15, new ItemBuilder(Material.IRON_BLOCK, 1).setSubId(7).setName("§7Iron Spawns").build());
        inventory.setItem(16, new ItemBuilder(Material.GOLD_BLOCK, 1).setSubId(10).setName("§7Gold Spawns").build());
        inventory.setItem(22, new ItemBuilder(Material.INK_SACK, 1).setSubId(10).setName("§aBeenden").build());
    }

    public void openArenaTeamHubInv(Arena arena){
        Inventory inventory = Bukkit.createInventory(null, 1*9, "§8Teams: §a" + arena.getName());
        player.openInventory(inventory);
        for (int i = 0; i < arena.getTeams() ; i++) {
            inventory.setItem(i, new ItemBuilder(Material.WOOL, 1).setName("§7Team #"+ i)
                    .addLore("§7Klick zum öffnen")
                    .setSubId(Teams.fromID(i).getWoolid()).build());
        }
    }

    public void openArenaTeam(Arena arena, Teams team){
        Inventory inventory = Bukkit.createInventory(null, 3*9, "§8Team: §a" + team.getId());
        player.openInventory(inventory);

        for (int i = 0; i < inventory.getSize() ; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
        }
        inventory.setItem(10, new ItemBuilder(Material.BED, 1).setName("§7Bed Front").build());
        inventory.setItem(11, new ItemBuilder(Material.BED, 1).setName("§7Bed Back").build());

        inventory.setItem(15, new ItemBuilder(Material.EMERALD, 1).setName("§7Shop").build());
        inventory.setItem(16, new ItemBuilder(Material.MAGMA_CREAM, 1).setName("§7Spawn").build());
    }

    public void openArenaMaterialsInv(Arena arena, ArenaMaterials material){
        Inventory inventory = Bukkit.createInventory(null, 6*9, "§8Materials: §a" + arena.getName());
        player.openInventory(inventory);
        List<String> list = new ArrayList<>();

        switch (material){
            case BRONZE:
                list = arena.getBronze();
                break;
            case GOLD:
                list = arena.getGold();
                break;
            case IRON:
                list = arena.getIron();
                break;
        }

        if(list.size() == 1){
            if(list.get(0).equalsIgnoreCase("")){
                list.clear();
            }
        }

        for (int i = 0; i < list.size() ; i++) {
            if(i < 36){
                inventory.setItem(i, new ItemBuilder(material.getMaterial(), 1).setName("§7Material")
                        .addLore("§7Name:" + list.get(i))
                        .addLore("§7Type: " + material.name())
                        .addLore("§7Klicken zum löschen").build());
            }
        }
        for (int i = 36; i < 45; i++) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE, 1).setSubId(7).setName(" ").build());
        }
        inventory.setItem(49, new ItemBuilder(Material.INK_SACK, 1).setSubId(10).setName("§aSpawn setzen").addLore("§aType: " + material.toString()).build());

    }


    public void openGoldVote() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§7« §aGold§2Voting §7»");
        int forGold, againstGold;
        forGold = 0;
        againstGold = 0;
        if(!BedWars.getInstance().getGameHandler().getGoldVoting().isEmpty()) {
            for (int i = 0; i < BedWars.getInstance().getGameHandler().getGoldVoting().size(); i++) {
                Player target = (Player) BedWars.getInstance().getGameHandler().getGoldVoting().keySet().toArray()[i];
                VoteType type = BedWars.getInstance().getGameHandler().getGoldVoting().get(target);
                switch (type) {
                    case FOR:
                        forGold++;
                        break;
                    case AGAINST:
                        againstGold++;
                        break;
                }
            }
        }

        inventory.setItem(1, new ItemBuilder(Material.INK_SACK, 1).setSubId(10)
                .setName("§aFür Gold Stimmen")
                .addLore("§a" + forGold +" §2Votes")
                .build());
        inventory.setItem(3, new ItemBuilder(Material.INK_SACK, 1).setSubId(1)
                .setName("§cGegen Gold Stimmen")
                .addLore("§c" + againstGold +" §4Votes")
                .build());
        this.player.openInventory(inventory);

    }


    public void openWebVote() {
        Inventory inventory = Bukkit.createInventory(null, InventoryType.HOPPER, "§7« §aWeb§2Voting §7»");
        int forWeb, againstWeb;
        forWeb = 0;
        againstWeb = 0;
        if(!BedWars.getInstance().getGameHandler().getWebVoting().isEmpty()) {
            for (int i = 0; i < BedWars.getInstance().getGameHandler().getWebVoting().size(); i++) {
                Player target = (Player) BedWars.getInstance().getGameHandler().getWebVoting().keySet().toArray()[i];
                VoteType type = BedWars.getInstance().getGameHandler().getWebVoting().get(target);
                switch (type) {
                    case FOR:
                        forWeb++;
                        break;
                    case AGAINST:
                        againstWeb++;
                        break;
                }
            }
        }

        inventory.setItem(1, new ItemBuilder(Material.INK_SACK, 1).setSubId(10)
                .setName("§aFür Webs Stimmen")
                .addLore("§a" + forWeb +" §2Votes")
                .build());
        inventory.setItem(3, new ItemBuilder(Material.INK_SACK, 1).setSubId(1)
                .setName("§cGegen Webs Stimmen")
                .addLore("§c" + againstWeb +" §4Votes")
                .build());
        this.player.openInventory(inventory);

    }
}
