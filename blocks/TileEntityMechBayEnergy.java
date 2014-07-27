package zornco.reploidcraftenv.blocks;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileEntityMechBayEnergy extends TileEntityMechBay implements IEnergyHandler{

	protected EnergyStorage storage = new EnergyStorage(320000);
	@Override
	public boolean canConnectEnergy(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return isMaster();
	}

	@Override
	public int extractEnergy(ForgeDirection arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return isMaster() ? storage.extractEnergy(arg1, arg2) : hasMaster() ? ((TileEntityMechBayEnergy)worldObj.getTileEntity(getMasterX(), getMasterY(), getMasterZ())).storage.extractEnergy(arg1, arg2) : 0 ;
	}

	@Override
	public int getEnergyStored(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return isMaster() ? storage.getEnergyStored() : hasMaster() ? ((TileEntityMechBayEnergy)worldObj.getTileEntity(getMasterX(), getMasterY(), getMasterZ())).storage.getEnergyStored() : 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection arg0) {
		// TODO Auto-generated method stub
		return isMaster() ? storage.getMaxEnergyStored() : hasMaster() ? ((TileEntityMechBayEnergy)worldObj.getTileEntity(getMasterX(), getMasterY(), getMasterZ())).storage.getMaxEnergyStored() : 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection arg0, int arg1, boolean arg2) {
		// TODO Auto-generated method stub
		return isMaster() ? storage.receiveEnergy(arg1, arg2) : hasMaster() ? ((TileEntityMechBayEnergy)worldObj.getTileEntity(getMasterX(), getMasterY(), getMasterZ())).storage.receiveEnergy(arg1, arg2) : 0;
	}
	@Override
	public void masterWriteToNBT(NBTTagCompound compound) {
		super.masterWriteToNBT(compound);
		storage.writeToNBT(compound);
	}
	@Override
	public void masterReadFromNBT(NBTTagCompound compound) {
		super.masterReadFromNBT(compound);
		storage.readFromNBT(compound);
	}
}
