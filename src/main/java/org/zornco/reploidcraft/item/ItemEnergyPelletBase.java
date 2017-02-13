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
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
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
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
    ItemStack itemStackIn = playerIn.getHeldItem(handIn);
		int toHeal = EnumEnergyPelletStr.byMetadata(itemStackIn.getMetadata()).getStrength();
		if (playerIn.getHealth() == playerIn.getMaxHealth()) {

			return new ActionResult(EnumActionResult.FAIL, itemStackIn);
		}
		//if (!worldIn.isRemote)
		{
			while(playerIn.getHealth() < playerIn.getMaxHealth() && toHeal > 0)
			{
				/*if( itemStackIn.getItemDamage() == itemStackIn.getMaxDamage())
					break;*/


				//itemStackIn.setItemDamage(itemStackIn.getItemDamage() + 1);
				playerIn.heal(1);
				--toHeal;
			}
		}
		switch(itemStackIn.getMetadata())
		{
		case 0:
		case 1:
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, RCSounds.SOUNDBIT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + itemStackIn.getMetadata() * 0.25F);

			break;
		case 2:
		case 3:
			worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, RCSounds.SOUNDBYTE, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + itemStackIn.getMetadata()/2.0F * 0.25F);
			break;
		}
		if (!playerIn.capabilities.isCreativeMode)
		{
			itemStackIn.shrink(1);
			
			playerIn.addStat(StatList.getObjectUseStats(this));
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		}
		else
		{
			return new ActionResult(EnumActionResult.PASS, itemStackIn);
		}
	}

	/**
	 * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
	 * different names based on their damage or NBT.
	 */
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		int i = stack.getMetadata();
		return super.getUnlocalizedName() + "." + EnumEnergyPelletStr.byMetadata(i).getName();
	}

	/**
	 * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		for (int i = 0; i < 4; ++i)
		{
			subItems.add(new ItemStack(itemIn, 1, i));
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack)
	{
		return true;
	}

	public int getColor(ItemStack stack) {
		// TODO Auto-generated method stub
		return 280;//152;//hue, rgb=42584;
	}
}
