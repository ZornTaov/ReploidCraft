package org.zornco.reploidcraft.item;

import java.util.List;

import org.lwjgl.input.Keyboard;
import org.zornco.reploidcraft.ReploidCraft;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemBasicBlade extends ItemSword implements IKeyBound, IColorable {
	protected int capacity;
	private int maxReceive;
	private int maxExtract;
    private final static Item.ToolMaterial material = Item.ToolMaterial.IRON;
	public ItemBasicBlade()
	{
		super(material);
		capacity = 200000;
		maxReceive = 2000;
		maxExtract = 0;
		this.setCreativeTab(ReploidCraft.reploidCraftTab);
	}

    /**
     * set max damage of an Item
     */
    public Item setMaxDamage(int maxDamageIn)
    {
        super.setMaxDamage(0);
        return this;
    }

    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {
        return true;
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
}
