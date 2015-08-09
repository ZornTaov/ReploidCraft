package zornco.reploidcraft.entities.armorParts.green;

import zornco.reploidcraft.entities.armorParts.IPartLegs;
import zornco.reploidcraft.entities.armorParts.PartBase;

public class GreenLegs extends PartBase implements IPartLegs {

	public GreenLegs() {
		super(20, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.5F, 1.0F});
	}

	@Override
	public float getSpeed() {
		return 0.1F;
	}

	@Override
	public float getDashSpeed() {
		return 0F;
	}

}