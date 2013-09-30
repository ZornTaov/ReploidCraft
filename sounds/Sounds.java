package zornco.reploidcraftenv.sounds;

import java.io.File;
import java.util.logging.Level;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.lib.Reference;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLCommonHandler;

public class Sounds {
	private static final String SOUND_RESOURCE_LOCATION = Reference.MOD_ID.toLowerCase() + ":";
    private static final String SOUND_PREFIX = Reference.MOD_ID.toLowerCase() + ":";
	public static String[] soundFiles = { SOUND_RESOURCE_LOCATION + "bit.ogg", SOUND_RESOURCE_LOCATION + "byte.ogg" };
	public static final String BIT = SOUND_PREFIX + "bit";
	public static final String BYTE = SOUND_PREFIX + "byte";
	
	public Sounds()
	{
		MinecraftForge.EVENT_BUS.register(this);
	}
	@ForgeSubscribe
	public void onSoundLoad(SoundLoadEvent event)
	{
		// For each custom sound file we have defined in Sounds
		for (String soundFile : Sounds.soundFiles) {
			// Try to add the custom sound file to the pool of sounds
			try {
				event.manager.soundPoolSounds.addSound(soundFile);
			}
			// If we cannot add the custom sound file to the pool, log the exception
			catch (Exception e) {
				ReploidCraftEnv.logger.log(Level.WARNING, "Failed loading sound file: " + soundFile);
			}
		}
		ReploidCraftEnv.logger.info("ReploidCraft Sounds Loaded!");
	} 
}
