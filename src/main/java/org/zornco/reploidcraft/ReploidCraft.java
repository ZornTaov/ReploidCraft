package org.zornco.reploidcraft;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.zornco.reploidcraft.client.TabReploidCraft;
import org.zornco.reploidcraft.client.handler.GuiHandler;
import org.zornco.reploidcraft.init.*;
import org.zornco.reploidcraft.network.ReploidNetwork;
import org.zornco.reploidcraft.proxy.ClientProxy;
import org.zornco.reploidcraft.proxy.CommonProxy;

@Mod(modid = ReploidCraft.MODID, 
	name = ReploidCraft.MOD_NAME,
	version = ReploidCraft.VERSION,
	acceptedMinecraftVersions = "[1.9,)"
	)
public class ReploidCraft {
	public static final String MODID = "reploidcraft";
	public static final String MOD_NAME = "ReploidCraft";
	public static final String VERSION = "${version}";
	// The instance of your mod that Forge uses.
	@Instance(ReploidCraft.MODID)
	public static ReploidCraft instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="org.zornco.reploidcraft.proxy.ClientProxy", serverSide="org.zornco.reploidcraft.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs reploidCraftTab;
	
	public static Logger logger;
	
	
	@EventHandler
	public void pre(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		reploidCraftTab = new TabReploidCraft(ReploidCraft.MODID);
		RCBlocks.preInit();
		RCItems.preInit();
		proxy.registerEntities();
		proxy.registerRenderersPre();
		proxy.registerArmors();
		proxy.registerHandlers();
		ReploidNetwork.init();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init();
		proxy.registerRenderersInit();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

	@EventHandler
	public void post(FMLPostInitializationEvent event) {

	}
}
