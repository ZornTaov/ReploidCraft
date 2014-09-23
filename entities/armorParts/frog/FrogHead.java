package zornco.reploidcraftenv.entities.armorParts.frog;

import net.minecraft.util.ResourceLocation;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorBase;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorHeadFrog;
import zornco.reploidcraftenv.entities.armorParts.IPartHead;
import zornco.reploidcraftenv.entities.armorParts.PartBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FrogHead extends PartBase implements IPartHead {

	public FrogHead() {
		super(10, new float[] {0.0F, 3.0F, 0.0F}, new float[] {0.5F, 1.5F});
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSealed() {
		return true;
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
		model = new ModelRideArmorHeadFrog();
		texture = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/rideArmorHeadFrog.png");
	}

}
