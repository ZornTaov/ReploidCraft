package zornco.reploidcraftenv.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.bullets.EntityMetBullet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBuster extends Item implements IKeyBound
{
	public ItemBuster()
	{
		super();
		this.maxStackSize = 1;
		this.setCreativeTab(ReploidCraftEnv.reploidTab);
	}
	/*@SideOnly(Side.CLIENT)
	int getIndex() {
		return this.iconIndex;
	}
	@SideOnly(Side.CLIENT)
	public String getTextureFile() {
		return "/zornco/megax/textures/XBusterDetailed.png";
	}*/
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses()
	{
		return true;
	}
	/*@SideOnly(Side.CLIENT)
	*//**
	 * Gets an icon index based on an item's damage value and the given render pass
	 *//*
	public int getIconFromDamageForRenderPass(int par1, int par2)
	{
		return par2 == 1 ? this.iconIndex + 1 : super.getIconFromDamageForRenderPass(par1, par2);
	}*/
	@Override
	public EnumAction getItemUseAction(ItemStack itemstack) {
		//return MegaX.busterAction;
		return EnumAction.bow;
	}
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
		return par1ItemStack;
	}
	public int ticksCharged = 0;
	public boolean stopUp = true;
	public boolean stopCont = true;
	@Override
	/**
	 * Called each tick while using an item.
	 * @param stack The Item being used
	 * @param player The Player using the item
	 * @param count The amount of time in tick the item has been used for continuously
	 */
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{

		if(true)// !player.worldObj.isRemote)
		{//System.out.println(count);
			if(count == getMaxItemUseDuration(stack) && stopUp)
			{
				ReploidCraftEnv.proxy.playChargeUpSound(player);
				this.stopCont = false;
			}
			
			else if(count <= getMaxItemUseDuration(stack)-35 && stopCont)
			{
				this.stopCont = false;
				ReploidCraftEnv.proxy.playChargeContSound(player);
			}
		}
	}
	public int getMaxItemUseDuration(ItemStack itemstack) 
	{
		return 72000;
	}
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int slot, boolean isSelected)
	{
	}
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
		int useTime = this.getMaxItemUseDuration(par1ItemStack) - par4;
		ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, useTime);
		MinecraftForge.EVENT_BUS.post(event);
		if (event.isCanceled())
		{
			return;
		}
		float chargeLevel = (float)event.charge / 20.0F;
		chargeLevel = (chargeLevel * chargeLevel + chargeLevel * 2.0F) / 3.0F;
		if ((double)chargeLevel < 0.01D)
		{
			return; //don't fire, didn't hold it long enough
		}
		if (chargeLevel > 2.0F)
		{
			chargeLevel = 2.0F; // max charge
		}
		//System.out.println("Pew! " + chargeLevel);
		EntityMetBullet lemon = new EntityMetBullet(par2World, par3EntityPlayer); // entity shot
		if (chargeLevel >= 0.50F && chargeLevel < 2.0F)
		{
			lemon.setTypeOfAttack(1);
		}
		if (chargeLevel == 2.0F)
		{
			lemon.setTypeOfAttack(2); //crit attack
		}
		lemon.canBePickedUp = 2;
		par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + chargeLevel * 0.5F);
		if (!par2World.isRemote)
		{
			par2World.spawnEntityInWorld(lemon);
			this.stopUp = true;
			this.stopCont = true;
		}
	}
	@Override
	public ItemStack onEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) 
	{
		return itemstack;
	}
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
	{
		return true;
	}
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		return true;
	}
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		return true;
	}
	@Override
	public void doKeyBindingAction(EntityPlayer thePlayer, ItemStack itemStack, String keyBinding) 
	{
		if (!itemStack.hasTagCompound())
		{
			itemStack.setTagCompound(new NBTTagCompound());
		}
		if (keyBinding.equals("up")) 
		{
			if (getWeaponType(itemStack) == 8) 
			{
				setWeaponType(itemStack, 0);
			}
			else 
			{
				setWeaponType(itemStack, getWeaponType(itemStack)+1);
			}
		}
		else if (keyBinding.equals("down")) 
		{
			if (getWeaponType(itemStack) == 0) 
			{
				setWeaponType(itemStack, 8);
			}
			else 
			{
				setWeaponType(itemStack, getWeaponType(itemStack)-1);
			}
		}
		//thePlayer.addChatMessage(new ChatComponentText("" + getWeaponType(itemStack)));
	}
	
	private void setWeaponType(ItemStack itemStack, int num) 
	{
		itemStack.getTagCompound().setByte("wSlot", (byte)num);
	}
	private byte getWeaponType(ItemStack itemStack) 
	{
		return itemStack.getTagCompound().getByte("wSlot");
	}
	private void openWeaponMenu(EntityPlayer thePlayer) 
	{
		
	}
	
	
}