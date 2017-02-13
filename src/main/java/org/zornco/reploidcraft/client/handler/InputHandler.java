package org.zornco.reploidcraft.client.handler;

import org.lwjgl.input.Keyboard;
import org.zornco.reploidcraft.item.IKeyBound;
import org.zornco.reploidcraft.network.MessageWheelUsed;
import org.zornco.reploidcraft.network.ReploidNetwork;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

public class InputHandler {

	public static KeyBinding reploidMenu = new KeyBinding("key.reploid.menu", Keyboard.KEY_V, "key.categories.reploidcraft");

	@SubscribeEvent
	public void onMouseInput(MouseEvent event)
	{
		if (FMLClientHandler.instance().getClient().inGameHasFocus) {
            EntityPlayer player = FMLClientHandler.instance().getClient().player;
            if (player != null) {
				if (player.isSneaking()) {
	                ItemStack currentItem = FMLClientHandler.instance().getClient().player.getHeldItemMainhand();

	                if (currentItem != null && currentItem.getItem() instanceof IKeyBound) {
		
						if (event.getDwheel() == 0) return;
						ReploidNetwork.INSTANCE.sendToServer(new MessageWheelUsed(player, event.getDwheel()));
						event.setCanceled(true);
	                }
				}
            }
		}
	}
	@SubscribeEvent
	public void onKeyInput(InputEvent.KeyInputEvent event) {
		if (reploidMenu.isPressed() && FMLClientHandler.instance().getClient().inGameHasFocus) {
            EntityPlayer player = FMLClientHandler.instance().getClient().player;
            if (player != null) {
                ItemStack currentItem = FMLClientHandler.instance().getClient().player.getHeldItemMainhand();

                if (currentItem != null && currentItem.getItem() instanceof IKeyBound) {

        			System.out.println("ping");
                    
                }
            }
		}
	}

}
