package zornco.reploidcraft.sounds;

import zornco.reploidcraft.ReploidCraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Sounds {
	private static final String SOUND_RESOURCE_LOCATION = ReploidCraft.MOD_ID.toLowerCase() + ":";
    private static final String SOUND_PREFIX = ReploidCraft.MOD_ID.toLowerCase() + ":";
	public static String[] soundFiles = { SOUND_RESOURCE_LOCATION + "bit.ogg", SOUND_RESOURCE_LOCATION + "byte.ogg" };
	public static final String BIT = SOUND_PREFIX + "bit";
	public static final String BYTE = SOUND_PREFIX + "byte";
	public static final String CHARGEUP = SOUND_PREFIX + "chargeUp";
	public static final String CHARGECONT = SOUND_PREFIX + "chargeCont";
	
	public Sounds()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SubscribeEvent
	public void onSoundLoad(SoundLoadEvent event)
	{
		ReploidCraft.logger.info("ReploidCraft Sounds Loaded!");
	} 
}
