package zornco.reploidcraftenv.client.renderers.mechParts;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRideArmorArmLeft extends ModelRideArmorBase {
	
	ModelRenderer lowerArmLeft;
	
	public ModelRideArmorArmLeft() {
		super();
	    
	    setTextureOffset("upperArmLeft.shoulderLeft", 58, 0);
	    setTextureOffset("upperArmLeft.bicepLeft", 106, 0);
	    setTextureOffset("lowerArmLeft.armLeft", 110, 0);
	    setTextureOffset("lowerArmLeft.thumbLeft", 158, 0);
	    setTextureOffset("lowerArmLeft.elbowLeft", 158, 11);
		
		upperArmLeft = new ModelRenderer(this, "upperArmLeft");
		upperArmLeft.setRotationPoint(18F, -25F, 0F);
		setRotation(upperArmLeft, 0F, 0F, 0F);
		upperArmLeft.mirror = true;
		upperArmLeft.addBox("shoulderLeft", -6F, -6F, -6F, 12, 12, 12);
		upperArmLeft.addBox("bicepLeft", -4F, 6F, -4F, 8, 16, 8);
			lowerArmLeft = new ModelRenderer(this, "lowerArmLeft");
			lowerArmLeft.setRotationPoint(0F, 22F, 0F);
			setRotation(lowerArmLeft, 0F, 0F, 0F);
			lowerArmLeft.mirror = true;
			lowerArmLeft.addBox("armLeft", -5F, -5F, -22F, 10, 13, 28);
			lowerArmLeft.addBox("thumbLeft", -7F, -7F, -17F, 8, 8, 3);
			lowerArmLeft.mirror = false;
			lowerArmLeft.addBox("elbowLeft", -4F, -4F, 6F, 8, 11, 5);
		upperArmLeft.addChild(lowerArmLeft);
	}

	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.renderPart(entity, f, f1, f2, f3, f4, f5);
		upperArmLeft.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float inOut = (float)(Math.cos((double)(f1 * (float)Math.PI / 128F ))*4F + 4F);
		if(inOut > 1F) inOut = 1F;
		if(inOut < 0F) inOut = 0F;
		//System.out.println(f);

		float f7 = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(upperArmLeft, -f7, f7/2, -0.2F);
		setRotation(lowerArmLeft, f7, 0F, 0F);
	}
}
