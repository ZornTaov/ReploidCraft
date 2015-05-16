package zornco.reploidcraft.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.client.renderers.mechParts.ModelRideArmorArmLeft;
import zornco.reploidcraft.client.renderers.mechParts.ModelRideArmorArmRight;
import zornco.reploidcraft.client.renderers.mechParts.ModelRideArmorBack;
import zornco.reploidcraft.client.renderers.mechParts.ModelRideArmorBase;
import zornco.reploidcraft.client.renderers.mechParts.ModelRideArmorHead;
import zornco.reploidcraft.client.renderers.mechParts.ModelRideArmorLegs;
import zornco.reploidcraft.client.renderers.mechParts.ModelRideArmorTorso;
import zornco.reploidcraft.entities.EntityRideArmor;
import zornco.reploidcraft.entities.EntityRideArmorPart;
import zornco.reploidcraft.entities.armorParts.PartBase;
import zornco.reploidcraft.entities.armorParts.PartSlot;

public class RenderRideArmor extends Render {

	private int nagged = -1;
	private ModelBase modelHead;
	private ModelBase modelChest;
	private ModelBase modelBack;
	private ModelBase modelLegs;
	private ModelBase modelArmLeft;
	private ModelBase modelArmRight;
	
	/**
	 * for tabula
	 */
	private ModelBase greenHead 	= new ModelRideArmorHead();    
	private ModelBase greenChest    = new ModelRideArmorTorso();   
	private ModelBase greenBack     = new ModelRideArmorBack();    
	private ModelBase greenLegs     = new ModelRideArmorLegs();    
	private ModelBase greenArmLeft  = new ModelRideArmorArmLeft(); 
	private ModelBase greenArmRight = new ModelRideArmorArmRight();
	
	public ResourceLocation defaultTexture = new ResourceLocation(ReploidCraft.MOD_ID + ":textures/entity/rideArmorGreen.png");

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
			this.modelHead 		= new ModelRideArmorHead();
			this.modelChest 	= new ModelRideArmorTorso();
			this.modelBack 		= new ModelRideArmorBack();
			this.modelLegs 		= new ModelRideArmorLegs();
			this.modelArmLeft 	= new ModelRideArmorArmLeft();
			this.modelArmRight 	= new ModelRideArmorArmRight();
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
		GL11.glTranslatef(0.0F, -f9/5F-(f10*0.6325F)-0.01F + (entity.hasPart(PartSlot.LEGS)?0.0F:1F), 0F);
		//ModelRideArmorBase temp;
		this.bindTexture(ReploidCraft.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[PartSlot.BODY.ordinal()]).getType()));
		renderPart((ModelRideArmorBase) modelChest, entity, PartSlot.BODY, f6, f7, f8, 0.0F);
		if(entity.rideArmorParts != null)
		{
			if (entity.hasPart(PartSlot.HEAD))
			{
				this.bindTexture(getPartTexture(entity, PartSlot.HEAD));
				renderPart((ModelRideArmorBase) modelHead, entity, PartSlot.HEAD, f6, f7, f8, 0.0F);
			}
			if (entity.hasPart(PartSlot.ARMLEFT))
			{
				this.bindTexture(getPartTexture(entity, PartSlot.ARMLEFT));
				renderPart((ModelRideArmorBase) modelArmLeft, entity, PartSlot.ARMLEFT, f6, f7, f8, entity.hasPart(PartSlot.LEGS)?1.0F:5F);
			}
			if (entity.hasPart(PartSlot.ARMRIGHT))
			{
				this.bindTexture(getPartTexture(entity, PartSlot.ARMRIGHT));
				renderPart((ModelRideArmorBase) modelArmRight, entity, PartSlot.ARMRIGHT, f6, f7, f8, entity.hasPart(PartSlot.LEGS)?1.0F:5F);
			}
			if (entity.hasPart(PartSlot.BACK))
			{
				this.bindTexture(getPartTexture(entity, PartSlot.BACK));
				renderPart((ModelRideArmorBase) modelBack, entity, PartSlot.BACK, f6, f7, f8, 0.0F);
				
			}
			if (entity.hasPart(PartSlot.LEGS))
			{
				this.bindTexture(getPartTexture(entity, PartSlot.LEGS));
				renderPart((ModelRideArmorBase) modelLegs, entity, PartSlot.LEGS, f6, f7, f8, 0.0F);
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
	private ResourceLocation getPartTexture(Entity entity, PartSlot slot)
	{
		PartBase part = ReploidCraft.proxy.partRegistry.getPart(((EntityRideArmorPart) entity.getParts()[slot.ordinal()]).getType(), slot);
		return part != null && part.getTexture() != null ?part.getTexture() :ReploidCraft.proxy.partRegistry.getPartTexture(((EntityRideArmorPart) entity.getParts()[slot.ordinal()]).getType());

	}
	private void renderPart(ModelRideArmorBase part, Entity entity, PartSlot slot, float f6, float f7, float f8, float f9)
	{
		if(ReploidCraft.proxy.partRegistry.getPart(((EntityRideArmorPart) entity.getParts()[slot.getIndex()]).getType(), slot).getModel()!=null)
		{
			ReploidCraft.proxy.partRegistry.getPart(((EntityRideArmorPart) entity.getParts()[slot.getIndex()]).getType(), slot).getModel().renderPart(entity, f6, f7, f8, f9, 0.0F, 0.0625F);
		}
		else
		{
			part.renderPart(entity, f6, f7, f8, f9, 0.0F, 0.0625F);
		}
	}
}
