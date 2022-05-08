package com.github.mechalopa.hmag.world.level.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class TintedReinforcedGlassBlock extends AbstractGlassBlock
{
	public TintedReinforcedGlassBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos)
	{
		return false;
	}

	@Override
	public int getLightBlock(BlockState state, BlockGetter getter, BlockPos pos)
	{
		return getter.getMaxLightLevel();
	}

	@Override
	public boolean canEntityDestroy(BlockState state, BlockGetter getter, BlockPos pos, Entity entity)
	{
		return false;
	}
}