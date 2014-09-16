package zornco.reploidcraftenv.client.renderers.mechParts;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelRideArmorBackRabbit extends ModelRideArmorBase
{  
	private ModelRenderer finRight;
	private ModelRenderer tipRight;
	private ModelRenderer finRight2;
	private ModelRenderer finLeft;
	private ModelRenderer tipLeft;
	private ModelRenderer finLeft2;

	public ModelRideArmorBackRabbit()
	{
		textureWidth = 64;
		textureHeight = 64;
		setTextureOffset("BackPackB.Mid", 32, 0);
		setTextureOffset("BackPackB.baseRight", 0, 25);
		setTextureOffset("BackPackB.baseLeft", 0, 25);
		setTextureOffset("finRight.fin", 0, 0);
		setTextureOffset("tipRight.tip", 0, 0);
		setTextureOffset("finRight2.fin2", 6, 25);
		setTextureOffset("finLeft.fin", 0, 0);
		setTextureOffset("tipLeft.tip", 0, 0);
		setTextureOffset("finLeft2.fin2", 6, 25);


		backPack = new ModelRenderer(this, "BackPackB");
		backPack.setRotationPoint(0F, -23F, 0F);
		setRotation(backPack, 0F, 0F, 0F);
		backPack.addBox("Mid", -3F, 1F, 13F, 6, 9, 5);
		backPack.addBox("baseRight", -10F, 0F, 13F, 7, 11, 6);
		backPack.addBox("baseLeft", 3F, 0F, 13F, 7, 11, 6);
			finRight = new ModelRenderer(this, "finRight");
			finRight.setRotationPoint(-6.5F, 0F, 13F);
			setRotation(finRight, 0.644F, -0.05F, 0F);
				finRight.addBox("fin", -3F, 5F, 0F, 6, 5, 20);
				tipRight = new ModelRenderer(this, "tipRight");
				tipRight.setRotationPoint(0F, 5F, 20F);
				setRotation(tipRight, -0.927F, 0F, 0F);
				tipRight.addBox("tip", -2.5F, 0F, 0F, 5, 3, 4);
			finRight.addChild(tipRight);
				finRight2 = new ModelRenderer(this, "finRight2");
				finRight2.setRotationPoint(0F, 0F, 0F);
				setRotation(finRight2, -0.257F, 0F, 0F);
				finRight2.addBox("fin2", -2.5F, 0F, 0F, 5, 4, 20);
			finRight.addChild(finRight2);
		backPack.addChild(finRight);
			finLeft = new ModelRenderer(this, "finLeft");
			finLeft.setRotationPoint(6.5F, 0F, 13F);
			setRotation(finLeft, 0.644F, 0.05F, 0F);
			finLeft.addBox("fin", -3F, 5F, 0F, 6, 5, 20);
				tipLeft = new ModelRenderer(this, "tipLeft");
				tipLeft.setRotationPoint(0F, 5F, 20F);
				setRotation(tipLeft, -0.927F, 0F, 0F);
				tipLeft.addBox("tip", -2.5F, 0F, 0F, 5, 3, 4);
			finLeft.addChild(tipLeft);
				finLeft2 = new ModelRenderer(this, "finLeft2");
				finLeft2.setRotationPoint(0F, 0F, 0F);
				setRotation(finLeft2, -0.257F, 0F, 0F);
				finLeft2.addBox("fin2", -2.5F, 0F, 0F, 5, 4, 20);
			finLeft.addChild(finLeft2);
		backPack.addChild(finLeft);
	}

	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.renderPart(entity, f, f1, f2, f3, f4, f5);
		backPack.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		float f7 = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(backPack, 0F, f7/2, 0F);
	}

}
