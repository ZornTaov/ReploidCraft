package zornco.reploidcraftenv.entities;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.utils.RiderState;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.boss.EntityDragonPart;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

public class EntityRideArmor extends Entity implements IEntityMultiPart
{
	/** true if no player in rideArmor */
	private boolean isRideArmorEmpty;
	private double speedMultiplier;
	private int rideArmorPosRotationIncrements;
	private double rideArmorX;
	private double rideArmorY;
	private double rideArmorZ;
	private double rideArmorYaw;
	private double rideArmorPitch;
	@SideOnly(Side.CLIENT)
	private double velocityX;
	@SideOnly(Side.CLIENT)
	private double velocityY;
	@SideOnly(Side.CLIENT)
	private double velocityZ;
	public float prevLimbSwingAmount;
	public float limbSwing;
	public float limbSwingAmount;
	public RiderState riderState;
	private boolean isHighJumping;
	public float prevSitAngle;
	public float sitAngle;
	/** An array containing all body parts of this Ride Armor, have to use EntityDragonPart even though it's a generic part and IEntityMultiPart solely uses it */
	private EntityRideArmorPart[] rideArmorParts;
	private String[] partsNamesList = {"head", "body", "back", "armLeft", "armRight", "feet"};
	/** The head bounding box of the Ride Armor */
	//public EntityRideArmorPart rideArmorHead;
	/** The body bounding box of Ride Armor */
	/*public EntityRideArmorPart rideArmorBody;
	/*public EntityRideArmorPart rideArmorBack;
	public EntityRideArmorPart rideArmorFeet;
	public EntityRideArmorPart rideArmorArmLeft;
	public EntityRideArmorPart rideArmorArmRight;*/
	public HashMap<String, EntityRideArmorPart> rideArmorPartsMap;
	private static final String __OBFID = "CL_00001667";

	public EntityRideArmor(World par1World)
	{
		super(par1World);
		
		/*this.rideArmorParts = new EntityRideArmorPart[] {new EntityRideArmorPart(this, "head", 2.0F, 2.0F), new EntityRideArmorPart(this, "body", 2.0F, 3.0F), 
				new EntityRideArmorPart(this, "legs", 1.0F, 1.0F), new EntityRideArmorPart(this, "back", 1.0F, 1.0F),
				new EntityRideArmorPart(this, "armLeft", 2.0F, 1.0F), new EntityRideArmorPart(this, "armRight", 2.0F, 1.0F)};*/
		this.isRideArmorEmpty = true;
		this.speedMultiplier = 0.07D;
		this.preventEntitySpawning = true;
		this.setSize(.9F, 3F);
		this.yOffset = /*this.height / 2*/.0F;
		this.riderState = new RiderState();
		this.stepHeight = 1.0F;
	}

	public EntityRideArmor(World par1World, double par2, double par4, double par6)
	{
		this(par1World);
		this.setPosition(par2, par4 + (double)this.yOffset, par6);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = par2;
		this.prevPosY = par4;
		this.prevPosZ = par6;
		
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	protected boolean canTriggerWalking()
	{
		return true;
	}

	protected void entityInit()
	{
		this.dataWatcher.addObject(17, new Integer(0));
		this.dataWatcher.addObject(18, new Integer(1));
		this.dataWatcher.addObject(19, new Float(0.0F));
		if(true)
		{
			if(this.rideArmorPartsMap == null)
			{
				System.out.println("dafuq happened on the " + FMLCommonHandler.instance().getEffectiveSide().toString());
				this.rideArmorPartsMap = new HashMap<String, EntityRideArmorPart>();
				this.rideArmorPartsMap.put("head", 		new EntityRideArmorPart(this, "green", "head", 2.0F, 2.0F));
				this.rideArmorPartsMap.put("body", 		new EntityRideArmorPart(this, "green", "body", 2.0F, 3.0F));
				this.rideArmorPartsMap.put("back", 		new EntityRideArmorPart(this, "green", "back", 1.0F, 1.0F));
				this.rideArmorPartsMap.put("legs", 		new EntityRideArmorPart(this, "green", "legs", 1.0F, 1.0F));
				this.rideArmorPartsMap.put("armRight", 	new EntityRideArmorPart(this, "green", "armRight", 2.0F, 1.0F));
				this.rideArmorPartsMap.put("armLeft", 	new EntityRideArmorPart(this, "green", "armLeft", 2.0F, 1.0F));
			}
			else
			{
			}
		}

	}

	public void setRiderState(RiderState riderState) {
		this.riderState.setMoveStrafe(riderState.getMoveStrafe());
		this.riderState.setMoveForward(riderState.getMoveForward());
		this.riderState.setJump(riderState.isJump());
		this.riderState.setSneak(riderState.isSneak());
		//if(this.riderState.isJump() && FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		//System.out.println(this.riderState.isJump());
	}

	public void updateRiderPosition()
	{
		if (this.riddenByEntity != null)
		{
			double d0 = Math.sin((double)this.rotationYaw * Math.PI / 180.0D) * 0.2D;
			double d1 = -Math.cos((double)this.rotationYaw * Math.PI / 180.0D) * 0.2D;
			this.riddenByEntity.setPosition(this.posX + d0, this.posY + this.getMountedYOffset() + this.riddenByEntity.getYOffset(), this.posZ + d1);
		}
	}

	/**
	 * Returns the Y offset from the entity's position for any entity riding this one.
	 */
	public double getMountedYOffset()
	{
		float f10 = (float)(Math.sin((double)(this.sitAngle * (float)Math.PI +0.75F)))*0.7375F-0.5F;
		return (double)this.height * 0.0D + 2.125D + f10;
	}

	/**
	 * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
	 * pushable on contact, like rideArmors or minecarts.
	 */
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.boundingBox;
	}

