package zornco.reploidcraft.core;


import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.blocks.ContainerItemHolder;
import zornco.reploidcraft.blocks.ContainerMechBay;
import zornco.reploidcraft.blocks.TileEntityItemHolder;
import zornco.reploidcraft.blocks.TileEntityMechBayController;
import zornco.reploidcraft.entities.EntityRideArmor;
import zornco.reploidcraft.entities.armorParts.PartList;
import zornco.reploidcraft.entities.armorParts.PartSlot;
import zornco.reploidcraft.entities.armorParts.PartsRegistry;
import zornco.reploidcraft.entities.armorParts.creeper.CreeperBody;
import zornco.reploidcraft.entities.armorParts.creeper.CreeperLegs;
import zornco.reploidcraft.entities.armorParts.frog.FrogHead;
import zornco.reploidcraft.entities.armorParts.green.GreenArm;
import zornco.reploidcraft.entities.armorParts.green.GreenBack;
import zornco.reploidcraft.entities.armorParts.green.GreenBody;
import zornco.reploidcraft.entities.armorParts.green.GreenLegs;
import zornco.reploidcraft.entities.armorParts.rabbit.RabbitBack;
import zornco.reploidcraft.entities.armorParts.red.RedBack;
import zornco.reploidcraft.entities.armorParts.red.RedBody;
import zornco.reploidcraft.items.ContainerReploidArmor;
import zornco.reploidcraft.items.ContainerReploidBuster;
import zornco.reploidcraft.items.InventoryArmor;
import zornco.reploidcraft.items.InventoryBuster;
import zornco.reploidcraft.utils.RiderState;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity te = world.getTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityItemHolder)
		{
			TileEntityItemHolder icte = (TileEntityItemHolder) te;
			return new ContainerItemHolder(player.inventory, icte, 0, 0);
		}
		else if (ID == Config.GUI_ARMOR_INV)
		{
			return new ContainerReploidArmor(player, player.inventory, new InventoryArmor(player.getHeldItem()));
		}
		else if (ID == Config.GUI_BUSTER_INV)
		{
			return new ContainerReploidBuster(player, player.inventory, new InventoryBuster(player.getHeldItem()));
		}
		else if (te != null && te instanceof TileEntityMechBayController)
		{
			TileEntityMechBayController icte = (TileEntityMechBayController) te;
			return new ContainerMechBay(icte, player.inventory);
		}
		else
		{
			return null;
		}
	}
	public Entity getEntityByID(int entityId, int dimension)
	{
		Entity targetEntity = null;
		if (Side.SERVER == FMLCommonHandler.instance().getEffectiveSide())
		{
			WorldServer worldserver = MinecraftServer.getServer().worldServerForDimension(dimension);
			targetEntity = worldserver.getEntityByID(entityId);
		}

		if ((null != targetEntity) && ((targetEntity instanceof EntityRideArmor)))
		{
			return (EntityRideArmor)targetEntity;
		}

		return null;
	}
	// Client stuff
	public void registerRenderInformation () {
		// Nothing here as this is the server side proxy
	}
	public void registerKeyBindingHandler() {

	}
	public void initTickHandlers()
	{

	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}
	public int addArmor(String path) {
		return 0;
	}
	public void busterShot(World worldObj, double sx, double sy, double sz, float size, int type)
	{
	}
	public void setKeyBinding(String name, int value) {

	}
	public void registerSounds() {

	}
	public Object getSoundManager() { return null;}

	public World getClientWorld() {
		return null;
	}
	public void updateRiderState(EntityRideArmor rideArmor, Entity entity)
	{
		
	}
	public RiderState getRiderState(Entity rider)
	{
		return null;
	}

	public PartsRegistry partRegistry = new PartsRegistry();

	public void registerParts()
	{
		this.partRegistry.registerPart("EMPTY", PartSlot.HEAD, 		0, new float[] {0.0F, 3.0F, 0.0F}, new float[] {0.5F, 1.5F});
		this.partRegistry.registerPart("EMPTY", PartSlot.BODY, 		0, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});
		this.partRegistry.registerPart("EMPTY", PartSlot.BACK, 		0, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.0F, 1.0F});
		this.partRegistry.registerPart("EMPTY", PartSlot.LEGS, 		0, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.5F, 1.0F});
		this.partRegistry.registerPart("EMPTY", PartSlot.ARMLEFT, 	0, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F});  
		this.partRegistry.registerPart("EMPTY", PartSlot.ARMRIGHT, 	0, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F});
		
		//this.partRegistry.registerPart("GREEN", PartSlot.HEAD, 		20, new float[] {0.0F, 3.0F, 0.0F}, new float[] {0.5F, 1.5F});
		this.partRegistry.registerPart("GREEN", PartSlot.BODY, 		new GreenBody());
		this.partRegistry.registerPart("GREEN", PartSlot.BACK, 		new GreenBack());
		this.partRegistry.registerPart("GREEN", PartSlot.LEGS, 		new GreenLegs());
		this.partRegistry.registerPart("GREEN", PartSlot.ARMLEFT, 	new GreenArm());
		this.partRegistry.registerPart("GREEN", PartSlot.ARMRIGHT, 	new GreenArm(PartSlot.ARMRIGHT));
		
		this.partRegistry.registerPart("RED", PartSlot.BODY, 		new RedBody());
		this.partRegistry.registerPart("RED", PartSlot.BACK, 		new RedBack());
		this.partRegistry.registerPart("RED", PartSlot.LEGS, 		20, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.5F, 1.0F});
		this.partRegistry.registerPart("RED", PartSlot.ARMLEFT, 	20, new float[] {1.25F, 1.0F, 0.0F}, new float[] {1.0F, 1.0F});
		this.partRegistry.registerPart("RED", PartSlot.ARMRIGHT, 	20, new float[] {-1.25F, 1.0F, 0.0F}, new float[] {1.0F, 1.0F});
		
		this.partRegistry.registerPart("FROG", PartSlot.HEAD, 		new FrogHead());

		this.partRegistry.registerPart("RABBIT", PartSlot.BACK, 	new RabbitBack());
		
		this.partRegistry.registerPart("CREEPER", PartSlot.LEGS, 	new CreeperLegs());
		this.partRegistry.registerPart("CREEPER", PartSlot.BODY, 	new CreeperBody());//20, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});

		int s = 0;
		for (Object list : this.partRegistry.getMap().values()) {
			s += ((PartList) list).getPartList().size();
		}
		ReploidCraft.logger.info("Registered " + s + " parts!");
	}
	public void registerArmors(){}
	public void playChargeContSound(EntityPlayer player){}
	public void playChargeUpSound(EntityPlayer player){}
}