package zornco.reploidcraftenv.client.gui;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.TileEntityMechBay;
import zornco.reploidcraftenv.entities.armorParts.PartSlot;
import zornco.reploidcraftenv.items.ItemRideArmorPart;
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
		return super.isItemValid(p_75214_1_) && p_75214_1_.getItem() == ReploidCraftEnv.rideArmorPart && 
				PartSlot.getSlot(ItemRideArmorPart.getPartByMetadata(p_75214_1_.getItemDamage()).split("\\.")[1]) == this.slot && ((TileEntityMechBay) inventory).hasRide();
	}
}
