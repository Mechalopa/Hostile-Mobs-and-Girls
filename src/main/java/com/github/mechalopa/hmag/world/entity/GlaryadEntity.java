package com.github.mechalopa.hmag.world.entity;

import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class GlaryadEntity extends Monster implements NeutralMob, IModMob
{
	private static final UniformInt FIRST_ANGER_SOUND_DELAY = TimeUtil.rangeOfSeconds(0, 1);
	private int playFirstAngerSoundIn;
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private int remainingPersistentAngerTime;
	@Nullable
	private UUID persistentAngerTarget;
	private static final UniformInt ALERT_INTERVAL = TimeUtil.rangeOfSeconds(4, 6);
	private int ticksUntilNextAlert;

	public GlaryadEntity(EntityType<? extends GlaryadEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.xpReward = 12;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(4, new GlaryadEntity.GlaryadTemptGoal(this, 1.0D, Ingredient.of(ModTags.GLARYAD_TEMPT_ITEMS)));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true, this::isAngryAt));
		this.targetSelector.addGoal(3, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.23D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ARMOR, 2.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
	}

	@Override
	protected void customServerAiStep()
	{
		if (this.isAngry())
		{
			this.maybePlayFirstAngerSound();
		}

		this.updatePersistentAnger((ServerLevel)this.level, true);

		if (this.getTarget() != null)
		{
			this.maybeAlertOthers();
		}

		if (this.isAngry())
		{
			this.lastHurtByPlayerTime = this.tickCount;
		}

		super.customServerAiStep();

		if (this.isAlive() && ModConfigs.cachedServer.GLARYAD_REGEN)
		{
			if (this.isInWaterRainOrBubble() && !this.hasEffect(MobEffects.REGENERATION))
			{
				this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 2));
			}
		}
	}

	@Override
	protected void updateNoActionTime()
	{
		float f = this.getLightLevelDependentMagicValue();

		if (f <= 0.5F)
		{
			this.noActionTime += 2;
		}
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader levelReader)
	{
		return levelReader.getPathfindingCostFromLightLevels(pos) - 0.5F;
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		if (super.doHurtTarget(entityIn))
		{
			if (entityIn instanceof LivingEntity)
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
					((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i * 20, 0));
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	private void maybePlayFirstAngerSound()
	{
		if (this.playFirstAngerSoundIn > 0)
		{
			--this.playFirstAngerSoundIn;

			if (this.playFirstAngerSoundIn == 0)
			{
				this.playAngerSound();
				this.level.broadcastEntityEvent(this, (byte)13);
			}
		}
	}

	private void maybeAlertOthers()
	{
		if (this.ticksUntilNextAlert > 0)
		{
			--this.ticksUntilNextAlert;
		}
		else
		{
			if (this.getSensing().hasLineOfSight(this.getTarget()))
			{
				this.alertOthers();
			}

			this.ticksUntilNextAlert = ALERT_INTERVAL.sample(this.random);
		}
	}

	private void alertOthers()
	{
		double d0 = this.getAttributeValue(Attributes.FOLLOW_RANGE);
		AABB aabb = AABB.unitCubeFromLowerCorner(this.position()).inflate(d0, 10.0D, d0);
		this.level.getEntitiesOfClass(GlaryadEntity.class, aabb, EntitySelector.NO_SPECTATORS).stream().filter((p) -> {
			return p != this;
		}).filter((p) -> {
			return p.getTarget() == null;
		}).filter((p) -> {
			return !p.isAlliedTo(this.getTarget());
		}).forEach((p) -> {
			p.setTarget(this.getTarget());
		});
	}

	private void playAngerSound()
	{
		this.playSound(SoundEvents.AZALEA_BREAK, this.getSoundVolume(), this.getVoicePitch());
	}

	@Override
	public void setTarget(@Nullable LivingEntity livingEntity)
	{
		if (this.getTarget() == null && livingEntity != null)
		{
			this.playFirstAngerSoundIn = FIRST_ANGER_SOUND_DELAY.sample(this.random);
			this.ticksUntilNextAlert = ALERT_INTERVAL.sample(this.random);
		}

		if (livingEntity instanceof Player)
		{
			this.setLastHurtByPlayer((Player)livingEntity);
		}

		super.setTarget(livingEntity);
	}

	@Override
	public int getRemainingPersistentAngerTime()
	{
		return this.remainingPersistentAngerTime;
	}

	@Override
	public void setRemainingPersistentAngerTime(int time)
	{
		this.remainingPersistentAngerTime = time;
	}

	@Override
	@Nullable
	public UUID getPersistentAngerTarget()
	{
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(UUID uuid)
	{
		this.persistentAngerTarget = uuid;
	}

	@Override
	public void startPersistentAngerTimer()
	{
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
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
	public boolean isPreventingPlayerRest(Player player)
	{
		return this.isAngryAt(player);
	}

	@Override
	public boolean checkSpawnRules(LevelAccessor levelAccessor, MobSpawnType spawnType)
	{
		return true;
	}

	public static boolean checkGlaryadSpawnRules(EntityType<? extends GlaryadEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random)
	{
		return Monster.checkAnyLightMonsterSpawnRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.getBlockState(pos.below()).is(ModTags.GLARYADS_SPAWNABLE_ON));
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void handleEntityEvent(byte id)
	{
		if (id == 13)
		{
			for (int i = 0; i < 5; ++i)
			{
				double d0 = this.random.nextGaussian() * 0.02D;
				double d1 = this.random.nextGaussian() * 0.02D;
				double d2 = this.random.nextGaussian() * 0.02D;
				this.level.addParticle(ParticleTypes.ANGRY_VILLAGER, this.getRandomX(1.0D), this.getRandomY() + 1.0D, this.getRandomZ(1.0D), d0, d1, d2);
			}
		}
		else
		{
			super.handleEntityEvent(id);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.readPersistentAngerSaveData(this.level, compound);
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
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
	{
		return 1.74F;
	}

	@SuppressWarnings("deprecation")
	@Override
	public float getLightLevelDependentMagicValue()
	{
		return Mth.clamp(super.getLightLevelDependentMagicValue() + 0.4F, 0.0F, 1.0F);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GIRL_MOB_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.GIRL_MOB_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GIRL_MOB_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class GlaryadTemptGoal extends TemptGoal
	{
		public GlaryadTemptGoal(GlaryadEntity mob, double speed, Ingredient ingredient)
		{
			super(mob, speed, ingredient, false);
		}

		@Override
		public boolean canUse()
		{
			return !GlaryadEntity.this.isAngry() && super.canUse();
		}

		@Override
		public boolean canContinueToUse()
		{
			return !GlaryadEntity.this.isAngry() && super.canContinueToUse();
		}
	}
}