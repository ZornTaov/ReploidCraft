package org.zornco.reploidcraft.client.render.ridearmor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

/**
 * ModelRideArmor - Zorn_Taov
 * Created using Tabula 5.1.0
 */
public class ModelRideArmor extends ModelBase {
    public ModelRenderer rootTorso;
    public ModelRenderer rootArmLeft;
    public ModelRenderer rootArmRight;
    public ModelRenderer rootBack;
    public ModelRenderer seatback;
    public ModelRenderer rootLegs;
    public ModelRenderer base;
    public ModelRenderer frontSide;
    public ModelRenderer backSide;
    public ModelRenderer rightSide;
    public ModelRenderer leftSide;
    public ModelRenderer rightStick1;
    public ModelRenderer leftStick1;
    public ModelRenderer frontLeft1;
    public ModelRenderer frontRight1;
    public ModelRenderer frontCover;
    public ModelRenderer jawMid;
    public ModelRenderer teethmid;
    public ModelRenderer panelRoot;
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
    public ModelRenderer frontCover2;
    public ModelRenderer jawRight;
    public ModelRenderer jawLeft;
    public ModelRenderer teethRight;
    public ModelRenderer teethLeft;
    public ModelRenderer panel;
    public ModelRenderer key;
    public ModelRenderer backLeft;
    public ModelRenderer backRight;
    public ModelRenderer backSide_1;
    public ModelRenderer rightNozzle;
    public ModelRenderer rightSideBottom;
    public ModelRenderer leftNozzle;
    public ModelRenderer leftSideBottom;
    public ModelRenderer rightStick2;
    public ModelRenderer rightStick3;
    public ModelRenderer rightStick4;
    public ModelRenderer leftStick2;
    public ModelRenderer leftStick3;
    public ModelRenderer leftStick4;
    public ModelRenderer armLeft1;
    public ModelRenderer armLeft2;
    public ModelRenderer upperArmLeft;
    public ModelRenderer jointArmLeft;
    public ModelRenderer lowerArmLeft;
    public ModelRenderer armRight1;
    public ModelRenderer armRight2;
    public ModelRenderer upperArmRight;
    public ModelRenderer joinArmRight;
    public ModelRenderer lowerArmRight;
    public ModelRenderer Mid;
    public ModelRenderer tankRight;
    public ModelRenderer tankLeft;
    public ModelRenderer topRight;
    public ModelRenderer chokeRight;
    public ModelRenderer nozzleRight;
    public ModelRenderer topLeft;
    public ModelRenderer chokeLeft;
    public ModelRenderer nozzleLeft;
    public ModelRenderer seat;
    public ModelRenderer pedelRight;
    public ModelRenderer pedelLeft;
    public ModelRenderer hips;
    public ModelRenderer panelLeft1;
    public ModelRenderer panelLeft2;
    public ModelRenderer panelRight1;
    public ModelRenderer panelRight2;
    public ModelRenderer upperLegLeft;
    public ModelRenderer upperLegRight;
    public ModelRenderer kneeLeft;
    public ModelRenderer lowerLegLeft;
    public ModelRenderer kneeRight;
    public ModelRenderer lowerLegRight;

