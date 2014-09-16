package zornco.reploidcraftenv.client.renderers.mechParts;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRideArmorArmRight extends ModelRideArmorBase {

	ModelRenderer lowerArmRight;
	
	public ModelRideArmorArmRight() {
		super();
	    
	    setTextureOffset("upperArmRight.shoulderRight", 58, 0);
	    setTextureOffset("upperArmRight.bicepLeftRight", 106, 0);
	    setTextureOffset("lowerArmRight.armRight", 110, 0);
	    setTextureOffset("lowerArmRight.thumbRight", 158, 0);
	    setTextureOffset("lowerArmRight.elbowRight", 158, 11);
	    
		upperArmRight = new ModelRenderer(this, "upperArmRight");
		upperArmRight.setRotationPoint(-18F, -25F, 0F);
		setRotation(upperArmRight, 0F, 0F, 0F);
		upperArmRight.addBox("shoulderRight", -6F, -6F, -6F, 12, 12, 12);
		upperArmRight.addBox("bicepLeftRight", -4F, 6F, -4F, 8, 16, 8);
			lowerArmRight = new ModelRenderer(this, "lowerArmRight");
			lowerArmRight.setRotationPoint(0F, 22F, 0F);
			setRotation(lowerArmRight, 0F, 0F, 0F);
			lowerArmRight.addBox("armRight", -5F, -5F, -22F, 10, 13, 28);
			lowerArmRight.addBox("thumbRight", -1F, -7F, -17F, 8, 8, 3);
			lowerArmRight.addBox("elbowRight", -4F, -4F, 6F, 8, 11, 5);
		upperArmRight.addChild(lowerArmRight);
	}
	@Override
	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.renderPart(entity, f, f1, f2, f3, f4, f5);
		upperArmRight.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float inOut = (float)(Math.cos((double)(f1 * (float)Math.PI / 128F ))*4F + 4F);
		if(inOut > 1F) inOut = 1F;
		if(inOut < 0F) inOut = 0F;
		//System.out.println(f);

		float f7 = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(upperArmRight, f7, f7/2, 0.2F);
		setRotation(lowerArmRight, -f7, 0F, 0F);
	}
}
