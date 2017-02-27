package org.zornco.reploidcraft.init;

import net.minecraft.block.Block;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.zornco.reploidcraft.item.*;

public class RCItems {

	public static final Item rideArmorPlacer = new ItemRideArmorPlacer();
	public static final Item platformPlacer = new ItemPlatformPlacer();
	public static final Item platformRemote = new ItemPlatformRemote();
	public static final Item basicBuster = new ItemBasicBuster();
	public static final Item basicReploidArmorHelm = new ItemBasicReploidArmor(EntityEquipmentSlot.HEAD);
	public static final Item basicReploidArmorChest = new ItemBasicReploidArmor(EntityEquipmentSlot.CHEST);
	public static final Item basicReploidArmorLegs = new ItemBasicReploidArmor(EntityEquipmentSlot.LEGS);
	public static final Item basicReploidArmorFeet = new ItemBasicReploidArmor(EntityEquipmentSlot.FEET);
	public static final Item energyPellet = new ItemEnergyPelletBase();
	public static final Item basicBlade = new ItemBasicBlade();
	public static final Item rideArmorPart = new ItemRideArmorPart();
	
	public static void preInit() {
		registerItem(rideArmorPlacer, "rideArmorPlacer");
		registerItem(platformPlacer, "platformPlacer");
		registerItem(platformRemote, "platformRemote");
		registerItem(basicBuster, "basicBuster");
		registerItem(basicBlade, "basicBlade");
		registerItem(basicReploidArmorHelm, "basicReploidArmorHelm");
		registerItem(basicReploidArmorChest, "basicReploidArmorChest");
		registerItem(basicReploidArmorLegs, "basicReploidArmorLegs");
		registerItem(basicReploidArmorFeet, "basicReploidArmorFeet");
		registerItem(energyPellet, "energyPellet");
		registerItem(rideArmorPart, "rideArmorPart");
	}
	private static void registerItem(Item item, String registryName)
    {
        item.setRegistryName(registryName).setUnlocalizedName(registryName);
        GameRegistry.register(item);
    }
}