    public ModelRideArmor() {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.frontLeftBacking = new ModelRenderer(this, 67, 49);
        this.frontLeftBacking.mirror = true;
        this.frontLeftBacking.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontLeftBacking.addBox(0.0F, 3.0F, 2.0F, 13, 13, 1, 0.0F);
        this.leftVent6 = new ModelRenderer(this, -2, 54);
        this.leftVent6.mirror = true;
        this.leftVent6.setRotationPoint(1.0F, 0.6F, 2.0F);
        this.leftVent6.addBox(0.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.setRotateAngle(leftVent6, 0.5235987755982988F, -0.0F, 0.0F);
        this.chokeRight = new ModelRenderer(this, 41, 107);
        this.chokeRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.chokeRight.addBox(-8.0F, 11.0F, 14.0F, 4, 1, 4, 0.0F);
        this.rightStick1 = new ModelRenderer(this, 84, 0);
        this.rightStick1.setRotationPoint(-10.0F, 4.5F, -0.5F);
        this.rightStick1.addBox(-2.0F, -3.5F, -3.5F, 3, 7, 7, 0.0F);
        this.leftVent1 = new ModelRenderer(this, 0, 41);
        this.leftVent1.mirror = true;
        this.leftVent1.setRotationPoint(3.0F, 2.2F, -1.8F);
        this.leftVent1.addBox(0.0F, 0.0F, 0.0F, 8, 1, 2, 0.0F);
        this.setRotateAngle(leftVent1, 0.1308996938995747F, 0.0F, 0.0F);
        this.leftVent2 = new ModelRenderer(this, 0, 44);
        this.leftVent2.mirror = true;
        this.leftVent2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftVent2.addBox(0.0F, 1.0F, -0.0F, 1, 2, 2, 0.0F);
        this.leftVent8 = new ModelRenderer(this, -2, 54);
        this.leftVent8.mirror = true;
        this.leftVent8.setRotationPoint(0.0F, 1.8F, -1.1F);
        this.leftVent8.addBox(0.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.leftStick1 = new ModelRenderer(this, 84, 0);
        this.leftStick1.mirror = true;
        this.leftStick1.setRotationPoint(10.0F, 4.5F, -0.5F);
        this.leftStick1.addBox(-1.0F, -3.5F, -3.5F, 3, 7, 7, 0.0F);
        this.jawRight = new ModelRenderer(this, 33, 48);
        this.jawRight.setRotationPoint(-1.3F, 0.0F, 0.1F);
        this.jawRight.addBox(-14.0F, 0.0F, 0.0F, 14, 5, 3, 0.0F);
        this.setRotateAngle(jawRight, 0.0F, 0.39479347680111737F, 0.39479347680111737F);
        this.rightVent3 = new ModelRenderer(this, 6, 48);
        this.rightVent3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightVent3.addBox(-8.0F, 1.0F, 0.0F, 1, 4, 2, 0.0F);
        this.seatback = new ModelRenderer(this, 88, 15);
        this.seatback.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.seatback.addBox(-6.0F, -4.0F, 7.0F, 12, 18, 3, 0.0F);
        this.leftVent4 = new ModelRenderer(this, 0, 48);
        this.leftVent4.mirror = true;
        this.leftVent4.setRotationPoint(8.0F, 5.0F, 0.0F);
        this.leftVent4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(leftVent4, 0.0F, -0.0F, 1.5707963267948966F);
        this.panelLeft2 = new ModelRenderer(this, 48, 68);
        this.panelLeft2.mirror = true;
        this.panelLeft2.setRotationPoint(11.0F, -3.0F, -4.0F);
        this.panelLeft2.addBox(-6.5F, -2.0F, -1.0F, 7, 7, 1, 0.0F);
        this.setRotateAngle(panelLeft2, -0.27314402793711257F, -0.5009094953223726F, 0.0F);
        this.kneeLeft = new ModelRenderer(this, 21, 6);
        this.kneeLeft.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.kneeLeft.addBox(-7.0F, -2.5F, -2.5F, 8, 5, 5, 0.0F);
        this.setRotateAngle(kneeLeft, 0.7853981633974483F, 0.0F, 0.0F);
        this.rootArmRight = new ModelRenderer(this, 0, 0);
        this.rootArmRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootArmRight.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.frontCover = new ModelRenderer(this, 52, 23);
        this.frontCover.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.frontCover.addBox(-4.0F, 15.0F, 9.0F, 8, 0, 2, 0.0F);
        this.setRotateAngle(frontCover, 0.0F, 3.141592653589793F, 0.0F);
        this.backRight = new ModelRenderer(this, 32, 23);
        this.backRight.setRotationPoint(-9.0F, 0.0F, 13.0F);
        this.backRight.addBox(-5.0F, 0.0F, -3.0F, 5, 15, 3, 0.0F);
        this.setRotateAngle(backRight, 0.0F, -0.6435028952103092F, 0.0F);
        this.tankRight = new ModelRenderer(this, 6, 114);
        this.tankRight.mirror = true;
        this.tankRight.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.tankRight.addBox(-9.0F, 3.0F, 13.0F, 6, 8, 6, 0.0F);
        this.leftVent3 = new ModelRenderer(this, 6, 48);
        this.leftVent3.mirror = true;
        this.leftVent3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftVent3.addBox(7.0F, 1.0F, 0.0F, 1, 4, 2, 0.0F);
        this.backSide_1 = new ModelRenderer(this, 42, 15);
        this.backSide_1.setRotationPoint(-9.0F, 15.0F, 13.0F);
        this.backSide_1.addBox(0.0F, 0.0F, -3.0F, 18, 5, 3, 0.0F);
        this.setRotateAngle(backSide_1, -0.6435028952103092F, 0.0F, 0.0F);
        this.rootBack = new ModelRenderer(this, 0, 0);
        this.rootBack.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootBack.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.rightNozzle = new ModelRenderer(this, 88, 38);
        this.rightNozzle.setRotationPoint(-4.0F, 15.0F, -13.0F);
        this.rightNozzle.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 2, 0.0F);
        this.setRotateAngle(rightNozzle, 0.3218387140677544F, 0.0F, 0.0F);
        this.seat = new ModelRenderer(this, 104, 0);
        this.seat.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.seat.addBox(-7.0F, -6.0F, 11.0F, 8, 12, 3, 0.0F);
        this.setRotateAngle(seat, 0.0F, 1.5707963267948966F, 1.5707963267948966F);
        this.rightStick2 = new ModelRenderer(this, 97, 1);
        this.rightStick2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightStick2.addBox(-0.5F, 0.5F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotateAngle(rightStick2, 0.0F, 0.9424777960769379F, -1.5707963267948966F);
        this.leftStick4 = new ModelRenderer(this, 84, 0);
        this.leftStick4.mirror = true;
        this.leftStick4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftStick4.addBox(1.0F, -6.0F, -1.0F, 1, 5, 2, 0.0F);
        this.backLeft = new ModelRenderer(this, 32, 23);
        this.backLeft.mirror = true;
        this.backLeft.setRotationPoint(9.0F, 0.0F, 13.0F);
        this.backLeft.addBox(0.0F, 0.0F, -3.0F, 5, 15, 3, 0.0F);
        this.setRotateAngle(backLeft, 0.0F, 0.6435028952103092F, 0.0F);
        this.rightVent2 = new ModelRenderer(this, 0, 44);
        this.rightVent2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightVent2.addBox(-1.0F, 1.0F, 0.0F, 1, 2, 2, 0.0F);
        this.armLeft2 = new ModelRenderer(this, 5, 105);
        this.armLeft2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.armLeft2.addBox(-5.0F, 0.0F, -5.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(armLeft2, 0.0F, 1.5707963267948966F, 0.0F);
        this.backSide = new ModelRenderer(this, 42, 0);
        this.backSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.backSide.addBox(-9.0F, 0.0F, 10.0F, 18, 15, 3, 0.0F);
        this.frontCover2 = new ModelRenderer(this, 55, 23);
        this.frontCover2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontCover2.addBox(-6.5F, 19.0F, 9.0F, 14, 0, 2, 0.0F);
        this.jointArmLeft = new ModelRenderer(this, 0, 0);
        this.jointArmLeft.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.jointArmLeft.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(jointArmLeft, 0.0F, 1.5707963267948966F, 0.0F);
        this.leftNozzle = new ModelRenderer(this, 88, 38);
        this.leftNozzle.mirror = true;
        this.leftNozzle.setRotationPoint(4.0F, 15.0F, -13.0F);
        this.leftNozzle.addBox(-1.5F, -1.5F, -0.5F, 3, 3, 2, 0.0F);
        this.setRotateAngle(leftNozzle, 0.3218387140677544F, 0.0F, 0.0F);
        this.upperLegLeft = new ModelRenderer(this, 1, 0);
        this.upperLegLeft.setRotationPoint(8.0F, 0.0F, 0.0F);
        this.upperLegLeft.addBox(-3.0F, -3.0F, -3.0F, 6, 8, 6, 0.0F);
        this.leftStick2 = new ModelRenderer(this, 97, 1);
        this.leftStick2.mirror = true;
        this.leftStick2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftStick2.addBox(-0.5F, -5.5F, -0.5F, 2, 5, 1, 0.0F);
        this.setRotateAngle(leftStick2, 0.0F, 0.9424777960769379F, -1.5707963267948966F);
        this.frontSide = new ModelRenderer(this, 0, 0);
        this.frontSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontSide.addBox(-1.0F, 0.0F, -13.0F, 2, 3, 3, 0.0F);
        this.jawMid = new ModelRenderer(this, 28, 56);
        this.jawMid.setRotationPoint(0.0F, 18.8F, -12.7F);
        this.jawMid.addBox(-3.5F, 0.0F, 0.0F, 7, 5, 3, 0.0F);
        this.setRotateAngle(jawMid, 0.15707963267948966F, 0.0F, 0.0F);
        this.rightVent9 = new ModelRenderer(this, -2, 54);
        this.rightVent9.setRotationPoint(0.0F, 2.6F, -1.7F);
        this.rightVent9.addBox(-6.0F, 0.0F, -2.0F, 4, 0, 2, 0.0F);
        this.frontLeft1 = new ModelRenderer(this, 0, 23);
        this.frontLeft1.mirror = true;
        this.frontLeft1.setRotationPoint(1.0F, 0.0F, -13.0F);
        this.frontLeft1.addBox(0.0F, 0.0F, 0.0F, 13, 3, 3, 0.0F);
        this.setRotateAngle(frontLeft1, 0.0F, -0.39479347680111737F, 0.0F);
        this.rightVent4 = new ModelRenderer(this, 0, 48);
        this.rightVent4.setRotationPoint(-8.0F, 5.0F, 0.0F);
        this.rightVent4.addBox(0.0F, -4.0F, 0.0F, 1, 4, 2, 0.0F);
        this.setRotateAngle(rightVent4, 0.0F, 0.0F, 1.5707963267948966F);
        this.rightSideBottom = new ModelRenderer(this, 0, 15);
        this.rightSideBottom.setRotationPoint(-10.0F, 15.0F, -13.0F);
        this.rightSideBottom.addBox(0.0F, 0.0F, 0.0F, 18, 5, 3, 0.0F);
        this.setRotateAngle(rightSideBottom, 0.6435028952103092F, 0.0F, 0.0F);
        this.leftVent5 = new ModelRenderer(this, 6, 44);
        this.leftVent5.mirror = true;
        this.leftVent5.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.leftVent5.addBox(0.0F, -1.0F, 0.0F, 5, 1, 2, 0.0F);
        this.setRotateAngle(leftVent5, 0.0F, 0.0F, 0.6435028952103092F);
        this.rightSide = new ModelRenderer(this, 0, 0);
        this.rightSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightSide.addBox(-10.0F, 0.0F, -13.0F, 18, 15, 3, 0.0F);
        this.setRotateAngle(rightSide, 0.0F, 1.5707963267948966F, 0.0F);
        this.frontRightBacking = new ModelRenderer(this, 67, 49);
        this.frontRightBacking.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.frontRightBacking.addBox(-13.0F, 3.0F, 2.0F, 13, 13, 1, 0.0F);
        this.lowerLegLeft = new ModelRenderer(this, 0, 6);
        this.lowerLegLeft.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.lowerLegLeft.addBox(-6.5F, 0.0F, -5.0F, 7, 6, 7, 0.0F);
        this.base = new ModelRenderer(this, 30, 25);
        this.base.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.base.addBox(-10.0F, 15.0F, -10.0F, 20, 4, 18, 0.0F);
        this.setRotateAngle(base, 0.0F, 3.141592653589793F, 0.0F);
        this.teethmid = new ModelRenderer(this, 3, 56);
        this.teethmid.setRotationPoint(-0.5F, 0.0F, -11.47F);
        this.teethmid.addBox(0.0F, 12.0F, 0.0F, 1, 7, 1, 0.0F);
        this.rightVent5 = new ModelRenderer(this, 6, 44);
        this.rightVent5.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.rightVent5.addBox(-5.0F, -1.0F, 0.0F, 5, 1, 2, 0.0F);
        this.setRotateAngle(rightVent5, 0.0F, -0.0F, -0.6435028952103092F);
        this.joinArmRight = new ModelRenderer(this, 0, 0);
        this.joinArmRight.setRotationPoint(0.0F, 7.0F, 0.0F);
        this.joinArmRight.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(joinArmRight, 0.0F, 1.5707963267948966F, 0.0F);
        this.rightVent7 = new ModelRenderer(this, -2, 54);
        this.rightVent7.setRotationPoint(0.0F, 0.9F, -0.6F);
        this.rightVent7.addBox(-6.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.armRight1 = new ModelRenderer(this, 0, 104);
        this.armRight1.setRotationPoint(-18.0F, -1.0F, 0.0F);
        this.armRight1.addBox(-6.0F, -6.0F, -6.0F, 12, 9, 12, 0.0F);
        this.setRotateAngle(armRight1, 0.0F, 0.0F, 0.2617993877991494F);
        this.nozzleLeft = new ModelRenderer(this, 36, 106);
        this.nozzleLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.nozzleLeft.addBox(3.0F, 12.0F, 13.0F, 6, 3, 6, 0.0F);
        this.rightVent1 = new ModelRenderer(this, 0, 41);
        this.rightVent1.setRotationPoint(-3.0F, 2.2F, -1.8F);
        this.rightVent1.addBox(-8.0F, 0.0F, 0.0F, 8, 1, 2, 0.0F);
        this.setRotateAngle(rightVent1, 0.1308996938995747F, -0.0F, 0.0F);
        this.frontRight1 = new ModelRenderer(this, 0, 23);
        this.frontRight1.setRotationPoint(-1.0F, 0.0F, -13.0F);
        this.frontRight1.addBox(-13.0F, 0.0F, 0.0F, 13, 3, 3, 0.0F);
        this.setRotateAngle(frontRight1, 0.0F, 0.39479347680111737F, 0.0F);
        this.Mid = new ModelRenderer(this, 0, 90);
        this.Mid.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.Mid.addBox(-3.0F, 4.0F, 13.0F, 6, 9, 5, 0.0F);
        this.rightVent6 = new ModelRenderer(this, -2, 54);
        this.rightVent6.setRotationPoint(-1.0F, 0.6F, 2.0F);
        this.rightVent6.addBox(-6.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.setRotateAngle(rightVent6, 0.5235987755982988F, 0.0F, 0.0F);
        this.upperArmLeft = new ModelRenderer(this, 24, 8);
        this.upperArmLeft.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.upperArmLeft.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
        this.teethLeft = new ModelRenderer(this, 0, 56);
        this.teethLeft.mirror = true;
        this.teethLeft.setRotationPoint(1.0F, 0.0F, 0.0F);
        this.teethLeft.addBox(0.0F, 12.0F, 0.0F, 13, 7, 1, 0.0F);
        this.setRotateAngle(teethLeft, 0.0F, -0.27820548276789614F, 0.0F);
        this.panelRight1 = new ModelRenderer(this, 48, 68);
        this.panelRight1.mirror = true;
        this.panelRight1.setRotationPoint(-11.0F, -3.0F, -4.0F);
        this.panelRight1.addBox(0.0F, -2.0F, 0.0F, 10, 7, 1, 0.0F);
        this.setRotateAngle(panelRight1, 0.27314402793711257F, -1.593485607070823F, 0.0F);
        this.frontRight2 = new ModelRenderer(this, 0, 29);
        this.frontRight2.setRotationPoint(1.0F, 3.05F, -0.8F);
        this.frontRight2.addBox(-14.0F, 0.0F, 0.0F, 14, 10, 2, 0.0F);
        this.setRotateAngle(frontRight2, 0.08028514559173915F, 0.0F, 0.0F);
        this.chokeLeft = new ModelRenderer(this, 41, 107);
        this.chokeLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.chokeLeft.addBox(4.0F, 11.0F, 14.0F, 4, 1, 4, 0.0F);
        this.topLeft = new ModelRenderer(this, 41, 107);
        this.topLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topLeft.addBox(4.0F, 2.0F, 14.0F, 4, 1, 4, 0.0F);
        this.rightVent8 = new ModelRenderer(this, -2, 54);
        this.rightVent8.setRotationPoint(0.0F, 1.8F, -1.1F);
        this.rightVent8.addBox(-6.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.tankLeft = new ModelRenderer(this, 6, 114);
        this.tankLeft.setRotationPoint(0.0F, 0.0F, 1.0F);
        this.tankLeft.addBox(3.0F, 3.0F, 13.0F, 6, 8, 6, 0.0F);
        this.leftStick3 = new ModelRenderer(this, 84, 0);
        this.leftStick3.mirror = true;
        this.leftStick3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftStick3.addBox(-1.0F, -6.0F, -1.0F, 1, 5, 2, 0.0F);
        this.leftVent9 = new ModelRenderer(this, -2, 54);
        this.leftVent9.mirror = true;
        this.leftVent9.setRotationPoint(0.0F, 2.6F, -1.7F);
        this.leftVent9.addBox(2.0F, 0.0F, -2.0F, 4, 0, 2, 0.0F);
        this.rootArmLeft = new ModelRenderer(this, 0, 0);
        this.rootArmLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootArmLeft.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.key = new ModelRenderer(this, 26, 46);
        this.key.setRotationPoint(-2.0F, 0.0F, 3.0F);
        this.key.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
        this.pedelLeft = new ModelRenderer(this, 48, 56);
        this.pedelLeft.mirror = true;
        this.pedelLeft.setRotationPoint(2.0F, 8.0F, 4.0F);
        this.pedelLeft.addBox(-3.0F, 11.0F, -3.0F, 6, 1, 7, 0.0F);
        this.setRotateAngle(pedelLeft, -1.413716694115407F, -0.3141592653589793F, 0.0F);
        this.leftSideBottom = new ModelRenderer(this, 0, 15);
        this.leftSideBottom.mirror = true;
        this.leftSideBottom.setRotationPoint(-8.0F, 15.0F, -13.0F);
        this.leftSideBottom.addBox(0.0F, 0.0F, 0.0F, 18, 5, 3, 0.0F);
        this.setRotateAngle(leftSideBottom, 0.6435028952103092F, 0.0F, 0.0F);
        this.leftVent7 = new ModelRenderer(this, -2, 54);
        this.leftVent7.mirror = true;
        this.leftVent7.setRotationPoint(0.0F, 0.9F, -0.6F);
        this.leftVent7.addBox(0.0F, 0.0F, -2.0F, 6, 0, 2, 0.0F);
        this.lowerArmRight = new ModelRenderer(this, 0, 7);
        this.lowerArmRight.setRotationPoint(2.0F, 0.0F, 0.0F);
        this.lowerArmRight.addBox(-9.0F, 0.0F, -4.0F, 22, 8, 8, 0.0F);
        this.lowerLegRight = new ModelRenderer(this, 0, 6);
        this.lowerLegRight.mirror = true;
        this.lowerLegRight.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.lowerLegRight.addBox(-6.5F, 0.0F, -5.0F, 7, 6, 7, 0.0F);
        this.frontLeft2 = new ModelRenderer(this, 0, 29);
        this.frontLeft2.mirror = true;
        this.frontLeft2.setRotationPoint(-1.0F, 3.05F, -0.8F);
        this.frontLeft2.addBox(0.0F, 0.0F, 0.0F, 14, 10, 2, 0.0F);
        this.setRotateAngle(frontLeft2, 0.08028514559173915F, 0.0F, 0.0F);
        this.jawLeft = new ModelRenderer(this, 33, 48);
        this.jawLeft.mirror = true;
        this.jawLeft.setRotationPoint(1.3F, 0.0F, 0.1F);
        this.jawLeft.addBox(0.0F, 0.0F, 0.0F, 14, 5, 3, 0.0F);
        this.setRotateAngle(jawLeft, 0.0F, -0.39479347680111737F, -0.39479347680111737F);
        this.upperArmRight = new ModelRenderer(this, 24, 8);
        this.upperArmRight.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.upperArmRight.addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F);
        this.panel = new ModelRenderer(this, 13, 41);
        this.panel.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.panel.addBox(-1.5F, -3.5F, -0.5F, 3, 7, 7, 0.0F);
        this.armLeft1 = new ModelRenderer(this, 0, 104);
        this.armLeft1.setRotationPoint(18.0F, -1.0F, 0.0F);
        this.armLeft1.addBox(-6.0F, -6.0F, -6.0F, 12, 9, 12, 0.0F);
        this.setRotateAngle(armLeft1, 0.0F, 0.0F, -0.2617993877991494F);
        this.teethRight = new ModelRenderer(this, 0, 56);
        this.teethRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.teethRight.addBox(-13.0F, 12.0F, 0.0F, 13, 7, 1, 0.0F);
        this.setRotateAngle(teethRight, 0.0F, 0.27820548276789614F, 0.0F);
        this.rightStick4 = new ModelRenderer(this, 84, 0);
        this.rightStick4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightStick4.addBox(-1.0F, 1.0F, -1.0F, 1, 5, 2, 0.0F);
        this.panelLeft1 = new ModelRenderer(this, 48, 68);
        this.panelLeft1.mirror = true;
        this.panelLeft1.setRotationPoint(11.0F, -3.0F, -4.0F);
        this.panelLeft1.addBox(-10.0F, -2.0F, 0.0F, 10, 7, 1, 0.0F);
        this.setRotateAngle(panelLeft1, 0.27314402793711257F, 1.593485607070823F, 0.0F);
        this.rootLegs = new ModelRenderer(this, 0, 0);
        this.rootLegs.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.rootLegs.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.lowerArmLeft = new ModelRenderer(this, 0, 7);
        this.lowerArmLeft.setRotationPoint(2.0F, 0.0F, 0.0F);
        this.lowerArmLeft.addBox(-9.0F, 0.0F, -4.0F, 22, 8, 8, 0.0F);
        this.nozzleRight = new ModelRenderer(this, 36, 106);
        this.nozzleRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.nozzleRight.addBox(-9.0F, 12.0F, 13.0F, 6, 3, 6, 0.0F);
        this.topRight = new ModelRenderer(this, 41, 107);
        this.topRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.topRight.addBox(-8.0F, 2.0F, 14.0F, 4, 1, 4, 0.0F);
        this.pedelRight = new ModelRenderer(this, 48, 56);
        this.pedelRight.setRotationPoint(-2.0F, 8.0F, 4.0F);
        this.pedelRight.addBox(-3.0F, 11.0F, -3.0F, 6, 1, 7, 0.0F);
        this.setRotateAngle(pedelRight, -1.413716694115407F, 0.3141592653589793F, 0.0F);
        this.upperLegRight = new ModelRenderer(this, 1, 0);
        this.upperLegRight.setRotationPoint(-8.0F, 0.0F, 0.0F);
        this.upperLegRight.addBox(-3.0F, -3.0F, -3.0F, 6, 8, 6, 0.0F);
        this.panelRight2 = new ModelRenderer(this, 48, 68);
        this.panelRight2.mirror = true;
        this.panelRight2.setRotationPoint(-11.0F, -3.0F, -4.0F);
        this.panelRight2.addBox(-0.5F, -2.0F, -1.0F, 7, 7, 1, 0.0F);
        this.setRotateAngle(panelRight2, -0.27314402793711257F, 0.5009094953223726F, 0.0F);
        this.leftSide = new ModelRenderer(this, 0, 0);
        this.leftSide.mirror = true;
        this.leftSide.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftSide.addBox(-8.0F, 0.0F, -13.0F, 18, 15, 3, 0.0F);
        this.setRotateAngle(leftSide, 0.0F, -1.5707963267948966F, 0.0F);
        this.panelRoot = new ModelRenderer(this, 63, 114);
        this.panelRoot.setRotationPoint(0.0F, 0.0F, -10.0F);
        this.panelRoot.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.setRotateAngle(panelRoot, 0.0F, 0.9424777960769379F, 1.5707963267948966F);
        this.armRight2 = new ModelRenderer(this, 5, 105);
        this.armRight2.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.armRight2.addBox(-5.0F, 0.0F, -5.0F, 10, 3, 10, 0.0F);
        this.setRotateAngle(armRight2, 0.0F, 1.5707963267948966F, 0.0F);
        this.rootTorso = new ModelRenderer(this, 0, 0);
        this.rootTorso.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rootTorso.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0, 0.0F);
        this.rightStick3 = new ModelRenderer(this, 84, 0);
        this.rightStick3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightStick3.addBox(1.0F, 1.0F, -1.0F, 1, 5, 2, 0.0F);
        this.hips = new ModelRenderer(this, 0, 64);
        this.hips.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.hips.addBox(-5.0F, -4.0F, -5.0F, 10, 8, 10, 0.0F);
        this.kneeRight = new ModelRenderer(this, 21, 6);
        this.kneeRight.setRotationPoint(3.0F, 5.0F, 2.0F);
        this.kneeRight.addBox(-7.0F, -2.5F, -2.5F, 8, 5, 5, 0.0F);
        this.setRotateAngle(kneeRight, 0.7853981633974483F, 0.0F, 0.0F);
        this.frontLeft1.addChild(this.frontLeftBacking);
        this.leftVent1.addChild(this.leftVent6);
        this.tankRight.addChild(this.chokeRight);
        this.rootTorso.addChild(this.rightStick1);
        this.frontLeft2.addChild(this.leftVent1);
        this.leftVent1.addChild(this.leftVent2);
        this.leftVent6.addChild(this.leftVent8);
        this.rootTorso.addChild(this.leftStick1);
        this.jawMid.addChild(this.jawRight);
        this.rightVent1.addChild(this.rightVent3);
        this.leftVent1.addChild(this.leftVent4);
        this.rootLegs.addChild(this.panelLeft2);
        this.upperLegLeft.addChild(this.kneeLeft);
        this.frontSide.addChild(this.frontCover);
        this.backSide.addChild(this.backRight);
        this.Mid.addChild(this.tankRight);
        this.leftVent1.addChild(this.leftVent3);
        this.backSide.addChild(this.backSide_1);
        this.rightSide.addChild(this.rightNozzle);
        this.seatback.addChild(this.seat);
        this.rightStick1.addChild(this.rightStick2);
        this.leftStick2.addChild(this.leftStick4);
        this.backSide.addChild(this.backLeft);
        this.rightVent1.addChild(this.rightVent2);
        this.armLeft1.addChild(this.armLeft2);
        this.rootTorso.addChild(this.backSide);
        this.frontCover.addChild(this.frontCover2);
        this.upperArmLeft.addChild(this.jointArmLeft);
        this.leftSide.addChild(this.leftNozzle);
        this.hips.addChild(this.upperLegLeft);
        this.leftStick1.addChild(this.leftStick2);
        this.rootTorso.addChild(this.frontSide);
        this.frontSide.addChild(this.jawMid);
        this.rightVent6.addChild(this.rightVent9);
        this.frontSide.addChild(this.frontLeft1);
        this.rightVent1.addChild(this.rightVent4);
        this.rightSide.addChild(this.rightSideBottom);
        this.leftVent1.addChild(this.leftVent5);
        this.rootTorso.addChild(this.rightSide);
        this.frontRight1.addChild(this.frontRightBacking);
        this.upperLegLeft.addChild(this.lowerLegLeft);
        this.rootTorso.addChild(this.base);
        this.frontSide.addChild(this.teethmid);
        this.rightVent1.addChild(this.rightVent5);
        this.upperArmRight.addChild(this.joinArmRight);
        this.rightVent6.addChild(this.rightVent7);
        this.rootArmRight.addChild(this.armRight1);
        this.tankLeft.addChild(this.nozzleLeft);
        this.frontRight2.addChild(this.rightVent1);
        this.frontSide.addChild(this.frontRight1);
        this.rootBack.addChild(this.Mid);
        this.rightVent1.addChild(this.rightVent6);
        this.armLeft1.addChild(this.upperArmLeft);
        this.teethmid.addChild(this.teethLeft);
        this.rootLegs.addChild(this.panelRight1);
        this.frontRight1.addChild(this.frontRight2);
        this.tankLeft.addChild(this.chokeLeft);
        this.tankLeft.addChild(this.topLeft);
        this.rightVent6.addChild(this.rightVent8);
        this.Mid.addChild(this.tankLeft);
        this.leftStick2.addChild(this.leftStick3);
        this.leftVent6.addChild(this.leftVent9);
        this.panel.addChild(this.key);
        this.seatback.addChild(this.pedelLeft);
        this.leftSide.addChild(this.leftSideBottom);
        this.leftVent6.addChild(this.leftVent7);
        this.joinArmRight.addChild(this.lowerArmRight);
        this.upperLegRight.addChild(this.lowerLegRight);
        this.frontLeft1.addChild(this.frontLeft2);
        this.jawMid.addChild(this.jawLeft);
        this.armRight1.addChild(this.upperArmRight);
        this.panelRoot.addChild(this.panel);
        this.rootArmLeft.addChild(this.armLeft1);
        this.teethmid.addChild(this.teethRight);
        this.rightStick2.addChild(this.rightStick4);
        this.rootLegs.addChild(this.panelLeft1);
        this.jointArmLeft.addChild(this.lowerArmLeft);
        this.tankRight.addChild(this.nozzleRight);
        this.tankRight.addChild(this.topRight);
        this.seatback.addChild(this.pedelRight);
        this.hips.addChild(this.upperLegRight);
        this.rootLegs.addChild(this.panelRight2);
        this.rootTorso.addChild(this.leftSide);
        this.frontSide.addChild(this.panelRoot);
        this.armRight1.addChild(this.armRight2);
        this.rightStick2.addChild(this.rightStick3);
        this.rootLegs.addChild(this.hips);
        this.upperLegRight.addChild(this.kneeRight);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) { 
        //flip to change "on" state from 0, 54, 90 to 180, -180+54, 90
        //setRotateAngle(panel, 0, 0, (float)Math.toRadians((Math.toDegrees(limbSwing)%180>90?-180:0)));
        float f7 = MathHelper.cos(limbSwing * 0.6662F / .57F) * 1.4F * limbSwingAmount / 6F;

		setRotateAngle(rootTorso, 0F, f7/2, 0F);
		setRotateAngle(rootArmRight, 0F, f7, 0);
		setRotateAngle(rootArmLeft, 0, f7, 0);
		setRotateAngle(rootBack, 0, f7/2, 0);
		setRotateAngle(rootLegs, 0, -f7/2, 0);
		setRotateAngle(armLeft1, -f7, 0F, (float)Math.toRadians(-5));
		setRotateAngle(armRight1, f7, 0F, (float)Math.toRadians(5));
		setRotateAngle(lowerArmLeft, 0, 0, f7);
		setRotateAngle(lowerArmRight, 0, 0, -f7);
		setRotateAngle(upperLegLeft, f7, 0F, 0);
		setRotateAngle(upperLegRight, -f7, 0F, 0);
		setRotateAngle(lowerLegLeft, f7, 0F, 0);
		setRotateAngle(lowerLegRight, -f7, 0F, 0);
        this.seatback.render(scale);
        this.rootArmRight.render(scale);
        this.rootBack.render(scale);
        this.rootArmLeft.render(scale);
        this.rootLegs.render(scale);
        this.rootTorso.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
