package de.byteevolve.bedwars.itembuilder;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;


public class SkullBuilder {
    private String name;
    private String owner;
    private int count;
    private List<String> lore;

    public SkullBuilder(String owner, int count) {
        this.owner = owner;
        this.count = count;
        this.lore = new ArrayList<>();
    }

    public ItemStack build() {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, this.count, (short) 3);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwner(this.owner);
        skullMeta.setDisplayName(this.name);
        skullMeta.setLore(this.lore);
        itemStack.setItemMeta((ItemMeta) skullMeta);
        return itemStack;
    }

    public SkullBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public SkullBuilder setOwner(String owner) {
        this.owner = owner;
        return this;
    }

    public SkullBuilder setCount(int count) {
        this.count = count;
        return this;
    }

    public SkullBuilder addLore(String lore) {
        this.lore.add(lore);
        return this;
    }

    public SkullBuilder setLore(List<String> lore) {
        this.lore = lore;
        return this;
    }
}

