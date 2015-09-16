package zornco.reploidcraft;



import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import zornco.reploidcraft.core.CommonProxy;
import zornco.reploidcraft.core.Config;
import zornco.reploidcraft.core.EventBus;
import zornco.reploidcraft.core.TabReploid;
import zornco.reploidcraft.items.ItemRideArmorPart;
import zornco.reploidcraft.network.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
//import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid=ReploidCraft.MOD_ID, name="ReploidCraft", version="0.0.1", acceptedMinecraftVersions = "[1.6,)")
//@NetworkMod(channels = { "ReploidEnv" }, clientSideRequired=true, serverSideRequired=false, packetHandler=PacketHandler.class)
public class ReploidCraft {

	public static final String MOD_ID = "reploidcraft";
	// The instance of your mod that Forge uses.
	@Instance(ReploidCraft.MOD_ID)
	public static ReploidCraft instance;

	//public static final PacketPipeline packetPipeline = new PacketPipeline();
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="zornco.reploidcraft.client.ClientProxy", serverSide="zornco.reploidcraft.core.CommonProxy")
	public static CommonProxy proxy;

	public static CreativeTabs reploidTab = new TabReploid(ReploidCraft.MOD_ID);

	public static Logger logger = LogManager.getLogger(ReploidCraft.MOD_ID);

	public static Random rand = new Random();

	public static Config config = new Config();

	public static EventBus events;
	
	public static Item reploidHelm;
	public static Item reploidChest;
	public static Item reploidLegs;
	public static Item reploidBoots;
	public static Item reploidBuster;
	
	public static Item upgradeChip;
	
	public static Item healthBit;
	public static Item healthByte;
	public static Item weaponBit;
	public static Item weaponByte;
	public static Item healthTank;
	public static Item weaponTank;
	public static Item extraMan;
	public static Item component;
	public static Item reploidPlate;
	public static Item itemHolder;
	public static Item debugger;

	public static Item platformPlacer;
	public static Item rideArmorPlacer;
	public static Item rideArmorPart;
	public static Item doorBossItem;

	public static Block spikes;
	public static Block doorBossBlock;
	public static Block blockItemHolder;
	public static Block blockMechBay;

	public static ArmorMaterial enumArmorReploid = EnumHelper.addArmorMaterial("reploid", 20, new int[]{2, 6, 5, 2}, 0);
	public static EnumAction busterAction = EnumHelper.addAction("buster");
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerKeyBindingHandler();
		proxy.registerSounds();
		config.addItems();
		config.addBlocks();
		proxy.registerParts();
		((ItemRideArmorPart) rideArmorPart).populateParts();
		proxy.registerArmors();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		Config.recipes.registerRecipes();
		proxy.registerRenderInformation();
		events = new EventBus();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        PacketHandler.init();
		config.registerEntities();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		//packetPipeline.postInitialise();
	}
}