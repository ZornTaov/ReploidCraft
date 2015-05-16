package zornco.reploidcraft.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Facing;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.entities.EntityFloatingPlatform;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemPlatformPlacer extends Item {

	@SideOnly(Side.CLIENT)
	private IIcon platformSpawnerIcon;

	public ItemPlatformPlacer()
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(ReploidCraft.reploidTab);
	}
	/**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par3World.isRemote)
        {
            return true;
        }
        else
        {
            Block var11 = par3World.getBlock(par4, par5, par6);
            par4 += Facing.offsetsXForSide[par7];
            par5 += Facing.offsetsYForSide[par7];
            par6 += Facing.offsetsZForSide[par7];
            //double var12 = 0.0D;

            if (par7 == 1 && var11 != null && var11.getRenderType() == 11)
            {
                //var12 = 0.5D;
            }

            EntityFloatingPlatform platform = new EntityFloatingPlatform(par3World, (double)((float)par4 + 0.5F), (double)((float)par5 + 1.0F), (double)((float)par6 + 0.5F));
			platform.rotationYaw = 0;

			if (!par3World.getCollidingBoundingBoxes(platform, platform.boundingBox.expand(-0.1D, -0.1D, -0.1D)).isEmpty())
			{
				return false;
			}

			if (!par3World.isRemote)
			{
				par3World.spawnEntityInWorld(platform);
			}

			if (!par2EntityPlayer.capabilities.isCreativeMode)
			{
				--par1ItemStack.stackSize;
			}

            return true;
        }
    }

	@SideOnly(Side.CLIENT)

	/**
	 * Gets an icon index based on an item's damage value
	 */
	public IIcon getIconFromDamage(int par1)
	{
		return this.platformSpawnerIcon;
    }

	@Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister par1IconRegister)
    {
        this.platformSpawnerIcon = par1IconRegister.registerIcon(ReploidCraft.MOD_ID+":"+this.getUnlocalizedName().substring(5));
    }
}
