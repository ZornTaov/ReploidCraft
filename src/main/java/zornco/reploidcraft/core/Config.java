package zornco.reploidcraft.core;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraft.world.biome.BiomeGenBase;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.blocks.BlockItemHolder;
import zornco.reploidcraft.blocks.BlockMechBay;
import zornco.reploidcraft.blocks.BlockSpikes;
import zornco.reploidcraft.blocks.TileEntityItemHolder;
import zornco.reploidcraft.blocks.TileEntityMechBay;
import zornco.reploidcraft.blocks.TileEntityMechBayController;
import zornco.reploidcraft.blocks.TileEntityMechBayEnergy;
import zornco.reploidcraft.bullets.EntityMetBullet;
import zornco.reploidcraft.crafting.RecipeHandler;
import zornco.reploidcraft.entities.EntityFloatingPlatform;
import zornco.reploidcraft.entities.EntityMet;
import zornco.reploidcraft.entities.EntityRideArmor;
import zornco.reploidcraft.items.ItemBuster;
import zornco.reploidcraft.items.ItemChip;
import zornco.reploidcraft.items.ItemComponent;
import zornco.reploidcraft.items.ItemDebugger;
import zornco.reploidcraft.items.ItemHPEnergy;
import zornco.reploidcraft.items.ItemMechBay;
import zornco.reploidcraft.items.ItemPlatformPlacer;
import zornco.reploidcraft.items.ItemReploidArmor;
import zornco.reploidcraft.items.ItemReploidPlate;
import zornco.reploidcraft.items.ItemRideArmorPart;
import zornco.reploidcraft.items.ItemRideArmorPlacer;
import zornco.reploidcraft.items.ItemTank;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class Config {
	public static final int GUI_ITEM_HOLDER = 0;
	public static final int GUI_MECH_BAY = 1;
	public static final int GUI_ARMOR_INV = 2;
	public static final int GUI_BUSTER_INV = 3;
	//ID's
	public static int spikesRI = -1;
	public static int bossDoorRI = -1;

	public static RecipeHandler recipes = new RecipeHandler();

	public void registerEntities() {
		/** Registers **/
		
		EntityRegistry.registerGlobalEntityID(EntityMet.class, "Met", EntityRegistry.findGlobalUniqueEntityId(), 0xFFFF00, 0x0F0000);
		EntityRegistry.registerModEntity(EntityMet.class, "Met", 2, ReploidCraft.instance, 80, 3, true);
		EntityRegistry.addSpawn("Met", 30, 2, 4, EnumCreatureType.monster, BiomeGenBase.beach, BiomeGenBase.swampland);
		EntityRegistry.registerGlobalEntityID(EntityFloatingPlatform.class, "FloatingPlatform", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityFloatingPlatform.class, "FloatingPlatform", 3, ReploidCraft.instance, 80, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityMetBullet.class, "metBullet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityMetBullet.class, "metBullet", 4, ReploidCraft.instance, 250, 1, true);
		EntityRegistry.registerGlobalEntityID(EntityRideArmor.class, "rideArmor", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityRideArmor.class, "rideArmor", 5, ReploidCraft.instance, 250, 1, true);
	}

	public void addBlocks() {
		/** Blocks **/
		ReploidCraft.spikes = new BlockSpikes().setStepSound(Block.soundTypeStone).setBlockName("spikes").setHardness(3.5F);
		//doorBossBlock = (new BlockBossDoor(doorBossBlockID, Material.iron)).setHardness(5.0F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("doorBoss").setRequiresSelfNotify();
		ReploidCraft.blockItemHolder = new BlockItemHolder().setStepSound(Block.soundTypeStone).setBlockName("itemHolder").setHardness(3.5F);
		ReploidCraft.blockMechBay = new BlockMechBay().setStepSound(Block.soundTypeAnvil).setBlockName("mechBay").setHardness(3.5F);

		GameRegistry.registerBlock(ReploidCraft.spikes, "spikes");
		//GameRegistry.registerBlock(doorBossBlock, "doorBoss");
		GameRegistry.registerBlock(ReploidCraft.blockItemHolder, "itemHolder");
		GameRegistry.registerTileEntity(TileEntityItemHolder.class, "itemHolder");
		GameRegistry.registerBlock(ReploidCraft.blockMechBay, ItemMechBay.class, "mechBay");
		GameRegistry.registerTileEntity(TileEntityMechBay.class, "mechBay");
		GameRegistry.registerTileEntity(TileEntityMechBayController.class, "mechBayController");
		GameRegistry.registerTileEntity(TileEntityMechBayEnergy.class, "mechBayEnergy");
	}
	public void addItems() {
		/** Items **/
		
		
		ReploidCraft.reploidHelm = new ItemReploidArmor(ReploidCraft.enumArmorReploid, 1, 0).setUnlocalizedName("reploid_Helm");
		ReploidCraft.reploidChest = new ItemReploidArmor(ReploidCraft.enumArmorReploid, 1, 1).setUnlocalizedName("reploid_Chest");
		ReploidCraft.reploidLegs = new ItemReploidArmor(ReploidCraft.enumArmorReploid, 1, 2).setUnlocalizedName("reploid_Legs");
		ReploidCraft.reploidBoots = new ItemReploidArmor(ReploidCraft.enumArmorReploid, 1, 3).setUnlocalizedName("reploid_Boots"); 
		ReploidCraft.reploidBuster = new ItemBuster().setUnlocalizedName("reploid_buster");
		
		ReploidCraft.upgradeChip = new ItemChip().setUnlocalizedName("chip");
		
		ReploidCraft.healthBit = new ItemHPEnergy(0, 3, 0.5F).setPotionEffect(Potion.heal.id, 1, 0, 1.0F).setAlwaysEdible().setUnlocalizedName("healthBit").setMaxStackSize(1);
		ReploidCraft.healthByte = new ItemHPEnergy(1, 8, 0.8F).setPotionEffect(Potion.heal.id, 1, 1, 1.0F).setAlwaysEdible().setUnlocalizedName("healthByte").setMaxStackSize(1);
		//weaponBit = new ItemWPEnergy(weaponBitID).setItemName("Weapon Bit").setMaxStackSize(1);
		//weaponByte = new ItemWPEnergy(weaponByteID).setItemName("Weapon Byte").setMaxStackSize(1);
		ReploidCraft.healthTank = new ItemTank().setUnlocalizedName("Health Tank").setMaxStackSize(1); //use EventBus to add to this, then use some other method to heal the player per heart
		//weaponTank = new ItemTank(weaponTankID).setItemName("Weapon Tank").setMaxStackSize(1);
		//extraMan = new ItemLife(extraManID).setItemName("Extra Man").setMaxStackSize(9); //possibly use ticker to instantly heal player?
		
		ReploidCraft.reploidPlate = new ItemReploidPlate().setUnlocalizedName("reploidPlate");
		ReploidCraft.component = new ItemComponent().setUnlocalizedName("component");
		ReploidCraft.rideArmorPart = new ItemRideArmorPart().setUnlocalizedName("part");
		//ReploidCraft.itemHolder = (new ItemItemHolder(itemHolderID)).setUnlocalizedName("holder").setTextureName("item_holder");
		ReploidCraft.debugger = new ItemDebugger().setUnlocalizedName("debugger");

		ReploidCraft.platformPlacer = new ItemPlatformPlacer().setUnlocalizedName("platformPlacer");
		ReploidCraft.rideArmorPlacer = new ItemRideArmorPlacer().setUnlocalizedName("rideArmorPlacer");
		//doorBossItem = (new ItemBossDoor(doorBossItemID, Material.iron)).setIconCoord(12, 2).setItemName("doorBoss");
		registerItem(ReploidCraft.reploidHelm);
		registerItem(ReploidCraft.reploidChest);
		registerItem(ReploidCraft.reploidLegs);
		registerItem(ReploidCraft.reploidBoots);
		registerItem(ReploidCraft.reploidBuster);
		registerItem(ReploidCraft.upgradeChip);
		registerItem(ReploidCraft.healthBit);
		registerItem(ReploidCraft.healthByte);
		registerItem(ReploidCraft.healthTank);
		registerItem(ReploidCraft.rideArmorPart);
		registerItem(ReploidCraft.reploidPlate);
		registerItem(ReploidCraft.component);
		registerItem(ReploidCraft.debugger);
		registerItem(ReploidCraft.platformPlacer);
		registerItem(ReploidCraft.rideArmorPlacer);
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
