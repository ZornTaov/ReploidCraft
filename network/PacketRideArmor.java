package zornco.reploidcraftenv.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.utils.RiderState;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;


public class PacketRideArmor extends AbstractPacket {
	public float moveForward = 0.0F;
	public float moveStrafe = 0.0F;
	public boolean jump = false;
	public boolean sneak = false;
	public boolean dash = false;
	public boolean punch = false;
	public int ID = 0;
	public int dimension = 0;
	
	public PacketRideArmor(){}
	public PacketRideArmor(EntityRideArmor rideArmor, Entity entity) {
		if(rideArmor != null && entity != null){
			RiderState rs = ReploidCraftEnv.proxy.getRiderState(entity);
			if (null != rs)
			{
				moveStrafe = rs.getMoveStrafe();
				moveForward = rs.getMoveForward();
				jump = rs.isJump();
				sneak = rs.isSneak();
			}
			ID = rideArmor.getEntityId();
			dimension = rideArmor.dimension;
		}
	}
	@Override
	public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) {
		buffer.writeFloat(moveForward);
		buffer.writeFloat(moveStrafe);
		buffer.writeBoolean(jump);
		buffer.writeBoolean(sneak);
		buffer.writeBoolean(dash);
		buffer.writeBoolean(punch);
		buffer.writeInt(ID);
		buffer.writeInt(dimension);
	}

	@Override
	public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) {
		moveForward = buffer.readFloat();
		moveStrafe = buffer.readFloat();
		jump = buffer.readBoolean();
		sneak = buffer.readBoolean();
		dash = buffer.readBoolean();
		punch = buffer.readBoolean();
		ID = buffer.readInt();
		dimension = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		
	}
	
	@Override
	public void handleServerSide(EntityPlayer player) {
		RiderState rs = new RiderState();
		
		rs.setMoveForward(moveForward);
		rs.setMoveStrafe(moveStrafe);
		rs.setJump(jump);
		rs.setSneak(sneak);
		Entity armor = getEntityByID(ID, dimension);
		
		if( armor != null && armor instanceof EntityRideArmor)
		{
			((EntityRideArmor)armor).setRiderState(rs);
		}
	}
	public Entity getEntityByID(int entityId, int dimension)
	{
		Entity targetEntity = null;
		if (Side.SERVER == FMLCommonHandler.instance().getEffectiveSide())
		{
			WorldServer worldserver = MinecraftServer.getServer().worldServerForDimension(dimension);
			targetEntity = worldserver.getEntityByID(entityId);
		}

		if ((null != targetEntity) && ((targetEntity instanceof EntityRideArmor)))
		{
			return (EntityRideArmor)targetEntity;
		}

		return null;
	}
}
