package com.github.mechalopa.hmag.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractGlassBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class ReinforcedGlassBlock extends AbstractGlassBlock
{
	public ReinforcedGlassBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}

	@Override
	public boolean canEntityDestroy(BlockState state, IBlockReader world, BlockPos pos, Entity entity)
	{
		return false;
	}
}