package zornco.reploidcraft.client.renderers.mechParts;

import zornco.reploidcraft.ReploidCraft;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

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
		float limbSwing = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(upperArmLeft, -limbSwing, limbSwing/2, -0.2F*f3);
		setRotation(lowerArmLeft, limbSwing, 0F, 0F);
        float f6;
        float f7;
		if (this.onGround > -9990.0F)
        {
            f6 = this.onGround;
            //ReploidCraft.logger.info(onGround);
            /*this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt_float(f6) * (float)Math.PI * 2.0F) * 0.2F;
            this.bipedRightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedLeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleY;*/
            f6 = 1.0F - this.onGround;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(this.onGround * (float)Math.PI);// * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.upperArmLeft.rotateAngleX = (float)((double)this.upperArmLeft.rotateAngleX - ((double)f7 * 0.6D + (double)f8));
            this.lowerArmLeft.rotateAngleX = -this.upperArmLeft.rotateAngleX;
            //this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.upperArmLeft.rotateAngleZ = MathHelper.sin(this.onGround * (float)Math.PI) * -0.6F -0.2F*f3;
        }
	}
}
