package zornco.reploidcraft.entities.armorParts;

import java.util.Map;
import java.util.Properties;

import net.minecraft.item.ItemStack;
import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.crafting.RecipeHandler;
import zornco.reploidcraft.items.ItemRideArmorPart;

import com.google.common.collect.Maps;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

public class PartsRegistry extends RegistrySimple
{

	@SuppressWarnings( "rawtypes" )
	private Map field_148764_a;
	private static int partNumber = 0;
	private float[] emptyPos = {
		0, 0, 0 };
	private float[] emptySize = {
		0, 0, 0 };
	private PartBase emptyPart = new PartBase( 0, emptyPos, emptySize );
	public static ResourceLocation defaultTexture = new ResourceLocation( ReploidCraft.MOD_ID + ":textures/entity/rideArmorGreen.png" );

	@SuppressWarnings( "rawtypes" )
	/**
	 * Creates the Map we will use to map keys to their registered values.
	 */
	protected Map createUnderlyingMap()
	{
		this.field_148764_a = Maps.newHashMap();
		return this.field_148764_a;
	}

	@SuppressWarnings( "rawtypes" )
	public Map getMap()
	{
		return this.registryObjects;
	}

	public PartBase getPart( String type, PartSlot slot )
	{
		if ( this.containsKey( type ) )
		{
			PartBase entry = (PartBase) ( (PartList) this.getObject( type ) ).getPartList().get( slot );

			return entry != null ? entry : emptyPart;
		}
		else
		{
			ReploidCraft.logger.warn( "ERROR: called a missing part " + type + ":" + slot );
			return emptyPart;
		}
	}

	public ResourceLocation getPartTexture( String type )
	{
		if ( this.containsKey( type ) )
		{
			ResourceLocation entry = (ResourceLocation) ( (PartList) this.getObject( type ) ).getTexture();
			return entry != null ? entry : defaultTexture;
		}
		else
		{
			ReploidCraft.logger.warn( "ERROR: called a missing Texture for " + type );
			return defaultTexture;
		}
	}

	public void registerPart( String type, PartSlot slot, float health, float[] offsetXYZ, float[] size )
	{
		this.registerPart( type, slot, new PartBase( health, offsetXYZ, size ) );
	}

	@SuppressWarnings( "unchecked" )
	public void registerPart( String type, PartSlot slot, PartBase part )
	{
		if ( !this.containsKey( type ) )
		{
			this.registerSet( new PartList().setPartCategory( type ).setTexture( type.toString() ) );
		}
		if ( ( (PartList) this.getObject( type ) ).getPartList().get( slot ) != null )
		{
			ReploidCraft.logger.warn( "ERROR: Tried adding a part that was already there. " + type + ":" + slot );
			return;
		}
		part.setPartNumber( partNumber++ );
		part.setString( type + "." + slot );

		if ( FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT ) part.initModel();
		( (PartList) this.getObject( type ) ).getPartList().put( slot, part );
		ItemRideArmorPart.getPartList().add( (PartBase) part );
		if ( part.getRecipe() != null )
		{
			RecipeHandler.addOreRecipe( new ItemStack( ReploidCraft.rideArmorPart, 1, part.getPartNumber() ), part.getRecipe(), false );
		}
		ReploidCraft.logger.info( "registered " + type + ":" + slot );
	}

	public void registerSet( PartList list )
	{
		this.putObject( list.getPartCategory(), list );
	}

	public void func_148763_c()
	{
		this.field_148764_a.clear();
	}

	protected class TypeMap
	{
		@SuppressWarnings( "rawtypes" )
		Map<Enum, Properties> slotMap;

		protected Properties getPartFromSlot( PartSlot slot )
		{
			return slotMap.get( slot );
		}
	}
}
