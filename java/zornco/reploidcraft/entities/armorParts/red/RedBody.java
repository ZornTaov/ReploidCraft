package zornco.reploidcraft.entities.armorParts.red;

import net.minecraft.util.DamageSource;
import zornco.reploidcraft.entities.EntityRideArmor;
import zornco.reploidcraft.entities.armorParts.IPartBody;
import zornco.reploidcraft.entities.armorParts.PartBase;

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
