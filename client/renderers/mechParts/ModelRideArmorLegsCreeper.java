
package zornco.reploidcraftenv.client.renderers.mechParts;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRideArmorLegsCreeper extends ModelRideArmorBase {

	ModelRenderer upperLegLeft1;
	ModelRenderer lowerLegLeft1;
	ModelRenderer footLeft1;
	ModelRenderer upperLegRight1;
	ModelRenderer lowerLegRight1;
	ModelRenderer footRight1;
	ModelRenderer upperLegLeft2;
	ModelRenderer lowerLegLeft2;
	ModelRenderer footLeft2;
	ModelRenderer upperLegRight2;
	ModelRenderer lowerLegRight2;
	ModelRenderer footRight2;

	public ModelRideArmorLegsCreeper() {
		textureWidth = 128;
		textureHeight = 64;
		setTextureOffset("hips.waist", 70, 0);
		setTextureOffset("hips.hip1", 0, 0);
		setTextureOffset("hips.hip2", 60, 16);
		setTextureOffset("upperLegRight1.upperLeg1Right", 0, 29);
		setTextureOffset("upperLegRight1.upperLeg2Right", 4, 29);
		setTextureOffset("lowerLegRight1.lowerLeg1Right", 86, 42);
		setTextureOffset("lowerLegRight1.lowerLeg2Right", 0, 0);
		setTextureOffset("lowerLegRight1.lowerLeg3Right", 0, 50);
		setTextureOffset("lowerLegRight1.lowerLeg4Right", 0, 50);
		setTextureOffset("footRight1.foot1Right", 46, 42);
		setTextureOffset("footRight1.foot2Right", 0, 46);
		setTextureOffset("footRight1.foot3Right", 31, 51);
		setTextureOffset("footRight1.foot4Right", 0, 6);
		setTextureOffset("footRight1.foot5Right", 0, 6);
		setTextureOffset("upperLegLeft1.upperLeg1Left", 0, 29);
		setTextureOffset("upperLegLeft1.upperLeg2Left", 4, 29);
		setTextureOffset("lowerLegLeft1.lowerLeg1Left", 86, 42);
		setTextureOffset("lowerLegLeft1.lowerLeg2Left", 0, 0);
		setTextureOffset("lowerLegLeft1.lowerLeg3Left", 0, 50);
		setTextureOffset("lowerLegLeft1.lowerLeg4Left", 0, 50);
		setTextureOffset("footLeft1.foot1Left", 46, 42);
		setTextureOffset("footLeft1.foot2Left", 0, 46);
		setTextureOffset("footLeft1.foot3Left", 31, 51);
		setTextureOffset("footLeft1.foot4Left", 0, 6);
		setTextureOffset("footLeft1.foot5Left", 0, 6);
		setTextureOffset("upperLegRight2.upperLeg1Right", 0, 29);
		setTextureOffset("upperLegRight2.upperLeg2Right", 4, 29);
		setTextureOffset("lowerLegRight2.lowerLeg1Right", 86, 42);
		setTextureOffset("lowerLegRight2.lowerLeg2Right", 0, 0);
		setTextureOffset("lowerLegRight2.lowerLeg3Right", 0, 50);
		setTextureOffset("lowerLegRight2.lowerLeg4Right", 0, 50);
		setTextureOffset("footRight2.foot1Right", 46, 42);
		setTextureOffset("footRight2.foot2Right", 0, 46);
		setTextureOffset("footRight2.foot3Right", 31, 51);
		setTextureOffset("footRight2.foot4Right", 0, 6);
		setTextureOffset("footRight2.foot5Right", 0, 6);
		setTextureOffset("upperLegLeft2.upperLeg1Left", 0, 29);
		setTextureOffset("upperLegLeft2.upperLeg2Left", 4, 29);
		setTextureOffset("lowerLegLeft2.lowerLeg1Left", 86, 42);
		setTextureOffset("lowerLegLeft2.lowerLeg2Left", 0, 0);
		setTextureOffset("lowerLegLeft2.lowerLeg3Left", 0, 50);
		setTextureOffset("lowerLegLeft2.lowerLeg4Left", 0, 50);
		setTextureOffset("footLeft2.foot1Left", 46, 42);
		setTextureOffset("footLeft2.foot2Left", 0, 46);
		setTextureOffset("footLeft2.foot3Left", 31, 51);
		setTextureOffset("footLeft2.foot4Left", 0, 6);
		setTextureOffset("footLeft2.foot5Left", 0, 6);

		hips = new ModelRenderer(this, "hips");
		hips.setRotationPoint(0F, 0F, 0F);
		setRotation(hips, 0F, 0F, 0F);
		hips.addBox("waist", -6F, -6F, -6F, 12, 4, 12);
		hips.addBox("hip1", -7F, -2F, -12F, 14, 5, 24);
		hips.addBox("hip2", -7F, 3F, -10F, 14, 6, 20);
			upperLegRight1 = new ModelRenderer(this, "upperLegRight1");
			upperLegRight1.setRotationPoint(-9F, 6F, -7F);
			setRotation(upperLegRight1, 0F, 0F, 0F);
			upperLegRight1.addBox("upperLeg1Right", 0F, -2F, -2F, 2, 4, 4);
			upperLegRight1.addBox("upperLeg2Right", -6F, -3F, -4F, 6, 8, 8);
				lowerLegRight1 = new ModelRenderer(this, "lowerLegRight1");
				lowerLegRight1.setRotationPoint(-3F, 6F, 0F);
				setRotation(lowerLegRight1, 0F, 0F, 0F);
				lowerLegRight1.addBox("lowerLeg1Right", -4F, 0F, -4F, 8, 10, 8);
				lowerLegRight1.addBox("lowerLeg2Right", -3F, -2F, -2F, 6, 2, 4);
				lowerLegRight1.addBox("lowerLeg3Right", 3F, -3F, -3F, 2, 6, 6);
				lowerLegRight1.addBox("lowerLeg4Right", -5F, -3F, -3F, 2, 6, 6);
					footRight1 = new ModelRenderer(this, "footRight1");
					footRight1.setRotationPoint(0F, 8F, 0F);
					setRotation(footRight1, 0F, 0F, 0F);
					footRight1.addBox("foot1Right", -5F, 2F, -12F, 10, 2, 20);
					footRight1.addBox("foot2Right", -3.5F, 0F, -10F, 7, 2, 16);
					footRight1.addBox("foot3Right", -3.5F, -1F, -7F, 7, 1, 10);
					footRight1.addBox("foot4Right", 3.5F, -1F, -3F, 2, 5, 5);
					footRight1.addBox("foot5Right", -5.5F, -1F, -3F, 2, 5, 5);
				lowerLegRight1.addChild(footRight1);
			upperLegRight1.addChild(lowerLegRight1);
		hips.addChild(upperLegRight1);
		
			upperLegLeft1 = new ModelRenderer(this, "upperLegLeft1");
			upperLegLeft1.setRotationPoint(9F, 6F, -7F);
			setRotation(upperLegLeft1, 0F, 0F, 0F);
			upperLegLeft1.addBox("upperLeg1Left", -2F, -2F, -2F, 2, 4, 4);
			upperLegLeft1.addBox("upperLeg2Left", 0F, -3F, -4F, 6, 8, 8);
				lowerLegLeft1 = new ModelRenderer(this, "lowerLegLeft1");
				lowerLegLeft1.setRotationPoint(3F, 6F, 0F);
				setRotation(lowerLegLeft1, 0F, 0F, 0F);
				lowerLegLeft1.addBox("lowerLeg1Left", -4F, 0F, -4F, 8, 10, 8);
				lowerLegLeft1.addBox("lowerLeg2Left", -3F, -2F, -2F, 6, 2, 4);
				lowerLegLeft1.addBox("lowerLeg3Left", 3F, -3F, -3F, 2, 6, 6);
				lowerLegLeft1.addBox("lowerLeg4Left", -5F, -3F, -3F, 2, 6, 6);
					footLeft1 = new ModelRenderer(this, "footLeft1");
					footLeft1.setRotationPoint(0F, 8F, 0F);
					setRotation(footLeft1, 0F, 0F, 0F);
					footLeft1.addBox("foot1Left", -5F, 2F, -12F, 10, 2, 20);
					footLeft1.addBox("foot2Left", -3.5F, 0F, -10F, 7, 2, 16);
					footLeft1.addBox("foot3Left", -3.5F, -1F, -7F, 7, 1, 10);
					footLeft1.addBox("foot4Left", 3.5F, -1F, -3F, 2, 5, 5);
					footLeft1.addBox("foot5Left", -5.5F, -1F, -3F, 2, 5, 5);
				lowerLegLeft1.addChild(footLeft1);
			upperLegLeft1.addChild(lowerLegLeft1);
		hips.addChild(upperLegLeft1);
		
			upperLegRight2 = new ModelRenderer(this, "upperLegRight2");
			upperLegRight2.setRotationPoint(-9F, 6F, 7F);
			setRotation(upperLegRight2, 0F, 0F, 0F);
			upperLegRight2.addBox("upperLeg1Right", 0F, -2F, -2F, 2, 4, 4);
			upperLegRight2.addBox("upperLeg2Right", -6F, -3F, -4F, 6, 8, 8);
				lowerLegRight2 = new ModelRenderer(this, "lowerLegRight2");
				lowerLegRight2.setRotationPoint(-3F, 6F, 0F);
				setRotation(lowerLegRight2, 0F, 0F, 0F);
				lowerLegRight2.addBox("lowerLeg1Right", -4F, 0F, -4F, 8, 10, 8);
				lowerLegRight2.addBox("lowerLeg2Right", -3F, -2F, -2F, 6, 2, 4);
				lowerLegRight2.addBox("lowerLeg3Right", 3F, -3F, -3F, 2, 6, 6);
				lowerLegRight2.addBox("lowerLeg4Right", -5F, -3F, -3F, 2, 6, 6);
					footRight2 = new ModelRenderer(this, "footRight2");
					footRight2.setRotationPoint(0F, 8F, 0F);
					setRotation(footRight2, 0F, 0F, 0F);
					footRight2.addBox("foot1Right", -5F, 2F, -12F, 10, 2, 20);
					footRight2.addBox("foot2Right", -3.5F, 0F, -10F, 7, 2, 16);
					footRight2.addBox("foot3Right", -3.5F, -1F, -7F, 7, 1, 10);
					footRight2.addBox("foot4Right", 3.5F, -1F, -3F, 2, 5, 5);
					footRight2.addBox("foot5Right", -5.5F, -1F, -3F, 2, 5, 5);
				lowerLegRight2.addChild(footRight2);
			upperLegRight2.addChild(lowerLegRight2);
		hips.addChild(upperLegRight2);
			upperLegLeft2 = new ModelRenderer(this, "upperLegLeft2");
			upperLegLeft2.setRotationPoint(9F, 6F, 7F);
			setRotation(upperLegLeft2, 0F, 0F, 0F);
			upperLegLeft2.addBox("upperLeg1Left", -2F, -2F, -2F, 2, 4, 4);
			upperLegLeft2.addBox("upperLeg2Left", 0F, -3F, -4F, 6, 8, 8);
				lowerLegLeft2 = new ModelRenderer(this, "lowerLegLeft2");
				lowerLegLeft2.setRotationPoint(3F, 6F, 0F);
				setRotation(lowerLegLeft2, 0F, 0F, 0F);
				lowerLegLeft2.addBox("lowerLeg1Left", -4F, 0F, -4F, 8, 10, 8);
				lowerLegLeft2.addBox("lowerLeg2Left", -3F, -2F, -2F, 6, 2, 4);
				lowerLegLeft2.addBox("lowerLeg3Left", 3F, -3F, -3F, 2, 6, 6);
				lowerLegLeft2.addBox("lowerLeg4Left", -5F, -3F, -3F, 2, 6, 6);
					footLeft2 = new ModelRenderer(this, "footLeft2");
					footLeft2.setRotationPoint(0F, 8F, 0F);
					setRotation(footLeft2, 0F, 0F, 0F);
					footLeft2.addBox("foot1Left", -5F, 2F, -12F, 10, 2, 20);
					footLeft2.addBox("foot2Left", -3.5F, 0F, -10F, 7, 2, 16);
					footLeft2.addBox("foot3Left", -3.5F, -1F, -7F, 7, 1, 10);
					footLeft2.addBox("foot4Left", 3.5F, -1F, -3F, 2, 5, 5);
					footLeft2.addBox("foot5Left", -5.5F, -1F, -3F, 2, 5, 5);
				lowerLegLeft2.addChild(footLeft2);
			upperLegLeft2.addChild(lowerLegLeft2);
		hips.addChild(upperLegLeft2);
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
		setRotation(upperLegRight1, 45F*DEG_TO_RAD+(f2*100F*DEG_TO_RAD), 0F, 0F);
		setRotation(lowerLegRight1, -45F*DEG_TO_RAD-(f2*80F*DEG_TO_RAD), 0F, 0F);
		setRotation(footRight1, 0F*DEG_TO_RAD-(f2*20F*DEG_TO_RAD), 0F, 0F);
		upperLegRight1.setRotationPoint(-9F, -f8*10F+4F, -f11*20F+7);

		setRotation(upperLegLeft1, 45F*DEG_TO_RAD+(f2*100F*DEG_TO_RAD), 0F, 0F);
		setRotation(lowerLegLeft1, -45F*DEG_TO_RAD-(f2*80F*DEG_TO_RAD), 0F, 0F);
		setRotation(footLeft1, 0F*DEG_TO_RAD-(f2*20F*DEG_TO_RAD), 0F, 0F);
		upperLegLeft1.setRotationPoint(9F, f8*10F+4F, f11*20F+7);

		setRotation(upperLegRight2, -45F*DEG_TO_RAD-(f2*100F*DEG_TO_RAD), 0F, 0F);
		setRotation(lowerLegRight2, 45F*DEG_TO_RAD+(f2*80F*DEG_TO_RAD), 0F, 0F);
		setRotation(footRight2, 00F*DEG_TO_RAD+(f2*20F*DEG_TO_RAD), 0F, 0F);
		upperLegRight2.setRotationPoint(-9F, f8*10F+4F, f11*20F-7);

		setRotation(upperLegLeft2, -45F*DEG_TO_RAD-(f2*100F*DEG_TO_RAD), 0F, 0F);
		setRotation(lowerLegLeft2, 45F*DEG_TO_RAD+(f2*80F*DEG_TO_RAD), 0F, 0F);
		setRotation(footLeft2, 0F*DEG_TO_RAD+(f2*20F*DEG_TO_RAD), 0F, 0F);
		upperLegLeft2.setRotationPoint(9F, -f8*10F+4F, -f11*20F-7);
	}

}
