package com.github.mechalopa.hmag.block;

import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockState;

public class PoisonSandBlock extends SoulSandBlock
{
	public PoisonSandBlock(AbstractBlock.Properties properties)
	{
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn)
	{
		if (!worldIn.isClientSide && entityIn != null && entityIn instanceof LivingEntity)
		{
			LivingEntity livingentity = (LivingEntity)entityIn;

			if (!livingentity.hasEffect(Effects.POISON))
			{
				livingentity.addEffect(new EffectInstance(Effects.POISON, 5 * 20, 0));
			}
		}
	}
}