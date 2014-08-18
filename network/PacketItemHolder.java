package zornco.reploidcraftenv.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import zornco.reploidcraftenv.utils.PlayerUtils;
import cpw.mods.fml.common.network.ByteBufUtils;

public class PacketItemHolder extends AbstractPacket {
	public int windowId;
	public String name;
	public int xPos;
	public int yPos;
	public int zPos;
    int typ = 0;//((tileEntityItemHolder.getFacing() << 4)) & 0xFF;
    int[] items = null;
    boolean hasStacks = (items != null);
	
	public PacketItemHolder() { }
	public PacketItemHolder(int windowId, String name, int columns, int rows, String title) {
		this.windowId = windowId;
		this.name = name;
		this.xPos = columns;
		this.yPos = rows;
		this.typ = 0;
		items = null;//tileEntityItemHolder.buildIntDataList();
}
	@Override
	public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) {
		buffer.writeInt(windowId);
		ByteBufUtils.writeUTF8String(buffer, name);
		buffer.writeByte(xPos);
		buffer.writeByte(yPos);
		//ByteBufUtils.writeUTF8String(buffer, title);
	}

	@Override
	public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) {
		windowId = buffer.readInt();
		name = ByteBufUtils.readUTF8String(buffer);
		xPos = buffer.readByte();
		yPos = buffer.readByte();
		//title = ByteBufUtils.readUTF8String(buffer);
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
		PlayerUtils.openGui(player, name, xPos, yPos, "hello");//title);
		player.openContainer.windowId = windowId;
	}
	
	@Override
	public void handleServerSide(EntityPlayer player) {
		// TODO Auto-generated method stub
		
	}
}
