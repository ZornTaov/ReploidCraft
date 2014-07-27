package zornco.reploidcraftenv.blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import zornco.reploidcraftenv.ReploidCraftEnv;

import com.google.common.collect.Lists;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import static net.minecraftforge.common.util.ForgeDirection.UP;
import static net.minecraftforge.common.util.ForgeDirection.DOWN;

public class BlockItemHolder extends BlockContainer {

	private Random random;

	@SideOnly(Side.CLIENT)
	private IIcon icons;

	public BlockItemHolder()
	{
		super(Material.iron);
		//setBlockBounds(0.0625F, 0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
		random = new Random();
		setCreativeTab(ReploidCraftEnv.reploidTab);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta)
	{
		return null;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/*@Override
	public int getRenderType()
	{
		return 22;
	}*/

	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add boxes to the list if they intersect the
	 * mask.) Parameters: World, X, Y, Z, mask, list, colliding entity
	 */
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
	{
		//this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
		//super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		this.setBlockBoundsForItemRender();
		super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.063F, 0.005F, 0.063F, 0.937F, 0.01625F, 0.937F);
	}

	@Override
	public TileEntity createTileEntity(World world, int metadata)
	{
		return new TileEntityItemHolder();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int i, int j)
	{
		return null;
	}

	@Override
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune)
	{
		ArrayList<ItemStack> items = Lists.newArrayList();
		ItemStack stack = new ItemStack(this,1,metadata);
		//IronChestType.values()[IronChestType.validateMeta(metadata)].adornItemDrop(stack);
		items.add(stack);
		return items;
	}
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int i1, float f1, float f2, float f3)
	{
		TileEntity te = world.getTileEntity(i, j, k);

		if (te == null || !(te instanceof TileEntityItemHolder))
		{
			return true;
		}

		if (world.isSideSolid(i, j + 1, k, ForgeDirection.DOWN))
		{
			return true;
		}

		if (world.isRemote)
		{
			return true;
		}

		player.openGui(ReploidCraftEnv.instance, 0, world, i, j, k);
		return true;
	}
	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World world, int par2, int par3, int par4, Entity par5Entity)
	{
		if(par5Entity instanceof EntityPlayer)
		{
			TileEntityItemHolder tileentitychest = (TileEntityItemHolder) world.getTileEntity(par2, par3, par4);
			if (tileentitychest != null && !tileentitychest.isDropped())
			{
				dropContent(0, tileentitychest, world, tileentitychest.xCoord, tileentitychest.yCoord, tileentitychest.zCoord, 1);
				tileentitychest.markDirty();
			}
		}
	}
	@Override
	public void onBlockAdded(World world, int i, int j, int k)
	{
		super.onBlockAdded(world, i, j, k);
		world.markBlockForUpdate(i, j, k);
	}

	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemStack)
	{
		byte chestFacing = 0;
		int facing = MathHelper.floor_double((double) ((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		if (facing == 0)
		{
			chestFacing = 2;
		}
		if (facing == 1)
		{
			chestFacing = 5;
		}
		if (facing == 2)
		{
			chestFacing = 3;
		}
		if (facing == 3)
		{
			chestFacing = 4;
		}
		TileEntity te = world.getTileEntity(i, j, k);
		if (te != null && te instanceof TileEntityItemHolder)
		{
			TileEntityItemHolder teic = (TileEntityItemHolder) te;
			teic.setFacing(chestFacing);
			world.markBlockForUpdate(i, j, k);
		}
	}

	@Override
	public int damageDropped(int i)
	{
		return i;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, Block i1, int i2)
	{
		TileEntityItemHolder tileentitychest = (TileEntityItemHolder) world.getTileEntity(i, j, k);
		if (tileentitychest != null)
		{
			tileentitychest.removeAdornments();
			dropContent(0, tileentitychest, world, tileentitychest.xCoord, tileentitychest.yCoord, tileentitychest.zCoord, -1);
		}
		super.breakBlock(world, i, j, k, i1, i2);
	}

	public void dropContent(int newSize, IInventory chest, World world, int xCoord, int yCoord, int zCoord, int count)
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
	public int getComparatorInputOverride(World par1World, int par2, int par3, int par4, int par5)
	{
		return Container.calcRedstoneFromInventory((TileEntityItemHolder) par1World.getTileEntity(par2, par3, par4));
	}


	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{

	}

	private static final ForgeDirection[] validRotationAxes = new ForgeDirection[] { UP, DOWN };
	@Override
	public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z)
	{
		return validRotationAxes;
	}

	@Override
	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
	{
		if (worldObj.isRemote)
		{
			return false;
		}
		if (axis == UP || axis == DOWN)
		{
			TileEntity tileEntity = worldObj.getTileEntity(x, y, z);
			if (tileEntity instanceof TileEntityItemHolder) {
				TileEntityItemHolder icte = (TileEntityItemHolder) tileEntity;
				icte.rotateAround(axis);
			}
			return true;
		}
		return false;
	}
}
