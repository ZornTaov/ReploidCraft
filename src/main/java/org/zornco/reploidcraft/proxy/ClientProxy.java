package org.zornco.reploidcraft.proxy;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.client.ClientTickHandler;
import org.zornco.reploidcraft.client.handler.InputHandler;
import org.zornco.reploidcraft.client.render.ModelReploidBlue;
import org.zornco.reploidcraft.client.render.RenderFloatingPlatform;
import org.zornco.reploidcraft.client.render.ridearmor.RenderRideArmor;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.init.RCItems;
import org.zornco.reploidcraft.init.RCSounds;
import org.zornco.reploidcraft.item.EnumEnergyPelletStr;
import org.zornco.reploidcraft.item.IColorable;
import org.zornco.reploidcraft.item.ItemEnergyPelletBase;
import org.zornco.reploidcraft.item.ItemBasicBuster;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy {

	public static final Map<Item, ModelBiped> armorModels = new HashMap<Item, ModelBiped>();
	@Override
	public void registerHandlers() {
    	MinecraftForge.EVENT_BUS.register(new InputHandler());
    	ClientRegistry.registerKeyBinding(InputHandler.reploidMenu);
	}

	@Override
	public void registerArmors(){ 
		ModelReploidBlue reploidArmor = new ModelReploidBlue(0.1F);
		ModelReploidBlue reploidLegs = new ModelReploidBlue(0.05F);
		armorModels.put(RCItems.basicReploidArmorHelm, reploidArmor);
		armorModels.put(RCItems.basicReploidArmorChest, reploidArmor);
		armorModels.put(RCItems.basicReploidArmorLegs, reploidLegs);
		armorModels.put(RCItems.basicReploidArmorFeet, reploidArmor);
	}
	@Override
	public void registerRenderersPre() {
		RCSounds.registerSounds();
		/*
		 * Item/Block models
		 */
		//ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		this.registerItemModel(RCItems.platformPlacer);
		this.registerItemModel(RCItems.rideArmorPlacer);
		this.registerItemModel(RCItems.platformRemote);
		this.registerItemModel(RCItems.basicBuster);
		this.registerItemModel(RCItems.basicBlade);
		this.registerItemModel(RCItems.basicReploidArmorHelm);
		this.registerItemModel(RCItems.basicReploidArmorChest);
		this.registerItemModel(RCItems.basicReploidArmorLegs);
		this.registerItemModel(RCItems.basicReploidArmorFeet);
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
		RenderingRegistry.registerEntityRenderingHandler(EntityRideArmor.class,
				new IRenderFactory<EntityRideArmor>() {
			@Override
			public Render<? super EntityRideArmor> createRenderFor(RenderManager manager) {
				return new RenderRideArmor(manager);
			}
		});
	}
	public void registerRenderersInit() {
		MinecraftForge.EVENT_BUS.register(new ClientTickHandler());
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(
				new IItemColor()
				{
					@Override
					public int getColorFromItemstack(ItemStack stack, int tintIndex)
					{
						float time = ClientTickHandler.ticksInGame + ClientTickHandler.partialTicks;
                        return Color.getHSBColor(((ItemEnergyPelletBase)stack.getItem()).getColor(stack)/360.0F, 1F, (float)Math.sin(time*0.05F)/4+0.75F).hashCode();
					}
				}, RCItems.energyPellet);
		Minecraft.getMinecraft().getItemColors().registerItemColorHandler(
				new IItemColor()
				{
					@Override
					public int getColorFromItemstack(ItemStack stack, int tintIndex)
					{

				        int[] aint = new int[3];
				        int i = 0;
				        int j = 0;
                        float[] afloat = ((IColorable)stack.getItem()).getColorF3(stack);
                        int l1 = (int)(afloat[0] * 255.0F);
                        int i2 = (int)(afloat[1] * 255.0F);
                        int j2 = (int)(afloat[2] * 255.0F);
                        i += Math.max(l1, Math.max(i2, j2));
                        aint[0] += l1;
                        aint[1] += i2;
                        aint[2] += j2;
                        ++j;
                        int i1 = aint[0] / j;
                        int j1 = aint[1] / j;
                        int k1 = aint[2] / j;
                        float f3 = (float)i / (float)j;
                        float f4 = (float)Math.max(i1, Math.max(j1, k1));
                        i1 = (int)((float)i1 * f3 / f4);
                        j1 = (int)((float)j1 * f3 / f4);
                        k1 = (int)((float)k1 * f3 / f4);
                        int lvt_12_3_ = (i1 << 8) + j1;
                        lvt_12_3_ = (lvt_12_3_ << 8) + k1;
                        return lvt_12_3_;
					}
				}, new Item[] {RCItems.basicBuster, RCItems.basicBlade, RCItems.basicReploidArmorHelm, RCItems.basicReploidArmorChest, RCItems.basicReploidArmorLegs, RCItems.basicReploidArmorFeet});
	}
	private void registerItemModel(Item item)
	{
		this.registerItemModel(item, 0);
	}

	private void registerItemModel(Item item, int meta)
	{
		//ResourceLocation rl = Item.REGISTRY.getNameForObject(item);
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	private void registerItemModel(Item item, int meta, String customName)
	{
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(RCItems.energyPellet.getRegistryName() + "/" + customName, "inventory"));

	}
}
