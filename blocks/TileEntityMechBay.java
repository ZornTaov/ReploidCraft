package zornco.reploidcraftenv.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMechBay extends TileMultiBlock implements IInventory {
	public ItemStack[] inv = new ItemStack[11];
	Container eventHandler;
	private int direction = 0;
	private static final int[][] baseBlocks = new int[][] {
		{ 0, 0, 0 }, { -1, 0, 0 }, { 1, 0, 0 }, { 0, 0, -1 }, { 0, 0, 1 }, { -1, 0, -1 }, { -1, 0, 1 }, { 1, 0, -1 }, { 1, 0, 1 }
	};
	private static final int[][] airBlocks = new int[][] {
		{ -1, 0, -1 }, { -1, 0, 0 }, { -1, 0, 1 }, { 0, 0, -1 }, { 0, 0, 0 }, { 0, 0, 1 }, { 1, 0, -1 }, { 1, 0, 0 }, { 1, 0, 1 }
	};
	private static final int[][][] sideRotations = new int[][][] {

		//North South
		{
			{ 2, 0, -1 }, { 2, 0, 0 }, { 2, 0, 1 }, { 2, 1, -1 }, { 2, 1, 0 },	{ 2, 1, 1 },
			{ -2, 0, -1 }, { -2, 0, 0 }, { -2, 0, 1 }, { -2, 1, -1 }, { -2, 1, 0 }, { -2, 1, 1 }
		},
		//East West
		{
			{ -1, 0, -2 }, { 0, 0, -2 }, { 1, 0, -2 }, { -1, 1, -2 }, { 0, 1, -2 }, { 1, 1, -2 },
			{ -1, 0, 2 }, { 0, 0, 2 }, { 1, 0, 2 }, { -1, 1, 2 }, { 0, 1, 2 }, { 1, 1, 2 }
		}
	};
	private static final int[][][] backRotations = new int[][][] {
		//North
		{
			{ -1, 0, -2 }, { 0, 0, -2 }, { 1, 0, -2 }, { -1, 1, -2 }, { 0, 1, -2 }, { 1, 1, -2 }, { -1, 2, -2 }, { 0, 2, -2 }, { 1, 2, -2 }
		},
		//South
		{
			{ -1, 0, 2 }, { 0, 0, 2 }, { 1, 0, 2 }, { -1, 1, 2 }, { 0, 1, 2 }, { 1, 1, 2 }, { -1, 2, 2 }, { 0, 2, 2 }, { 1, 2, 2 }
		},
		//West
		{
			{ -2, 0, -1 }, { -2, 0, 0 }, { -2, 0, 1 }, { -2, 1, -1 }, { -2, 1, 0 }, { -2, 1, 1 }, { -2, 2, -1 }, { -2, 2, 0 }, { -2, 2, 1 }
		},
		//East
		{
			{ 2, 0, -1 }, { 2, 0, 0 }, { 2, 0, 1 }, { 2, 1, -1 }, { 2, 1, 0 }, { 2, 1, 1 }, { 2, 2, -1 }, { 2, 2, 0 }, { 2, 2, 1 }
		}
	};
	private static final int[][] backCheck = new int [][] {
		{ 0, 2, -2 }, { 0, 2, 2 }, { -2, 2, 0 }, { 2, 2, 0 }
	};

	public Packet getDescriptionPacket()
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
		int dir = -1;
		int checked = 0;
		TileEntity tile;
		for (int i = 0; i < backCheck.length; i++) {
			if (worldObj.getTileEntity(this.xCoord + backCheck[i][0], this.yCoord + backCheck[i][1]+1, this.zCoord + backCheck[i][2]) instanceof TileEntityMechBay)
			{
				checked++;
				dir = i;
			}
		}
		if (checked != 1) {
			//set invalid structure
			return false;
		}
		for (int[] base : baseBlocks) {
			tile = worldObj.getTileEntity(this.xCoord + base[0], this.yCoord + base[1], this.zCoord + base[2]);
			if(!(tile instanceof TileEntityMechBay) )
			{
				return false;
			}
		}
		for (int[] back : backRotations[dir])
		{
			tile = worldObj.getTileEntity(this.xCoord + back[0], this.yCoord + back[1]+1, this.zCoord + back[2]);
			if(!(tile instanceof TileEntityMechBay) )
			{
				return false;
			}
		}
		
		for (int[] sides : sideRotations[dir / 2])
		{
			tile = worldObj.getTileEntity(this.xCoord + sides[0], this.yCoord + sides[1]+1, this.zCoord + sides[2]);
			if(!(tile instanceof TileEntityMechBay) )
			{
				return false;
			}
		}
		Block block;
		for (int i = 0; i < 4; i++) {
			for (int[] air : airBlocks) {
				block = worldObj.getBlock(this.xCoord + air[0], this.yCoord + air[1] + i+1, this.zCoord + air[2]);
				if( block != Blocks.air )
				{
					return false;
				}
			}
		}
		this.setDirection(dir);
		return true;
	}
	
	@Override
	public void setupStructure() {

		TileEntity tile;
		System.out.println(getDirection());
		int i = 0;
		for (int[] base : baseBlocks) {
			tile = worldObj.getTileEntity(this.xCoord + base[0], this.yCoord + base[1], this.zCoord + base[2]);
			i++;
			boolean master = (this.xCoord + base[0] == xCoord && this.yCoord + base[1] == yCoord && this.zCoord + base[2] == zCoord);
			if(tile != null && (tile instanceof TileEntityMechBay) )
			{
				((TileEntityMechBay) tile).setMasterCoords(xCoord, yCoord, zCoord);
				((TileEntityMechBay) tile).setHasMaster(true);
				((TileEntityMechBay) tile).setIsMaster(i == 1);
				if(((TileEntityMechBay) tile).isMaster())System.out.println("ding");
			}
			else
			{
				System.out.println((this.xCoord + base[0]) + " " + (this.yCoord + base[1]) + " " + (this.zCoord + base[2]));
			}
		}
		for (int[] back : backRotations[getDirection()])
		{
			tile = worldObj.getTileEntity(this.xCoord + back[0], this.yCoord + back[1]+1, this.zCoord + back[2]);
			if(tile != null && (tile instanceof TileEntityMechBay) ){
				((TileEntityMechBay) tile).setMasterCoords(xCoord, yCoord, zCoord);
				((TileEntityMechBay) tile).setHasMaster(true);
			}
			else
			{
				System.out.println((this.xCoord + back[0]) + " " + (this.yCoord + back[1]) + " " + (this.zCoord + back[2]));
			}
		}
		for (int[] sides : sideRotations[getDirection() / 2])
		{
			tile = worldObj.getTileEntity(this.xCoord + sides[0], this.yCoord + sides[1]+1, this.zCoord + sides[2]);
			if(tile != null && (tile instanceof TileEntityMechBay) ){
				((TileEntityMechBay) tile).setMasterCoords(xCoord, yCoord, zCoord);
				((TileEntityMechBay) tile).setHasMaster(true);
			}
			else
			{
				System.out.println((this.xCoord + sides[0]) + " " + (this.yCoord + sides[1]) + " " + (this.zCoord + sides[2]));
			}
		}
		System.out.println("done");
	}

	@Override
	public void resetStructure() {

		TileEntity tile;

		for (int[] base : baseBlocks) {
			tile = worldObj.getTileEntity(this.xCoord + base[0], this.yCoord + base[1], this.zCoord + base[2]);

			if(tile != null && !(tile instanceof TileEntityMechBay) )
			{
				((TileEntityMechBay) tile).reset();
			}
		}
		for (int[] back : backRotations[getDirection()])
		{
			tile = worldObj.getTileEntity(this.xCoord + back[0], this.yCoord + back[1], this.zCoord + back[2]);
			if(tile != null && !(tile instanceof TileEntityMechBay) ){
				((TileEntityMechBay) tile).reset();
			}
		}
		for (int[] sides : sideRotations[getDirection() / 2])
		{
			tile = worldObj.getTileEntity(this.xCoord + sides[0], this.yCoord + sides[1], this.zCoord + sides[2]);
			if(tile != null && !(tile instanceof TileEntityMechBay) ){
				((TileEntityMechBay) tile).reset();
			}
		}
	}

	@Override
	public void masterWriteToNBT(NBTTagCompound tag) {
		tag.setByte("direction", (byte)getDirection());
	}

	@Override
	public void masterReadFromNBT(NBTTagCompound tag) {
		setDirection(tag.getByte("direction"));
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

}
