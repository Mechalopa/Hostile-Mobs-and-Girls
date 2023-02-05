package com.github.mechalopa.hmag.world.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModSpawnRules;
import com.github.mechalopa.hmag.util.ModUtils;
import com.github.mechalopa.hmag.world.entity.ai.goal.HurtByTargetGoal2;
import com.github.mechalopa.hmag.world.entity.projectile.MagicBulletEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.FlyingMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.BodyRotationControl;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.NetworkHooks;

public class DyssomniaEntity extends FlyingMob implements Enemy
{
	private static final EntityDataAccessor<Byte> ATTACK_PHASE = SynchedEntityData.defineId(DyssomniaEntity.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Integer> ATTACKING_TIME = SynchedEntityData.defineId(DyssomniaEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> RETREATING = SynchedEntityData.defineId(DyssomniaEntity.class, EntityDataSerializers.BOOLEAN);
	private float xRotAnimation;
	private float xRotAnimationO;
	private int animationTick;
	private int animationTickO;

	public DyssomniaEntity(EntityType<? extends DyssomniaEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 25;
		this.moveControl = new DyssomniaEntity.DyssomniaMoveControl(this);
		this.lookControl = new DyssomniaEntity.DyssomniaLookControl(this);
	}

	@Override
	protected BodyRotationControl createBodyControl()
	{
		return new DyssomniaEntity.DyssomniaBodyRotationControl(this);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(5, new DyssomniaEntity.RandomFlyGoal(this));
		this.goalSelector.addGoal(6, new DyssomniaEntity.LookAroundGoal(this));
		if (ModConfigs.cachedServer.DYSSOMNIA_SUMMON_PHANTOM)
			this.goalSelector.addGoal(7, new DyssomniaEntity.SummonGoal(this));
		this.goalSelector.addGoal(8, new DyssomniaEntity.ShotAttackGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal2(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true).setUnseenMemoryTicks(180));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACK_PHASE, (byte)0);
		this.entityData.define(ATTACKING_TIME, -1);
		this.entityData.define(RETREATING, false);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Mob.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 32.0D);
	}

	@Override
	public MobType getMobType()
	{
		return MobType.UNDEAD;
	}

