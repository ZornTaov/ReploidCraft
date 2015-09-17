package zornco.reploidcraft.entities.armorParts.green;

import net.minecraft.item.ItemStack;
import zornco.reploidcraft.ReploidCraft;
import zornco.reploidcraft.entities.armorParts.IPartLegs;
import zornco.reploidcraft.entities.armorParts.PartBase;
import zornco.reploidcraft.items.ItemComponent.Components;

public class GreenLegs extends PartBase implements IPartLegs
{

	public GreenLegs()
	{
		super( 20, new float[] {
			0.0F, 0.0F, 0.0F }, new float[] {
			1.5F, 1.0F } );
	}

	@Override
	public float getSpeed()
	{
		return 0.1F;
	}

	@Override
	public float getDashSpeed()
	{
		return 0F;
	}

	@Override
	public Object[] getRecipe()
	{
		Object[] recipe = new Object[] {
			" c ", "lgl", 
			Character.valueOf('g'), new ItemStack( ReploidCraft.reploidPlate, 1, 10 ), // lime plate
			Character.valueOf('l'), new ItemStack( ReploidCraft.component, 1, Components.greenLeg.getIndex() ),
			Character.valueOf('c'), new ItemStack( ReploidCraft.component, 1, Components.coupling.getIndex() )
		};
		return recipe;
	}
}
