package zornco.reploidcraftenv.blocks;

import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityMechBayController extends TileEntityMechBay{

	Container eventHandler;
	boolean isIC2Validated = false;

	@Override
	public void masterWriteToNBT(NBTTagCompound compound) {
		super.masterWriteToNBT(compound);
		
		NBTTagList list = new NBTTagList();
		for (int i = 0; i < inv.length; i++) {
			if(this.inv[i] == null)
				continue;
			NBTTagCompound tag = new NBTTagCompound();
			tag.setByte("Slot", (byte)i);
			this.inv[i].writeToNBT(tag);
			list.appendTag(tag);
		}

		compound.setTag("Inventory", list);
	}

	@Override
	public void masterReadFromNBT(NBTTagCompound compound) {
		super.masterReadFromNBT(compound);

		NBTTagList list = compound.getTagList("Inventory", 10);
		this.inv = new ItemStack[getSizeInventory()];

		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound tag = list.getCompoundTagAt(i);
			int slot = tag.getByte("Slot") & 0xFF;

			if ((slot < 0) || slot >= this.inv.length)
				continue;
			this.inv[slot] = ItemStack.loadItemStackFromNBT(tag);
		}
	}	
}
