package zornco.reploidcraftenv.client.renderers.mechParts;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRideArmorTorso extends ModelRideArmorBase
{
	//fields
	ModelRenderer leftPedel;
	ModelRenderer rightPedel;
	ModelRenderer leftVentA;
	ModelRenderer leftVentAAngled1;
	ModelRenderer leftVentAAngled2;
	ModelRenderer rightVentA;
	ModelRenderer rightVentAAngled1;
	ModelRenderer rightVentAAngled2;
	ModelRenderer seat;
	ModelRenderer chest;

	public ModelRideArmorTorso()
	{
		super();
		setTextureOffset("rightPedel.pedel", 0, 100);
		setTextureOffset("leftPedel.pedel", 0, 100);
		setTextureOffset("seat.seatbottom", 0, 46);
		setTextureOffset("seat.seatback", 26, 63);
		setTextureOffset("seat.leftStick1", 8, 57);
		setTextureOffset("seat.leftStick2", 0, 57);
		setTextureOffset("seat.leftStick3", 3, 62);
		setTextureOffset("seat.rightStick1", 8, 57);
		setTextureOffset("seat.rightStick2", 0, 57);
		setTextureOffset("seat.rightStick3", 3, 62);
		
		setTextureOffset("chest.frontSide", 0, 0);
		setTextureOffset("chest.backSide", 0, 19);
		setTextureOffset("chest.rightSide", 0, 64);
		setTextureOffset("chest.leftSide", 46, 64);
		setTextureOffset("chest.base", 0, 100);
		setTextureOffset("chest.leftNozzle", 94, 24);
		setTextureOffset("chest.rightNozzle", 94, 24);
		setTextureOffset("rightVentA.rightVent1", 0, 38);
		setTextureOffset("rightVentA.rightVent2", 0, 41);
		setTextureOffset("rightVentA.rightVent3", 26, 38);
		setTextureOffset("rightVentA.rightVent4", 6, 41);
		setTextureOffset("rightVentAAngled1.rightVent5", 20, 38);
		setTextureOffset("rightVentAAngled2.rightVent6", 4, 44);
		setTextureOffset("rightVentAAngled2.rightVent7", 4, 44);
		setTextureOffset("rightVentAAngled2.rightVent8", 5, 44);
		setTextureOffset("rightVentAAngled2.rightVent9", 6, 44);
		setTextureOffset("leftVentA.leftVent1", 0, 38);
		setTextureOffset("leftVentA.leftVent2", 0, 41);
		setTextureOffset("leftVentA.leftVent3", 26, 38);
		setTextureOffset("leftVentA.leftVent4", 6, 41);
		setTextureOffset("leftVentAAngled1.leftVent5", 20, 38);
		setTextureOffset("leftVentAAngled2.leftVent6", 4, 44);
		setTextureOffset("leftVentAAngled2.leftVent7", 4, 44);
		setTextureOffset("leftVentAAngled2.leftVent8", 5, 44);
		setTextureOffset("leftVentAAngled2.leftVent9", 6, 44);
	
		torso = new ModelRenderer(this, "torso");
		torso.setRotationPoint(0F, -24F, 0F);
		setRotation(torso, 0F, 0F, 0F);
			seat = new ModelRenderer(this, "seat");
			seat.setRotationPoint(0F, 0F, 0F);
			setRotation(seat, 0F, 0F, 0F);
			rightPedel = new ModelRenderer(this, "rightPedel");
			rightPedel.setRotationPoint(-2F, 8F, 4F);
			setRotation(rightPedel, -1.256637F, 0.3141593F, 0F);
			rightPedel.addBox("pedel", -3F, 12F, -3F, 6, 1, 7);
			seat.addChild(rightPedel);
			leftPedel = new ModelRenderer(this, "leftPedel");
			leftPedel.setRotationPoint(2F, 8F, 4F);
			setRotation(leftPedel, -1.256637F, -0.3141593F, 0F);
			leftPedel.mirror = true;
			leftPedel.addBox("pedel", -3F, 12F, -3F, 6, 1, 7);
			seat.addChild(leftPedel);
			seat.addBox("seatbottom", -6F, 11F, -2F, 12, 3, 8);
			seat.addBox("seatback", -6F, -4F, 6F, 12, 18, 3);
			seat.mirror = true;
			seat.addBox("leftStick1", 5F, 3F, -5F, 5, 2, 2);
			seat.addBox("leftStick2", 5F, -5F, -5F, 2, 8, 2);
			seat.mirror = false;
			seat.addBox("leftStick3", 9F, 1.5F, -6.5F, 3, 5, 5);
			seat.addBox("rightStick1", -10F, 3F, -5F, 5, 2, 2);
			seat.addBox("rightStick2", -7F, -5F, -5F, 2, 8, 2);
			seat.addBox("rightStick3", -12F, 1.5F, -6.5F, 3, 5, 5);
		torso.addChild(seat);
		
		
			chest = new ModelRenderer(this, "chest");
			chest.setRotationPoint(0F, 0F, 0F);
			setRotation(chest, 0F, 0F, 0F);
			chest.addBox("frontSide", -13F, 0F, -13F, 26, 16, 3);
			chest.addBox("backSide", -13F, 0F, 10F, 26, 16, 3);
			chest.addBox("rightSide", -13F, 0F, -10F, 3, 16, 20);
			chest.addBox("leftSide", 10F, 0F, -10F, 3, 16, 20);
			chest.addBox("base", -13F, 16F, -13F, 26, 2, 26);
			chest.mirror = true;
			chest.addBox("leftNozzle", 13F, 13.5F, -0.5F, 1, 3, 3);
			chest.mirror = false;
			chest.addBox("rightNozzle", -14F, 13.5F, -0.5F, 1, 3, 3);
				leftVentA = new ModelRenderer(this, "leftVentA");
				leftVentA.setRotationPoint(0F, 0F, 0F);
				setRotation(leftVentA, 0F, 0F, 0F);
				leftVentA.mirror = true;
				leftVentA.addBox("leftVent1", 2F, 5F, -15F, 8, 1, 2);
				leftVentA.addBox("leftVent2", 2F, 6F, -15F, 1, 2, 2);
				leftVentA.addBox("leftVent3", 9F, 6F, -15F, 1, 4, 2);
				leftVentA.addBox("leftVent4", 5F, 10F, -15F, 5, 1, 2);
					leftVentAAngled1 = new ModelRenderer(this, "leftVentAAngled1");
					leftVentAAngled1.setRotationPoint(0F, 0F, 0F);
					setRotation(leftVentAAngled1, 0F, 0F, -0.7853982F);
					leftVentAAngled1.mirror = true;
					leftVentAAngled1.addBox("leftVent5", -4F, 7F, -15F, 1, 4, 2);
				leftVentA.addChild(leftVentAAngled1);
					leftVentAAngled2 = new ModelRenderer(this, "leftVentAAngled2");
					leftVentAAngled2.setRotationPoint(0F, 0F, 0F);
					setRotation(leftVentAAngled2, 0.1858931F, 0F, 0F);
					leftVentAAngled2.mirror = true;
					leftVentAAngled2.addBox("leftVent6", 3F, 4F, -16F, 6, 0, 2);
					leftVentAAngled2.addBox("leftVent7", 3F, 5F, -16F, 6, 0, 2);
					leftVentAAngled2.addBox("leftVent8", 4F, 6F, -16F, 5, 0, 2);
					leftVentAAngled2.addBox("leftVent9", 5F, 7F, -16F, 4, 0, 2);
				leftVentA.addChild(leftVentAAngled2);
			chest.addChild(leftVentA);
				rightVentA = new ModelRenderer(this, "rightVentA");
				rightVentA.setRotationPoint(0F, 0F, 0F);
				setRotation(rightVentA, 0F, 0F, 0F);
				rightVentA.addBox("rightVent1", -10F, 5F, -15F, 8, 1, 2);
				rightVentA.addBox("rightVent2", -3F, 6F, -15F, 1, 2, 2);
				rightVentA.addBox("rightVent3", -10F, 6F, -15F, 1, 4, 2);
				rightVentA.addBox("rightVent4", -10F, 10F, -15F, 5, 1, 2);
					rightVentAAngled1 = new ModelRenderer(this, "rightVentAAngled1");
					rightVentAAngled1.setRotationPoint(0F, 0F, 0F);
					setRotation(rightVentAAngled1, 0F, 0F, 0.7853982F);
					rightVentAAngled1.addBox("rightVent5", 3F, 7F, -15F, 1, 4, 2);
				rightVentA.addChild(rightVentAAngled1);
					rightVentAAngled2 = new ModelRenderer(this, "rightVentAAngled2");
					rightVentAAngled2.setRotationPoint(0F, 0F, 0F);
					setRotation(rightVentAAngled2, 0.1858931F, 0F, 0F);
					rightVentAAngled2.addBox("rightVent6", -9F, 4F, -16F, 6, 0, 2);
					rightVentAAngled2.addBox("rightVent7", -9F, 5F, -16F, 6, 0, 2);
					rightVentAAngled2.addBox("rightVent8", -9F, 6F, -16F, 5, 0, 2);
					rightVentAAngled2.addBox("rightVent9", -9F, 7F, -16F, 4, 0, 2);
				rightVentA.addChild(rightVentAAngled2);
			chest.addChild(rightVentA);
		torso.addChild(chest);
	}

	public void renderPart(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.renderPart(entity, f, f1, f2, f3, f4, f5);
		torso.render(f5);
	}
	
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float f7 = (float)(Math.sin((double)(f1 * (float)Math.PI * 0.2F )))/4f * f;
		setRotation(chest, 0F, f7/2, 0F);
	}

}
