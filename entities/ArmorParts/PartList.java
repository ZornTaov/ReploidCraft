package zornco.reploidcraftenv.entities.armorParts;

import java.util.HashMap;

import net.minecraft.util.ResourceLocation;
import zornco.reploidcraftenv.ReploidCraftEnv;

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
		this.texture = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/rideArmor"+tex+".png");
		ReploidCraftEnv.logger.info("registered Texture for " + tex);
		return this;
	}

	public boolean hasPart(String type, PartSlot slot)
	{
		return this.partList.get(slot) != null;
	}
}