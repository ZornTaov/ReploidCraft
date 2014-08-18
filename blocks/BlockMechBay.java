package zornco.reploidcraftenv.blocks;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import zornco.reploidcraftenv.ReploidCraftEnv;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMechBay extends Block implements ITileEntityProvider {

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
			
		default:
			return new TileEntityMechBay();	
		}
	}
	@Override
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_,
			int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_) {
		// TODO Auto-generated method stub
		return super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_,
				p_149646_4_, p_149646_5_);
	}
}
