package zornco.reploidcraftenv.blocks;

import cofh.api.energy.IEnergyHandler;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TilePowerSupply extends TileEntity implements IEnergyHandler {

	IEnergyHandler[] sides = new IEnergyHandler[6];
	boolean checked = false;
	@Override
	public boolean canConnectEnergy(ForgeDirection arg0) {
		return false;
	}

	@Override
	public int extractEnergy(ForgeDirection arg0, int arg1, boolean arg2) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection arg0) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection arg0) {
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection arg0, int arg1, boolean arg2) {
		return 0;
	}
	@Override
	public void updateEntity() {
		super.updateEntity();
		if(!checked)
			updateCheckedBlocks();
		
	}

	public void updateCheckedBlocks() {
		
	}
}
