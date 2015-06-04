package zornco.reploidcraft.network;

import zornco.reploidcraft.ReploidCraft;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ReploidCraft.MOD_ID);
    private static int id = 0;
    
    public static void init()
    {
        INSTANCE.registerMessage(MessageRideArmor.class, MessageRideArmor.class, id++, Side.SERVER);
        INSTANCE.registerMessage(MessageReploidCraftGui.class, MessageReploidCraftGui.class, id++, Side.CLIENT);
    }

}
