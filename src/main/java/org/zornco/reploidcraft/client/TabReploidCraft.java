package org.zornco.reploidcraft.client;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class TabReploidCraft extends CreativeTabs {

	public TabReploidCraft(String label) {
		super(label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.DIAMOND);
	}

}
