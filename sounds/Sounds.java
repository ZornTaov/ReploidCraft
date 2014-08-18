package zornco.reploidcraftenv.sounds;

import zornco.reploidcraftenv.ReploidCraftEnv;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class Sounds {
	private static final String SOUND_RESOURCE_LOCATION = ReploidCraftEnv.MOD_ID.toLowerCase() + ":";
    private static final String SOUND_PREFIX = ReploidCraftEnv.MOD_ID.toLowerCase() + ":";
	public static String[] soundFiles = { SOUND_RESOURCE_LOCATION + "bit.ogg", SOUND_RESOURCE_LOCATION + "byte.ogg" };
	public static final String BIT = SOUND_PREFIX + "bit";
	public static final String BYTE = SOUND_PREFIX + "byte";
	
	public Sounds()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	@SubscribeEvent
	public void onSoundLoad(SoundLoadEvent event)
	{
		ReploidCraftEnv.logger.info("ReploidCraft Sounds Loaded!");
	} 
}
