package org.zornco.reploidcraft.entities.armorparts;



public enum PartSlot
{
	HEAD(0),
	BODY(1),
	BACK(2),
	LEGS(3),
	ARMLEFT(4),
	ARMRIGHT(5);
	
	private final int index;
	
	private PartSlot(int index)
	{
		this.index = index;
	}
	
	/**
	 * A common method for all enums since they can't have another base class
	 * @param <T> Enum type
	 * @param c enum type. All enums must be all caps.
	 * @param string case insensitive
	 * @return corresponding enum, or null
	 */
	public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string)
	{
	    if( c != null && string != null )
	    {
	        try
	        {
	            return Enum.valueOf(c, string.trim().toUpperCase());
	        }
	        catch(IllegalArgumentException ex)
	        {
	        }
	    }
	    return null;
	}
	public static PartSlot getSlot(String name)
	{
	    return getEnumFromString(PartSlot.class, name);
	}
	public static PartSlot getSlot(int i)
	{
		switch (i) {
		default:
		case 0:
			return HEAD;
		case 1:
			return BODY;
		case 2:
			return BACK;
		case 3:
			return LEGS;
		case 4:
			return ARMLEFT;
		case 5:
			return ARMRIGHT;
		}
	}
	
	public static int getSize()
	{
		return values().length;
	}

	public int getIndex()
	{
		return index;
	}
}