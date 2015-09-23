package zornco.reploidcraft.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.client.ClientProxy;
import zornco.reploidcraft.core.Config;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemReploidArmor extends ItemArmor
{

	public ItemReploidArmor(ArmorMaterial material, int render_idx, int type)
	{
		super(material, render_idx, type);

		this.setCreativeTab(ReploidCraft.reploidTab);
		// ItemStacks that store an NBT Tag Compound are limited to stack size of 1
		setMaxStackSize(1);
	}

	// Without this method, your inventory will NOT work!!!
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 1; // return any value greater than zero
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		// only want to check on server - the client gui will be opened automatically by the gui handler
		if (!world.isRemote) {
			// you may or may not want to check if the player is sneaking - up to you
			if (player.isSneaking()) {
				// open the inventory:
				player.openGui(ReploidCraft.instance, Config.GUI_ARMOR_INV, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
			}
		}
		return stack;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister){
		this.itemIcon = iconRegister.registerIcon(ReploidCraft.MOD_ID+":"+this.getUnlocalizedName().substring(this.getUnlocalizedName().indexOf('.') + 1));
	}
	@Override
	@SideOnly(Side.CLIENT)
	public ModelBiped getArmorModel (EntityLivingBase entityLiving, ItemStack itemstack, int armorSlot){
		//TODO: change this to have custom render, boo to boxes!
		ModelBiped armorModel = ClientProxy.armorModels.get(this);
		if(armorModel != null){
			armorModel.bipedHead.showModel = armorSlot == 0;
			armorModel.bipedHeadwear.showModel = false;
			armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
			armorModel.bipedRightArm.showModel = armorSlot == 1;
			armorModel.bipedLeftArm.showModel = armorSlot == 1;
			armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
			armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
			armorModel.isSneak = entityLiving.isSneaking();
			armorModel.isRiding = entityLiving.isRiding();
			armorModel.isChild = entityLiving.isChild();
			armorModel.heldItemRight = 0;
			armorModel.aimedBow = false;
			EntityPlayer player = (EntityPlayer)entityLiving;
			ItemStack held_item = player.getEquipmentInSlot(0);
			if (held_item != null){
				armorModel.heldItemRight = 1;
				if (player.getItemInUseCount() > 0){
					EnumAction enumaction = held_item.getItemUseAction();
					if (enumaction == EnumAction.bow){
						armorModel.aimedBow = true;
					}else if (enumaction == EnumAction.block){
						armorModel.heldItemRight = 3;
					}
				}
			}
		}
		return armorModel;
	}
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String layer){
		//TODO: make this have different armor colors, go all 16 maybe?
		String name = this.getUnlocalizedName().substring(5);
		name = name.substring(0, name.indexOf('_'));
		return ReploidCraft.MOD_ID + ":textures/armor/" + name + "_layer_" + (slot == 2 ? 2 : 1) + ".png";
	}
}	