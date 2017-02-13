package org.zornco.reploidcraft.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.zornco.reploidcraft.item.IKeyBound;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;

public class MessageWheelUsed implements IMessage, IMessageHandler<MessageWheelUsed, IMessage> {

	int dimension;
	int entityID;
	int dir;
	
    public MessageWheelUsed() {

    }

    public MessageWheelUsed(EntityPlayer player, int dir) {


		this.dimension = player.world.provider.getDimension();
		this.entityID = player.getEntityId();
		this.dir = dir;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
		this.dimension = buf.readInt();
		this.entityID = buf.readInt();
		this.dir = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
		buf.writeInt(this.dimension);
		buf.writeInt(this.entityID);
		buf.writeInt(this.dir);
    }

	@Override
    public IMessage onMessage(final MessageWheelUsed message, MessageContext ctx)
    {
		

		IThreadListener mainThread = (WorldServer) ctx.getServerHandler().playerEntity.world;
		mainThread.addScheduledTask(new Runnable() {
			@Override
			public void run() {
				World world = DimensionManager.getWorld(message.dimension);
				if(world!=null)
				{
					Entity entity = world.getEntityByID(message.entityID);
					if(entity!=null && entity instanceof EntityPlayer)
					{
						EntityPlayer entityPlayer = (EntityPlayer)entity;
		
				        if (entityPlayer != null && entityPlayer.getHeldItemMainhand() != null && (entityPlayer.getHeldItemMainhand().getItem() instanceof IKeyBound)) {
				            ((IKeyBound) entityPlayer.getHeldItemMainhand().getItem()).doMouseAction(entityPlayer, entityPlayer.getHeldItemMainhand(), message.dir);
				        }
					}
				}
			}
		});
        return null;
    }

}
