package zornco.reploidcraft.network;

import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.entities.EntityRideArmor;
import zornco.reploidcraft.utils.RiderState;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MessageRideArmor implements IMessage, IMessageHandler<MessageRideArmor, IMessage> {
	public float moveForward = 0.0F;
	public float moveStrafe = 0.0F;
	public boolean jump = false;
	public boolean sneak = false;
	public boolean dash = false;
	public boolean punch = false;
	public int ID = 0;
	public int dimension = 0;

	public MessageRideArmor() {}
	public MessageRideArmor(EntityRideArmor rideArmor, Entity entity) {
		if(rideArmor != null && entity != null){
			RiderState rs = ReploidCraft.proxy.getRiderState(entity);
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
	public IMessage onMessage(MessageRideArmor message, MessageContext ctx) {
		RiderState rs = new RiderState();
		
		rs.setMoveForward(message.moveForward);
		rs.setMoveStrafe(message.moveStrafe);
		rs.setJump(message.jump);
		rs.setSneak(message.sneak);
		Entity armor = getEntityByID(message.ID, message.dimension);
		
		if( armor != null && armor instanceof EntityRideArmor)
		{
			((EntityRideArmor)armor).setRiderState(rs);
		}
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		moveForward = buf.readFloat();
		moveStrafe = buf.readFloat();
		jump = buf.readBoolean();
		sneak = buf.readBoolean();
		dash = buf.readBoolean();
		punch = buf.readBoolean();
		ID = buf.readInt();
		dimension = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeFloat(moveForward);
		buf.writeFloat(moveStrafe);
		buf.writeBoolean(jump);
		buf.writeBoolean(sneak);
		buf.writeBoolean(dash);
		buf.writeBoolean(punch);
		buf.writeInt(ID);
		buf.writeInt(dimension);
		
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
