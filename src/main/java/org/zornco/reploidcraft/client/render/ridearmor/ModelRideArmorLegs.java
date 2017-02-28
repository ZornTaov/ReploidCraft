package org.zornco.reploidcraft.client.render.ridearmor;

import org.zornco.reploidcraft.client.render.ridearmor.model.ModelRideArmorBase;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelRideArmor - Zorn_Taov
 * Created using Tabula 5.1.0
 */
public class ModelRideArmorLegs extends ModelRideArmorBase {
	
    public ModelRenderer hips;
    public ModelRenderer panelLeft1;
    public ModelRenderer panelLeft2;
    public ModelRenderer panelRight1;
    public ModelRenderer panelRight2;
    public ModelRenderer upperLegLeft;
    public ModelRenderer upperLegRight;
    public ModelRenderer kneeLeft;
    public ModelRenderer lowerLegLeft;
    public ModelRenderer kneeRight;
    public ModelRenderer lowerLegRight;

    public ModelRideArmorLegs() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.panelLeft2 = new ModelRenderer(this, 48, 68);
        this.panelLeft2.mirror = true;
        this.panelLeft2.setRotationPoint(11.0F, -3.0F, -4.0F);
        this.panelLeft2.addBox(-6.5F, -2.0F, -1.0F, 7, 7, 1, 0.0F);
        this.setRotation(panelLeft2, -0.27314402793711257F, -0.5009094953223726F, 0.0F);
        this.kneeLeft = new ModelRenderer(this, 21, 6);
        this.kneeLeft.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.kneeLeft.addBox(-7.0F, -2.5F, -2.5F, 8, 5, 5, 0.0F);
        this.setRotation(kneeLeft, 0.7853981633974483F, 0.0F, 0.0F);
        this.upperLegLeft = new ModelRenderer(this, 1, 0);
        this.upperLegLeft.setRotationPoint(8.0F, 0.0F, 0.0F);
        this.upperLegLeft.addBox(-3.0F, -3.0F, -3.0F, 6, 8, 6, 0.0F);
        this.lowerLegLeft = new ModelRenderer(this, 0, 6);
        this.lowerLegLeft.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.lowerLegLeft.addBox(-6.5F, 0.0F, -5.0F, 7, 6, 7, 0.0F);
        this.panelRight1 = new ModelRenderer(this, 48, 68);
        this.panelRight1.mirror = true;
        this.panelRight1.setRotationPoint(-11.0F, -3.0F, -4.0F);
        this.panelRight1.addBox(0.0F, -2.0F, 0.0F, 10, 7, 1, 0.0F);
        this.setRotation(panelRight1, 0.27314402793711257F, -1.593485607070823F, 0.0F);
        this.lowerLegRight = new ModelRenderer(this, 0, 6);
        this.lowerLegRight.mirror = true;
        this.lowerLegRight.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.lowerLegRight.addBox(-6.5F, 0.0F, -5.0F, 7, 6, 7, 0.0F);
        this.panelLeft1 = new ModelRenderer(this, 48, 68);
        this.panelLeft1.mirror = true;
        this.panelLeft1.setRotationPoint(11.0F, -3.0F, -4.0F);
        this.panelLeft1.addBox(-10.0F, -2.0F, 0.0F, 10, 7, 1, 0.0F);
        this.setRotation(panelLeft1, 0.27314402793711257F, 1.593485607070823F, 0.0F);
        this.upperLegRight = new ModelRenderer(this, 1, 0);
        this.upperLegRight.setRotationPoint(-8.0F, 0.0F, 0.0F);
        this.upperLegRight.addBox(-3.0F, -3.0F, -3.0F, 6, 8, 6, 0.0F);
        this.panelRight2 = new ModelRenderer(this, 48, 68);
        this.panelRight2.mirror = true;
        this.panelRight2.setRotationPoint(-11.0F, -3.0F, -4.0F);
        this.panelRight2.addBox(-0.5F, -2.0F, -1.0F, 7, 7, 1, 0.0F);
        this.setRotation(panelRight2, -0.27314402793711257F, 0.5009094953223726F, 0.0F);
        this.hips = new ModelRenderer(this, 0, 64);
        this.hips.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hips.addBox(-5.0F, -4.0F, -5.0F, 10, 8, 10, 0.0F);
        this.kneeRight = new ModelRenderer(this, 21, 6);
        this.kneeRight.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.kneeRight.addBox(-7.0F, -2.5F, -2.5F, 8, 5, 5, 0.0F);
        this.setRotation(kneeRight, 0.7853981633974483F, 0.0F, 0.0F);


        this.rootLegs.addChild(this.hips);
        this.hips.addChild(this.upperLegRight);
        this.upperLegRight.addChild(this.kneeRight);
        this.upperLegRight.addChild(this.lowerLegRight);
        this.hips.addChild(this.upperLegLeft);
        this.upperLegLeft.addChild(this.kneeLeft);
        this.upperLegLeft.addChild(this.lowerLegLeft);

        this.rootLegs.addChild(this.panelRight1);
        this.rootLegs.addChild(this.panelRight2);
        this.rootLegs.addChild(this.panelLeft1);
        this.rootLegs.addChild(this.panelLeft2);
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

		setRotation(upperLegLeft, f7, 0F, 0);
		setRotation(upperLegRight, -f7, 0F, 0);
		setRotation(lowerLegLeft, f7, 0F, 0);
		setRotation(lowerLegRight, -f7, 0F, 0);
    }
    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }

	@Override
	public void renderPart(Entity entity, float limbSwing,
			float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
        this.rootLegs.render(scale);
		
	}
}
