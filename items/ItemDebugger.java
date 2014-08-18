package zornco.reploidcraftenv.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.TileEntityMechBay;
import zornco.reploidcraftenv.blocks.TileEntityMechBayEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDebugger extends Item {

	public ItemDebugger() {
		super();
	}

	/**
	 * Callback for item usage. If the item does something special on right clicking, he will have one of those. Return
	 * True if something happen and false if it don't. This is for ITEMS, not BLOCKS
	 */
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
		/*BedCraftBeyond.logger.info(""+par4+" "+par5+" "+par6);
		BedCraftBeyond.logger.info(""+(par3World.getClass().toString()));
		BedCraftBeyond.logger.info(""+par3World.getBlockId(par4, par5, par6));*/
		ReploidCraftEnv.logger.info(""+par3World.getBlockMetadata(par4, par5, par6));
		TileEntity tile = par3World.getTileEntity(par4, par5, par6);
		if (tile != null)
		{if(tile instanceof TileEntityMechBayEnergy)
		{
			TileEntityMechBay tilebed = (TileEntityMechBay)tile;
			ReploidCraftEnv.logger.info(tilebed.isMaster()+"2");
			ReploidCraftEnv.logger.info(tilebed.getMasterX() + " " + tilebed.getMasterY() + " " + tilebed.getMasterZ());
		}
			if(tile instanceof TileEntityMechBay)
			{
				TileEntityMechBay tilebed = (TileEntityMechBay)tile;
				ReploidCraftEnv.logger.info(tilebed.isMaster()+"1");
				ReploidCraftEnv.logger.info(tilebed.getMasterX() + " " + tilebed.getMasterY() + " " + tilebed.getMasterZ());
			}
		}
		else
		{
			return false;
		}
		/*if(par3World.getBlock(par4, par5-1, par6) ==  useblock)
		{
			if(par3World.getBlock(par4 - 1, par5, par6) ==  useblock)
			{
				if(par3World.getBlock(par4, par5, par6 - 4) ==  useblock && par3World.getBlock(par4, par5 - 1, par6 - 4) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 - j, par6) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 - j, par6 - 4) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - 3, par5 + 1 - j, par6 - 1 - i) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - i, par5 - 2, par6 - 1 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 - i, par5 - 1 + j, par6 - 1 - k)) 
								{
									return false;
								}
							}
						}
					}
					dir = ForgeDirection.EAST;
				}
				else if(par3World.getBlock(par4, par5, par6 + 4) ==  useblock && par3World.getBlock(par4, par5 - 1, par6 + 4) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 - j, par6) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 - j, par6 + 4) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - 3, par5 + 1 - j, par6 + 1 + i) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - i, par5 - 2, par6 + 1 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 - i, par5 - 1 + j, par6 + 1 + k)) 
								{
									return false;
								}
							}
						}
					}
					mirrored = true;
					dir = ForgeDirection.EAST;
				}
			}
			else if(par3World.getBlock(par4 + 1, par5, par6) ==  useblock)
			{
				if(par3World.getBlock(par4, par5, par6 + 4) ==  useblock && par3World.getBlock(par4, par5 - 1, par6 + 4) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 + 1 + i, par5 - j, par6) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 + 1 + i, par5 - j, par6 + 4) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + 3, par5 + 1 - j, par6 + 1 + i) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + i, par5 - 2, par6 + 1 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 + i, par5 - 1 + j, par6 + 1 + k)) 
								{
									return false;
								}
							}
						}
					}

					dir = ForgeDirection.WEST;
				}
				else if(par3World.getBlock(par4, par5, par6 - 4) ==  useblock && par3World.getBlock(par4, par5 - 1, par6 - 4) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 + 1 + i, par5 - j, par6) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 + i + i, par5 - j, par6 - 4) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + 3, par5 + 1 - j, par6 - 1 - i) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + i, par5 - 2, par6 - 1 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 + i, par5 - 1 + j, par6 - 1 - k)) 
								{
									return false;
								}
							}
						}
					}
					dir = ForgeDirection.WEST;
					mirrored = true;
				}
			}
			else if(par3World.getBlock(par4, par5, par6 + 1) ==  useblock)
			{
				if(par3World.getBlock(par4 - 4, par5, par6) ==  useblock && par3World.getBlock(par4 - 4, par5 - 1, par6) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4, par5 - i, par6 + 1 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 - 4, par5 - i, par6 + 1 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 + 1 - j, par6 + 3) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 - 2, par6 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 - 1 - i, par5 - 1 + j, par6 + k)) 
								{
									return false;
								}
							}
						}
					}

					dir = ForgeDirection.SOUTH;
				}
				else if(par3World.getBlock(par4 + 4, par5, par6) ==  useblock && par3World.getBlock(par4 + 4, par5 - 1, par6) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4, par5 - i, par6 + 1 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 + 4, par5 - i, par6 + 1 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + 1 + i, par5 + 1 - j, par6 + 3) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + 1 + i, par5 - 2, par6 + j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 + 1 + i, par5 - 1 + j, par6 + k)) 
								{
									return false;
								}
							}
						}
					}
					mirrored = true;
					dir = ForgeDirection.SOUTH;
				}
			}
			else if(par3World.getBlock(par4, par5, par6 - 1) ==  useblock)
			{
				if(par3World.getBlock(par4 + 4, par5, par6) ==  useblock && par3World.getBlock(par4 + 4, par5 - 1, par6) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4, par5 - i, par6 - 1 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 + 4, par5 - i, par6 - 1 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + 1 + i, par5 + 1 - j, par6 - 3) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 + 1 + i, par5 - 2, par6 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 + 1 + i, par5 - 1 + j, par6 - k)) 
								{
									return false;
								}
							}
						}
					}

					dir = ForgeDirection.NORTH;
				}
				else if(par3World.getBlock(par4 - 4, par5, par6) ==  useblock && par3World.getBlock(par4 - 4, par5 - 1, par6) ==  useblock)
				{
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4, par5 - i, par6 - 1 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 2; i++) {
						for (int j = 0; j < 2; j++) {
							if(par3World.getBlock(par4 - 4, par5 - i, par6 - 1 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 + 1 - j, par6 - 3) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 3; j++) {
							if(par3World.getBlock(par4 - 1 - i, par5 - 2, par6 - j) !=  useblock) 
							{
								return false;
							}
						}
					}
					for (int i = 0; i < 3; i++) {
						for (int j = 0; j < 4; j++) {
							for (int k = 0; k < 3; k++) {
								if(!par3World.isAirBlock(par4 - 1 - i, par5 - 1 + j, par6 - k)) 
								{
									return false;
								}
							}
						}
					}
					mirrored = true;
					dir = ForgeDirection.NORTH;
				}
			}
			System.out.println("" + dir +  " " + mirrored);
		}*/
		return true;

	}


	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister par1IconRegister) {
		itemIcon = par1IconRegister.registerIcon("bedcraftbeyond:scissors");
	}
}
