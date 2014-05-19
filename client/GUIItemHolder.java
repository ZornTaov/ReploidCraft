package zornco.reploidcraftenv.client;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraftenv.blocks.ContainerItemHolder;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

public class GUIItemHolder extends GuiContainer {
	private ResourceLocation resourceLocation = new ResourceLocation("reploidcraftenv", "textures/gui/itemHolder.png");

	GUIItemHolder(IInventory player, IInventory chest)
    {
        super( new ContainerItemHolder(player, chest, 184, 184));
        this.xSize = 184;
        this.ySize = 184;
        this.allowUserInput = false;
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
    }
}
