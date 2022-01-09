package net.mechalopa.hmag.client.model;

import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RedcapModel<T extends MobEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer cap1;
	private ModelRenderer cap2;
	private ModelRenderer cap3;
	private ModelRenderer cap4;
	private ModelRenderer rightHair;
	private ModelRenderer rightHair2;
	private ModelRenderer rightHair3;
	private ModelRenderer rightHair4;
	private ModelRenderer leftHair;
	private ModelRenderer leftHair2;
	private ModelRenderer leftHair3;
	private ModelRenderer leftHair4;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;

	public RedcapModel()
	{
		this(0.0F);
	}

	public RedcapModel(float modelSizeIn)
	{
		this(modelSizeIn, false);
	}

	public RedcapModel(float modelSizeIn, boolean isArmor)
	{
		super(modelSizeIn, 0.0F, 64, isArmor ? 32 : 128, isArmor);

		if (!isArmor)
		{
			this.cap1 = new ModelRenderer(this, 0, 64);
			this.cap1.addBox(-5.0F, -1.0F, -5.0F, 10.0F, 2.0F, 10.0F, modelSizeIn);
			this.cap1.setPos(0.0F, -7.0F, 0.0F);
			this.head.addChild(this.cap1);
			this.cap2 = new ModelRenderer(this, 0, 48);
			this.cap2.addBox(-4.0F, 0.0F, -4.0F, 8.0F, 1.0F, 8.0F, modelSizeIn);
			this.cap2.setPos(0.0F, -2.0F, 0.5F);
			this.cap1.addChild(this.cap2);
			this.cap3 = new ModelRenderer(this, 0, 40);
			this.cap3.addBox(-3.0F, 0.0F, -3.0F, 6.0F, 1.0F, 6.0F, modelSizeIn);
			this.cap3.setPos(0.0F, -1.0F, 0.5F);
			this.cap2.addChild(this.cap3);
			this.cap4 = new ModelRenderer(this, 0, 80);
			this.cap4.addBox(-4.0F, 0.0F, -3.0F, 8.0F, 1.0F, 3.0F, modelSizeIn);
			this.cap4.setPos(0.0F, 0.0F, -5.0F);
			this.cap1.addChild(this.cap4);

			this.rightHair = new ModelRenderer(this, 32, 80);
			this.rightHair.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, modelSizeIn);
			this.rightHair.setPos(-3.5F, -1.0F, 3.0F);
			this.head.addChild(this.rightHair);
			this.leftHair = new ModelRenderer(this, 32, 80);
			this.leftHair.mirror = true;
			this.leftHair.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F, modelSizeIn);
			this.leftHair.setPos(3.5F, -1.0F, 3.0F);
			this.head.addChild(this.leftHair);
			this.rightHair2 = new ModelRenderer(this, 40, 80);
			this.rightHair2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSizeIn);
			this.rightHair2.setPos(0.0F, 2.0F, 0.0F);
			this.rightHair.addChild(this.rightHair2);
			this.leftHair2 = new ModelRenderer(this, 40, 80);
			this.leftHair2.mirror = true;
			this.leftHair2.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 2.0F, 3.0F, modelSizeIn);
			this.leftHair2.setPos(0.0F, 2.0F, 0.0F);
			this.leftHair.addChild(this.leftHair2);
			this.rightHair3 = new ModelRenderer(this, 32, 88);
			this.rightHair3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, modelSizeIn);
			this.rightHair3.setPos(0.0F, 2.0F, 0.0F);
			this.rightHair2.addChild(this.rightHair3);
			this.leftHair3 = new ModelRenderer(this, 32, 88);
			this.leftHair3.mirror = true;
			this.leftHair3.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 1.0F, 4.0F, modelSizeIn);
			this.leftHair3.setPos(0.0F, 2.0F, 0.0F);
			this.leftHair2.addChild(this.leftHair3);
			this.rightHair4 = new ModelRenderer(this, 32, 96);
			this.rightHair4.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, modelSizeIn);
			this.rightHair4.setPos(0.0F, 1.0F, 0.0F);
			this.rightHair3.addChild(this.rightHair4);
			this.leftHair4 = new ModelRenderer(this, 32, 96);
			this.leftHair4.mirror = true;
			this.leftHair4.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 2.0F, 4.0F, modelSizeIn);
			this.leftHair4.setPos(0.0F, 1.0F, 0.0F);
			this.leftHair3.addChild(this.leftHair4);

			this.skirt1 = new ModelRenderer(this, 32, 48);
			this.skirt1.addBox(-3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, modelSizeIn);
			this.skirt1.setPos(0.0F, 11.0F, 0.0F);
			this.body.addChild(this.skirt1);
			this.skirt2 = new ModelRenderer(this, 32, 56);
			this.skirt2.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, modelSizeIn);
			this.skirt2.setPos(0.0F, 12.0F, 0.0F);
			this.body.addChild(this.skirt2);
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (!this.isArmor)
		{
			this.cap1.xRot = -((float)Math.PI * 0.016F);

			this.rightHair.xRot = (float)Math.PI / 7.0F;
			this.leftHair.xRot = (float)Math.PI / 7.0F;
			this.rightHair.zRot = (float)Math.PI / 8.0F;
			this.leftHair.zRot = -((float)Math.PI / 8.0F);
			this.rightHair.zRot -= MathHelper.sin(ageInTicks * 0.09F) * 0.033F;
			this.leftHair.zRot += MathHelper.sin(ageInTicks * 0.09F) * 0.033F;

			if (this.riding)
			{
				this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
			}
			else
			{
				this.skirt2.xRot = this.body.xRot;
			}
		}
	}
}