package org.zornco.reploidcraft.item;

import java.util.List;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.init.RCSounds;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEnergyPelletBase extends Item {
	
	public ItemEnergyPelletBase() {

		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(ReploidCraft.reploidCraftTab);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {

		if (!worldIn.isRemote)
		{

			playerIn.addPotionEffect(new PotionEffect(MobEffects.heal, 1));
		}
		switch(itemStackIn.getMetadata())
		{
		case 0:
		case 1:
			worldIn.playSound((EntityPlayer)playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, RCSounds.SOUNDBIT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + itemStackIn.getMetadata() * 0.25F);
			
			break;
		case 2:
		case 3:
			worldIn.playSound((EntityPlayer)playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, RCSounds.SOUNDBYTE, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + itemStackIn.getMetadata()/2.0F * 0.25F);
			break;
		}

		if (!playerIn.capabilities.isCreativeMode)
        {
			--itemStackIn.stackSize;

            if (itemStackIn.stackSize == 0)
            {
            	playerIn.inventory.deleteStack(itemStackIn);
            }
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }
        else
        {
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        }
	}
	
    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     */
    public String getUnlocalizedName(ItemStack stack)
    {
        int i = stack.getMetadata();
        return super.getUnlocalizedName() + "." + EnumEnergyPelletStr.byMetadata(i).getName();
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems)
    {
        for (int i = 0; i < 4; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack)
    {
        return true;
    }
    
    public boolean hasColor(ItemStack stack)
    {
    	return true;
    }

	public int getColor(ItemStack stack) {
		// TODO Auto-generated method stub
		return 280;//152;//hue, rgb=42584;
	}
}
