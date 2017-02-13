package org.zornco.reploidcraft.proxy;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.block.BlockMechBay;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.init.RCBlocks;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

	public void registerEntities()
	{
        int id = 0;
        EntityRegistry.registerModEntity(new ResourceLocation(ReploidCraft.MODID, "floatingplatform"), EntityFloatingPlatform.class, "floatingplatform", id++, ReploidCraft.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ReploidCraft.MODID, "ridearmor"), EntityRideArmor.class, "ridearmor", id++, ReploidCraft.instance, 64, 3, true);
	}
	public void registerRenderersPre() {}
	public void registerRenderersInit() {}
	public void registerHandlers() {}
	public void registerArmors() {}
	public void init() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			GameRegistry.registerTileEntity(RCBlocks.mechBay.createNewTileEntity(null, i).getClass(), RCBlocks.mechBay.getRegistryName().toString()+"."+BlockMechBay.EnumType.byMetadata(i));
		}
	}

}
