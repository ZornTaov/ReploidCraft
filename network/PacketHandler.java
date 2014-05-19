package zornco.reploidcraftenv.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.TileEntityItemHolder;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {
    @Override
    public void onPacketData(INetworkManager network, Packet250CustomPayload packet, Player player)
    {
        ByteArrayDataInput dat = ByteStreams.newDataInput(packet.data);
        int x = dat.readInt();
        int y = dat.readInt();
        int z = dat.readInt();
        int typDat = dat.readByte();
        byte typ = (byte)(typDat & 0xf);
        byte facing = (byte)((typDat >> 4) & 0xf);
        boolean hasStacks = dat.readByte() != 0;
        int[] items = new int[0];
        if (hasStacks)
        {
            items = new int[24];
            for (int i = 0; i < items.length; i++)
            {
                items[i] = dat.readInt();
            }
        }
        World world = ReploidCraftEnv.proxy.getClientWorld();
        TileEntity te = world.getBlockTileEntity(x, y, z);
        if (te instanceof TileEntityItemHolder)
        {
        	TileEntityItemHolder icte = (TileEntityItemHolder) te;
            icte.setFacing(facing);
            icte.handlePacketData(typ, items);
        }
    }

    public static Packet getPacket(TileEntityItemHolder tileEntityItemHolder)
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(140);
        DataOutputStream dos = new DataOutputStream(bos);
        int x = tileEntityItemHolder.xCoord;
        int y = tileEntityItemHolder.yCoord;
        int z = tileEntityItemHolder.zCoord;
        int typ = ((tileEntityItemHolder.getFacing() << 4)) & 0xFF;
        int[] items = tileEntityItemHolder.buildIntDataList();
        boolean hasStacks = (items != null);
        try
        {
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(z);
            dos.writeByte(typ);
            dos.writeByte(hasStacks ? 1 : 0);
            if (hasStacks)
            {
                for (int i = 0; i < 24; i++)
                {
                    dos.writeInt(items[i]);
                }
            }
        }
        catch (IOException e)
        {
            // UNPOSSIBLE?
        }
        Packet250CustomPayload pkt = new Packet250CustomPayload();
        pkt.channel = "ReploidEnv";
        pkt.data = bos.toByteArray();
        pkt.length = bos.size();
        pkt.isChunkDataPacket = true;
        return pkt;
    }
}

