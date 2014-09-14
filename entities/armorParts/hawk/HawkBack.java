package zornco.reploidcraftenv.entities.armorParts.hawk;

import zornco.reploidcraftenv.entities.armorParts.IPartBack;
import zornco.reploidcraftenv.entities.armorParts.PartBase;

public class HawkBack extends PartBase implements IPartBack {

	public HawkBack() {
		super(20, new float[] {-1.5F, 1.0F, 0.0F}, new float[] {1.5F, 1.0F});
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getJumpHeight() {
		// TODO Auto-generated method stub
		return 0.7F;
	}

	@Override
	public float getJumpDuration() {
		// TODO Auto-generated method stub
		return 5;
	}

}
