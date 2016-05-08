package org.zornco.reploidcraft.item;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.zornco.reploidcraft.ReploidCraft;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
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
import net.minecraft.world.World;

public class ItemSimpleBuster extends Item {
	protected int capacity;
    private int maxReceive;
    private int maxExtract;
	public ItemSimpleBuster()
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
            boolean flag = entityplayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.infinity, stack) > 0;

            int i = this.getMaxItemUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, (EntityPlayer)entityLiving, i, flag);
            if (i < 0) return;
            
            if (flag)
            {
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
                        ItemArrow itemarrow = (ItemArrow)( Items.arrow );
                        EntityArrow entityarrow = itemarrow.makeTippedArrow(worldIn, new ItemStack(itemarrow), entityplayer);
                        entityarrow.func_184547_a(entityplayer, entityplayer.rotationPitch, entityplayer.rotationYaw, 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F)
                        {
                            entityarrow.setIsCritical(true);
                        }

                        //stack.damageItem(1, entityplayer);

                        /*if (flag1)
                        {
                            entityarrow.canBePickedUp = EntityArrow.PickupStatus.CREATIVE_ONLY;
                        }*/

                        worldIn.spawnEntityInWorld(entityarrow);
                    }

                    worldIn.playSound((EntityPlayer)null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.entity_arrow_shoot, SoundCategory.NEUTRAL, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    //entityplayer.addChatComponentMessage(new TextComponentString(((IEnergyContainerItem)stack.getItem()).getEnergyStored(stack)+""));
                    entityplayer.addStat(StatList.func_188057_b(this));
                }
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
        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemStackIn, worldIn, playerIn, hand, true);
        if (ret != null) return ret;

        if (!playerIn.capabilities.isCreativeMode )
        {
            return new ActionResult(EnumActionResult.PASS, itemStackIn);
        }
        else
        {
            playerIn.setActiveHand(hand);
            return new ActionResult(EnumActionResult.SUCCESS, itemStackIn);
        }
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
            list.add("pew pew");
        } else {
            list.add(TextFormatting.WHITE + "shift to see secret use");
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
