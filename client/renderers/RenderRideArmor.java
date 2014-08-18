package zornco.reploidcraftenv.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.EntityRideArmorPart;
import zornco.reploidcraftenv.entities.parts.PartSlot;

public class RenderRideArmor extends Render {

	private int nagged = -1;
	private ModelBase modelRideArmor;
	public ResourceLocation defaultTexture = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/rideArmorGreen.png");

	public RenderRideArmor()
	{
		this.shadowSize = 2.5F;
	}
	/**
	 * Args: entity, x, y, z, yaw,
	 * partialTickTime
	 */
	@Override
	public void doRender(Entity entity, double par2, double par4, double par6,
			float par8, float par9)
	{
		this.doRenderRideArmor((EntityRideArmor)entity, par2, par4, par6, par8, par9);
	}

	private void doRenderRideArmor(EntityRideArmor entity, 
			double par2, double par4, double par6, float par8, float par9)
	{
		if (this.nagged != 1)
		{
			this.modelRideArmor = new ModelrideArmor();
			this.nagged = 1;
		}

		float f6 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * par9;
        float f7 = entity.limbSwing - entity.limbSwingAmount * (1.0F - par9);
        float f8 = entity.prevSitAngle + (entity.sitAngle - entity.prevSitAngle) * par9;
        
        GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glTranslatef(0.0F, 1.25F, 0.0F);

		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.BODY.ordinal()]).getType()));
        ((ModelrideArmor) this.modelRideArmor).renderTorso(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);
        if(entity.rideArmorParts != null)
        {
        	if (entity.hasPart(PartSlot.ARMLEFT))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.ARMLEFT.ordinal()]).getType()));
        		((ModelrideArmor) this.modelRideArmor).renderArmLeftA(0.0625F);
        	}
        	if (entity.hasPart(PartSlot.ARMRIGHT))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.ARMRIGHT.ordinal()]).getType()));
        		((ModelrideArmor) this.modelRideArmor).renderArmRightA((0.0625F));
        	}
        	if (entity.hasPart(PartSlot.BACK))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.BACK.ordinal()]).getType()));
        		((ModelrideArmor) this.modelRideArmor).renderBackPackA((0.0625F));
        	}
        	if (entity.hasPart(PartSlot.LEGS))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.LEGS.ordinal()]).getType()));
        		((ModelrideArmor) this.modelRideArmor).renderHips((0.0625F));
        	}/*
        	((ModelrideArmor)modelRideArmor).upperArmLeftA.isHidden = !entity.hasPart(PartSlot.ARMLEFT);
        	((ModelrideArmor)modelRideArmor).upperArmRightA.isHidden = !entity.hasPart(PartSlot.ARMRIGHT);
        	((ModelrideArmor)modelRideArmor).backPackA.isHidden = !entity.hasPart(PartSlot.BACK);*/
        }

		GL11.glPopMatrix();
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		
		return defaultTexture;
	}

}
