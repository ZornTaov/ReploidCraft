package zornco.reploidcraft.entities.armorParts.creeper;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.DamageSource;
import zornco.reploidcraft.entities.EntityRideArmor;
import zornco.reploidcraft.entities.armorParts.IPartBody;
import zornco.reploidcraft.entities.armorParts.PartBase;

public class CreeperBody extends PartBase implements IPartBody
{

	public CreeperBody()
	{
		super(40, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});
	}

	@Override
	public boolean isLavaResistant()
	{
		return false;
	}

	@Override
	public boolean doExplode(EntityRideArmor ride, DamageSource source)
	{
		if(ride.riddenByEntity instanceof EntityCreeper || source.isExplosion())
		{
			boolean flag = ride.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
			ride.worldObj.createExplosion(ride, ride.posX, ride.posY, ride.posZ, 9, flag);
		}
		return false;
	}

}
