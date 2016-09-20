package org.zornco.reploidcraft.item;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.client.render.ModelReploidBlue;
import org.zornco.reploidcraft.init.RCItems;
import org.zornco.reploidcraft.proxy.ClientProxy;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBasicReploidArmor extends ItemArmor implements IColorable {

	public ItemBasicReploidArmor(EntityEquipmentSlot equipmentSlotIn) {
		super(ItemArmor.ArmorMaterial.IRON, 0, equipmentSlotIn);
		this.setCreativeTab(ReploidCraft.reploidCraftTab);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default){
		//TODO: change this to have custom render, boo to boxes!
		if (entityLiving instanceof AbstractClientPlayer) {

            String s = ((AbstractClientPlayer)entityLiving).getSkinType();
            //ReploidCraft.logger.info(s);
		}
		ModelBiped armorModel = ClientProxy.armorModels.get(this);
		//ModelBiped armorModel = new ModelReploidBlue((this == RCItems.basicReploidArmorLegs)?0.05F:0.1F);
		if(armorModel != null){
			armorModel.setModelAttributes(_default);

			armorModel.bipedHead.showModel = armorSlot == EntityEquipmentSlot.HEAD;
			armorModel.bipedBody.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			armorModel.bipedRightArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			armorModel.bipedLeftArm.showModel = armorSlot == EntityEquipmentSlot.CHEST;
			armorModel.bipedRightLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
			armorModel.bipedLeftLeg.showModel = armorSlot == EntityEquipmentSlot.LEGS || armorSlot == EntityEquipmentSlot.FEET;
			
			return armorModel;
		}
		return super.getArmorModel(entityLiving, itemStack, armorSlot, _default);
	}

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
    {

        if (this == RCItems.basicReploidArmorLegs)
        {
            return "reploidcraft:textures/armor/reploid_layer_2" + ((type != null && type.equals("overlay"))?"_overlay":"") + ".png";
        }
        else 
        {
            return "reploidcraft:textures/armor/reploid_layer_1" + ((type != null && type.equals("overlay"))?"_overlay":"") + ".png";
        }
    }
    
    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {
    	if (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() instanceof IColorable && !(player.getHeldItemMainhand().getItem() instanceof ItemBasicReploidArmor)) {
			if (getColorIndex(itemStack) != ((IColorable)player.getHeldItemMainhand().getItem()).getColorIndex(player.getHeldItemMainhand())) {
				setColorIndex(itemStack, ((IColorable)player.getHeldItemMainhand().getItem()).getColorIndex(player.getHeldItemMainhand()));
			}
		}
    }
    @Override
    public int getColor(ItemStack stack) {
    	

        int[] aint = new int[3];
        int i = 0;
        int j = 0;
        float[] afloat = ((IColorable)stack.getItem()).getColorF3(stack);
        int l1 = (int)(afloat[0] * 255.0F);
        int i2 = (int)(afloat[1] * 255.0F);
        int j2 = (int)(afloat[2] * 255.0F);
        i += Math.max(l1, Math.max(i2, j2));
        aint[0] += l1;
        aint[1] += i2;
        aint[2] += j2;
        ++j;
        int i1 = aint[0] / j;
        int j1 = aint[1] / j;
        int k1 = aint[2] / j;
        float f3 = (float)i / (float)j;
        float f4 = (float)Math.max(i1, Math.max(j1, k1));
        i1 = (int)((float)i1 * f3 / f4);
        j1 = (int)((float)j1 * f3 / f4);
        k1 = (int)((float)k1 * f3 / f4);
        int lvt_12_3_ = (i1 << 8) + j1;
        lvt_12_3_ = (lvt_12_3_ << 8) + k1;
        return lvt_12_3_-1;
    }
    @Override
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
    @Override
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

        return 0;
    }
    @Override
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
}
