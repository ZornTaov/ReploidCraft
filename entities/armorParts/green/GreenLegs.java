package zornco.reploidcraftenv.entities.armorParts.green;

import zornco.reploidcraftenv.entities.armorParts.IPartLegs;
import zornco.reploidcraftenv.entities.armorParts.PartBase;

public class GreenLegs extends PartBase implements IPartLegs {

	public GreenLegs() {
		super(20, makeFloatArray(0.0F, 0.0F, 0.0F), makeFloatArray(1.5F, 1.0F));
		// TODO Auto-generated constructor stub
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
