package zornco.reploidcraftenv.core;


import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.ContainerItemHolder;
import zornco.reploidcraftenv.blocks.TileEntityItemHolder;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.parts.PartList;
import zornco.reploidcraftenv.entities.parts.PartSlot;
import zornco.reploidcraftenv.entities.parts.PartType;
import zornco.reploidcraftenv.entities.parts.PartsRegistry;
import zornco.reploidcraftenv.network.PacketRideArmor;
import zornco.reploidcraftenv.utils.RiderState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.FMLCommonHandler;
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
		this.partRegistry.registerPart(PartType.GREEN, PartSlot.HEAD, makeFloatArray(0.0F, 3.0F, 0.0F), makeFloatArray(0.5F, 1.5F));
		this.partRegistry.registerPart(PartType.GREEN, PartSlot.BODY, makeFloatArray(0.0F, 1.5F, 0.0F), makeFloatArray(1.5F, 1.5F));
		this.partRegistry.registerPart(PartType.GREEN, PartSlot.BACK, makeFloatArray(0.0F, 1.5F, 1.5F), makeFloatArray(1.0F, 1.0F));
		this.partRegistry.registerPart(PartType.GREEN, PartSlot.LEGS, makeFloatArray(0.0F, 0.0F, 0.0F), makeFloatArray(1.5F, 1.0F));
		this.partRegistry.registerPart(PartType.GREEN, PartSlot.ARMLEFT, makeFloatArray(1.25F, 1.0F, 0.0F), makeFloatArray(1.0F, 1.0F));
		this.partRegistry.registerPart(PartType.GREEN, PartSlot.ARMRIGHT, makeFloatArray(-1.25F, 1.0F, 0.0F), makeFloatArray(1.0F, 1.0F));

		this.partRegistry.registerPart(PartType.RED, PartSlot.HEAD, makeFloatArray(0.0F, 3.0F, 0.0F), makeFloatArray(0.5F, 1.5F));
		this.partRegistry.registerPart(PartType.RED, PartSlot.BODY, makeFloatArray(0.0F, 1.5F, 0.0F), makeFloatArray(1.5F, 1.5F));
		this.partRegistry.registerPart(PartType.RED, PartSlot.BACK, makeFloatArray(0.0F, 1.5F, 1.5F), makeFloatArray(1.0F, 1.0F));
		this.partRegistry.registerPart(PartType.RED, PartSlot.LEGS, makeFloatArray(0.0F, 0.0F, 0.0F), makeFloatArray(1.5F, 1.0F));
		this.partRegistry.registerPart(PartType.RED, PartSlot.ARMLEFT, makeFloatArray(1.25F, 1.0F, 0.0F), makeFloatArray(1.0F, 1.0F));
		this.partRegistry.registerPart(PartType.RED, PartSlot.ARMRIGHT, makeFloatArray(-1.25F, 1.0F, 0.0F), makeFloatArray(1.0F, 1.0F));
		
		this.partRegistry.registerPart(PartType.EMPTY, PartSlot.HEAD, makeFloatArray(0.0F, 3.0F, 0.0F), makeFloatArray(0.5F, 1.5F));
		this.partRegistry.registerPart(PartType.EMPTY, PartSlot.BODY, makeFloatArray(0.0F, 1.5F, 0.0F), makeFloatArray(1.5F, 1.5F));
		this.partRegistry.registerPart(PartType.EMPTY, PartSlot.BACK, makeFloatArray(0.0F, 1.5F, 0.0F), makeFloatArray(1.0F, 1.0F));
		this.partRegistry.registerPart(PartType.EMPTY, PartSlot.LEGS, makeFloatArray(0.0F, 0.0F, 0.0F), makeFloatArray(1.5F, 1.0F));
		this.partRegistry.registerPart(PartType.EMPTY, PartSlot.ARMLEFT, makeFloatArray(0.0F, 0.0F, 0.0F), makeFloatArray(1.0F, 1.0F));  
		this.partRegistry.registerPart(PartType.EMPTY, PartSlot.ARMRIGHT, makeFloatArray(0.0F, 0.0F, 0.0F), makeFloatArray(1.0F, 1.0F));
		int s = 0;
		for (Object list : this.partRegistry.getMap().values()) {
			s += ((PartList) list).getPartList().size();
		}
		ReploidCraftEnv.logger.info("Registered " + s + " parts!");
	}

	public float[] makeFloatArray(float ... args)
	{
		return args;
	}
}