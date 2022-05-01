package com.github.mechalopa.hmag.entity;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.entity.goal.MeleeAttackGoal2;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class SavagefangEntity extends MonsterEntity implements IModMob
{
	private static final DataParameter<Byte> DATA_FLAGS_ID = EntityDataManager.defineId(SavagefangEntity.class, DataSerializers.BYTE);
	private SavagefangEntity leader;
	private int schoolSize = 1;
	private int leapCooldown;
	private float xRotAnimation;
	private float xRotAnimationO;
	private float attackAnimation;
	private float attackAnimationO;

	public SavagefangEntity(EntityType<? extends SavagefangEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.moveControl = new SavagefangEntity.MoveHelperController(this);
		this.lookControl = new SavagefangEntity.LookHelperController(this);
		this.xpReward = 3;
		this.leapCooldown = 0;
	}

	@Override
	protected PathNavigator createNavigation(World worldIn)
	{
		return new SwimmerPathNavigator(this, worldIn);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(2, new SavagefangEntity.LeapGoal(this));
		this.goalSelector.addGoal(3, new SavagefangEntity.AttackGoal(this));
		this.goalSelector.addGoal(4, new SavagefangEntity.SwimGoal(this, 1.0D, 20));
		this.goalSelector.addGoal(5, new SavagefangEntity.FollowSchoolLeaderGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, new SavagefangEntity.TargetPredicate(this)).setUnseenMemoryTicks(30));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_FLAGS_ID, (byte)0);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 12.0D)
				.add(Attributes.MOVEMENT_SPEED, 3.0D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D);
	}

	@Override
	public CreatureAttribute getMobType()
	{
		return CreatureAttribute.WATER;
	}

	@Override
	public void baseTick()
	{
		int i = this.getAirSupply();
		super.baseTick();
		this.handleAirSupply(i);
	}

	protected void handleAirSupply(int airSupply)
	{
		if (this.isAlive() && !this.isInWaterOrBubble())
		{
			this.setAirSupply(airSupply - 1);

			if (this.getAirSupply() == -20)
			{
				this.setAirSupply(0);
				this.hurt(DamageSource.DROWN, 2.0F);
			}
		}
		else
		{
			this.setAirSupply(300);
		}
	}

	@Override
	public void tick()
	{
		if (this.level.isClientSide)
		{
			if (this.isLaunched())
			{
				this.xRotAnimation = 0.0F;
			}

			this.attackAnimationO = this.attackAnimation;
			this.xRotAnimationO = this.xRotAnimation;
		}

		super.tick();

		if (this.hasFollowers() && this.level.random.nextInt(200) == 1)
		{
			List<SavagefangEntity> list = this.level.getEntitiesOfClass(this.getClass(), this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D));

			if (list.size() <= 1)
			{
				this.schoolSize = 1;
			}
		}

		if (this.level.isClientSide)
		{
			if (!this.isLaunched())
			{
				this.xRotAnimation = MathHelper.clamp(ModUtils.rotlerp(this.xRotAnimation, this.xRot, 90.0F, false), -180.0F, 180.0F);
			}

			if (this.isAttacking())
			{
				this.attackAnimation = Math.min(1.0F, this.attackAnimation + 0.25F);
			}
			else
			{
				this.attackAnimation = Math.max(0.0F, this.attackAnimation - 0.08F);
			}
		}
	}

	@Override
	public void aiStep()
	{
		if (!this.level.isClientSide)
		{
			if (this.getTarget() != null && this.getTarget().isAlive() && !this.isLaunched())
			{
				if (!this.isAttacking())
				{
					this.setAttacking(true);
				}

				if (this.leapCooldown > 0)
				{
					--this.leapCooldown;
				}
			}
			else
			{
				if (this.isAttacking())
				{
					this.setAttacking(false);
				}

				this.leapCooldown = 0;
			}
		}

		if (!this.isInWater())
		{
			if (this.isOnGround() && this.verticalCollision)
			{
				final float f = this.isLaunched() ? 0.2F : 0.4F;
				this.setDeltaMovement(this.getDeltaMovement().add((double)((this.getRandom().nextFloat() * 2.0F - 1.0F) * f), (double)(this.isLaunched() ? 0.4F : 0.6F), (double)((this.getRandom().nextFloat() * 2.0F - 1.0F) * f)));
				this.onGround = false;
				this.hasImpulse = true;
				this.playSound(this.getFlopSound(), this.getSoundVolume(), this.getVoicePitch());

				if (!this.isNoAi() && (this.getRandom().nextInt(8) == 0 || this.getAirSupply() <= 100))
				{
					this.setLaunched(true);
				}
			}
		}
		else
		{
			if (this.isLaunched())
			{
				this.setLaunched(false);
			}
		}

		super.aiStep();
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (this.isInvulnerableTo(source))
		{
			return false;
		}
		else if (super.hurt(source, amount))
		{
			if (!this.level.isClientSide && !this.isNoAi() && !this.isInWater() && !this.isLaunched())
			{
				this.setLaunched(true);
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		if (spawnDataIn == null)
		{
			spawnDataIn = new SavagefangEntity.GroupData(this);
		}
		else
		{
			this.startFollowing(((SavagefangEntity.GroupData)spawnDataIn).leader);
		}

		this.leapCooldown = 0;

		return spawnDataIn;
	}

	@Override
	public void travel(Vector3d vector3dIn)
	{
		if (this.isEffectiveAi() && this.isInWater())
		{
			this.moveRelative(0.01F, vector3dIn);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));

			if (this.getTarget() == null)
			{
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		}
		else
		{
			super.travel(vector3dIn);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public float getWalkTargetValue(BlockPos pos, IWorldReader worldIn)
	{
		return worldIn.getFluidState(pos).is(FluidTags.WATER) ? 10.0F + worldIn.getBrightness(pos) - 0.5F : super.getWalkTargetValue(pos, worldIn);
	}

	@Override
	public boolean checkSpawnObstruction(IWorldReader worldIn)
	{
		return worldIn.isUnobstructed(this);
	}

	@Override
	public boolean isPushedByFluid()
	{
		return false;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return sizeIn.height * 0.65F;
	}

	public void setLeapCooldown(int value)
	{
		this.leapCooldown = value;
	}

	public int getLeapCooldown()
	{
		return this.leapCooldown;
	}

	public boolean isLaunched()
	{
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setLaunched(boolean flag)
	{
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		this.entityData.set(DATA_FLAGS_ID, flag ? (byte)(b0 | 1) : (byte)(b0 & -2));
	}

	public boolean isAttacking()
	{
		return (this.entityData.get(DATA_FLAGS_ID) & 2) != 0;
	}

	public void setAttacking(boolean flag)
	{
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		this.entityData.set(DATA_FLAGS_ID, flag ? (byte)(b0 | 2) : (byte)(b0 & -3));
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setLaunched(compound.getBoolean("isLaunched"));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putBoolean("isLaunched", this.isLaunched());
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 8;
	}

	@SuppressWarnings("deprecation")
	public static boolean checkSavagefangSpawnRules(EntityType<? extends SavagefangEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return worldIn.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(worldIn, pos, randomIn) && worldIn.getFluidState(pos).is(FluidTags.WATER) && (reason == SpawnReason.SPAWNER || (pos.getY() >= worldIn.getSeaLevel() - 10 && worldIn.canSeeSkyFromBelowWater(pos) && randomIn.nextDouble() < ModConfigs.cachedServer.SAVAGEFANG_SPAWN_CHANCE));
	}

	public int getMaxSchoolSize()
	{
		return 5;
	}

	protected boolean canRandomSwim()
	{
		return !this.isFollower();
	}

	public boolean isFollower()
	{
		return this.leader != null && this.leader.isAlive();
	}

	public SavagefangEntity startFollowing(SavagefangEntity mob)
	{
		this.leader = mob;
		mob.addFollower();
		return mob;
	}

	public void stopFollowing()
	{
		this.leader.removeFollower();
		this.leader = null;
	}

	private void addFollower()
	{
		++this.schoolSize;
	}

	private void removeFollower()
	{
		--this.schoolSize;
	}

	public boolean canBeFollowed()
	{
		return this.hasFollowers() && this.schoolSize < this.getMaxSchoolSize();
	}

	public boolean hasFollowers()
	{
		return this.schoolSize > 1;
	}

	public boolean inRangeOfLeader()
	{
		return this.distanceToSqr(this.leader) <= 121.0D;
	}

	public void pathToLeader()
	{
		if (this.isFollower() && this.getTarget() == null)
		{
			this.getNavigation().moveTo(this.leader, 1.0D);
		}
	}

	public void addFollowers(Stream<SavagefangEntity> stream)
	{
		stream.limit((long)(this.getMaxSchoolSize() - this.schoolSize)).filter((p) -> {
			return p != this;
		}).forEach((p) -> {
			p.startFollowing(this);
		});
	}

	@OnlyIn(Dist.CLIENT)
	public float getXRotAnimationScale(float f)
	{
		return MathHelper.lerp(f, this.xRotAnimationO / 180.0F, this.xRotAnimation / 180.0F);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAttackAnimationScale(float f)
	{
		return MathHelper.lerp(f, this.attackAnimationO, this.attackAnimation);
	}

	@Override
	protected boolean isMovementNoisy()
	{
		return false;
	}

	@Override
	public int getAmbientSoundInterval()
	{
		return 120;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return null;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.COD_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.COD_DEATH;
	}

	@Override
	protected SoundEvent getSwimSound()
	{
		return SoundEvents.FISH_SWIM;
	}

	protected SoundEvent getFlopSound()
	{
		return SoundEvents.COD_FLOP;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn){}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 15)
		{
			Vector3d vector3d1 = this.getViewVector(0.0F);

			for (int i = 0; i < 12; ++i)
			{
				this.level.addParticle(ParticleTypes.BUBBLE, this.getRandomX(0.5D) - vector3d1.x * 1.0D, this.getRandomY() - vector3d1.y * 1.0D, this.getRandomZ(0.5D) - vector3d1.z * 1.0D, 0.0D, 0.0D, 0.0D);
			}
		}
		else
		{
			super.handleEntityEvent(id);
		}
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static class GroupData implements ILivingEntityData
	{
		public final SavagefangEntity leader;

		public GroupData(SavagefangEntity mob)
		{
			this.leader = mob;
		}
	}

	protected static class MoveHelperController extends MovementController
	{
		private final SavagefangEntity mob;

		public MoveHelperController(SavagefangEntity mob)
		{
			super(mob);
			this.mob = mob;
		}

		@Override
		public void tick()
		{
			if (this.mob.isEyeInFluid(FluidTags.WATER))
			{
				this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.mob.getNavigation().isDone())
			{
				float f = (float)(this.speedModifier * this.mob.getAttributeValue(Attributes.MOVEMENT_SPEED));
				this.mob.setSpeed(MathHelper.lerp(0.125F, this.mob.getSpeed(), f));
				double d0 = this.wantedX - this.mob.getX();
				double d1 = this.wantedY - this.mob.getY();
				double d2 = this.wantedZ - this.mob.getZ();
				double d3 = (double)MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);

				if (d1 != 0.0D)
				{
					this.mob.setDeltaMovement(this.mob.getDeltaMovement().add(0.0D, (double)this.mob.getSpeed() * (d1 / d3) * 0.01D, 0.0D));
				}

				if (d3 >= (double)2.5E-7F)
				{
					float f1 = (float)(MathHelper.atan2(d2, d0) * (double)(180.0F / (float)Math.PI)) - 90.0F;
					this.mob.yRot = this.rotlerp(this.mob.yRot, f1, this.mob.getTarget() != null ? 90.0F : 30.0F);
					this.mob.yBodyRot = this.mob.yRot;
				}
			}
			else
			{
				this.mob.setSpeed(MathHelper.lerp(0.125F, this.mob.getSpeed(), 0.0F));
			}
		}
	}

	protected class LookHelperController extends LookController
	{
		private final SavagefangEntity mob;

		public LookHelperController(SavagefangEntity mob)
		{
			super(mob);
			this.mob = mob;
		}

		@Override
		public void tick()
		{
			if (!this.mob.isLaunched())
			{
				Vector3d vector3d = this.mob.getDeltaMovement();

				if (vector3d.lengthSqr() > 0.001D)
				{
					vector3d = vector3d.normalize();
					double d0 = vector3d.x;
					double d1 = vector3d.z;
					double d2 = Math.sqrt(d0 * d0 + d1 * d1);
					this.mob.xRot = MathHelper.clamp((float)(MathHelper.atan2(vector3d.y, d2) * (180.0F / (float)Math.PI)), -60.0F, 60.0F);
				}
				else
				{
					this.mob.xRot = 0.0F;
				}
			}
			else
			{
				this.mob.xRot = 0.0F;
			}

			this.mob.yHeadRot = this.mob.yRot;
		}
	}

	private class AttackGoal extends MeleeAttackGoal2
	{
		private final SavagefangEntity mob;

		public AttackGoal(SavagefangEntity mob)
		{
			super(mob, 1.5D, false, 0.7F);
			this.mob = mob;
		}

		@Override
		public boolean canUse()
		{
			return super.canUse() && !this.mob.isLaunched();
		}

		@Override
		public boolean canContinueToUse()
		{
			return super.canContinueToUse() && !this.mob.isLaunched();
		}

		@Override
		protected int getAttackInterval()
		{
			return 10;
		}
	}

	private static class LeapGoal extends Goal
	{
		private final SavagefangEntity mob;
		private LivingEntity target;

		public LeapGoal(SavagefangEntity mob)
		{
			this.mob = mob;
			this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			if (this.mob.isVehicle() || this.mob.getLeapCooldown() > 0 || this.mob.isLaunched())
			{
				return false;
			}
			else
			{
				this.target = this.mob.getTarget();

				if (this.target == null)
				{
					return false;
				}
				else
				{
					double d0 = this.mob.distanceToSqr(this.target);

					if (!(d0 < 4.0D) && !(d0 > 16.0D))
					{
						if (!this.mob.isInWaterOrBubble())
						{
							return false;
						}
						else
						{
							return this.mob.getRandom().nextInt(4) == 0;
						}
					}
					else
					{
						return false;
					}
				}
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return false;
		}

		@Override
		public boolean isInterruptable()
		{
			return false;
		}

		@Override
		public void start()
		{
			this.mob.setLeapCooldown(15 + this.mob.getRandom().nextInt(15));
			Vector3d vector3d = this.mob.getDeltaMovement();
			Vector3d vector3d1 = new Vector3d(this.target.getX() - this.mob.getX(), 0.0D, this.target.getZ() - this.mob.getZ());

			if (vector3d1.lengthSqr() > 1.0E-7D)
			{
				vector3d1 = vector3d1.normalize().scale(0.25D + (double)(this.mob.getRandom().nextFloat() * 0.05F)).add(vector3d.scale(0.5D));
			}

			if (!this.target.isInWaterOrBubble() || ( this.target.getY() + 1.0D > this.mob.getEyeY() && this.mob.level.isEmptyBlock(new BlockPos(this.mob.blockPosition().getX(), this.mob.blockPosition().getY() + 1.0D, this.mob.blockPosition().getZ()))))
			{
				this.mob.setDeltaMovement(vector3d1.x, 0.4D + (double)(this.mob.getRandom().nextFloat() * 0.2F), vector3d1.z);
			}
			else
			{
				this.mob.setDeltaMovement(vector3d1.x * 1.2D, 0.05D, vector3d1.z * 1.2D);
			}

			this.mob.getNavigation().stop();

			FluidState fluidstate = this.mob.level.getFluidState(this.mob.blockPosition());

			if (fluidstate.is(FluidTags.WATER))
			{
				this.mob.playSound(SoundEvents.DOLPHIN_JUMP, 0.9F, 1.2F);
				this.mob.level.broadcastEntityEvent(this.mob, (byte)15);
			}
		}
	}

	private static class SwimGoal extends RandomSwimmingGoal
	{
		private final SavagefangEntity mob;

		public SwimGoal(SavagefangEntity mob, double speed, int interval)
		{
			super(mob, speed, interval);
			this.mob = mob;
		}

		@Override
		public boolean canUse()
		{
			return this.mob.canRandomSwim() && super.canUse();
		}
	}

	private static class FollowSchoolLeaderGoal extends Goal
	{
		private final SavagefangEntity mob;
		private int timeToRecalcPath;
		private int nextStartTick;

		public FollowSchoolLeaderGoal(SavagefangEntity mob)
		{
			this.mob = mob;
			this.nextStartTick = this.nextStartTick(mob);
		}

		protected int nextStartTick(SavagefangEntity mob)
		{
			return 200 + mob.getRandom().nextInt(200) % 20;
		}

		@Override
		public boolean canUse()
		{
			if (this.mob.getTarget() != null)
			{
				return false;
			}
			else if (this.mob.hasFollowers())
			{
				return false;
			}
			else if (this.mob.isFollower())
			{
				return true;
			}
			else if (this.nextStartTick > 0)
			{
				--this.nextStartTick;
				return false;
			}
			else
			{
				this.nextStartTick = this.nextStartTick(this.mob);
				Predicate<SavagefangEntity> predicate = (p) -> {
					return p.canBeFollowed() || !p.isFollower();
				};
				List<SavagefangEntity> list = this.mob.level.getEntitiesOfClass(this.mob.getClass(), this.mob.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), predicate);
				SavagefangEntity monsterentity = list.stream().filter(SavagefangEntity::canBeFollowed).findAny().orElse(this.mob);
				monsterentity.addFollowers(list.stream().filter((p) -> {
					return !p.isFollower();
				}));
				return this.mob.isFollower();
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return this.mob.getTarget() == null && this.mob.isFollower() && this.mob.inRangeOfLeader();
		}

		@Override
		public void start()
		{
			this.timeToRecalcPath = 0;
		}

		@Override
		public void stop()
		{
			this.mob.stopFollowing();
		}

		@Override
		public void tick()
		{
			if (--this.timeToRecalcPath <= 0)
			{
				this.timeToRecalcPath = 30;
				this.mob.pathToLeader();
			}
		}
	}

	private static class TargetPredicate implements Predicate<LivingEntity>
	{
		private final SavagefangEntity parent;

		public TargetPredicate(SavagefangEntity mob)
		{
			this.parent = mob;
		}

		@Override
		public boolean test(@Nullable LivingEntity livingEntityIn)
		{
			if (this.parent.isLaunched() || ModTags.checkTagContains(ModTags.SAVAGEFANG_TARGET_BLACKLIST, livingEntityIn.getType()) || livingEntityIn instanceof SavagefangEntity || livingEntityIn instanceof CreeperEntity)
			{
				return false;
			}
			else if (livingEntityIn instanceof PlayerEntity || (livingEntityIn.getMobType() != CreatureAttribute.WATER && !livingEntityIn.canBreatheUnderwater() && livingEntityIn instanceof AnimalEntity && ModConfigs.cachedServer.SAVAGEFANG_ATTACK_ANIMALS) || (livingEntityIn instanceof AbstractVillagerEntity && ModConfigs.cachedServer.SAVAGEFANG_ATTACK_VILLAGERS) || (livingEntityIn.getMobType() == CreatureAttribute.ILLAGER && ModConfigs.cachedServer.SAVAGEFANG_ATTACK_ILLAGERS))
			{
				return livingEntityIn.distanceToSqr(this.parent) <= 4.0D * 4.0D || (livingEntityIn.isInWaterOrBubble() && livingEntityIn.distanceToSqr(this.parent) <= 9.0D * 9.0D);
			}
			else if (!(livingEntityIn instanceof FlyingEntity) && ModConfigs.cachedServer.SAVAGEFANG_ATTACK_DAMAGED_MOBS)
			{
				return (livingEntityIn.isInWaterOrBubble() && livingEntityIn.distanceToSqr(this.parent) <= 9.0D * 9.0D) && ((livingEntityIn.getHealth() < 10.0F && livingEntityIn.getHealth() < livingEntityIn.getMaxHealth()) || livingEntityIn.getHealth() < livingEntityIn.getMaxHealth() * 0.25F);
			}
			else
			{
				return false;
			}
		}
	}
}