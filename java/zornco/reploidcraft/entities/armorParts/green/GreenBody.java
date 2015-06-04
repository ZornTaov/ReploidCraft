package zornco.reploidcraft.entities.armorParts.green;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import zornco.reploidcraft.entities.EntityRideArmor;
import zornco.reploidcraft.entities.armorParts.IPartBody;
import zornco.reploidcraft.entities.armorParts.PartBase;

public class GreenBody extends PartBase implements IPartBody {

	public GreenBody() {
		super(40, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});
	}

	@Override
	public boolean isLavaResistant() {
		return false;
	}

	@Override
	public void doParticle(Entity mech) {
		for (int i = 0; i < 4; ++i)
		{
			float f3 = mech.rotationYaw * (float)Math.PI / 180.0F;
			float f1 = MathHelper.sin(f3);
			float f2 = MathHelper.cos(f3);
			double X = mech.posX + 0.4 * f2 * ((i%2)==0?-1:1) - 1.2 * f1;
			double Z = mech.posZ + 0.4 * f1 * ((i%2)==0?-1:1) + 1.2 * f2;
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			mech.worldObj.spawnParticle("cloud", 
					X, 
					mech.posY + 1.8D + ((EntityRideArmor)mech).getSitOffset(), 
					Z, d0, d1, d2);
		}
	}

	@Override
	public boolean doExplode(EntityRideArmor ride, DamageSource source)
	{
		return false;
	}

}
