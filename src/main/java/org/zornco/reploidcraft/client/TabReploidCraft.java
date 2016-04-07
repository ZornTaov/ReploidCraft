package org.zornco.reploidcraft.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class TabReploidCraft extends CreativeTabs {

	public TabReploidCraft(String label) {
		super(label);
	}

	@Override
	public Item getTabIconItem() {
		return Items.diamond;
	}

}
