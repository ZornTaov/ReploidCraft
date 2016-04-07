package org.zornco.reploidcraft.proxy;

import org.zornco.reploidcraft.client.render.RenderFloatingPlatform;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;
import org.zornco.reploidcraft.init.RCItems;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		/*
		 * Item/Block models
		 */
		this.registerItemModel(RCItems.platformPlacer);
		
		
		/*
		 * Entities
		 */
		RenderingRegistry.registerEntityRenderingHandler(EntityFloatingPlatform.class,
				new IRenderFactory<EntityFloatingPlatform>() {
					@Override
					public Render<? super EntityFloatingPlatform> createRenderFor(RenderManager manager) {
						return new RenderFloatingPlatform(manager);
					}
				});
	}
	private void registerItemModel(Item item)
    {
        this.registerItemModel(item, 0);
    }

    private void registerItemModel(Item item, int meta)
    {
        ResourceLocation rl = Item.itemRegistry.getNameForObject(item);
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(rl, "inventory"));
    }
}
