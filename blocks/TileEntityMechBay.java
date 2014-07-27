package zornco.reploidcraftenv.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMechBay extends TileMultiBlock implements IInventory {
	public ItemStack[] inv = new ItemStack[11];
	Container eventHandler;
	
	public Packet func_145844_m()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		masterWriteToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
		masterReadFromNBT(pkt.func_148857_g());
	}

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
	public void doMultiBlockStuff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean checkMultiBlockForm() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setupStructure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetStructure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void masterWriteToNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void masterReadFromNBT(NBTTagCompound tag) {
		// TODO Auto-generated method stub
		
	}
}
