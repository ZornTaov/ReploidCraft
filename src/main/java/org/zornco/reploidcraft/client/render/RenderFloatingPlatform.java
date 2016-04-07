package org.zornco.reploidcraft.client.render;

import org.lwjgl.opengl.GL11;
import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFloatingPlatform extends Render<EntityFloatingPlatform> {
	private static final ResourceLocation platform = new ResourceLocation(ReploidCraft.MODID + ":textures/entity/FloatingPlatform.png");
	private ModelBase modelPlatform = new ModelFloatingPlatform();
	
	public RenderFloatingPlatform(RenderManager rendermanagerIn) {
		super(rendermanagerIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doRender(EntityFloatingPlatform entity, double x, double y, double z, float entityYaw, float partialTicks) {
		float var10 = ((float) entity.innerRotation + partialTicks) / 5.0F;
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.translate(0.0F, 0.25F, 0.0F);
		// System.out.println(par9);
		/*
		 * GL11.glTranslated(entity.lerp( entity.posX, entity.posX -
		 * entity.prevPosX, 1D, par9) - entity.prevPosX,
		 * entity.lerp(entity.posY, entity.posY - entity.prevPosY, 1D, par9) -
		 * entity.prevPosY, entity.lerp(entity.posZ, entity.posZ -
		 * entity.prevPosZ, 1D, par9) - entity.prevPosZ);
		 */

		GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.bindEntityTexture(entity);
		this.modelPlatform.render(entity, 0.0F, var10 * 3.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFloatingPlatform entity) {
		// TODO Auto-generated method stub
		return platform;
	}

}
