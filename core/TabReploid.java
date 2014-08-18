package zornco.reploidcraftenv.core;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import zornco.reploidcraftenv.ReploidCraftEnv;

public class TabReploid extends CreativeTabs
{
	public TabReploid(String label) 
	{
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		// TODO Auto-generated method stub
		return ReploidCraftEnv.component;
	}
}
