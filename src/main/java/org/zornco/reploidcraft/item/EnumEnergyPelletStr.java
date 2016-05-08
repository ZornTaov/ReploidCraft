package org.zornco.reploidcraft.item;

import net.minecraft.util.text.TextFormatting;

public enum EnumEnergyPelletStr {
	SMALL(0, 1, "bit"),
	MEDIUM(1, 4, "nibble"),
	LARGE(2, 8, "byte"),
	HUGE(3, 16, "word");
	private static final EnumEnergyPelletStr[] META_LOOKUP = new EnumEnergyPelletStr[values().length];
	private final int meta;
	private final int strength;
	private final String name;
	private EnumEnergyPelletStr(int m, int s, String n) {

		this.meta = m;
		this.name = n;
		this.strength = s;
	}

	public int getMetadata()
	{
		return this.meta;
	}

	public String getName()
	{
		return this.name;
	}

	public String toString()
	{
		return this.name;
	}

	public static EnumEnergyPelletStr byMetadata(int meta)
	{
		if (meta < 0 || meta >= META_LOOKUP.length)
		{
			meta = 0;
		}

		return META_LOOKUP[meta];
	}

	static
	{
		for (EnumEnergyPelletStr enumtype : values())
		{
			META_LOOKUP[enumtype.getMetadata()] = enumtype;
		}
	}
}
