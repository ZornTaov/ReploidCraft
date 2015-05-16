package zornco.reploidcraft.client.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.entities.armorParts.PartSlot;
import zornco.reploidcraft.items.ItemRideArmorPart;

public class SlotMechBayBody extends Slot {
	PartSlot slot;
	public SlotMechBayBody(IInventory inv, int id, int x, int y, PartSlot slot) {
		super(inv, id, x, y);
		this.slot = slot;
	}
	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	 */
	public boolean isItemValid(ItemStack p_75214_1_)
	{
		return super.isItemValid(p_75214_1_) && p_75214_1_.getItem() == ReploidCraft.rideArmorPart && 
				PartSlot.getSlot(ItemRideArmorPart.getPartByMetadata(p_75214_1_.getItemDamage()).split("\\.")[1]) == this.slot;
	}
}
