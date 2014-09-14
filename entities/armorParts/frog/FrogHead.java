package zornco.reploidcraftenv.entities.armorParts.frog;

import zornco.reploidcraftenv.entities.armorParts.IPartHead;
import zornco.reploidcraftenv.entities.armorParts.PartBase;

public class FrogHead extends PartBase implements IPartHead {

	public FrogHead() {
		super(10, new float[] {0.0F, 3.0F, 0.0F}, new float[] {0.5F, 1.5F});
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isSealed() {
		return true;
	}

}
