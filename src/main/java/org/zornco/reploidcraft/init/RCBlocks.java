package org.zornco.reploidcraft.init;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.block.BlockMechBay;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RCBlocks {
	public static BlockMechBay mechBay;
	public static void preInit() {
		// TODO Auto-generated method stub
		mechBay = new BlockMechBay();
	}
}
