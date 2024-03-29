package com.github.mechalopa.hmag.client.model;

import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrownedGirlArmorModel<T extends Zombie> extends ZombieGirlArmorModel<T>
{
	public DrownedGirlArmorModel(ModelPart modelPart)
	{
		super(modelPart);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAmount, float partialTick)
	{
		this.rightArmPose = HumanoidModel.ArmPose.EMPTY;
		this.leftArmPose = HumanoidModel.ArmPose.EMPTY;
		ItemStack stack = entity.getItemInHand(InteractionHand.MAIN_HAND);

		if (ModUtils.isTrident(stack) && entity.isAggressive())
		{
			if (entity.getMainArm() == HumanoidArm.RIGHT)
			{
				this.rightArmPose = HumanoidModel.ArmPose.THROW_SPEAR;
			}
			else
			{
				this.leftArmPose = HumanoidModel.ArmPose.THROW_SPEAR;
			}
		}

		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTick);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		DrownedGirlModel.doAnim(entity, ageInTicks, attackTime, this);
	}
}