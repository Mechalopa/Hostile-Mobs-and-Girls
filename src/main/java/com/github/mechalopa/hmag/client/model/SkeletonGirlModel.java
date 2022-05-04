package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SkeletonGirlModel<T extends AbstractSkeleton> extends AbstractAdvancedGirlModel<T>
{
	private ModelPart skirt1;
	private ModelPart skirt2;
	private ModelPart rightHair1;
	private ModelPart leftHair1;
	private ModelPart rightHair2;
	private ModelPart leftHair2;

	public SkeletonGirlModel(ModelPart modelPart)
	{
		super(modelPart);
		this.skirt1 = this.body.getChild("skirt_1");
		this.skirt2 = this.skirt1.getChild("skirt_2");
		this.rightHair1 = this.head.getChild("right_hair_1");
		this.leftHair1 = this.head.getChild("left_hair_1");
		this.rightHair2 = this.rightHair1.getChild("right_hair_2");
		this.leftHair2 = this.leftHair1.getChild("left_hair_2");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = AbstractAdvancedGirlModel.createMesh(cd, 0.0F);
		PartDefinition pd = md.getRoot();
		ModClientUtils.addC(pd, cd, "right_arm", 40, 16, 0.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, -5.0F, 2.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "left_arm", 40, 16, -2.0F, -2.0F, -1.0F, 2.0F, 12.0F, 2.0F, 5.0F, 2.0F, 0.0F, true);
		ModClientUtils.addC(pd, cd, "right_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, -1.75F, 12.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "left_leg", 0, 16, -1.0F, 0.0F, -1.0F, 2.0F, 12.0F, 2.0F, 1.75F, 12.0F, 0.0F, true);
		PartDefinition bodypd = pd.getChild("body");
		PartDefinition skirt1pd = ModClientUtils.addC(bodypd, cd, "skirt_1", 0, 38, -3.5F, 0.0F, -2.0F, 7.0F, 1.0F, 4.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(skirt1pd, cd, "skirt_2", 0, 44, -4.0F, 0.0F, -2.5F, 8.0F, 6.0F, 5.0F, 0.0F, 12.0F, 0.0F);
		PartDefinition headpd = pd.getChild("head");
		PartDefinition rhpd = ModClientUtils.addC(headpd, cd, "right_hair_1", 32, 32, -0.5F, -1.0F, 0.0F, 1.0F, 12.0F, 1.0F, -4.0F, -8.0F, 2.75F, 0.25F);
		PartDefinition lhpd = ModClientUtils.addC(headpd, cd, "left_hair_1", 32, 32, -0.5F, -1.0F, 0.0F, 1.0F, 12.0F, 1.0F, 4.0F, -8.0F, 2.75F, true, 0.25F);
		ModClientUtils.addC(rhpd, cd, "right_hair_2", 36, 32, -0.5F, -0.5F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, 11.0F, 0.0F);
		ModClientUtils.addC(lhpd, cd, "left_hair_2", 36, 32, -0.5F, -0.5F, 0.0F, 1.0F, 10.0F, 1.0F, 0.0F, 11.0F, 0.0F, true);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 64);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		prepareSkeletonModel(entityIn, this);

		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
	}

	public static void prepareSkeletonModel(AbstractSkeleton entityIn, HumanoidModel<?> model)
	{
		model.rightArmPose = HumanoidModel.ArmPose.EMPTY;
		model.leftArmPose = HumanoidModel.ArmPose.EMPTY;

		ItemStack stack = entityIn.getItemInHand(InteractionHand.MAIN_HAND);

		if (ModUtils.isBow(stack) && entityIn.isAggressive())
		{
			if (entityIn.getMainArm() == HumanoidArm.RIGHT)
			{
				model.rightArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
			}
			else
			{
				model.leftArmPose = HumanoidModel.ArmPose.BOW_AND_ARROW;
			}
		}
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		doAnim(entityIn, ageInTicks, this.attackTime, this.rightArm, this.leftArm);

		if (this.riding)
		{
			this.skirt2.xRot = -((float)Math.PI * 2.0F / 5.0F);
		}
		else
		{
			this.skirt2.xRot = this.body.xRot;
		}

		this.rightHair1.xRot = ((float)Math.PI / 18.0F);
		this.leftHair1.xRot = ((float)Math.PI / 18.0F);
		this.rightHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.036F;
		this.leftHair1.xRot += Mth.sin(ageInTicks * 0.06F) * 0.036F;
		this.rightHair1.zRot = ((float)Math.PI / 9.0F);
		this.leftHair1.zRot = -((float)Math.PI / 9.0F);
		this.rightHair1.zRot -= Mth.sin(ageInTicks * 0.09F) * 0.03F;
		this.leftHair1.zRot += Mth.sin(ageInTicks * 0.09F) * 0.03F;
		this.rightHair2.zRot = -((float)Math.PI / 15.0F);
		this.leftHair2.zRot = ((float)Math.PI / 15.0F);
		this.rightHair2.zRot -= Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
		this.leftHair2.zRot += Mth.sin(ageInTicks * 0.06F + (float)Math.PI / 3.0F) * 0.03F;
	}

	public static void doAnim(AbstractSkeleton entityIn, float ageInTicks, float attackTime, ModelPart rightArm, ModelPart leftArm)
	{
		ItemStack stack = entityIn.getMainHandItem();

		if (entityIn.isAggressive() && (stack.isEmpty() || !ModUtils.isBow(stack)))
		{
			float f = Mth.sin(attackTime * (float)Math.PI);
			float f1 = Mth.sin((1.0F - (1.0F - attackTime) * (1.0F - attackTime)) * (float)Math.PI);
			rightArm.zRot = 0.0F;
			leftArm.zRot = 0.0F;
			rightArm.yRot = -(0.1F - f * 0.6F);
			leftArm.yRot = 0.1F - f * 0.6F;
			rightArm.xRot = (-(float)Math.PI / 2.0F);
			leftArm.xRot = (-(float)Math.PI / 2.0F);
			rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			AnimationUtils.bobArms(rightArm, leftArm, ageInTicks);
		}
	}

	@Override
	protected boolean isSkeletonHandTranslate()
	{
		return true;
	}
}