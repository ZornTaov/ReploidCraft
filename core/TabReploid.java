package zornco.reploidcraftenv.core;

import net.minecraft.creativetab.CreativeTabs;
import zornco.reploidcraftenv.ReploidCraftEnv;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TabReploid extends CreativeTabs
{
	public TabReploid(String label) 
	{
		super(label);
	}

	@SideOnly(Side.CLIENT)
	public int getTabIconItemIndex()
	{
		return ReploidCraftEnv.component.itemID;
	}
}
