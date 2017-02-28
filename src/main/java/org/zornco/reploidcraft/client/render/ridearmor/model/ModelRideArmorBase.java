package org.zornco.reploidcraft.client.render.ridearmor.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
/**
 * ModelRideArmor - Zorn_Taov
 * Created using Tabula 5.1.0
 */
public class ModelRideArmorBase extends ModelBase {

	public ModelRenderer rootHead;
	public ModelRenderer rootTorso;
    public ModelRenderer rootArmRight;
    public ModelRenderer rootArmLeft;
    public ModelRenderer rootBack;
    public ModelRenderer rootLegs;
    
	public ModelRideArmorBase() {
        this.textureWidth = 128;
        this.textureHeight = 128;


        this.rootHead = new ModelRenderer(this, 0, 0);
        this.rootHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootHead.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        
        this.rootTorso = new ModelRenderer(this, 0, 0);
        this.rootTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootTorso.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);

        this.rootArmRight = new ModelRenderer(this, 0, 0);
        this.rootArmRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootArmRight.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        
        this.rootArmLeft = new ModelRenderer(this, 0, 0);
        this.rootArmLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootArmLeft.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        
        this.rootBack = new ModelRenderer(this, 0, 0);
        this.rootBack.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootBack.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        
        this.rootLegs = new ModelRenderer(this, 0, 0);
        this.rootLegs.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.rootLegs.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        
        this.rootTorso.addChild(rootArmLeft);
	}
	
	public void renderPart(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		
	}
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
			super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			float f7 = MathHelper.cos(limbSwing * 0.6662F / .57F) * 1.4F * limbSwingAmount / 6F;
	        
	        this.renderPart(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	        
			setRotation(rootTorso, 0F, f7/2, 0F);
			setRotation(rootArmRight, 0F, f7, 0);
			setRotation(rootArmLeft, 0, f7, 0);
			setRotation(rootBack, 0, f7/2, 0);
			setRotation(rootLegs, 0, -f7/2, 0);
			
			setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
	}
	protected void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
