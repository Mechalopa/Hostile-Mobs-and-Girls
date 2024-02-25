package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.network.NetworkHooks;

public class MeltyMonsterEntity extends Monster implements RangedAttackMob
{
	public MeltyMonsterEntity(EntityType<? extends MeltyMonsterEntity> type, Level level)
	{
		super(type, level);
		this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
		this.setPathfindingMalus(BlockPathTypes.LAVA, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, LivingEntity.class, (p) -> {
			return p.getType().is(ModTags.EntityTypeTags.MELTY_MONSTER_AVOIDS);
		}, 10.0F, 1.0D, 1.5D, EntitySelector.NO_SPECTATORS::test));
		this.goalSelector.addGoal(3, new MeltyMonsterEntity.GoToLavaGoal(this, 1.5D));
		this.goalSelector.addGoal(5, new RangedAttackGoal(this, 1.0D, 30, 40, 8.0F));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, (p) -> {
			return !(p.getVehicle() != null && p.getVehicle().getType().is(ModTags.EntityTypeTags.MELTY_MONSTER_AVOIDS));
		}));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 25.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.18D);
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide)
		{
			if (!this.isInWaterOrRain())
			{
				if (this.tickCount % 5 == 0)
				{
					this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY() - 0.5D, this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
				}

				if (this.tickCount % 10 == 1)
				{
					this.level.addParticle(ParticleTypes.LAVA, this.getRandomX(0.5D), this.getRandomY() - 0.5D, this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
				}
			}
		}

		super.aiStep();

		if (!this.level.isClientSide)
		{
			if (ModConfigs.cachedServer.MELTY_MONSTER_SET_FIRE && !this.isInLava() && !this.isInWaterRainOrBubble() && !this.isFreezing() && ForgeEventFactory.getMobGriefingEvent(this.level, this))
			{
				int i = Mth.floor(this.getX());
				int j = Mth.floor(this.getY());
				int k = Mth.floor(this.getZ());

				for (int l = 0; l < 4; ++l)
				{
					i = Mth.floor(this.getX() + (l % 2 * 2 - 1) * 0.25F);
					j = Mth.floor(this.getY());
					k = Mth.floor(this.getZ() + (l / 2 % 2 * 2 - 1) * 0.25F);
					BlockPos blockpos = new BlockPos(i, j, k);

					if (this.level.isEmptyBlock(blockpos))
					{
						this.level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level, blockpos));
		            }
				}
			}
		}
	}

	@Override
	public boolean isSensitiveToWater()
	{
		return true;
	}

	@Override
	public boolean isOnFire()
	{
		return false;
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor)
	{
		double d1 = target.getX() - this.getX();
		double d2 = target.getY() + target.getEyeHeight() * 0.5D - this.getY(0.5D);
		double d3 = target.getZ() - this.getZ();
		double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.02D;
		SmallFireball fireballentity = new SmallFireball(this.level, this, d1 + this.getRandom().nextGaussian() * d4, d2, d3 + this.getRandom().nextGaussian() * d4);
		fireballentity.setPos(fireballentity.getX(), this.getY(0.5D) + 0.5D, fireballentity.getZ());
		this.level.addFreshEntity(fireballentity);
		this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	public static boolean checkMeltyMonsterSpawnRules(EntityType<MeltyMonsterEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random)
	{
		BlockPos.MutableBlockPos blockpos$mutable = pos.mutable();

		do
		{
			blockpos$mutable.move(Direction.UP);
		}
		while(levelAccessor.getFluidState(blockpos$mutable).is(FluidTags.LAVA));

		return levelAccessor.getBlockState(blockpos$mutable).isAir() && (spawnType == MobSpawnType.SPAWNER || pos.getY() < (levelAccessor.getMinBuildHeight() < 0  ? 0 : 32) || random.nextBoolean());
	}

	@Override
	public boolean checkSpawnObstruction(LevelReader levelReader)
	{
		return levelReader.isUnobstructed(this);
	}

	@Override
	public boolean canStandOnFluid(FluidState state)
	{
		return state.is(FluidTags.LAVA);
	}

	@Override
	protected void checkFallDamage(double d0, boolean onGround, BlockState blockstate, BlockPos pos)
	{
		this.checkInsideBlocks();

		if (this.isInLava())
		{
			this.fallDistance = 0.0F;
		}
		else
		{
			super.checkFallDamage(d0, onGround, blockstate, pos);
		}
	}

	@Override
	public void tick()
	{
		super.tick();
		this.floatMob();
		this.checkInsideBlocks();
	}

	private void floatMob()
	{
		if (this.isInLava())
		{
			CollisionContext collisioncontext = CollisionContext.of(this);

			if (collisioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA))
			{
				this.onGround = true;
			}
			else
			{
				this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.05D, 0.0D));
			}
		}
	}

	@Override
	protected PathNavigation createNavigation(Level level)
	{
		return new MeltyMonsterEntity.StriderPathNavigation(this, level);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader levelReader)
	{
		if (levelReader.getBlockState(pos).getFluidState().is(FluidTags.LAVA))
		{
			return 10.0F;
		}
		else
		{
			return this.isInLava() ? Float.NEGATIVE_INFINITY : 0.0F;
		}
	}

	@Override
	public boolean isPushedByFluid()
	{
		return false;
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 3;
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
	public float getLightLevelDependentMagicValue()
	{
		return 1.0F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.MAGMA_CUBE_JUMP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return SoundEvents.MAGMA_CUBE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.MAGMA_CUBE_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.MAGMA_CUBE_SQUISH, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private static class StriderPathNavigation extends GroundPathNavigation
	{
		public StriderPathNavigation(MeltyMonsterEntity mob, Level level)
		{
			super(mob, level);
		}

		@Override
		protected PathFinder createPathFinder(int maxVisitedNodes)
		{
			this.nodeEvaluator = new WalkNodeEvaluator();
			return new PathFinder(this.nodeEvaluator, maxVisitedNodes);
		}

		@Override
		protected boolean hasValidPathType(BlockPathTypes blockPathTypes)
		{
			return blockPathTypes != BlockPathTypes.LAVA && blockPathTypes != BlockPathTypes.DAMAGE_FIRE && blockPathTypes != BlockPathTypes.DANGER_FIRE ? super.hasValidPathType(blockPathTypes) : true;
		}

		@Override
		public boolean isStableDestination(BlockPos pos)
		{
			return this.level.getBlockState(pos).is(Blocks.LAVA) || super.isStableDestination(pos);
		}
	}

	private static class GoToLavaGoal extends MoveToBlockGoal
	{
		private final MeltyMonsterEntity parent;

		private GoToLavaGoal(MeltyMonsterEntity mob, double d0)
		{
			super(mob, d0, 8, 2);
			this.parent = mob;
		}

		@Override
		public BlockPos getMoveToTarget()
		{
			return this.blockPos;
		}

		@Override
		public boolean canContinueToUse()
		{
			return !this.parent.isInLava() && this.isValidTarget(this.parent.level, this.blockPos);
		}

		@Override
		public boolean canUse()
		{
			return !this.parent.isInLava() && super.canUse();
		}

		@Override
		protected boolean isValidTarget(LevelReader levelReader, BlockPos pos)
		{
			return levelReader.getBlockState(pos).is(Blocks.LAVA) && levelReader.getBlockState(pos.above()).isPathfindable(levelReader, pos, PathComputationType.LAND);
		}
	}
}