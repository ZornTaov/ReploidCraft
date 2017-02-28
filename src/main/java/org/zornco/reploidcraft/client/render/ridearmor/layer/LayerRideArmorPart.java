package org.zornco.reploidcraft.client.render.ridearmor.layer;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

import org.zornco.reploidcraft.client.render.ridearmor.RenderRideArmor;
import org.zornco.reploidcraft.client.render.ridearmor.model.ModelRideArmorBase;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.entities.RideArmorType;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;

public class LayerRideArmorPart implements LayerRenderer<EntityRideArmor> {
	private final ResourceLocation TEXTURE;
	private final ModelRideArmorBase MODEL_PART;
	private final PartSlot SLOT;
	private final String TYPE;
	public LayerRideArmorPart(ModelRideArmorBase modelPart, ResourceLocation TEX, PartSlot sl, String typ) {
		//this.renderer = RenderRideArmor.INSTANCE;
		this.MODEL_PART = modelPart;
		this.TEXTURE = TEX;
		this.SLOT = sl;
		this.TYPE = typ;
	}

	public ResourceLocation getTexture() {
		return TEXTURE;
	}

	public ModelRideArmorBase getModelPart() {
		return MODEL_PART;
	}

	public PartSlot getSlot() {
		return SLOT;
	}

	public String getType() {
		return TYPE;
	}

	@Override
	public void doRenderLayer(EntityRideArmor rideArmor,
			float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		// TODO Auto-generated method stub
		if(rideArmor.hasPart(SLOT))
		{
			if(rideArmor.getPartType(SLOT) == this.TYPE)
			{
				
			}
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
