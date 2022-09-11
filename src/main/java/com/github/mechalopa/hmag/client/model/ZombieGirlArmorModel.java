package com.github.mechalopa.hmag.client.model;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZombieGirlArmorModel<T extends Zombie> extends GirlArmorModel<T>
{
	public ZombieGirlArmorModel(ModelPart modelPart)
	{
		super(modelPart);
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		AnimationUtils.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);
	}

	public boolean isAggressive(T entityIn)
	{
		return entityIn.isAggressive();
	}
}