package com.github.mechalopa.hmag.entity;

import java.util.Random;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.PathType;
import net.minecraft.pathfinding.WalkNodeProcessor;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

public class MeltyMonsterEntity extends MonsterEntity implements IModMob, IRangedAttackMob
{
	public MeltyMonsterEntity(EntityType<? extends MeltyMonsterEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
		this.setPathfindingMalus(PathNodeType.LAVA, 0.0F);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, LivingEntity.class, 10.0F, 1.0D, 1.5D, (p) -> {
			return ModTags.checkTagContains(ModTags.MELTY_MONSTER_AVOID_MOBS, p.getType());
		}));
		this.goalSelector.addGoal(3, new MeltyMonsterEntity.MoveToLavaGoal(this, 1.5D));
		this.goalSelector.addGoal(5, new RangedAttackGoal(this, 1.0D, 30, 40, 8.0F));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, (p) -> {
			return !ModTags.checkTagContains(ModTags.MELTY_MONSTER_AVOID_MOBS, p.getType());
		}));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
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
			if (ModConfigs.cachedServer.MELTY_MONSTER_SET_FIRE && !this.isInLava() && !this.isInWaterRainOrBubble() && ForgeEventFactory.getMobGriefingEvent(this.level, this))
			{
				int i = MathHelper.floor(this.getX());
				int j = MathHelper.floor(this.getY());
				int k = MathHelper.floor(this.getZ());

				for (int l = 0; l < 4; ++l)
				{
					i = MathHelper.floor(this.getX() + (double)((float)(l % 2 * 2 - 1) * 0.25F));
					j = MathHelper.floor(this.getY());
					k = MathHelper.floor(this.getZ() + (double)((float)(l / 2 % 2 * 2 - 1) * 0.25F));
					BlockPos blockpos = new BlockPos(i, j, k);

					if (this.level.isEmptyBlock(blockpos))
					{
						this.level.setBlockAndUpdate(blockpos, AbstractFireBlock.getState(this.level, blockpos));
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
	public void performRangedAttack(LivingEntity target, float distanceFactor)
	{
		double d1 = target.getX() - this.getX();
		double d2 = target.getY() + (double)target.getEyeHeight() * 0.5D - this.getY(0.5D);
		double d3 = target.getZ() - this.getZ();
		float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.02F;
		SmallFireballEntity fireballentity = new SmallFireballEntity(this.level, this, d1 + this.getRandom().nextGaussian() * (double)f, d2, d3 + this.getRandom().nextGaussian() * (double)f);
		fireballentity.setPos(fireballentity.getX(), this.getY(0.5D) + 0.5D, fireballentity.getZ());
		this.level.addFreshEntity(fireballentity);
		this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	@SuppressWarnings("deprecation")
	public static boolean checkMeltyMonsterSpawnRules(EntityType<MeltyMonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		BlockPos.Mutable blockpos$mutable = pos.mutable();

		do
		{
			blockpos$mutable.move(Direction.UP);
		}
		while (worldIn.getFluidState(blockpos$mutable).is(FluidTags.LAVA));

		return worldIn.getBlockState(blockpos$mutable).isAir() && (reason == SpawnReason.SPAWNER || pos.getY() < 32 || randomIn.nextBoolean());
	}

	@Override
	public boolean checkSpawnObstruction(IWorldReader worldIn)
	{
		return worldIn.isUnobstructed(this);
	}

	@Override
	public boolean canStandOnFluid(Fluid fluidIn)
	{
		return fluidIn.is(FluidTags.LAVA);
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
			ISelectionContext iselectioncontext = ISelectionContext.of(this);

			if (iselectioncontext.isAbove(FlowingFluidBlock.STABLE_SHAPE, this.blockPosition(), true) && !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA))
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
	protected PathNavigator createNavigation(World worldIn)
	{
		return new MeltyMonsterEntity.LavaPathNavigator(this, worldIn);
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn)
	{
		if (worldIn.getBlockState(pos).getFluidState().is(FluidTags.LAVA))
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
		return 2;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.45D;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 1.74F;
	}

	@Override
	public float getBrightness()
	{
		return 1.0F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.MAGMA_CUBE_JUMP;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.MAGMA_CUBE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.MAGMA_CUBE_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.MAGMA_CUBE_SQUISH, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private static class LavaPathNavigator extends GroundPathNavigator
	{
		public LavaPathNavigator(MeltyMonsterEntity mob, World worldIn)
		{
			super(mob, worldIn);
		}

		@Override
		protected PathFinder createPathFinder(int i)
		{
			this.nodeEvaluator = new WalkNodeProcessor();
			return new PathFinder(this.nodeEvaluator, i);
		}

		@Override
		protected boolean hasValidPathType(PathNodeType pathNodeTypeIn)
		{
			return pathNodeTypeIn != PathNodeType.LAVA && pathNodeTypeIn != PathNodeType.DAMAGE_FIRE && pathNodeTypeIn != PathNodeType.DANGER_FIRE ? super.hasValidPathType(pathNodeTypeIn) : true;
		}

		@Override
		public boolean isStableDestination(BlockPos pos)
		{
			return this.level.getBlockState(pos).is(Blocks.LAVA) || super.isStableDestination(pos);
		}
	}

	private static class MoveToLavaGoal extends MoveToBlockGoal
	{
		private final MeltyMonsterEntity parent;

		private MoveToLavaGoal(MeltyMonsterEntity mob, double d0)
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
		protected boolean isValidTarget(IWorldReader world, BlockPos pos)
		{
			return world.getBlockState(pos).is(Blocks.LAVA) && world.getBlockState(pos.above()).isPathfindable(world, pos, PathType.LAND);
		}
	}
}