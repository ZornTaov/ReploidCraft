package org.zornco.reploidcraft.client.render.ridearmor.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
/**
 * ModelRideArmor - Zorn_Taov
 * Created using Tabula 5.1.0
 */
public class ModelRideArmorRightArm extends ModelRideArmorBase {

    public ModelRenderer armRight1;
    public ModelRenderer armRight2;
    public ModelRenderer upperArmRight;
    public ModelRenderer jointArmRight;
    public ModelRenderer lowerArmRight;
    
	public ModelRideArmorRightArm() {
		super();

        this.armRight1 = new ModelRenderer(this, 0, 104);
        this.armRight1.setRotationPoint(-18.0F, -1.0F, 0.0F);
        this.armRight1.addBox(-6.0F, -6.0F, -6.0F, 12, 9, 12, 0.0F);
        this.setRotation(armRight1, 0.0F, 0.0F, 0.2617993877991494F);
        this.armRight2 = new ModelRenderer(this, 5, 105);
        this.armRight2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.armRight2.addBox(-5.0F, 0.0F, -5.0F, 10, 3, 10, 0.0F);
        this.setRotation(armRight2, 0.0F, 1.5707963267948966F, 0.0F);
        this.upperArmRight = new ModelRenderer(this, 24, 8);
        this.upperArmRight.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.upperArmRight.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
        this.jointArmRight = new ModelRenderer(this, 0, 0);
        this.jointArmRight.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.jointArmRight.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotation(jointArmRight, 0.0F, 1.5707963267948966F, 0.0F);
        this.lowerArmRight = new ModelRenderer(this, 0, 7);
        this.lowerArmRight.setRotationPoint(2.0F, 0.0F, 0.0F);
        this.lowerArmRight.addBox(-9.0F, 0.0F, -4.0F, 22, 8, 8, 0.0F);

        this.rootArmRight.addChild(this.armRight1);
        this.armRight1.addChild(this.armRight2);
        this.armRight1.addChild(this.upperArmRight);
        this.upperArmRight.addChild(this.jointArmRight);
        this.jointArmRight.addChild(this.lowerArmRight);
	}

	@Override
	public void renderPart(Entity entity, float limbSwing,
			float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {

        this.rootArmRight.render(scale);
	}
	
	/**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        float f7 = MathHelper.cos(limbSwing * 0.6662F / .57F) * 1.4F * limbSwingAmount / 6F;
		setRotation(armRight1, f7, 0F, (float)Math.toRadians(5));
		setRotation(lowerArmRight, 0, 0, -f7);
    }

}
