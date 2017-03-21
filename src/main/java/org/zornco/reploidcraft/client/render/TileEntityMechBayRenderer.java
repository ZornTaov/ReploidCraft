package org.zornco.reploidcraft.client.render;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.opengl.GL12;
import org.zornco.reploidcraft.block.BlockMechBay;
import org.zornco.reploidcraft.block.TileEntityMechBay;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class TileEntityMechBayRenderer extends TileEntitySpecialRenderer {

	protected static final ResourceLocation itemHolderTexture = new ResourceLocation("reploidcraft","textures/blocks/ModelMechBay.png");
	private ModelMechBay model;
	public TileEntityMechBayRenderer() {
		super();
		this.model = new ModelMechBay();
	}
	public void render(TileEntityMechBay tile, double x, double y, double z, float partialTick) {
		if (tile == null) {
			return;
		}
		IBlockState state = tile.getWorld().getBlockState(tile.getPos()); 
		if (!(state.getBlock() instanceof BlockMechBay))return;
		if (!state.getValue(BlockMechBay.INVIS).booleanValue()) return;
		if (!tile.isMaster()) return;
		int facing = 3;
		if (tile != null && tile.getWorld() != null) {
			facing = tile.getDirection();
		}
		bindTexture(itemHolderTexture);
		glPushMatrix();
		glEnable(GL12.GL_RESCALE_NORMAL);
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		glTranslatef((float) x, (float) y + 3.0F, (float) z + 1.0F);
		glScalef(1.0F, -1F, -1F);
		glTranslatef(0.5F, 0.5F, 0.5F);	
		int k = 0;
		if (facing == 0) {
			k = 0;
		}
		if (facing == 1) {
			k = 180;
		}
		if (facing == 2) {
			k = -90;
		}
		if (facing == 3) {
			k = 90;
		}
		glRotatef(k, 0.0F, 1.0F, 0.0F);
		//glTranslatef(-0.5F, -0.5F, -0.5F);
		//float lidangle = tile.prevLidAngle + (tile.lidAngle - tile.prevLidAngle) * partialTick;
		//lidangle = 1.0F - lidangle;
		//lidangle = 1.0F - lidangle * lidangle * lidangle;
		//model.chestLid.rotateAngleX = -((lidangle * 3.141593F) / 2.0F);
		// Render the chest itself
		model.render(0.0625F);
		glDisable(GL12.GL_RESCALE_NORMAL);
		glPopMatrix();
		glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float partialTick, int destroyStage)
	{
		render((TileEntityMechBay) tileentity, x, y, z, partialTick);
	}
}
