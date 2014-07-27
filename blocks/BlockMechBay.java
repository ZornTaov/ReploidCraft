package zornco.reploidcraftenv.blocks;

import zornco.reploidcraftenv.ReploidCraftEnv;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMechBay extends Block {

	public BlockMechBay() {
		super(Material.iron);
		setCreativeTab(ReploidCraftEnv.reploidTab);
	}
	public TileEntity createTileEntity(World world, int metadata)
	{
	   return new TileEntityMechBay();
	}
}
