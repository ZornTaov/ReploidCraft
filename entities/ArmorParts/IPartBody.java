package zornco.reploidcraft.entities.armorParts;

import net.minecraft.util.DamageSource;
import zornco.reploidcraft.entities.EntityRideArmor;

public interface IPartBody {
	public boolean isLavaResistant();
	public boolean doExplode(EntityRideArmor ride, DamageSource source);
}
