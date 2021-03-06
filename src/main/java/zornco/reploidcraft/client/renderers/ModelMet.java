// Date: 10/2/2012 4:09:08 AM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package zornco.reploidcraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraft.entities.EntityMet;
public class ModelMet extends ModelBase
{
	//fields
	ModelRenderer Head;
	ModelRenderer LeftFoot;
	ModelRenderer RightFoot;
	ModelRenderer Mouth;

	public ModelMet()
	{
		textureWidth = 64;
		textureHeight = 32;

		Head = new ModelRenderer(this, 0, 17);
		Head.addBox(-3.5F, -2F, -3.5F, 7, 4, 7);
		Head.setRotationPoint(0F, 22F, 0F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0F, 0F, 0F);
		LeftFoot = new ModelRenderer(this, 0, 10);
		LeftFoot.addBox(2.5F, -2F, -2F, 3, 3, 4);
		LeftFoot.setRotationPoint(0F, 24F, -1F);
		LeftFoot.setTextureSize(64, 32);
		LeftFoot.mirror = true;
		setRotation(LeftFoot, 0F, -0.2617994F, 0F);
		RightFoot = new ModelRenderer(this, 0, 10);
		RightFoot.addBox(-5.5F, -2F, -2F, 3, 3, 4);
		RightFoot.setRotationPoint(0F, 24F, -1F);
		RightFoot.setTextureSize(64, 32);
		RightFoot.mirror = true;
		setRotation(RightFoot, 0F, 0.2617994F, 0F);
		Mouth = new ModelRenderer(this, 2, 12);
		Mouth.addBox(-1F, -1F, -1F, 2, 2, 2);
		Mouth.setRotationPoint(0F, 23F, -4F);
		Mouth.setTextureSize(64, 32);
		Mouth.mirror = true;
		setRotation(Mouth, 0F, 1.570796F, 0F);

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		if(this.isChild)
		{
			float var8 = 2.0F;
			GL11.glPushMatrix();
			GL11.glScalef(1.0F / var8, 1.0F / var8, 1.0F / var8);
			GL11.glTranslatef(0.0F, 24.0F * f5, 0.0F);

			Head.render(f5);
			LeftFoot.render(f5);
			RightFoot.render(f5);
			Mouth.render(f5);
			GL11.glPopMatrix();
		}
		else
		{
			Head.render(f5);
			LeftFoot.render(f5);
			RightFoot.render(f5);
			Mouth.render(f5);
		}
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity par7Entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, par7Entity);
	}
	public void setLivingAnimations(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
		EntityMet var5 = (EntityMet)par1EntityLiving;

        if (var5.isSitting()||var5.isHiding())
        {
        	this.LeftFoot.setRotationPoint(2F, 24F, -1F);
    		this.RightFoot.setRotationPoint(-2F, 24F, -1F);
    		this.Mouth.setRotationPoint(0, 23F, 0F);
            this.LeftFoot.rotateAngleY = ((float)Math.PI);
            this.RightFoot.rotateAngleY = ((float)Math.PI);
        }
        else
        {
    		this.LeftFoot.setRotationPoint(0F, 24F, -1F);
    		this.RightFoot.setRotationPoint(0F, 24F, -1F);
    		this.Mouth.setRotationPoint(0, 23F, -4F);
            this.LeftFoot.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 0.50F * par3;
            this.RightFoot.rotateAngleY = MathHelper.cos(par2 * 0.6662F) * 0.50F * par3;
        }
    }
}
