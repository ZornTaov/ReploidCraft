package org.zornco.reploidcraft.client.render.ridearmor.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
/**
 * ModelRideArmor - Zorn_Taov
 * Created using Tabula 5.1.0
 */
public class ModelRideArmorChest extends ModelRideArmorBase {


    
    public ModelRenderer base;
    
    public ModelRenderer frontSide;
    public ModelRenderer frontCover;
    public ModelRenderer frontCover2;
    
    public ModelRenderer frontRight1;
    public ModelRenderer frontRight2;
    public ModelRenderer frontRightBacking;
    public ModelRenderer rightVent1;
    public ModelRenderer rightVent2;
    public ModelRenderer rightVent3;
    public ModelRenderer rightVent4;
    public ModelRenderer rightVent5;
    public ModelRenderer rightVent6;
    public ModelRenderer rightVent7;
    public ModelRenderer rightVent8;
    public ModelRenderer rightVent9;

    public ModelRenderer frontLeft1;
    public ModelRenderer frontLeft2;
    public ModelRenderer frontLeftBacking;
    public ModelRenderer leftVent1;
    public ModelRenderer leftVent2;
    public ModelRenderer leftVent3;
    public ModelRenderer leftVent4;
    public ModelRenderer leftVent5;
    public ModelRenderer leftVent6;
    public ModelRenderer leftVent7;
    public ModelRenderer leftVent8;
    public ModelRenderer leftVent9;
    
    public ModelRenderer panelRoot;
    public ModelRenderer panel;
    public ModelRenderer key;
    
    public ModelRenderer teethmid;
    public ModelRenderer teethRight;
    public ModelRenderer teethLeft;
    
    public ModelRenderer jawMid;
    public ModelRenderer jawRight;
    public ModelRenderer jawLeft;
    
    public ModelRenderer backSide;
    public ModelRenderer backSide_1;
    public ModelRenderer backRight;
    public ModelRenderer backLeft;
    
    public ModelRenderer rightSide;
    public ModelRenderer rightSideBottom;
    public ModelRenderer rightNozzle;

    public ModelRenderer leftSide;
    public ModelRenderer leftSideBottom;
    public ModelRenderer leftNozzle;
    
	public ModelRenderer seatback;
    public ModelRenderer seat;
    public ModelRenderer pedelRight;
    public ModelRenderer pedelLeft;
    
    public ModelRenderer rightStick1;
    public ModelRenderer rightStick2;
    public ModelRenderer rightStick3;
    public ModelRenderer rightStick4;
    
    public ModelRenderer leftStick1;
    public ModelRenderer leftStick2;
    public ModelRenderer leftStick3;
    public ModelRenderer leftStick4;
	
	public ModelRideArmorChest() {
		super();

		//SETUP
        
        this.base = new ModelRenderer(this, 30, 25);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-10.0F, 15.0F, -10.0F, 20, 4, 18, 0.0F);
        this.setRotation(base, 0.0F, 3.141592653589793F, 0.0F);
        
        this.frontSide = new ModelRenderer(this, 0, 0);
        this.frontSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontSide.addBox(-1.0F, 0.0F, -13.0F, 2, 3, 3, 0.0F);
        this.frontCover = new ModelRenderer(this, 52, 23);
        this.frontCover.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.frontCover.addBox(-4.0F, 15.0F, 9.0F, 8, 0, 2, 0.0F);
        this.setRotation(frontCover, 0.0F, 3.141592653589793F, 0.0F);
        this.frontCover2 = new ModelRenderer(this, 55, 23);
        this.frontCover2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontCover2.addBox(-6.5F, 19.0F, 9.0F, 14, 0, 2, 0.0F);

