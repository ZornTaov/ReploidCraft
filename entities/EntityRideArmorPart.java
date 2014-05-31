package zornco.reploidcraftenv.entities;

import zornco.reploidcraftenv.entities.EntityRideArmor.PartName;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class EntityRideArmorPart extends EntityDragonPart {

	protected String type;
	
    public EntityRideArmorPart(IEntityMultiPart par1iEntityMultiPart, String name) {
    	super(par1iEntityMultiPart, name, 0, 0);
	}

	public EntityRideArmorPart(IEntityMultiPart par1iEntityMultiPart, String type, String par2Str, float par3, float par4) {
		super(par1iEntityMultiPart, par2Str, par3, par4);
		this.type = type;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	protected void entityInit() {}

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
    	this.type = par1NBTTagCompound.getString("type");
    	this.setSize(par1NBTTagCompound.getFloat("height"), par1NBTTagCompound.getFloat("width"));
    	
    }

	/**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
    	par1NBTTagCompound.setString("type", type);
    	par1NBTTagCompound.setFloat("height", height);
    	par1NBTTagCompound.setFloat("width", width);
    	
    }

	@Override
	public boolean interactFirst(EntityPlayer par1EntityPlayer) {
		return ((Entity) entityDragonObj).interactFirst(par1EntityPlayer);
	}
}
