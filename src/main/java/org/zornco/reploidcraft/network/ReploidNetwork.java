package org.zornco.reploidcraft.network;

import org.zornco.reploidcraft.ReploidCraft;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class ReploidNetwork {

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ReploidCraft.MODID);
    public static void init() {
        INSTANCE.registerMessage(MessageWheelUsed.class, MessageWheelUsed.class, 0, Side.SERVER);
    }
}
