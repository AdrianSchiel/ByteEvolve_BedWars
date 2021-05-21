package de.byteevolve.bedwars.player;

import de.byteevolve.bedwars.arena.Arena;
import de.byteevolve.bedwars.itembuilder.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class PlayerHandler {

    private final Player player;

    public PlayerHandler(Player player) {
        this.player = player;
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



}
