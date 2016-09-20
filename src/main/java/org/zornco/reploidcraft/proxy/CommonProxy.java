package org.zornco.reploidcraft.proxy;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;

import net.minecraftforge.fml.common.registry.EntityRegistry;

public class CommonProxy {

	public void registerEntities()
	{
        int id = 0;
        EntityRegistry.registerModEntity(EntityFloatingPlatform.class, "floatingplatform", id++, ReploidCraft.instance, 64, 3, true);
	}
	public void registerRenderersPre() {}
	public void registerRenderersInit() {}
	public void registerHandlers() {}
	public void registerArmors() {}

}
