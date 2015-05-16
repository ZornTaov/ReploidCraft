package zornco.reploidcraftenv.client.renderers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ModelReploidBlue - Zorn_Taov
 * Created using Tabula 4.0.2
 */
public class ModelReploidBlue extends ModelBiped {
	public ModelRenderer visor;
	public ModelRenderer ridge;
	public ModelRenderer earLeft;
	public ModelRenderer earRight;
	
	public ModelRenderer chest;
	public ModelRenderer chestBand;
	public ModelRenderer chestTab;
	
	public ModelRenderer pauldronLeft;
    public ModelRenderer pauldronTabLeft;
	public ModelRenderer gauntletLeft;
	public ModelRenderer gauntletTabLeft;
	
	public ModelRenderer bootLeft;
	public ModelRenderer bootTabLeft;
	public ModelRenderer footLeft;
	public ModelRenderer footTopLeft;
	
	public ModelRenderer pauldronRight;
    public ModelRenderer pauldronTabRight;
	public ModelRenderer gauntletRight;
	public ModelRenderer gauntletTabRight;
	
	public ModelRenderer bootRight;
	public ModelRenderer bootTabRight;
	public ModelRenderer footRight;
	public ModelRenderer footTopRight;

	public ModelReploidBlue(float expand) {

		super(expand, 0, 64, 64);
		
		this.visor = new ModelRenderer(this, 17, 40);
		this.visor.setRotationPoint(0.0F, -5.0F, -4.5F);
		this.visor.addBox(-5.0F, -1.0F, 0.0F, 10, 2, 2, expand);

		this.ridge = new ModelRenderer(this, 0, 39);
		this.ridge.setRotationPoint(0.0F, -8.0F, 0.0F);
		this.ridge.addBox(-2.0F, -1.0F, -4.5F, 4, 3, 9, expand);
		
		this.earRight = new ModelRenderer(this, 0, 39);
		this.earRight.setRotationPoint(-4.0F, -2.5F, 0.0F);
		this.earRight.addBox(-1.0F, -1.5F, -1.5F, 1, 3, 3, expand);

		this.earLeft = new ModelRenderer(this, 0, 39);
		this.earLeft.mirror = true;
		this.earLeft.setRotationPoint(4.0F, -2.5F, 0.0F);
		this.earLeft.addBox(0.0F, -1.5F, -1.5F, 1, 3, 3, expand);

		this.chest = new ModelRenderer(this, 16, 16);
		this.chest.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.chest.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4, expand*2.5F);
		
		this.chestBand = new ModelRenderer(this, 16, 16);
		this.chestBand.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.chestBand.addBox(-4.0F, 0.0F, -2.0F, 8, 2, 4, expand*5F);
		
		this.chestTab = new ModelRenderer(this, 12, 16);
		this.chestTab.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.chestTab.addBox(-1.0F, 2.0F, -2.0F, 2, 2, 1, expand*5F);

		this.pauldronLeft = new ModelRenderer(this, 21, 32);
		this.pauldronLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pauldronLeft.addBox(-1.5F, -2.5F, -2.5F, 5, 3, 5, expand);

        this.pauldronTabLeft = new ModelRenderer(this, 28, 35);
        this.pauldronTabLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.pauldronTabLeft.addBox(2.5F, -0.5F, -1.0F, 1, 1, 2);
        
		this.gauntletLeft = new ModelRenderer(this, 1, 32);
		this.gauntletLeft.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.gauntletLeft.addBox(-1.5F, -2.5F, -2.5F, 5, 2, 5, expand);
		
		this.gauntletTabLeft = new ModelRenderer(this, 0, 32);
		this.gauntletTabLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.gauntletTabLeft.addBox(2.5F, -3.5F, -1.0F, 1, 1, 2, expand);
		
		this.bootLeft = new ModelRenderer(this, 41, 32);
		this.bootLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bootLeft.addBox(-2.5F, 5.0F, -2.5F, 5, 7, 5, expand);

		this.bootTabLeft = new ModelRenderer(this, 16, 34);
		this.bootTabLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bootTabLeft.addBox(-1.0F, 4.0F, -2.5F, 2, 1, 1, expand);

		this.footLeft = new ModelRenderer(this, 17, 44);
		this.footLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.footLeft.addBox(-2.0F, 10.0F, -4.5F, 4, 2, 2, expand);

		this.footTopLeft = new ModelRenderer(this, 16, 32);
		this.footTopLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.footTopLeft.addBox(-2.0F, 9.0F, -3.5F, 4, 1, 1, expand);

		this.pauldronRight = new ModelRenderer(this, 21, 32);
		this.pauldronRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.pauldronRight.addBox(-3.5F, -2.5F, -2.5F, 5, 3, 5, expand);

        this.pauldronTabRight = new ModelRenderer(this, 28, 35);
        this.pauldronTabRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.pauldronTabRight.addBox(-3.5F, -0.5F, -1.0F, 1, 1, 2);
		
		this.gauntletRight = new ModelRenderer(this, 1, 32);
		this.gauntletRight.setRotationPoint(0.0F, 8.0F, 0.0F);
		this.gauntletRight.addBox(-3.5F, -2.5F, -2.5F, 5, 2, 5, expand);
		
		this.gauntletTabRight = new ModelRenderer(this, 0, 32);
		this.gauntletTabRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.gauntletTabRight.addBox(-3.5F, -3.5F, -1.0F, 1, 1, 2, expand);
		
		this.bootRight = new ModelRenderer(this, 41, 32);
		this.bootRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bootRight.addBox(-2.5F, 5.0F, -2.5F, 5, 7, 5, expand);

		this.bootTabRight = new ModelRenderer(this, 16, 34);
		this.bootTabRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bootTabRight.addBox(-1.0F, 4.0F, -2.5F, 2, 1, 1, expand);

		this.footRight = new ModelRenderer(this, 17, 44);
		this.footRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.footRight.addBox(-2.0F, 10.0F, -4.5F, 4, 2, 2, expand);

		this.footTopRight = new ModelRenderer(this, 16, 32);
		this.footTopRight.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.footTopRight.addBox(-2.0F, 9.0F, -3.5F, 4, 1, 1, expand);
		
		this.bipedHead.addChild(this.earRight);
		this.bipedHead.addChild(this.earLeft);
		this.bipedHead.addChild(this.visor);
		this.bipedHead.addChild(this.ridge);
		
		this.chest.addChild(this.chestTab);
		this.chest.addChild(this.chestBand);
		this.bipedBody.addChild(this.chest);

		this.gauntletLeft.addChild(this.gauntletTabLeft);
		this.bipedLeftArm.addChild(this.gauntletLeft);
		this.pauldronLeft.addChild(this.pauldronTabLeft);
		this.bipedLeftArm.addChild(this.pauldronLeft);
		
		this.bootLeft.addChild(this.footTopLeft);
		this.bootLeft.addChild(this.footLeft);
		this.bootLeft.addChild(this.bootTabLeft);
		this.bipedLeftLeg.addChild(this.bootLeft);
		
		this.gauntletRight.addChild(this.gauntletTabRight);
		this.bipedRightArm.addChild(this.gauntletRight);
		this.pauldronRight.addChild(this.pauldronTabRight);
		this.bipedRightArm.addChild(this.pauldronRight);
		
		this.bootRight.addChild(this.footTopRight);
		this.bootRight.addChild(this.footRight);
		this.bootRight.addChild(this.bootTabRight);
		this.bipedRightLeg.addChild(this.bootRight);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
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
