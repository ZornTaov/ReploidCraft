package zornco.reploidcraft.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import zornco.reploidcraft.ReploidCraft;

public class ItemBossDoor extends Item
{
    private Material doorMaterial;

    public ItemBossDoor(Material par2Material)
    {
        super();
        this.doorMaterial = par2Material;
        this.maxStackSize = 1;
		this.setCreativeTab(ReploidCraft.reploidTab);
    }

    /**
     * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
     * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
     */
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
        if (par7 != 1)
        {
            return false;
        }
        else
        {
            ++par5;
            Block var11;

            if (this.doorMaterial == Material.wood)
            {
                var11 = Blocks.wooden_door;
            }
            else
            {
                var11 = Blocks.iron_door;
            }

            if (par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack) && par2EntityPlayer.canPlayerEdit(par4, par5 + 1, par6, par7, par1ItemStack))
            {
                if (!var11.canPlaceBlockAt(par3World, par4, par5, par6))
                {
                    return false;
                }
                else
                {
                    int var12 = MathHelper.floor_double((double)((par2EntityPlayer.rotationYaw + 180.0F) * 4.0F / 360.0F) - 0.5D) & 3;
                    placeDoorBlock(par3World, par4, par5, par6, var12, var11);
                    --par1ItemStack.stackSize;
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static void placeDoorBlock(World par0World, int par1, int par2, int par3, int par4, Block par5Block)
    {
        byte var6 = 0;
        byte var7 = 0;

        if (par4 == 0)
        {
            var7 = 1;
        }

        if (par4 == 1)
        {
            var6 = -1;
        }

        if (par4 == 2)
        {
            var7 = -1;
        }

        if (par4 == 3)
        {
            var6 = 1;
        }

        int var8 = (par0World.isAirBlock(par1 - var6, par2, par3 - var7) ? 1 : 0) + (par0World.isAirBlock(par1 - var6, par2 + 1, par3 - var7) ? 1 : 0);
        int var9 = (par0World.isAirBlock(par1 + var6, par2, par3 + var7) ? 1 : 0) + (par0World.isAirBlock(par1 + var6, par2 + 1, par3 + var7) ? 1 : 0);
        boolean var10 = par0World.getBlock(par1 - var6, par2, par3 - var7) == par5Block || par0World.getBlock(par1 - var6, par2 + 1, par3 - var7) == par5Block;
        boolean var11 = par0World.getBlock(par1 + var6, par2, par3 + var7) == par5Block || par0World.getBlock(par1 + var6, par2 + 1, par3 + var7) == par5Block;
        boolean var12 = false;

        if (var10 && !var11)
        {
            var12 = true;
        }
        else if (var9 > var8)
        {
            var12 = true;
        }

        par0World.setBlock(par1, par2, par3, par5Block, par4, 2);
        par0World.setBlock(par1, par2 + 1, par3, par5Block, 8 | (var12 ? 1 : 0), 2);
        par0World.notifyBlocksOfNeighborChange(par1, par2, par3, par5Block);
        par0World.notifyBlocksOfNeighborChange(par1, par2 + 1, par3, par5Block);
    }
}
