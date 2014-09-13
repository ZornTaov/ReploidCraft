package zornco.reploidcraftenv.blocks;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.EntityRideArmorPart;
import zornco.reploidcraftenv.entities.armorParts.PartSlot;
import zornco.reploidcraftenv.items.ItemRideArmorPart;
import cofh.api.energy.EnergyStorage;

public class TileEntityMechBay extends TileMultiBlock implements IInventory {
	public ItemStack[] inv = new ItemStack[6];
	public int numPlayersUsing = 0;
	Container eventHandler;
	protected EnergyStorage storage = new EnergyStorage(320000);
	private int direction = 0;
	private int[][][] sideOffset = {
			{{0,-1}, {0,0}}, 
			{{0,0}, {0,1}}, 
			{{-1,0}, {0,0}},
			{{0,0}, {1,0}}

	};
	private int[] rotations = {0, 180, -90, 90};
	private EntityRideArmor myRide;
	private boolean hasRide = false;
	private int ticksSinceSync;
	private static Random random = new Random();
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
			{ -2, 0, -2 }, { -1, 0, -2 }, { 0, 0, -2 }, { 1, 0, -2 }, { 2, 0, -2 }, 
			{ -2, 1, -2 }, { -1, 1, -2 }, { 0, 1, -2 }, { 1, 1, -2 }, { 2, 1, -2 }, 
			{ -2, 2, -2 }, { -1, 2, -2 }, { 0, 2, -2 }, { 1, 2, -2 }, { 2, 2, -2 }
		},
		//South
		{
			{ -2, 0, 2 }, { -1, 0, 2 }, { 0, 0, 2 }, { 1, 0, 2 }, { 2, 0, 2 }, 
			{ -2, 1, 2 }, { -1, 1, 2 }, { 0, 1, 2 }, { 1, 1, 2 }, { 2, 1, 2 }, 
			{ -2, 2, 2 }, { -1, 2, 2 }, { 0, 2, 2 }, { 1, 2, 2 }, { 2, 2, 2 }
		},
		//West
		{
			{ -2, 0, -2 }, { -2, 0, -1 }, { -2, 0, 0 }, { -2, 0, 1 }, { -2, 0, 2 }, 
			{ -2, 1, -2 }, { -2, 1, -1 }, { -2, 1, 0 }, { -2, 1, 1 }, { -2, 1, 2 }, 
			{ -2, 2, -2 }, { -2, 2, -1 }, { -2, 2, 0 }, { -2, 2, 1 }, { -2, 2, 2 }
		},
		//East
		{
			{ 2, 0, -2 }, { 2, 0, -1 }, { 2, 0, 0 }, { 2, 0, 1 }, { 2, 0, 2 }, 
			{ 2, 1, -2 }, { 2, 1, -1 }, { 2, 1, 0 }, { 2, 1, 1 }, { 2, 1, 2 }, 
			{ 2, 2, -2 }, { 2, 2, -1 }, { 2, 2, 0 }, { 2, 2, 1 }, { 2, 2, 2 }
		}
	};
	private static final int[][] backCheck = new int [][] {
		{ 0, 2, -2 }, { 0, 2, 2 }, { -2, 2, 0 }, { 2, 2, 0 }
	};
	/*
	 * Inventory
	 */
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
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}
	public World getWorld()
	{
		return this.worldObj;
	}
	@Override
	public void openInventory() {

		if (this.numPlayersUsing < 0)
		{
			this.numPlayersUsing = 0;
		}
		++this.numPlayersUsing;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
	}

	@Override
	public void closeInventory() {
		--this.numPlayersUsing;
		this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, this.getBlockType(), 1, this.numPlayersUsing);
	}

	public void populateInventory(EntityRideArmor rideArmor, TileEntityMechBay bay)
	{
		if(bay.hasRide())
		{
			for (int i = 0; i < rideArmor.getParts().length; i++)
			{
				if(((EntityRideArmorPart) rideArmor.getParts()[i]).getType() != "EMPTY")
					bay.inv[i] = new ItemStack(ReploidCraftEnv.rideArmorPart, 1, ReploidCraftEnv.proxy.partRegistry.getPart(((EntityRideArmorPart) rideArmor.getParts()[i]).getType(), PartSlot.getSlot(i)).getPartNumber());
			}
		}
	}

	public static void setPartsToArmor(EntityRideArmor rideArmor, TileEntityMechBay bay)
	{
		if(bay.hasRide())
		{
			String[] s;
			for (int i = 0; i < rideArmor.getParts().length; i++) {
				if(bay.inv[i] == null) 
				{
					rideArmor.setPart(PartSlot.getSlot(i), "EMPTY");
					continue;
				}
				s = ItemRideArmorPart.getPartByMetadata(bay.inv[i].getItemDamage()).split("\\.");
				if(!PartSlot.getSlot(i).equals(PartSlot.getSlot(s[1])))
				{
					dropContent(i, bay, bay.getWorldObj(), bay.getMasterX(), bay.getMasterY() + 1, bay.zCoord, bay.inv[i].stackSize);
					rideArmor.setPart(PartSlot.getSlot(i), "EMPTY");
					continue;
				}
				rideArmor.setPart(PartSlot.getSlot(s[1]), s[0]);
			}
		}
	}
	public static void dropContent(int newSize, IInventory chest, World world, int xCoord, int yCoord, int zCoord, int count)
	{
		for (int l = newSize; l < chest.getSizeInventory(); l++)
		{
			ItemStack itemstack = count != -1? chest.decrStackSize(l, 1) : chest.getStackInSlot(l);
			if (itemstack == null)
			{
				continue;
			}
			float f = random.nextFloat() * 0.8F + 0.1F;
			float f1 = random.nextFloat() * 0.8F + 0.1F;
			float f2 = random.nextFloat() * 0.8F + 0.1F;
			while (itemstack.stackSize > 0)
			{
				int i1 = random.nextInt(21) + 10;
				if (i1 > itemstack.stackSize)
				{
					i1 = itemstack.stackSize;
				}
				itemstack.stackSize -= i1;
				EntityItem entityitem = new EntityItem(world, (float) xCoord + f, (float) yCoord + (newSize > 0 ? 1 : 0) + f1, (float) zCoord + f2,
						new ItemStack(itemstack.getItem(), i1, itemstack.getItemDamage()));
				float f3 = 0.05F;
				entityitem.motionX = (float) random.nextGaussian() * f3;
				entityitem.motionY = (float) random.nextGaussian() * f3 + 0.2F;
				entityitem.motionZ = (float) random.nextGaussian() * f3;
				if (itemstack.hasTagCompound())
				{
					entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
				}

				if(!world.isRemote)
					world.spawnEntityInWorld(entityitem);
			}
		}
	}
	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return (var1 != 10) || (var2 == null);
	}

	/*
	 * Multiblock
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doMultiBlockStuff() {

		++this.ticksSinceSync;
		float f;
		if (this.numPlayersUsing != 0 && (this.ticksSinceSync + this.xCoord + this.yCoord + this.zCoord) % 200 == 0)
		{
			this.numPlayersUsing = 0;
			f = 5.0F;
			List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, getRenderBoundingBox().expand(f, f, f));
			Iterator<Entity> iterator = list.iterator();

			while (iterator.hasNext())
			{
				EntityPlayer entityplayer = (EntityPlayer)iterator.next();

				if (entityplayer.openContainer instanceof ContainerMechBay)
				{
					IInventory iinventory = ((ContainerMechBay)entityplayer.openContainer).getMechBay();

					if (iinventory == this)
					{
						++this.numPlayersUsing;
					}
				}
			}
		}
		List<Entity> list = this.worldObj.getEntitiesWithinAABB(EntityRideArmor.class, getRenderBoundingBox());
		if(!hasRide)
		{

			for (Entity entity : list) {
				if(entity instanceof EntityRideArmor && entity.riddenByEntity == null)
				{
					myRide = ((EntityRideArmor)entity);
					hasRide = true;
				}
				break; //only need the one
			}
		}
		else
		{
			if((list.isEmpty() || myRide == null || myRide.riddenByEntity != null) && hasRide) 
			{
				hasRide = false;
				this.inv = new ItemStack[11];
				return;
			}
			myRide.setLocationAndAngles(this.xCoord + 0.5F, this.yCoord + 1F, this.zCoord + 0.5F, rotations[direction], 0);
			if(numPlayersUsing == 0)
			{
				if(this.hasMaster() && this.isMaster() && this.hasRide)
					this.populateInventory(myRide, this);
			}
			else
			{
				if(this.hasMaster() && this.isMaster() && this.hasRide)
					setPartsToArmor(myRide, getMaster());
			}
		}

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
		int i = 0;
		for (int[] base : baseBlocks) {
			tile = worldObj.getTileEntity(this.xCoord + base[0], this.yCoord + base[1], this.zCoord + base[2]);
			i++;
			//boolean master = (this.xCoord + base[0] == xCoord && this.yCoord + base[1] == yCoord && this.zCoord + base[2] == zCoord);
			if(tile != null && (tile instanceof TileEntityMechBay) )
			{
				((TileEntityMechBay) tile).setMasterCoords(xCoord, yCoord, zCoord);
				((TileEntityMechBay) tile).setHasMaster(true);
				((TileEntityMechBay) tile).setIsMaster(i == 1);
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
	}

	@Override
	public void resetStructure() {

		TileEntity tile;

		for (int[] base : baseBlocks) {
			tile = worldObj.getTileEntity(this.xCoord + base[0], this.yCoord + base[1], this.zCoord + base[2]);

			if(tile != null && (tile instanceof TileEntityMechBay) )
			{
				((TileEntityMechBay) tile).reset();
				((TileEntityMechBay) tile).hasRide = false;
			}
		}
		for (int[] back : backRotations[getDirection()])
		{
			tile = worldObj.getTileEntity(this.xCoord + back[0], this.yCoord + back[1]+1, this.zCoord + back[2]);
			if(tile != null && (tile instanceof TileEntityMechBay) ){
				((TileEntityMechBay) tile).reset();
			}
		}
		for (int[] sides : sideRotations[getDirection() / 2])
		{
			tile = worldObj.getTileEntity(this.xCoord + sides[0], this.yCoord + sides[1]+1, this.zCoord + sides[2]);
			if(tile != null && (tile instanceof TileEntityMechBay) ){
				((TileEntityMechBay) tile).reset();
			}
		}
	}
	@Override
	public void masterWriteToNBT(NBTTagCompound tag) {
		tag.setByte("direction", (byte)getDirection());
		tag.setBoolean("hasMech", hasRide);
		if(hasRide)
		{
			tag.setInteger("MechID", myRide.getEntityId());
			NBTTagList list = new NBTTagList();
			for (int i = 0; i < inv.length; i++) {
				if(this.inv[i] == null)
					continue;
				NBTTagCompound compound = new NBTTagCompound();
				compound.setByte("Slot", (byte)i);
				this.inv[i].writeToNBT(compound);
				list.appendTag(compound);
			}

			tag.setTag("Inventory", list);
		}
	}

	@Override
	public void masterReadFromNBT(NBTTagCompound compound) {
		setDirection(compound.getByte("direction"));
		this.hasRide = compound.getBoolean("hasMech");
		if(hasRide && this.worldObj != null)
		{
			Entity ent = this.worldObj.getEntityByID(compound.getInteger("MechID"));
			if(ent != null) myRide = (EntityRideArmor) ent; 	
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
	public TileEntityMechBay getMaster()
	{
		return (TileEntityMechBay) this.worldObj.getTileEntity(this.getMasterX(), this.getMasterY(), this.getMasterZ());
	}
	@Override
	public boolean receiveClientEvent(int p_145842_1_, int p_145842_2_) {
		if (p_145842_1_ == 1)
		{
			this.numPlayersUsing = p_145842_2_;
			return true;
		}
		else
		{
			return super.receiveClientEvent(p_145842_1_, p_145842_2_);
		}
	}
	/*
	 * Mech Bay 
	 */
	 @Override
	 public AxisAlignedBB getRenderBoundingBox() 
	 {
		 return AxisAlignedBB.getBoundingBox(
				 xCoord-2 + sideOffset[direction][0][0], 
				 yCoord, 
				 zCoord-2 + sideOffset[direction][0][1], 
				 xCoord+3 + sideOffset[direction][1][0], 
				 yCoord+4, 
				 zCoord+3 + sideOffset[direction][1][1]);
	 }
	 public int getDirection() {
		 return direction;
	 }

	 public void setDirection(int direction) {
		 this.direction = direction;
	 }

	 public EntityRideArmor getMyRide() {
		 return myRide;
	 }

	 public boolean hasRide() {
		 return hasRide;
	 }

}
