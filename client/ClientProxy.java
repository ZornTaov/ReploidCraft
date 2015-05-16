package zornco.reploidcraftenv.client;


import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.TileEntityItemHolder;
import zornco.reploidcraftenv.blocks.TileEntityMechBay;
import zornco.reploidcraftenv.blocks.TileEntityMechBayController;
import zornco.reploidcraftenv.bullets.EntityMetBullet;
import zornco.reploidcraftenv.client.gui.GUIItemHolder;
import zornco.reploidcraftenv.client.gui.GuiArmorInventory;
import zornco.reploidcraftenv.client.gui.GuiBusterInventory;
import zornco.reploidcraftenv.client.gui.GuiMechBayController;
import zornco.reploidcraftenv.client.renderers.BlockBossDoorRenderer;
import zornco.reploidcraftenv.client.renderers.BlockSpikesRenderer;
import zornco.reploidcraftenv.client.renderers.ItemItemHolderRenderer;
import zornco.reploidcraftenv.client.renderers.ModelMet;
import zornco.reploidcraftenv.client.renderers.ModelMetHat;
import zornco.reploidcraftenv.client.renderers.ModelReploidBlue;
import zornco.reploidcraftenv.client.renderers.RenderBulletBase;
import zornco.reploidcraftenv.client.renderers.RenderFloatingPlatform;
import zornco.reploidcraftenv.client.renderers.RenderMet;
import zornco.reploidcraftenv.client.renderers.RenderRideArmor;
import zornco.reploidcraftenv.client.renderers.TileEntityItemHolderRenderer;
import zornco.reploidcraftenv.client.renderers.TileEntityMechBayRenderer;
import zornco.reploidcraftenv.core.CommonProxy;
import zornco.reploidcraftenv.core.Config;
import zornco.reploidcraftenv.entities.EntityFloatingPlatform;
import zornco.reploidcraftenv.entities.EntityMet;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.items.ContainerReploidArmor;
import zornco.reploidcraftenv.items.ContainerReploidBuster;
import zornco.reploidcraftenv.items.InventoryArmor;
import zornco.reploidcraftenv.items.InventoryBuster;
import zornco.reploidcraftenv.network.MessageRideArmor;
import zornco.reploidcraftenv.network.PacketHandler;
import zornco.reploidcraftenv.sounds.BusterChargeContMoving;
import zornco.reploidcraftenv.sounds.BusterChargeUpMoving;
import zornco.reploidcraftenv.sounds.Sounds;
import zornco.reploidcraftenv.utils.RiderState;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	Sounds sounds;
	public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();
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
		RenderingRegistry.registerEntityRenderingHandler(EntityRideArmor.class, new RenderRideArmor()); 

		Config.spikesRI = RenderingRegistry.getNextAvailableRenderId();
		Config.bossDoorRI = RenderingRegistry.getNextAvailableRenderId();

		RenderingRegistry.registerBlockHandler(new BlockSpikesRenderer());
		RenderingRegistry.registerBlockHandler(new BlockBossDoorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityItemHolder.class, new TileEntityItemHolderRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMechBay.class, new TileEntityMechBayRenderer());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ReploidCraftEnv.blockItemHolder), new ItemItemHolderRenderer());

	}
	@Override
	public void registerArmors(){ 
		ModelReploidBlue reploidArmor = new ModelReploidBlue(0.1F);
		ModelReploidBlue reploidLegs = new ModelReploidBlue(0.05F);
		armorModels.put(ReploidCraftEnv.reploidHelm, reploidArmor);
		armorModels.put(ReploidCraftEnv.reploidChest, reploidArmor);
		armorModels.put(ReploidCraftEnv.reploidLegs, reploidLegs);
		armorModels.put(ReploidCraftEnv.reploidBoots, reploidArmor);
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && ID == Config.GUI_ITEM_HOLDER)
        {
            return new GUIItemHolder(player.inventory, (TileEntityItemHolder) te);
        }
        else if (ID == Config.GUI_ARMOR_INV)
        {
        	return new GuiArmorInventory((ContainerReploidArmor) new ContainerReploidArmor(player, player.inventory, new InventoryArmor(player.getHeldItem())));
        }
        else if (ID == Config.GUI_ARMOR_INV)
        {
        	return new GuiBusterInventory((ContainerReploidBuster) new ContainerReploidBuster(player, player.inventory, new InventoryBuster(player.getHeldItem())));
        }
        else if (te != null && ID == Config.GUI_MECH_BAY && te instanceof TileEntityMechBayController)
        {
            return new GuiMechBayController(player.inventory, (TileEntityMechBayController) te);
        }
        else
        {
            return null;
        }
		
	}
	@Override
	public Object getSoundManager()
	{
		return Minecraft.getMinecraft().getSoundHandler();
	}
	@Override
	public void playChargeContSound(EntityPlayer player)
	{
		FMLClientHandler.instance().getClient().getSoundHandler().playSound(new BusterChargeContMoving(player));
	}
	@Override
	public void playChargeUpSound(EntityPlayer player)
	{
		FMLClientHandler.instance().getClient().getSoundHandler().playSound(new BusterChargeUpMoving(player));
	}
	public World getClientWorld() {
		return FMLClientHandler.instance().getClient().theWorld;
	}
	@Override
	public Entity getEntityByID(int entityId, int dimension)
	{
		Entity targetEntity = null;
		if (Side.CLIENT == FMLCommonHandler.instance().getEffectiveSide())
		{
			targetEntity = FMLClientHandler.instance().getClient().theWorld.getEntityByID(entityId);
		}

		if ((null != targetEntity) && ((targetEntity instanceof EntityRideArmor)))
		{
			return (EntityRideArmor)targetEntity;
		}

		return null;
	}
	@Override
	public void updateRiderState(EntityRideArmor rideArmor, Entity entity) {
		if (rideArmor.worldObj.isRemote)
		{
			rideArmor.setRiderState(getRiderState(entity));

			if (rideArmor.riderState.isChanged())
			{
				MessageRideArmor packet = new MessageRideArmor(rideArmor, entity);
				PacketHandler.INSTANCE.sendToServer(packet);
			}

			rideArmor.riderState.resetChanged();
		}
	}
	@Override
	public RiderState getRiderState(Entity rider) {
		RiderState rs = new RiderState();
		if (rider instanceof EntityPlayerSP)
	    {
	      EntityPlayerSP riderSP = (EntityPlayerSP)rider;
	      rs.setMoveForward(riderSP.movementInput.moveForward);
	      rs.setMoveStrafe(riderSP.movementInput.moveStrafe);
	      rs.setJump(riderSP.movementInput.jump);
	      rs.setSneak(riderSP.movementInput.sneak);
	    }
		else if (rider instanceof EntityMob)
		{
			EntityMob riderMob = (EntityMob)rider;
			rs.setMoveForward(riderMob.moveForward);
		      rs.setMoveStrafe(0.0F);
		      rs.setJump(riderMob.motionY > 0.0F);
		      rs.setSneak(false);
		}
		return rs;
	}
}