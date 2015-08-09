package zornco.reploidcraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import zornco.reploidcraft.blocks.ContainerMechBay;
import zornco.reploidcraft.blocks.TileEntityMechBay;
import zornco.reploidcraft.blocks.TileMultiBlock;
import zornco.reploidcraft.entities.EntityRideArmor;

public class GuiMechBayController extends GuiContainer {
	private ResourceLocation resourceLocation = new ResourceLocation("reploidcraft", "textures/gui/mechBay.png");

	TileEntityMechBay mechBay;

	private float field_147033_y;

	private float field_147032_z;
	public GuiMechBayController(IInventory player, IInventory mechBay) {
		super(new ContainerMechBay(mechBay, player));
		if(((TileMultiBlock) mechBay).hasMaster() && !((TileMultiBlock) mechBay).isMaster() && ((TileEntity) mechBay).hasWorldObj())
		{
			this.mechBay = (TileEntityMechBay) ((TileEntityMechBay) mechBay).getWorldObj().getTileEntity(((TileEntityMechBay) mechBay).getMasterX(), ((TileMultiBlock) mechBay).getMasterY(), ((TileMultiBlock) mechBay).getMasterZ());
		}
		else
		{
			this.mechBay = (TileEntityMechBay) mechBay;
		}
	}

	public GuiMechBayController(EntityPlayer player, String title,
			boolean localized) {
		this((IInventory) player, new InventoryBasic(title, localized, 1));
	}

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        this.field_147033_y = (float)p_73863_1_;
        this.field_147032_z = (float)p_73863_2_;
        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		// new "bind tex"
		this.mc.getTextureManager().bindTexture(resourceLocation );
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		if(mechBay != null)
		{

			if(mechBay.hasRide() && this.mechBay.getMyRide() != null)
				func_147046_a(this.guiLeft + this.xSize - this.xSize/4, this.guiTop + 70, 20, (float)(x + 51) - this.field_147033_y, (float)(y + 75 - 50) - this.field_147032_z, mechBay.getMyRide());
		}

	}
	public static void func_147046_a(int p_147046_0_, int p_147046_1_, int p_147046_2_, float p_147046_3_, float p_147046_4_, EntityLivingBase p_147046_5_)
	{
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)p_147046_0_, (float)p_147046_1_, 50.0F);
		GL11.glScalef((float)(-p_147046_2_), (float)p_147046_2_, (float)p_147046_2_);
		GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
		float f2 = p_147046_5_.renderYawOffset;
		float f3 = p_147046_5_.rotationYaw;
		float f4 = p_147046_5_.rotationPitch;
		float f5 = p_147046_5_.prevRotationYaw;
		float f6 = p_147046_5_.rotationYaw;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
		p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
		p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
		p_147046_5_.rotationYaw = p_147046_5_.rotationYaw;
		p_147046_5_.prevRotationYaw = p_147046_5_.rotationYaw;
		GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		float sit = ((EntityRideArmor)p_147046_5_).sitAngle;
		((EntityRideArmor)p_147046_5_).sitAngle = 0;
		RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, p_147046_5_.rotationYaw, 1.0F);
		//RenderManager.instance.renderEntitySimple(p_147046_5_, 1.0F);
		((EntityRideArmor)p_147046_5_).sitAngle = sit;
		
		p_147046_5_.renderYawOffset = f2;
		p_147046_5_.rotationYaw = f3;
		p_147046_5_.rotationPitch = f4;
		p_147046_5_.prevRotationYaw = f5;
		p_147046_5_.rotationYaw = f6;
		GL11.glPopMatrix();
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}