	/**
	 * returns the bounding box for this entity
	 */
	public AxisAlignedBB getBoundingBox()
	{
		return this.boundingBox;
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	public boolean canBePushed()
	{
		return this.riddenByEntity != null;
	}

	@Override
	public boolean canRiderInteract() {
		return true;
	}

	@Override
	public Entity[] getParts() {
		if(rideArmorPartsMap != null){
		rideArmorParts = new EntityRideArmorPart[rideArmorPartsMap.size()];
		int i = 0;
		String s = "";
		for (String part : rideArmorPartsMap.keySet()) {
			rideArmorParts[i] = rideArmorPartsMap.get(part);
			i++;
			s += part + " ";
		}
		//System.out.println(s);
		//System.out.println("getParts" + (rideArmorParts.length));
		return rideArmorParts;
		}
		return null;
	}

	@Override
	public World func_82194_d() {
		return this.worldObj;
	}

	@Override
	public boolean attackEntityFromPart(EntityDragonPart var1, DamageSource var2, float var3) 
	{
		if(var1.field_146032_b == "armLeft" && this.rideArmorPartsMap.containsKey("armLeft"))
		{
			worldObj.removeEntity(rideArmorPartsMap.get("armLeft"));
			System.out.println("hit on the " + FMLCommonHandler.instance().getEffectiveSide().toString());
			//this.rideArmorPartsMap.get("armLeft").setDead();
		}
		if(var1.field_146032_b == "armRight" && this.rideArmorPartsMap.containsKey("armRight"))
		{
			worldObj.removeEntity(rideArmorPartsMap.get("armRight"));
			System.out.println("hit on the " + FMLCommonHandler.instance().getEffectiveSide().toString());
			//this.rideArmorPartsMap.get("armLeft").setDead();
		}
		if (this.isEntityInvulnerable())
		{
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead)
		{
			if(var2.getEntity() != null)// && var2.getEntity() == this.riddenByEntity)
			{
				System.out.println("hit " + var1.field_146032_b);
				if(var1.field_146032_b == "armLeft" && this.rideArmorPartsMap.containsKey("armLeft"))
				{
					worldObj.removeEntity(rideArmorPartsMap.get("armLeft"));
					System.out.println("dong");
					//this.rideArmorPartsMap.get("armLeft").setDead();
				}
				if(var1.field_146032_b == "head")
				{
					if(this.riddenByEntity != null && var2.getEntity() != this.riddenByEntity)
					{
						this.riddenByEntity.attackEntityFrom(var2, var3);
						//return true;
					}
				}

				if(var1.field_146032_b == "body")
				{
					if(this.riddenByEntity != null && var2.getEntity() != this.riddenByEntity)
					{
						
					}
				}
				//return false;
			}
			//System.out.println("hit " + var1.field_146032_b);
			this.setForwardDirection(-this.getForwardDirection());
			this.setTimeSinceHit(10);
			this.setDamageTaken(this.getDamageTaken() + var3 * 10.0F);
			this.setBeenAttacked();
			boolean flag = var2.getEntity() instanceof EntityPlayer && ((EntityPlayer)var2.getEntity()).capabilities.isCreativeMode;

			if (flag || this.getDamageTaken() > 40.0F)
			{
				System.out.println("Hit");
				if (this.riddenByEntity != null)
				{
					this.riddenByEntity.mountEntity(this);
				}

				if (!flag)
				{
					this.func_145778_a(ReploidCraftEnv.rideArmorPlacer, 1, 0.0F);
				}
				if (var2.getEntity() != this.riddenByEntity)
					this.setDead();
			}

			return true;
		}
		else
		{
			return true;
		}
	}


	/**
	 * Called when the entity is attacked.
	 */
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		return false;
	}

