package zornco.reploidcraft.blocks;

import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;

public class TileEntityMechBayController extends TileEntityMechBay{

	Container eventHandler;
	boolean isIC2Validated = false;

	@Override
	public void masterWriteToNBT(NBTTagCompound compound) {
		super.masterWriteToNBT(compound);
		
		
	}

	@Override
	public void masterReadFromNBT(NBTTagCompound compound) {
		super.masterReadFromNBT(compound);

		
	}	
}
