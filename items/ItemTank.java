package zornco.reploidcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import zornco.reploidcraft.sounds.Sounds;
import zornco.reploidcraft.ReploidCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTank extends Item {

	private IIcon iconTank;
	public ItemTank() {
		super();
		setMaxDamage(30);
		this.canRepair = false;
		this.setCreativeTab(ReploidCraft.reploidTab);
	}

	public ItemStack onItemRightClick(ItemStack is, World par2World, EntityPlayer player)
	{

		if (!par2World.isRemote)
		{
		//if playerHP is full, return false
		//else heal player till full and remove difference from tank
		while(player.getHealth() != player.getMaxHealth())
		{
			if( is.getItemDamage() == is.getMaxDamage())
				break;
			par2World.playSoundAtEntity(player, Sounds.BYTE, 1.0F, 1.0F);
			is.setItemDamage(is.getItemDamage() + 1);
			player.heal(1);
		}

		}
		return is;
	}
	public static void setType(ItemStack is, String t) {
		NBTTagCompound tag = initTags(is);
		tag.setString("tanktype", t);
	}

	public static String getType(ItemStack is) {
		NBTTagCompound tag = initTags(is);
		return tag.getString("tanktype");
	}

	public boolean getShareTag()
	{
		return true;
	}
	
	public static NBTTagCompound initTags(ItemStack stack) {
		NBTTagCompound tag = stack.getTagCompound();

		if (tag == null)
		{
			tag = new NBTTagCompound();
			stack.setTagCompound(tag);
			tag.setString("tanktype", "");
		}

		return tag;
	}

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
            this.iconTank = par1IconRegister.registerIcon(ReploidCraft.MOD_ID+":"+this.getUnlocalizedName().substring(5));
    }

    @SideOnly(Side.CLIENT)

    /**
     * Gets an icon index based on an item's damage value
     */
    public IIcon getIconFromDamage(int par1)
    {
        return this.iconTank;
    }
	public ItemStack getContainerItemStack(ItemStack itemStack)
	{
		ItemStack rIS = new ItemStack(getContainerItem());
		rIS.setItemDamage(itemStack.getItemDamage() + 1);
		return rIS;
	}
}
