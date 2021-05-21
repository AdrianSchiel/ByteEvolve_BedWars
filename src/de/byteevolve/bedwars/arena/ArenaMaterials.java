package de.byteevolve.bedwars.arena;

import org.bukkit.Material;

public enum ArenaMaterials {

    BRONZE(Material.STAINED_CLAY),
    IRON(Material.IRON_BLOCK),
    GOLD(Material.GOLD_BLOCK);

    private Material material;

    public Material getMaterial() {
        return material;
    }

    ArenaMaterials(Material mat) {
        this.material = mat;
    }
}
