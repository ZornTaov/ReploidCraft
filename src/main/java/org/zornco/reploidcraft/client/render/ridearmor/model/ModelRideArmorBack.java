package org.zornco.reploidcraft.client.render.ridearmor.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
/**
 * ModelRideArmor - Zorn_Taov
 * Created using Tabula 5.1.0
 */
public class ModelRideArmorBack extends ModelRideArmorBase {

    public ModelRenderer Mid;
    public ModelRenderer tankRight;
    public ModelRenderer topRight;
    public ModelRenderer chokeRight;
    public ModelRenderer nozzleRight;
    public ModelRenderer tankLeft;
    public ModelRenderer topLeft;
    public ModelRenderer chokeLeft;
    public ModelRenderer nozzleLeft;
    
	public ModelRideArmorBack() {
		super();
        this.Mid = new ModelRenderer(this, 0, 90);
        this.Mid.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mid.addBox(-3.0F, 4.0F, 13.0F, 6, 9, 5, 0.0F);

        this.tankRight = new ModelRenderer(this, 6, 114);
        this.tankRight.mirror = true;
        this.tankRight.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.tankRight.addBox(-9.0F, 3.0F, 13.0F, 6, 8, 6, 0.0F);
        this.topRight = new ModelRenderer(this, 41, 107);
        this.topRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topRight.addBox(-8.0F, 2.0F, 14.0F, 4, 1, 4, 0.0F);
        this.chokeRight = new ModelRenderer(this, 41, 107);
        this.chokeRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.chokeRight.addBox(-8.0F, 11.0F, 14.0F, 4, 1, 4, 0.0F);
        this.nozzleRight = new ModelRenderer(this, 36, 106);
        this.nozzleRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.nozzleRight.addBox(-9.0F, 12.0F, 13.0F, 6, 3, 6, 0.0F);
        
        this.tankLeft = new ModelRenderer(this, 6, 114);
        this.tankLeft.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.tankLeft.addBox(3.0F, 3.0F, 13.0F, 6, 8, 6, 0.0F);
        this.topLeft = new ModelRenderer(this, 41, 107);
        this.topLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topLeft.addBox(4.0F, 2.0F, 14.0F, 4, 1, 4, 0.0F);
        this.chokeLeft = new ModelRenderer(this, 41, 107);
        this.chokeLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.chokeLeft.addBox(4.0F, 11.0F, 14.0F, 4, 1, 4, 0.0F);
        this.nozzleLeft = new ModelRenderer(this, 36, 106);
        this.nozzleLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.nozzleLeft.addBox(3.0F, 12.0F, 13.0F, 6, 3, 6, 0.0F);

        this.rootBack.addChild(this.Mid);
        
        this.Mid.addChild(this.tankRight);
        this.tankRight.addChild(this.topRight);
        this.tankRight.addChild(this.chokeRight);
        this.tankRight.addChild(this.nozzleRight);
        
        this.Mid.addChild(this.tankLeft);
        this.tankLeft.addChild(this.topLeft);
        this.tankLeft.addChild(this.chokeLeft);
        this.tankLeft.addChild(this.nozzleLeft);
	}

	@Override
	public void renderPart(Entity entity, float limbSwing,
			float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
        this.rootBack.render(scale);
	}

}
