package org.zornco.reploidcraft.item;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;

public class ItemRideArmorPlacer extends Item {
	public ItemRideArmorPlacer()
	{
		super();
		this.setMaxStackSize(4);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setUnlocalizedName("ridearmorplacer");
		this.setCreativeTab(ReploidCraft.reploidCraftTab);
	}
	@Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if (facing == EnumFacing.DOWN)
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            ItemStack stack = player.getHeldItem(hand);
            boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
            BlockPos blockpos = flag ? pos : pos.offset(facing);

            if (!player.canPlayerEdit(blockpos, facing, stack))
            {
                return EnumActionResult.FAIL;
            }
            else
            {
                BlockPos blockpos1 = blockpos.up();
                boolean flag1 = !worldIn.isAirBlock(blockpos) && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
                flag1 = flag1 | (!worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1));

                if (flag1)
                {
                    return EnumActionResult.FAIL;
                }
                else
                {
                    double d0 = (double)blockpos.getX();
                    double d1 = (double)blockpos.getY();
                    double d2 = (double)blockpos.getZ();
                    List<Entity> list = worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D));

                    if (!list.isEmpty())
                    {
                        return EnumActionResult.FAIL;
                    }
                    else
                    {

                        EntityRideArmor rideArmor = new EntityRideArmor(worldIn, d0 + 0.5D, d1, d2 + 0.5D);
                        rideArmor.setPart(PartSlot.BODY, "GREEN");
            			rideArmor.setPart(PartSlot.BACK, "GREEN");
            			rideArmor.setPart(PartSlot.LEGS, "GREEN");
            			rideArmor.setPart(PartSlot.ARMLEFT, "GREEN");
            			rideArmor.setPart(PartSlot.ARMRIGHT, "GREEN");
                        if (!worldIn.isRemote)
                        {
                            worldIn.setBlockToAir(blockpos);
                            worldIn.setBlockToAir(blockpos1);
                            float f = (float)(((MathHelper.floor((player.rotationYaw * 4.0F / 360.0F) + 0.5F) & 3) - 2F) * 90F);
                            rideArmor.setLocationAndAngles(d0 + 0.5D, d1, d2 + 0.5D, f, 0.0F);
                            worldIn.spawnEntity(rideArmor);
                            worldIn.playSound((EntityPlayer)null, rideArmor.posX, rideArmor.posY, rideArmor.posZ, SoundEvents.ENTITY_ARMORSTAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
                        }
                        
                        if (!player.capabilities.isCreativeMode)
            			{
                        	//itemstack.shrink(1);
            			}
                        return EnumActionResult.SUCCESS;
                    }
                }
            }
        }
	}
}