	public boolean attackEntityFrom2(DamageSource par1DamageSource, float par2)
	{
		return super.attackEntityFrom(par1DamageSource, par2);

	}
	/**
	 * Setups the entity to do the hurt animation. Only used by packets in multiplayer.
	 */
	@SideOnly(Side.CLIENT)
	public void performHurtAnimation()
	{
		this.setForwardDirection(-this.getForwardDirection());
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	/**
	 * First layer of player interaction
	 */
	public boolean interactFirst(EntityPlayer par1EntityPlayer)
	{
		//System.out.println("Ding");
		boolean ridden = this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer && this.riddenByEntity != par1EntityPlayer;
		if (ridden)
		{
			return true;
		}
		else
		{
			if (!this.worldObj.isRemote)
			{
				if(this.riddenByEntity == null || ridden)
					par1EntityPlayer.mountEntity(this);
			}

			return true;
		}
	}

	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	public void applyEntityCollision(Entity par1Entity) {

		if (this.riddenByEntity == null && par1Entity.ridingEntity == null)
		{
			par1Entity.mountEntity(this);
		}
		else
		{
			super.applyEntityCollision(par1Entity);
		}

	}

	@Override
	public void moveEntity(double par1, double par3, double par5) {
		if ((this.riddenByEntity instanceof EntityPlayer))
		{
			if (this.riderState.isJump() && !isHighJumping)
			{

				par3 += 5D;
				this.isHighJumping = true;

			}
			else
			{
				//this.motionY = -0.3D;
			}
		}
		super.moveEntity(par1, par3, par5);
	}

	/**
	 * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	 * posY, posZ, yaw, pitch
	 */
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double par1, double par3, double par5, float par7, float par8, int par9)
	{
		if (this.isRideArmorEmpty)
		{
			this.rideArmorPosRotationIncrements = par9 + 5;
		}
		else
		{
			double d3 = par1 - this.posX;
			double d4 = par3 - this.posY;
			double d5 = par5 - this.posZ;
			double d6 = d3 * d3 + d4 * d4 + d5 * d5;

			if (d6 <= 1.0D)
			{
				return;
			}

			this.rideArmorPosRotationIncrements = 3;
		}

		this.rideArmorX = par1;
		this.rideArmorY = par3;
		this.rideArmorZ = par5;
		this.rideArmorYaw = (double)par7;
		this.rideArmorPitch = (double)par8;
		this.motionX = this.velocityX;
		this.motionY = this.velocityY;
		this.motionZ = this.velocityZ;
	}

	/**
	 * Sets the velocity to the args. Args: x, y, z
	 */
	@SideOnly(Side.CLIENT)
	public void setVelocity(double par1, double par3, double par5)
	{
		this.velocityX = this.motionX = par1;
		this.velocityY = this.motionY = par3;
		this.velocityZ = this.motionZ = par5;
	}

