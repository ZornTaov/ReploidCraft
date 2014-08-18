package zornco.reploidcraftenv.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TileEntityMechBayController extends TileEntityMechBay implements IInventory {

	Container eventHandler;
	public ItemStack[] inv = new ItemStack[11];
	boolean isIC2Validated = false;
	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return var1 >= getSizeInventory() ? null : this.inv[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		if (this.inv[var1] != null)
		{
			if (this.inv[var1].stackSize <= var2)
			{
				ItemStack var3 = this.inv[var1];
				this.inv[var1] = null;
				if (this.eventHandler != null) this.eventHandler.onCraftMatrixChanged(this);
				return var3;
			}

			ItemStack var3 = this.inv[var1].splitStack(var2);

			if (this.inv[var1].stackSize == 0)
			{
				this.inv[var1] = null;
			}

			if (this.eventHandler != null) this.eventHandler.onCraftMatrixChanged(this);
			return var3;
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if (this.inv[var1] != null)
		{
			ItemStack itemstack = this.inv[var1];
			this.inv[var1] = null;
			return itemstack;
		}
		else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.inv[var1] = var2;
		if (this.eventHandler != null) this.eventHandler.onCraftMatrixChanged(this);
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
	}

	@Override
	public void markDirty() {
		super.markDirty();
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return (var1 != 10) || (var2 == null);
	}

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
