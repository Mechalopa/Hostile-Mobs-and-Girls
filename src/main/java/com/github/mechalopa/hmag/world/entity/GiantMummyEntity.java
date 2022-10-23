package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.entity.ai.goal.MeleeAttackGoal2;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public class GiantMummyEntity extends Monster
{
	public GiantMummyEntity(EntityType<? extends GiantMummyEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(3, new MeleeAttackGoal2(this, 1.0D, false, 8.0F / 9.0F));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		if (ModConfigs.cachedServer.GIANT_MUMMY_ATTACK_VILLAGERS)
		{
			this.goalSelector.addGoal(4, new MoveThroughVillageGoal(this, 1.0D, true, 4, () -> false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		}
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		if (ModConfigs.cachedServer.GIANT_MUMMY_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.22D)
				.add(Attributes.ATTACK_DAMAGE, 10.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.75D)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	protected PathNavigation createNavigation(Level level)
	{
		return new GiantMummyEntity.GiantMummyNavigation(this, level);
	}

	@Override
	public MobType getMobType()
	{
		return MobType.UNDEAD;
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide && this.tickCount % 2 == 0)
		{
			this.level.addParticle(ParticleTypes.ASH, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.getRandom().nextDouble() - 0.5D) * 3.0D, -this.getRandom().nextDouble(), (this.getRandom().nextDouble() - 0.5D) * 3.0D);
		}

//		ModUtils.burnInDay(this, this.getRandom(), this.isSunBurnTick(), 8);

		super.aiStep();

		if (this.isAlive())
		{
			if (ModConfigs.cachedServer.GIANT_MUMMY_DESTROY_BLOCKS && this.level.getDifficulty().getId() > 1 && this.getTarget() != null && this.horizontalCollision && ForgeEventFactory.getMobGriefingEvent(this.level, this))
			{
				boolean flag = false;
				AABB aabb = this.getBoundingBox().inflate(0.25D);

				for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ)))
				{
					BlockState state = this.level.getBlockState(blockpos);

					if (this.canDestroyBlock(state, this.level, blockpos, this))
					{
						flag = this.level.destroyBlock(blockpos, true, this) || flag;
					}
				}
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entity)
	{
		if (super.doHurtTarget(entity))
		{
			float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();

			if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.getRandom().nextFloat() < f * 0.3F)
			{
				entity.setSecondsOnFire(2 * (int)f);
			}

			if (entity instanceof LivingEntity)
			{
				int i = 0;

				if (this.level.getDifficulty() == Difficulty.NORMAL)
				{
					i = 7;
				}
				else if (this.level.getDifficulty() == Difficulty.HARD)
				{
					i = 15;
				}

				if (i > 0)
				{
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.HUNGER, i * 20, 0));
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i * 20, 0));
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isProjectile())
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 2;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.6D;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size)
	{
		return 2.5F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GIANT_MUMMY_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.GIANT_MUMMY_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GIANT_MUMMY_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.HUSK_STEP, 0.15F, 1.0F);
	}

	private boolean canDestroyBlock(BlockState state, Level level, BlockPos pos, LivingEntity livingEntity)
	{
		return state.is(ModTags.GIANT_MUMMY_DESTROYABLES) && state.canEntityDestroy(this.level, pos, this) && ForgeEventFactory.onEntityDestroyBlock(this, pos, state);
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private static class GiantMummyNavigation extends GroundPathNavigation
	{
		public GiantMummyNavigation(GiantMummyEntity mob, Level level)
		{
			super(mob, level);
		}

		@Override
		protected PathFinder createPathFinder(int maxVisitedNodes)
		{
			this.nodeEvaluator = new GiantMummyEntity.GiantMummyNodeEvaluator();
			return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
		}
	}

	private static class GiantMummyNodeEvaluator extends WalkNodeEvaluator
	{
		@Override
		protected BlockPathTypes evaluateBlockPathType(BlockGetter getter, boolean flag, boolean flag1, BlockPos pos, BlockPathTypes blockPathTypes)
		{
			if ((blockPathTypes == BlockPathTypes.BLOCKED || blockPathTypes == BlockPathTypes.FENCE || blockPathTypes == BlockPathTypes.LEAVES || blockPathTypes == BlockPathTypes.COCOA) && this.mob != null && this.mob.isAlive() && ModConfigs.cachedServer.GIANT_MUMMY_DESTROY_BLOCKS && this.mob.level.getDifficulty().getId() > 1 && this.mob.getTarget() != null && getter.getBlockState(pos).is(ModTags.GIANT_MUMMY_DESTROYABLES))
			{
				return BlockPathTypes.OPEN;
			}
			else
			{
				return super.evaluateBlockPathType(getter, flag, flag1, pos, blockPathTypes);
			}
		}
	}
}