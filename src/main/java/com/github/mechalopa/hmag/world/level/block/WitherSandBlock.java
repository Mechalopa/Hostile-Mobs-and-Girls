package com.github.mechalopa.hmag.world.level.block;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoulSandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.IPlantable;

public class WitherSandBlock extends SoulSandBlock
{
	public WitherSandBlock(BlockBehaviour.Properties properties)
	{
		super(properties);
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn)
	{
		if (!level.isClientSide && entityIn != null && entityIn instanceof LivingEntity)
		{
			LivingEntity livingentity = (LivingEntity)entityIn;

			if (!livingentity.isInvulnerableTo(DamageSource.WITHER))
			{
				livingentity.addEffect(new MobEffectInstance(MobEffects.WITHER, 2 * 20, 0));
			}
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level level, BlockPos pos, Random rand)
	{
		super.animateTick(stateIn, level, pos, rand);

		if (rand.nextInt(5) == 0)
		{
			level.addParticle(ParticleTypes.SMOKE, pos.getX() + rand.nextDouble(), pos.getY() + 1.1D, pos.getZ() + rand.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	public boolean canSustainPlant(BlockState state, BlockGetter getter, BlockPos pos, Direction facing, IPlantable plantable)
	{
		BlockState plant = plantable.getPlant(getter, pos.relative(facing));

		if (plant.getBlock() == Blocks.WITHER_ROSE)
		{
			return true;
		}

		return false;
	}
}