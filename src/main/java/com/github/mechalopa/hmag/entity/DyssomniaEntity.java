package com.github.mechalopa.hmag.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.Random;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.entity.goal.HurtByTargetGoal2;
import com.github.mechalopa.hmag.entity.projectile.MagicBulletEntity;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModSpawnRules;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.BodyController;
import net.minecraft.entity.ai.controller.LookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.PhantomEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.network.NetworkHooks;

public class DyssomniaEntity extends FlyingEntity implements IMob, IModMob
{
	private static final DataParameter<Byte> ATTACK_PHASE = EntityDataManager.defineId(DyssomniaEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> ATTACKING_TIME = EntityDataManager.defineId(DyssomniaEntity.class, DataSerializers.INT);
	private static final DataParameter<Boolean> RETREATING = EntityDataManager.defineId(DyssomniaEntity.class, DataSerializers.BOOLEAN);
	private float xRotAnimation;
	private float xRotAnimationO;
	private int animationTick;
	private int animationTickO;

	public DyssomniaEntity(EntityType<? extends DyssomniaEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 25;
		this.moveControl = new DyssomniaEntity.MoveHelperController(this);
		this.lookControl = new DyssomniaEntity.LookHelperController(this);
	}

	@Override
	protected BodyController createBodyControl()
	{
		return new DyssomniaEntity.BodyHelperController(this);
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
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true).setUnseenMemoryTicks(180));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(ATTACK_PHASE, (byte)0);
		this.entityData.define(ATTACKING_TIME, -1);
		this.entityData.define(RETREATING, false);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MobEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 32.0D);
	}

	@Override
	public CreatureAttribute getMobType()
	{
		return CreatureAttribute.UNDEAD;
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
			float f = MathHelper.cos((float)(this.getId() * 3 + this.tickCount) * 0.13F + (float)Math.PI);
			float f1 = MathHelper.cos((float)(this.getId() * 3 + this.tickCount + 1) * 0.13F + (float)Math.PI);

			if (f > 0.0F && f1 <= 0.0F)
			{
				this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), SoundEvents.PHANTOM_FLAP, this.getSoundSource(), 0.95F + this.random.nextFloat() * 0.05F, 0.95F + this.random.nextFloat() * 0.05F, false);
			}

			boolean flag = this.isActuallyRetreating();

			for (int i = 0; i < 3; ++i)
			{
				float f2 = MathHelper.cos(this.yRot * ((float)Math.PI / 180.0F)) * ((float)i * 0.4F + 1.7F);
				float f3 = MathHelper.sin(this.yRot * ((float)Math.PI / 180.0F)) * ((float)i * 0.4F + 1.7F);
				float f4 = (0.4F + f * ((float)i * 0.05F + 0.5F));

				if (flag && this.tickCount % 12 == i * 3)
				{
					this.level.addParticle(ParticleTypes.FLAME, this.getX() + (double)f2, this.getY() + (double)f4, this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
					this.level.addParticle(ParticleTypes.FLAME, this.getX() - (double)f2, this.getY() + (double)f4, this.getZ() - (double)f3, 0.0D, 0.0D, 0.0D);
				}
				else
				{
					this.level.addParticle(ParticleTypes.MYCELIUM, this.getX() + (double)f2, this.getY() + (double)f4, this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
					this.level.addParticle(ParticleTypes.MYCELIUM, this.getX() - (double)f2, this.getY() + (double)f4, this.getZ() - (double)f3, 0.0D, 0.0D, 0.0D);
				}
			}

			if (this.getAttackingTime() >= 0)
			{
				if (this.getAttackPhase() == DyssomniaEntity.AttackPhase.SHOT)
				{
					double d0 = 1.75D;
					double d1 = 1.5D;
					Vector3d vector3d = this.getViewVector(1.0F);
					this.level.addParticle(ParticleTypes.SMOKE, this.getX() + vector3d.x * d0, this.getEyeY() - vector3d.y * d1, this.getZ() + vector3d.z * d0, 0.0D, 0.0D, 0.0D);
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

			this.xRotAnimation = MathHelper.clamp(ModUtils.rotlerp(this.xRotAnimation, this.xRot, 90.0F, false), -180.0F, 180.0F);
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
			float f = this.getBrightness();

			if (f <= 0.5F && this.random.nextFloat() * 30.0F < (0.6F - f) * 2.0F)
			{
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canBeAffected(EffectInstance potioneffectIn)
	{
		if (potioneffectIn.getEffect() == Effects.BLINDNESS)
		{
			PotionEvent.PotionApplicableEvent event = new PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == Event.Result.ALLOW;
		}

		return super.canBeAffected(potioneffectIn);
	}

	private void summonPhantom(ServerWorld serverworld, LivingEntity attacker, LivingEntity target, Random random, int count)
	{
		boolean flag = false;

		for (int i = 0; i < count; ++i)
		{
			double d1 = attacker.getX() + (random.nextDouble() - random.nextDouble()) * 3.0D;
			double d2 = attacker.getY() + 0.5D;
			double d3 = attacker.getZ() + (random.nextDouble() - random.nextDouble()) * 3.0D;

			PhantomEntity phantomentity = EntityType.PHANTOM.create(attacker.level);
			phantomentity.moveTo(d1, d2, d3, this.yRot + (random.nextFloat() - 0.5F) * 12.0F, 0.0F);
			phantomentity.finalizeSpawn(serverworld, attacker.level.getCurrentDifficultyAt(attacker.blockPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);
			serverworld.addFreshEntityWithPassengers(phantomentity);
			phantomentity.getPersistentData().putBoolean(ModUtils.WITH_SPAWN_PARTICLE_KEY, true);

			if (this.isOnFire())
			{
				phantomentity.setSecondsOnFire(this.getRemainingFireTicks() / 20 + 2);
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

	public static boolean checkDyssomniaSpawnRules(EntityType<DyssomniaEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return ModSpawnRules.checkMobSpawnInLightRules(type, worldIn, reason, pos, randomIn) && (reason == SpawnReason.SPAWNER || (worldIn.canSeeSky(pos) && randomIn.nextFloat() < worldIn.getMoonBrightness()));
	}

	public DyssomniaEntity.AttackPhase getAttackPhase()
	{
		return DyssomniaEntity.AttackPhase.byId((int)this.entityData.get(ATTACK_PHASE));
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
		return MathHelper.lerp(f, this.xRotAnimationO / 180.0F, this.xRotAnimation / 180.0F);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAttackAnimationScale(float f)
	{
		return MathHelper.lerp(f, (float)this.animationTickO / 30.0F, (float)this.animationTick / 30.0F);
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
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setRetreating(compound.getBoolean("isRetreating"));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
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
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return sizeIn.height * 0.45F;
	}

	@Override
	public SoundCategory getSoundSource()
	{
		return SoundCategory.HOSTILE;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.DYSSOMNIA_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
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
	public IPacket<?> getAddEntityPacket()
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

		private AttackPhase(int idIn)
		{
			this.id = idIn;
		}

		public int getId()
		{
			return this.id;
		}

		public static DyssomniaEntity.AttackPhase byId(int idIn)
		{
			if (idIn < 0 || idIn >= BY_ID.length)
			{
				idIn = 0;
			}

			return BY_ID[idIn];
		}
	}

	private static class SummonGoal extends Goal
	{
		private final DyssomniaEntity parent;
		public int attackTimer;
		private final EntityPredicate phantomCountTargeting = (new EntityPredicate()).range(32.0D).allowUnseeable().ignoreInvisibilityTesting().allowInvulnerable();

		public SummonGoal(DyssomniaEntity mob)
		{
			this.parent = mob;
		}

		@Override
		public boolean canUse()
		{
			if (!this.parent.isRetreating() && this.parent.getTarget() != null && this.parent.getTarget() instanceof PlayerEntity && this.parent.getAttackPhase() == DyssomniaEntity.AttackPhase.WAIT && (this.parent.getRandom().nextInt(6) == 0 || this.parent.isOnFire()) && this.parent.distanceToSqr(this.parent.getTarget()) < 16.0D * 16.0D)
			{
				return this.parent.level.getNearbyEntities(PhantomEntity.class, this.phantomCountTargeting, this.parent, this.parent.getBoundingBox().inflate(32.0D)).size() < 1;
			}
			else
			{
				return false;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			return this.parent.getTarget() != null && this.parent.getTarget() instanceof PlayerEntity && this.parent.getAttackPhase() == DyssomniaEntity.AttackPhase.SUMMON;
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

			if ((target.distanceToSqr(this.parent) < d0 * d0 || this.attackTimer > 10) && this.parent.canSee(target))
			{
				World world = this.parent.level;
				++this.attackTimer;

				if (this.attackTimer == 20)
				{
					this.parent.summonPhantom((ServerWorld)world, this.parent, target, this.parent.getRandom(), 1 + this.parent.getRandom().nextInt(this.parent.level.getDifficulty() == Difficulty.HARD ? 3 : 2));
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

			if ((target.distanceToSqr(this.parent) < d0 * d0 || this.attackTimer > 10) && this.parent.canSee(target))
			{
				World world = this.parent.level;
				++this.attackTimer;

				if (this.attackTimer == 30)
				{
					double d1 = 1.75D;
					double d2 = 1.5D;
					Vector3d vector3d = this.parent.getViewVector(1.0F);
					double d3 = target.getX() - (this.parent.getX() + vector3d.x * d1);
					double d4 = target.getY(0.5D) - (this.parent.getEyeY() - vector3d.y * d2);
					double d5 = target.getZ() - (this.parent.getZ() + vector3d.z * d1);

					if (!this.parent.isSilent())
					{
						world.levelEvent((PlayerEntity)null, 1024, this.parent.blockPosition(), 0);
					}

					float f = MathHelper.sqrt(d3 * d3 + d5 * d5) * 0.02F;
					MagicBulletEntity mugicbulletentity = new MagicBulletEntity(world, this.parent, d3 + this.parent.getRandom().nextGaussian() * (double)f, d4, d5 + this.parent.getRandom().nextGaussian() * (double)f);
					mugicbulletentity.setPos(this.parent.getX() + vector3d.x * d1, this.parent.getEyeY() - vector3d.y * d2, this.parent.getZ() + vector3d.z * d1);
					mugicbulletentity.setDamage(5.0F);
					mugicbulletentity.setEffectLevel((byte)1);
					mugicbulletentity.setVariant(1);
					world.addFreshEntity(mugicbulletentity);
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
				Vector3d vector3d = this.parent.getDeltaMovement().normalize();
				double d0 = vector3d.x;
				double d1 = vector3d.z;
				double d2 = Math.sqrt(d0 * d0 + d1 * d1);
				this.parent.yRot = -((float)MathHelper.atan2(d0, d1)) * (180.0F / (float)Math.PI);
				this.parent.yBodyRot = this.parent.yRot;
				this.parent.xRot = MathHelper.clamp((float)(MathHelper.atan2(vector3d.y, d2) * (180.0F / (float)Math.PI)), -45.0F, 45.0F);
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
					this.parent.yRot = -((float)MathHelper.atan2(d0, d2)) * (180.0F / (float)Math.PI);
					this.parent.yBodyRot = this.parent.yRot;
					this.parent.xRot = MathHelper.clamp(-(float)(MathHelper.atan2(this.parent.getY() - d1, d3) * (180.0F / (float)Math.PI)), -45.0F, 45.0F);
				}
			}
		}
	}

	protected class MoveHelperController extends MovementController
	{
		private final DyssomniaEntity parent;
		private int courseChangeCooldown;

		public MoveHelperController(DyssomniaEntity mob)
		{
			super(mob);
			this.parent = mob;
		}

		@Override
		public void tick()
		{
			if (this.operation == MovementController.Action.MOVE_TO)
			{
				if (this.courseChangeCooldown-- <= 0)
				{
					this.courseChangeCooldown += this.parent.getRandom().nextInt(5) + 5;
					Vector3d vector3d = new Vector3d(this.wantedX - this.parent.getX(), this.wantedY - this.parent.getY(), this.wantedZ - this.parent.getZ());
					double d0 = vector3d.length();
					vector3d = vector3d.normalize();

					if (ModUtils.canReach(this.parent, vector3d, MathHelper.ceil(d0)))
					{
						this.parent.setDeltaMovement(this.parent.getDeltaMovement().add(vector3d.scale(0.1D)));
					}
					else
					{
						this.operation = MovementController.Action.WAIT;
					}
				}
			}
		}
	}

	protected class BodyHelperController extends BodyController
	{
		public BodyHelperController(MobEntity mob)
		{
			super(mob);
		}

		@Override
		public void clientTick()
		{
			DyssomniaEntity.this.yHeadRot = DyssomniaEntity.this.yBodyRot;
			DyssomniaEntity.this.yBodyRot = DyssomniaEntity.this.yRot;
		}
	}

	protected class LookHelperController extends LookController
	{
		public LookHelperController(MobEntity mob)
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
			MovementController movementcontroller = this.parent.getMoveControl();

			if (!movementcontroller.hasWanted())
			{
				return true;
			}
			else
			{
				double d0 = movementcontroller.getWantedX() - this.parent.getX();
				double d1 = movementcontroller.getWantedY() - this.parent.getY();
				double d2 = movementcontroller.getWantedZ() - this.parent.getZ();
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
			Random random = this.parent.getRandom();
			BlockPos blockpos = new BlockPos(this.parent.getX(), (double)Math.round(this.parent.getY()), this.parent.getZ());
			boolean flag = this.parent.isActuallyRetreating();
			double d0 = this.parent.getX();
			double d1 = this.parent.getY();
			double d2 = this.parent.getZ();

			if (flag && this.parent.level.canSeeSky(blockpos) && blockpos.getY() < (this.parent.level.getMaxBuildHeight() + 64))
			{
				d0 = d0 + (double)((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
				d1 = d1 + (double)((random.nextFloat() * 4.0F) + 8.0F);
				d2 = d2 + (double)((random.nextFloat() * 2.0F - 1.0F) * 8.0F);
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
						Vector3d vector3d = target.getEyePosition(1.0F);

						float f = random.nextFloat() * 2.0F;
						float f1 = 4.0F + random.nextFloat() * 4.0F;
						d0 = vector3d.x + (double)(MathHelper.cos(f * (float)Math.PI) * f1);
						d1 = vector3d.y + 2.0D + random.nextDouble() * 2.0D;
						d2 = vector3d.z + (double)(MathHelper.sin(f * (float)Math.PI) * f1);
						flag1 = true;
					}
				}

				if (!flag1)
				{
					World world = this.parent.level;
					BlockPos.Mutable blockpos$mutable = blockpos.mutable();
					boolean flag2 = false;
					int i = 0;

					while (world.isEmptyBlock(blockpos$mutable) && blockpos$mutable.getY() > 0)
					{
						blockpos$mutable.move(Direction.DOWN);
						++i;

						if (i >= 16)
						{
							flag2 = true;
							break;
						}
					}

					d0 = d0 + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
					d1 = d1 + (double)(flag2 ? ((random.nextFloat() * -8.0F)) : ((random.nextFloat() * 2.0F - 1.0F) * 8.0F));
					d2 = d2 + (double)((random.nextFloat() * 2.0F - 1.0F) * 16.0F);
				}
			}

			this.parent.getMoveControl().setWantedPosition(d0, d1, d2, flag ? 0.75D : 0.25D);
		}
	}
}