	private boolean isMovementBlocked() {
		return this.isRideArmorEmpty && this.sitAngle == 1.0F;
	}
	/**
	 * Called to update the entity's position/logic.
	 */
	public void onUpdate()
	{
		super.onUpdate();
		this.stepHeight = 1.0F;
		//System.out.println(this.worldObj.isRemote);
		//this.setDead();
		if (this.getTimeSinceHit() > 0)
		{
			this.setTimeSinceHit(this.getTimeSinceHit() - 1);
		}

		if (this.getDamageTaken() > 0.0F)
		{
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}
		if ((this.isHighJumping) && (this.onGround))
		{
			this.isHighJumping = false;
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (Math.abs(this.motionX) < 0.0001D)
		{
			this.motionX = 0.0D;
		}

		if (Math.abs(this.motionY) < 0.0001D)
		{
			this.motionY = 0.0D;
		}

		if (Math.abs(this.motionZ) < 0.0001D)
		{
			this.motionZ = 0.0D;
		}
		byte b0 = 5;
		double d0 = 0.0D;

		/*for (int i = 0; i < b0; ++i)
		{
			double d1 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 0) / (double)b0 - 0.125D;
			double d3 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(i + 1) / (double)b0 - 0.125D;
			AxisAlignedBB axisalignedbb = AxisAlignedBB.getAABBPool().getAABB(this.boundingBox.minX, d1, this.boundingBox.minZ, this.boundingBox.maxX, d3, this.boundingBox.maxZ);

			if (this.worldObj.isAABBInMaterial(axisalignedbb, Material.water))
			{
				d0 += 1.0D / (double)b0;
			}
		}*/
		if (this.isMovementBlocked())
		{
			this.isHighJumping = false;
			this.motionX = 0.0F;
			this.motionZ= 0.0F;
		}
		double d10 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
		double d2;
		double d4;
		int j;

		double d11;
		double d12;

		//System.out.println( rotationYaw + " " + this.riddenByEntity.rotationYaw);
		if (this.worldObj.isRemote && this.isRideArmorEmpty)
		{
			if (this.rideArmorPosRotationIncrements > 0)
			{
				d2 = this.posX + (this.rideArmorX - this.posX) / (double)this.rideArmorPosRotationIncrements;
				d4 = this.posY + (this.rideArmorY - this.posY) / (double)this.rideArmorPosRotationIncrements;
				d11 = this.posZ + (this.rideArmorZ - this.posZ) / (double)this.rideArmorPosRotationIncrements;
				d12 = MathHelper.wrapAngleTo180_double(this.rideArmorYaw - (double)this.rotationYaw);
				this.rotationYaw = (float)((double)this.rotationYaw + d12 / (double)this.rideArmorPosRotationIncrements);
				this.rotationPitch = (float)((double)this.rotationPitch + (this.rideArmorPitch - (double)this.rotationPitch) / (double)this.rideArmorPosRotationIncrements);
				--this.rideArmorPosRotationIncrements;
				this.setPosition(d2, d4, d11);
				this.setRotation(this.rotationYaw, this.rotationPitch);
			}
			else
			{
				d2 = this.posX + this.motionX;
				d4 = this.posY + this.motionY;
				d11 = this.posZ + this.motionZ;
				this.setPosition(d2, d4, d11);

				if (this.onGround)
				{
					this.motionX *= 0.5D;
					this.motionY *= 0.5D;
					this.motionZ *= 0.5D;
				}

				this.motionX *= 0.90D;
				this.motionY *= 0.90D;
				this.motionZ *= 0.90D;
			}

		}
		else
		{
			if (d0 < 1.0D)
			{
				d2 = d0 * 2.0D - 1.0D;
				this.motionY += 0.03999999910593033D * d2;
			}
			else
			{
				if (this.motionY < 0.0D)
				{
					this.motionY /= 1.0D;
				}

				this.motionY += 0.007000000216066837D;
			}

			if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityLivingBase)
			{
				if(this.riddenByEntity.isSprinting()) System.out.println(this.riddenByEntity.isSprinting());
				EntityLivingBase entitylivingbase = (EntityLivingBase)this.riddenByEntity;
				float f = entitylivingbase.rotationYaw + -entitylivingbase.moveStrafing * 90.0F;
				this.motionX += -Math.sin((double)(f * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entitylivingbase.moveForward * 0.05D;
				this.motionZ += Math.cos((double)(f * (float)Math.PI / 180.0F)) * this.speedMultiplier * (double)entitylivingbase.moveForward * 0.05D;

				/*double riderStrafing = ((EntityLivingBase)this.riddenByEntity).moveStrafing * 0.5F;
				double riderForward = ((EntityLivingBase)this.riddenByEntity).moveForward;
				if (riderForward <= 0.0F)
				{
					riderForward *= 0.25F;
				}

				double riderSpeed = riderStrafing * riderStrafing + riderForward * riderForward;
				float friction = 0.546F;
				float friction2 = 0.16277136F/ (friction * friction * friction);
				float speed = 0.1F * friction2;
				if (riderSpeed >= 1.0E-4F)
				{
					riderSpeed = MathHelper.sqrt_double(riderSpeed);

					if (riderSpeed < 1.0F)
					{
						riderSpeed = 1.0F;
					}

					riderSpeed = speed / riderSpeed;
					riderStrafing *= riderSpeed;
					riderForward *= riderSpeed;
					float f4 = MathHelper.sin(this.riddenByEntity.rotationYaw * (float)Math.PI / 180.0F);
					float f5 = MathHelper.cos(this.riddenByEntity.rotationYaw * (float)Math.PI / 180.0F);
					this.motionX += riderStrafing * f5 - riderForward * f4;
					this.motionZ += riderForward * f5 + riderStrafing * f4;
				}*/
			}

			d2 = Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);

			if (d2 > 0.35D)
			{
				d4 = 0.35D / d2;
				this.motionX *= d4;
				this.motionZ *= d4;
				d2 = 0.35D;
			}

			if (d2 > d10 && this.speedMultiplier < 0.35D)
			{
				this.speedMultiplier += (0.35D - this.speedMultiplier) / 35.0D;

				if (this.speedMultiplier > 0.35D)
				{
					this.speedMultiplier = 0.35D;
				}
			}
			else
			{
				this.speedMultiplier -= (this.speedMultiplier - 0.07D) / 35.0D;

				if (this.speedMultiplier < 0.07D)
				{
					this.speedMultiplier = 0.07D;
				}
			}

			int l;

			for (l = 0; l < 4; ++l)
			{
				int i1 = MathHelper.floor_double(this.posX + ((double)(l % 2) - 0.5D) * 0.8D);
				j = MathHelper.floor_double(this.posZ + ((double)(l / 2) - 0.5D) * 0.8D);

				for (int j1 = 0; j1 < 2; ++j1)
				{
					int k = MathHelper.floor_double(this.posY) + j1;
					Block block = this.worldObj.getBlock(i1, k, j);

					if (block == Blocks.snow_layer)
					{
						this.worldObj.setBlockToAir(i1, k, j);
						this.isCollidedHorizontally = false;
					}
					else if (block == Blocks.waterlily)
					{
						this.worldObj.func_147480_a(i1, k, j, true);
						this.isCollidedHorizontally = false;
					}
				}
			}

			this.moveEntity(this.motionX, this.motionY, this.motionZ);

			if (this.isCollidedHorizontally && onGround)
			{
				//this.motionY += 0.42F;
			}
			else
			{
				this.motionX *= 0.90D;
				this.motionY *= 0.95D;
				this.motionZ *= 0.90D;
			}

			this.rotationPitch = 0.0F;
			d4 = (double)this.rotationYaw;
			d11 = this.prevPosX - this.posX;
			d12 = this.prevPosZ - this.posZ;

			if (d11 * d11 + d12 * d12 > 0.001D)
			{
				d4 = (double)((float)(Math.atan2(d12, d11) * 180.0D / Math.PI)+ 90F);
			}
			double d7 = MathHelper.wrapAngleTo180_double(d4 - (double)this.rotationYaw);

			if (d7 > 5.0D)
			{
				d7 = 5.0D;
			}

			if (d7 < -5.0D)
			{
				d7 = -5.0D;
			}

			this.rotationYaw = (float)((double)this.rotationYaw + d7);
			this.setRotation(this.rotationYaw, this.rotationPitch);
			if (!this.worldObj.isRemote)
			{
				List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.expand(0.20000000298023224D, 0.0D, 0.20000000298023224D));

				if (list != null && !list.isEmpty())
				{
					for (int k1 = 0; k1 < list.size(); ++k1)
					{
						Entity entity = (Entity)list.get(k1);

						if (entity != this.riddenByEntity && entity.canBePushed() && entity instanceof EntityRideArmor)
						{
							entity.applyEntityCollision(this);
						}
					}
				}

				if (this.riddenByEntity != null && this.riddenByEntity.isDead)
				{
					this.riddenByEntity = null;
				}
			}
		}
		this.updateParts();
		//this.worldObj.spawnParticle("reddust", this.rideArmorHead.posX , this.rideArmorHead.posY, this.rideArmorHead.posZ, 0.0D, 0.0D, 0.0D);

		if(this.riddenByEntity != null)
			ReploidCraftEnv.proxy.updateRiderState(this, this.riddenByEntity);
		d0 = this.posX - this.prevPosX;
		double d1 = this.posZ - this.prevPosZ;
		float f6 = MathHelper.sqrt_double(d0 * d0 + d1 * d1) * 4.0F;

		if (f6 > 1.0F)
		{
			f6 = 1.0F;
		}
		this.prevLimbSwingAmount = this.limbSwingAmount;
		this.limbSwingAmount += (f6 - this.limbSwingAmount) * 0.4F;
		this.limbSwing += this.limbSwingAmount;
		this.prevSitAngle = this.sitAngle;
		float sitStep = 0.1F;
		//System.out.println(this.sitAngle);
		if ((this.riddenByEntity != null && this.sitAngle > 0.0F) || (this.riddenByEntity == null && this.sitAngle < 1.0F))
		{
			float f1 = this.sitAngle;

			if (this.riddenByEntity == null)
			{
				this.sitAngle += sitStep;
			}
			else
			{
				this.sitAngle -= sitStep;
			}

			if (this.sitAngle > 1.0F)
			{
				this.sitAngle = 1.0F;
			}

			if (this.sitAngle < 0.0F)
			{
				this.sitAngle = 0.0F;
			}

			float f2 = 0.5F;

			if (this.sitAngle < f2 && f1 >= f2)
			{
				double d5 = (double)this.posX + 0.5D;
				double d6 = (double)this.posZ + 0.5D;

				this.worldObj.playSoundEffect(d5, (double)this.posY + 0.5D, d6, "random.chestclosed", 0.5F, this.worldObj.rand.nextFloat() * 0.1F + 0.9F);
			}
		}
	}

