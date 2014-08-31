package zornco.reploidcraftenv.entities.armorParts.green;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraftforge.common.ForgeHooks;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.armorParts.IPartArm;
import zornco.reploidcraftenv.entities.armorParts.PartBase;
import zornco.reploidcraftenv.entities.armorParts.PartSlot;

public class GreenArm extends PartBase implements IPartArm{

	ItemStack[] tools = {new ItemStack(Items.iron_pickaxe), new ItemStack(Items.iron_shovel), new ItemStack(Items.iron_axe)};
	public GreenArm() {
		super(20, makeFloatArray(1.25F, 1.0F, 0.0F), makeFloatArray(1.0F, 1.0F));
	}

	@Override
	public float[] getArmPos(PartSlot slot) {
		if(isMirrored() && slot == PartSlot.ARMRIGHT)
		{
			float[] xFlip = {this.getOffsetXYZ()[0]*-1.0F, this.getOffsetXYZ()[1], this.getOffsetXYZ()[2]};
			return xFlip;
		}
		return this.getOffsetXYZ();
	}

	@Override
	public boolean isMirrored() {
		return true;
	}

	@Override
	public boolean doAttack(EntityRideArmor mech) {
		MovingObjectPosition mop = getMovingObjectPositionFromMech(mech);
		if(mop != null)
		{
			if(mop.typeOfHit == MovingObjectType.ENTITY)
			{
				mop.entityHit.attackEntityFrom(DamageSource.causeMobDamage(mech), 7);
				return true;
			}
			else //if (mop.typeOfHit == MovingObjectType.BLOCK)
			{
				Block block = null;
				int meta = 0;
				for(int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						for (int k = -1; k < 2; k++) {
							block = mech.worldObj.getBlock(mop.blockX + i, mop.blockY + j, mop.blockZ + k);
							if(block == Blocks.air) continue;
							meta = mech.worldObj.getBlockMetadata(mop.blockX + i, mop.blockY + j, mop.blockZ + k);
							for (int k2 = 0; k2 < harvestEquivilent().length; k2++) {
								
								if(ForgeHooks.canToolHarvestBlock(block, meta, this.harvestEquivilent()[k2]))
								{
									mech.worldObj.func_147480_a(mop.blockX + i, mop.blockY + j, mop.blockZ + k, true);
			                    
									if (!mech.worldObj.isRemote)
									{
										mech.worldObj.markBlockForUpdate(mop.blockX + i, mop.blockY + j, mop.blockZ + k);
										mech.worldObj.notifyBlockChange(mop.blockX + i, mop.blockY + j, mop.blockZ + k, block);

										if (block.hasComparatorInputOverride())
										{
											mech.worldObj.func_147453_f(mop.blockX + i, mop.blockY + j, mop.blockZ + k, block);
										}
									}
								}
							}
						}
					}
				}
				//mech.worldObj.setBlock(mop.blockX, mop.blockY, mop.blockZ, Blocks.air);
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] getRecipe() {
		Object[] recipe = new Object[] {
				"ib", 
				Character.valueOf('i'), new ItemStack(ReploidCraftEnv.component, 1, 2), 
				Character.valueOf('b'), new ItemStack(ReploidCraftEnv.component, 1, 5)
			};
		return recipe;
	}

	@Override
	public ItemStack[] harvestEquivilent() {
		return tools;
	}
}
