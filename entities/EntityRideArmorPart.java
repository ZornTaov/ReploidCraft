package zornco.reploidcraftenv.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.parts.PartSlot;
import zornco.reploidcraftenv.entities.parts.PartType;
//import zornco.reploidcraftenv.entities.parts.SharedPartsAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.ServersideAttributeMap;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;

public class EntityRideArmorPart extends EntityDragonPart {

	protected PartSlot slot;
	private BaseAttributeMap attributeMap;
	protected float offsetX = 0.0F;
	protected float offsetY = 0.0F;
	protected float offsetZ = 0.0F;
	public EntityRideArmorPart(IEntityMultiPart par1iEntityMultiPart, PartSlot name) {
		this(par1iEntityMultiPart, PartType.EMPTY, name);
	}

	public EntityRideArmorPart(IEntityMultiPart par1iEntityMultiPart, PartType type, PartSlot slot) {
		super(par1iEntityMultiPart, "", 0.0F, 0.0F);
		this.slot = slot;
		this.setType(type);
		float[] size = ReploidCraftEnv.proxy.partRegistry.getPart(type, slot).getSize();
		this.setSize(size[0], size[1]);
		//this.applyEntityAttributes();
		this.setHealth(this.getMaxHealth());

		//System.out.println("created on " + FMLCommonHandler.instance().getEffectiveSide().toString() + " at: " + this.posX + ", " + this.posY + ", " + this.posZ);
	}

	/*private void applyEntityAttributes() {
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.maxHealth);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.knockback);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.speed);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.attackDamage);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.range);
	}*/

	protected void entityInit() {
		this.dataWatcher.addObject(6, Float.valueOf(1.0F));
        this.dataWatcher.addObject(20, String.valueOf(""));
	}

	public float[] getOffsetsPos() {
		return ReploidCraftEnv.proxy.partRegistry.getPart(getType(), slot).getOffsetXYZ();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.dataWatcher.hasChanges())
		{
			//System.out.println(this.dataWatcher.getChanged().toString());
			float[] size = getSize();
			if(this.width != size[0])
			{
				this.width = size[0];
			}
			if(this.height != size[1])
			{
				this.height = size[1];
			}
		}
		
	}

	public float[] getSize()
	{
		return ReploidCraftEnv.proxy.partRegistry.getPart(getType(), slot).getSize();
	}
	public PartType getType() {
		return (PartType.fromString(this.dataWatcher.getWatchableObjectString(20)));
	}

	public EntityRideArmorPart setType(PartType type) {
		this.dataWatcher.updateObject(20, type.toString());
		//if(this.worldObj.isRemote)System.out.println(FMLCommonHandler.instance().getEffectiveSide() + " set type for " + this.slot + " to " + this.getType());
		return this;
	}
	public PartSlot getName() {
		return slot;
	}

	public EntityRideArmorPart setName(PartSlot name) {
		this.slot = name;
		return this;
	}

	/**
	 * Heal living entity (param: amount of half-hearts)
	 */
	public void heal(float par1)
	{
		float f1 = this.getHealth();

		if (f1 > 0.0F)
		{
			this.setHealth(f1 + par1);
		}
	}

	public final float getHealth()
	{
		return this.dataWatcher.getWatchableObjectFloat(6);
	}

	public void setHealth(float par1)
	{
		this.dataWatcher.updateObject(6, Float.valueOf(MathHelper.clamp_float(par1, 0.0F, this.getMaxHealth())));
	}

	public final float getMaxHealth()
	{
		//IAttributeInstance Hp = this.getEntityAttribute(SharedPartsAttributes.maxHealth);
		//return Hp != null ? (float)Hp.getAttributeValue():20.0F;
		return 20.0F;
	}

	public IAttributeInstance getEntityAttribute(IAttribute par1Attribute)
	{
		return this.getAttributeMap().getAttributeInstance(par1Attribute);
	}

	public BaseAttributeMap getAttributeMap()
	{
		if (this.attributeMap == null)
		{
			this.attributeMap = new ServersideAttributeMap();
		}

		return this.attributeMap;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	 protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		 this.setType(PartType.fromString(par1NBTTagCompound.getString("type")));
		 this.setSize(par1NBTTagCompound.getFloat("width"), par1NBTTagCompound.getFloat("height"));

	 }

	 @Override
	 public String toString() {
		 NBTTagCompound tags = new NBTTagCompound();
		 writeEntityToNBT(tags);
		 return tags.toString();
	 }

	 /**
	  * (abstract) Protected helper method to write subclass entity data to NBT.
	  */
	 protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		 par1NBTTagCompound.setString("type", getType().toString());
		 par1NBTTagCompound.setFloat("height", height);
		 par1NBTTagCompound.setFloat("width", width);

	 }

	 @Override
	 public boolean interactFirst(EntityPlayer par1EntityPlayer) {
		 ItemStack itemstack = par1EntityPlayer.inventory.getCurrentItem();
		 if (par1EntityPlayer.isSneaking())
		 {
			 if (itemstack != null)
			 {
				 boolean flag = false;

				 if (itemstack.getItem() == Items.wheat)
				 {
					 ((EntityRideArmor) this.entityDragonObj).setPart(this.slot, PartType.RED);
				 }
			 }
			 return true;
		 }
		 return ((Entity) entityDragonObj).interactFirst(par1EntityPlayer);
	 }
}