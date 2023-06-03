package com.github.mechalopa.hmag.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ThornSandBlock extends SoulSandBlock
{
	public ThornSandBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity)
	{
		if (entity != null && entity instanceof LivingEntity)
		{
			entity.hurt(level.damageSources().cactus(), 1.0F);
		}
	}
}