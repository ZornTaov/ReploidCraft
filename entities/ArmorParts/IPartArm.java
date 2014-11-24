package zornco.reploidcraftenv.entities.armorParts;

import net.minecraft.item.ItemStack;
import zornco.reploidcraftenv.entities.EntityRideArmor;

public interface IPartArm {
	public boolean isMirrored();
	public float[] getArmPos(PartSlot slot);
	public boolean doAttack(EntityRideArmor mech);
	public ItemStack[] harvestEquivilent();
	public double getDamage();
}
