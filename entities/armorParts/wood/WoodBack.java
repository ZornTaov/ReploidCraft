package zornco.reploidcraft.entities.armorParts.wood;

import zornco.reploidcraft.entities.armorParts.IPartBack;
import zornco.reploidcraft.entities.armorParts.IPartChest;
import zornco.reploidcraft.entities.armorParts.PartBase;

public class WoodBack extends PartBase implements IPartBack, IPartChest
{

	public WoodBack()
	{
		super(10,  new float[] {0.0F, 1.5F, 1.5F}, new float[] {1.0F, 1.0F});
		// TODO Auto-generated constructor stub
	}

	@Override
	public float getJumpHeight()
	{
		// TODO Auto-generated method stub
		return 0.2F;
	}

	@Override
	public float getJumpDuration()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
