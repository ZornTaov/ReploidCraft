package org.zornco.reploidcraft.entities;

import java.util.Arrays;
import java.util.UUID;

import javax.annotation.Nullable;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.entities.armorparts.IPartArm;
import org.zornco.reploidcraft.entities.armorparts.IPartBack;
import org.zornco.reploidcraft.entities.armorparts.IPartBody;
import org.zornco.reploidcraft.entities.armorparts.IPartChest;
import org.zornco.reploidcraft.entities.armorparts.IPartHead;
import org.zornco.reploidcraft.entities.armorparts.IPartLegs;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;

import static org.zornco.reploidcraft.entities.armorparts.PartSlot.ARMLEFT;
import static org.zornco.reploidcraft.entities.armorparts.PartSlot.ARMRIGHT;
import static org.zornco.reploidcraft.entities.armorparts.PartSlot.BACK;
import static org.zornco.reploidcraft.entities.armorparts.PartSlot.BODY;
import static org.zornco.reploidcraft.entities.armorparts.PartSlot.HEAD;
import static org.zornco.reploidcraft.entities.armorparts.PartSlot.LEGS;

import com.google.common.base.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IInventoryChangedListener;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializer;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityRideArmor extends EntityAnimal implements IEntityMultiPart, IInventoryChangedListener, IJumpingMount
{
	private static final IAttribute JUMP_STRENGTH = (new RangedAttribute((IAttribute)null, "rideArmor.jumpStrength", 0.7D, 0.0D, 2.0D)).setDescription("Jump Strength").setShouldWatch(true);
	//private static final UUID ARMOR_MODIFIER_UUID = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
	private static final DataParameter<Byte> STATUS = EntityDataManager.<Byte>createKey(EntityRideArmor.class, DataSerializers.BYTE);
	//private static final DataParameter<Integer> RIDEARMOR_TYPE = EntityDataManager.<Integer>createKey(EntityRideArmor.class, DataSerializers.VARINT);
	//private static final DataParameter<Integer> RIDEARMOR_VARIANT = EntityDataManager.<Integer>createKey(EntityRideArmor.class, DataSerializers.VARINT);
	private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.<Optional<UUID>>createKey(EntityRideArmor.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final DataParameter<String> PARTS_STRING = EntityDataManager.<String>createKey(EntityRideArmor.class, DataSerializers.STRING);
	//private static final DataParameter<Integer> RIDEARMOR_ARMOR = EntityDataManager.<Integer>createKey(EntityRideArmor.class, DataSerializers.VARINT);
	//private static final String[] RIDEARMOR_TEXTURES = new String[] {"textures/entity/rideArmor/rideArmor_white.png", "textures/entity/rideArmor/rideArmor_creamy.png", "textures/entity/rideArmor/rideArmor_chestnut.png", "textures/entity/rideArmor/rideArmor_brown.png", "textures/entity/rideArmor/rideArmor_black.png", "textures/entity/rideArmor/rideArmor_gray.png", "textures/entity/rideArmor/rideArmor_darkbrown.png"};
	//private static final String[] RIDEARMOR_TEXTURES_ABBR = new String[] {"hwh", "hcr", "hch", "hbr", "hbl", "hgr", "hdb"};
	//private static final String[] RIDEARMOR_MARKING_TEXTURES = new String[] {null, "textures/entity/rideArmor/rideArmor_markings_white.png", "textures/entity/rideArmor/rideArmor_markings_whitefield.png", "textures/entity/rideArmor/rideArmor_markings_whitedots.png", "textures/entity/rideArmor/rideArmor_markings_blackdots.png"};
	//private static final String[] RIDEARMOR_MARKING_TEXTURES_ABBR = new String[] {"", "wo_", "wmo", "wdo", "bdo"};
	//private int eatingHaystackCounter;
	//private int openMouthCounter;
	//private int jumpRearingCounter;
	//public int tailCounter;
	public int sprintCounter;
	protected boolean rideArmorJumping;
	private ContainerHorseChest rideArmorChest;
	private boolean hasReproduced;
	/** "The higher this value, the more likely the rideArmor is to be tamed next time a player rides it." */
	protected float jumpPower;
	private boolean allowStandSliding;
	private boolean skeletonTrap;
	//private float headLean;
	//private float prevHeadLean;
	//private float rearingAmount;
	//private float prevRearingAmount;
	//private float mouthOpenness;
	//private float prevMouthOpenness;
	/** Used to determine the sound that the rideArmor should make when it steps */
	private int gallopTime;
	//private String texturePrefix;
	//private String[] rideArmorTexturesArray = new String[3];
	//private boolean hasTexture = false;
	
	//Ride Armor Parts code
	public EntityRideArmorPart[] rideArmorParts;
	/** The head bounding box of the Ride Armor */
	public EntityRideArmorPart rideArmorHead;
	/** The body bounding box of Ride Armor */
	public EntityRideArmorPart rideArmorBody;
	public EntityRideArmorPart rideArmorBack;
	public EntityRideArmorPart rideArmorFeet;
	public EntityRideArmorPart rideArmorArmLeft;
	public EntityRideArmorPart rideArmorArmRight;

	private String lastPartsString = "";
	
	//Ride Armor AI
	protected EntityAIBase swimming = new EntityAISwimming(this);
	protected EntityAIBase panic = new EntityAIPanic(this, 1.2D);
	protected EntityAIBase wander = new EntityAIWander(this, 0.7D);
	protected EntityAIBase watchClosest = new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F);
	protected EntityAIBase lookIdle = new EntityAILookIdle(this);
	private EnumMobType mobType;

	public EntityRideArmor(World worldIn)
	{
		super(worldIn);
		this.setSize(1.3964844F, 1.6F);
		this.isImmuneToFire = false;
		this.setChested(false);
		this.stepHeight = 1.0F;
		this.initRideArmorChest();
		
		this.rideArmorParts = new EntityRideArmorPart[] { rideArmorHead = new EntityRideArmorPart(this, "EMPTY", HEAD),
				rideArmorBody = new EntityRideArmorPart(this, "EMPTY", BODY), rideArmorBack = new EntityRideArmorPart(this, "EMPTY", BACK),
				rideArmorFeet = new EntityRideArmorPart(this, "EMPTY", LEGS), rideArmorArmLeft = new EntityRideArmorPart(this, "EMPTY", ARMLEFT),
				rideArmorArmRight = new EntityRideArmorPart(this, "EMPTY", ARMRIGHT) };
	}

	public EntityRideArmor(World worldIn, BlockPos p) {
		this(worldIn, p.getX(), p.getY(), p.getZ());
	}
	public EntityRideArmor(World worldIn, double x, double y, double z) {
		this(worldIn);
		this.setPosition(x, y, z);
		this.motionX = this.motionY = this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	protected void initEntityAI()
	{
		this.tasks.addTask(0, swimming);
		this.tasks.addTask(1, panic);
		this.tasks.addTask(6, wander);
		this.tasks.addTask(7, watchClosest);
		this.tasks.addTask(8, lookIdle);
	}

	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(STATUS, Byte.valueOf((byte)0));
		this.dataManager.register(PARTS_STRING, "");
		//this.dataManager.register(RIDEARMOR_TYPE, Integer.valueOf(RideArmorType.RIDEARMOR.getOrdinal()));
		//this.dataManager.register(RIDEARMOR_VARIANT, Integer.valueOf(0));
		this.dataManager.register(OWNER_UNIQUE_ID, Optional.<UUID>absent());
		//this.dataManager.register(RIDEARMOR_ARMOR, Integer.valueOf(RideArmorArmorType.NONE.getOrdinal()));
	}

	/*public void setType(RideArmorType armorType)
	{
		this.dataManager.set(RIDEARMOR_TYPE, Integer.valueOf(armorType.getOrdinal()));
		this.resetTexturePrefix();
	}

	public RideArmorType getType()
	{
		return RideArmorType.getArmorType(((Integer)this.dataManager.get(RIDEARMOR_TYPE)).intValue());
	}

	public void setRideArmorVariant(int variant)
	{
		this.dataManager.set(RIDEARMOR_VARIANT, Integer.valueOf(variant));
		this.resetTexturePrefix();
	}

	public int getRideArmorVariant()
	{
		return ((Integer)this.dataManager.get(RIDEARMOR_VARIANT)).intValue();
	}*/

	/**
	 * Get the name of this object. For players this returns their username
	 */
	public String getName()
	{
		return this.hasCustomName() ? this.getCustomNameTag() : "entity.rideArmor.name";//this.getType().getDefaultName().getUnformattedText();
	}

	private boolean getRideArmorWatchableBoolean(int p_110233_1_)
	{
		return (((Byte)this.dataManager.get(STATUS)).byteValue() & p_110233_1_) != 0;
	}

	private void setRideArmorWatchableBoolean(int p_110208_1_, boolean p_110208_2_)
	{
		byte b0 = ((Byte)this.dataManager.get(STATUS)).byteValue();

		if (p_110208_2_)
		{
			this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 | p_110208_1_)));
		}
		else
		{
			this.dataManager.set(STATUS, Byte.valueOf((byte)(b0 & ~p_110208_1_)));
		}
	}

	/*public boolean isAdultRideArmor()
	{
		return !this.isChild();
	}*/

	/*public boolean isTame()
	{
		return true;//this.getRideArmorWatchableBoolean(2);
	}*/

	public boolean isRidable()
	{
		return true;// this.isAdultRideArmor();
	}

	@Nullable
	public UUID getOwnerUniqueId()
	{
		return (UUID)((Optional)this.dataManager.get(OWNER_UNIQUE_ID)).orNull();
	}

	public void setOwnerUniqueId(@Nullable UUID uniqueId)
	{
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.fromNullable(uniqueId));
	}

	public float getRideArmorSize()
	{
		return 0.5F;
	}

	/**
	 * "Sets the scale for an ageable entity according to the boolean parameter, which says if it's a child."
	 */
	/*public void setScaleForAge(boolean child)
	{
		if (child)
		{
			this.setScale(this.getRideArmorSize());
		}
		else
		{
			this.setScale(1.0F);
		}
	}*/

	public boolean isRideArmorJumping()
	{
		return this.rideArmorJumping;
	}

	/*public void setRideArmorTamed(boolean tamed)
	{
		this.setRideArmorWatchableBoolean(2, tamed);
	}*/

	public void setRideArmorJumping(boolean jumping)
	{
		this.rideArmorJumping = jumping;
	}

	/*public boolean canBeLeashedTo(EntityPlayer player)
	{
		return !this.getType().isUndead() && super.canBeLeashedTo(player);
	}*/

	/*protected void onLeashDistance(float p_142017_1_)
	{
		if (p_142017_1_ > 6.0F && this.isEatingHaystack())
		{
			this.setEatingHaystack(false);
		}
	}*/

	public boolean isChested()
	{
		return /*this.getType().canBeChested() &&*/ this.getRideArmorWatchableBoolean(8);
	}

	/**
	 * Sets the parts of the entity.
	 */
	public void setPartsString()
	{
		String s = "";
		if (this.rideArmorParts != null)
		{
			for (int i = 0; i < rideArmorParts.length; i++)
			{

				s = s + partSwitch(i).getType() + ":";
			}
		}
		this.dataManager.set(PARTS_STRING, String.valueOf(s));
	}

	/**
	 * Gets the parts of the entity.
	 */
	public String getPartsString()
	{
		String s = this.dataManager.get(PARTS_STRING);
		if (!s.equals(""))
		{
			String[] sa = s.split(":");
			for (int i = 0; i < sa.length; i++)
			{
				partSwitch(i).setType(sa[i]);
			}
		}
		return s;
	}

	/*public RideArmorArmorType getRideArmorArmorType()
	{
		return RideArmorArmorType.getByOrdinal(((Integer)this.dataManager.get(RIDEARMOR_ARMOR)).intValue());
	}*/

	/**
	 * Set rideArmor armor stack (for example: new ItemStack(Items.iron_rideArmor_armor))
	 */
	/*public void setRideArmorArmorStack(ItemStack itemStackIn)
	{
		RideArmorArmorType rideArmorarmortype = RideArmorArmorType.getByItemStack(itemStackIn);
		this.dataManager.set(RIDEARMOR_ARMOR, Integer.valueOf(rideArmorarmortype.getOrdinal()));
		this.resetTexturePrefix();

		if (!this.worldObj.isRemote)
		{
			this.getEntityAttribute(SharedMonsterAttributes.ARMOR).removeModifier(ARMOR_MODIFIER_UUID);
			int i = rideArmorarmortype.getProtection();

			if (i != 0)
			{
				this.getEntityAttribute(SharedMonsterAttributes.ARMOR).applyModifier((new AttributeModifier(ARMOR_MODIFIER_UUID, "RideArmor armor bonus", (double)i, 0)).setSaved(false));
			}
		}
	}*/

	public void setChested(boolean chested)
	{
		this.setRideArmorWatchableBoolean(8, chested);
	}

	/*public void setHasReproduced(boolean hasReproducedIn)
	{
		this.hasReproduced = hasReproducedIn;
	}*/

	/*public void setRideArmorSaddled(boolean saddled)
	{
		this.setRideArmorWatchableBoolean(4, saddled);
	}

	public int getTemper()
	{
		return this.temper;
	}

	public void setTemper(int temperIn)
	{
		this.temper = temperIn;
	}*/

	/*public int increaseTemper(int p_110198_1_)
	{
		int i = MathHelper.clamp_int(this.getTemper() + p_110198_1_, 0, this.getMaxTemper());
		this.setTemper(i);
		return i;
	}*/

	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		Entity entity = source.getEntity();
		return this.isBeingRidden() && entity != null && this.isRidingOrBeingRiddenBy(entity) ? false : super.attackEntityFrom(source, amount);
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return !this.isBeingRidden();
	}

	public boolean prepareChunkForSpawn()
	{
		int i = MathHelper.floor(this.posX);
		int j = MathHelper.floor(this.posZ);
		this.world.getBiomeForCoordsBody(new BlockPos(i, 0, j));
		return true;
	}

	/*public void dropChests()
	{
		if (!this.worldObj.isRemote && this.isChested())
		{
			this.dropItem(Item.getItemFromBlock(Blocks.CHEST), 1);
			this.setChested(false);
		}
	}*/

	/*private void eatingRideArmor()
	{
		this.openRideArmorMouth();

		if (!this.isSilent())
		{
			this.worldObj.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, SoundEvents.ENTITY_RIDEARMOR_EAT, this.getSoundCategory(), 1.0F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F);
		}
	}*/

	public void fall(float distance, float damageMultiplier)
	{
		if (distance > 1.0F)
		{
			this.playSound(SoundEvents.BLOCK_ANVIL_LAND, 0.4F, 0.2F);
			ReploidCraft.logger.info(distance);
		}

		int i = MathHelper.ceil((distance * 0.5F - 3.0F) * damageMultiplier);

		if (i > 0)
		{
			this.attackEntityFrom(DamageSource.FALL, (float)i);

			if (this.isBeingRidden())
			{
				for (Entity entity : this.getRecursivePassengers())
				{
					entity.attackEntityFrom(DamageSource.FALL, (float)i);
				}
			}

			IBlockState iblockstate = this.world.getBlockState(new BlockPos(this.posX, this.posY - 0.2D - (double)this.prevRotationYaw, this.posZ));
			Block block = iblockstate.getBlock();

			if (iblockstate.getMaterial() != Material.AIR && !this.isSilent())
			{
				SoundType soundtype = block.getSoundType();
				this.world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, soundtype.getStepSound(), this.getSoundCategory(), soundtype.getVolume() * 0.5F, soundtype.getPitch() * 0.75F);
			}
		}
	}

	/**
	 * Returns number of slots depending rideArmor type
	 */
	private int getChestSize()
	{
		//RideArmorType rideArmortype = this.getType();
		return this.isChested() /*&& rideArmortype.canBeChested()*/ ? 17 : 2;
	}

	private void initRideArmorChest()
	{
		ContainerHorseChest animalchest = this.rideArmorChest;
		this.rideArmorChest = new ContainerHorseChest("RideArmorChest", this.getChestSize());
		this.rideArmorChest.setCustomName(this.getName());

		if (animalchest != null)
		{
			animalchest.removeInventoryChangeListener(this);
			int i = Math.min(animalchest.getSizeInventory(), this.rideArmorChest.getSizeInventory());

			for (int j = 0; j < i; ++j)
			{
				ItemStack itemstack = animalchest.getStackInSlot(j);

				if (itemstack != null)
				{
					this.rideArmorChest.setInventorySlotContents(j, itemstack.copy());
				}
			}
		}

		this.rideArmorChest.addInventoryChangeListener(this);
		//this.updateRideArmorSlots();
		this.itemHandler = new net.minecraftforge.items.wrapper.InvWrapper(this.rideArmorChest);
	}

	/**
	 * Updates the items in the saddle and armor slots of the rideArmor's inventory.
	 */
	/*private void updateRideArmorSlots()
	{
		if (!this.worldObj.isRemote)
		{
			this.setRideArmorSaddled(this.rideArmorChest.getStackInSlot(0) != null);

			if (this.getType().isRideArmor())
			{
				//this.setRideArmorArmorStack(this.rideArmorChest.getStackInSlot(1));
			}
		}
	}*/

	/**
	 * Called by InventoryBasic.onInventoryChanged() on a array that is never filled.
	 */
	public void onInventoryChanged(InventoryBasic invBasic)
	{
		/*RideArmorArmorType rideArmorarmortype = this.getRideArmorArmorType();
		boolean flag = this.isRideArmorSaddled();
		this.updateRideArmorSlots();

		if (this.ticksExisted > 20)
		{
			if (rideArmorarmortype == RideArmorArmorType.NONE && rideArmorarmortype != this.getRideArmorArmorType())
			{
				this.playSound(SoundEvents.ENTITY_RIDEARMOR_ARMOR, 0.5F, 1.0F);
			}
			else if (rideArmorarmortype != this.getRideArmorArmorType())
			{
				this.playSound(SoundEvents.ENTITY_RIDEARMOR_ARMOR, 0.5F, 1.0F);
			}

			if (!flag && this.isRideArmorSaddled())
			{
				this.playSound(SoundEvents.ENTITY_RIDEARMOR_SADDLE, 0.5F, 1.0F);
			}
		}*/
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	public boolean getCanSpawnHere()
	{
		this.prepareChunkForSpawn();
		return super.getCanSpawnHere();
	}

	/*protected EntityRideArmor getClosestRideArmor(Entity entityIn, double distance)
	{
		double d0 = Double.MAX_VALUE;
		Entity entity = null;

		for (Entity entity1 : this.worldObj.getEntitiesInAABBexcluding(entityIn, entityIn.getEntityBoundingBox().addCoord(distance, distance, distance), IS_RIDEARMOR_BREEDING))
		{
			double d1 = entity1.getDistanceSq(entityIn.posX, entityIn.posY, entityIn.posZ);

			if (d1 < d0)
			{
				entity = entity1;
				d0 = d1;
			}
		}

		return (EntityRideArmor)entity;
	}*/

	public double getRideArmorJumpStrength()
	{
		return this.getEntityAttribute(JUMP_STRENGTH).getAttributeValue();
	}

	protected SoundEvent getDeathSound()
	{
		//this.openRideArmorMouth();
		return super.getDeathSound();//this.getType().getDeathSound();
	}

	protected SoundEvent getHurtSound()
	{
		//this.openRideArmorMouth();

		if (this.rand.nextInt(3) == 0)
		{
			//this.makeRideArmorRear();
		}

		return super.getHurtSound();//this.getType().getHurtSound();
	}

	/*public boolean isRideArmorSaddled()
	{
		return this.getRideArmorWatchableBoolean(4);
	}*/

	protected SoundEvent getAmbientSound()
	{
		//this.openRideArmorMouth();

		if (this.rand.nextInt(10) == 0 && !this.isMovementBlocked())
		{
			//this.makeRideArmorRear();
		}

		return super.getAmbientSound();//this.getType().getAmbientSound();
	}

	/*@Nullable
	protected SoundEvent getAngrySound()
	{
		this.openRideArmorMouth();
		this.makeRideArmorRear();
		RideArmorType rideArmortype = this.getType();
		return rideArmortype.isUndead() ? null : (rideArmortype.hasMuleEars() ? SoundEvents.ENTITY_DONKEY_ANGRY : SoundEvents.ENTITY_RIDEARMOR_ANGRY);
	}*/

	protected void playStepSound(BlockPos pos, Block blockIn)
	{
		SoundType soundtype = blockIn.getSoundType();

		if (this.world.getBlockState(pos.up()).getBlock() == Blocks.SNOW_LAYER)
		{
			soundtype = Blocks.SNOW_LAYER.getSoundType();
		}

		/*if (!blockIn.getDefaultState().getMaterial().isLiquid())
		{
			RideArmorType rideArmortype = this.getType();

			if (this.isBeingRidden() && !rideArmortype.hasMuleEars())
			{
				++this.gallopTime;

				if (this.gallopTime > 5 && this.gallopTime % 3 == 0)
				{
					this.playSound(SoundEvents.ENTITY_RIDEARMOR_GALLOP, soundtype.getVolume() * 0.15F, soundtype.getPitch());

					if (rideArmortype == RideArmorType.RIDEARMOR && this.rand.nextInt(10) == 0)
					{
						this.playSound(SoundEvents.ENTITY_RIDEARMOR_BREATHE, soundtype.getVolume() * 0.6F, soundtype.getPitch());
					}
				}
				else if (this.gallopTime <= 5)
				{
					this.playSound(SoundEvents.ENTITY_RIDEARMOR_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
				}
			}
			else if (soundtype == SoundType.WOOD)
			{
				this.playSound(SoundEvents.ENTITY_RIDEARMOR_STEP_WOOD, soundtype.getVolume() * 0.15F, soundtype.getPitch());
			}
			else
			{
				this.playSound(SoundEvents.ENTITY_RIDEARMOR_STEP, soundtype.getVolume() * 0.15F, soundtype.getPitch());
			}
		}*/
		this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, soundtype.getVolume() * 0.15F, soundtype.getPitch());
	}

	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getAttributeMap().registerAttribute(JUMP_STRENGTH);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(53.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22499999403953552D);
	}

	/**
	 * Will return how many at most can spawn in a chunk at once.
	 */
	public int getMaxSpawnedInChunk()
	{
		return 6;
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	protected float getSoundVolume()
	{
		return 0.8F;
	}

	/**
	 * Get number of ticks, at least during which the living entity will be silent.
	 */
	public int getTalkInterval()
	{
		return 400;
	}

	@SideOnly(Side.CLIENT)
	public boolean hasLayeredTextures()
	{
		return true;//this.getType() == RideArmorType.RIDEARMOR;// || this.getRideArmorArmorType() != RideArmorArmorType.NONE;
	}

	/*private void resetTexturePrefix()
	{
		this.texturePrefix = null;
	}*/

	@SideOnly(Side.CLIENT)
	public boolean hasTexture()
	{
		return true;//this.hasTexture;
	}

	/*@SideOnly(Side.CLIENT)
	private void setRideArmorTexturePaths()
	{
		this.texturePrefix = "rideArmor/";
		this.rideArmorTexturesArray[0] = null;
		this.rideArmorTexturesArray[1] = null;
		this.rideArmorTexturesArray[2] = null;
		RideArmorType rideArmortype = this.getType();
		int i = this.getRideArmorVariant();

		if (rideArmortype == RideArmorType.RIDEARMOR)
		{
			int j = i & 255;
			int k = (i & 65280) >> 8;

			if (j >= RIDEARMOR_TEXTURES.length)
			{
				this.hasTexture = false;
				return;
			}

			this.rideArmorTexturesArray[0] = RIDEARMOR_TEXTURES[j];
			this.texturePrefix = this.texturePrefix + RIDEARMOR_TEXTURES_ABBR[j];

			if (k >= RIDEARMOR_MARKING_TEXTURES.length)
			{
				this.hasTexture = false;
				return;
			}

			this.rideArmorTexturesArray[1] = RIDEARMOR_MARKING_TEXTURES[k];
			this.texturePrefix = this.texturePrefix + RIDEARMOR_MARKING_TEXTURES_ABBR[k];
		}
		else
		{
			this.rideArmorTexturesArray[0] = "";
			this.texturePrefix = this.texturePrefix + "_" + rideArmortype + "_";
		}

		RideArmorArmorType rideArmorarmortype = this.getRideArmorArmorType();
		this.rideArmorTexturesArray[2] = rideArmorarmortype.getTextureName();
		this.texturePrefix = this.texturePrefix + rideArmorarmortype.getHash();
		this.hasTexture = true;
	}

	@SideOnly(Side.CLIENT)
	public String getRideArmorTexture()
	{
		if (this.texturePrefix == null)
		{
			this.setRideArmorTexturePaths();
		}

		return this.texturePrefix;
	}

	@SideOnly(Side.CLIENT)
	public String[] getVariantTexturePaths()
	{
		if (this.texturePrefix == null)
		{
			this.setRideArmorTexturePaths();
		}

		return this.rideArmorTexturesArray;
	}*/

	public void openGUI(EntityPlayer playerEntity)
	{
		if (!this.world.isRemote && (!this.isBeingRidden() || this.isPassenger(playerEntity)))// && this.isTame())
		{
			this.rideArmorChest.setCustomName(this.getName());
			//playerEntity.openGuiRideArmorInventory(this, this.rideArmorChest);
		}
	}

	public boolean processInteract(EntityPlayer player, EnumHand hand)
	{
		/*if (stack != null && stack.getItem() == Items.SPAWN_EGG)
		{
			return super.processInteract(player, hand, stack);
		}
		else if (!this.isTame())
		{
			return false;
		}
		else if (this.isTame() && this.isAdultRideArmor() && player.isSneaking())
		{
			this.openGUI(player);
			return true;
		}
		else if (this.isRidable() && this.isBeingRidden())
		{
			return super.processInteract(player, hand, stack);
		}
		else
		{
			if (stack != null)
			{
				if (this.getType().isRideArmor())
				{
					RideArmorArmorType rideArmorarmortype = RideArmorArmorType.getByItemStack(stack);

					if (rideArmorarmortype != RideArmorArmorType.NONE)
					{
						if (!this.isTame())
						{
							//this.makeRideArmorRearWithSound();
							return true;
						}

						this.openGUI(player);
						return true;
					}
				}

				boolean flag = false;


					float f = 0.0F;
					int i = 0;
					int j = 0;

					if (stack.getItem() == Items.WHEAT)
					{
						f = 2.0F;
						i = 20;
						j = 3;
					}
					else if (stack.getItem() == Items.SUGAR)
					{
						f = 1.0F;
						i = 30;
						j = 3;
					}
					else if (Block.getBlockFromItem(stack.getItem()) == Blocks.HAY_BLOCK)
					{
						f = 20.0F;
						i = 180;
					}
					else if (stack.getItem() == Items.APPLE)
					{
						f = 3.0F;
						i = 60;
						j = 3;
					}
					else if (stack.getItem() == Items.GOLDEN_CARROT)
					{
						f = 4.0F;
						i = 60;
						j = 5;

						if (this.isTame() && this.getGrowingAge() == 0)
						{
							flag = true;
							this.setInLove(player);
						}
					}
					else if (stack.getItem() == Items.GOLDEN_APPLE)
					{
						f = 10.0F;
						i = 240;
						j = 10;

						if (this.isTame() && this.getGrowingAge() == 0 && !this.isInLove())
						{
							flag = true;
							this.setInLove(player);
						}
					}

					if (this.getHealth() < this.getMaxHealth() && f > 0.0F)
					{
						this.heal(f);
						flag = true;
					}

					if (!this.isAdultRideArmor() && i > 0)
					{
						if (!this.worldObj.isRemote)
						{
							this.addGrowth(i);
						}

						flag = true;
					}

					if (j > 0 && (flag || !this.isTame()) && this.getTemper() < this.getMaxTemper())
					{
						flag = true;

						if (!this.worldObj.isRemote)
						{
							this.increaseTemper(j);
						}
					}

					if (flag)
					{
						//this.eatingRideArmor();
					}


				if (!this.isTame() && !flag)
				{
					if (stack.interactWithEntity(player, this, hand))
					{
						return true;
					}

					//this.makeRideArmorRearWithSound();
					return true;
				}

				if (!flag && this.getType().canBeChested() && !this.isChested() && stack.getItem() == Item.getItemFromBlock(Blocks.CHEST))
				{
					this.setChested(true);
					this.playSound(SoundEvents.ENTITY_DONKEY_CHEST, 1.0F, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2F + 1.0F);
					flag = true;
					this.initRideArmorChest();
				}

				if (!flag && this.isRidable() && !this.isRideArmorSaddled() && stack.getItem() == Items.SADDLE)
				{
					this.openGUI(player);
					return true;
				}

				if (flag)
				{
					if (!player.capabilities.isCreativeMode)
					{
						--stack.stackSize;
					}

					return true;
				}
			}*/

        ItemStack stack = player.getHeldItem(hand);
		if (this.isRidable() && !this.isBeingRidden())
		{
			if (stack != null && stack.interactWithEntity(player, this, hand))
			{
				return true;
			}
			else
			{
				this.mountTo(player);
				return true;
			}
		}
		else
		{
			return super.processInteract(player, hand);
		}
		//}
	}

	private void mountTo(EntityPlayer player)
	{
		player.rotationYaw = this.rotationYaw;
		player.rotationPitch = this.rotationPitch;

		if (!this.world.isRemote)
		{
			player.startRiding(this);
		}
	}

	/**
	 * Dead and sleeping entities cannot move
	 */
	protected boolean isMovementBlocked()
	{
		return this.isBeingRidden();// && this.isRideArmorSaddled();
	}

	/**
	 * Checks if the parameter is an item which this animal can be fed to breed it (wheat, carrots or seeds depending on
	 * the animal type)
	 */
	/*public boolean isBreedingItem(@Nullable ItemStack stack)
	{
		return false;
	}*/

	private void moveTail()
	{
		//this.tailCounter = 1;
	}

	/**
	 * Called when the mob's health reaches 0.
	 */
	public void onDeath(DamageSource cause)
	{
		super.onDeath(cause);

		if (!this.world.isRemote)
		{
			this.dropChestItems();
		}
	}

	/**
	 * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
	 * use this to react to sunlight and start to burn.
	 */
	public void onLivingUpdate()
	{
		if (this.rand.nextInt(200) == 0)
		{
			this.moveTail();
		}

		super.onLivingUpdate();

		if (!this.world.isRemote)
		{
			if (this.rand.nextInt(900) == 0 && this.deathTime == 0)
			{
				this.heal(1.0F);
			}

			/*if (this.isBreeding() && !this.isAdultRideArmor() && !this.isEatingHaystack())
			{
				EntityRideArmor entityrideArmor = this.getClosestRideArmor(this, 16.0D);

				if (entityrideArmor != null && this.getDistanceSqToEntity(entityrideArmor) > 4.0D)
				{
					this.navigator.getPathToEntityLiving(entityrideArmor);
				}
			}*/
		}
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();

		if (this.world.isRemote && this.dataManager.isDirty())
		{
			this.dataManager.setClean();
			//this.resetTexturePrefix();
		}

		/*if (this.openMouthCounter > 0 && ++this.openMouthCounter > 30)
		{
			this.openMouthCounter = 0;
			this.setRideArmorWatchableBoolean(128, false);
		}

		if (this.canPassengerSteer() && this.jumpRearingCounter > 0 && ++this.jumpRearingCounter > 20)
		{
			this.jumpRearingCounter = 0;
			//this.setRearing(false);
		}

		if (this.tailCounter > 0 && ++this.tailCounter > 8)
		{
			this.tailCounter = 0;
		}*/

		if (this.sprintCounter > 0)
		{
			++this.sprintCounter;

			if (this.sprintCounter > 300)
			{
				this.sprintCounter = 0;
			}
		}

		//this.prevHeadLean = this.headLean;

		/*if (this.isEatingHaystack())
		{
			this.headLean += (1.0F - this.headLean) * 0.4F + 0.05F;

			if (this.headLean > 1.0F)
			{
				this.headLean = 1.0F;
			}
		}
		else
		{*/
		/*this.headLean += (0.0F - this.headLean) * 0.4F - 0.05F;

		if (this.headLean < 0.0F)
		{
			this.headLean = 0.0F;
		}
		//}

		this.prevRearingAmount = this.rearingAmount;*/

		/*if (this.isRearing())
		{
			this.prevHeadLean = this.headLean = 0.0F;
			this.rearingAmount += (1.0F - this.rearingAmount) * 0.4F + 0.05F;

			if (this.rearingAmount > 1.0F)
			{
				this.rearingAmount = 1.0F;
			}
		}
		else
		{*/
		this.allowStandSliding = false;
		//this.rearingAmount += (0.8F * this.rearingAmount * this.rearingAmount * this.rearingAmount - this.rearingAmount) * 0.6F - 0.05F;

		/*if (this.rearingAmount < 0.0F)
		{
			this.rearingAmount = 0.0F;
		}*/
		//}

		//this.prevMouthOpenness = this.mouthOpenness;

		/*if (this.getRideArmorWatchableBoolean(128))
		{
			this.mouthOpenness += (1.0F - this.mouthOpenness) * 0.7F + 0.05F;

			if (this.mouthOpenness > 1.0F)
			{
				this.mouthOpenness = 1.0F;
			}
		}
		else
		{
			this.mouthOpenness += (0.0F - this.mouthOpenness) * 0.7F - 0.05F;

			if (this.mouthOpenness < 0.0F)
			{
				this.mouthOpenness = 0.0F;
			}
		}*/
		if (getMobType() != EnumMobType.empty && getPassengers() == null)
		{
			updateRideArmorAITasks(null);
		}
		if (getMobType() == EnumMobType.empty && getPassengers() != null)
		{
			updateRideArmorAITasks(getPassengers().get(0));
		}
	}
	private void updateRideArmorAITasks(Entity p_70108_1_)
	{
		if (p_70108_1_ != null && !(p_70108_1_ instanceof EntityPlayer))
		{
			if (p_70108_1_ instanceof EntityPig)
			{
				this.tasks.addTask(0, swimming);
				this.tasks.addTask(1, panic);
				this.tasks.addTask(6, wander);
				this.tasks.addTask(7, watchClosest);
				this.tasks.addTask(8, lookIdle);
				setMobType(EnumMobType.pig);
			}
			else if (p_70108_1_ instanceof EntityMob)
			{
				if (p_70108_1_ instanceof EntityZombie)
				{
					//this.getNavigator().setBreakDoors(true);
					this.tasks.addTask(0, swimming);
					//this.tasks.addTask(2, attackOnCollide);
					//this.tasks.addTask(5, moveTowardsRestriction);
					//this.tasks.addTask(6, moveThroughVillage);
					this.tasks.addTask(7, wander);
					this.tasks.addTask(8, watchClosest);
					this.tasks.addTask(8, lookIdle);
					//this.targetTasks.addTask(1, hurtByTarget);
					//this.targetTasks.addTask(2, nearestAttackableTarget);
					setMobType(EnumMobType.zombie);

				}
				/*else if (p_70108_1_ instanceof EntitySkeleton)
				{
					this.tasks.addTask(1, swimming);
					this.tasks.addTask(2, restrictSun);
					this.tasks.addTask(3, fleeSun);
					this.tasks.addTask(4, aiArrowAttack);
					this.tasks.addTask(5, wander);
					this.tasks.addTask(6, watchClosest);
					this.tasks.addTask(6, lookIdle);
					this.targetTasks.addTask(1, hurtByTarget);
					this.targetTasks.addTask(2, nearestAttackableTarget);
					setMobType(EnumMobType.skeleton);

				}*/
				/*else if (p_70108_1_ instanceof EntityCreeper)
				{
					this.tasks.addTask(1, swimming);
					this.tasks.addTask(2, creeperSwell);
					this.tasks.addTask(3, avoidEntity);
					this.tasks.addTask(4, attackOnCollide);
					this.tasks.addTask(5, wander);
					this.tasks.addTask(6, watchClosest);
					this.tasks.addTask(6, lookIdle);
					this.targetTasks.addTask(1, nearestAttackableTarget);
					this.targetTasks.addTask(2, hurtByTarget);
					setMobType(EnumMobType.creeper);
				}*/
			}
			/*else if (p_70108_1_ instanceof EntityVillager)
			{
				this.getNavigator().setAvoidsWater(true);
				this.tasks.addTask(1, attackOnCollide);
				this.tasks.addTask(2, moveTowardsTarget);
				this.tasks.addTask(3, moveThroughVillage);
				this.tasks.addTask(4, moveTowardsRestriction);
				this.tasks.addTask(5, lookAtVillager);
				this.tasks.addTask(6, wander);
				this.tasks.addTask(7, watchClosest);
				this.tasks.addTask(8, lookIdle);
				this.targetTasks.addTask(1, defendVillage);
				this.targetTasks.addTask(2, hurtByTarget);
				this.targetTasks.addTask(3, nearestAttackableTarget);
				setMobType(EnumMobType.villager);
			}*/
			else
			{
				//unknown rider, defaulting
				
				//this.getNavigator().setAvoidsWater(true);
				this.tasks.addTask(0, swimming);
				this.tasks.addTask(1, panic);
				this.tasks.addTask(6, wander);
				this.tasks.addTask(7, watchClosest);
				this.tasks.addTask(8, lookIdle);
				setMobType(EnumMobType.unknown);
			}
		}
		else
		{
			// Player Rider
			
			/*this.getNavigator().setAvoidsWater(false);
			this.getNavigator().setBreakDoors(false);*/

			this.tasks.removeTask(swimming);
			this.tasks.removeTask(panic);
			this.tasks.removeTask(wander);
			this.tasks.removeTask(watchClosest);
			this.tasks.removeTask(lookIdle);
			/*this.tasks.removeTask(attackOnCollide);
			this.tasks.removeTask(moveTowardsTarget);
			this.tasks.removeTask(moveThroughVillage);
			this.tasks.removeTask(moveTowardsRestriction);
			this.tasks.removeTask(lookAtVillager);
			this.targetTasks.removeTask(defendVillage);
			this.tasks.removeTask(creeperSwell);
			this.tasks.removeTask(avoidEntity);
			this.tasks.removeTask(restrictSun);
			this.tasks.removeTask(fleeSun);
			this.tasks.removeTask(aiArrowAttack);
			this.targetTasks.removeTask(hurtByTarget);
			this.targetTasks.removeTask(nearestAttackableTarget);*/

			setMobType(p_70108_1_ instanceof EntityPlayer ? EnumMobType.player : EnumMobType.empty);
		}
		ReploidCraft.logger.info(getMobType());
	}

	public void dropChestItems()
	{
		this.dropItemsInChest(this, this.rideArmorChest);
		//this.dropChests();
	}

	private void dropItemsInChest(Entity entityIn, ContainerHorseChest animalChestIn)
	{
		if (animalChestIn != null && !this.world.isRemote)
		{
			for (int i = 0; i < animalChestIn.getSizeInventory(); ++i)
			{
				ItemStack itemstack = animalChestIn.getStackInSlot(i);

				if (itemstack != null)
				{
					this.entityDropItem(itemstack, 0.0F);
				}
			}
		}
	}

	/*public boolean setTamedBy(EntityPlayer player)
	{
		this.setOwnerUniqueId(player.getUniqueID());
		this.setRideArmorTamed(true);
		return true;
	}*/

	/**
	 * Moves the entity based on the specified heading.
	 */
	public void moveEntityWithHeading(float strafe, float forward)
	{
		if (this.isBeingRidden() && this.canBeSteered())// && this.isRideArmorSaddled())
		{
			EntityLivingBase entitylivingbase = (EntityLivingBase)this.getControllingPassenger();
			this.prevRotationYaw = this.rotationYaw = entitylivingbase.rotationYaw;
			this.rotationPitch = entitylivingbase.rotationPitch * 0.5F;
			this.setRotation(this.rotationYaw, this.rotationPitch);
			this.rotationYawHead = this.renderYawOffset = this.rotationYaw;
			strafe = entitylivingbase.moveStrafing * 0.5F;
			forward = entitylivingbase.moveForward;

			if (forward <= 0.0F)
			{
				forward *= 0.25F;
				this.gallopTime = 0;
			}

			if (this.onGround && this.jumpPower == 0.0F && !this.canWalk() && !this.allowStandSliding)
			{
				strafe = 0.0F;
				forward = 0.0F;
			}

			if (this.jumpPower > 0.0F && !this.isRideArmorJumping() && this.onGround)
			{
				this.motionY = this.getRideArmorJumpStrength() * (double)this.jumpPower;

				if (this.isPotionActive(MobEffects.JUMP_BOOST))
				{
					this.motionY += (double)((float)(this.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
				}

				this.setRideArmorJumping(true);
				this.isAirBorne = true;

				if (forward > 0.0F)
				{
					float f = MathHelper.sin(this.rotationYaw * 0.017453292F);
					float f1 = MathHelper.cos(this.rotationYaw * 0.017453292F);
					this.motionX += (double)(-0.4F * f * this.jumpPower);
					this.motionZ += (double)(0.4F * f1 * this.jumpPower);
					//this.playSound(SoundEvents.ENTITY_RIDEARMOR_JUMP, 0.4F, 1.0F);
				}

				this.jumpPower = 0.0F;
				net.minecraftforge.common.ForgeHooks.onLivingJump(this);
			}

			this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;

			if (this.canPassengerSteer())
			{
				this.setAIMoveSpeed((float)this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getAttributeValue());
				super.moveEntityWithHeading(strafe, forward);
			}
			else if (entitylivingbase instanceof EntityPlayer)
			{
				this.motionX = 0.0D;
				this.motionY = 0.0D;
				this.motionZ = 0.0D;
			}

			if (this.onGround)
			{
				this.jumpPower = 0.0F;
				this.setRideArmorJumping(false);
			}

			this.prevLimbSwingAmount = this.limbSwingAmount;
			double d1 = this.posX - this.prevPosX;
			double d0 = this.posZ - this.prevPosZ;
			float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

			if (f2 > 1.0F)
			{
				f2 = 1.0F;
			}

			this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
			this.limbSwing += this.limbSwingAmount;
		}
		else
		{
			this.jumpMovementFactor = 0.02F;
			super.moveEntityWithHeading(strafe, forward);
		}
	}

	private boolean canWalk() {
		return true;//this.getType().canWalk();
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		//compound.setBoolean("ChestedRideArmor", this.isChested());
		//compound.setInteger("Type", this.getType().getOrdinal());
		//compound.setInteger("Variant", this.getRideArmorVariant());

		if (this.getOwnerUniqueId() != null)
		{
			compound.setString("OwnerUUID", this.getOwnerUniqueId().toString());
		}

		/*if (this.isChested())
		{
			NBTTagList nbttaglist = new NBTTagList();

			for (int i = 2; i < this.rideArmorChest.getSizeInventory(); ++i)
			{
				ItemStack itemstack = this.rideArmorChest.getStackInSlot(i);

				if (itemstack != null)
				{
					NBTTagCompound nbttagcompound = new NBTTagCompound();
					nbttagcompound.setByte("Slot", (byte)i);
					itemstack.writeToNBT(nbttagcompound);
					nbttaglist.appendTag(nbttagcompound);
				}
			}

			compound.setTag("Items", nbttaglist);
		}*/

		/*if (this.rideArmorChest.getStackInSlot(1) != null)
		{
			compound.setTag("ArmorItem", this.rideArmorChest.getStackInSlot(1).writeToNBT(new NBTTagCompound()));
		}

		if (this.rideArmorChest.getStackInSlot(0) != null)
		{
			compound.setTag("SaddleItem", this.rideArmorChest.getStackInSlot(0).writeToNBT(new NBTTagCompound()));
		}*/
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		//this.setChested(compound.getBoolean("ChestedRideArmor"));
		//this.setType(RideArmorType.getArmorType(compound.getInteger("Type")));
		//this.setRideArmorVariant(compound.getInteger("Variant"));
		String s = "";

		if (compound.hasKey("OwnerUUID", 8))
		{
			s = compound.getString("OwnerUUID");
		}
		else
		{
			String s1 = compound.getString("Owner");
			s = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), s1);
		}

		if (!s.isEmpty())
		{
			this.setOwnerUniqueId(UUID.fromString(s));
		}

		IAttributeInstance iattributeinstance = this.getAttributeMap().getAttributeInstanceByName("Speed");

		if (iattributeinstance != null)
		{
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(iattributeinstance.getBaseValue() * 0.25D);
		}

		/*if (this.isChested())
		{
			NBTTagList nbttaglist = compound.getTagList("Items", 10);
			this.initRideArmorChest();

			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound.getByte("Slot") & 255;

				if (j >= 2 && j < this.rideArmorChest.getSizeInventory())
				{
					this.rideArmorChest.setInventorySlotContents(j, ItemStack.loadItemStackFromNBT(nbttagcompound));
				}
			}
		}*/

		/*if (compound.hasKey("ArmorItem", 10))
		{
			ItemStack itemstack = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("ArmorItem"));

			if (itemstack != null && RideArmorArmorType.isRideArmorArmor(itemstack.getItem()))
			{
				this.rideArmorChest.setInventorySlotContents(1, itemstack);
			}
		}*/

		/*if (compound.hasKey("SaddleItem", 10))
		{
			ItemStack itemstack1 = ItemStack.loadItemStackFromNBT(compound.getCompoundTag("SaddleItem"));

			if (itemstack1 != null && itemstack1.getItem() == Items.SADDLE)
			{
				this.rideArmorChest.setInventorySlotContents(0, itemstack1);
			}
		}

		this.updateRideArmorSlots();*/
	}

	/**
	 * Called only once on an entity when first time spawned, via egg, mob spawner, natural spawning etc, but not called
	 * when entity is reloaded from nbt. Mainly used for initializing attributes and inventory
	 */
	@Nullable
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata)
	{
		livingdata = super.onInitialSpawn(difficulty, livingdata);
		RideArmorType rideArmortype = RideArmorType.RIDEARMOR;
		int i = 0;

		if (livingdata instanceof EntityRideArmor.GroupData)
		{
			rideArmortype = ((EntityRideArmor.GroupData)livingdata).rideArmorType;
			i = ((EntityRideArmor.GroupData)livingdata).rideArmorVariant & 255 | this.rand.nextInt(5) << 8;
		}
		else
		{
			/*if (this.rand.nextInt(10) == 0)
			{
				rideArmortype = RideArmorType.DONKEY;
			}
			else
			{*/
			int j = this.rand.nextInt(7);
			int k = this.rand.nextInt(5);
			rideArmortype = RideArmorType.RIDEARMOR;
			i = j | k << 8;
			//}

			livingdata = new EntityRideArmor.GroupData(rideArmortype, i);
		}

		//this.setType(rideArmortype);
		//this.setRideArmorVariant(i);

		if (this.rand.nextInt(5) == 0)
		{
			this.setGrowingAge(-24000);
		}

		/*if (rideArmortype.isUndead())
		{
			this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(15.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
		}
		else
		{*/
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)this.getModifiedMaxHealth());

		if (rideArmortype == RideArmorType.RIDEARMOR)
		{
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(this.getModifiedMovementSpeed());
		}
		else
		{
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.17499999701976776D);
		}
		//}

		/*if (rideArmortype.hasMuleEars())
		{
			this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(0.5D);
		}
		else
		{*/
		this.getEntityAttribute(JUMP_STRENGTH).setBaseValue(this.getModifiedJumpStrength());
		//}

		this.setHealth(this.getMaxHealth());
		return livingdata;
	}

	/**
	 * returns true if all the conditions for steering the entity are met. For pigs, this is true if it is being ridden
	 * by a player and the player is holding a carrot-on-a-stick
	 */
	public boolean canBeSteered()
	{
		Entity entity = this.getControllingPassenger();
		return entity instanceof EntityLivingBase;
	}

	/*@SideOnly(Side.CLIENT)
	public float getGrassEatingAmount(float p_110258_1_)
	{
		return this.prevHeadLean + (this.headLean - this.prevHeadLean) * p_110258_1_;
	}*/

	/*@SideOnly(Side.CLIENT)
	public float getRearingAmount(float p_110223_1_)
	{
		return this.prevRearingAmount + (this.rearingAmount - this.prevRearingAmount) * p_110223_1_;
	}*/

	/*@SideOnly(Side.CLIENT)
	public float getMouthOpennessAngle(float p_110201_1_)
	{
		return this.prevMouthOpenness + (this.mouthOpenness - this.prevMouthOpenness) * p_110201_1_;
	}*/

	@SideOnly(Side.CLIENT)
	public void setJumpPower(int jumpPowerIn)
	{
		if (true)//this.isRideArmorSaddled())
		{
			if (jumpPowerIn < 0)
			{
				jumpPowerIn = 0;
			}
			else
			{
				this.allowStandSliding = true;
				//this.makeRideArmorRear();
			}

			if (jumpPowerIn >= 90)
			{
				this.jumpPower = 1.0F;
			}
			else
			{
				this.jumpPower = 0.4F + 0.4F * (float)jumpPowerIn / 90.0F;
			}
		}
	}

	public boolean canJump()
	{
		return true;//this.isRideArmorSaddled();
	}

	public void handleStartJump(int p_184775_1_)
	{
		ReploidCraft.logger.info(p_184775_1_);
		this.allowStandSliding = true;
		ReploidCraft.logger.info("jump");
		//this.makeRideArmorRear();
	}

	public void handleStopJump()
	{
		ReploidCraft.logger.info("land");
	}

	/**
	 * "Spawns particles for the rideArmor entity. par1 tells whether to spawn hearts. If it is false, it spawns smoke."
	 */
	@SideOnly(Side.CLIENT)
	protected void spawnRideArmorParticles(boolean p_110216_1_)
	{
		EnumParticleTypes enumparticletypes = p_110216_1_ ? EnumParticleTypes.HEART : EnumParticleTypes.SMOKE_NORMAL;

		for (int i = 0; i < 7; ++i)
		{
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			this.world.spawnParticle(enumparticletypes, this.posX + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, this.posY + 0.5D + (double)(this.rand.nextFloat() * this.height), this.posZ + (double)(this.rand.nextFloat() * this.width * 2.0F) - (double)this.width, d0, d1, d2, new int[0]);
		}
	}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id)
	{
		if (id == 7)
		{
			this.spawnRideArmorParticles(true);
		}
		else if (id == 6)
		{
			this.spawnRideArmorParticles(false);
		}
		else
		{
			super.handleStatusUpdate(id);
		}
	}

	public void updatePassenger(Entity passenger)
	{
		super.updatePassenger(passenger);

		if (passenger instanceof EntityLiving)
		{
			EntityLiving entityliving = (EntityLiving)passenger;
			this.renderYawOffset = entityliving.renderYawOffset;
		}

		float f = MathHelper.sin(this.renderYawOffset * (float) Math.PI / 180.0F);
		float f1 = MathHelper.cos(this.renderYawOffset * (float) Math.PI / 180.0F);
		float f2 = 0.2F;

		passenger.setPosition(this.posX + (double) (f2 * f), this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ - (double) (f2 * f1));

		if (passenger instanceof EntityLivingBase)
		{
			((EntityLivingBase)passenger).renderYawOffset = this.renderYawOffset;
		}

	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	public double getMountedYOffset()
	{
		double d0 = super.getMountedYOffset();

		/*if (this.getType() == RideArmorType.SKELETON)
		{
			d0 -= 0.1875D;
		}
		else if (this.getType() == RideArmorType.DONKEY)
		{
			d0 -= 0.25D;
		}*/

		return d0;
	}

	/**
	 * Returns randomized max health
	 */
	private float getModifiedMaxHealth()
	{
		return 15.0F + (float)this.rand.nextInt(8) + (float)this.rand.nextInt(9);
	}

	/**
	 * Returns randomized jump strength
	 */
	private double getModifiedJumpStrength()
	{
		return 0.4000000059604645D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D + this.rand.nextDouble() * 0.2D;
	}

	/**
	 * Returns randomized movement speed
	 */
	private double getModifiedMovementSpeed()
	{
		return (0.44999998807907104D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D + this.rand.nextDouble() * 0.3D) * 0.25D;
	}

	/**
	 * returns true if this entity is by a ladder, false otherwise
	 */
	public boolean isOnLadder()
	{
		return false;
	}

	public float getEyeHeight()
	{
		return this.height;
	}

	public boolean replaceItemInInventory(int inventorySlot, @Nullable ItemStack itemStackIn)
	{
		/*if (inventorySlot == 499 && this.getType().canBeChested())
		{
			if (itemStackIn == null && this.isChested())
			{
				this.setChested(false);
				this.initRideArmorChest();
				return true;
			}

			if (itemStackIn != null && itemStackIn.getItem() == Item.getItemFromBlock(Blocks.CHEST) && !this.isChested())
			{
				this.setChested(true);
				this.initRideArmorChest();
				return true;
			}
		}

		int i = inventorySlot - 400;

		if (i >= 0 && i < 2 && i < this.rideArmorChest.getSizeInventory())
		{
			if (i == 0 && itemStackIn != null && itemStackIn.getItem() != Items.SADDLE)
			{
				return false;
			}
			else if (i != 1 || (itemStackIn == null || RideArmorArmorType.isRideArmorArmor(itemStackIn.getItem())) && this.getType().isRideArmor())
			{
				this.rideArmorChest.setInventorySlotContents(i, itemStackIn);
				this.updateRideArmorSlots();
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			int j = inventorySlot - 500 + 2;

			if (j >= 2 && j < this.rideArmorChest.getSizeInventory())
			{
				this.rideArmorChest.setInventorySlotContents(j, itemStackIn);
				return true;
			}
			else
			{
				return false;
			}
		}*/
		return false;
	}

	/**
	 * For vehicles, the first passenger is generally considered the controller and "drives" the vehicle. For example,
	 * Pigs, RideArmors, and Boats are generally "steered" by the controlling passenger.
	 */
	@Nullable
	public Entity getControllingPassenger()
	{
		return this.getPassengers().isEmpty() ? null : (Entity)this.getPassengers().get(0);
	}

	/**
	 * Get this Entity's EnumCreatureAttribute
	 */
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return /*this.getType().isUndead() ? EnumCreatureAttribute.UNDEAD : */EnumCreatureAttribute.UNDEFINED;
	}

	@Nullable
	protected ResourceLocation getLootTable()
	{
		return super.getLootTable();//this.getType().getLootTable();
	}

	public static class GroupData implements IEntityLivingData
	{
		public RideArmorType rideArmorType;
		public int rideArmorVariant;

		public GroupData(RideArmorType p_i46589_1_, int p_i46589_2_)
		{
			this.rideArmorType = p_i46589_1_;
			this.rideArmorVariant = p_i46589_2_;
		}
	}

	// FORGE
	private net.minecraftforge.items.IItemHandler itemHandler = null; // Initialized by initRideArmorChest above.

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing)
	{
		if (capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return (T) itemHandler;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing)
	{
		return capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}

	@Override
	public void onInventoryChanged(IInventory invBasic) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public World getWorld() {
		return this.world;
	}

	public void setPart(PartSlot slot, String type)
	{
		this.partSwitch(slot.ordinal()).setType(type);
		this.updatePartsString();
	}

	public boolean hasPart(PartSlot part)
	{
		return !this.partSwitch(part.ordinal()).getType().equals("EMPTY");
	}

	@Override
	public boolean attackEntityFromPart(EntityDragonPart dragonPart,
			DamageSource source, float damage) {
		EntityRideArmorPart part = (EntityRideArmorPart) dragonPart;
		/*if (part.getHealth() <= 0.0F)
		{
			return false;
		}*/
		if ((float) part.hurtResistantTime > (float) part.maxHurtResistantTime / 2.0F)
		{
			if (damage <= part.lastDamage)
			{
				return false;
			}

			part.damageEntity(source, damage - part.lastDamage);
			part.lastDamage = damage;
		}
		else
		{
			part.lastDamage = damage;
			part.prevHealth = part.getHealth();
			part.hurtResistantTime = part.maxHurtResistantTime;
			part.damageEntity(source, damage);
			part.hurtTime = this.maxHurtTime = 10;
		}
		ReploidCraft.logger.info("hit on the " + part.getName() + " Health: " + part.getHealth() + "/" + damage + FMLCommonHandler.instance().getEffectiveSide());
		
		//doesn't seem like this is working
		if (part.getHealth() <= 0.0F)
		{
			if (part.getSlot() == ARMLEFT && hasPart(ARMLEFT))
			{
				this.rideArmorArmLeft.setType("EMPTY");
				return true;
			}
			if (part.getSlot() == ARMRIGHT && hasPart(ARMRIGHT))
			{
				this.rideArmorArmRight.setType("EMPTY");
				return true;
			}
			if (part.getSlot() == BACK && hasPart(BACK))
			{
				this.rideArmorBack.setType("EMPTY");
				return true;
			}
			if (part.getSlot() == HEAD)
			{
				if (this.getPassengers() != null && source.getEntity() != this.getPassengers().get(0))
				{
					this.getPassengers().get(0).attackEntityFrom(source, damage);
					return true;
				}
				else if (source.getEntity() == this.getPassengers().get(0))
				{
					//TODO: left attack
					//doMechAttackLeft();
					return true;
				}
			}

			if (part.getSlot() == BODY)
			{
				if (this.getPassengers() != null && source.getEntity() == this.getPassengers().get(0))
				{
					//TODO: left attack
					//doMechAttackLeft();
					return true;
				}
				//TODO: exploud
				//doMechExplode(source);
				return this.attackEntityFrom(source, damage);
			}

			if (part.getSlot() == LEGS)
			{
				// effect legs
				if (this.getRidingEntity() != null) //mech may be in a cart?
				{
					this.getRidingEntity().attackEntityFrom(source, damage);
				}
				this.rideArmorFeet.setType("EMPTY");
				//return this.attackEntityFrom2(var2, var3);
			}
		}

		return false;
	}
	
	/*
	 * SECTION: Logic
	 */
	public enum EnumMobType
	{
		empty(),
		unknown(),
		creeper(),
		skeleton(),
		zombie(),
		pig(),
		villager(),
		player();

	}

	public EntityRideArmorPart partSwitch(int index)
	{
		if (index == HEAD.ordinal())
		{
			return rideArmorHead;
		}
		else if (index == BODY.ordinal())
		{
			return rideArmorBody;
		}
		else if (index == BACK.ordinal())
		{
			return rideArmorBack;
		}
		else if (index == LEGS.ordinal())
		{
			return rideArmorFeet;
		}
		else if (index == ARMLEFT.ordinal())
		{
			return rideArmorArmLeft;
		}
		else if (index == ARMRIGHT.ordinal())
		{
			return rideArmorArmRight;
		}
		else return null;

	}

	private void updatePartsString()
	{
		if (!this.world.isRemote)
		{
			this.setPartsString();
		}
		String s = this.getPartsString();

		if (!lastPartsString.equals(s))
		{
			lastPartsString = s;
		}
	}

	public EnumMobType getMobType()
	{
		return mobType;
	}

	private void setMobType(EnumMobType mobType)
	{
		this.mobType = mobType;
	}
}