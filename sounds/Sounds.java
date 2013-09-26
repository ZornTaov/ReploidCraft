package zornco.reploidcraftenv.sounds;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.common.FMLCommonHandler;

public class Sounds {
	private static final String SOUND_RESOURCE_LOCATION = "zornco/reploidcraftenv/sounds/";
	private static final String SOUND_PREFIX = "zornco.reploidcraftenv.sounds.";
	public static String[] soundFiles = { "zornco/reploidcraftenv/sounds/bit.ogg", "zornco/reploidcraftenv/sounds/byte.ogg" };
	public static final String BIT = "zornco.reploidcraftenv.sounds.bit";
	public static final String BYTE = "zornco.reploidcraftenv.sounds.byte";
	
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
				event.manager.soundPoolSounds.addSound(soundFile, this.getClass().getResource("/" + soundFile));
			}
			// If we cannot add the custom sound file to the pool, log the exception
			catch (Exception e) {
				FMLCommonHandler.instance().getFMLLogger().log(Level.WARNING, "Failed loading sound file: " + soundFile);
			}
		}
		//System.out.println("ReploidCraft Sounds Loaded!");
	} 
}
