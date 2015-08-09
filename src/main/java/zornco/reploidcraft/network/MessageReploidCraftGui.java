package zornco.reploidcraft.network;

import zornco.reploidcraft.utils.PlayerUtils;
import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageReploidCraftGui implements IMessage, IMessageHandler<MessageReploidCraftGui, IMessage> {
	public int windowId;
	public String name;
	public int xPos;
	public int yPos;
	public int zPos;
    int typ = 0;//((tileEntityItemHolder.getFacing() << 4)) & 0xFF;
    int[] items = null;
    boolean hasStacks = (items != null);

	public MessageReploidCraftGui() { }
	public MessageReploidCraftGui(int windowId, String name, int columns, int rows, String title) {
		this.windowId = windowId;
		this.name = name;
		this.xPos = columns;
		this.yPos = rows;
		this.typ = 0;
		items = null;//tileEntityItemHolder.buildIntDataList();
	}
	@Override
	public IMessage onMessage(MessageReploidCraftGui message, MessageContext ctx) {
		PlayerUtils.openGui(ctx.getServerHandler().playerEntity, name, xPos, yPos, "hello");//title);
		ctx.getServerHandler().playerEntity.openContainer.windowId = windowId;
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		windowId = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
		xPos = buf.readByte();
		yPos = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(windowId);
		ByteBufUtils.writeUTF8String(buf, name);
		buf.writeByte(xPos);
		buf.writeByte(yPos);
	}

}
