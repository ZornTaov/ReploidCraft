package zornco.reploidcraftenv.entities.armorParts.creeper;

import net.minecraft.util.ResourceLocation;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorBase;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorLegsCreeper;
import zornco.reploidcraftenv.entities.armorParts.IPartLegs;
import zornco.reploidcraftenv.entities.armorParts.PartBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreeperLegs extends PartBase implements IPartLegs
{

	public CreeperLegs()
	{
		super(20, new float[] {0.0F, 0.0F, 0.0F}, new float[] {2F, 1.0F});
	}

	@Override
	public float getSpeed()
	{
		return 0.5F;
	}

	@Override
	public float getDashSpeed()
	{
		return 0;
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
		model = new ModelRideArmorLegsCreeper();
		texture = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/rideArmorCreeperLegs.png");
	}

}
