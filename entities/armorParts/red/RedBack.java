package zornco.reploidcraftenv.entities.armorParts.red;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.armorParts.IPartBack;
import zornco.reploidcraftenv.entities.armorParts.PartBase;

public class RedBack extends PartBase implements IPartBack {

	public RedBack() {
		super(10, new float[] {0.0F, 1.5F, 1.5F}, new float[] {1.0F, 1.0F});
	}

	@Override
	public float getJumpHeight() {
		return 1.1F;
	}

	@Override
	public float getJumpDuration() {
		return 0;
	}

	@Override
	public void doParticle(Entity mech) {
		for (int i = 0; i < 8; ++i)
		{
			float f3 = mech.rotationYaw * (float)Math.PI / 180.0F;
			float f1 = MathHelper.sin(f3);
			float f2 = MathHelper.cos(f3);
			double X = mech.posX + 0.4 * f2 * ((i%2)==0?-1:1) + 1.2 * f1;
			double Z = mech.posZ + 0.4 * f1 * ((i%2)==0?-1:1) - 1.2 * f2;
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			mech.worldObj.spawnParticle("explode", 
					X, 
					mech.posY + 1.8D + ((EntityRideArmor)mech).getSitOffset(), 
					Z, d0, d1, d2);
		}
	}
}
