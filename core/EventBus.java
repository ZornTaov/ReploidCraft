package zornco.reploidcraftenv.core;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.items.ItemHPEnergy;
import zornco.reploidcraftenv.items.ItemTank;
import zornco.reploidcraftenv.sounds.Sounds;
/**
 * Name and cast of this class are irrelevant
 */
public class EventBus
{

	public EventBus()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}

	/**
	 * The key is the @ForgeSubscribe annotation and the cast of the Event you put in as argument.
	 * The method name you pick does not matter. Method signature is public void, always.
	 */
	@ForgeSubscribe
	public void entityAttacked(EntityItemPickupEvent event)
	{
		/*
		 * You can then proceed to read and change the Event's fields where possible
		 */
		if(event.entityLiving == null || event.item == null) return;
		EntityLiving playerEnt = event.entityLiving;
		EntityItem item = event.item;
		/*
		 * Note this possibility to interrupt certain (not all) events
		 */
		/*if (event.isCancelable())
		{
			event.setCanceled(true);
		}*/
		if(item.getEntityItem().itemID == ReploidCraftEnv.healthBit.itemID 
				|| item.getEntityItem().itemID == ReploidCraftEnv.healthByte.itemID 
				//|| item.getEntityItem().itemID == ReploidCraft.weaponBit.shiftedIndex 
				//|| item.getEntityItem().itemID == ReploidCraft.weaponByte.shiftedIndex 
				)
		{
			if(event.entityLiving instanceof EntityPlayerMP && !playerEnt.isSneaking())
			{
				ItemHPEnergy bit = (ItemHPEnergy) item.getEntityItem()
						.getItem();
				switch (bit.type) {
				case 0:
					playerEnt.worldObj.playSoundAtEntity(playerEnt,
							Sounds.BIT, 1.0F, 1.0F);
					break;
				case 1:
					playerEnt.worldObj.playSoundAtEntity(playerEnt,
							Sounds.BYTE, 1.0F, 1.0F);
					break;
				}
				if (event.entityLiving.getHealth() == event.entityLiving.getMaxHealth())
				{
					processBit((EntityPlayerMP)event.entityLiving, item);
				}
				else {
					bit.applyEffect(playerEnt, item.getEntityItem().stackSize);
				}

				item.getEntityItem().stackSize = 0;
				item.setDead();
				return;
			}
		}
		/*
		 * Events may offer further fields and methods. Just read them, it should be obvious.
		 */
	}
	public void processBit(EntityPlayerMP player, EntityItem item)
	{
		for (int i = 0; i < 36; i++)
		{
			ItemStack is = player.inventory.getStackInSlot(i);

			if (is == null) {
				continue;
			}
			if (is.getItem().equals(ReploidCraftEnv.healthTank)) {
				if (ItemTank.getType(is).isEmpty())
				{
					ItemTank.setType(is, "HP");
					is.setItemDamage(is.getItemDamage() <= 0 ? 0 : is.getItemDamage() - item.getEntityItem().stackSize*bitSize(item));
					break;
				}
				if ((!ItemTank.getType(is).equals("HP")))
					continue;
				if (is.getItemDamage() == 0)
					continue;
				is.setItemDamage(is.getItemDamage() <= 0 ? 0 : is.getItemDamage() - item.getEntityItem().stackSize*bitSize(item));
				break;
			}
		}
	}
	private int bitSize(EntityItem item) {
		if(item.getEntityItem().itemID == ReploidCraftEnv.healthBit.itemID)// || item.getEntityItem().itemID == ReploidCraft.weaponBit.shiftedIndex )
			return 3;
		else if(item.getEntityItem().itemID == ReploidCraftEnv.healthByte.itemID)// || item.getEntityItem().itemID == ReploidCraft.weaponByte.shiftedIndex )
			return 6;
		else 
			return 0;
	}
}
