package zornco.reploidcraftenv.client.renderers.mechParts;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRideArmorBack extends ModelRideArmorBase {

	ModelRenderer backPackARight;
	ModelRenderer backPackALeft;
	
	public ModelRideArmorBack() {
		super();
	    
	    setTextureOffset("backPackA.Mid", 72, 64);
	    setTextureOffset("backPackARight.top", 72, 78);
	    setTextureOffset("backPackARight.tank", 78, 100);
	    setTextureOffset("backPackARight.choke", 78, 114);
	    setTextureOffset("backPackARight.nozzle", 40, 54);
	    setTextureOffset("backPackALeft.top", 72, 78);
	    setTextureOffset("backPackALeft.tank", 78, 100);
	    setTextureOffset("backPackALeft.choke", 78, 114);
	    setTextureOffset("backPackALeft.nozzle", 40, 54);
	    
		backPack = new ModelRenderer(this, "backPackA");
		backPack.setRotationPoint(0F, -24F, 0F);
		setRotation(backPack, 0F, 0F, 0F);
		backPack.addBox("Mid", -3F, 4F, 13F, 6, 9, 5);
			backPackARight = new ModelRenderer(this, "backPackARight");
			backPackARight.setRotationPoint(0F, 0F, 0F);
			setRotation(backPackARight, 0F, 0F, 0F);
			backPackARight.addBox("top", -8F, 2F, 14F, 4, 1, 4);
			backPackARight.addBox("tank", -9F, 3F, 13F, 6, 8, 6);
			backPackARight.addBox("choke", -8F, 11F, 14F, 4, 1, 4);
			backPackARight.addBox("nozzle", -9F, 12F, 13F, 6, 3, 6);
		backPack.addChild(backPackARight);
			backPackALeft = new ModelRenderer(this, "backPackALeft");
			backPackALeft.setRotationPoint(0F, 0F, 0F);
			setRotation(backPackALeft, 0F, 0F, 0F);
			backPackALeft.mirror = true;
			backPackALeft.addBox("top", 4F, 2F, 14F, 4, 1, 4);
			backPackALeft.addBox("tank", 3F, 3F, 13F, 6, 8, 6);
			backPackALeft.addBox("choke", 4F, 11F, 14F, 4, 1, 4);
			backPackALeft.addBox("nozzle", 3F, 12F, 13F, 6, 3, 6);
		backPack.addChild(backPackALeft);
	}

	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.renderPart(entity, f, f1, f2, f3, f4, f5);
		backPack.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float inOut = (float)(Math.cos((double)(f1 * (float)Math.PI / 128F ))*4F + 4F);
		if(inOut > 1F) inOut = 1F;
		if(inOut < 0F) inOut = 0F;
		//System.out.println(f);

		float f7 = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(backPack, 0F, f7/2, 0F);
	}

}