        this.frontRight1 = new ModelRenderer(this, 0, 23);
        this.frontRight1.setRotationPoint(-1.0F, 0.0F, -13.0F);
        this.frontRight1.addBox(-13.0F, 0.0F, 0.0F, 13, 3, 3, 0.0F);
        this.setRotation(frontRight1, 0.0F, 0.39479347680111737F, 0.0F);
        this.frontRight2 = new ModelRenderer(this, 0, 29);
        this.frontRight2.setRotationPoint(1.0F, 3.05F, -0.8F);
        this.frontRight2.addBox(-14.0F, 0.0F, 0.0F, 14, 10, 2, 0.0F);
        this.setRotation(frontRight2, 0.08028514559173915F, 0.0F, 0.0F);
        this.rightVent1 = new ModelRenderer(this, 0, 41);
        this.rightVent1.setRotationPoint(-3.0F, 2.2F, -1.8F);
        this.rightVent1.addBox(-8.0F, 0.0F, 0.0F, 8, 1, 2, 0.0F);
        this.setRotation(rightVent1, 0.1308996938995747F, -0.0F, 0.0F);
        this.rightVent2 = new ModelRenderer(this, 0, 44);
        this.rightVent2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightVent2.addBox(-1.0F, 1.0F, 0.0F, 1, 2, 2, 0.0F);
        this.rightVent3 = new ModelRenderer(this, 6, 48);
        this.rightVent3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightVent3.addBox(-8.0F, 1.0F, 0.0F, 1, 4, 2, 0.0F);
        this.rightVent4 = new ModelRenderer(this, 0, 48);
        this.rightVent4.setRotationPoint(-8.0F, 5.0F, 0.0F);
        this.rightVent4.addBox(0.0F, -4.0F, 0.0F, 1, 4, 2, 0.0F);
        this.setRotation(rightVent4, 0.0F, 0.0F, 1.5707963267948966F);
        this.rightVent5 = new ModelRenderer(this, 6, 44);
        this.rightVent5.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.rightVent5.addBox(-5.0F, -1.0F, 0.0F, 5, 1, 2, 0.0F);
        this.setRotation(rightVent5, 0.0F, -0.0F, -0.6435028952103092F);
        this.rightVent6 = new ModelRenderer(this, -2, 54);
        this.rightVent6.setRotationPoint(-1.0F, 0.6F, 2.0F);
        this.rightVent6.addBox(-6.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.setRotation(rightVent6, 0.5235987755982988F, 0.0F, 0.0F);
        this.rightVent7 = new ModelRenderer(this, -2, 54);
        this.rightVent7.setRotationPoint(0.0F, 0.9F, -0.6F);
        this.rightVent7.addBox(-6.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.rightVent8 = new ModelRenderer(this, -2, 54);
        this.rightVent8.setRotationPoint(0.0F, 1.8F, -1.1F);
        this.rightVent8.addBox(-6.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.rightVent9 = new ModelRenderer(this, -2, 54);
        this.rightVent9.setRotationPoint(0.0F, 2.6F, -1.7F);
        this.rightVent9.addBox(-6.0F, 0.0F, -2.0F, 4, 0, 2, 0.0F);
        this.frontRightBacking = new ModelRenderer(this, 67, 49);
        this.frontRightBacking.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontRightBacking.addBox(-13.0F, 3.0F, 2.0F, 13, 13, 1, 0.0F);
        
        this.frontLeft1 = new ModelRenderer(this, 0, 23);
        this.frontLeft1.mirror = true;
        this.frontLeft1.setRotationPoint(1.0F, 0.0F, -13.0F);
        this.frontLeft1.addBox(0.0F, 0.0F, 0.0F, 13, 3, 3, 0.0F);
        this.setRotation(frontLeft1, 0.0F, -0.39479347680111737F, 0.0F);
        this.frontLeft2 = new ModelRenderer(this, 0, 29);
        this.frontLeft2.mirror = true;
        this.frontLeft2.setRotationPoint(-1.0F, 3.05F, -0.8F);
        this.frontLeft2.addBox(0.0F, 0.0F, 0.0F, 14, 10, 2, 0.0F);
        this.setRotation(frontLeft2, 0.08028514559173915F, 0.0F, 0.0F);
        this.leftVent1 = new ModelRenderer(this, 0, 41);
        this.leftVent1.mirror = true;
        this.leftVent1.setRotationPoint(3.0F, 2.2F, -1.8F);
        this.leftVent1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 2, 0.0F);
        this.setRotation(leftVent1, 0.1308996938995747F, 0.0F, 0.0F);
        this.leftVent2 = new ModelRenderer(this, 0, 44);
        this.leftVent2.mirror = true;
        this.leftVent2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftVent2.addBox(0.0F, 1.0F, -0.0F, 1, 2, 2, 0.0F);
        this.leftVent3 = new ModelRenderer(this, 6, 48);
        this.leftVent3.mirror = true;
        this.leftVent3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftVent3.addBox(7.0F, 1.0F, 0.0F, 1, 4, 2, 0.0F);
        this.leftVent4 = new ModelRenderer(this, 0, 48);
        this.leftVent4.mirror = true;
        this.leftVent4.setRotationPoint(8.0F, 5.0F, 0.0F);
        this.leftVent4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2, 0.0F);
        this.setRotation(leftVent4, 0.0F, -0.0F, 1.5707963267948966F);
        this.leftVent5 = new ModelRenderer(this, 6, 44);
        this.leftVent5.mirror = true;
        this.leftVent5.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.leftVent5.addBox(0.0F, -1.0F, 0.0F, 5, 1, 2, 0.0F);
        this.setRotation(leftVent5, 0.0F, 0.0F, 0.6435028952103092F);
        this.leftVent6 = new ModelRenderer(this, -2, 54);
        this.leftVent6.mirror = true;
        this.leftVent6.setRotationPoint(1.0F, 0.6F, 2.0F);
        this.leftVent6.addBox(0.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.setRotation(leftVent6, 0.5235987755982988F, -0.0F, 0.0F);
        this.leftVent7 = new ModelRenderer(this, -2, 54);
        this.leftVent7.mirror = true;
        this.leftVent7.setRotationPoint(0.0F, 0.9F, -0.6F);
        this.leftVent7.addBox(0.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.leftVent8 = new ModelRenderer(this, -2, 54);
        this.leftVent8.mirror = true;
        this.leftVent8.setRotationPoint(0.0F, 1.8F, -1.1F);
        this.leftVent8.addBox(0.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.leftVent9 = new ModelRenderer(this, -2, 54);
        this.leftVent9.mirror = true;
        this.leftVent9.setRotationPoint(0.0F, 2.6F, -1.7F);
        this.leftVent9.addBox(2.0F, 0.0F, -2.0F, 4, 0, 2, 0.0F);
        this.frontLeftBacking = new ModelRenderer(this, 67, 49);
        this.frontLeftBacking.mirror = true;
        this.frontLeftBacking.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontLeftBacking.addBox(0.0F, 3.0F, 2.0F, 13, 13, 1, 0.0F);
        
        this.panelRoot = new ModelRenderer(this, 63, 114);
        this.panelRoot.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.panelRoot.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotation(panelRoot, 0.0F, 0.9424777960769379F, 1.5707963267948966F);
        this.panel = new ModelRenderer(this, 13, 41);
        this.panel.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.panel.addBox(-1.5F, -3.5F, -0.5F, 3, 7, 7, 0.0F);
        this.key = new ModelRenderer(this, 26, 46);
        this.key.setRotationPoint(-2.0F, 0.0F, 3.0F);
        this.key.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        
        this.teethmid = new ModelRenderer(this, 3, 56);
        this.teethmid.setRotationPoint(-0.5F, 0.0F, -11.47F);
        this.teethmid.addBox(0.0F, 12.0F, 0.0F, 1, 7, 1, 0.0F);
        this.teethRight = new ModelRenderer(this, 0, 56);
        this.teethRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.teethRight.addBox(-13.0F, 12.0F, 0.0F, 13, 7, 1, 0.0F);
        this.setRotation(teethRight, 0.0F, 0.27820548276789614F, 0.0F);
        this.teethLeft = new ModelRenderer(this, 0, 56);
        this.teethLeft.mirror = true;
        this.teethLeft.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.teethLeft.addBox(0.0F, 12.0F, 0.0F, 13, 7, 1, 0.0F);
        this.setRotation(teethLeft, 0.0F, -0.27820548276789614F, 0.0F);
        
        this.jawMid = new ModelRenderer(this, 28, 56);
        this.jawMid.setRotationPoint(0.0F, 18.8F, -12.7F);
        this.jawMid.addBox(-3.5F, 0.0F, 0.0F, 7, 5, 3, 0.0F);
        this.setRotation(jawMid, 0.15707963267948966F, 0.0F, 0.0F);
        this.jawRight = new ModelRenderer(this, 33, 48);
        this.jawRight.setRotationPoint(-1.3F, 0.0F, 0.1F);
        this.jawRight.addBox(-14.0F, 0.0F, 0.0F, 14, 5, 3, 0.0F);
        this.setRotation(jawRight, 0.0F, 0.39479347680111737F, 0.39479347680111737F);
        this.jawLeft = new ModelRenderer(this, 33, 48);
        this.jawLeft.mirror = true;
        this.jawLeft.setRotationPoint(1.3F, 0.0F, 0.1F);
        this.jawLeft.addBox(0.0F, 0.0F, 0.0F, 14, 5, 3, 0.0F);
        this.setRotation(jawLeft, 0.0F, -0.39479347680111737F, -0.39479347680111737F);


        this.backSide = new ModelRenderer(this, 42, 0);
        this.backSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.backSide.addBox(-9.0F, 0.0F, 10.0F, 18, 15, 3, 0.0F);
        this.backSide_1 = new ModelRenderer(this, 42, 15);
        this.backSide_1.setRotationPoint(-9.0F, 15.0F, 13.0F);
        this.backSide_1.addBox(0.0F, 0.0F, -3.0F, 18, 5, 3, 0.0F);
        this.setRotation(backSide_1, -0.6435028952103092F, 0.0F, 0.0F);
        this.backRight = new ModelRenderer(this, 32, 23);
        this.backRight.setRotationPoint(-9.0F, 0.0F, 13.0F);
        this.backRight.addBox(-5.0F, 0.0F, -3.0F, 5, 15, 3, 0.0F);
        this.setRotation(backRight, 0.0F, -0.6435028952103092F, 0.0F);
        this.backLeft = new ModelRenderer(this, 32, 23);
        this.backLeft.mirror = true;
        this.backLeft.setRotationPoint(9.0F, 0.0F, 13.0F);
        this.backLeft.addBox(0.0F, 0.0F, -3.0F, 5, 15, 3, 0.0F);
        this.setRotation(backLeft, 0.0F, 0.6435028952103092F, 0.0F);

        this.rightSide = new ModelRenderer(this, 0, 0);
        this.rightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightSide.addBox(-10.0F, 0.0F, -13.0F, 18, 15, 3, 0.0F);
        this.setRotation(rightSide, 0.0F, 1.5707963267948966F, 0.0F);
        this.rightSideBottom = new ModelRenderer(this, 0, 15);
        this.rightSideBottom.setRotationPoint(-10.0F, 15.0F, -13.0F);
        this.rightSideBottom.addBox(0.0F, 0.0F, 0.0F, 18, 5, 3, 0.0F);
        this.setRotation(rightSideBottom, 0.6435028952103092F, 0.0F, 0.0F);
        this.rightNozzle = new ModelRenderer(this, 88, 38);
        this.rightNozzle.setRotationPoint(-4.0F, 15.0F, -13.0F);
        this.rightNozzle.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 2, 0.0F);
        this.setRotation(rightNozzle, 0.3218387140677544F, 0.0F, 0.0F);

        this.leftSide = new ModelRenderer(this, 0, 0);
        this.leftSide.mirror = true;
        this.leftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftSide.addBox(-8.0F, 0.0F, -13.0F, 18, 15, 3, 0.0F);
        this.setRotation(leftSide, 0.0F, -1.5707963267948966F, 0.0F);
        this.leftSideBottom = new ModelRenderer(this, 0, 15);
        this.leftSideBottom.mirror = true;
        this.leftSideBottom.setRotationPoint(-8.0F, 15.0F, -13.0F);
        this.leftSideBottom.addBox(0.0F, 0.0F, 0.0F, 18, 5, 3, 0.0F);
        this.setRotation(leftSideBottom, 0.6435028952103092F, 0.0F, 0.0F);
        this.leftNozzle = new ModelRenderer(this, 88, 38);
        this.leftNozzle.mirror = true;
        this.leftNozzle.setRotationPoint(4.0F, 15.0F, -13.0F);
        this.leftNozzle.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 2, 0.0F);
        this.setRotation(leftNozzle, 0.3218387140677544F, 0.0F, 0.0F);
        
        this.seatback = new ModelRenderer(this, 88, 15);
        this.seatback.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.seatback.addBox(-6.0F, -4.0F, 7.0F, 12, 18, 3, 0.0F);
        this.seat = new ModelRenderer(this, 104, 0);
        this.seat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.seat.addBox(-7.0F, -6.0F, 11.0F, 8, 12, 3, 0.0F);
        this.setRotation(seat, 0.0F, 1.5707963267948966F, 1.5707963267948966F);
        this.pedelLeft = new ModelRenderer(this, 48, 56);
        this.pedelLeft.mirror = true;
        this.pedelLeft.setRotationPoint(2.0F, 8.0F, 4.0F);
        this.pedelLeft.addBox(-3.0F, 11.0F, -3.0F, 6, 1, 7, 0.0F);
        this.setRotation(pedelLeft, -1.413716694115407F, -0.3141592653589793F, 0.0F);
        this.pedelRight = new ModelRenderer(this, 48, 56);
        this.pedelRight.setRotationPoint(-2.0F, 8.0F, 4.0F);
        this.pedelRight.addBox(-3.0F, 11.0F, -3.0F, 6, 1, 7, 0.0F);
        this.setRotation(pedelRight, -1.413716694115407F, 0.3141592653589793F, 0.0F);
        
        this.rightStick1 = new ModelRenderer(this, 84, 0);
        this.rightStick1.setRotationPoint(-10.0F, 4.5F, -0.5F);
        this.rightStick1.addBox(-2.0F, -3.5F, -3.5F, 3, 7, 7, 0.0F);
        this.rightStick2 = new ModelRenderer(this, 97, 1);
        this.rightStick2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightStick2.addBox(-0.5F, 0.5F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotation(rightStick2, 0.0F, 0.9424777960769379F, -1.5707963267948966F);
        this.rightStick3 = new ModelRenderer(this, 84, 0);
        this.rightStick3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightStick3.addBox(1.0F, 1.0F, -1.0F, 1, 5, 2, 0.0F);
        this.rightStick4 = new ModelRenderer(this, 84, 0);
        this.rightStick4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightStick4.addBox(-1.0F, 1.0F, -1.0F, 1, 5, 2, 0.0F);
        
        this.leftStick1 = new ModelRenderer(this, 84, 0);
        this.leftStick1.mirror = true;
        this.leftStick1.setRotationPoint(10.0F, 4.5F, -0.5F);
        this.leftStick1.addBox(-1.0F, -3.5F, -3.5F, 3, 7, 7, 0.0F);
        this.leftStick2 = new ModelRenderer(this, 97, 1);
        this.leftStick2.mirror = true;
        this.leftStick2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftStick2.addBox(-0.5F, -5.5F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotation(leftStick2, 0.0F, 0.9424777960769379F, -1.5707963267948966F);
        this.leftStick3 = new ModelRenderer(this, 84, 0);
        this.leftStick3.mirror = true;
        this.leftStick3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftStick3.addBox(-1.0F, -6.0F, -1.0F, 1, 5, 2, 0.0F);
        this.leftStick4 = new ModelRenderer(this, 84, 0);
        this.leftStick4.mirror = true;
        this.leftStick4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftStick4.addBox(1.0F, -6.0F, -1.0F, 1, 5, 2, 0.0F);
        
        
        
        
        //BIND

        this.rootTorso.addChild(this.base);
        this.rootTorso.addChild(this.frontSide);
        this.frontSide.addChild(this.frontCover);
        this.frontCover.addChild(this.frontCover2);
        this.frontSide.addChild(this.frontRight1);
        this.frontRight1.addChild(this.frontRight2);
        this.frontRight2.addChild(this.rightVent1);
        this.rightVent1.addChild(this.rightVent2);
        this.rightVent1.addChild(this.rightVent3);
        this.rightVent1.addChild(this.rightVent4);
        this.rightVent1.addChild(this.rightVent5);
        this.rightVent1.addChild(this.rightVent6);
        this.rightVent6.addChild(this.rightVent7);
        this.rightVent6.addChild(this.rightVent8);
        this.rightVent6.addChild(this.rightVent9);
        this.frontRight1.addChild(this.frontRightBacking);
        
        this.frontSide.addChild(this.frontLeft1);
        this.frontLeft1.addChild(this.frontLeft2);
        this.frontLeft2.addChild(this.leftVent1);
        this.leftVent1.addChild(this.leftVent2);
        this.leftVent1.addChild(this.leftVent3);
        this.leftVent1.addChild(this.leftVent4);
        this.leftVent1.addChild(this.leftVent5);
        this.leftVent1.addChild(this.leftVent6);
        this.leftVent6.addChild(this.leftVent7);
        this.leftVent6.addChild(this.leftVent8);
        this.leftVent6.addChild(this.leftVent9);
        this.frontLeft1.addChild(this.frontLeftBacking);
        
        this.frontSide.addChild(this.panelRoot);
        this.panelRoot.addChild(this.panel);
        this.panel.addChild(this.key);
        this.frontSide.addChild(this.teethmid);
        this.teethmid.addChild(this.teethRight);
        this.teethmid.addChild(this.teethLeft);
        this.frontSide.addChild(this.jawMid);
        this.jawMid.addChild(this.jawRight);
        this.jawMid.addChild(this.jawLeft);
        

        this.rootTorso.addChild(this.backSide);
        this.backSide.addChild(this.backSide_1);
        this.backSide.addChild(this.backRight);
        this.backSide.addChild(this.backLeft);
        
        this.rootTorso.addChild(this.rightSide);
        this.rightSide.addChild(this.rightSideBottom);
        this.rightSide.addChild(this.rightNozzle);
        
        this.rootTorso.addChild(this.leftSide);
        this.leftSide.addChild(this.leftSideBottom);
        this.leftSide.addChild(this.leftNozzle);
        
        
        this.seatback.addChild(this.seat);
        this.seatback.addChild(this.pedelLeft);
        this.seatback.addChild(this.pedelRight);

        this.rootTorso.addChild(this.rightStick1);
        this.rightStick1.addChild(this.rightStick2);
        this.rightStick2.addChild(this.rightStick3);
        this.rightStick2.addChild(this.rightStick4);
        this.rootTorso.addChild(this.leftStick1);
        this.leftStick1.addChild(this.leftStick2);
        this.leftStick2.addChild(this.leftStick3);
        this.leftStick2.addChild(this.leftStick4);
	}

	@Override
	public void renderPart(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        //flip to change "on" state from 0, 54, 90 to 180, -180+54, 90
        //setRotateAngle(panel, 0, 0, (float)Math.toRadians((Math.toDegrees(limbSwing)%180>90?-180:0)));
        this.rootTorso.render(scale);
        this.seatback.render(scale);
	}

}
