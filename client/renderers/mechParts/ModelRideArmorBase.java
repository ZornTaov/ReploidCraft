package zornco.reploidcraftenv.client.renderers.mechParts;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRideArmorBase extends ModelBase {

	ModelRenderer head;
	ModelRenderer torso;
	ModelRenderer upperArmLeft;
	ModelRenderer upperArmRight;
	ModelRenderer backPack;
	ModelRenderer hips;
	public ModelRideArmorBase() {
		textureWidth = 256;
		textureHeight = 128;
		
	}
	
	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	protected void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
