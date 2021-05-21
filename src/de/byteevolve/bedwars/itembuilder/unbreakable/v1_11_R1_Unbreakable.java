package de.byteevolve.bedwars.itembuilder.unbreakable;

import net.minecraft.server.v1_11_R1.NBTTagByte;
import net.minecraft.server.v1_11_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_11_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class v1_11_R1_Unbreakable implements Unbreakable{
    @Override
    public ItemStack makeUnbreakable(ItemStack itemStack) {
        net.minecraft.server.v1_11_R1.ItemStack nmsstack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = new NBTTagCompound();
        tag.set("Unbreakable", new NBTTagByte((byte) 1));
        nmsstack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsstack);
    }
}
