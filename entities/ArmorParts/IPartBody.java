package zornco.reploidcraftenv.entities.armorParts;

import net.minecraft.util.DamageSource;
import zornco.reploidcraftenv.entities.EntityRideArmor;

public interface IPartBody {
	public boolean isLavaResistant();
	public boolean doExplode(EntityRideArmor ride, DamageSource source);
}
