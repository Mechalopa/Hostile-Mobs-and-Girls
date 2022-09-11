package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.client.util.ModClientUtils;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrownedGirlModel<T extends Zombie> extends ZombieGirlModel<T>
{
	private ModelPart rightArmPart;
	private ModelPart leftArmPart;

	public DrownedGirlModel(ModelPart modelPart)
	{
		super(modelPart);
		this.rightArmPart = this.rightArm.getChild("right_arm_part");
		this.leftArmPart = this.leftArm.getChild("left_arm_part");
	}

	public static MeshDefinition createMesh(CubeDeformation cd)
	{
		MeshDefinition md = ZombieGirlModel.createMesh(cd);
		PartDefinition pd = md.getRoot();
		PartDefinition rightarmpd = ModClientUtils.addC(pd, cd, "right_arm", 50, 32, -1.0F, -2.0F, -1.5F, 3.0F, 12.0F, 3.0F, -5.0F, 2.0F, 0.0F);
		ModClientUtils.addC(pd, cd, "right_leg", 50, 48, -1.5F, 0.0F, -1.5F, 3.0F, 12.0F, 3.0F, -1.9F, 12.0F, 0.0F);
		PartDefinition leftarmpd = pd.getChild("left_arm");
		ModClientUtils.addC(rightarmpd, cd, "right_arm_part", 24, 56, -1.0F, -2.0F, 0.0F, 1.0F, 4.0F, 3.0F, -1.0F, 2.0F, 1.5F);
		ModClientUtils.addC(leftarmpd, cd, "left_arm_part", 40, 54, 0.0F, -2.0F, 0.0F, 1.0F, 8.0F, 2.0F, 1.0F, 2.0F, 1.5F);
		return md;
	}

	public static LayerDefinition createBodyLayer()
	{
		return LayerDefinition.create(createMesh(CubeDeformation.NONE), 64, 64);
	}

	@Override
	public void prepareMobModel(T entityIn, float limbSwing, float limbSwingAmount, float partialTick)
	{
		prepareDrownedModel(entityIn, this);

		super.prepareMobModel(entityIn, limbSwing, limbSwingAmount, partialTick);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		doAnim(entityIn, ageInTicks, attackTime, this);

		this.rightArmPart.yRot = -((float)Math.PI / 18.0F);
		this.rightArmPart.yRot += Mth.sin(ageInTicks * 0.045F + (float)Math.PI) * 0.06F;
		this.leftArmPart.yRot = (float)Math.PI / 18.0F;
		this.leftArmPart.yRot -= Mth.sin(ageInTicks * 0.045F + (float)Math.PI) * 0.06F;
	}

	public static void prepareDrownedModel(Zombie entityIn, HumanoidModel<?> model)
	{
		model.rightArmPose = HumanoidModel.ArmPose.EMPTY;
		model.leftArmPose = HumanoidModel.ArmPose.EMPTY;
		ItemStack stack = entityIn.getItemInHand(InteractionHand.MAIN_HAND);

		if (ModUtils.isTrident(stack) && entityIn.isAggressive())
		{
			if (entityIn.getMainArm() == HumanoidArm.RIGHT)
			{
				model.rightArmPose = HumanoidModel.ArmPose.THROW_SPEAR;
			}
			else
			{
				model.leftArmPose = HumanoidModel.ArmPose.THROW_SPEAR;
			}
		}
	}

	public static void doAnim(Zombie entityIn, float ageInTicks, float attackTime, AbstractGirlModel<?> model)
	{
		if (model.leftArmPose == HumanoidModel.ArmPose.THROW_SPEAR)
		{
			model.leftArm.xRot = model.leftArm.xRot * 0.5F - (float)Math.PI;
			model.leftArm.yRot = 0.0F;
		}

		if (model.rightArmPose == HumanoidModel.ArmPose.THROW_SPEAR)
		{
			model.rightArm.xRot = model.rightArm.xRot * 0.5F - (float)Math.PI;
			model.rightArm.yRot = 0.0F;
		}

		if (model.swimAmount > 0.0F)
		{
			model.rightArm.xRot = ModUtils.rotlerpRad(model.swimAmount, model.rightArm.xRot, -2.5132742F) + model.swimAmount * 0.35F * Mth.sin(0.1F * ageInTicks);
			model.leftArm.xRot = ModUtils.rotlerpRad(model.swimAmount, model.leftArm.xRot, -2.5132742F) - model.swimAmount * 0.35F * Mth.sin(0.1F * ageInTicks);
			model.rightArm.zRot = ModUtils.rotlerpRad(model.swimAmount, model.rightArm.zRot, -0.15F);
			model.leftArm.zRot = ModUtils.rotlerpRad(model.swimAmount, model.leftArm.zRot, 0.15F);
			model.leftLeg.xRot -= model.swimAmount * 0.55F * Mth.sin(0.1F * ageInTicks);
			model.rightLeg.xRot += model.swimAmount * 0.55F * Mth.sin(0.1F * ageInTicks);
			model.head.xRot = 0.0F;
		}

		model.hat.copyFrom(model.head);
	}
}