package org.zornco.reploidcraft.entities;

//import zornco.reploidcraft.entities.parts.SharedPartsAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.ai.attributes.AttributeMap;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;
import org.zornco.reploidcraft.entities.armorparts.SharedPartsAttributes;

public class EntityRideArmorPart extends EntityDragonPart {

	protected PartSlot slot;
	private AttributeMap attributeMap;
	protected float offsetX = 0.0F;
	protected float offsetY = 0.0F;
	protected float offsetZ = 0.0F;
	public int maxHurtResistantTime = 20;
	public float lastDamage;
	public float prevHealth;
	private static final DataParameter<Float> HEALTH = EntityDataManager.<Float>createKey(EntityLivingBase.class, DataSerializers.FLOAT);
	private static final DataParameter<String> TYPE = EntityDataManager.<String>createKey(EntityLivingBase.class, DataSerializers.STRING);

    public int hurtTime;
	public EntityRideArmorPart(IEntityMultiPart par1iEntityMultiPart, PartSlot name) {
		this(par1iEntityMultiPart, "EMPTY", name);
	}

	public EntityRideArmorPart(IEntityMultiPart par1iEntityMultiPart, String type, PartSlot slot) {
		super(par1iEntityMultiPart, "", 0.0F, 0.0F);
		this.slot = slot;
		this.setType(type);
		float[] size = ReploidCraft.proxy.partRegistry.getPart(type, slot).getSize();
		this.setSize(size[0], size[1]);
		this.applyEntityAttributes();
		this.setHealth(this.getMaxHealth());

		//System.out.println("created on " + FMLCommonHandler.instance().getEffectiveSide().toString() + " at: " + this.posX + ", " + this.posY + ", " + this.posZ);
	}

	private void applyEntityAttributes() {
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.maxHealth);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.knockback);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.speed);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.attackDamage);
		this.getAttributeMap().registerAttribute(SharedPartsAttributes.range);
	}

	protected void entityInit() {
        this.dataManager.register(HEALTH, Float.valueOf(1.0F));
        this.dataManager.register(TYPE, String.valueOf(""));
	}

	public float[] getOffsetsPos() {
		return ReploidCraft.proxy.partRegistry.getPart(getType(), slot).getOffsetXYZ();
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if(this.getDataManager().isDirty())
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
		if (this.hurtTime > 0)
        {
            --this.hurtTime;
        }

        if (this.hurtResistantTime > 0)
        {
            --this.hurtResistantTime;
        }

	}
	/**
	 * Deals damage to the entity. If its a EntityPlayer then will take damage from the armor first and then health
	 * second with the reduced value. Args: damageAmount
	 */
	protected void damageEntity(DamageSource p_70665_1_, float p_70665_2_)
	{
		if (!this.getIsInvulnerable())
		{
			this.setHealth(this.getHealth() - p_70665_2_);
		}
	}
	public float[] getSize()
	{
		return ReploidCraft.proxy.partRegistry.getPart(getType(), slot).getSize();
	}
	public String getType() {
		return (this.dataManager.get(TYPE)).toString();
	}

	public EntityRideArmorPart setType(String type) {
		this.dataManager.set(TYPE, type.toString());
		//if(this.worldObj.isRemote)System.out.println(FMLCommonHandler.instance().getEffectiveSide() + " set type for " + this.slot + " to " + this.getType());
		return this;
	}
	public PartSlot getSlot() {
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
        return ((Float)this.dataManager.get(HEALTH)).floatValue();
    }

    public void setHealth(float health)
    {
        this.dataManager.set(HEALTH, Float.valueOf(MathHelper.clamp(health, 0.0F, this.getMaxHealth())));
    }

	public final float getMaxHealth()
	{
		return ReploidCraft.proxy.partRegistry.getPart(this.getType(), slot).getMaxHealth();
	}

	public IAttributeInstance getEntityAttribute(IAttribute par1Attribute)
	{
		return this.getAttributeMap().getAttributeInstance(par1Attribute);
	}

	public AttributeMap getAttributeMap()
	{
		if (this.attributeMap == null)
		{
			this.attributeMap = new AttributeMap();
		}

		return this.attributeMap;
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		this.setType(par1NBTTagCompound.getString("type"));
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
	public boolean processInitialInteract(EntityPlayer par1EntityPlayer, EnumHand hand) {

        ItemStack itemstack = par1EntityPlayer.getHeldItem(hand);

		boolean flag = true;
		if (par1EntityPlayer.isSneaking())
		{
			if (itemstack != null)
			{
				if (itemstack.getItem() == Items.IRON_INGOT)//placeholder for something the a player can do to the armer with a held item
				{
					this.heal(1.0F);
					flag = false;
				}
			}
		}
		if (flag)
		{
			//((EntityRideArmor) this.entityDragonObj).doMechAttackRight();
		}
		return flag && ((Entity) entityDragonObj).processInitialInteract(par1EntityPlayer, hand);

	}
}