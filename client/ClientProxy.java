package zornco.reploidcraftenv.client;


import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.TileEntityItemHolder;
import zornco.reploidcraftenv.bullets.EntityMetBullet;
import zornco.reploidcraftenv.client.renderers.BlockBossDoorRenderer;
import zornco.reploidcraftenv.client.renderers.BlockSpikesRenderer;
import zornco.reploidcraftenv.client.renderers.ModelMet;
import zornco.reploidcraftenv.client.renderers.ModelMetHat;
import zornco.reploidcraftenv.client.renderers.RenderBulletBase;
import zornco.reploidcraftenv.client.renderers.RenderFloatingPlatform;
import zornco.reploidcraftenv.client.renderers.RenderMet;
import zornco.reploidcraftenv.client.renderers.TileEntityItemHolderRenderer;
import zornco.reploidcraftenv.core.CommonProxy;
import zornco.reploidcraftenv.entities.EntityFloatingPlatform;
import zornco.reploidcraftenv.entities.EntityMet;
import zornco.reploidcraftenv.sounds.Sounds;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	Sounds sounds;
	public ClientProxy()
	{
		
	}
	public void registerSounds() {
		sounds = new Sounds();
	}
	@Override
	public void registerRenderInformation()
	{
		//MinecraftForgeClient.preloadTexture( "/zornco/megax/textures/MegaXItemTextures.png" );
		/*MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/gui/UpgradeStation.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/fx/buster0.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/fx/buster1.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/fx/buster2.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/models/X1LightBusterDetailed.png" );
		//MinecraftForgeClient.preloadTexture( "/zornco/megax/textures/MetHat.png" );
		MinecraftForgeClient.preloadTexture( "/mods/ReploidCraft/textures/models/FloatingPlatform.png" );
		*/
		RenderingRegistry.registerEntityRenderingHandler(EntityMet.class, new RenderMet(new ModelMet(), new ModelMetHat(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(EntityFloatingPlatform.class, new RenderFloatingPlatform());  
		RenderingRegistry.registerEntityRenderingHandler(EntityMetBullet.class, new RenderBulletBase()); 

		ReploidCraftEnv.config.spikesRI = RenderingRegistry.getNextAvailableRenderId();
		ReploidCraftEnv.config.bossDoorRI = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new BlockSpikesRenderer());
		RenderingRegistry.registerBlockHandler(new BlockBossDoorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(zornco.reploidcraftenv.blocks.TileEntityItemHolder.class, new TileEntityItemHolderRenderer());


	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te != null && te instanceof TileEntityItemHolder)
        {
            return new GUIItemHolder(player.inventory, (TileEntityItemHolder) te);
        }
        else
        {
            return null;
        }
		
	}
	@Override
	public Object getSoundManager()
	{
		return Minecraft.getMinecraft().sndManager;
	}
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
}