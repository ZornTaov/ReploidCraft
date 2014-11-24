package zornco.reploidcraftenv.client.renderers;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.EntityMet;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMet extends RenderLiving
{
	private static final ResourceLocation met = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/MetHat.png");

	public RenderMet(ModelBase par1ModelBase, ModelBase par2ModelBase, float shadowSize)
	{
		super(par1ModelBase, shadowSize);
		this.setRenderPassModel(par2ModelBase);
	}

	protected int setHatTypeAndRender(EntityMet par1EntityMet, int pass, float partialTickTime)
	{
		if (pass == 0 && par1EntityMet.getIsHatNotWorn())
		{
			this.bindTexture(met);
			//int type = par1EntityMet.getCollarColor();
			GL11.glColor3f(EntitySheep.fleeceColorTable[4][0], EntitySheep.fleeceColorTable[4][1], EntitySheep.fleeceColorTable[4][2]);
			return 1;
		}
		else
		{
			return -1;
		}
	}

	public void doRenderMet(EntityMet par1EntitySheep, double par2, double par4, double par6, float par8, float partialTickTime)
	{
		super.doRender(par1EntitySheep, par2, par4, par6, par8, partialTickTime);
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	@Override
	protected int shouldRenderPass(EntityLivingBase par1EntityLiving, int pass, float partialTickTime)
	{
		return this.setHatTypeAndRender((EntityMet)par1EntityLiving, pass, partialTickTime);
	}

	/**
	 * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
	 * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
	 * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
	 * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
	 */
	@Override
	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float partialTickTime)
	{
		this.doRenderMet((EntityMet)par1Entity, par2, par4, par6, par8, partialTickTime);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return met;
	}
}
