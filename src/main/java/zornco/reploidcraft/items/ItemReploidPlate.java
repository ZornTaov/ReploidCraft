package zornco.reploidcraft.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import zornco.reploidcraft.ReploidCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemReploidPlate extends Item
{

	/** List of dye color names */
	public static final String[] plateColorNames = new String[] { "Black", "Red", "Green", "Brown", "Blue", "Purple", "Cyan", "LightGray", "Gray", "Pink", "Lime", "Yellow", "LightBlue", "Magenta", "Orange", "Enhanced", "Basic" };
	public static final int[] chipColors = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 11250603, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320, 8943479 };

	private IIcon iconPlate;
	public static final int typeAmmount = 17;

	public ItemReploidPlate()
	{
		super();
		this.setHasSubtypes( true );
		this.setMaxDamage( 0 );
		this.setCreativeTab( ReploidCraft.reploidTab );
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have different names based on their damage or NBT.
	 */
	public String getUnlocalizedName( ItemStack par1ItemStack )
	{
		int i = MathHelper.clamp_int( par1ItemStack.getItemDamage(), 0, typeAmmount );
		return super.getUnlocalizedName() + "." + plateColorNames[i];
	}

	@Override
	@SideOnly( Side.CLIENT )
	public void registerIcons( IIconRegister par1IconRegister )
	{
		this.iconPlate = par1IconRegister.registerIcon( ReploidCraft.MOD_ID + ":reploidPlate" );
	}

	@SideOnly( Side.CLIENT )
	/**
	 * Gets an icon index based on an item's damage value
	 */
	public IIcon getIconFromDamage( int par1 )
	{
		return this.iconPlate;
	}

	@SuppressWarnings( {
		"rawtypes", "unchecked" } )
	@SideOnly( Side.CLIENT )
	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	public void getSubItems( Item par1, CreativeTabs par2CreativeTabs, List par3List )
	{
		for ( int var4 = 0; var4 < typeAmmount; ++var4 )
		{
			par3List.add( new ItemStack( par1, 1, var4 ) );
		}
	}

	public int getColorFromItemStack( ItemStack par1, int par2 )
	{
		/*if(par1 != 0)
		{
			//System.out.println(par1);
			//color = par1;
			switch (par2)
			{
			case 0:
				return this.sheetColor();

			case 1:
				return this.pillowColor();
			case 2:
				return this.woodFrameColor();

			}
		}
		System.out.println("getColorFromDamage par1 is not 0");
		return 0xFF00FF;*/
		return chipColors[par1.getItemDamage()];
	}
}
