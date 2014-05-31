package zornco.reploidcraftenv.core;


import zornco.reploidcraftenv.blocks.ContainerItemHolder;
import zornco.reploidcraftenv.blocks.TileEntityItemHolder;
import zornco.reploidcraftenv.entities.EntityRideArmor;
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
}