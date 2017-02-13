package org.zornco.reploidcraft.client.gui;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.block.TileEntityMechBay;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotMechBay extends Slot {
	PartSlot slot;
	public SlotMechBay(IInventory inv, int id, int x, int y, PartSlot slot) {
		super(inv, id, x, y);
		this.slot = slot;
	}
	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	 */
	public boolean isItemValid(ItemStack p_75214_1_)
	{
		return false;/*super.isItemValid(p_75214_1_) && p_75214_1_.getItem() == ReploidCraft.rideArmorPart && 
				PartSlot.getSlot(ItemRideArmorPart.getPartByMetadata(p_75214_1_.getItemDamage()).split("\\.")[1]) == this.slot && ((TileEntityMechBay) inventory).hasRide();*/
	}
}
