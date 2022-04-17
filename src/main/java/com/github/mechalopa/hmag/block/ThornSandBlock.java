package com.github.mechalopa.hmag.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoulSandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ThornSandBlock extends SoulSandBlock
{
	public ThornSandBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if (entityIn != null && entityIn instanceof LivingEntity)
		{
			entityIn.hurt(DamageSource.CACTUS, 1.0F);
		}
	}
}