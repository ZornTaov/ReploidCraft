package zornco.reploidcraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.entities.EntityFloatingPlatform;

public class RenderFloatingPlatform extends Render {

	private ModelBase modelPlatform = new ModelFloatingPlatform();
	private static final ResourceLocation platform = new ResourceLocation(ReploidCraft.MOD_ID + ":textures/entity/FloatingPlatform.png");

	public RenderFloatingPlatform()
	{
		this.shadowSize = 0.5F;
	}
	/**
	 * Args: entity, x, y, z, yaw,
	 * partialTickTime
	 */
	@Override
	public void doRender(Entity entity, double par2, double par4, double par6,
			float par8, float par9)
	{
		this.doRenderFloatingPlatform((EntityFloatingPlatform)entity, par2, par4, par6, par8, par9);
	}

	private void doRenderFloatingPlatform(EntityFloatingPlatform entity, 
			double par2, double par4, double par6, float par8, float par9)
	{

		float var10 = ((float)entity.innerRotation + par9)/5.0F;
		GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glTranslatef(0.0F, 0.25F, 0.0F);
//System.out.println(par9);
		/*GL11.glTranslated(entity.lerp( entity.posX, entity.posX - entity.prevPosX, 1D, par9) - entity.prevPosX, 
				entity.lerp(entity.posY, entity.posY - entity.prevPosY, 1D, par9) - entity.prevPosY, 
				entity.lerp(entity.posZ, entity.posZ - entity.prevPosZ, 1D, par9) - entity.prevPosZ);*/

		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		//this.loadTexture("/mods/ReploidCraft/textures/models/FloatingPlatform.png");
        this.bindEntityTexture(entity);
		this.modelPlatform.render(entity, 0.0F, var10 * 3.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return platform;
	}

}
