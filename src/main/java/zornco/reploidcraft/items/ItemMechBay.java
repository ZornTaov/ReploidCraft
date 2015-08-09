package zornco.reploidcraft.items;

import java.util.List;

import zornco.reploidcraft.blocks.BlockMechBay;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

public class ItemMechBay extends ItemMultiTexture {

	public ItemMechBay(Block var1){
		super(var1, var1, BlockMechBay.names);

		setMaxDamage(0);
		setHasSubtypes(true);
	}
	public int getMetadata(int i)
	{
		return i;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(Item p_150895_1_, CreativeTabs p_150895_2_,
			List p_150895_3_) {
		for (int ix = 0; ix <  BlockMechBay.names.length; ix++) {
			p_150895_3_.add(new ItemStack(this, 1, ix));
		}
	}
}
