package zornco.reploidcraft.client.renderers.mechParts;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelRideArmorHead extends ModelRideArmorBase {
    
	public ModelRideArmorHead() {

		super();
		setTextureOffset("head.headTop", 92, 63);
		setTextureOffset("head.headFront", 104, 88);
		setTextureOffset("head.headBack", 104, 104);
		setTextureOffset("head.headLeft", 154, 88);
		setTextureOffset("head.headRight", 166, 50);

		head = new ModelRenderer(this, "head");
		head.setRotationPoint(0F, -24F, 0F);
		setRotation(head, 0F, 0F, 0F);
		head.mirror = true;
		head.addBox("headTop", -12F, -16F, -12F, 24, 1, 24);
		head.addBox("headFront", -12F, -15F, -12F, 24, 15, 1);
		head.addBox("headBack", -12F, -15F, 11F, 24, 15, 1);
		head.addBox("headLeft", 11F, -15F, -11F, 1, 15, 22);
		head.addBox("headRight", -12F, -15F, -11F, 1, 15, 22);
	}

	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.renderPart(entity, f, f1, f2, f3, f4, f5);
		head.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float f7 = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(head, 0F, f7/2, 0F);
	}
}
