package zornco.reploidcraftenv.entities.armorParts;



public enum PartSlot
{
	HEAD,
	BODY,
	BACK,
	LEGS,
	ARMLEFT,
	ARMRIGHT;
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
	public static int getSize()
	{
		return values().length;
	}
}