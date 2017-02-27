package org.zornco.reploidcraft.entities.armorparts;

import net.minecraft.util.DamageSource;

import org.zornco.reploidcraft.entities.EntityRideArmor;

public interface IPartBody {
	public boolean isLavaResistant();
	public boolean doExplode(EntityRideArmor ride, DamageSource source);
}
