package zornco.reploidcraftenv.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMechBay extends ModelBase
{
	//fields
	ModelRenderer back;
	ModelRenderer top;
	ModelRenderer beam1;
	ModelRenderer beam2;
	ModelRenderer beam3;
	ModelRenderer base;
	ModelRenderer floor1;
	ModelRenderer floor2;
	ModelRenderer support1;
	ModelRenderer support2;
	ModelRenderer pillar1;
	ModelRenderer pillar2;
	ModelRenderer pillar3;
	ModelRenderer pillar;
	ModelRenderer rail1;
	ModelRenderer rail2;
	ModelRenderer rail3;
	ModelRenderer rail4;
	ModelRenderer hose1;

	public ModelMechBay()
	{
		textureWidth = 256;
		textureHeight = 256;

		back = new ModelRenderer(this, 0, 0);
		back.addBox(-40F, -24F, 32F, 80, 48, 8);
		back.setRotationPoint(0F, 0F, 0F);
		back.setTextureSize(256, 256);
		setRotation(back, 0F, 0F, 0F);

		top = new ModelRenderer(this, 0, 60);
		top.addBox(-40F, -24F, 26F, 80, 8, 6);
		top.setRotationPoint(0F, 0F, 0F);
		top.setTextureSize(256, 256);
		setRotation(top, 0F, 0F, 0F);

		beam1 = new ModelRenderer(this, 0, 56);
		beam1.addBox(-40F, 11F, 30F, 80, 2, 2);
		beam1.setRotationPoint(0F, 0F, 0F);
		beam1.setTextureSize(256, 256);
		setRotation(beam1, 0F, 0F, 0F);

		beam2 = new ModelRenderer(this, 0, 56);
		beam2.addBox(-40F, 1F, 30F, 80, 2, 2);
		beam2.setRotationPoint(0F, 0F, 0F);
		beam2.setTextureSize(256, 256);
		setRotation(beam2, 0F, 0F, 0F);

		beam3 = new ModelRenderer(this, 0, 56);
		beam3.addBox(-40F, -9F, 30F, 80, 2, 2);
		beam3.setRotationPoint(0F, 0F, 0F);
		beam3.setTextureSize(256, 256);
		setRotation(beam3, 0F, 0F, 0F);

		base = new ModelRenderer(this, 0, 131);
		base.addBox(-24F, 24F, -24F, 48, 16, 48);
		base.setRotationPoint(0F, 0F, 0F);
		base.setTextureSize(256, 256);
		setRotation(base, 0F, 0F, 0F);

		floor1 = new ModelRenderer(this, 0, 74);
		floor1.addBox(0F, 23F, -24F, 40, 1, 56);
		floor1.setRotationPoint(0F, 0F, 0F);
		floor1.setTextureSize(256, 256);
		floor1.mirror = true;
		setRotation(floor1, 0F, 0F, 0F);

		floor2 = new ModelRenderer(this, 0, 74);
		floor2.addBox(-40F, 23F, -24F, 40, 1, 56);
		floor2.setRotationPoint(0F, 0F, 0F);
		floor2.setTextureSize(256, 256);
		setRotation(floor2, 0F, 0F, 0F);

		support1 = new ModelRenderer(this, 200, 0);
		support1.addBox(22F, -23F, 24F, 4, 46, 8);
		support1.setRotationPoint(0F, 0F, 0F);
		support1.setTextureSize(256, 256);
		setRotation(support1, 0F, 0F, 0F);

		support2 = new ModelRenderer(this, 200, 0);
		support2.addBox(-26F, -23F, 24F, 4, 46, 8);
		support2.setRotationPoint(0F, 0F, 0F);
		support2.setTextureSize(256, 256);
		setRotation(support2, 0F, 0F, 0F);

		pillar1 = new ModelRenderer(this, 176, 0);
		pillar1.addBox(30F, -16F, 5F, 8, 39, 4);
		pillar1.setRotationPoint(0F, 0F, 0F);
		pillar1.setTextureSize(256, 256);
		setRotation(pillar1, 0F, 0F, 0F);

		pillar2 = new ModelRenderer(this, 176, 0);
		pillar2.addBox(-38F, -16F, 5F, 8, 39, 4);
		pillar2.setRotationPoint(0F, 0F, 0F);
		pillar2.setTextureSize(256, 256);
		setRotation(pillar2, 0F, 0F, 0F);

		pillar3 = new ModelRenderer(this, 176, 0);
		pillar3.addBox(30F, -16F, -22F, 8, 39, 4);
		pillar3.setRotationPoint(0F, 0F, 0F);
		pillar3.setTextureSize(256, 256);
		setRotation(pillar3, 0F, 0F, 0F);

		pillar = new ModelRenderer(this, 176, 0);
		pillar.addBox(-38F, -16F, -22F, 8, 39, 4);
		pillar.setRotationPoint(0F, 0F, 0F);
		pillar.setTextureSize(256, 256);
		setRotation(pillar, 0F, 0F, 0F);

		rail1 = new ModelRenderer(this, 0, 195);
		rail1.addBox(-36F, 14F, -18F, 4, 4, 50);
		rail1.setRotationPoint(0F, 0F, 0F);
		rail1.setTextureSize(256, 256);
		setRotation(rail1, 0F, 0F, 0F);

		rail2 = new ModelRenderer(this, 0, 195);
		rail2.addBox(-36F, -14F, -18F, 4, 4, 50);
		rail2.setRotationPoint(0F, 0F, 0F);
		rail2.setTextureSize(256, 256);
		setRotation(rail2, 0F, 0F, 0F);

		rail3 = new ModelRenderer(this, 0, 195);
		rail3.addBox(32F, 14F, -18F, 4, 4, 50);
		rail3.setRotationPoint(0F, 0F, 0F);
		rail3.setTextureSize(256, 256);
		setRotation(rail3, 0F, 0F, 0F);

		rail4 = new ModelRenderer(this, 0, 195);
		rail4.addBox(32F, -14F, -18F, 4, 4, 50);
		rail4.setRotationPoint(0F, 0F, 0F);
		rail4.setTextureSize(256, 256);
		setRotation(rail4, 0F, 0F, 0F);

		hose1 = new ModelRenderer(this, 108, 195);
		hose1.addBox(0F, 0F, 0F, 24, 16, 0);
		hose1.setRotationPoint(0F, 0F, 0F);
		hose1.setTextureSize(256, 256);
		setRotation(hose1, 0F, 0F, 0F);
	}

	public void render(float f5)
	{
		back.render(f5);
		top.render(f5);
		beam1.render(f5);
		beam2.render(f5);
		beam3.render(f5);
		base.render(f5);
		floor1.render(f5);
		floor2.render(f5);
		support1.render(f5);
		support2.render(f5);
		pillar1.render(f5);
		pillar2.render(f5);
		pillar3.render(f5);
		pillar.render(f5);
		rail1.render(f5);
		rail2.render(f5);
		rail3.render(f5);
		rail4.render(f5);
		hose1.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

}
