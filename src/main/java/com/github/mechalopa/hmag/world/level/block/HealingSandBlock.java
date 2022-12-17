package com.github.mechalopa.hmag.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class HealingSandBlock extends SoulSandBlock
{
	public HealingSandBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		if (!level.isClientSide && entity != null && entity instanceof LivingEntity)
		{
			LivingEntity livingentity = (LivingEntity)entity;

			if (!livingentity.hasEffect(MobEffects.REGENERATION))
			{
				livingentity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 0));
			}
		}
	}
}