package org.zornco.reploidcraft.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public abstract class TileMultiBlock extends TileEntity implements ITickable {
    private boolean hasMaster, isMaster;
    private int masterX, masterY, masterZ;

    @Override
    public void update() {
        if (!world.isRemote) {
            if (hasMaster()) {
                if (isMaster()) {
                    if (checkMultiBlockForm())
                        doMultiBlockStuff();
                    else
                        resetStructure();
                } else {
                    if (!checkForMaster())
                        reset();
                }
            } else {
                // Constantly check if structure is formed until it is.
                if (checkMultiBlockForm())
                    setupStructure();
            }
        }
        else
        {
        	
        }
    	if (world != null && world.getWorldTime() % 20 == 0) world.notifyNeighborsOfStateChange(this.getPos(), this.getBlockType(), false);
    }

    /** Stuff the multiblock will do when formed */
    public abstract void doMultiBlockStuff();

    /** Check that structure is properly formed */
    public abstract boolean checkMultiBlockForm();

    /** Setup all the blocks in the structure*/
    public abstract void setupStructure();

    /** Reset method to be run when the master is gone or tells them to */
    public void reset() {
        masterX = 0;
        masterY = 0;
        masterZ = 0;
        hasMaster = false;
        isMaster = false;
    }

    /** Check that the master exists */
    public boolean checkForMaster() {
        TileEntity tile = world.getTileEntity(new BlockPos(masterX, masterY, masterZ));
        return (tile != null && (tile instanceof TileMultiBlock));
    }

    /** Reset all the parts of the structure */
    public abstract void resetStructure();

    public SPacketUpdateTileEntity getUpdatePacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		writeToNBT(nbttagcompound);
		return new SPacketUpdateTileEntity(this.pos, 3, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
		readFromNBT(pkt.getNbtCompound());
	}

    public abstract void masterWriteToNBT(NBTTagCompound tag);

    public abstract void masterReadFromNBT(NBTTagCompound tag);

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound data) {
        super.writeToNBT(data);
        data.setInteger("masterX", masterX);
        data.setInteger("masterY", masterY);
        data.setInteger("masterZ", masterZ);
        data.setBoolean("hasMaster", hasMaster);
        data.setBoolean("isMaster", isMaster);
        if (hasMaster() && isMaster())
            masterWriteToNBT(data);
		return data;
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        masterX = data.getInteger("masterX");
        masterY = data.getInteger("masterY");
        masterZ = data.getInteger("masterZ");
        hasMaster = data.getBoolean("hasMaster");
        isMaster = data.getBoolean("isMaster");
        if (hasMaster() && isMaster())
            masterReadFromNBT(data);
    }

    public boolean hasMaster() {
        return hasMaster;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public int getMasterX() {
        return masterX;
    }

    public int getMasterY() {
        return masterY;
    }

    public int getMasterZ() {
        return masterZ;
    }

    public void setHasMaster(boolean bool) {
        hasMaster = bool;
    }

    public void setIsMaster(boolean bool) {
        isMaster = bool;
    }

    public void setMasterCoords(BlockPos p) {
        masterX = pos.getX();
        masterY = pos.getY();
        masterZ = pos.getZ();
    }
}
