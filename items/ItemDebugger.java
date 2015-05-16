package zornco.reploidcraft.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.blocks.TileEntityMechBay;
import zornco.reploidcraft.blocks.TileEntityMechBayEnergy;
import zornco.reploidcraft.entities.EntityRideArmor;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDebugger extends Item {

	public ItemDebugger() {
		super();
		this.setCreativeTab(ReploidCraft.reploidTab);
	}

	/**
	 * Callback for item usage. If the item does something special on right
	 * clicking, he will have one of those. Return True if something happen and
	 * false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		/*
		 * BedCraftBeyond.logger.info(""+par4+" "+par5+" "+par6);
		 * BedCraftBeyond.logger.info(""+(par3World.getClass().toString()));
		 * BedCraftBeyond.logger.info(""+par3World.getBlockId(par4, par5, par6));
		 */
		par2EntityPlayer.addChatMessage(new ChatComponentText(FMLCommonHandler.instance().getEffectiveSide()+""));
		par2EntityPlayer.addChatMessage(new ChatComponentText("" + par3World.getBlockMetadata(par4, par5, par6)));
		TileEntity tile = par3World.getTileEntity(par4, par5, par6);

		ReploidCraft.logger.info("" + tile.getClass());
		if (tile != null) {
			if (tile instanceof TileEntityMechBayEnergy) {
				TileEntityMechBay tilebed = (TileEntityMechBay) tile;
				par2EntityPlayer.addChatMessage(new ChatComponentText(tilebed.hasMaster() + "2"));
				par2EntityPlayer.addChatMessage(new ChatComponentText(tilebed.getMasterX() + " " + tilebed.getMasterY() + " " + tilebed.getMasterZ()));
			}
			if (tile instanceof TileEntityMechBay) {
				TileEntityMechBay tilebed = (TileEntityMechBay) tile;
				par2EntityPlayer.addChatMessage(new ChatComponentText(tilebed.hasMaster() + "1"));
				par2EntityPlayer.addChatMessage(new ChatComponentText(tilebed.getMasterX() + " " + tilebed.getMasterY() + " " + tilebed.getMasterZ()));
				if(tilebed.hasMaster()&&tilebed.isMaster())
					par2EntityPlayer.addChatMessage(new ChatComponentText(tilebed.hasRide()+""));
				else if(tilebed.hasMaster())
					par2EntityPlayer.addChatMessage(new ChatComponentText(tilebed.getMaster().hasRide()+""));
			}
		}

		return true;

	}
	@Override
	public boolean itemInteractionForEntity(ItemStack par1, EntityPlayer par2, EntityLivingBase par3)
	{
		if(par3 instanceof EntityRideArmor)
		{
			EntityRideArmor ride = (EntityRideArmor)par3;

			par2.addChatMessage(new ChatComponentText(FMLCommonHandler.instance().getEffectiveSide()+""));
			par2.addChatMessage(new ChatComponentText(ride.getMobType() + (ride.riddenByEntity != null?" " + ride.riddenByEntity.getClass().toString().substring(ride.riddenByEntity.getClass().toString().indexOf("Entity")) + " " + ride.getHealth():" ")));
		}
		return true;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon("bedcraftbeyond:scissors");
	}
}
