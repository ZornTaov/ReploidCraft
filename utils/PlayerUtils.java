package zornco.reploidcraftenv.utils;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.client.GUIItemHolder;
import zornco.reploidcraftenv.network.PacketItemHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;


public final class PlayerUtils {
	
	private PlayerUtils() {  }
	
	@SideOnly(Side.CLIENT)
	public static EntityPlayer getLocalPlayer() {
		return Minecraft.getMinecraft().thePlayer;
	}
	
	public static void openGui(EntityPlayer pl, String name, int columns, int rows, String title, Container container) {
		
		EntityPlayerMP player = (EntityPlayerMP)pl;
		if (title == null) title = "";
		
		player.closeContainer();
		player.getNextWindowId();
		
		ReploidCraftEnv.packetPipeline.sendTo(new PacketItemHolder(player.currentWindowId, name, columns, rows, title), player);
		
		player.openContainer = container;
		player.openContainer.windowId = player.currentWindowId;
		player.openContainer.addCraftingToCrafters(player);
		
	}
	
	@SideOnly(Side.CLIENT)
	public static void openGui(EntityPlayer player, String name, int columns, int rows, String title) {
		GuiScreen gui = createGuiFromName(player, name, columns, rows, title);
		Minecraft.getMinecraft().displayGuiScreen(gui);
	}
	@SideOnly(Side.CLIENT)
	private static GuiScreen createGuiFromName(EntityPlayer player, String name, int columns, int rows, String title) {
		
		boolean localized = !title.isEmpty();
		if (!localized) title = name;
		
		if (name.equals("ItemHolder")) {
			return new GUIItemHolder(player, title, localized);
		} else 
		{
			ReploidCraftEnv.logger.warn("Error, attempted to create a gui that isn't set up yet!");
			return null;//new GuiBetterStorage(player, columns, rows, title, localized);
		}
		
	}
	
}