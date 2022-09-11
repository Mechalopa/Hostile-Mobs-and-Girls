package com.github.mechalopa.hmag.world.entity.projectile;

import java.util.List;

import com.github.mechalopa.hmag.registry.ModEntityTypes;

import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PoisonSeedEntity extends ModProjectileItemEntity
{
	public PoisonSeedEntity(EntityType<? extends PoisonSeedEntity> type, Level worldIn)
	{
		super(type, worldIn);
	}

	public PoisonSeedEntity(Level worldIn, LivingEntity throwerIn)
	{
		super(ModEntityTypes.POISON_SEED.get(), throwerIn, worldIn);
	}

	public PoisonSeedEntity(Level worldIn, double x, double y, double z)
	{
		super(ModEntityTypes.POISON_SEED.get(), x, y, z, worldIn);
	}

	@Override
	protected Item getDefaultItem()
	{
		return Items.BEETROOT_SEEDS;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 3)
		{
			for (int i = 0; i < 8; ++i)
			{
				this.level.addParticle(new ItemParticleOption(ParticleTypes.ITEM, this.getItem()), this.getX(), this.getY(), this.getZ(), (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D, (this.random.nextFloat() - 0.5D) * 0.08D);
			}
		}
	}

	@Override
	protected void onHitServer(HitResult result)
	{
		AABB aabb = this.getBoundingBox().inflate(4.0D, 2.0D, 4.0D);
		List<LivingEntity> list = this.level.getEntitiesOfClass(LivingEntity.class, aabb);

		if (!list.isEmpty())
		{
			for (LivingEntity livingEntity : list)
			{
				if (livingEntity.isAffectedByPotions())
				{
					double d0 = this.distanceToSqr(livingEntity);

					if (d0 < 16.0D)
					{
						double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

						if (result.getType() == HitResult.Type.ENTITY && ((EntityHitResult)result).getEntity() == livingEntity)
						{
							d1 = 1.0D;
						}

						int i = 0;

						if (this.level.getDifficulty() == Difficulty.NORMAL)
						{
							i = 7 * 20;
						}
						else if (this.level.getDifficulty() == Difficulty.HARD)
						{
							i = 15 * 20;
						}

						i = (int)(d1 * i + 0.5D);

						if (i > 20)
						{
							livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, i, 0));
							livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, i, 0));
							livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i, 0));
						}
					}
				}
			}
		}

		this.level.levelEvent(2002, this.blockPosition(), MobEffects.POISON.getColor());
		this.level.broadcastEntityEvent(this, (byte)3);
		super.onHitServer(result);
	}

	@Override
	protected void onHitEntity(EntityHitResult result)
	{
		super.onHitEntity(result);
		result.getEntity().hurt(DamageSource.thrown(this, this.getOwner()), this.getDamage());
	}
}