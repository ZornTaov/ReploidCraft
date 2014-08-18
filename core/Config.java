package zornco.reploidcraftenv.core;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.BlockItemHolder;
import zornco.reploidcraftenv.blocks.BlockMechBay;
import zornco.reploidcraftenv.blocks.BlockSpikes;
import zornco.reploidcraftenv.blocks.TileEntityItemHolder;
import zornco.reploidcraftenv.blocks.TileEntityMechBay;
import zornco.reploidcraftenv.blocks.TileEntityMechBayController;
import zornco.reploidcraftenv.blocks.TileEntityMechBayEnergy;
import zornco.reploidcraftenv.bullets.EntityMetBullet;
import zornco.reploidcraftenv.crafting.RecipeHandler;
import zornco.reploidcraftenv.entities.EntityFloatingPlatform;
import zornco.reploidcraftenv.entities.EntityMet;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.items.ItemComponent;
import zornco.reploidcraftenv.items.ItemDebugger;
import zornco.reploidcraftenv.items.ItemHPEnergy;
import zornco.reploidcraftenv.items.ItemMechBay;
import zornco.reploidcraftenv.items.ItemPlatformPlacer;
import zornco.reploidcraftenv.items.ItemReploidPlate;
import zornco.reploidcraftenv.items.ItemRideArmorPlacer;
import zornco.reploidcraftenv.items.ItemTank;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Config {
	//ID's
	public static int spikesRI = -1;
	public static int bossDoorRI = -1;

	public static RecipeHandler recipes = new RecipeHandler();

	public void registerEntities() {
		/** Registers **/
		
		EntityRegistry.registerGlobalEntityID(EntityMet.class, "Met", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFF00, 0x0F0000);
		EntityRegistry.registerModEntity(EntityMet.class, "Met", 2, ReploidCraftEnv.instance, 80, 3, true);
		EntityRegistry.addSpawn("Met", 30, 2, 40, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.swampland);
		EntityRegistry.registerGlobalEntityID(EntityFloatingPlatform.class, "FloatingPlatform", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFloatingPlatform.class, "FloatingPlatform", 3, ReploidCraftEnv.instance, 80, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityMetBullet.class, "metBullet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityMetBullet.class, "metBullet", 4, ReploidCraftEnv.instance, 250, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityRideArmor.class, "rideArmor", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityRideArmor.class, "rideArmor", 5, ReploidCraftEnv.instance, 250, 1, true);
	}

	public void addBlocks() {
		/** Blocks **/
		ReploidCraftEnv.spikes = new BlockSpikes().setStepSound(Block.soundTypeStone).setBlockName("spikes").setHardness(3.5F);
		//doorBossBlock = (new BlockBossDoor(doorBossBlockID, Material.iron)).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("doorBoss").setRequiresSelfNotify();
		ReploidCraftEnv.blockItemHolder = new BlockItemHolder().setStepSound(Block.soundTypeStone).setBlockName("itemHolder").setHardness(3.5F);
		ReploidCraftEnv.blockMechBay = new BlockMechBay().setStepSound(Block.soundTypeAnvil).setBlockName("mechBay").setHardness(3.5F);

		GameRegistry.registerBlock(ReploidCraftEnv.spikes, "spikes");
		//GameRegistry.registerBlock(doorBossBlock, "doorBoss");
		GameRegistry.registerBlock(ReploidCraftEnv.blockItemHolder, "itemHolder");
		GameRegistry.registerTileEntity(TileEntityItemHolder.class, "itemHolder");
		GameRegistry.registerBlock(ReploidCraftEnv.blockMechBay, ItemMechBay.class, "mechBay");
		GameRegistry.registerTileEntity(TileEntityMechBay.class, "mechBay");
		GameRegistry.registerTileEntity(TileEntityMechBayController.class, "mechBayController");
		GameRegistry.registerTileEntity(TileEntityMechBayEnergy.class, "mechBayEnergy");
	}
	public void addItems() {
		/** Items **/

		ReploidCraftEnv.healthBit = new ItemHPEnergy(0, 3, 0.5F).setPotionEffect(Potion.heal.id, 1, 0, 1.0F).setAlwaysEdible().setUnlocalizedName("healthBit").setMaxStackSize(1);
		ReploidCraftEnv.healthByte = new ItemHPEnergy(1, 8, 0.8F).setPotionEffect(Potion.heal.id, 1, 1, 1.0F).setAlwaysEdible().setUnlocalizedName("healthByte").setMaxStackSize(1);
		//weaponBit = new ItemWPEnergy(weaponBitID).setItemName("Weapon Bit").setMaxStackSize(1);
		//weaponByte = new ItemWPEnergy(weaponByteID).setItemName("Weapon Byte").setMaxStackSize(1);
		ReploidCraftEnv.healthTank = new ItemTank().setUnlocalizedName("Health Tank").setMaxStackSize(1); //use EventBus to add to this, then use some other method to heal the player per heart
		//weaponTank = new ItemTank(weaponTankID).setItemName("Weapon Tank").setMaxStackSize(1);
		//extraMan = new ItemLife(extraManID).setItemName("Extra Man").setMaxStackSize(9); //possibly use ticker to instantly heal player?

		ReploidCraftEnv.reploidPlate = new ItemReploidPlate().setUnlocalizedName("reploidPlate");
		ReploidCraftEnv.component = new ItemComponent().setUnlocalizedName("component");
		//ReploidCraftEnv.itemHolder = (new ItemItemHolder(itemHolderID)).setUnlocalizedName("holder").setTextureName("item_holder");
		ReploidCraftEnv.debugger = new ItemDebugger().setUnlocalizedName("debugger");

		ReploidCraftEnv.platformPlacer = new ItemPlatformPlacer().setUnlocalizedName("platformPlacer");
		ReploidCraftEnv.rideArmorPlacer = new ItemRideArmorPlacer().setUnlocalizedName("rideArmorPlacer");
		//doorBossItem = (new ItemBossDoor(doorBossItemID, Material.iron)).setIconCoord(12, 2).setItemName("doorBoss");
		registerItem(ReploidCraftEnv.healthBit);
		registerItem(ReploidCraftEnv.healthByte);
		registerItem(ReploidCraftEnv.healthTank);
		registerItem(ReploidCraftEnv.reploidPlate);
		registerItem(ReploidCraftEnv.component);
		registerItem(ReploidCraftEnv.debugger);
		registerItem(ReploidCraftEnv.platformPlacer);
		registerItem(ReploidCraftEnv.rideArmorPlacer);
	}
	
	public static void registerItem(Item item)
	{
 		GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
	
	public static void registerBlock(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName().replace("tile.", ""));	
	}
	
	public static void registerBlock(Block block, Class<? extends ItemBlock> itemBlockClass)
	{
		GameRegistry.registerBlock(block, itemBlockClass, block.getUnlocalizedName().replace("tile.", ""));
	}
}
