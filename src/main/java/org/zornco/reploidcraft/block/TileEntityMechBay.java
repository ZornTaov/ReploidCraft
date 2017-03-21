package org.zornco.reploidcraft.block;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.init.RCBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class TileEntityMechBay extends TileMultiBlock implements IInventory {
    private NonNullList<ItemStack> inv = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
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
	 * SECTION: Inventory
	 */
	@Override
	public int getSizeInventory() {
		return inv.size();
	}

	@Override
	public ItemStack getStackInSlot(int index) {

        return (ItemStack)this.inv.get(index);
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
		/*if (this.inv[var1] != null)
		{
			if (this.inv[var1].getCount() <= var2)
			{
				ItemStack var3 = this.inv[var1];
				this.inv[var1] = null;
				if (this.eventHandler != null) this.eventHandler.onCraftMatrixChanged(this);
				return var3;
			}

			ItemStack var3 = this.inv[var1].splitStack(var2);

			if (this.inv[var1].getCount() == 0)
			{
				this.inv[var1] = null;
			}

			if (this.eventHandler != null) this.eventHandler.onCraftMatrixChanged(this);
			return var3;
		}

		return null;*/

        return ItemStackHelper.getAndSplit(this.inv, index, count);
	}

	@Override
	public ItemStack removeStackFromSlot(int index)
    {
        return ItemStackHelper.getAndRemove(this.inv, index);
    }

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {

        ItemStack itemstack = (ItemStack)this.inv.get(index);
        boolean flag = !stack.isEmpty() && stack.isItemEqual(itemstack) && ItemStack.areItemStackTagsEqual(stack, itemstack);
        this.inv.set(index, stack);

        if (stack.getCount() > this.getInventoryStackLimit())
        {
            stack.setCount(this.getInventoryStackLimit());
        }
		if (this.eventHandler != null) this.eventHandler.onCraftMatrixChanged(this);
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public boolean hasCustomName() {
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
	public boolean isUsableByPlayer(EntityPlayer player) {
		return this.world.getTileEntity(pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
	}
	@Override
	public void openInventory(EntityPlayer player) {

		if (this.numPlayersUsing < 0)
		{
			this.numPlayersUsing = 0;
		}
		++this.numPlayersUsing;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
	}

	@Override
	public void closeInventory(EntityPlayer player) {
		--this.numPlayersUsing;
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
	}

	/*public void populateInventory(EntityRideArmor rideArmor, TileEntityMechBay bay)
	{
		if(bay.hasRide())
		{
			for (int i = 0; i < rideArmor.getParts().length; i++)
			{
				if(((EntityRideArmorPart) rideArmor.getParts()[i]).getType() != "EMPTY" && ReploidCraft.proxy.partRegistry.getPart(((EntityRideArmorPart) rideArmor.getParts()[i]).getType(), PartSlot.getSlot(i)) != null)
					bay.inv[i] = new ItemStack(ReploidCraft.rideArmorPart, 1, ReploidCraft.proxy.partRegistry.getPart(((EntityRideArmorPart) rideArmor.getParts()[i]).getType(), PartSlot.getSlot(i)).getPartNumber());
			}
		}
	}*/

	/*public static void setPartsToArmor(EntityRideArmor rideArmor, TileEntityMechBay bay)
	{
		if(bay.hasRide())
		{
			String[] s;
			for (int i = 0; i < rideArmor.getParts().length; i++) {
				if(bay.inv[i] == null) 
				{
					if(i == 1)
					{
						//going to assume that the chest has been removed
						bay.myRide.setDead();
						dropContent(0, bay.getMaster(), bay.getWorld(), bay.pos, -1);
						break;
					}
					rideArmor.setPart(PartSlot.getSlot(i), "EMPTY");
					continue;
				}
				s = ItemRideArmorPart.getPartByMetadata(bay.inv[i].getItemDamage()).split("\\.");
				if(!PartSlot.getSlot(i).equals(PartSlot.getSlot(s[1])))
				{
					dropContent(i, bay, bay.getWorld(), bay.getMasterX(), bay.getMasterY() + 1, bay.getMasterZ(), bay.inv[i].getCount());
					rideArmor.setPart(PartSlot.getSlot(i), "EMPTY");
					continue;
				}
				rideArmor.setPart(PartSlot.getSlot(s[1]), s[0]);
			}
		}
	}*/
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
			while (itemstack.getCount() > 0)
			{
				int i1 = random.nextInt(21) + 10;
				if (i1 > itemstack.getCount())
				{
					i1 = itemstack.getCount();
				}
				itemstack.shrink(i1);
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
					world.spawnEntity(entityitem);

			}
		}
	}
	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return (var1 != 10) || (var2 == null);
	}

	/*
	 * SECTION: Multiblock
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void doMultiBlockStuff() {

		++this.ticksSinceSync;
		float f;
		if (this.numPlayersUsing != 0 && (this.ticksSinceSync + this.pos.hashCode()) % 200 == 0)
		{
			this.numPlayersUsing = 0;
			f = 5.0F;
			List<EntityPlayer> list = this.world.getEntitiesWithinAABB(EntityPlayer.class, getRenderBoundingBox().expand(f, f, f));
			Iterator<EntityPlayer> iterator = list.iterator();

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
		List<EntityRideArmor> list = this.world.getEntitiesWithinAABB(EntityRideArmor.class, getRenderBoundingBox());
		if(!hasRide)
		{
			for (Entity entity : list) {
				if(entity instanceof EntityRideArmor && entity.getRidingEntity() == null)
				{
					myRide = ((EntityRideArmor)entity);
					hasRide = true;
				}
				break; //only need the one
			}

			if(inv.get(1).isEmpty() && list.isEmpty())
			{
				//spawn a new ride armor with the new chest
				myRide = new EntityRideArmor(getWorld(), this.getPos().up(1));
				myRide.rotationYaw = (float)(MathHelper.floor((double)this.rotations[direction]));//par2EntityPlayer.rotationYawHead - 180;

				if (!this.getWorld().getCollisionBoxes(myRide, myRide.getEntityBoundingBox().expand(-0.1D, -0.1D, -0.1D)).isEmpty())
				{
					return;
				}
				//myRide.setPlayerCreated(true);

				if (!this.getWorld().isRemote)
				{
					//this.getWorld().spawnEntity(myRide);
				}
				//myRide.setPart(PartSlot.BODY, ItemRideArmorPart.getPartByMetadata(inv[1].getItemDamage()).split("\\.")[0]);
				hasRide = true;
				return;
			}
		}
		else
		{
			if((list.isEmpty() || myRide == null || myRide.getRidingEntity() != null) && hasRide) 
			{
				hasRide = false;
				this.inv.clear();
				return;
			}
			myRide.setLocationAndAngles(this.pos.getX() + 0.5F, this.pos.getY() + 1F, this.pos.getZ() + 0.5F, rotations[direction], 0);

		}

	}
	@Override
	public void update() {
		if(hasMaster() && isMaster())
			if(hasRide && myRide != null){

				if(numPlayersUsing == 0)
				{
					if(this.hasRide) ;
						//this.populateInventory(myRide, this);
				}
				else
				{
					if(myRide.getRidingEntity() != null)
					{
						myRide.dismountRidingEntity();
						return;
					}
					if(inv.get(1) == null )
					{
						//going to assume that the chest has been removed
						myRide.setDead();
						hasRide = false;
						//dropContent(0, getMaster(), getWorld(), pos.up(3), -1);
						this.inv.clear();
						return;
					}
					if( this.hasRide);
						//setPartsToArmor(myRide, getMaster());
				}

			}
        if (++this.ticksSinceSync % 20 * 4 == 0)
        {
            this.world.addBlockEvent(this.pos, RCBlocks.mechBay, 1, this.numPlayersUsing);
        }
		super.update();
	
	}
	@Override
	public boolean checkMultiBlockForm() {
		int dir = -1;
		int checked = 0;
		TileEntity tile;
		for (int i = 0; i < backCheck.length; i++) {
			if (world.getTileEntity(this.pos.add(backCheck[i][0], backCheck[i][1]+1, backCheck[i][2])) instanceof TileEntityMechBay)
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
			tile = world.getTileEntity(this.pos.add(base[0], base[1], base[2]));
			if(!(tile instanceof TileEntityMechBay) )
			{
				return false;
			}
		}
		for (int[] back : backRotations[dir])
		{
			tile = world.getTileEntity(this.pos.add(back[0], back[1]+1, back[2]));
			if(!(tile instanceof TileEntityMechBay) )
			{
				return false;
			}
		}

		for (int[] sides : sideRotations[dir / 2])
		{
			tile = world.getTileEntity(this.pos.add(sides[0], sides[1]+1, sides[2]));
			if(!(tile instanceof TileEntityMechBay) )
			{
				return false;
			}
		}
		Block block;
		for (int i = 0; i < 4; i++) {
			for (int[] air : airBlocks) {
				if(!world.isAirBlock(this.pos.add(air[0], air[1]+i+1, air[2])))
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
		IBlockState state;
		BlockPos offsetPos;
		int i = 0;
		for (int[] base : baseBlocks) {
			offsetPos = this.pos.add(base[0], base[1], base[2]);
			tile = world.getTileEntity(offsetPos);
			state = world.getBlockState(offsetPos);
			i++;
			//boolean master = (this.xCoord + base[0] == xCoord && this.yCoord + base[1] == yCoord && this.zCoord + base[2] == zCoord);
			if(tile != null && (tile instanceof TileEntityMechBay) && state.getBlock() instanceof BlockMechBay)
			{
				((TileEntityMechBay) tile).setMasterCoords(pos);
				((TileEntityMechBay) tile).setHasMaster(true);
				((TileEntityMechBay) tile).setIsMaster(i == 1);
				world.setBlockState(offsetPos, state.withProperty(BlockMechBay.INVIS, true));
				tile.markDirty();
			}
			else
			{
				System.out.println(this.pos.add(base[0], base[1], base[2]));
			}
		}
		for (int[] back : backRotations[getDirection()])
		{
			offsetPos = this.pos.add(back[0], back[1]+1, back[2]);
			tile = world.getTileEntity(offsetPos);
			state = world.getBlockState(offsetPos);
			if(tile != null && (tile instanceof TileEntityMechBay) && state.getBlock() instanceof BlockMechBay){
				((TileEntityMechBay) tile).setMasterCoords(pos);
				((TileEntityMechBay) tile).setHasMaster(true);
				world.setBlockState(offsetPos, state.withProperty(BlockMechBay.INVIS, true));
				tile.markDirty();
			}
			else
			{
				//System.out.println((this.xCoord + back[0]) + " " + (this.yCoord + back[1]) + " " + (this.zCoord + back[2]));
			}
		}
		for (int[] sides : sideRotations[getDirection() / 2])
		{
			offsetPos = this.pos.add(sides[0], sides[1]+1, sides[2]);
			tile = world.getTileEntity(offsetPos);
			state = world.getBlockState(offsetPos);
			if(tile != null && (tile instanceof TileEntityMechBay) && state.getBlock() instanceof BlockMechBay){
				((TileEntityMechBay) tile).setMasterCoords(pos);
				((TileEntityMechBay) tile).setHasMaster(true);
				world.setBlockState(offsetPos, state.withProperty(BlockMechBay.INVIS, true));
				tile.markDirty();
			}
			else
			{
				//System.out.println((this.xCoord + sides[0]) + " " + (this.yCoord + sides[1]) + " " + (this.zCoord + sides[2]));
			}
		}
		ReploidCraft.logger.info(FMLCommonHandler.instance().getEffectiveSide() + " setup completed!");
	}

	@Override
	public void resetStructure() {

		TileEntity tile;
		IBlockState state;
		BlockPos offsetPos;

		for (int[] base : baseBlocks) {
			offsetPos = this.pos.add(base[0], base[1], base[2]);
			tile = world.getTileEntity(offsetPos);
			state = world.getBlockState(offsetPos);

			if(tile != null && (tile instanceof TileEntityMechBay) && state.getBlock() instanceof BlockMechBay)
			{
				((TileEntityMechBay) tile).reset();
				((TileEntityMechBay) tile).hasRide = false;
				world.setBlockState(offsetPos, state.withProperty(BlockMechBay.INVIS, false));
				tile.markDirty();
			}
		}
		for (int[] back : backRotations[getDirection()])
		{
			offsetPos = this.pos.add(back[0], back[1]+1, back[2]);
			tile = world.getTileEntity(offsetPos);
			state = world.getBlockState(offsetPos);
			
			if(tile != null && (tile instanceof TileEntityMechBay) && state.getBlock() instanceof BlockMechBay)
			{
				((TileEntityMechBay) tile).reset();
				world.setBlockState(offsetPos, state.withProperty(BlockMechBay.INVIS, false));
				tile.markDirty();
			}
		}
		for (int[] sides : sideRotations[getDirection() / 2])
		{
			offsetPos = this.pos.add(sides[0], sides[1]+1, sides[2]);
			tile = world.getTileEntity(offsetPos);
			state = world.getBlockState(offsetPos);
			
			if(tile != null && (tile instanceof TileEntityMechBay) && state.getBlock() instanceof BlockMechBay)
			{
				((TileEntityMechBay) tile).reset();
				world.setBlockState(offsetPos, state.withProperty(BlockMechBay.INVIS, false));
				tile.markDirty();
			}
		}
	}
	@Override
	public void masterWriteToNBT(NBTTagCompound compound) {
		compound.setByte("direction", (byte)getDirection());
		compound.setBoolean("hasMech", hasRide);
		if(hasRide)
		{
			if(myRide != null)
				compound.setInteger("MechID", myRide.getEntityId());

	        ItemStackHelper.saveAllItems(compound, inv);
		}
	}

	@Override
	public void masterReadFromNBT(NBTTagCompound compound) {
		setDirection(compound.getByte("direction"));
		this.hasRide = compound.getBoolean("hasMech");
		if(hasRide && this.world != null)
		{
			Entity ent = this.world.getEntityByID(compound.getInteger("MechID"));
			if(ent != null) myRide = (EntityRideArmor) ent; 	

	        ItemStackHelper.loadAllItems(compound, inv);
		}
	}
	public TileEntityMechBay getMaster()
	{
		return (TileEntityMechBay) this.world.getTileEntity(new BlockPos(this.getMasterX(), this.getMasterY(), this.getMasterZ()));
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
	 * SECTION: Mech Bay 
	 */
	@Override
	public AxisAlignedBB getRenderBoundingBox() 
	{
		return new AxisAlignedBB(
				pos.getX()-2 + sideOffset[direction][0][0], 
				pos.getY(), 
				pos.getZ()-2 + sideOffset[direction][0][1], 
				pos.getX()+3 + sideOffset[direction][1][0], 
				pos.getY()+4, 
				pos.getZ()+3 + sideOffset[direction][1][1]);
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


	@Override
	public boolean isEmpty() {

        for (ItemStack itemstack : this.inv)
        {
            if (!itemstack.isEmpty())
            {
                return false;
            }
        }

        return true;
	}

	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getFieldCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		this.inv.clear();
	}

}
