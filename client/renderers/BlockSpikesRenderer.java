package zornco.reploidcraft.client.renderers;

import static net.minecraftforge.common.util.ForgeDirection.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraft.core.Config;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockSpikesRenderer extends RenderBlocks
implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		if (modelID == Config.spikesRI)
		{
			renderer.setRenderBounds(0.0F, 0.25F, 0.0F, 1.0F, 0.3125F, 1.0F);
			renderDo(renderer, block, metadata);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {

		Tessellator tess = Tessellator.instance;
		IIcon icon = getBlockIconFromSide(block, 0);
		double uLeft = (double)icon.getMinU();
		double uRight = (double)icon.getMaxU();
		double vTop = (double)icon.getMinV();
		double vBottom = (double)icon.getMaxV(); 
		double offset = 0.0625F;
		//double i, j;
		int light = block.getMixedBrightnessForBlock(renderer.blockAccess, x, y, z);
		tess.setBrightness(light);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		if (renderer.blockAccess.isSideSolid(x, y + 1, z, UP, true) || (renderer.blockAccess.getBlock(x, y + 1, z).getMaterial() == Material.piston && renderer.blockAccess.getBlock(x, y + 1, z) != Blocks.piston_extension))
		{
			renderer.setRenderBounds(0.0, 1.0-offset, 0.0, 1.0, 1.0, 1.0);
			renderer.renderStandardBlock(block, x, y, z);
			tess.setNormal(0.0F, -1F, 0.0F);

			drawY1(tess, x, y, z, uLeft, uRight, vTop, vBottom, light);

		}
		if (renderer.blockAccess.isSideSolid(x, y - 1, z, DOWN, true) || (renderer.blockAccess.getBlock(x, y - 1, z).getMaterial() ==  Material.piston /*&& renderer.blockAccess.getBlock(x, y - 1, z) != Blocks.piston_extension*/))
		{
			renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, offset, 1.0);
			renderer.renderStandardBlock(block, x, y, z);
			tess.setNormal(0.0F, 1.0F, 0.0F);

			drawY2(tess, x, y, z, uLeft, uRight, vTop, vBottom, light);

		}
		if (renderer.blockAccess.isSideSolid(x + 1, y, z, EAST, true) || (renderer.blockAccess.getBlock(x + 1, y, z).getMaterial() == Material.piston && renderer.blockAccess.getBlock(x + 1, y, z) != Blocks.piston_extension))
		{
			//PROBLEM SIDE
			renderer.setRenderBounds(1.0-offset, 0.0, 0.0, 1.0, 1.0, 1.0);
			renderer.renderStandardBlock(block, x, y, z);
			tess.setNormal(-1F, 0.0F, 0.0F);

			drawX1(tess, x, y, z, uLeft, uRight, vTop, vBottom, light);

		}
		if (renderer.blockAccess.isSideSolid(x - 1, y, z, WEST, true) || (renderer.blockAccess.getBlock(x - 1, y, z).getMaterial() == Material.piston && renderer.blockAccess.getBlock(x - 1, y, z) != Blocks.piston_extension))
		{
			renderer.setRenderBounds(0.0, 0.0, 0.0, offset, 1.0, 1.0);
			renderer.renderStandardBlock(block, x, y, z);
			tess.setNormal(1.0F, 0.0F, 0.0F);

			drawX2(x, y, z, tess, uLeft, uRight, vTop, vBottom, light);

		}
		if (renderer.blockAccess.isSideSolid(x, y, z + 1, SOUTH, true) || (renderer.blockAccess.getBlock(x, y, z + 1).getMaterial() == Material.piston && renderer.blockAccess.getBlock(x, y, z + 1) != Blocks.piston_extension))
		{
			renderer.setRenderBounds(0.0, 0.0, 1.0-offset, 1.0, 1.0, 1.0);
			renderer.renderStandardBlock(block, x, y, z);
			tess.setNormal(0.0F, 0.0F, -1F);

			drayZ1(tess, x, y, z, uLeft, uRight, vTop, vBottom, light);

		}
		if (renderer.blockAccess.isSideSolid(x, y, z - 1, NORTH, true) || (renderer.blockAccess.getBlock(x, y, z - 1).getMaterial() == Material.piston && renderer.blockAccess.getBlock(x, y, z - 1) != Blocks.piston_extension))
		{
			renderer.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, offset);
			renderer.renderStandardBlock(block, x, y, z);
			tess.setNormal(0.0F, 0.0F, 1.0F);

			drawZ2(tess, x, y, z, uLeft, uRight, vTop, vBottom, light);

		}
		return true;
	}

	private void drawX1(Tessellator tess, int x, int y, int z, double uLeft, 
			double uRight, double vTop, double vBottom, int light)
	{
		//int mode = tess.instance.drawMode;
		//tess.draw();
		//tess.startDrawing(4);
		tess.setBrightness(light);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		double /*side = 1.0, point = 0.5,*/ topleft = 0.2, bottomright = 0.8;
		tess.addVertexWithUV(x + 1.0, y + topleft, z + bottomright, uLeft, vTop);
		tess.addVertexWithUV(x + 1.0, y + topleft, z + bottomright, uLeft, vBottom);
		tess.addVertexWithUV(x + 0.6, y + 0.5, z + 0.5, uRight, vBottom);
		tess.addVertexWithUV(x + 1.0, y + topleft, z + topleft, uRight, vTop);
                                               
		tess.addVertexWithUV(x + 1.0, y + topleft, z + topleft, uLeft, vTop);
		tess.addVertexWithUV(x + 1.0, y + topleft, z + topleft, uLeft, vBottom);
		tess.addVertexWithUV(x + 0.6, y + 0.5, z + 0.5, uRight, vBottom);
		tess.addVertexWithUV(x + 1.0, y + bottomright, z + topleft, uRight, vTop);
                                               
		tess.addVertexWithUV(x + 1.0, y + bottomright, z + topleft, uLeft, vTop);
		tess.addVertexWithUV(x + 1.0, y + bottomright, z + topleft, uLeft, vBottom);
		tess.addVertexWithUV(x + 0.6, y + 0.5, z + 0.5, uRight, vBottom);
		tess.addVertexWithUV(x + 1.0, y + bottomright, z + bottomright, uRight, vTop);
                                               
		tess.addVertexWithUV(x + 1.0, y + bottomright, z + bottomright, uLeft, vTop);
		tess.addVertexWithUV(x + 1.0, y + bottomright, z + bottomright, uLeft, vBottom);
		tess.addVertexWithUV(x + 0.6, y + 0.5, z + 0.5, uRight, vBottom);
		tess.addVertexWithUV(x + 1.0, y + topleft, z + bottomright, uRight, vTop);

		//tess.draw();
		//tess.startDrawing(mode);
	}

	private void drawX2(int x, int y, int z, Tessellator tess, double uLeft,
			double uRight, double vTop, double vBottom, int light) {
		//int mode = tess.instance.drawMode;
		//tess.draw();
		//tess.startDrawing(4);
		tess.setBrightness(light);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.9 ), (double)(z + 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.9 ), (double)(z + 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.4), (double)(y + 0.5 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.1 ),	(double)(z + 0.1 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.1 ), (double)(z + 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.1 ), (double)(z + 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.4), (double)(y + 0.5 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.1 ),	(double)(z + 0.9 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.1 ), (double)(z + 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.1 ), (double)(z + 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.4), (double)(y + 0.5 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.9 ),	(double)(z + 0.9 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.9 ), (double)(z + 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.9 ), (double)(z + 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.4), (double)(y + 0.5 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.0), (double)(y + 0.9 ),	(double)(z + 0.1 ), uRight, vTop);

		//tess.draw();
		//tess.startDrawing(mode);
	}

	private void drayZ1(Tessellator tess, int x, int y, int z, double uLeft,
			double uRight, double vTop, double vBottom, int light) {
		//int mode = tess.instance.drawMode;
		//tess.draw();
		//tess.startDrawing(4);
		tess.setBrightness(light);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.9 ), (double)(z + 1.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.9 ), (double)(z + 1.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.6 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.1 ),	(double)(z + 1.0 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.1 ), (double)(z + 1.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.1 ), (double)(z + 1.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.6 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.1 ),	(double)(z + 1.0 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.1 ), (double)(z + 1.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.1 ), (double)(z + 1.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.6 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.9 ),	(double)(z + 1.0 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.9 ), (double)(z + 1.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.9 ), (double)(z + 1.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.6 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.9 ),	(double)(z + 1.0 ), uRight, vTop);

		//tess.draw();
		//tess.startDrawing(mode);
	}

	private void drawZ2(Tessellator tess, int x, int y, int z, double uLeft,
			double uRight, double vTop, double vBottom, int light) {
		//int mode = tess.instance.drawMode;
		//tess.draw();
		//tess.startDrawing(4);
		tess.setBrightness(light);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.setNormal(0.0F, -1F, 0.0F);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.1 ), (double)(z + 0.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.1 ), (double)(z + 0.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.4 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.1 ),	(double)(z + 0.0 ), uRight, vTop);

		tess.setNormal(-1F, 0.0F, 0.0F);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.1 ), (double)(z + 0.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.1 ), (double)(z + 0.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.4 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.9 ),	(double)(z + 0.0 ), uRight, vTop);

		tess.setNormal(0.0F, 1F, 0.0F);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.9 ), (double)(z + 0.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1), (double)(y + 0.9 ), (double)(z + 0.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.4 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.9 ),	(double)(z + 0.0 ), uRight, vTop);

		tess.setNormal(1F, 0.0F, 0.0F);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.9 ), (double)(z + 0.0 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.9 ), (double)(z + 0.0 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5), (double)(y + 0.5 ), (double)(z + 0.4 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9), (double)(y + 0.1 ),	(double)(z + 0.0 ), uRight, vTop);

		//tess.draw();
		//tess.startDrawing(mode);
	}

	private void drawY1(Tessellator tess, int x, int y, int z, double uLeft,
			double uRight, double vTop, double vBottom, int light){
		//int mode = tess.instance.drawMode;
		//tess.draw();
		//tess.startDrawing(4);
		tess.setBrightness(light);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 1.0 ), (double)(z + 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 1.0 ), (double)(z + 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.6 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 1.0 ), (double)(z + 0.1 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 1.0 ), (double)(z + 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 1.0 ), (double)(z + 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.6 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 1.0 ), (double)(z + 0.9 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 1.0 ), (double)(z + 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 1.0 ), (double)(z + 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.6 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 1.0 ), (double)(z + 0.9 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 1.0 ), (double)(z + 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 1.0 ), (double)(z + 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.6 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 1.0 ), (double)(z + 0.1 ), uRight, vTop);

		//tess.draw();
		//tess.startDrawing(mode);
	}

	private void drawY2(Tessellator tess, int x, int y, int z, double uLeft,
			double uRight, double vTop, double vBottom, int light) {
		//int mode = tess.instance.drawMode;
		//tess.draw();
		//tess.startDrawing(4);
		tess.setBrightness(light);
		tess.setColorOpaque_F(1.0F, 1.0F, 1.0F);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 0.0 ), (double)(z + 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 0.0 ), (double)(z + 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.4 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 0.0 ), (double)(z + 0.1 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 0.0 ), (double)(z + 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 0.0 ), (double)(z + 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.4 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 0.0 ), (double)(z + 0.1 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 0.0 ), (double)(z + 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 0.0 ), (double)(z + 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.4 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 0.0 ), (double)(z + 0.9 ), uRight, vTop);

		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 0.0 ), (double)(z + 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)(x + 0.9 ), (double)(y + 0.0 ), (double)(z + 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)(x + 0.5 ), (double)(y + 0.4 ), (double)(z + 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)(x + 0.1 ), (double)(y + 0.0 ), (double)(z + 0.9 ), uRight, vTop);

		//tess.draw();
		//tess.startDrawing(mode);
	}
	
	@Override
	public boolean shouldRender3DInInventory(int modelId) {
		return true;
	}

	@Override
	public int getRenderId() {
		return Config.spikesRI;
	}

	public static void renderDo(RenderBlocks renderblocks, Block block, int meta)
	{
		Tessellator tess = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tess.startDrawingQuads();
		IIcon icon = block.getBlockTextureFromSide(0);
		tess.setNormal(0.0F, -1.0F, 0.0F);
		renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 0, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 1, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, -1.0F);
		renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 2, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 3, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(-1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 4, meta));
		tess.draw();
		tess.startDrawingQuads();
		tess.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 5, meta));
		tess.draw();
		double uLeft = (double)icon.getMinU();
		double uRight = (double)icon.getMaxU();
		double vTop = (double)icon.getMinV();
		double vBottom = (double)icon.getMaxV(); 
		tess.startDrawingQuads();
		tess.addVertexWithUV((double)( 0.1 ), (double)( 0.25 ), (double)( 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)( 0.1 ), (double)( 0.25 ), (double)( 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)( 0.5 ), (double)( 1.00 ), (double)( 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)( 0.1 ), (double)( 0.25 ), (double)( 0.1 ), uRight, vTop);

		tess.addVertexWithUV((double)( 0.1 ), (double)( 0.25 ), (double)( 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)( 0.1 ), (double)( 0.25 ), (double)( 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)( 0.5 ), (double)( 1.00 ), (double)( 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)( 0.9 ), (double)( 0.25 ), (double)( 0.1 ), uRight, vTop);

		tess.addVertexWithUV((double)( 0.9 ), (double)( 0.25 ), (double)( 0.1 ), uLeft, vTop);
		tess.addVertexWithUV((double)( 0.9 ), (double)( 0.25 ), (double)( 0.1 ), uLeft, vBottom);
		tess.addVertexWithUV((double)( 0.5 ), (double)( 1.00 ), (double)( 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)( 0.9 ), (double)( 0.25 ), (double)( 0.9 ), uRight, vTop);

		tess.addVertexWithUV((double)( 0.9 ), (double)( 0.25 ), (double)( 0.9 ), uLeft, vTop);
		tess.addVertexWithUV((double)( 0.9 ), (double)( 0.25 ), (double)( 0.9 ), uLeft, vBottom);
		tess.addVertexWithUV((double)( 0.5 ), (double)( 1.00 ), (double)( 0.5 ), uRight, vBottom);
		tess.addVertexWithUV((double)( 0.1 ), (double)( 0.25 ), (double)( 0.9 ), uRight, vTop);
		tess.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}
}
