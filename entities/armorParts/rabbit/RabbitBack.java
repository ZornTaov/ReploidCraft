package zornco.reploidcraftenv.entities.armorParts.rabbit;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorBackRabbit;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorBase;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.armorParts.IPartBack;
import zornco.reploidcraftenv.entities.armorParts.PartBase;

public class RabbitBack extends PartBase implements IPartBack {
	
	public RabbitBack() {
		super(20, new float[] {-1.5F, 1.0F, 0.0F}, new float[] {1.5F, 1.0F});
	}

	@Override
	public float getJumpHeight() {
		return 0.6F;
	}

	@Override
	public float getJumpDuration() {
		return 5;
	}
	
	@Override
	public void doParticle(Entity mech) {
		for (int i = 0; i < 8; ++i)
		{
			float f3 = mech.rotationYaw * (float)Math.PI / 180.0F;
			float f1 = MathHelper.sin(f3);
			float f2 = MathHelper.cos(f3);
			double X = mech.posX + 0.7 * f2 * ((i%2)==0?-1:1) + 1.2 * f1;
			double Z = mech.posZ + 0.7 * f1 * ((i%2)==0?-1:1) - 1.2 * f2;
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * -0.04D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			mech.worldObj.spawnParticle("cloud", 
					X, 
					mech.posY + 2.0D + ((EntityRideArmor)mech).getSitOffset(), 
					Z, d0, d1, d2);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ModelRideArmorBase getModel() {
		return model;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ResourceLocation getTexture() {
		return texture;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel() {
		model = new ModelRideArmorBackRabbit();
		texture = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/rideArmorBackRabbit.png");
	}
}
