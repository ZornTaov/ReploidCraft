package zornco.reploidcraftenv.entities.armorParts;

import java.util.List;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import zornco.reploidcraftenv.entities.EntityRideArmor;

public class PartBase {


	private final float[] offsetXYZ;

	/**
	 * 0 = width, 1 = height
	 */
	private final float[] size;
	
	private final float maxHealth;
	
	protected Random rand;

	public PartBase(float health, float[] offsetXYZ, float[] size)
	{
		this.offsetXYZ = offsetXYZ;
		this.size = size;
		this.maxHealth = health;
		this.rand = new Random();
	}
	/**
	 * 0 = width, 1 = height
	 */
	public float[] getOffsetXYZ() {
		return offsetXYZ;
	}

	public float[] getSize() {
		return size;
	}
	
	public Object[] getRecipe() {
		return null;
	}
	
	
	public float getMaxHealth() {
		return maxHealth;
	}
	public static float[] makeFloatArray(float ... args)
	{
		return args;
	}
	protected MovingObjectPosition getMovingObjectPositionFromMech(EntityRideArmor mech)
    {
		float f = 1.0F;
        float f1 = mech.prevRotationPitch + (mech.rotationPitch - mech.prevRotationPitch) * f;
        float f2 = mech.prevRotationYaw + (mech.rotationYaw - mech.prevRotationYaw) * f;
        double d0 = mech.prevPosX + (mech.posX - mech.prevPosX) * (double)f;
        double d1 = mech.prevPosY + (mech.posY - mech.prevPosY) * (double)f + 1.62D - (double)mech.yOffset;
        double d2 = mech.prevPosZ + (mech.posZ - mech.prevPosZ) * (double)f;
        Vec3 vec3 = Vec3.createVectorHelper(d0, d1, d2);
        float f3 = MathHelper.cos(-f2 * 0.017453292F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.017453292F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.017453292F);
        float f6 = MathHelper.sin(-f1 * 0.017453292F);
        float f7 = f4 * f5;
        float f8 = f3 * f5;
        double d3 = 5.0D;
        Vec3 vec31 = vec3.addVector((double)f7 * d3, (double)f6 * d3, (double)f8 * d3);
        MovingObjectPosition movingobjectposition = mech.worldObj.rayTraceBlocks(vec3, vec31, true);

        if (movingobjectposition != null)
        {
            return movingobjectposition;
        }
        else
        {
            Vec3 vec32 = mech.getLook(f);
            //boolean flag = false;
            float f9 = 1.0F;
            List<?> list = mech.worldObj.getEntitiesWithinAABBExcludingEntity(mech, mech.boundingBox.addCoord(vec32.xCoord * d3, vec32.yCoord * d3, vec32.zCoord * d3).expand((double)f9, (double)f9, (double)f9));
            int i;
            Entity entity = null;
            for (i = 0; i < list.size(); ++i)
            {
                Entity entity1 = (Entity)list.get(i);

                if (entity1.canBeCollidedWith())
                {
                    float f10 = entity1.getCollisionBorderSize();
                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f10, (double)f10, (double)f10);

                    if (axisalignedbb.isVecInside(vec3))
                    {
                        entity = entity1;
                    }
                }
            }
            if(entity != null)
            {
            	return new MovingObjectPosition(entity);
            }
        }
		return null;
    }
	public void doParticle(Entity mech) {}
}