	protected void updateParts()
	{
		float f3 = this.rotationYaw * (float)Math.PI / 180.0F;
		float f11 = MathHelper.sin(f3);
		float f4 = MathHelper.cos(f3);
		this.height = 2.5F;
		this.width = 1.4F;
		if(rideArmorPartsMap != null)
		{
			if(rideArmorPartsMap.containsKey("feet"))
			{
				updatePart(rideArmorPartsMap.get("feet"), 1.50F, 1.0F, 0.0F, 0.0F, f3);
			}
			if(rideArmorPartsMap.containsKey("body"))
			{
				updatePart(rideArmorPartsMap.get("body"), 1.50F, 1.50F, 0.0F, 1.5F, f3);		
			}
			if(rideArmorPartsMap.containsKey("head"))
			{
				updatePart(rideArmorPartsMap.get("head"), 0.5F, 1.50F, 0.0F, 3.0F, f3);
			}
			if(rideArmorPartsMap.containsKey("armLeft"))
			{
				updatePart(rideArmorPartsMap.get("armLeft"), 1.0F, 1.0F, 1.25F, 1.0F, f3);
			}
			if(rideArmorPartsMap.containsKey("armRight"))
			{
				updatePart(rideArmorPartsMap.get("armRight"), 1.0F, 1.0F, -1.25F, 1.0F, f3);
			}
		}
	}
	
