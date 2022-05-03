package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractGirlModel<T extends LivingEntity> extends HumanoidModel<T>
{
	protected ModelPart bodyPart1;
	protected ModelPart bodyPart2;
	protected ModelPart bust;
//	protected final boolean isArmor;
//	protected final boolean hasBust;
//
//	public AbstractGirlModel(float modelSize, float yOffsetIn, boolean isArmor)
//	{
//		this(modelSize, yOffsetIn, isArmor, true);
//	}
//
//	public AbstractGirlModel(float modelSize, float yOffsetIn, boolean isArmor, boolean hasBust)
//	{
//		this(modelSize, yOffsetIn, 64, isArmor ? 32 : 64, isArmor, hasBust);
//	}
//
//	public AbstractGirlModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn, boolean isArmor)
//	{
//		this(modelSize, yOffsetIn, textureWidthIn, textureHeightIn, isArmor, true);
//	}
//
//	public AbstractGirlModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn, boolean isArmor, boolean hasBust)
//	{
//		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
	public AbstractGirlModel(ModelPart modelPart)
	{
		super(modelPart);
//		this.isArmor = false;
//		this.hasBust = true;
		this.bodyPart1 = this.body.getChild("body_part_1");
		this.bodyPart2 = this.bodyPart1.getChild("body_part_2");
		this.bust = this.body.getChild("bust");

//		if (!isArmor)
//		{
//			this.head = new ModelPart(this, 0, 0);
//			this.head.addBox(-3.0F, -6.0F - 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, modelSize + 1.0F);
//			this.head.setPos(0.0F, 0.0F + yOffsetIn, 0.0F);
//
//			if (hasBust)
//			{
//				this.body = new ModelPart(this, 16, 16);
//				this.body.addBox(-3.0F, 0.0F, -1.5F, 6.0F, (float)this.getBodyHeight(), 3.0F, modelSize);
//				this.body.setPos(0.0F, 0.0F, 0.0F);
//				this.bodyPart1 = new ModelPart(this, 32, 32);
//				this.bodyPart1.addBox(-2.5F, 0.0F, -1.0F, 5.0F, 3.0F, 2.0F, modelSize);
//				this.bodyPart1.setPos(0.0F, (float)this.getBodyHeight(), 0.0F);
//				this.body.addChild(this.bodyPart1);
//				this.bodyPart2 = new ModelPart(this, 32, 40);
//				this.bodyPart2.addBox(-3.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, modelSize);
//				this.bodyPart2.setPos(0.0F, 3.0F, 0.0F);
//				this.bodyPart1.addChild(this.bodyPart2);
//
//				this.bust = new ModelPart(this, 0, 32);
//				this.bust.addBox(-3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, modelSize - 0.001F);
//				this.bust.setPos(0.0F, 3.5F, -1.1F);
//				this.body.addChild(this.bust);
//			}
//			else
//			{
//				this.body = new ModelPart(this, 16, 16);
//				this.body.addBox(-3.0F, 0.0F, -1.5F, 6.0F, this.getBodyHeight() + 5.0F, 3.0F, modelSize);
//				this.body.setPos(0.0F, 0.0F + yOffsetIn, 0.0F);
//			}
//
//			this.rightArm = new ModelPart(this, 40, 16);
//			this.rightArm.addBox(-1.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
//			this.rightArm.setPos(-5.0F, 2.0F + yOffsetIn, 0.0F);
//			this.leftArm = new ModelPart(this, 40, 16);
//			this.leftArm.mirror = true;
//			this.leftArm.addBox(-2.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
//			this.leftArm.setPos(5.0F, 2.0F + yOffsetIn, 0.0F);
//			this.rightLeg = new ModelPart(this, 0, 16);
//			this.rightLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
//			this.rightLeg.setPos(-1.9F, 12.0F + yOffsetIn, 0.0F);
//			this.leftLeg = new ModelPart(this, 0, 16);
//			this.leftLeg.mirror = true;
//			this.leftLeg.addBox(-1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, modelSize);
//			this.leftLeg.setPos(1.9F, 12.0F + yOffsetIn, 0.0F);
//		}
	}

	public static MeshDefinition createMesh(CubeDeformation cd, float yOffset)
	{
		return createMesh(cd, yOffset, 7);
	}

	public static MeshDefinition createMesh(CubeDeformation cd, float yOffset, int bodyHeight)
	{
		MeshDefinition md = HumanoidModel.createMesh(cd, yOffset);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, cd, "head", 0, 0, -3.0F, -6.0F - 1.0F, -3.0F, 6.0F, 6.0F, 6.0F, 0.0F, 0.0F + yOffset, 0.0F, 1.0F);
		PartDefinition bodypd = ModClientUtils.addC(pd, cd, "body", 16, 16, -3.0F, 0.0F, -1.5F, 6.0F, (float)bodyHeight, 6.0F, 0.0F, 0.0F + yOffset, 0.0F);
		PartDefinition bodypart1pd = ModClientUtils.addC(bodypd, cd, "body_part_1", 32, 32, -2.5F, 0.0F, -1.0F, 5.0F, 3.0F, 2.0F, 0.0F, (float)bodyHeight, 0.0F);
		ModClientUtils.addC(bodypart1pd, cd, "body_part_2", 32, 40, -3.0F, 0.0F, -1.5F, 6.0F, 2.0F, 3.0F, 0.0F, 3.0F, 0.0F);
		ModClientUtils.addC(bodypd, cd, "bust", 0, 32, -3.0F, -1.5F, -1.5F, 6.0F, 3.0F, 3.0F, 0.0F, 3.5F, -1.1F, -0.001F);
		ModClientUtils.addC(pd, cd, "right_arm", 40, 16, -1.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, -5.0F, 2.0F + yOffset, 0.0F);
		ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, 5.0F, 2.0F + yOffset, 0.0F, true);
		ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, -1.9F, 12.0F + yOffset, 0.0F);
		ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, 1.9F, 12.0F + yOffset, 0.0F, true);
		return md;
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		if (!this.riding)
		{
			this.rightLeg.zRot -= this.getLegRotZ();
			this.leftLeg.zRot += this.getLegRotZ();
		}

//		if (!this.isArmor && this.hasBust)
//		{
			this.bust.xRot = ((float)Math.PI / 4.0F) + ((float)Math.PI / 18.0F);
//		}
	}

	protected float getLegRotZ()
	{
		return (float)Math.PI * 0.008F;
	}
}