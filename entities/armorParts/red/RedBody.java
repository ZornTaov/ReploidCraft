package zornco.reploidcraftenv.entities.armorParts.red;

import zornco.reploidcraftenv.entities.armorParts.IPartBody;
import zornco.reploidcraftenv.entities.armorParts.PartBase;

public class RedBody extends PartBase implements IPartBody {

	public RedBody() {
		super(40, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isLavaResistant() {
		// TODO Auto-generated method stub
		return true;
	}

}
