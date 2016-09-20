package org.zornco.reploidcraft.entities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.init.RCItems;
import org.zornco.reploidcraft.util.PlatformPathPoint;

import com.google.common.base.Optional;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFloatingPlatform extends Entity {
	private static final DataParameter<Boolean> PATH_TYPE = EntityDataManager.<Boolean>createKey(EntityFloatingPlatform.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> PATH_DIR = EntityDataManager.<Boolean>createKey(EntityFloatingPlatform.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> PATH_POS = EntityDataManager.<Integer>createKey(EntityFloatingPlatform.class, DataSerializers.VARINT);
	private static final DataParameter<Float> PATH_TIME = EntityDataManager.<Float>createKey(EntityFloatingPlatform.class, DataSerializers.FLOAT);
	private static final DataParameter<Float> PATH_TIME_TO_NEXT = EntityDataManager.<Float>createKey(EntityFloatingPlatform.class, DataSerializers.FLOAT);
	private static final DataParameter<Optional<ItemStack>> PATH_DATA = EntityDataManager.<Optional<ItemStack>>createKey(EntityFloatingPlatform.class, DataSerializers.OPTIONAL_ITEM_STACK);
    
	/**
	 * List of absolute int coords starting from where the platform was placed
	 */
	public List<PlatformPathPoint> currentFlightTargets = new ArrayList<PlatformPathPoint>();
	public PlatformPathPoint prevFlightTarget;

	/** Used to create the rotation animation when rendering the propeller. */
	public int innerRotation;

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

        //this.dataWatcher.set(PATH_DATA, Optional.of(new ItemStack(RCItems.platformRemote,1,0,new NBTTagCompound())));
	}

	/**
	 * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
	 */
	@Override
	public boolean processInitialInteract(EntityPlayer par1EntityPlayer, ItemStack stack, EnumHand hand)
	{
		//if (!this.worldObj.isRemote)
		//{

			if (stack != null )
			{
				if (stack.getItem() == Items.DIAMOND)
				{
					if(par1EntityPlayer.isSneaking())
					{
					//par1EntityPlayer.openGui(ReploidCraft.instance, GuiIds.UPGRADE_STATION, par1World, i, j, k);
						setNextPointPosition();
					//open gui
					}
					else
					{
						this.setPathType(!this.getPathType());
					}
				}
				else if (stack.getItem() == RCItems.platformRemote)
				{
					if (!stack.hasTagCompound()) {
						stack.setTagCompound(new NBTTagCompound());
					}
					if (stack.getTagCompound().hasKey("hasPath")) {
						stack.getTagCompound().removeTag("hasPath");
						this.readPathFromItem(stack.getTagCompound());
					}
					else
					{
						NBTTagCompound path = new NBTTagCompound();
						this.writePathToItem(stack.getTagCompound());
						stack.getTagCompound().setBoolean("hasPath", true);
					}
				}
			}
		//}
		return true;

	}
	double nextPosX, nextPosY, nextPosZ;
	@Override
	public void onUpdate() {
		super.onUpdate();
		//ReploidCraft.logger.info(this.currentFlightTargets.size() + " " + this.worldObj.isRemote);
		//ReploidCraft.logger.info((this.worldObj.isRemote?"client ":"server ") + this.getPointPosition() + " " + this.posX + " " + this.posY + " " + this.posZ);
		//this.setDead();

		
        
		if(worldObj.isRemote)
		{
			++this.innerRotation;

			
			if (this.innerRotation%30==0)
			for (int i = 0; i < this.currentFlightTargets.size(); i++) { 
				PlatformPathPoint point1 = currentFlightTargets.get(i);
				PlatformPathPoint point2 = currentFlightTargets.get(i != this.currentFlightTargets.size()-1? i+1 : 0);
				this.worldObj.spawnParticle(EnumParticleTypes.CLOUD, point2.posX, point2.posY+0.2, point2.posZ, 0.0, 0.0, 0.0, new int[0]);
				for (int j = 0; j < 20; j++) {
					double targetPosX = point2.posX;
					double targetPosY = point2.posY;
					double targetPosZ = point2.posZ;
					
					float speed = point2.speed;
					double delta_x = targetPosX - point1.posX;
					double delta_y = targetPosY - point1.posY;
					double delta_z = targetPosZ - point1.posZ;
					double goal_dist = Math.sqrt( (delta_x * delta_x) + (delta_y * delta_y) + (delta_z * delta_z) );
					
					//time = goal_dist*20D;
					double nexPosX = lerp(point1.posX, targetPosX, 20, j);
					double nexPosY = lerp(point1.posY, targetPosY, 20, j);
					double nexPosZ = lerp(point1.posZ, targetPosZ, 20, j);
					
					this.worldObj.spawnParticle(EnumParticleTypes.REDSTONE, nexPosX, nexPosY+0.2, nexPosZ, 0.0, 0.0, 0.0, new int[0]);
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
			if (getTimeToNext() >= getTime()) {
				float speed = this.currentFlightTargets.get(getPointPosition()).speed;
				double delta_x = targetPosX - this.prevFlightTarget.posX;
				double delta_y = targetPosY - this.prevFlightTarget.posY;
				double delta_z = targetPosZ - this.prevFlightTarget.posZ;
				float goal_dist = (float) Math.sqrt( (delta_x * delta_x) + (delta_y * delta_y) + (delta_z * delta_z) );
				
				setTimeToNext(goal_dist*20F);;
			}
			
			
			nextPosX = lerp(this.prevFlightTarget.posX, targetPosX, getTimeToNext(), getTime());
			nextPosY = lerp(this.prevFlightTarget.posY, targetPosY, getTimeToNext(), getTime());
			nextPosZ = lerp(this.prevFlightTarget.posZ, targetPosZ, getTimeToNext(), getTime());
			
			this.moveEntity(nextPosX-posX, nextPosY-posY, nextPosZ-posZ);
			//ReploidCraft.logger.warn("" + nextPosX + " " + nextPosY + " " + nextPosZ);

			if (getTime() >= getTimeToNext())
			{
				setNextPointPosition();
				setTime(0);
			}
			else 
				incTime();

			/*nextRenderPosX = lerp(this.prevFlightTarget.posX, targetPosX, time, timeSinceStart);
			nextRenderPosY = lerp(this.prevFlightTarget.posY, targetPosY, time, timeSinceStart);
			nextRenderPosZ = lerp(this.prevFlightTarget.posZ, targetPosZ, time, timeSinceStart);*/
		}
		else
		{
			this.currentFlightTargets.add(new PlatformPathPoint(this.posX, this.posY, this.posZ, 1F, 0));
			this.currentFlightTargets.add(new PlatformPathPoint(this.posX + 1, this.posY, this.posZ, 1F, 0));
			this.currentFlightTargets.add(new PlatformPathPoint(this.posX + 1.0, this.posY + 3.0, this.posZ, 1F, 0));
			this.currentFlightTargets.add(new PlatformPathPoint(this.posX, this.posY + 3.0, this.posZ, 1F, 0));
		}
		//TODO: move the platform smoothly
		List<Entity> list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().offset(0.0, 1, 0.0));
		if (!list.isEmpty())
        {
            for (Entity entity : list)
            {
                if (!(entity instanceof EntityFloatingPlatform) && !entity.noClip)
                {
                	//entity.setPosition(entity.posX, this.nextPosY + .9F + entity.getYOffset(), entity.posZ);
                	entity.moveEntity((this.nextPosX-this.posX), (this.nextPosY-this.posY), (this.nextPosZ-this.posZ));
                	
                	entity.isAirBorne = false;
                	entity.onGround = true;
                	entity.fallDistance = 0;
                    //entity.moveEntity(this.prevPosX-this.posX, this.prevPosY-this.posY, this.prevPosZ-this.posZ);
                    //entity.moveEntity(this.posX-this.prevPosX, this.posY-this.prevPosY, this.posZ-this.prevPosZ);
                }
            }
        }
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
						dataPoint = currentFlightTargets.size()-1;
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
		nbttagcompound.setFloat("Time", this.getTime());
		nbttagcompound.setFloat("TimeToNext", this.getTimeToNext());
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
		this.setTime(nbttagcompound.getFloat("Time"));
		this.setTimeToNext(nbttagcompound.getFloat("TimeToNext"));
		NBTTagList nbttaglist = nbttagcompound.getTagList("CordList", 3);
		this.currentFlightTargets.clear();
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int var5 = nbttagcompound1.getByte("point") & 255;
			this.currentFlightTargets.add(var5, PlatformPathPoint.loadPointFromNBT(nbttagcompound1));
		}
	}
	public void writePathToItem(NBTTagCompound itemnbt)
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		nbttagcompound.setInteger("Point", this.getPointPosition());
		nbttagcompound.setFloat("Time", this.getTime());
		nbttagcompound.setFloat("TimeToNext", this.getTimeToNext());
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
		nbttagcompound.setByte("Size", i);
		nbttagcompound.setTag("CordList", nbttaglist);
		itemnbt.setTag("path", nbttagcompound);
	}
	public void readPathFromItem(NBTTagCompound itemnbt)
	{
		NBTTagCompound nbttagcompound = itemnbt.getCompoundTag("path");
		this.setPointPosition(nbttagcompound.getInteger("Point"));
		this.setTime(nbttagcompound.getFloat("Time"));
		this.setTimeToNext(nbttagcompound.getFloat("TimeToNext"));
		NBTTagList nbttaglist = nbttagcompound.getTagList("CordList", 3);
		this.currentFlightTargets.clear();
		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int var5 = nbttagcompound1.getByte("point") & 255;
			this.currentFlightTargets.add(var5, PlatformPathPoint.loadPointFromNBT(nbttagcompound1));
		}
	}
	public void setupPathNBT(NBTTagCompound nbttagcompound)
	{
		/*nbttagcompound.setInteger("Point", this.getPointPosition());
		nbttagcompound.setFloat("Time", this.getTime());
		nbttagcompound.setFloat("TimeToNext", this.getTimeToNext());*/
		NBTTagList nbttaglist = new NBTTagList();
		byte i = 0;
		NBTTagCompound entry = new NBTTagCompound();
		entry = new NBTTagCompound();
		entry.setByte("Point", (byte)0);
		PlatformPathPoint point = new PlatformPathPoint(this.posX, this.posY, this.posZ, 1, 1);
		point.writeToNBT(entry);
		nbttaglist.appendTag(entry);
		i++;
		
		nbttagcompound.setByte("Size", i);
		nbttagcompound.setTag("CordList", nbttaglist);
	}
	@Override
	protected void entityInit() {
		this.getDataManager().register(PATH_TYPE, Boolean.valueOf(true));
		this.getDataManager().register(PATH_DIR, Boolean.valueOf(false));
		this.getDataManager().register(PATH_POS, Integer.valueOf(0));
		this.getDataManager().register(PATH_TIME, Float.valueOf(0));
		this.getDataManager().register(PATH_TIME_TO_NEXT, Float.valueOf(0));
        this.getDataManager().register(PATH_DATA, Optional.of(new ItemStack(RCItems.platformRemote,1,0,new NBTTagCompound())));
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
	protected int getPointPosition()
	{
		return this.getDataManager().get(PATH_POS);
	}
	protected void setPointPosition(int point)
	{
		this.getDataManager().set(PATH_POS, Integer.valueOf((int)point));
	}
	protected float getTime()
	{
		return this.getDataManager().get(PATH_TIME);
	}
	protected void setTime(float time)
	{
		this.getDataManager().set(PATH_TIME, Float.valueOf((float)time));
	}
	protected void incTime()
	{
		this.getDataManager().set(PATH_TIME, Float.valueOf((float)getTime()+1));
	}
	protected float getTimeToNext()
	{
		return this.getDataManager().get(PATH_TIME_TO_NEXT);
	}
	protected void setTimeToNext(float time)
	{
		this.getDataManager().set(PATH_TIME_TO_NEXT, Float.valueOf((float)time));
	}
	protected ItemStack getPathData()
	{
		ItemStack stack = this.getDataManager().get(PATH_DATA).orNull();
		if (stack == null)
        {
			//shouldn't happen if I do this right, but this should make a new path at it's current position
            stack = new ItemStack(RCItems.platformRemote,1,0,new NBTTagCompound());
            this.setupPathNBT(stack.getTagCompound());
            setPathData(stack);
        }
        return stack;
	}
	protected void setPathData(ItemStack stack)
    {
        this.getDataManager().set(PATH_DATA, Optional.fromNullable(stack));
        this.getDataManager().setDirty(PATH_DATA);
    }
	
	protected void addPathPoint(PlatformPathPoint e) {
		
		NBTTagCompound list= this.getPathData().getTagCompound();
		
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
    	if (!this.isRidingSameEntity(entityIn))
        {
            if (!entityIn.noClip && !this.noClip)
            {
                double d0 = entityIn.posX - this.posX;
                double d1 = entityIn.posZ - this.posZ;
                double d2 = MathHelper.abs_max(d0, d1);

                if (d2 >= 0.01D)
                {
                    d2 = (double)MathHelper.sqrt_double(d2);
                    d0 = d0 / d2;
                    d1 = d1 / d2;
                    double d3 = 1.0D / d2;

                    if (d3 > 1.0D)
                    {
                        d3 = 1.0D;
                    }

                    d0 = d0 * d3;
                    d1 = d1 * d3;
                    d0 = d0 * 0.05000000074505806D;
                    d1 = d1 * 0.05000000074505806D;
                    d0 = d0 * (double)(1.0F - this.entityCollisionReduction);
                    d1 = d1 * (double)(1.0F - this.entityCollisionReduction);

                    if (!this.isBeingRidden())
                    {
                        //this.addVelocity(-d0, 0.0D, -d1);
                    }

                    if (!entityIn.isBeingRidden())
                    {
                        entityIn.addVelocity(d0, 0.0D, d1);
                    }
                }
            }
        }
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
    /**
     * Used in model rendering to determine if the entity riding this entity should be in the 'sitting' position.
     * @return false to prevent an entity that is mounted to this entity from displaying the 'sitting' animation.
     */
    public boolean shouldRiderSit()
    {
        return false;
    }
	@Override
	public void fall(float distance, float damageMultiplier) { }
	@Override
	protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos) { }
}
