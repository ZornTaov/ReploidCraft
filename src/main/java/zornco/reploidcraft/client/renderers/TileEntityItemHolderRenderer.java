package zornco.reploidcraft.client.renderers;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import java.util.Random;

import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import zornco.reploidcraft.blocks.TileEntityItemHolder;

import com.google.common.primitives.SignedBytes;

public class TileEntityItemHolderRenderer extends TileEntitySpecialRenderer {

	protected static final ResourceLocation itemHolderTexture = new ResourceLocation("reploidcraft","textures/blocks/itemHolder.png");

	private Random random;

	//private RenderBlocks renderBlocks;

	private RenderItem itemRenderer;

	private ModelItemHolder model;

	public TileEntityItemHolderRenderer()
	{
		model = new ModelItemHolder();
		random = new Random();
		//renderBlocks = new RenderBlocks();
		itemRenderer = new RenderItem() {
			@Override
			public byte getMiniBlockCount(ItemStack stack, byte original) {
				return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 15) + 1);
			}
			@Override
			public byte getMiniItemCount(ItemStack stack, byte original) {
				return SignedBytes.saturatedCast(Math.min(stack.stackSize / 32, 7) + 1);
			}
			@Override
			public boolean shouldSpreadItems() {
				return false;
			}
		};
		itemRenderer.setRenderManager(RenderManager.instance);
	}

	public void render(TileEntityItemHolder tile, double x, double y, double z, float partialTick) {
		if (tile == null) {
			return;
		}
		int facing = 3;
		if (tile != null && tile.getWorldObj() != null) {
			facing = tile.getFacing();
		}
		boolean empty = true;
		//if (tile.getDistanceFrom(this.field_147501_a.field_147560_j, 
		//		this.field_147501_a.field_147561_k, this.field_147501_a.field_147558_l) < 128d) {
			random.setSeed(254L);
			float blockScale = 0.90F;
			float timeD = (float) (360.0 * (double) (System.currentTimeMillis() & 0x3FFFL) / (double) 0x3FFFL);
			if (tile.getTopItemStacks()[1] == null) {
				blockScale = 1.0F;
			}
			glPushMatrix();
			glDisable(2896 /* GL_LIGHTING */);
			glTranslatef((float) x, (float) y, (float) z);
			if(((TileEntityItemHolder)tile).getStackInSlot(0) != null){
				EntityItem customitem = new EntityItem(field_147501_a.field_147550_f);
				customitem.hoverStart = 0f;
				ItemStack item = tile.getStackInSlot(0);
				empty = false;
				glPushMatrix();
				glTranslatef(0.5F, 0.75F, 0.5F);
				glRotatef(timeD, 0.0F, 1.0F, 0.0F);
				glScalef(blockScale, blockScale, blockScale);
				customitem.setEntityItemStack(item);
				itemRenderer.doRender(customitem, 0, 0, 0, 0, 0);
				glPopMatrix();
			}
			glEnable(2896 /* GL_LIGHTING */);
			glPopMatrix();
			glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		//}
		glDisable(GL_CULL_FACE);
		glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		bindTexture(itemHolderTexture);
		glPushMatrix();
		glEnable(32826 /* GL_RESCALE_NORMAL_EXT */);
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		glTranslatef((float) x, (float) y + 0.0F, (float) z + 1.0F);
		glScalef(1.0F, -1F, -1F);
		glTranslatef(0.5F, 0.5F, 0.5F);
		int k = 0;
		if (facing == 2) {
			k = 180;
		}
		if (facing == 3) {
			k = 0;
		}
		if (facing == 4) {
			k = 90;
		}
		if (facing == 5) {
			k = -90;
		}
		glRotatef(k, 0.0F, 1.0F, 0.0F);
		glTranslatef(-0.5F, -0.5F, -0.5F);
		//float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
		//lidangle = 1.0F - lidangle;
		//lidangle = 1.0F - lidangle * lidangle * lidangle;
		//model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
		// Render the chest itself
		model.render(0.0625F, empty);
		glColor4f(0.25F, 0.25F, 1.0F, 0.5F);
		model.renderLight(0.0625F);
		glDisable(GL_BLEND);
        glEnable(GL_CULL_FACE);
		glDisable(32826 /* GL_RESCALE_NORMAL_EXT */);
		glPopMatrix();
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
	}

	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick)
	{
		render((TileEntityItemHolder) tileentity, x, y, z, partialTick);
	}

}
