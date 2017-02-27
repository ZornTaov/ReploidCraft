package org.zornco.reploidcraft.client.render.ridearmor;

import org.lwjgl.opengl.GL11;
import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.client.render.ridearmor.ChestA3;
import org.zornco.reploidcraft.client.render.ridearmor.ModelRideArmor;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;
import org.zornco.reploidcraft.entities.EntityRideArmor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.ResourceLocation;

public class RenderRideArmor extends RenderLiving<EntityRideArmor> {
	private static final ResourceLocation platform = new ResourceLocation(ReploidCraft.MODID + ":textures/entity/ridearmor/chest.png");//FloatingPlatform.png");

	private ModelBase modelHead;
	private ModelBase modelChest;
	private ModelBase modelBack;
	private ModelBase modelLegs;
	private ModelBase modelArmLeft;
	private ModelBase modelArmRight;
	
	public RenderRideArmor(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelRideArmor(), 1F);
		// TODO Auto-generated constructor stub
	}
	/**
     * Allows the render to do state modifications necessary before the model is rendered.
     */
    protected void preRenderCallback(EntityRideArmor entitylivingbaseIn, float partialTickTime)
    {

		GlStateManager.translate(0.0F, -0.5F, 0.0F);
        super.preRenderCallback(entitylivingbaseIn, partialTickTime);
    }
    
	/*@Override
	public void doRender(EntityRideArmor entity, double x, double y, double z, float entityYaw, float partialTicks) {
		float var10 = ((float) entity.moveForward + partialTicks) / 5.0F;
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) x, (float) y, (float) z);
		GlStateManager.translate(0.0F, 2.0625F, 0.0F);
		// System.out.println(par9);
		
		 * GL11.glTranslated(entity.lerp( entity.posX, entity.posX -
		 * entity.prevPosX, 1D, par9) - entity.prevPosX,
		 * entity.lerp(entity.posY, entity.posY - entity.prevPosY, 1D, par9) -
		 * entity.prevPosY, entity.lerp(entity.posZ, entity.posZ -
		 * entity.prevPosZ, 1D, par9) - entity.prevPosZ);
		 

		GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.bindEntityTexture(entity);
		this.modelPlatform.render(entity, 0.0F, var10 * 3.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

		GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}*/

	@Override
	protected ResourceLocation getEntityTexture(EntityRideArmor entity) {
		// TODO Auto-generated method stub
		return platform;
	}

}
