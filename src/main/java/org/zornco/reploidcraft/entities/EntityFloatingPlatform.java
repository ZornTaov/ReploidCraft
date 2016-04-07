package org.zornco.reploidcraft.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.util.PlatformPathPoint;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityFloatingPlatform extends Entity {
	private static final DataParameter<Boolean> PATH_TYPE = EntityDataManager.<Boolean>createKey(EntityFloatingPlatform.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PATH_DIR = EntityDataManager.<Boolean>createKey(EntityFloatingPlatform.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> PATH_POS = EntityDataManager.<Integer>createKey(EntityFloatingPlatform.class, DataSerializers.VARINT);

	/**
	 * List of absolute int coords starting from where the platform was placed
	 */
	public List<PlatformPathPoint> currentFlightTargets = new ArrayList<PlatformPathPoint>();
	public PlatformPathPoint prevFlightTarget;

	/** Used to create the rotation animation when rendering the propeller. */
	public int innerRotation;
	public double timeSinceStart = 0;
	public double time = 0;

	public EntityFloatingPlatform(World worldIn) {
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(1.0F, 0.5F);
		this.innerRotation = this.rand.nextInt(100000);
	}
	public EntityFloatingPlatform(World worldIn, double x, double y, double z)
	{
		this(worldIn);
		this.setPosition(x, y, z);
		this.motionX = this.motionY = this.motionZ = 0.0D;
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean processInitialInteract(EntityPlayer par1EntityPlayer, ItemStack stack, EnumHand hand)
	{
		if (!this.worldObj.isRemote)
		{
			ItemStack var2 = par1EntityPlayer.inventory.getCurrentItem();

			if (var2 != null && var2.getItem() == Items.diamond)
			{
				//par1EntityPlayer.openGui(ReploidCraft.instance, GuiIds.UPGRADE_STATION, par1World, i, j, k);
				setNextPointPosition();
				//open gui
			}
		}
		return true;

	}
	double nextPosX, nextPosY, nextPosZ;
	@Override
	public void onUpdate() {
		super.onUpdate();
		//ReploidCraft.logger.info(this.currentFlightTargets.size() + " " + this.worldObj.isRemote);
		//this.setDead();
		
		if(worldObj.isRemote)
		{
			++this.innerRotation;
			List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());

	        if (!list.isEmpty())
	        {
	            for (Entity entity : list)
	            {
	                if (!(entity instanceof EntityShulker) && !entity.noClip)
	                {
	                    entity.moveEntity(nextPosX-posX, nextPosY-posY, nextPosZ-posZ);
	                }
	            }
	        }
		}
		else
		{
			
		}
		if(this.currentFlightTargets.size() > 0)
		{
			if(this.getPointPosition() >= this.currentFlightTargets.size())
			{
				this.setPointPosition(0); //sanity check
			}
			if (this.currentFlightTargets.get(getPointPosition()) != null && this.currentFlightTargets.get(getPointPosition()).getDistanceSquared(this.posX, this.posY, this.posZ) < 0.1F)
			{
				//setNextPointPosition();
			}
			else if(this.currentFlightTargets.get(getPointPosition()) == null)
			{
				this.currentFlightTargets.add(new PlatformPathPoint(this.posX, this.posY, this.posZ, 1, 0)); //
			}
			if(this.prevFlightTarget == null)
			{//System.out.println("alive");
				this.prevFlightTarget = (new PlatformPathPoint(this.posX, this.posY, this.posZ, 1, 0)); //
			}
			double targetPosX = this.currentFlightTargets.get(getPointPosition()).posX;
			double targetPosY = this.currentFlightTargets.get(getPointPosition()).posY;
			double targetPosZ = this.currentFlightTargets.get(getPointPosition()).posZ;
			//ReploidCraft.logger.warning("" + targetPosX + " " + targetPosY + " " + targetPosZ);
			float speed = this.currentFlightTargets.get(getPointPosition()).speed;
			double delta_x = targetPosX - this.prevFlightTarget.posX;
			double delta_y = targetPosY - this.prevFlightTarget.posY;
			double delta_z = targetPosZ - this.prevFlightTarget.posZ;
			double goal_dist = Math.sqrt( (delta_x * delta_x) + (delta_y * delta_y) + (delta_z * delta_z) );
			/*if (goal_dist > speed)
			{
			    double ratio = speed / goal_dist;
			    motionX = ratio * delta_x;
			    motionY = ratio * delta_y;
			    motionZ = ratio * delta_z;
				this.prevPosX = this.posX;
				this.prevPosY = this.posY;
				this.prevPosZ = this.posZ;
			    this.setPosition(this.posX + motionX, this.posY + motionY, this.posZ + motionZ);
			}
			else
			{
				this.setPosition(targetPosX + Math.signum(targetPosX) * 0.5D, 
						targetPosY + 0.5D, 
						targetPosZ + Math.signum(targetPosZ) * 0.5D);
				setNextPointPosition();
			}*/
			time = goal_dist*20D;
			nextPosX = lerp(this.prevFlightTarget.posX, targetPosX, time, timeSinceStart);
			nextPosY = lerp(this.prevFlightTarget.posY, targetPosY, time, timeSinceStart);
			nextPosZ = lerp(this.prevFlightTarget.posZ, targetPosZ, time, timeSinceStart);
			
			this.moveEntity(nextPosX-posX, nextPosY-posY, nextPosZ-posZ);
			//ReploidCraft.logger.warning("" + nextPosX + " " + nextPosY + " " + nextPosZ);

			if (timeSinceStart >= time)
			{
				setNextPointPosition();
				timeSinceStart = 0;
			}
			else /*if (ticksExisted%5 == 0)*/
				timeSinceStart++;

			/*nextRenderPosX = lerp(this.prevFlightTarget.posX, targetPosX, time, timeSinceStart);
			nextRenderPosY = lerp(this.prevFlightTarget.posY, targetPosY, time, timeSinceStart);
			nextRenderPosZ = lerp(this.prevFlightTarget.posZ, targetPosZ, time, timeSinceStart);*/
		}
		//TODO: move the platform smoothly
		
	}
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isEntityInvulnerable(source))
        {
            return false;
        }
        else
        {
            if (!this.isDead && !this.worldObj.isRemote)
            {
                this.setDead();
                this.setBeenAttacked();
            }

            return true;
        }
	}
	public int setNextPointPosition()
	{
		int dataPoint = this.getPointPosition();
		if(currentFlightTargets.size() > 1)
		{
			prevFlightTarget = currentFlightTargets.get(dataPoint);
			if (getPathType())//circle
			{
				if(getPathDirection())//direction
				{
					dataPoint++;
					if(dataPoint == currentFlightTargets.size())
					{
						dataPoint = 0;
					}
				}
				else
				{
					dataPoint--;
					if(dataPoint < 0)
					{
						dataPoint = currentFlightTargets.size();
					}
				}
				this.setPointPosition(dataPoint);
			}
			else//pingPong
			{
				if(getPathDirection())//direction
				{
					dataPoint++;
					if(dataPoint == currentFlightTargets.size())
					{
						dataPoint = currentFlightTargets.size() - 1;
						setPathDirection(false);
					}
				}
				else
				{
					dataPoint--;
					if(dataPoint < 0)
					{
						dataPoint = 1;
						setPathDirection(true);
					}
				}
				this.setPointPosition(dataPoint);
			}
		}
		return this.getPointPosition();
	}
	@Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setInteger("Point", this.getPointPosition());
		NBTTagList nbttaglist = new NBTTagList();
		Iterator<PlatformPathPoint> it=currentFlightTargets.iterator();
		byte i = 0;
		while(it.hasNext())
		{
			NBTTagCompound entry = new NBTTagCompound();
			entry = new NBTTagCompound();
			entry.setByte("point", (byte)i);
			((PlatformPathPoint) it.next()).writeToNBT(entry);
			nbttaglist.appendTag(entry);
			i++;
		}
		nbttagcompound.setTag("CordList", nbttaglist);
	}
	@Override
	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{
		this.setPointPosition(nbttagcompound.getInteger("Point"));
		NBTTagList nbttaglist = nbttagcompound.getTagList("CordList", 3);
		this.currentFlightTargets.clear();
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int var5 = nbttagcompound1.getByte("point") & 255;
			this.currentFlightTargets.add(var5, PlatformPathPoint.loadPointFromNBT(nbttagcompound1));
		}
	}
	@Override
	protected void entityInit() {
		this.getDataManager().register(PATH_TYPE, Boolean.valueOf(false));
		this.getDataManager().register(PATH_DIR, Boolean.valueOf(false));
		this.getDataManager().register(PATH_POS, Integer.valueOf(0));
	}
	/**
	 * Returns true if circle, pingpong false
	 */
	protected boolean getPathType()
	{
		return this.getDataManager().get(PATH_TYPE);
	}
	/**
	 * Set Path type, true if circle, pingpong false.
	 */
	protected void setPathType(boolean par1)
	{
		this.getDataManager().set(PATH_TYPE, Boolean.valueOf(par1));
	}
	/**
	 * Returns path direction, true to increment, false to decrement
	 */
	protected boolean getPathDirection()
	{
		return this.getDataManager().get(PATH_DIR);
	}
	/**
	 * Set Path type, true if circle, pingpong false.
	 */
	protected void setPathDirection(boolean par1)
	{
		this.getDataManager().set(PATH_DIR, Boolean.valueOf(par1));
	}
	public int getPointPosition()
	{
		return this.getDataManager().get(PATH_POS);
	}
	public void setPointPosition(int point)
	{
		this.getDataManager().set(PATH_POS, Integer.valueOf((int)point));
	}
	
	public void addPathPoint(PlatformPathPoint e) {
		this.currentFlightTargets.add(e);
	}
	public double lerp(double start, double target, double duration, double timeSinceStart)
	{
		double value = start;
		if (timeSinceStart > 0.0f && timeSinceStart < duration)
		{
			double range = target - start;
			double percent = timeSinceStart / duration;
			value = start + (range * percent);
		}
		else if (timeSinceStart >= duration)
		{
			value = target;
		}
		return value;
	}

	public double ease(double start, double target, double duration, double timeSinceStart)
	{
		double value = start;
		if (timeSinceStart > 0.0f && timeSinceStart < duration)
		{
			final double range = target - start;
			final double percent = timeSinceStart / (duration / 2.0f);
			if (percent < 1.0f)
			{
				value = start + ((range / 2.0f) * percent * percent * percent);
			}
			else
			{
				final double shiftedPercent = percent - 2.0f;
				value = start + ((range / 2.0f) *
						((shiftedPercent * shiftedPercent * shiftedPercent) + 2.0f));
			}
		}
		else if (timeSinceStart >= duration)
		{
			value = target;
		}
		return value;
	}
	/**
     * Applies a velocity to each of the entities pushing them away from each other. Args: entity
     */
    public void applyEntityCollision(Entity entityIn)
    {
    }
    /**
     * Returns the collision bounding box for this entity
     */
    public AxisAlignedBB getCollisionBoundingBox()
    {
        return this.getEntityBoundingBox();
    }
	/**
	 * Returns a boundingBox used to collide the entity with other entities and blocks. This enables the entity to be
	 * pushable on contact, like boats or minecarts.
	 */
	@Override
	public AxisAlignedBB getCollisionBox(Entity par1Entity)
	{
		return par1Entity.getEntityBoundingBox();
	}
	/**
	 * Return whether this entity should NOT trigger a pressure plate or a tripwire.
	 */
	@Override
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}
	/**
	 * Returns true if other Entities should be prevented from moving through this Entity.
	 */
	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}
	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
	 * prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		return false;
	}
	@Override
	public void fall(float distance, float damageMultiplier) { }
	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) { }
}
