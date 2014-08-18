package zornco.reploidcraftenv.blocks;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.core.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSpikes extends Block {

	public BlockSpikes() {
		super(Material.iron);
		this.setCreativeTab(ReploidCraftEnv.reploidTab);
		//this.blockIndexInTexture = 22;
	}

	/**
	 * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
	 */
	/*public boolean canPlaceBlockOnSide(World par1World, int x, int y, int z, int par5)
	{
		ForgeDirection dir = ForgeDirection.getOrientation(par5);
		return (dir == DOWN  && (par1World.isBlockSolidOnSide(x, y + 1, z, DOWN ) || par1World.getBlockMaterial(x, y + 1, z) == Material.piston)) ||
				(dir == UP    && (par1World.isBlockSolidOnSide(x, y - 1, z, UP   ) || par1World.getBlockMaterial(x, y - 1, z) == Material.piston)) ||
				(dir == NORTH && (par1World.isBlockSolidOnSide(x, y, z + 1, NORTH) || par1World.getBlockMaterial(x, y, z + 1) == Material.piston)) ||
				(dir == SOUTH && (par1World.isBlockSolidOnSide(x, y, z - 1, SOUTH) || par1World.getBlockMaterial(x, y, z - 1) == Material.piston)) ||
				(dir == WEST  && (par1World.isBlockSolidOnSide(x + 1, y, z, WEST ) || par1World.getBlockMaterial(x + 1, y, z) == Material.piston)) ||
				(dir == EAST  && (par1World.isBlockSolidOnSide(x - 1, y, z, EAST ) || par1World.getBlockMaterial(x - 1, y, z) == Material.piston));
	}*/
	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	/*public boolean canPlaceBlockAt(World par1World, int x, int y, int z)
	{
		return (par1World.isBlockSolidOnSide(x - 1, y, z, EAST ) || par1World.getBlockMaterial(x - 1, y, z) == Material.piston) ||
				(par1World.isBlockSolidOnSide(x + 1, y, z, WEST ) || par1World.getBlockMaterial(x + 1, y, z) == Material.piston) ||
				(par1World.isBlockSolidOnSide(x, y, z - 1, SOUTH) || par1World.getBlockMaterial(x, y, z - 1) == Material.piston) ||
				(par1World.isBlockSolidOnSide(x, y, z + 1, NORTH) || par1World.getBlockMaterial(x, y, z + 1) == Material.piston) ||
				(par1World.isBlockSolidOnSide(x, y - 1, z, UP   ) || par1World.getBlockMaterial(x, y - 1, z) == Material.piston) ||
				(par1World.isBlockSolidOnSide(x, y + 1, z, DOWN ) || par1World.getBlockMaterial(x, y + 1, z) == Material.piston);
	}*/

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
	 * their own) Args: x, y, z, neighbor blockID
	 */
	public void onNeighborBlockChange(World par1World, int x, int y, int z, int par5)
	{

		/*int valid = 0;

		if (par1World.isSideSolid(x - 1, y, z, EAST) || par1World.getBlock(x - 1, y, z).getMaterial() == Material.piston)
		{
			valid++;
		}

		if (par1World.isSideSolid(x + 1, y, z, WEST) || par1World.getBlock(x + 1, y, z).getMaterial() == Material.piston)
		{
			valid++;
		}

		if (par1World.isSideSolid(x, y, z - 1, SOUTH) || par1World.getBlock(x, y, z - 1).getMaterial() == Material.piston)
		{
			valid++;
		}

		if (par1World.isSideSolid(x, y, z + 1, NORTH) || par1World.getBlock(x, y, z + 1).getMaterial() == Material.piston)
		{
			valid++;
		}

		if (par1World.isSideSolid(x, y - 1, z, UP) || par1World.getBlock(x, y - 1, z).getMaterial() == Material.piston)
		{
			valid++;
		}

		if (par1World.isSideSolid(x, y + 1, z, DOWN) || par1World.getBlock(x, y + 1, z).getMaterial() == Material.piston)
		{
			valid++;
		}*/

		/*if (valid == 0)
		{
			this.dropBlockAsItem(par1World, x, y, z, par1World.getBlockMetadata(x, y, z), 0);
			par1World.setBlockToAir(x, y, z);
		}*/

	}
	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
	 * cleared to be reused)
	 */
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		/*int var1 = 0;
		boolean var3 = (canConnectSpikesTo(world, x - 1, y, z));
		boolean var4 = (canConnectSpikesTo(world, x + 1, y, z));
		boolean var5 = (canConnectSpikesTo(world, x, y - 1, z));
		boolean var6 = (canConnectSpikesTo(world, x, y + 1, z));
		boolean var7 = (canConnectSpikesTo(world, x, y, z - 1));
		boolean var8 = (canConnectSpikesTo(world, x, y, z + 1));

		float var9 = 0F; 
		float var10 = 1F;
		float var11 = 0F;
		float var12 = 1F;
		float var13 = 0F;
		float var14 = 1F;

		if (var3) var1++;
		if (var4) var1++;
		if (var5) var1++;
		if (var6) var1++;
		if (var7) var1++;
		if (var8) var1++;

		if (var3) var10 = 0.5F; 
		if (var4) var9 = 0.5F;
		if (var5) var12 = 0.5F;
		if (var6) var11 = 0.5F;
		if (var7) var14 = 0.5F;
		if (var8) var13 = 0.5F;
		if (var1 > 1)
		{
			var9 = 0F; 
			var10 = 1F;
			var11 = 0F;
			var12 = 1F;
			var13 = 0F;
			var14 = 1F;
		}*/
		//return AxisAlignedBB.getAABBPool().getAABB((double)((float)x + var9), (double)((float)y + var11), (double)((float)z + var13),(double)((float)x + var10), (double)((float)y + var12), (double)((float)z + var14));
	    float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox(x + f, y + f, z + f, x + 1 - f, y + 1 - f, z + 1 - f);
    
	}@SideOnly(Side.CLIENT)

    /**
     * Returns the bounding box of the wired rectangular prism to render.
     */
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox(par2 + f, par3 + f, par4 + f, par2 + 1 - f, par3 + 1 - f, par4 + 1 - f);
    }
	/*public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		int var1 = 0;
		boolean var3 = (canConnectSpikesTo(world, x - 1, y, z));
		boolean var4 = (canConnectSpikesTo(world, x + 1, y, z));
		boolean var5 = (canConnectSpikesTo(world, x, y - 1, z));
		boolean var6 = (canConnectSpikesTo(world, x, y + 1, z));
		boolean var7 = (canConnectSpikesTo(world, x, y, z - 1));
		boolean var8 = (canConnectSpikesTo(world, x, y, z + 1));

		float var9 = 0F; 
		float var10 = 1F;
		float var11 = 0F;
		float var12 = 1F;
		float var13 = 0F;
		float var14 = 1F;

		if (var3) var1++;
		if (var4) var1++;
		if (var5) var1++;
		if (var6) var1++;
		if (var7) var1++;
		if (var8) var1++;

		if (var3) var10 = 0.5F; 
		if (var4) var9 = 0.5F;
		if (var5) var12 = 0.5F;
		if (var6) var11 = 0.5F;
		if (var7) var14 = 0.5F;
		if (var8) var13 = 0.5F;
		if (var1 > 1)
		{
			var9 = 0F; 
			var10 = 1F;
			var11 = 0F;
			var12 = 1F;
			var13 = 0F;
			var14 = 1F;
		}
		float f = 0.0625F;
		this.setBlockBounds(f, f, f, 1 - f, 1 - f, 1 - f);

	}*/

	/**
	 * Return whether an adjacent block can connect to a wall.
	 */
	public boolean canConnectSpikesTo(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
	{
		Block var5 = par1IBlockAccess.getBlock(par2, par3, par4);

		if (var5 != this)
		{
			Block var6 = var5;
			return var6 != null && var6.getMaterial().isOpaque() && (var6.renderAsNormalBlock() || ( var6.getMaterial() == Material.piston && var6 != Blocks.piston_extension)) ? var6.getMaterial() != Material.plants : false;
		}
		else
		{
			return false;
		}
	}

	/**
	 * Triggered whenever an entity collides with this block (enters into the block). Args: world, x, y, z, entity
	 */
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
	{
		par5Entity.attackEntityFrom(DamageSource.cactus, 6);
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
	 * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
	 */
	public boolean isOpaqueCube()
	{
		return false;
	}

    @SideOnly(Side.CLIENT)

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public IIcon getBlockTextureFromSideAndMetadata(int par1, int par2)
    {
        return this.blockIcon;
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerBlockIcons(IIconRegister par1IconRegister)
    {
        this.blockIcon = Blocks.iron_block.getBlockTextureFromSide(0);
    }

	/**
	 * The type of render function that is called for this block
	 */
	public int getRenderType()
	{
		return Config.spikesRI;
	}
	@SideOnly(Side.CLIENT)

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given
	 * coordinates.  Args: blockAccess, x, y, z, side
	 */
	public boolean shouldSideBeRendered(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}

}
