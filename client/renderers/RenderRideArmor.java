package zornco.reploidcraftenv.client.renderers;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorArmLeft;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorArmRight;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorBack;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorBase;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorHead;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorLegs;
import zornco.reploidcraftenv.client.renderers.mechParts.ModelRideArmorTorso;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.EntityRideArmorPart;
import zornco.reploidcraftenv.entities.armorParts.PartSlot;

public class RenderRideArmor extends Render {

	private int nagged = -1;
	private ModelRideArmorBase modelHead;
	private ModelRideArmorBase modelChest;
	private ModelRideArmorBase modelBack;
	private ModelRideArmorBase modelLegs;
	private ModelRideArmorBase modelArmLeft;
	private ModelRideArmorBase modelArmRight;
	public ResourceLocation defaultTexture = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/rideArmorGreen.png");

	public RenderRideArmor()
	{
		this.shadowSize = 1.5F;
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
			this.modelHead = new ModelRideArmorHead();
			this.modelChest = new ModelRideArmorTorso();
			this.modelBack = new ModelRideArmorBack();
			this.modelLegs = new ModelRideArmorLegs();
			this.modelArmLeft = new ModelRideArmorArmLeft();
			this.modelArmRight = new ModelRideArmorArmRight();
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
		float f9 = (float)(Math.cos((double)(f7 * (float)Math.PI * 0.4F )))/4f * f6;
		float f10 = (float)(Math.sin((double)(f8 * (float)Math.PI +0.75F)))*0.7375F-0.5F;
		//System.out.println(f10);
		GL11.glTranslatef(0.0F, -f9/5F-(f10*0.6325F)-0.01F, 0F);
		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.BODY.ordinal()]).getType()));
        this.modelChest.renderPart(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);
        if(entity.rideArmorParts != null)
        {
        	if (entity.hasPart(PartSlot.HEAD))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.HEAD.ordinal()]).getType()));
        		this.modelHead.renderPart(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);
        	}
        	if (entity.hasPart(PartSlot.ARMLEFT))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.ARMLEFT.ordinal()]).getType()));
        		this.modelArmLeft.renderPart(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);
        	}
        	if (entity.hasPart(PartSlot.ARMRIGHT))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.ARMRIGHT.ordinal()]).getType()));
        		this.modelArmRight.renderPart(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);
        	}
        	if (entity.hasPart(PartSlot.BACK))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.BACK.ordinal()]).getType()));
        		this.modelBack.renderPart(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);
        	}
        	if (entity.hasPart(PartSlot.LEGS))
        	{
        		this.bindTexture(ReploidCraftEnv.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.LEGS.ordinal()]).getType()));
        		this.modelLegs.renderPart(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);
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
