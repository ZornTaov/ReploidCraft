package zornco.reploidcraft.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockPowerSupply extends Block {

	protected BlockPowerSupply() {
		super(Material.iron);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TilePowerSupply();
	}
}
