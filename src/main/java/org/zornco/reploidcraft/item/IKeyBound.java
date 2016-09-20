package org.zornco.reploidcraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IKeyBound {

    public abstract void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, byte key);
    public abstract void doMouseAction(EntityPlayer thePlayer, ItemStack itemStack, int key);

}
