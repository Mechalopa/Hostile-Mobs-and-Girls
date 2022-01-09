package net.mechalopa.hmag.entity;

import javax.annotation.Nullable;

import net.minecraft.entity.LivingEntity;

public interface IBeamAttackMob
{
	public int getAttackDuration();

	public boolean attackEntityWithBeamAttack(LivingEntity target, float damage);

	public void setActiveAttackTarget(int entityId);

	public boolean hasActiveAttackTarget();

	@Nullable
	public LivingEntity getActiveAttackTarget();

	public float getAttackAnimationScale(float f);
}