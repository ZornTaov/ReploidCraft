package org.zornco.reploidcraft.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.zornco.reploidcraft.item.*;

public class RCItems {

	public static final Item platformPlacer = new ItemPlatformPlacer();
	public static final Item platformRemote = new ItemPlatformRemote();
	public static final Item simpleBuster = new ItemSimpleBuster();
	public static final Item energyPellet = new ItemEnergyPelletBase();
	
	public static void preInit() {
		registerItem(platformPlacer, "platformPlacer");
		registerItem(platformRemote, "platformRemote");
		registerItem(simpleBuster, "simpleBuster");
		registerItem(energyPellet, "energyPellet");
	}
	private static void registerItem(Item item, String registryName)
    {
        item.setRegistryName(registryName).setUnlocalizedName(registryName);
        GameRegistry.register(item);
    }
}
