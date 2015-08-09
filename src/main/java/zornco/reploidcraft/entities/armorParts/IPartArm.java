package zornco.reploidcraft.entities.armorParts;

import net.minecraft.item.ItemStack;
import zornco.reploidcraft.entities.EntityRideArmor;

public interface IPartArm {
	public boolean isMirrored();
	public float[] getArmPos(PartSlot slot);
	public boolean doAttack(EntityRideArmor mech);
	public ItemStack[] harvestEquivilent();
	public double getDamage();
}
