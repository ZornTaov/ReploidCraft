package zornco.reploidcraftenv.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.ArmorParts.PartSlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRideArmorPart extends Item
{
	@SideOnly(Side.CLIENT)
	private IIcon[] partIcon;
	private int typeAmmount = PartSlot.values().length;// * PartType.values().length;
    public ItemRideArmorPart()
    {
    	super();
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.maxStackSize = 1;
		this.setCreativeTab(ReploidCraftEnv.reploidTab);
    }

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
	 * different names based on their damage or NBT.
	 */
	/*public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, typeAmmount);
		return super.getUnlocalizedName()
				+ "." + PartName.values()[par1ItemStack.getItemDamage()%PartName.getSize()].toString()
				+ "." + PartType.values()[par1ItemStack.getItemDamage()/PartType.getSize()].toString();
	}*/


	@SuppressWarnings({ "rawtypes", "unchecked" })
    @SideOnly(Side.CLIENT)

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    	String desc1 = PartSlot.values()[par1ItemStack.getItemDamage()%PartSlot.getSize()].toString();
    	if(!desc1.equals(""))
        par3List.add(desc1);

    	/*String desc2 = PartType.values()[par1ItemStack.getItemDamage()/PartType.getSize()].toString();
    	if(!desc2.equals(""))
        par3List.add(desc2);*/
    }


	@SuppressWarnings({ "rawtypes", "unchecked" })
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
		return this.partIcon[var2];
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
       /* this.partIcon = new IIcon[typeAmmount];
        int k = 0;
        for (int i = 0; i < PartSlot.values().length; ++i)
        {
        	for (int j = 0; j < PartType.getSize(); j++) {
        		if(j != PartType.EMPTY.ordinal())
        		{
        			this.partIcon[k] = par1IconRegister.registerIcon(ReploidCraftEnv.MOD_ID+":"+PartSlot.values()[i]+"."+PartType.values()[j]);
        			k++;
        		}
			}
        }*/
    }
}