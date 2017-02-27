package org.zornco.reploidcraft.entities.armorparts;

import java.util.HashMap;

import net.minecraft.util.ResourceLocation;

import org.zornco.reploidcraft.ReploidCraft;

import com.google.common.collect.Maps;

public class PartList
{
	private final HashMap<PartSlot, PartBase> partList = Maps.newHashMap();
	private String catagory;
	private ResourceLocation texture;

	@SuppressWarnings("rawtypes")
	public HashMap getPartList()
	{
		return this.partList;
	}

	public String getPartCategory()
	{
		return this.catagory;
	}

	public PartList setPartCategory(String cat)
	{
		this.catagory = cat;
		return this;
	}
	
	public ResourceLocation getTexture() {
		return texture;
	}

	public PartList setTexture(String tex) {
		this.texture = new ResourceLocation(ReploidCraft.MODID + ":textures/entity/rideArmor"+tex+".png");
		ReploidCraft.logger.info("registered Texture for " + tex);
		return this;
	}

	public boolean hasPart(String type, PartSlot slot)
	{
		return this.partList.get(slot) != null;
	}
}