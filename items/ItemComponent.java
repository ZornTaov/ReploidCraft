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

	 /** List of dye color names */
    public static final String[] componentNames = new String[] {"Diamond Dust", "AEGD", "Armored Fist", "Spiked Fist", "Red Arm", "Green Arm", "Blue Arm", "Cockpit", "Vent", "Propeller"};
    public static final String[][] componentDesc = new String[][] {{"",""}, {"Accumulative Energy", "Generation Device"}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}, {"",""}};
    public static final String[] componentIconName = new String[] {"diamond_dust", "AEGD", "fist", "spikedfist", "redarm", "greenarm", "bluearm", "cockpit", "vent", "propeller"};
	public static final int[] chipColors = new int[] {1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 2651799, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320};
    public static final int typeAmmount = 10;
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
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, typeAmmount);
		return super.getUnlocalizedName() + "." + componentNames[i];
	}

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	String desc1 = ItemComponent.componentDesc[par1ItemStack.getItemDamage()][0];
    	if(!desc1.equals(""))
        par3List.add(desc1);

    	String desc2 = ItemComponent.componentDesc[par1ItemStack.getItemDamage()][1];
    	if(!desc2.equals(""))
        par3List.add(desc2);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@SideOnly(Side.CLIENT)

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int var4 = 0; var4 < typeAmmount; ++var4)
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
        int var2 = MathHelper.clamp_int(par1, 0, typeAmmount);
		return this.componentIcon[var2];
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.componentIcon = new IIcon[componentIconName.length];

        for (int i = 0; i < componentIconName.length; ++i)
        {
            this.componentIcon[i] = par1IconRegister.registerIcon(ReploidCraft.MOD_ID+":"+componentIconName[i]);
        }
    }

}
