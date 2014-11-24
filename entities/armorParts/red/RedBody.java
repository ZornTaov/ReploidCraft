package zornco.reploidcraftenv.entities.armorParts.red;

import net.minecraft.util.DamageSource;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.armorParts.IPartBody;
import zornco.reploidcraftenv.entities.armorParts.PartBase;

public class RedBody extends PartBase implements IPartBody {

	public RedBody() {
		super(40, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});
	}

	@Override
	public boolean isLavaResistant() {
		return true;
	}

	@Override
	public boolean doExplode(EntityRideArmor ride, DamageSource source)
	{
		return false;
	}

}
