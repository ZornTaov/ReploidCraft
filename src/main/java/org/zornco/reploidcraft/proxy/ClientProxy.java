package org.zornco.reploidcraft.proxy;

import java.util.ArrayList;
import java.util.List;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.client.render.RenderFloatingPlatform;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;
import org.zornco.reploidcraft.init.RCItems;
import org.zornco.reploidcraft.item.EnumEnergyPelletStr;
import org.zornco.reploidcraft.item.ItemEnergyPelletBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderersPre() {
		/*
		 * Item/Block models
		 */
		this.registerItemModel(RCItems.platformPlacer);
		this.registerItemModel(RCItems.platformRemote);
		this.registerItemModel(RCItems.simpleBuster);
		for (int i = 0; i < EnumEnergyPelletStr.values().length; i++) {
			this.registerItemModel(RCItems.energyPellet, i, EnumEnergyPelletStr.byMetadata(i).getName());
		}

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
	public void registerRenderersInit() {
		// TODO Auto-generated method stub

		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(
				new IItemColor()
				{
					@Override
					public int getColorFromItemstack(ItemStack stack, int tintIndex)
					{
						return  ((ItemEnergyPelletBase)stack.getItem()).getColor(stack);
					}
				}, RCItems.energyPellet);
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

	private void registerItemModel(Item item, int meta, String customName)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(RCItems.energyPellet.getRegistryName() + "\\" + customName, "inventory"));

	}
}