	@Override
	public void tick()
	{
		if (this.level.isClientSide)
		{
			this.xRotAnimationO = this.xRotAnimation;
			this.animationTickO = this.animationTick;
		}

		super.tick();

		if (this.level.isClientSide)
		{
			float f = Mth.cos((this.getId() * 3 + this.tickCount) * 0.13F + (float)Math.PI);
			float f1 = Mth.cos((this.getId() * 3 + this.tickCount + 1) * 0.13F + (float)Math.PI);

			if (f > 0.0F && f1 <= 0.0F)
			{
				this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PHANTOM_FLAP, this.getSoundSource(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
			}

			boolean flag = this.isActuallyRetreating();

			for (int i = 0; i < 3; ++i)
			{
				float f2 = Mth.cos(this.getYRot() * ((float)Math.PI / 180.0F)) * (i * 0.4F + 1.7F);
				float f3 = Mth.sin(this.getYRot() * ((float)Math.PI / 180.0F)) * (i * 0.4F + 1.7F);
				float f4 = (0.4F + f * (i * 0.05F + 0.5F));

				if (flag && this.tickCount % 12 == i * 3)
				{
					this.level.addParticle(ParticleTypes.FLAME, this.getX() + f2, this.getY() + f4, this.getZ() + f3, 0.0D, 0.0D, 0.0D);
					this.level.addParticle(ParticleTypes.FLAME, this.getX() - f2, this.getY() + f4, this.getZ() - f3, 0.0D, 0.0D, 0.0D);
				}
				else
				{
					this.level.addParticle(ParticleTypes.MYCELIUM, this.getX() + f2, this.getY() + f4, this.getZ() + f3, 0.0D, 0.0D, 0.0D);
					this.level.addParticle(ParticleTypes.MYCELIUM, this.getX() - f2, this.getY() + f4, this.getZ() - f3, 0.0D, 0.0D, 0.0D);
				}
			}

			if (this.getAttackingTime() >= 0)
			{
				if (this.getAttackPhase() == DyssomniaEntity.AttackPhase.SHOT)
				{
					double d0 = 1.75D;
					double d1 = 1.5D;
					Vec3 vec3 = this.getViewVector(1.0F);
					this.level.addParticle(ParticleTypes.SMOKE, this.getX() + vec3.x * d0, this.getEyeY() - vec3.y * d1, this.getZ() + vec3.z * d0, 0.0D, 0.0D, 0.0D);
				}

				if (this.animationTick < 30)
				{
					++this.animationTick;
				}
			}
			else if (this.animationTick > 0)
			{
				this.animationTick = Math.max(this.animationTick - 2, 0);
			}

			this.xRotAnimation = Mth.clamp(ModUtils.rotlerp(this.xRotAnimation, this.getXRot(), 90.0F, false), -180.0F, 180.0F);
		}
	}

	@Override
	public void aiStep()
	{
		if (!this.level.isClientSide)
		{
			if (this.isAlive() && !this.isNoAi())
			{
				if (!this.isRetreating() && this.isSunBurnTick())
				{
					this.setRetreating(true);
				}
				else if (this.isRetreating() && this.isStopRetreatTick())
				{
					this.setRetreating(false);
				}
			}
		}

		super.aiStep();
	}

	private boolean isStopRetreatTick()
	{
		if (this.level.isNight() && !this.level.isClientSide)
		{
			@SuppressWarnings("deprecation")
			float f = this.getLightLevelDependentMagicValue();

			if (f <= 0.5F && this.random.nextFloat() * 30.0F < (0.6F - f) * 2.0F)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance potioneffect)
	{
		if (potioneffect.getEffect() == MobEffects.BLINDNESS)
		{
			MobEffectEvent.Applicable event = new MobEffectEvent.Applicable(this, potioneffect);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == Event.Result.ALLOW;
		}

		return super.canBeAffected(potioneffect);
	}

	private void summonPhantom(ServerLevel serverlevel, LivingEntity attacker, LivingEntity target, RandomSource random, int count)
	{
		boolean flag = false;

		for (int i = 0; i < count; ++i)
		{
			double d1 = attacker.getX() + (random.nextDouble() - random.nextDouble()) * 3.0D;
			double d2 = attacker.getY() + 0.5D;
			double d3 = attacker.getZ() + (random.nextDouble() - random.nextDouble()) * 3.0D;

			Phantom phantom = EntityType.PHANTOM.create(attacker.level);
			phantom.moveTo(d1, d2, d3, this.getYRot() + (random.nextFloat() - 0.5F) * 12.0F, 0.0F);
			phantom.finalizeSpawn(serverlevel, attacker.level.getCurrentDifficultyAt(attacker.blockPosition()), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
			serverlevel.addFreshEntityWithPassengers(phantom);
			phantom.getPersistentData().putBoolean(ModUtils.WITH_SPAWN_PARTICLE_KEY, true);
			serverlevel.gameEvent(phantom, GameEvent.ENTITY_PLACE, phantom.blockPosition());

			if (this.isOnFire())
			{
				phantom.setSecondsOnFire(this.getRemainingFireTicks() / 20 + 2);
				flag = true;
			}
		}

		if (flag)
		{
			this.clearFire();
		}

		attacker.level.levelEvent(2004, attacker.blockPosition(), 0);
		attacker.playSound(ModSoundEvents.DYSSOMNIA_SUMMON.get(), 5.0F, 1.0F);
	}

	public static boolean checkDyssomniaSpawnRules(EntityType<DyssomniaEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random)
	{
		return ModSpawnRules.checkMobSpawnInLightRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || (levelAccessor.canSeeSky(pos) && random.nextFloat() < levelAccessor.getMoonBrightness()));
	}

	public DyssomniaEntity.AttackPhase getAttackPhase()
	{
		return DyssomniaEntity.AttackPhase.byId(this.entityData.get(ATTACK_PHASE));
	}

	private void setAttackPhase(DyssomniaEntity.AttackPhase phase)
	{
		this.entityData.set(ATTACK_PHASE, (byte)(phase.getId() & 255));
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackingTime()
	{
		return this.entityData.get(ATTACKING_TIME);
	}

	private void setAttackingTime(int value)
	{
		this.entityData.set(ATTACKING_TIME, value);
	}

	@OnlyIn(Dist.CLIENT)
	public float getXRotAnimationScale(float f)
	{
		return Mth.lerp(f, this.xRotAnimationO / 180.0F, this.xRotAnimation / 180.0F);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAttackAnimationScale(float f)
	{
		return Mth.lerp(f, this.animationTickO / 30.0F, this.animationTick / 30.0F);
	}

	public boolean isRetreating()
	{
		return this.entityData.get(RETREATING);
	}

	public void setRetreating(boolean flag)
	{
		this.entityData.set(RETREATING, flag);
	}

	public boolean isActuallyRetreating()
	{
		return this.isRetreating() && !this.isPersistenceRequired() && !this.requiresCustomPersistence();
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setRetreating(compound.getBoolean("isRetreating"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putBoolean("isRetreating", this.isRetreating());
	}

	@Override
	protected boolean shouldDespawnInPeaceful()
	{
		return true;
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 1;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size)
	{
		return size.height * 0.45F;
	}

	@Override
	public SoundSource getSoundSource()
	{
		return SoundSource.HOSTILE;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.DYSSOMNIA_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.DYSSOMNIA_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.DYSSOMNIA_DEATH.get();
	}

	@Override
	protected float getSoundVolume()
	{
		return 2.0F;
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum AttackPhase
	{
		WAIT(0),
		SUMMON(1),
		SHOT(2);

		private final int id;
		private static final DyssomniaEntity.AttackPhase[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(DyssomniaEntity.AttackPhase::getId)).toArray((p) -> {
			return new DyssomniaEntity.AttackPhase[p];
		});

		private AttackPhase(int id)
		{
			this.id = id;
		}

		public int getId()
		{
			return this.id;
		}

		public static DyssomniaEntity.AttackPhase byId(int id)
		{
			if (id < 0 || id >= BY_ID.length)
			{
				id = 0;
			}

			return BY_ID[id];
		}
	}

	private static class SummonGoal extends Goal
	{
		private final DyssomniaEntity parent;
		public int attackTimer;
		private final TargetingConditions phantomCountTargeting = TargetingConditions.forNonCombat().range(32.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

		public SummonGoal(DyssomniaEntity mob)
		{
			this.parent = mob;
		}

		@Override
		public boolean canUse()
		{
			if (!this.parent.isRetreating() && this.parent.getTarget() != null && this.parent.getTarget() instanceof Player && this.parent.getAttackPhase() == DyssomniaEntity.AttackPhase.WAIT && (this.parent.getRandom().nextInt(6) == 0 || this.parent.isOnFire()) && this.parent.distanceToSqr(this.parent.getTarget()) < 16.0D * 16.0D)
			{
				return this.parent.level.getNearbyEntities(Phantom.class, this.phantomCountTargeting, this.parent, this.parent.getBoundingBox().inflate(32.0D)).size() < 1;
			}
			else
			{
				return false;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return this.parent.getTarget() != null && this.parent.getTarget() instanceof Player && this.parent.getAttackPhase() == DyssomniaEntity.AttackPhase.SUMMON;
		}

		@Override
		public void start()
		{
			this.attackTimer = -30;
			this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.SUMMON);
		}

		@Override
		public void stop()
		{
			this.parent.setAttackingTime(-1);
			this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.WAIT);
		}

		@Override
		public void tick()
		{
			LivingEntity target = this.parent.getTarget();
			double d0 = 32.0D;

			if ((target.distanceToSqr(this.parent) < d0 * d0 || this.attackTimer > 10) && this.parent.hasLineOfSight(target))
			{
				Level level = this.parent.level;
				++this.attackTimer;

				if (this.attackTimer == 20)
				{
					this.parent.summonPhantom((ServerLevel)level, this.parent, target, this.parent.getRandom(), 1 + this.parent.getRandom().nextInt(this.parent.level.getDifficulty() == Difficulty.HARD ? 3 : 2));
					this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.WAIT);
				}
			}
			else
			{
				--this.attackTimer;

				if (this.attackTimer <= -40)
				{
					this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.WAIT);
				}
			}

			this.parent.setAttackingTime(this.attackTimer < 0 ? -1 : this.attackTimer);
		}
	}

	private static class ShotAttackGoal extends Goal
	{
		private final DyssomniaEntity parent;
		public int attackTimer;

		public ShotAttackGoal(DyssomniaEntity mob)
		{
			this.parent = mob;
		}

		@Override
		public boolean canUse()
		{
			return this.parent.getTarget() != null && this.parent.getAttackPhase() == DyssomniaEntity.AttackPhase.WAIT && this.parent.distanceToSqr(this.parent.getTarget()) < 24.0D * 24.0D;
		}

		@Override
		public boolean canContinueToUse()
		{
			return this.parent.getTarget() != null && this.parent.getAttackPhase() == DyssomniaEntity.AttackPhase.SHOT;
		}

		@Override
		public void start()
		{
			this.attackTimer = -50;
			this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.SHOT);
		}

		@Override
		public void stop()
		{
			this.parent.setAttackingTime(-1);
			this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.WAIT);
		}

		@Override
		public void tick()
		{
			LivingEntity target = this.parent.getTarget();
			double d0 = 32.0D;

			if ((target.distanceToSqr(this.parent) < d0 * d0 || this.attackTimer > 10) && this.parent.hasLineOfSight(target))
			{
				Level level = this.parent.level;
				++this.attackTimer;

				if (this.attackTimer == 30)
				{
					double d1 = 1.75D;
					double d2 = 1.5D;
					Vec3 vec3 = this.parent.getViewVector(1.0F);
					double d3 = target.getX() - (this.parent.getX() + vec3.x * d1);
					double d4 = target.getY(0.5D) - (this.parent.getEyeY() - vec3.y * d2);
					double d5 = target.getZ() - (this.parent.getZ() + vec3.z * d1);

					if (!this.parent.isSilent())
					{
						level.levelEvent((Player)null, 1024, this.parent.blockPosition(), 0);
					}

					double d6 = Math.sqrt(d3 * d3 + d5 * d5) * 0.02F;
					MagicBulletEntity bullet = new MagicBulletEntity(level, this.parent, d3 + this.parent.getRandom().nextGaussian() * d6, d4, d5 + this.parent.getRandom().nextGaussian() * d6);
					bullet.setPos(this.parent.getX() + vec3.x * d1, this.parent.getEyeY() - vec3.y * d2, this.parent.getZ() + vec3.z * d1);
					bullet.setDamage(5.0F);
					bullet.setEffectLevel((byte)1);
					bullet.setVariant(1);
					level.addFreshEntity(bullet);
					this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.WAIT);
				}
			}
			else
			{
				--this.attackTimer;

				if (this.attackTimer <= -60)
				{
					this.parent.setAttackPhase(DyssomniaEntity.AttackPhase.WAIT);
				}
			}

			this.parent.setAttackingTime(this.attackTimer < 0 ? -1 : this.attackTimer);
		}
	}

	private static class LookAroundGoal extends Goal
	{
		private final DyssomniaEntity parent;

		public LookAroundGoal(DyssomniaEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse()
		{
			return true;
		}

		@Override
		public void tick()
		{
			if (this.parent.getTarget() == null || (this.parent.isActuallyRetreating() && this.parent.getAttackPhase() == DyssomniaEntity.AttackPhase.WAIT))
			{
				Vec3 vec3 = this.parent.getDeltaMovement().normalize();
				double d0 = vec3.x;
				double d1 = vec3.z;
				double d2 = Math.sqrt(d0 * d0 + d1 * d1);
				this.parent.setYRot(-((float)Mth.atan2(d0, d1)) * (180.0F / (float)Math.PI));
				this.parent.yBodyRot = this.parent.getYRot();
				this.parent.setXRot(Mth.clamp((float)(Mth.atan2(vec3.y, d2) * (180.0F / (float)Math.PI)), -45.0F, 45.0F));
			}
			else
			{
				LivingEntity target = this.parent.getTarget();

				if (target.distanceToSqr(this.parent) < 4096.0D)
				{
					double d0 = target.getX() - this.parent.getX();
					double d1 = target.getY() + target.getEyeHeight();
					double d2 = target.getZ() - this.parent.getZ();
					double d3 = Math.sqrt(d0 * d0 + d2 * d2);
					this.parent.setYRot(-((float)Mth.atan2(d0, d2)) * (180.0F / (float)Math.PI));
					this.parent.yBodyRot = this.parent.getYRot();
					this.parent.setXRot(Mth.clamp(-(float)(Mth.atan2(this.parent.getY() - d1, d3) * (180.0F / (float)Math.PI)), -45.0F, 45.0F));
				}
			}
		}
	}

	protected class DyssomniaMoveControl extends MoveControl
	{
		private final DyssomniaEntity parent;
		private int floatDuration;

		public DyssomniaMoveControl(DyssomniaEntity mob)
		{
			super(mob);
			this.parent = mob;
		}

		@Override
		public void tick()
		{
			if (this.operation == MoveControl.Operation.MOVE_TO)
			{
				if (this.floatDuration-- <= 0)
				{
					this.floatDuration += this.parent.getRandom().nextInt(5) + 5;
					Vec3 vec3 = new Vec3(this.wantedX - this.parent.getX(), this.wantedY - this.parent.getY(), this.wantedZ - this.parent.getZ());
					double d0 = vec3.length();
					vec3 = vec3.normalize();

					if (ModUtils.canReach(this.parent, vec3, Mth.ceil(d0)))
					{
						this.parent.setDeltaMovement(this.parent.getDeltaMovement().add(vec3.scale(0.1D)));
					}
					else
					{
						this.operation = MoveControl.Operation.WAIT;
					}
				}
			}
		}
	}

	protected class DyssomniaBodyRotationControl extends BodyRotationControl
	{
		public DyssomniaBodyRotationControl(Mob mob)
		{
			super(mob);
		}

		@Override
		public void clientTick()
		{
			DyssomniaEntity.this.yHeadRot = DyssomniaEntity.this.yBodyRot;
			DyssomniaEntity.this.yBodyRot = DyssomniaEntity.this.getYRot();
		}
	}

	protected class DyssomniaLookControl extends LookControl
	{
		public DyssomniaLookControl(Mob mob)
		{
			super(mob);
		}

		@Override
		public void tick(){}
	}

	private static class RandomFlyGoal extends Goal
	{
		private final DyssomniaEntity parent;

		public RandomFlyGoal(DyssomniaEntity mob)
		{
			this.parent = mob;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse()
		{
			MoveControl movecontrol = this.parent.getMoveControl();

			if (!movecontrol.hasWanted())
			{
				return true;
			}
			else
			{
				double d0 = movecontrol.getWantedX() - this.parent.getX();
				double d1 = movecontrol.getWantedY() - this.parent.getY();
				double d2 = movecontrol.getWantedZ() - this.parent.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				return d3 < 1.0D || d3 > 3600.0D;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return false;
		}

		@Override
		public void start()
		{
			RandomSource random = this.parent.getRandom();
			BlockPos blockpos = new BlockPos(this.parent.getX(), Math.round(this.parent.getY()), this.parent.getZ());
			boolean flag = this.parent.isActuallyRetreating();
			double d0 = this.parent.getX();
			double d1 = this.parent.getY();
			double d2 = this.parent.getZ();

			if (flag && this.parent.level.canSeeSky(blockpos) && blockpos.getY() < (this.parent.level.getMaxBuildHeight() + 64))
			{
				d0 = d0 + (random.nextFloat() * 2.0F - 1.0F) * 8.0F;
				d1 = d1 + ((random.nextFloat() * 4.0F) + 8.0F);
				d2 = d2 + (random.nextFloat() * 2.0F - 1.0F) * 8.0F;
			}
			else
			{
				boolean flag1 = false;

				if (this.parent.getTarget() != null)
				{
					LivingEntity target = this.parent.getTarget();
					double distance = target.distanceToSqr(this.parent);

					if (distance < 4096.0D)
					{
						Vec3 vec3 = target.getEyePosition(1.0F);

						float f = random.nextFloat() * 2.0F;
						float f1 = 4.0F + random.nextFloat() * 4.0F;
						d0 = vec3.x + Mth.cos(f * (float)Math.PI) * f1;
						d1 = vec3.y + 2.0D + random.nextDouble() * 2.0D;
						d2 = vec3.z + Mth.sin(f * (float)Math.PI) * f1;
						flag1 = true;
					}
				}

				if (!flag1)
				{
					Level level = this.parent.level;
					BlockPos.MutableBlockPos blockpos$mutable = blockpos.mutable();
					boolean flag2 = false;
					int i = 0;

					while (level.isEmptyBlock(blockpos$mutable) && blockpos$mutable.getY() > level.getMinBuildHeight())
					{
						blockpos$mutable.move(Direction.DOWN);
						++i;

						if (i >= 16)
						{
							flag2 = true;
							break;
						}
					}

					d0 = d0 + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
					d1 = d1 + (flag2 ? ((random.nextFloat() * -8.0F)) : ((random.nextFloat() * 2.0F - 1.0F) * 8.0F));
					d2 = d2 + (random.nextFloat() * 2.0F - 1.0F) * 16.0F;
				}
			}

			this.parent.getMoveControl().setWantedPosition(d0, d1, d2, flag ? 0.75D : 0.25D);
		}
	}
}