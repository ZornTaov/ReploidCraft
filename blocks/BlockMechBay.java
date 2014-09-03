package zornco.reploidcraftenv.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import zornco.reploidcraftenv.ReploidCraftEnv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockMechBay extends Block implements ITileEntityProvider {

	@SideOnly(Side.CLIENT)
	protected IIcon[] blockIcons;
	public static String[] names = {"basic", "controller", "power"};
	public BlockMechBay() {
		super(Material.iron);
		setCreativeTab(ReploidCraftEnv.reploidTab);
	}
	@Override
	public int damageDropped (int metadata) {
		return metadata;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int unknown, CreativeTabs tab, List subItems) {
		for (int ix = 0; ix <  BlockMechBay.names.length; ix++) {
			subItems.add(new ItemStack(this, 1, ix));
		}
	}
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		switch (metadata) {
		case 1:
			return new TileEntityMechBayController();
		case 2:
			return new TileEntityMechBayEnergy();
		case 0:
		default:
			return new TileEntityMechBay();	
		}
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		this.setBlockBounds(0, 0, 0, 1, 1, 1);
	}
	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) 
	{
		int[][] sideOffset = {{0,1,0},{0,-1,0},{0,0,1},{0,0,-1},{1,0,0},{-1,0,0}};
		TileEntity tile = world.getTileEntity(x + sideOffset[side][0], y + sideOffset[side][1], z + sideOffset[side][2]);
		if(tile != null && tile instanceof TileEntityMechBay)
		{
			if(((TileEntityMechBay)tile).hasMaster())
			{
				return false;
			}
		}
		return true;
	}
	@Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer player, int i1, float f1, float f2, float f3)
	{
		TileEntity te = world.getTileEntity(i, j, k);

		if (te == null || !(te instanceof TileEntityMechBayController))
		{
			return false;
		}
		if (world.isRemote)
		{
			return true;
		}
		if(((TileMultiBlock) te).hasMaster())
		{
			player.openGui(ReploidCraftEnv.instance, 1, world, i, j, k);
		}
		else return false;
		return true;
	}
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	@Override
	@SideOnly(Side.CLIENT)

	/**
	 * When this method is called, your block should register all the icons it needs with the given IconRegister. This
	 * is the only chance you get to register icons.
	 */
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		this.blockIcons = new IIcon[] {par1IconRegister.registerIcon("reploidcraftenv:mechBay"), par1IconRegister.registerIcon("reploidcraftenv:mechBayController"), par1IconRegister.registerIcon("reploidcraftenv:mechBayEnergy")};
	}
	@Override
	public IIcon getIcon(int side, int metadata) {
		return blockIcons[metadata];
	}
}
