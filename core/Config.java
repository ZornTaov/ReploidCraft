package zornco.reploidcraftenv.core;

import net.minecraft.block.StepSound;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.BlockSpikes;
import zornco.reploidcraftenv.bullets.EntityMetBullet;
import zornco.reploidcraftenv.crafting.RecipeHandler;
import zornco.reploidcraftenv.entities.EntityFloatingPlatform;
import zornco.reploidcraftenv.entities.EntityMet;
import zornco.reploidcraftenv.items.ItemComponent;
import zornco.reploidcraftenv.items.ItemHPEnergy;
import zornco.reploidcraftenv.items.ItemPlatformPlacer;
import zornco.reploidcraftenv.items.ItemReploidPlate;
import zornco.reploidcraftenv.items.ItemTank;
import zornco.reploidcraftenv.lib.Reference;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class Config {
	//ID's
	public static int spikesRI = -1;
	public static int bossDoorRI = -1;

	private int healthBitID;
	private int healthByteID;
	//private int weaponBitID;
	//private int weaponByteID;
	private int healthTankID;
	//private int weaponTankID;
	//private int extraManID;
	private int reploidPlateID;
	private int componentID;

	private int platformPlacerID;
	private int doorBossItemID;

	private int spikesID;
	private int doorBossBlockID;

	public static RecipeHandler recipes = new RecipeHandler();

	public void loadConfig(FMLPreInitializationEvent event) {
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		//Items
		int itemID = 22100;
		healthBitID = config.getItem(config.CATEGORY_ITEM,"Health Bit", itemID++).getInt();
		healthByteID = config.getItem(config.CATEGORY_ITEM,"Health Byte", itemID++).getInt();
		//weaponBitID = config.getItem(config.CATEGORY_ITEM,"Weapon Bit", itemID++).getInt();
		//weaponByteID = config.getItem(config.CATEGORY_ITEM,"Weapon Byte", itemID++).getInt();
		healthTankID = config.getItem(config.CATEGORY_ITEM,"Health Tank", itemID++).getInt();
		//weaponTankID = config.getItem(config.CATEGORY_ITEM,"Weapon Tank", itemID++).getInt();
		//extraManID = config.getItem(config.CATEGORY_ITEM,"Extra Man", itemID++).getInt();
		reploidPlateID = config.getItem(config.CATEGORY_ITEM,"Reploid Plates", itemID++).getInt();
		componentID = config.getItem(config.CATEGORY_ITEM,"Components", itemID++).getInt();
		doorBossItemID = config.getItem(config.CATEGORY_ITEM,"Boss Door Item", itemID++).getInt();
		platformPlacerID = config.getItem(config.CATEGORY_ITEM,"Platform Placer", itemID++).getInt();

		//Blocks
		int blockID = 2500;
		spikesID = config.get(config.CATEGORY_BLOCK,"Metal Spikes", blockID++).getInt();
		doorBossBlockID = config.get(config.CATEGORY_BLOCK,"Boss Door Block", blockID++).getInt();

		config.save();
	}
	

	public void addNames() {
		/** Names **/
		LanguageRegistry.addName(ReploidCraftEnv.healthBit, "Health Bit");
		LanguageRegistry.addName(ReploidCraftEnv.healthByte, "Health Byte");
		//LanguageRegistry.addName(Reploid.weaponBit, "Weapon Bit");
		//LanguageRegistry.addName(Reploid.weaponByte, "Weapon Byte");
		LanguageRegistry.addName(ReploidCraftEnv.healthTank, "Health Tank");
		//LanguageRegistry.addName(Reploid.weaponTank, "Weapon Tank");
		//LanguageRegistry.addName(Reploid.extraMan, "Extra Man");
		for(String a : ItemReploidPlate.plateColorNames)
		{
			LanguageRegistry.instance().addStringLocalization("item.reploidPlate."+a+".name", "en_US", a+" Reploid Plate");
		}
		for(String a : ItemComponent.componentNames)
		{
			LanguageRegistry.instance().addStringLocalization("item.component."+a+".name", "en_US", a );
		}

		LanguageRegistry.addName(ReploidCraftEnv.platformPlacer, "Platform Placer");
		//LanguageRegistry.addName(Reploid.doorBossItem, "Boss Door");

		//LanguageRegistry.addName(Reploid.upgradeStation, "Upgrade Station");
		LanguageRegistry.addName(ReploidCraftEnv.spikes, "Metal Spikes");
		//LanguageRegistry.addName(Reploid.doorBossBlock, "Boss Door");

		LanguageRegistry.instance().addStringLocalization("entity.FloatingPlatform.name", "en_US", "Floating Platform");
		LanguageRegistry.instance().addStringLocalization("itemGroup." + Reference.MOD_ID, "en_US", "Reploid Enviroment");
	}

	public void registerEntities() {
		/** Registers **/
		
		EntityRegistry.registerGlobalEntityID(EntityMet.class, "Met", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFF00, 0x0F0000);
		EntityRegistry.registerModEntity(EntityMet.class, "Met", 2, ReploidCraftEnv.instance, 80, 3, true);
		EntityRegistry.addSpawn("Met", 3, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach);
		EntityRegistry.registerGlobalEntityID(EntityFloatingPlatform.class, "FloatingPlatform", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFloatingPlatform.class, "FloatingPlatform", 3, ReploidCraftEnv.instance, 80, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityMetBullet.class, "metBullet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityMetBullet.class, "metBullet", 4, ReploidCraftEnv.instance, 250, 1, true);
	}

	public void addBlocks() {
		/** Blocks **/
		ReploidCraftEnv.spikes = new BlockSpikes(spikesID).setStepSound(new StepSound("stone", 1.0F, 1.5F)).setUnlocalizedName("spikes").setHardness(3.5F);
		//doorBossBlock = (new BlockBossDoor(doorBossBlockID, Material.iron)).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("doorBoss").setRequiresSelfNotify();

		GameRegistry.registerBlock(ReploidCraftEnv.spikes, "spikes");
		//GameRegistry.registerBlock(doorBossBlock, "doorBoss");
	}
	public void addItems() {
		/** Items **/

		ReploidCraftEnv.healthBit = new ItemHPEnergy(healthBitID, 0, 3, 0.5F).setPotionEffect(Potion.heal.id, 1, 0, 1.0F).setAlwaysEdible().setUnlocalizedName("healthBit").setMaxStackSize(1);
		ReploidCraftEnv.healthByte = new ItemHPEnergy(healthByteID, 1, 8, 0.8F).setPotionEffect(Potion.heal.id, 1, 1, 1.0F).setAlwaysEdible().setUnlocalizedName("healthByte").setMaxStackSize(1);
		//weaponBit = new ItemWPEnergy(weaponBitID).setItemName("Weapon Bit").setMaxStackSize(1);
		//weaponByte = new ItemWPEnergy(weaponByteID).setItemName("Weapon Byte").setMaxStackSize(1);
		ReploidCraftEnv.healthTank = new ItemTank(healthTankID).setUnlocalizedName("Health Tank").setMaxStackSize(1); //use EventBus to add to this, then use some other method to heal the player per heart
		//weaponTank = new ItemTank(weaponTankID).setItemName("Weapon Tank").setMaxStackSize(1);
		//extraMan = new ItemLife(extraManID).setItemName("Extra Man").setMaxStackSize(9); //possibly use ticker to instantly heal player?

		ReploidCraftEnv.reploidPlate = new ItemReploidPlate(reploidPlateID).setUnlocalizedName("reploidPlate");
		ReploidCraftEnv.component = new ItemComponent(componentID).setUnlocalizedName("component");

		ReploidCraftEnv.platformPlacer = new ItemPlatformPlacer(platformPlacerID).setUnlocalizedName("platformPlacer");
		//doorBossItem = (new ItemBossDoor(doorBossItemID, Material.iron)).setIconCoord(12, 2).setItemName("doorBoss");
	}
}
