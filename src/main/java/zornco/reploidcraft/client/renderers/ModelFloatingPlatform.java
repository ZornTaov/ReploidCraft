package zornco.reploidcraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFloatingPlatform extends ModelBase
{
	//fields
	ModelRenderer platform;
	ModelRenderer base;
	ModelRenderer spinner;
	ModelRenderer blade2;
	ModelRenderer blade1;

	public ModelFloatingPlatform()
	{
		textureWidth = 64;
		textureHeight = 32;

		platform = new ModelRenderer(this, 0, 0);
		platform.addBox(-8F, -8F, -1F, 16, 16, 5);
		platform.setRotationPoint(0F, 0F, 0F);
		platform.setTextureSize(64, 32);
		platform.mirror = true;
		setRotation(platform, 1.570796F, 0F, 0F);
		base = new ModelRenderer(this, 0, 21);
		base.addBox(-4F, -4F, -1F, 8, 8, 3);
		base.setRotationPoint(0F, 3F, 0F);
		base.setTextureSize(64, 32);
		base.mirror = true;
		setRotation(base, 1.570796F, 0F, 0F);
		spinner = new ModelRenderer(this, 42, 0);
		spinner.addBox(-2F, -2F, -2F, 4, 4, 4);
		spinner.setRotationPoint(0F, 6F, 0F);
		spinner.setTextureSize(64, 32);
		spinner.mirror = true;
		setRotation(spinner, 0F, 0F, 0F);
		blade2 = new ModelRenderer(this, 22, 25);
		blade2.addBox(0F, -1.5F, -0.5F, 8, 3, 1);
		blade2.setRotationPoint(0F, 6F, 0F);
		blade2.setTextureSize(64, 32);
		blade2.mirror = true;
		setRotation(blade2, 0.4363323F, 0F, 0F);
		blade1 = new ModelRenderer(this, 22, 21);
		blade1.addBox(-8F, -1.5F, -0.5F, 8, 3, 1);
		blade1.setRotationPoint(0F, 6F, 0F);
		blade1.setTextureSize(64, 32);
		blade1.mirror = true;
		setRotation(blade1, -0.4363323F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		platform.renderWithRotation(f5);
		base.renderWithRotation(f5);
		spinner.renderWithRotation(f5);
		blade2.renderWithRotation(f5);
		blade1.renderWithRotation(f5);
		setRotation(blade1, -0.4363323F, f1, 0);
		setRotation(blade2, 0.4363323F, f1, 0);
		setRotation(spinner, 0F, f1, 0F);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
