package zornco.reploidcraftenv.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import zornco.reploidcraftenv.ReploidCraftEnv;
import zornco.reploidcraftenv.blocks.ContainerMechBay;
import zornco.reploidcraftenv.blocks.TileEntityMechBay;
import zornco.reploidcraftenv.blocks.TileMultiBlock;

public class GuiMechBayController extends GuiContainer {
	private ResourceLocation resourceLocation = new ResourceLocation("reploidcraftenv", "textures/gui/mechBay.png");

	TileEntityMechBay mechBay;
	public GuiMechBayController(IInventory player, IInventory mechBay) {
		super(new ContainerMechBay(mechBay, player));
		if(((TileMultiBlock) mechBay).hasMaster() && !((TileMultiBlock) mechBay).isMaster())
		{
			this.mechBay = (TileEntityMechBay) ReploidCraftEnv.proxy.getClientWorld().getTileEntity(((TileMultiBlock) mechBay).getMasterX(), ((TileMultiBlock) mechBay).getMasterY(), ((TileMultiBlock) mechBay).getMasterZ());
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
				func_147046_a(this.guiLeft + this.xSize - this.xSize/4, this.guiTop + 70, 20, 0, 0, mechBay.getMyRide());
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
		float f5 = p_147046_5_.prevRotationYawHead;
		float f6 = p_147046_5_.rotationYawHead;
		GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
		//RenderHelper.enableStandardItemLighting();
		GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F, 1.0F, 0.0F, 0.0F);
		p_147046_5_.renderYawOffset = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 20.0F;
		p_147046_5_.rotationYaw = (float)Math.atan((double)(p_147046_3_ / 40.0F)) * 40.0F;
		p_147046_5_.rotationPitch = -((float)Math.atan((double)(p_147046_4_ / 40.0F))) * 20.0F;
		p_147046_5_.rotationYawHead = p_147046_5_.rotationYaw;
		p_147046_5_.prevRotationYawHead = p_147046_5_.rotationYaw;
		GL11.glTranslatef(0.0F, p_147046_5_.yOffset, 0.0F);
		RenderManager.instance.playerViewY = 180.0F;
		RenderManager.instance.renderEntityWithPosYaw(p_147046_5_, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
		p_147046_5_.renderYawOffset = f2;
		p_147046_5_.rotationYaw = f3;
		p_147046_5_.rotationPitch = f4;
		p_147046_5_.prevRotationYawHead = f5;
		p_147046_5_.rotationYawHead = f6;
		GL11.glPopMatrix();
		//RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
	}
}
