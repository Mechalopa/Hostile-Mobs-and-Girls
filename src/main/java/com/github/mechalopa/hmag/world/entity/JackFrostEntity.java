package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.entity.ai.goal.RangedAttackGoal2;
import com.github.mechalopa.hmag.world.entity.projectile.HardSnowballEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public class JackFrostEntity extends Monster implements RangedAttackMob
{
	public JackFrostEntity(EntityType<? extends JackFrostEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new RangedAttackGoal2(this, 1.1D, 20, 40, 15.0F, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ARMOR, 2.0D);
	}

	@Override
	public void aiStep()
	{
		super.aiStep();

		if (!this.level.isClientSide)
		{
			if (isMeltingBiome(this, this.level))
			{
				this.hurt(DamageSource.ON_FIRE, 1.0F);
			}
			else if (ModConfigs.cachedServer.JACK_FROST_FREEZES_WATER && ForgeEventFactory.getMobGriefingEvent(this.level, this))
			{
				if (this.isOnGround())
				{
					BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
					float f = 2.0F;
					BlockPos blockpos = this.blockPosition();
					BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

					for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset((-f), -1.0D, (-f)), blockpos.offset(f, -1.0D, f)))
					{
						if (blockpos1.closerToCenterThan(this.position(), f))
						{
							blockpos$mutable.set(blockpos1.getX(), blockpos1.getY() + 1, blockpos1.getZ());

							if (this.level.isEmptyBlock(blockpos$mutable))
							{
								BlockState blockstate1 = level.getBlockState(blockpos1);
								boolean isFull = blockstate1.getBlock() == Blocks.WATER && blockstate1.getValue(LiquidBlock.LEVEL) == 0;

								if (blockstate1.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(level, blockpos1) && level.isUnobstructed(blockstate, blockpos1, CollisionContext.empty()) && !ForgeEventFactory.onBlockPlace(this, BlockSnapshot.create(level.dimension(), level, blockpos1), Direction.UP))
								{
									this.level.setBlockAndUpdate(blockpos1, blockstate);
									this.level.scheduleTick(blockpos1, Blocks.FROSTED_ICE, Mth.nextInt(this.getRandom(), 60, 120));
								}
							}
						}
					}
				}
			}
		}
	}

	private static boolean isMeltingBiome(Entity enity, Level level)
	{
		BlockPos blockpos = new BlockPos(Mth.floor(enity.getX()), Mth.floor(enity.getY()), Mth.floor(enity.getZ()));
		Holder<Biome> holder = level.getBiome(blockpos);
		return holder.value().shouldSnowGolemBurn(blockpos) && !holder.containsTag(ModTags.MELTS_JACK_FROSTS_BLACKLIST);
	}

	@Override
	protected void customServerAiStep()
	{
		super.customServerAiStep();

		if (this.isAlive() && ModConfigs.cachedServer.JACK_FROST_REGEN)
		{
			if ((this.isInPowderSnow || this.wasInPowderSnow) && !this.hasEffect(MobEffects.REGENERATION))
			{
				this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 2));
			}
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isFire())
		{
			amount = amount * 2.0F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distance)
	{
		int c = this.getRandom().nextInt(3) + 3;

		for (int i = 0; i < c; ++i)
		{
			HardSnowballEntity snowball = new HardSnowballEntity(this.level, this);
			double d0 = target.getEyeY() - 1.1F;
			double d1 = target.getX() - this.getX();
			double d2 = d0 - snowball.getY();
			double d3 = target.getZ() - this.getZ();
			double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.2D;
			snowball.shoot(d1, d2 + d4, d3, 1.5F, 10.0F);
			snowball.setDamage(3.0F);
			this.level.addFreshEntity(snowball);
		}

		this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 2;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.45D;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size)
	{
		return 1.74F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GIRL_MOB_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.GIRL_MOB_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GIRL_MOB_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}