	private void updatePart(EntityRideArmorPart part, float height, float width, float offsetXZ, float offsetY, float rotation)
	{
		float f11 = MathHelper.sin(rotation);
		float f4 = MathHelper.cos(rotation);
		part.height = height;
		part.width = width;
		part.onUpdate();
		part.setLocationAndAngles(this.posX + (double)(f4 * offsetXZ), this.posY + offsetY, this.posZ + (double)(f11 * offsetXZ), 0.0F, 0.0F);

		if(part.isDead)
		{
			this.rideArmorPartsMap.remove(part.field_146032_b);
		}
	}
	
	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	protected void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {

		/*
			if has left arm
				write left arm
			if has right arm
				write right arm
			if has body arm
				write body
			if has legs arm
				write legs
		 */
        if (this.rideArmorPartsMap != null && !this.rideArmorPartsMap.isEmpty())
        {
        	NBTTagList nbttaglist = new NBTTagList();
        	NBTTagCompound nbttagcompound1;

            for (EntityRideArmorPart part: this.rideArmorPartsMap.values())
            {
                nbttagcompound1 = new NBTTagCompound();

                if (part!= null)
                {
                    part.writeToNBT(nbttagcompound1);
                    System.out.println("wrote " + part.field_146032_b);
                }

                nbttaglist.appendTag(nbttagcompound1);
            }
            par1NBTTagCompound.setTag("Parts", nbttaglist);
        }
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        NBTTagList nbttaglist;
		if (par1NBTTagCompound.hasKey("Parts", 9))
        {
            //rideArmorPartsMap.clear();
            nbttaglist = par1NBTTagCompound.getTagList("Parts", 10);
            for (int i = 0; i < this.partsNamesList.length; ++i)
            {
                EntityRideArmorPart part = this.loadPartFromNBT(nbttaglist.getCompoundTagAt(i), this.partsNamesList[i]);
                System.out.println("read " + this.partsNamesList[i]);
                if(part != null)
                {
                	if(!rideArmorPartsMap.containsKey(partsNamesList[i])) 
                	{
                		rideArmorPartsMap.put(partsNamesList[i], part);
                		System.out.println("a");
                	}
                	else {
                		rideArmorPartsMap.remove(partsNamesList[i]);
                		rideArmorPartsMap.put(partsNamesList[i], part);
                		System.out.println("b");
                	}
                    System.out.println("read happened for" + this.partsNamesList[i]);
                }
            }
        }

	}
	public EntityRideArmorPart loadPartFromNBT(NBTTagCompound par0NBTTagCompound, String name)
    {
		EntityRideArmorPart part = new EntityRideArmorPart(this, name);
		part.readFromNBT(par0NBTTagCompound);
        return part.type == "" ? part : null;
    }

	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 2.0F;
	}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
	 * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
	 */
	protected void updateFallState(double par1, boolean par3)
	{
		int i = MathHelper.floor_double(this.posX);
		int j = MathHelper.floor_double(this.posY);
		int k = MathHelper.floor_double(this.posZ);

		if (par3)
		{
			/*if (this.fallDistance > 3.0F)
			{
				this.fall(this.fallDistance);

				if (!this.worldObj.isRemote && !this.isDead)
				{
					this.setDead();
					int l;

					for (l = 0; l < 3; ++l)
					{
						this.func_145778_a(Item.getItemFromBlock(Blocks.planks), 1, 0.0F);
					}

					for (l = 0; l < 2; ++l)
					{
						this.func_145778_a(Items.stick, 1, 0.0F);
					}
				}

				this.fallDistance = 0.0F;
			}*/
		}
		else if (this.worldObj.getBlock(i, j - 1, k).getMaterial() != Material.water && par1 < 0.0D)
		{
			this.fallDistance = (float)((double)this.fallDistance - par1);
		}
	}

	/**
	 * Sets the damage taken from the last hit.
	 */
	public void setDamageTaken(float par1)
	{
		this.dataWatcher.updateObject(19, Float.valueOf(par1));
	}

	/**
	 * Gets the damage taken from the last hit.
	 */
	public float getDamageTaken()
	{
		return this.dataWatcher.getWatchableObjectFloat(19);
	}

	/**
	 * Sets the time to count down from since the last time entity was hit.
	 */
	public void setTimeSinceHit(int par1)
	{
		this.dataWatcher.updateObject(17, Integer.valueOf(par1));
	}

	/**
	 * Gets the time since the last hit.
	 */
	public int getTimeSinceHit()
	{
		return this.dataWatcher.getWatchableObjectInt(17);
	}

	/**
	 * Sets the forward direction of the entity.
	 */
	public void setForwardDirection(int par1)
	{
		this.dataWatcher.updateObject(18, Integer.valueOf(par1));
	}

	/**
	 * Gets the forward direction of the entity.
	 */
	public int getForwardDirection()
	{
		return this.dataWatcher.getWatchableObjectInt(18);
	}

	/**
	 * true if no player in rideArmor
	 */
	@SideOnly(Side.CLIENT)
	public void setIsRideArmorEmpty(boolean par1)
	{
		this.isRideArmorEmpty = par1;
	}
	
	protected static enum PartName
	{
		HEAD,
		BODY,
		BACK,
		LEGS,
		ARMLEFT,
		ARMRIGHT
	}
}