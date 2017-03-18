package org.zornco.reploidcraft.entities.armorparts.red;

import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.client.render.ridearmor.RenderRideArmor;
import org.zornco.reploidcraft.client.render.ridearmor.layer.LayerRideArmorPart;
import org.zornco.reploidcraft.client.render.ridearmor.model.ModelRideArmorChest;
import org.zornco.reploidcraft.client.render.ridearmor.model.ModelRideArmorHeadFrog;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.entities.armorparts.IPartBody;
import org.zornco.reploidcraft.entities.armorparts.PartBase;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;

public class RedBody extends PartBase implements IPartBody {

	public RedBody() {
		super(40, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});
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
			mech.world.spawnParticle(EnumParticleTypes.CLOUD, 
					X, 
					mech.posY + 1.8D,// + ((EntityRideArmor)mech).getSitOffset(), 
					Z, d0, d1, d2, new int[0]);
		}
	}

	@Override
	public boolean isLavaResistant() {
		return true;
	}

	@Override
	public boolean doExplode(EntityRideArmor ride, DamageSource source)
	{
		return false;
	}@SideOnly(Side.CLIENT)
	@Override
	public LayerRideArmorPart getLayer() {
		return layer;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ResourceLocation getTexture() {
		return layer.getTexture();
	}
	/*@SideOnly(Side.CLIENT)
	@Override
	public void initModel(String type, PartSlot slot) {
		model = new ModelRideArmorChest();
		setLayer(type, slot);
	}*/
}
