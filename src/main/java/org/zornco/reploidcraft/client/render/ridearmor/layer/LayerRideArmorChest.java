package org.zornco.reploidcraft.client.render.ridearmor.layer;

import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

import org.zornco.reploidcraft.client.render.ridearmor.RenderRideArmor;
import org.zornco.reploidcraft.client.render.ridearmor.model.ModelRideArmorBase;
import org.zornco.reploidcraft.entities.EntityRideArmor;

public class LayerRideArmorChest implements LayerRenderer<EntityRideArmor> {
	private final ResourceLocation TEXTURE;
	private final RenderRideArmor renderer;
	private final ModelRideArmorBase modelPart;
	public LayerRideArmorChest(RenderRideArmor renderer,
			ModelRideArmorBase modelPart, ResourceLocation TEX) {
		this.renderer = renderer;
		this.modelPart = modelPart;
		this.TEXTURE = TEX;
	}

	@Override
	public void doRenderLayer(EntityRideArmor entitylivingbaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
