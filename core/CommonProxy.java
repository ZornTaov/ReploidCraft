package zornco.reploidcraftenv.core;


import zornco.reploidcraftenv.blocks.ContainerItemHolder;
import zornco.reploidcraftenv.blocks.TileEntityItemHolder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

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
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

		TileEntity te = world.getBlockTileEntity(x, y, z);
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
}