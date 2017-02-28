package org.zornco.reploidcraft.client.render.ridearmor.model.OLD;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRideArmorLegs extends ModelRideArmorBase {

	ModelRenderer upperLegLeft;
	ModelRenderer lowerLegLeft;
	ModelRenderer footLeft;
	ModelRenderer upperLegRight;
	ModelRenderer lowerLegRight;
	ModelRenderer footRight;
	
	public ModelRideArmorLegs() {
		super();
	    
		setTextureOffset("hips.waist", 70, 0);
	    setTextureOffset("hips.hip1", 0, 0);
	    setTextureOffset("hips.hip2", 64, 16);
	    setTextureOffset("upperLegRight.upperLeg1Right", 0, 25);
	    setTextureOffset("upperLegRight.upperLeg2Right", 4, 25);
	    setTextureOffset("lowerLegRight.lowerLeg1Right", 32, 25);
	    setTextureOffset("lowerLegRight.lowerLeg2Right", 0, 0);
	    setTextureOffset("lowerLegRight.lowerLeg3Right", 0, 41);
	    setTextureOffset("lowerLegRight.lowerLeg4Right", 0, 41);
	    setTextureOffset("footRight.foot1Right", 46, 38);
	    setTextureOffset("footRight.foot2Right", 0, 42);
	    setTextureOffset("footRight.foot3Right", 31, 43);
	    setTextureOffset("footRight.foot4Right", 0, 6);
	    setTextureOffset("footRight.foot5Right", 0, 6);
	    setTextureOffset("upperLegLeft.upperLeg1Left", 0, 25);
	    setTextureOffset("upperLegLeft.upperLeg2Left", 4, 25);
	    setTextureOffset("lowerLegLeft.lowerLeg1Left", 32, 25);
	    setTextureOffset("lowerLegLeft.lowerLeg2Left", 0, 0);
	    setTextureOffset("lowerLegLeft.lowerLeg3Left", 0, 41);
	    setTextureOffset("lowerLegLeft.lowerLeg4Left", 0, 41);
	    setTextureOffset("footLeft.foot1Left", 46, 38);
	    setTextureOffset("footLeft.foot2Left", 0, 42);
	    setTextureOffset("footLeft.foot3Left", 31, 43);
	    setTextureOffset("footLeft.foot4Left", 0, 6);
	    setTextureOffset("footLeft.foot5Left", 0, 6);
		hips = new ModelRenderer(this, "hips");
		hips.setRotationPoint(0F, 0F, 0F);
		setRotation(hips, 0F, 0F, 0F);
		hips.addBox("waist", -6F, -6F, -6F, 12, 4, 12);
		hips.addBox("hip1", -7F, -2F, -10F, 14, 5, 20);
		hips.addBox("hip2", -7F, 3F, -8F, 14, 6, 16);
		
		
			upperLegLeft = new ModelRenderer(this, "upperLegLeft");
			upperLegLeft.setRotationPoint(9F, 6F, 0F);
			setRotation(upperLegLeft, 0F, 0F, 0F);
			upperLegLeft.mirror = true;
			upperLegLeft.addBox("upperLeg1Left", -2F, -2F, -2F, 2, 4, 4);
			upperLegLeft.addBox("upperLeg2Left", 0F, -3F, -4F, 6, 8, 8);
				lowerLegLeft = new ModelRenderer(this, "lowerLegLeft");
				lowerLegLeft.setRotationPoint(3F, 6F, 0F);
				setRotation(lowerLegLeft, 0F, 0F, 0F);
				lowerLegLeft.mirror = true;
				lowerLegLeft.addBox("lowerLeg1Left", -4F, 0F, -4F, 8, 10, 8);
				lowerLegLeft.addBox("lowerLeg2Left", -3F, -2F, -2F, 6, 2, 4);
				lowerLegLeft.addBox("lowerLeg3Left", 3F, -3F, -3F, 2, 6, 6);
				lowerLegLeft.addBox("lowerLeg4Left", -5F, -3F, -3F, 2, 6, 6);
					footLeft = new ModelRenderer(this, "footLeft");
					footLeft.setRotationPoint(0F, 8F, 0F);
					setRotation(footLeft, 0F, 0F, 0F);
					footLeft.mirror = true;
					footLeft.addBox("foot1Left", -5F, 2F, -12F, 10, 2, 20);
					footLeft.addBox("foot2Left", -3.5F, 0F, -10F, 7, 2, 16);
					footLeft.addBox("foot3Left", -3.5F, -1F, -7F, 7, 1, 10);
					footLeft.addBox("foot4Left", 3.5F, -1F, -3F, 2, 5, 5);
					footLeft.addBox("foot5Left", -5.5F, -1F, -3F, 2, 5, 5);
				lowerLegLeft.addChild(footLeft);
			upperLegLeft.addChild(lowerLegLeft);
		hips.addChild(upperLegLeft);
			upperLegRight = new ModelRenderer(this, "upperLegRight");
			upperLegRight.setRotationPoint(-9F, 6F, 0F);
			setRotation(upperLegRight, 0F, 0F, 0F);
			upperLegRight.addBox("upperLeg1Right", 0F, -2F, -2F, 2, 4, 4);
			upperLegRight.addBox("upperLeg2Right", -6F, -3F, -4F, 6, 8, 8);
				lowerLegRight = new ModelRenderer(this, "lowerLegRight");
				lowerLegRight.setRotationPoint(-3F, 6F, 0F);
				setRotation(lowerLegRight, 0F, 0F, 0F);
				lowerLegRight.addBox("lowerLeg1Right", -4F, 0F, -4F, 8, 10, 8);
				lowerLegRight.addBox("lowerLeg2Right", -3F, -2F, -2F, 6, 2, 4);
				lowerLegRight.addBox("lowerLeg3Right", 3F, -3F, -3F, 2, 6, 6);
				lowerLegRight.addBox("lowerLeg4Right", -5F, -3F, -3F, 2, 6, 6);
					footRight = new ModelRenderer(this, "footRight");
					footRight.setRotationPoint(0F, 8F, 0F);
					setRotation(footRight, 0F, 0F, 0F);
					footRight.addBox("foot1Right", -5F, 2F, -12F, 10, 2, 20);
					footRight.addBox("foot2Right", -3.5F, 0F, -10F, 7, 2, 16);
					footRight.addBox("foot3Right", -3.5F, -1F, -7F, 7, 1, 10);
					footRight.addBox("foot4Right", 3.5F, -1F, -3F, 2, 5, 5);
					footRight.addBox("foot5Right", -5.5F, -1F, -3F, 2, 5, 5);
				lowerLegRight.addChild(footRight);
			upperLegRight.addChild(lowerLegRight);
		hips.addChild(upperLegRight);
	}

	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.renderPart(entity, f, f1, f2, f3, f4, f5);
		hips.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float DEG_TO_RAD = (float)Math.PI/180F;

		float f8 = (float)(Math.cos((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		float f11 = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(upperLegRight, 30F*DEG_TO_RAD-(f2*160F*DEG_TO_RAD), 0F, 0F);
		setRotation(lowerLegRight, -60F*DEG_TO_RAD+(f2*140F*DEG_TO_RAD), 0F, 0F);
		setRotation(footRight, 30F*DEG_TO_RAD+(f2*20F*DEG_TO_RAD), 0F, 0F);
		upperLegRight.setRotationPoint(-9F, -f8*10F+4F, -f11*20F);
		setRotation(upperLegLeft, 30F*DEG_TO_RAD-(f2*160F*DEG_TO_RAD), 0F, 0F);
		setRotation(lowerLegLeft, -60F*DEG_TO_RAD+(f2*140F*DEG_TO_RAD), 0F, 0F);
		setRotation(footLeft, 30F*DEG_TO_RAD+(f2*20F*DEG_TO_RAD), 0F, 0F);
		upperLegLeft.setRotationPoint(9F, f8*10F+4F, f11*20F);
	}

}
