package org.zornco.reploidcraft.entities.armorparts.frog;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.client.render.ridearmor.RenderRideArmor;
import org.zornco.reploidcraft.client.render.ridearmor.layer.LayerRideArmorPart;
import org.zornco.reploidcraft.client.render.ridearmor.model.ModelRideArmorBase;
import org.zornco.reploidcraft.client.render.ridearmor.model.ModelRideArmorHeadFrog;
import org.zornco.reploidcraft.entities.RideArmorType;
import org.zornco.reploidcraft.entities.armorparts.IPartHead;
import org.zornco.reploidcraft.entities.armorparts.PartBase;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;

public class FrogHead extends PartBase implements IPartHead {

	public FrogHead() {
		super(10, new float[] {0.0F, 3.0F, 0.0F}, new float[] {0.5F, 1.5F});
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSealed() {
		return true;
	}
	@SideOnly(Side.CLIENT)
	@Override
	public LayerRideArmorPart getLayer() {
		return layer;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ResourceLocation getTexture() {
		return layer.getTexture();
	}
	@SideOnly(Side.CLIENT)
	@Override
	public void initModel(String type, PartSlot slot) {
		model = new ModelRideArmorHeadFrog();
		texture = new ResourceLocation(ReploidCraft.MODID + ":textures/entity/rideArmorHeadFrog.png");
		setLayer(type, slot);
	}

}
