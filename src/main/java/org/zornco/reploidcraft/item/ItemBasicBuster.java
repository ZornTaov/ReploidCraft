package org.zornco.reploidcraft.item;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.zornco.reploidcraft.ReploidCraft;

import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemBasicBuster extends Item implements IKeyBound, IColorable {
	protected int capacity;
	private int maxReceive;
	private int maxExtract;
	public ItemBasicBuster()
	{
		super();
		this.setMaxStackSize(1);
		capacity = 200000;
		maxReceive = 2000;
		maxExtract = 0;
		this.setCreativeTab(ReploidCraft.reploidCraftTab);
	}

	/**
	 * Called when the player stops using an Item (stops holding the right mouse button).
	 */
	public void onPlayerStoppedUsing(ItemStack stack, World worldIn, EntityLivingBase entityLiving, int timeLeft)
	{
		if (entityLiving instanceof EntityPlayer)
		{

			EntityPlayer entityplayer = (EntityPlayer)entityLiving;
			if (entityplayer.isSneaking()) {
				//debugging
				//((IEnergyContainerItem)stack.getItem()).receiveEnergy(stack, maxReceive, false);
			}

			int i = this.getMaxItemUseDuration(stack) - timeLeft;
			
			int cost = 1000;
			cost *= 1.5f;
			//not ready yet
			/*int energy = ((IEnergyContainerItem)stack.getItem()).getEnergyStored(stack);
                if (cost > energy) {
                    entityplayer.addChatComponentMessage(new TextComponentString(TextFormatting.RED + "Not enough energy to shoot!"));
                    return;
                }
                ((IEnergyContainerItem)stack.getItem()).extractEnergy(stack, cost, false);
			 */float f = getShotSpeed(i);

			 if ((double)f >= 0.1D)
			 {

				 if (!worldIn.isRemote)
				 {
					 ItemArrow itemarrow = (ItemArrow)( Items.ARROW );
					 EntityArrow entityarrow = itemarrow.createArrow(worldIn, new ItemStack(itemarrow), entityplayer);
					 entityarrow.setAim(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

					 if (f == 1.0F)
					 {
						 entityarrow.setIsCritical(true);
					 }

					 //stack.damageItem(1, entityplayer);

					 /*if (flag1)
                        {
                            entityarrow.canBePickedUp = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }*/

					 worldIn.spawnEntity(entityarrow);
				 }

				 worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

				 //entityplayer.addChatComponentMessage(new TextComponentString(((IEnergyContainerItem)stack.getItem()).getEnergyStored(stack)+""));
				 entityplayer.addStat(StatList.getObjectUseStats(this));
			 }
		}
	}


	public static float getShotSpeed(int p_185059_0_)
	{
		float f = (float)p_185059_0_ / 20.0F;
		f = (f * f + f * 2.0F) / 3.0F;

		if (f > 1.0F)
		{
			f = 1.0F;
		}

		return f;
	}

	/**
	 * How long it takes to use or consume an item
	 */
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 72000;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack stack)
	{
		return EnumAction.BOW;
	}

	public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
	{
			playerIn.setActiveHand(hand);
			return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
		
	}

	@Override
	public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, byte key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doMouseAction(EntityPlayer thePlayer, ItemStack itemStack, int key) {
        if (itemStack.getTagCompound() == null) {
        	itemStack.setTagCompound(new NBTTagCompound());
        }
        int i = (getColorIndex(itemStack)+(key>0?1:-1))%16;
        if(i<0)i += 16;
        setColorIndex(itemStack, i);
		//thePlayer.addChatMessage(new TextComponentString(key+" "+getColorIndex(itemStack)));
	}
    /**
     * Return the color for the specified buster ItemStack.
     */
    public float[] getColorF3(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null)
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3))
            {
                return EntitySheep.getDyeRgb(EnumDyeColor.byDyeDamage(nbttagcompound1.getInteger("color")));
            }
        }

        return new float[]{1.0F, 1.0F, 1.0F};
    }
    /**
     * Return the color for the specified buster ItemStack.
     */
    public int getColorIndex(ItemStack stack)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound != null)
        {
            NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

            if (nbttagcompound1 != null && nbttagcompound1.hasKey("color", 3))
            {
                return nbttagcompound1.getInteger("color");
            }
        }

        return 15;
    }

    /**
     * Sets the color of the specified buster ItemStack
     */
    public void setColorIndex(ItemStack stack, int color)
    {
        NBTTagCompound nbttagcompound = stack.getTagCompound();

        if (nbttagcompound == null)
        {
            nbttagcompound = new NBTTagCompound();
            stack.setTagCompound(nbttagcompound);
        }

        NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("display");

        if (!nbttagcompound.hasKey("display", 10))
        {
            nbttagcompound.setTag("display", nbttagcompound1);
        }

        nbttagcompound1.setInteger("color", color);
    }
	//snipped from mcjty's rftools, since it seems the "norm" for items with energy
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean whatIsThis) {
		super.addInformation(itemStack, player, list, whatIsThis);
		NBTTagCompound tagCompound = itemStack.getTagCompound();
		if (tagCompound != null) {
			list.add(TextFormatting.AQUA + "Energy: " + tagCompound.getInteger("Energy") + " RF");
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
			list.add(I18n.format("item.simpleBuster.use.shown"));
		} else {
			list.add(TextFormatting.WHITE + I18n.format("item.simpleBuster.use.hidden"));
		}
	}
	//not ready yet
	/*@Override
    public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
        if (container.getTagCompound() == null) {
            container.setTagCompound(new NBTTagCompound());
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));

        if (!simulate) {
            energy += energyReceived;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
    	if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
            return 0;
        }
        int energy = container.getTagCompound().getInteger("Energy");
        int energyExtracted = Math.min(energy, maxExtract);

        if (!simulate) {
            energy -= energyExtracted;
            container.getTagCompound().setInteger("Energy", energy);
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored(ItemStack container) {
        if (container.getTagCompound() == null || !container.getTagCompound().hasKey("Energy")) {
            return 0;
        }
        return container.getTagCompound().getInteger("Energy");
    }

    @Override
    public int getMaxEnergyStored(ItemStack container) {
        return capacity;
    }*/
}
