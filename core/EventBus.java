package zornco.reploidcraftenv.core;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.items.IKeyBound;
import zornco.reploidcraftenv.items.ItemHPEnergy;
import zornco.reploidcraftenv.items.ItemTank;
import zornco.reploidcraftenv.sounds.Sounds;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**
 * Name and cast of this class are irrelevant
 */
public class EventBus
{

	public EventBus()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}


	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onMouseEvent(MouseEvent event)
	{
		if(FMLClientHandler.instance().getClient().currentScreen == null)
		{
			ItemStack buster = FMLClientHandler.instance().getClient().thePlayer.getHeldItem();
			if(buster != null && buster.getItem() != null && buster.getItem() instanceof IKeyBound)
			{

				if(GuiScreen.isShiftKeyDown())
				{
					int k = event.dwheel;
					if(k != 0)
					{
						if(k > 0)
						{
							((IKeyBound)buster.getItem()).doKeyBindingAction(FMLClientHandler.instance().getClient().thePlayer, buster, "up");
						}
						else
						{
							((IKeyBound)buster.getItem()).doKeyBindingAction(FMLClientHandler.instance().getClient().thePlayer, buster, "down");
						}
				        event.setCanceled(true);
					}
				}
			}
		}
	}

	public void onEntityDrop(LivingDropsEvent event) {
		EntityLivingBase victim = event.entityLiving;
		if(ReploidCraftEnv.rand.nextInt(64) == 0) {
			System.out.println("DING");
			if (victim instanceof EntityBlaze) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 1)));
			}
			if (victim instanceof EntityCreeper) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 3)));
			}
			if (victim instanceof EntityEnderman) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 4)));
			}
			if (victim instanceof EntityDragon) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 5)));
			}
			if (victim instanceof EntityGhast) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 6)));
			}
			if (victim instanceof EntityMagmaCube) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 7)));
			}
			if (victim instanceof EntityPigZombie) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 8)));
			}
			if (victim instanceof EntitySkeleton) {
				if(((EntitySkeleton)victim).getSkeletonType() == 1)
					event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 13)));
				else
					event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 9)));
			}
			if (victim instanceof EntitySlime) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 10)));
			}
			if (victim instanceof EntitySpider) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 11)));
			}
			if (victim instanceof EntityWither) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 12)));
			}
			if (victim instanceof EntityZombie) {
				event.drops.add(new EntityItem(victim.worldObj, victim.posX, victim.posY+0.2, victim.posZ, new ItemStack(ReploidCraftEnv.upgradeChip, 1, 14)));
			}
		}
	}

	@SubscribeEvent
	public void onEntityAttack(AttackEntityEvent event)
	{
		//if(event.target != null && event.entityLiving != null){
		//System.out.println(event.target != null ? event.target.getClass().toString():"null");
		//if(event.target instanceof EntityDragonPart) System.out.println(((EntityDragonPart)event.target).field_146032_b);
		//System.out.println(event.entityLiving != null ? event.entityLiving.getClass().toString():"null");
		//}
	}

	/**
	 * The key is the @ForgeSubscribe annotation and the cast of the Event you put in as argument.
	 * The method name you pick does not matter. Method signature is public void, always.
	 */
	@SubscribeEvent
	public void entityPickup(EntityItemPickupEvent event)
	{
		/*
		 * You can then proceed to read and change the Event's fields where possible
		 */
		if(event.entityLiving == null || event.item == null) return;
		EntityLivingBase playerEnt =  event.entityLiving;
		EntityItem item = event.item;
		/*
		 * Note this possibility to interrupt certain (not all) events
		 */
		/*if (event.isCancelable())
		{
			event.setCanceled(true);
		}*/
		if(item.getEntityItem().getItem() == ReploidCraftEnv.healthBit 
				|| item.getEntityItem().getItem() == ReploidCraftEnv.healthByte 
				//|| item.getEntityItem().getItem() == ReploidCraft.weaponBit 
				//|| item.getEntityItem().getItem() == ReploidCraft.weaponByte 
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
		if(item.getEntityItem().getItem() == ReploidCraftEnv.healthBit)// || item.getEntityItem().getItem() == ReploidCraft.weaponBit )
			return 3;
		else if(item.getEntityItem().getItem() == ReploidCraftEnv.healthByte)// || item.getEntityItem().getItem() == ReploidCraft.weaponByte )
			return 6;
		else 
			return 0;
	}
}
