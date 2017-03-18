package org.zornco.reploidcraft.proxy;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.block.BlockMechBay;
import org.zornco.reploidcraft.entities.EntityFloatingPlatform;
import org.zornco.reploidcraft.entities.EntityRideArmor;
import org.zornco.reploidcraft.entities.armorparts.PartSlot;
import org.zornco.reploidcraft.entities.armorparts.PartsRegistry;
import org.zornco.reploidcraft.entities.armorparts.frog.FrogHead;
import org.zornco.reploidcraft.entities.armorparts.green.GreenArm;
import org.zornco.reploidcraft.entities.armorparts.green.GreenBack;
import org.zornco.reploidcraft.entities.armorparts.green.GreenBody;
import org.zornco.reploidcraft.entities.armorparts.green.GreenLegs;
import org.zornco.reploidcraft.entities.armorparts.red.RedBody;
import org.zornco.reploidcraft.init.RCBlocks;
import org.zornco.reploidcraft.init.RCItems;
import org.zornco.reploidcraft.item.ItemRideArmorPart;

public class CommonProxy {

	public PartsRegistry partRegistry = new PartsRegistry();
	public void registerEntities()
	{
        int id = 0;
        EntityRegistry.registerModEntity(new ResourceLocation(ReploidCraft.MODID, "floatingplatform"), EntityFloatingPlatform.class, "floatingplatform", id++, ReploidCraft.instance, 64, 3, true);
        EntityRegistry.registerModEntity(new ResourceLocation(ReploidCraft.MODID, "ridearmor"), EntityRideArmor.class, "ridearmor", id++, ReploidCraft.instance, 64, 3, true);
	}
	public void registerRideArmorParts() 
	{
		this.partRegistry.registerPart("EMPTY", PartSlot.HEAD, 		0, new float[] {0.0F, 3.0F, 0.0F}, new float[] {0.5F, 1.5F});
		this.partRegistry.registerPart("EMPTY", PartSlot.BODY, 		0, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.5F, 1.5F});
		this.partRegistry.registerPart("EMPTY", PartSlot.BACK, 		0, new float[] {0.0F, 1.5F, 0.0F}, new float[] {1.0F, 1.0F});
		this.partRegistry.registerPart("EMPTY", PartSlot.LEGS, 		0, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.5F, 1.0F});
		this.partRegistry.registerPart("EMPTY", PartSlot.ARMLEFT, 	0, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F});  
		this.partRegistry.registerPart("EMPTY", PartSlot.ARMRIGHT, 	0, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.0F, 1.0F});
		
		this.partRegistry.registerPart("GREEN", PartSlot.BODY, 		new GreenBody());
		this.partRegistry.registerPart("GREEN", PartSlot.BACK, 		new GreenBack());
		this.partRegistry.registerPart("GREEN", PartSlot.LEGS, 		new GreenLegs());
		this.partRegistry.registerPart("GREEN", PartSlot.ARMLEFT, 	new GreenArm());
		this.partRegistry.registerPart("GREEN", PartSlot.ARMRIGHT, 	new GreenArm(PartSlot.ARMRIGHT));
		
		this.partRegistry.registerPart("RED", PartSlot.BODY, 		new RedBody());
		//this.partRegistry.registerPart("RED", PartSlot.BACK, 		new RedBack());
		this.partRegistry.registerPart("RED", PartSlot.LEGS, 		20, new float[] {0.0F, 0.0F, 0.0F}, new float[] {1.5F, 1.0F});
		this.partRegistry.registerPart("RED", PartSlot.ARMLEFT, 	20, new float[] {1.25F, 1.0F, 0.0F}, new float[] {1.0F, 1.0F});
		this.partRegistry.registerPart("RED", PartSlot.ARMRIGHT, 	20, new float[] {-1.25F, 1.0F, 0.0F}, new float[] {1.0F, 1.0F});

		this.partRegistry.registerPart("FROG", PartSlot.HEAD, 		new FrogHead());
		( (ItemRideArmorPart) RCItems.rideArmorPart ).populateParts();
		
		ReploidCraft.logger.info("PARTS DONE");
	}
	public void registerRenderersPre() {}
	public void registerRenderersInit() {}
	public void registerHandlers() {}
	public void registerArmors() {}
	public void init() {
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			GameRegistry.registerTileEntity(RCBlocks.mechBay.createNewTileEntity(null, i).getClass(), RCBlocks.mechBay.getRegistryName().toString()+"."+BlockMechBay.EnumType.byMetadata(i));
		}
	}

}
