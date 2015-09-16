package zornco.reploidcraft.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import zornco.reploidcraft.ReploidCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemComponent extends Item {

	public static final int[] chipColors = new int[] {1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 2651799, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};
	@SideOnly(Side.CLIENT)
	private IIcon[] componentIcon;
    public ItemComponent()
    {
        super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
		this.setCreativeTab(ReploidCraft.reploidTab);
    }

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
	 * different names based on their damage or NBT.
	 */
	public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, Components.getSize());
		return super.getUnlocalizedName() + "." + Components.values()[i].getUnlocName();
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	if (!Components.values()[par1ItemStack.getItemDamage()].getDesc().equals("")) {
    		String[] desc = Components.values()[par1ItemStack.getItemDamage()].getDesc().split(";");
        	for (String string : desc) {
        		if(!string.equals(""))
            		par3List.add(string);
    		}
		}
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < Components.getSize(); ++var4)
        {
            par3List.add(new ItemStack(par1, 1, var4));
        }
    }

	@SideOnly(Side.CLIENT)

	/**
	 * Gets an icon index based on an item's damage value
	 */
	public IIcon getIconFromDamage(int par1)
	{
        int var2 = MathHelper.clamp_int(par1, 0, Components.getSize());
		return this.componentIcon[var2];
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.componentIcon = new IIcon[Components.getSize()];

        for (int i = 0; i < Components.getSize(); ++i)
        {
            this.componentIcon[i] = par1IconRegister.registerIcon(ReploidCraft.MOD_ID+":"+Components.values()[i].getUnlocName());
        }
    }
	protected static int inc = 0;
	public enum Components
	{
		diamondDust	("diamondDust"	),
		AEGD		("AEGD"	 		, "Accumulative Energy;Generation Device"),
		fist		("fist"			),
		spikedFist	("spikedFist"	),
		drill		("drill"		, "May your Drill;peirce the Heavens!"),
		redArm		("redArm"		),
		greenArm	("greenArm"		),
		blueArm		("blueArm"		),
		cockpit		("cockpit"		),
		vent		("vent"			),
		propeller	("propeller"	),
		coupling	("coupling"		);

		private final int index;
		private final String unlocName;
		private final String desc;
		
		private Components(String name)
		{
			this(name, "");
		}
		
		private Components(String name, String des)
		{
			this.index = ItemComponent.inc++;
			this.unlocName = name;
			this.desc = des;
		}
		
		public static int getSize()
		{
			return values().length;
		}

		public int getIndex()
		{
			return index;
		}
		
		public String getUnlocName()
		{
			return unlocName;
		}
		
		public String getDesc()
		{
			return desc;
		}

	}
}
