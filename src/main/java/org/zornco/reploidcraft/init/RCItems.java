package org.zornco.reploidcraft.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.zornco.reploidcraft.item.*;

public class RCItems {

	public static final Item platformPlacer = new ItemPlatformPlacer();
	
	public static void preInit() {
		registerItem(platformPlacer, "platformPlacer");
	}
	private static void registerItem(Item item, String registryName)
    {
        item.setRegistryName(registryName);
        GameRegistry.register(item);
    }
}
