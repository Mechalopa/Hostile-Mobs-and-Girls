package net.mechalopa.hmag.client.model;

import java.util.Random;

import net.mechalopa.hmag.entity.GhastlySeekerEntity;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GhastlySeekerModel<T extends GhastlySeekerEntity> extends AbstractGirlModel<T>
{
	private ModelRenderer rightArmPart1;
	private ModelRenderer rightArmPart2;
	private ModelRenderer leftArmPart1;
	private ModelRenderer leftArmPart2;
	private ModelRenderer skirt1;
	private ModelRenderer skirt2;
	private ModelRenderer skirt3;
	private ModelRenderer skirt4;
	private ModelRenderer[] tentacles = new ModelRenderer[7];

	private ModelRenderer rightHairPart1;
	private ModelRenderer rightHairPart2;
	private ModelRenderer rightHairPart3;
	private ModelRenderer rightHairPart4;
	private ModelRenderer rightHairPart5;
	private ModelRenderer rightHairPart6;
	private ModelRenderer leftHairPart1;
	private ModelRenderer leftHairPart2;
	private ModelRenderer leftHairPart3;
	private ModelRenderer leftHairPart4;
	private ModelRenderer leftHairPart5;
	private ModelRenderer leftHairPart6;

	private ModelRenderer hairPart;
	private ModelRenderer headwearPart;

	public GhastlySeekerModel()
	{
		this(0.0F);
	}

	public GhastlySeekerModel(float modelSize)
	{
		super(modelSize, 0.0F, 64, 128, false);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(-1.0F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, modelSize);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 3.0F, 5.0F, 3.0F, modelSize);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);

		this.rightArmPart1 = new ModelRenderer(this, 40, 48);
		this.rightArmPart1.addBox(-1.5F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize);
		this.rightArmPart1.setPos(0.0F, 2.5F, 0.0F);
		this.rightArm.addChild(this.rightArmPart1);
		this.rightArmPart2 = new ModelRenderer(this, 40, 56);
		this.rightArmPart2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, modelSize);
		this.rightArmPart2.setPos(0.0F, 3.5F, 0.0F);
		this.rightArmPart1.addChild(this.rightArmPart2);

		this.leftArmPart1 = new ModelRenderer(this, 40, 48);
		this.leftArmPart1.mirror = true;
		this.leftArmPart1.addBox(-2.5F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, modelSize);
		this.leftArmPart1.setPos(0.0F, 2.5F, 0.0F);
		this.leftArm.addChild(this.leftArmPart1);
		this.leftArmPart2 = new ModelRenderer(this, 40, 56);
		this.leftArmPart2.mirror = true;
		this.leftArmPart2.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 4.0F, 5.0F, modelSize);
		this.leftArmPart2.setPos(0.0F, 3.5F, 0.0F);
		this.leftArmPart1.addChild(this.leftArmPart2);

		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, modelSize);
		this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, modelSize);
		this.leftLeg.setPos(2.0F, 12.0F, 0.0F);

		this.bodyPart2 = new ModelRenderer(this, 32, 40);
		this.bodyPart2.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, modelSize);
		this.bodyPart2.setPos(0.0F, 2.5F, 0.0F);
		this.bodyPart1.addChild(this.bodyPart2);

		this.skirt1 = new ModelRenderer(this, 0, 38);
		this.skirt1.addBox(-4.0F, 0.0F, -2.5F, 8.0F, 2.0F, 5.0F, modelSize);
		this.skirt1.setPos(0.0F, 10.0F, 0.0F);
		this.body.addChild(this.skirt1);
		this.skirt2 = new ModelRenderer(this, 0, 46);
		this.skirt2.addBox(-4.5F, 0.0F, -3.0F, 9.0F, 2.0F, 7.0F, modelSize);
		this.skirt2.setPos(0.0F, 11.0F, 0.0F);
		this.body.addChild(this.skirt2);
		this.skirt3 = new ModelRenderer(this, 0, 56);
		this.skirt3.addBox(-5.0F, 0.0F, -3.5F, 10.0F, 2.0F, 9.0F, modelSize);
		this.skirt3.setPos(0.0F, 1.0F, 0.0F);
		this.skirt2.addChild(this.skirt3);
		this.skirt4 = new ModelRenderer(this, 0, 72);
		this.skirt4.addBox(-5.5F, 0.0F, -4.0F, 11.0F, 3.0F, 11.0F, modelSize);
		this.skirt4.setPos(0.0F, 1.0F, 0.0F);
		this.skirt3.addChild(this.skirt4);

		Random random = new Random(1660L);

		for (int i = 0; i < this.tentacles.length; ++i)
		{
			this.tentacles[i] = new ModelRenderer(this, 0, 88);
			float f = (((float)(i % 3) - (float)(i / 3 % 2) * 0.5F + 0.25F) / 2.0F * 2.0F - 1.0F) * 3.0F;
			float f1 = ((float)(i / 3) / 2.0F * 2.0F - 1.0F) * 3.0F;
			int j = random.nextInt(3) + 8;
			this.tentacles[i].addBox(-1.5F, 0.0F, -1.5F, 3.0F, j, 3.0F, modelSize);
			this.tentacles[i].x = f;
			this.tentacles[i].z = f1 + 1.0F;
			this.tentacles[i].y = 1.75F;
			this.skirt4.addChild(this.tentacles[i]);
		}

		this.rightHairPart1 = new ModelRenderer(this, 16, 88);
		this.rightHairPart1.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.rightHairPart1.setPos(-3.75F, -7.0F, -0.5F);
		this.head.addChild(this.rightHairPart1);
		this.leftHairPart1 = new ModelRenderer(this, 16, 88);
		this.leftHairPart1.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 2.0F, modelSize);
		this.leftHairPart1.setPos(3.75F, -7.0F, -0.5F);
		this.head.addChild(this.leftHairPart1);

		this.rightHairPart2 = new ModelRenderer(this, 24, 88);
		this.rightHairPart2.addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, modelSize);
		this.rightHairPart2.setPos(-1.5F, 0.0F, -0.25F);
		this.rightHairPart1.addChild(this.rightHairPart2);
		this.leftHairPart2 = new ModelRenderer(this, 24, 88);
		this.leftHairPart2.addBox(-0.5F, -2.0F, -2.0F, 1.0F, 4.0F, 4.0F, modelSize);
		this.leftHairPart2.setPos(1.5F, 0.0F, -0.25F);
		this.leftHairPart1.addChild(this.leftHairPart2);

		this.rightHairPart3 = new ModelRenderer(this, 48, 72);
		this.rightHairPart3.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightHairPart3.setPos(-1.5F, 1.0F, 1.5F);
		this.rightHairPart1.addChild(this.rightHairPart3);
		this.rightHairPart4 = new ModelRenderer(this, 40, 88);
		this.rightHairPart4.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, modelSize + 0.25F);
		this.rightHairPart4.setPos(0.0F, 3.5F, 0.0F);
		this.rightHairPart3.addChild(this.rightHairPart4);
		this.rightHairPart5 = new ModelRenderer(this, 48, 88);
		this.rightHairPart5.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, modelSize);
		this.rightHairPart5.setPos(0.0F, 8.0F, 0.0F);
		this.rightHairPart4.addChild(this.rightHairPart5);
		this.rightHairPart6 = new ModelRenderer(this, 48, 80);
		this.rightHairPart6.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.rightHairPart6.setPos(0.0F, 6.0F, 0.0F);
		this.rightHairPart5.addChild(this.rightHairPart6);

		this.leftHairPart3 = new ModelRenderer(this, 48, 72);
		this.leftHairPart3.mirror = true;
		this.leftHairPart3.addBox(-1.0F, -1.5F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftHairPart3.setPos(1.5F, 1.0F, 1.5F);
		this.leftHairPart1.addChild(this.leftHairPart3);
		this.leftHairPart4 = new ModelRenderer(this, 40, 88);
		this.leftHairPart4.mirror = true;
		this.leftHairPart4.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 8.0F, 2.0F, modelSize + 0.25F);
		this.leftHairPart4.setPos(0.0F, 3.5F, 0.0F);
		this.leftHairPart3.addChild(this.leftHairPart4);
		this.leftHairPart5 = new ModelRenderer(this, 48, 88);
		this.leftHairPart5.mirror = true;
		this.leftHairPart5.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 6.0F, 3.0F, modelSize);
		this.leftHairPart5.setPos(0.0F, 8.0F, 0.0F);
		this.leftHairPart4.addChild(this.leftHairPart5);
		this.leftHairPart6 = new ModelRenderer(this, 48, 80);
		this.leftHairPart6.mirror = true;
		this.leftHairPart6.addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F, modelSize);
		this.leftHairPart6.setPos(0.0F, 6.0F, 0.0F);
		this.leftHairPart5.addChild(this.leftHairPart6);

		this.hairPart = new ModelRenderer(this, 0, 104);
		this.hairPart.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 2.0F, 1.0F, modelSize);
		this.hairPart.setPos(0.0F, 0.0F, 4.0F);
		this.head.addChild(this.hairPart);

		this.headwearPart = new ModelRenderer(this, 32, 104);
		this.headwearPart.addBox(-4.0F, -2.0F, -4.0F, 8.0F, 2.0F, 8.0F, modelSize + 0.5F);
		this.headwearPart.setPos(0.0F, 3.0F, 0.0F);
		this.hat.addChild(this.headwearPart);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		float f = MathHelper.sin(this.attackTime * (float)Math.PI);
		float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);

		if (entityIn.isAttacking())
		{
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.3F - f * 0.6F);
			this.leftArm.yRot = 0.3F - f * 0.6F;
			this.rightArm.xRot = -((float)Math.PI / 2F);
			this.leftArm.xRot = -((float)Math.PI / 2F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}
		else
		{
			this.rightArm.zRot = ((float)Math.PI / 12.0F);
			this.leftArm.zRot = -((float)Math.PI / 12.0F);
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = ((float)Math.PI / 15F);
			this.leftArm.xRot = ((float)Math.PI / 15F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.04F;
			this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.04F;
			this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.04F;
			this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.04F;
		}

		float f2 = MathHelper.sin(ageInTicks * 0.09F + (float)Math.PI / 2.0F);

		this.rightArmPart1.xRot = ((float)Math.PI / 24.0F);
		this.leftArmPart1.xRot = ((float)Math.PI / 24.0F);
		this.rightArmPart1.xRot += f2 * 0.067F;
		this.leftArmPart1.xRot += f2 * 0.067F;

		this.rightArmPart2.zRot = ((float)Math.PI / 30.0F);
		this.leftArmPart2.zRot = -((float)Math.PI / 30.0F);

		this.skirt2.xRot = ((float)Math.PI / 24.0F);
		this.skirt2.xRot -= f * 1.2F - f1 * 0.4F;
		this.skirt2.xRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 2.0F) * 0.04F;
		this.skirt3.xRot = ((float)Math.PI / 30.0F);
		this.skirt3.xRot += f2 * 0.03F;
		this.skirt4.xRot = ((float)Math.PI / 30.0F);
		this.skirt4.xRot += f2 * 0.03F;

		for (int i = 0; i < this.tentacles.length; ++i)
		{
			this.tentacles[i].xRot = 0.2F * MathHelper.sin(ageInTicks * 0.15F + (float)i) + 0.4F;
		}

		this.rightHairPart1.zRot = ((float)Math.PI / 27.0F);
		this.leftHairPart1.zRot = -((float)Math.PI / 27.0F);
		this.rightHairPart1.zRot += MathHelper.sin(ageInTicks * 0.067F) * 0.067F;
		this.leftHairPart1.zRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.067F;
		this.rightHairPart2.yRot = -((float)Math.PI / 5.0F);
		this.leftHairPart2.yRot = ((float)Math.PI / 5.0F);
		this.rightHairPart2.zRot = ((float)Math.PI / 24.0F);
		this.leftHairPart2.zRot = -((float)Math.PI / 24.0F);
		this.rightHairPart3.xRot = ((float)Math.PI / 12.0F);
		this.leftHairPart3.xRot = ((float)Math.PI / 12.0F);
		this.rightHairPart3.xRot += f2 * 0.045F;
		this.leftHairPart3.xRot += f2 * 0.045F;
		this.rightHairPart3.zRot = ((float)Math.PI / 15.0F);
		this.leftHairPart3.zRot = -((float)Math.PI / 15.0F);
		this.rightHairPart4.zRot = ((float)Math.PI / 30.0F);
		this.leftHairPart4.zRot = -((float)Math.PI / 30.0F);
		this.rightHairPart4.zRot -= MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.leftHairPart4.zRot += MathHelper.sin(ageInTicks * 0.067F + (float)Math.PI / 3.0F) * 0.045F;
		this.rightHairPart5.zRot = -((float)Math.PI / 27.0F);
		this.leftHairPart5.zRot = ((float)Math.PI / 27.0F);

		this.hairPart.xRot = ((float)Math.PI / 12.0F);
		this.hairPart.xRot += MathHelper.sin(ageInTicks * 0.075F) * 0.045F;
	}
}