package org.zornco.reploidcraft.util;

import net.minecraft.nbt.NBTTagCompound;

public class PlatformPathPoint implements Comparable<PlatformPathPoint> {
    /** the x coordinate */
	public double posX;
    /** the y coordinate */
    public double posY;
    /** the z coordinate */
    public double posZ;
    /** the speed to the next point */
	public float speed;
	/** the time to wait at this  point */
	public float wait;
	public PlatformPathPoint() {}
	public PlatformPathPoint(double x, double y, double z, float speed, float wait)
    {
        this.posX = x;
        this.posY = y;
        this.posZ = z;
        this.speed = speed;
        this.wait = wait;
    }

    /**
     * Returns the squared distance between this coordinates and the coordinates given as argument.
     */
    public double getDistanceSquared(double posX2, double posY2, double posZ2)
    {
    	double var4 = this.posX - posX2;
    	double var5 = this.posY - posY2;
    	double var6 = this.posZ - posZ2;
        return (var4 * var4 + var5 * var5 + var6 * var6);
    }

    /**
     * Return the squared distance between this coordinates and the ChunkCoordinates given as argument.
     */
    public double getDistanceSquaredToChunkCoordinates(PlatformPathPoint par1ChunkCoordinates)
    {
        return this.getDistanceSquared(par1ChunkCoordinates.posX, par1ChunkCoordinates.posY, par1ChunkCoordinates.posZ);
    }

    public PlatformPathPoint(PlatformPathPoint par1platformPathPoint)
    {
        this.posX = par1platformPathPoint.posX;
        this.posY = par1platformPathPoint.posY;
        this.posZ = par1platformPathPoint.posZ;
    }
	@Override
	public int compareTo(PlatformPathPoint par1PlatformPathPoint) {
		return (int)(this.posY == par1PlatformPathPoint.posY ? (this.posZ == par1PlatformPathPoint.posZ ? this.posX - par1PlatformPathPoint.posX : this.posZ - par1PlatformPathPoint.posZ) : this.posY - par1PlatformPathPoint.posY);
	}

    public boolean equals(Object par1Obj)
    {
        if (!(par1Obj instanceof PlatformPathPoint))
        {
            return false;
        }
        else
        {
            PlatformPathPoint var2 = (PlatformPathPoint)par1Obj;
            return this.posX == var2.posX && this.posY == var2.posY && this.posZ == var2.posZ;
        }
    }
    /**
     * Write the stack fields to a NBT object. Return the new NBT object.
     */
    public NBTTagCompound writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setDouble("X", (float)this.posX);
        par1NBTTagCompound.setDouble("Y", (float)this.posY);
        par1NBTTagCompound.setDouble("Z", (float)this.posZ);
        par1NBTTagCompound.setFloat("speed", (float)this.speed);
        par1NBTTagCompound.setFloat("wait", (float)this.wait);
        return par1NBTTagCompound;
    }

    /**
     * Read the stack fields from a NBT object.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.posX = par1NBTTagCompound.getDouble("X");
        this.posY = par1NBTTagCompound.getDouble("Y");
        this.posZ = par1NBTTagCompound.getDouble("Z");
        this.speed = par1NBTTagCompound.getFloat("speed");
        this.wait = par1NBTTagCompound.getFloat("wait");
    }
    public static PlatformPathPoint loadPointFromNBT(NBTTagCompound par0NBTTagCompound)
    {
    	PlatformPathPoint var1 = new PlatformPathPoint();
        var1.readFromNBT(par0NBTTagCompound);
        return var1;
    }
}
