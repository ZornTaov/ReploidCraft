package org.zornco.reploidcraft.init;

import org.zornco.reploidcraft.ReploidCraft;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RCSounds {

	public static SoundEvent SOUNDBIT;
	public static SoundEvent SOUNDNIBBLE;
	public static SoundEvent SOUNDBYTE;
	public static SoundEvent SOUNDWORD;
	public static SoundEvent SOUNDCHARGEUP;
	public static SoundEvent SOUNDCHARGECONT;

	/**
	 * Register the SoundEvent's.
	 */
	public static void registerSounds() {
		SOUNDBIT = registerSound("energy.bit");
		//SOUNDNIBBLE = registerSound("energy.nibble");
		SOUNDBYTE = registerSound("energy.byte");
		//SOUNDWORD = registerSound("energy.word");
		SOUNDCHARGEUP = registerSound("buster.chargeUp");
		SOUNDCHARGECONT = registerSound("buster.chargeCont");
	}
	public static SoundEvent registerSound(String soundName) {
		final ResourceLocation soundID = new ResourceLocation(ReploidCraft.MODID, soundName);
		return GameRegistry.register(new SoundEvent(soundID).setRegistryName(soundID));
	}
}
