package zornco.reploidcraftenv.items;

import zornco.reploidcraftenv.ReploidCraftEnv;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class ItemMechBay extends ItemMultiTexture {

	public ItemMechBay(Block var1,
			String[] var2) {
		super(var1, var1, var2);
		this.setCreativeTab(ReploidCraftEnv.reploidTab);
	}

}
