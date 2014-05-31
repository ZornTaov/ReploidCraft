package zornco.reploidcraftenv.client.renderers;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;

public class RenderRideArmor extends Render {

	private int nagged = -1;
	private ModelBase modelRideArmor;
	private static final ResourceLocation rideArmor = new ResourceLocation(ReploidCraftEnv.MOD_ID + ":textures/entity/rideArmorGreen.png");//FloatingPlatform.png");

	public RenderRideArmor()
	{
		this.shadowSize = 2.5F;
	}
	/**
	 * Args: entity, x, y, z, yaw,
	 * partialTickTime
	 */
	@Override
	public void doRender(Entity entity, double par2, double par4, double par6,
			float par8, float par9)
	{
		this.doRenderRideArmor((EntityRideArmor)entity, par2, par4, par6, par8, par9);
	}

	private void doRenderRideArmor(EntityRideArmor entity, 
			double par2, double par4, double par6, float par8, float par9)
	{
		if (this.nagged != 1)
		{
			this.modelRideArmor = new ModelrideArmor();
			this.nagged = 1;
		}

		float f6 = entity.prevLimbSwingAmount + (entity.limbSwingAmount - entity.prevLimbSwingAmount) * par9;
        float f7 = entity.limbSwing - entity.limbSwingAmount * (1.0F - par9);
        float f8 = entity.prevSitAngle + (entity.sitAngle - entity.prevSitAngle) * par9;
        
        GL11.glPushMatrix();
		GL11.glTranslatef((float)par2, (float)par4, (float)par6);
		GL11.glTranslatef(0.0F, 1.25F, 0.0F);

		GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
        this.bindEntityTexture(entity);
        if(entity.rideArmorPartsMap != null)
        {
        	((ModelrideArmor)modelRideArmor).upperArmLeft.isHidden = entity.rideArmorPartsMap.containsKey("armLeft")?false:true;
        	((ModelrideArmor)modelRideArmor).upperArmRight.isHidden = entity.rideArmorPartsMap.containsKey("armRight")?false:true;
        }
		this.modelRideArmor.render(entity, f6, f7, f8, 0.0F, 0.0F, 0.0625F);

		GL11.glPopMatrix();
	}
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return rideArmor;
	}

}
