package zornco.reploidcraft.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.entities.armorParts.PartBase;
import zornco.reploidcraft.entities.armorParts.PartSlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemRideArmorPart extends Item
{
	@SideOnly(Side.CLIENT)
	private IIcon[] partIcon;
	private int typeAmmount = PartSlot.values().length;// * PartType.values().length;
	private static List<PartBase> partList = new ArrayList<PartBase>();;
	public ItemRideArmorPart()
	{
		super();
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.maxStackSize = 1;
		this.setCreativeTab(ReploidCraft.reploidTab);
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
	 * different names based on their damage or NBT.
	 */
	/*public String getUnlocalizedName(ItemStack par1ItemStack)
	{
		int i = MathHelper.clamp_int(par1ItemStack.getItemDamage(), 0, typeAmmount);
		return super.getUnlocalizedName()
				+ "." + partList.get(par1ItemStack.getItemDamage()).toString();
	}*/


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)

	/**
	 * allows items to add custom lines of information to the mouseover description
	 */
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
	{
		String t = getPartList().get(par1ItemStack.getItemDamage()).toString();
		String[] s = t.split("\\.");
		if(!s.equals(""))
		{
			String desc1 = "type." + s[0].toLowerCase();
			if(!desc1.equals("type."))
				t = (StatCollector.translateToLocal(desc1));

			String desc2 = "slot." + s[1].toLowerCase();
			if(!desc2.equals("slot."))
				t += " " + (StatCollector.translateToLocal(desc2));
		}
		par3List.add(t);
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int var4 = 6; var4 < typeAmmount; ++var4)
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
		this.partIcon = new IIcon[typeAmmount];
		for (int i = 0; i < typeAmmount; ++i)
		{
			this.partIcon[i] = par1IconRegister.registerIcon(ReploidCraft.MOD_ID+":"+getPartList().get(i).toString());
		}
	}
	public void populateParts()
	{
		typeAmmount = getPartList().size();
	}
	public static List<PartBase> getPartList() {
		return partList;
	}

	public static String getPartByMetadata(int meta)
	{
		return getPartList().get(meta) != null ? getPartList().get(meta).toString() : "";
	}
}