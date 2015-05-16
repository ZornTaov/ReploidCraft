package zornco.reploidcraft.client.renderers;

import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glTranslatef;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemItemHolderRenderer implements IItemRenderer {

	private ModelItemHolder model;
	
	public ItemItemHolderRenderer() {
		this.model = new ModelItemHolder();
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		switch (type)
        {
            case ENTITY:
            {
             renderItemHolder(-0.5F, 0.0F, -0.5F);
                return;
            }
            
            case EQUIPPED:
            {
             renderItemHolder(0.0F, 0.0F, 0.0F);
                return;
            }
            
            case EQUIPPED_FIRST_PERSON:
            {
             renderItemHolder(0.0F, 0.0F, 0.0F);
                return;
            }
            
            case INVENTORY:
            {
             renderItemHolder(-0.5F, -0.5F, -0.5F);
                return;
            }
            
            default:{ }
        }	

	}
	private void renderItemHolder(float x, float y, float z)
    {
        GL11.glPushMatrix();

		glTranslatef((float) x, (float) y + 0.0F, (float) z + 1.0F);
		glScalef(1.0F, -1F, -1F);
		glTranslatef(0.5F, 0.5F, 0.5F);

        GL11.glTranslatef(x, y, z);

        FMLClientHandler.instance().getClient().renderEngine.bindTexture(TileEntityItemHolderRenderer.itemHolderTexture);

        model.render(0.0625F, true);
		glColor4f(0.25F, 0.25F, 1.0F, 0.5F);
		model.renderLight(0.0625F);

        GL11.glPopMatrix();
    }
}
