package de.byteevolve.bedwars.itembuilder.unbreakable;

import net.minecraft.server.v1_9_R2.NBTTagByte;
import net.minecraft.server.v1_9_R2.NBTTagCompound;
import org.bukkit.craftbukkit.v1_9_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class v1_9_R2_Unbreakable implements Unbreakable{

    @Override
    public ItemStack makeUnbreakable(ItemStack itemStack) {
        net.minecraft.server.v1_9_R2.ItemStack nmsstack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tag = new NBTTagCompound();
        tag.set("Unbreakable", new NBTTagByte((byte) 1));
        nmsstack.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsstack);
    }
}
