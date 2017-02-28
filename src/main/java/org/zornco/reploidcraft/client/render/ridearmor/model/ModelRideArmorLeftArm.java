package org.zornco.reploidcraft.client.render.ridearmor.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
/**
 * ModelRideArmor - Zorn_Taov
 * Created using Tabula 5.1.0
 */
public class ModelRideArmorLeftArm extends ModelRideArmorBase {

    public ModelRenderer armLeft1;
    public ModelRenderer armLeft2;
    public ModelRenderer upperArmLeft;
    public ModelRenderer jointArmLeft;
    public ModelRenderer lowerArmLeft;
    
	public ModelRideArmorLeftArm() {
		super();

        this.armLeft1 = new ModelRenderer(this, 0, 104);
        this.armLeft1.setRotationPoint(18.0F, -1.0F, 0.0F);
        this.armLeft1.addBox(-6.0F, -6.0F, -6.0F, 12, 9, 12, 0.0F);
        this.setRotation(armLeft1, 0.0F, 0.0F, -0.2617993877991494F);
        this.armLeft2 = new ModelRenderer(this, 5, 105);
        this.armLeft2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.armLeft2.addBox(-5.0F, 0.0F, -5.0F, 10, 3, 10, 0.0F);
        this.setRotation(armLeft2, 0.0F, 1.5707963267948966F, 0.0F);
        this.upperArmLeft = new ModelRenderer(this, 24, 8);
        this.upperArmLeft.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.upperArmLeft.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
        this.jointArmLeft = new ModelRenderer(this, 0, 0);
        this.jointArmLeft.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.jointArmLeft.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotation(jointArmLeft, 0.0F, 1.5707963267948966F, 0.0F);
        this.lowerArmLeft = new ModelRenderer(this, 0, 7);
        this.lowerArmLeft.setRotationPoint(2.0F, 0.0F, 0.0F);
        this.lowerArmLeft.addBox(-9.0F, 0.0F, -4.0F, 22, 8, 8, 0.0F);

        this.rootArmLeft.addChild(this.armLeft1);
        this.armLeft1.addChild(this.armLeft2);
        this.armLeft1.addChild(this.upperArmLeft);
        this.upperArmLeft.addChild(this.jointArmLeft);
        this.jointArmLeft.addChild(this.lowerArmLeft);
	}

	@Override
	public void renderPart(Entity entity, float limbSwing,
			float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {

        this.rootArmLeft.render(scale);
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
		setRotation(armLeft1, -f7, 0F, (float)Math.toRadians(-5));
		setRotation(lowerArmLeft, 0, 0, f7);
    }

}
