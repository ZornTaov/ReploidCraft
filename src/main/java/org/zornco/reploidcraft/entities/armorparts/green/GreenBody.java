package org.zornco.reploidcraft.entities.armorparts.green;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.MathHelper;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.entities.armorparts.IPartBody;
import org.zornco.reploidcraft.entities.armorparts.PartBase;

public class GreenBody extends PartBase implements IPartBody
{

	public GreenBody()
	{
		super( 40, new float[] {
				0.0F, 1.5F, 0.0F }, new float[] {
				1.5F, 1.5F } );
	}

	@Override
	public boolean isLavaResistant()
	{
		return false;
	}

	@Override
	public void doParticle( Entity mech )
	{
		for ( int i = 0; i < 4; ++i )
		{
			float f3 = mech.rotationYaw * (float) Math.PI / 180.0F;
			float f1 = MathHelper.sin( f3 );
			float f2 = MathHelper.cos( f3 );
			double X = mech.posX + 0.4 * f2 * ( ( i % 2 ) == 0 ? -1 : 1 ) - 1.2 * f1;
			double Z = mech.posZ + 0.4 * f1 * ( ( i % 2 ) == 0 ? -1 : 1 ) + 1.2 * f2;
			double d0 = this.rand.nextGaussian() * 0.02D;
			double d1 = this.rand.nextGaussian() * 0.02D;
			double d2 = this.rand.nextGaussian() * 0.02D;
			//mech.world.spawnParticle( "cloud", X, mech.posY + 1.8D + ( (EntityRideArmor) mech ).getMountedYOffset(), Z, d0, d1, d2 );
		}
	}

	@Override
	public boolean doExplode( EntityRideArmor ride, DamageSource source )
	{
		return false;
	}

	/*@Override
	public Object[] getRecipe()
	{
		Object[] recipe = new Object[] {
			"sCs", "gag", "vsv", 
			Character.valueOf('a'), new ItemStack( ReploidCraft.component, 1, Components.AEGD.getIndex() ),
			Character.valueOf('g'), new ItemStack( ReploidCraft.reploidPlate, 1, 10 ), // lime plate
			Character.valueOf('C'), new ItemStack( ReploidCraft.component, 1, Components.cockpit.getIndex() ),
			Character.valueOf('s'), new ItemStack( ReploidCraft.component, 1, Components.socket.getIndex() ),
			Character.valueOf('v'), new ItemStack( ReploidCraft.component, 1, Components.vent.getIndex() )
		};
		return recipe;
	}*/

}
