package zornco.reploidcraftenv.client.gui;

import org.lwjgl.opengl.GL11;

import zornco.reploidcraftenv.blocks.ContainerItemHolder;
import zornco.reploidcraftenv.entities.EntityRideArmor;
import zornco.reploidcraftenv.entities.ArmorParts.PartSlot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.ResourceLocation;

public class GUIItemHolder extends GuiContainer {
	private ResourceLocation resourceLocation = new ResourceLocation("reploidcraftenv", "textures/gui/itemHolder.png");

	public GUIItemHolder(IInventory player, IInventory chest)
    {
        super( new ContainerItemHolder(player, chest, 184, 184));
        this.xSize = 184;
        this.ySize = 184;
        this.allowUserInput = false;
    }

	public GUIItemHolder(EntityPlayer player, String title,
			boolean localized) {
		// TODO Auto-generated constructor stub
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
    }
}
