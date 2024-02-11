package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.util.ModUtils;
import com.github.mechalopa.hmag.world.entity.ai.goal.MeleeAttackGoal2;

import net.minecraft.core.BlockPos;
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
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.CaveSpider;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.network.NetworkHooks;

public class SpiderNestEntity extends Monster
{
	private static final EntityDataAccessor<Integer> SUMMON_DELAY = SynchedEntityData.defineId(SpiderNestEntity.class, EntityDataSerializers.INT);

	public SpiderNestEntity(EntityType<? extends SpiderNestEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(4, new SpiderNestEntity.MeleeAttackAndSummonGoal(this, 1.0D, false, 120, 10.0F, 2));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(SUMMON_DELAY, 60);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.175D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
	}

	@Override
	public MobType getMobType()
	{
		return MobType.ARTHROPOD;
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide)
		{
			this.level.addParticle(ParticleTypes.ASH, this.getRandomX(0.75D), this.getRandomY() - 0.25D, this.getRandomZ(0.75D), (this.getRandom().nextDouble() - 0.5D) * 3.0D, -this.getRandom().nextDouble(), (this.getRandom().nextDouble() - 0.5D) * 3.0D);
		}

		super.aiStep();
	}

	@Override
	public boolean doHurtTarget(Entity entity)
	{
		if (super.doHurtTarget(entity))
		{
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
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.POISON, i * 20, 0));
					((LivingEntity)entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, i * 20, 1));
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
	public void remove(Entity.RemovalReason reason)
	{
		if (!this.level.isClientSide && ModConfigs.cachedServer.SPIDER_NEST_SUMMON_CAVE_SPIDER && !this.isNoAi() && this.isDeadOrDying())
		{
			this.summonSpider((ServerLevel)this.level, this, this.getTarget(), this.getRandom(), 2 + this.getRandom().nextInt(3));
		}

		super.remove(reason);
	}

	@Override
	public void makeStuckInBlock(BlockState state, Vec3 stuckSpeedMultiplier)
	{
		if (!state.is(Blocks.COBWEB))
		{
			super.makeStuckInBlock(state, stuckSpeedMultiplier);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.is(ModTags.DamageTypeTags.SPIDER_NEST_VULNERABLE_TO))
		{
			amount = amount * 1.5F;
		}

		if (source.is(ModTags.DamageTypeTags.SPIDER_NEST_RESISTANT_TO))
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean canBeAffected(MobEffectInstance potioneffect)
	{
		if (ModTags.checkTagContains(potioneffect.getEffect(), ModTags.MobEffectTags.SPIDER_NEST_IMMUNE_TO))
		{
			MobEffectEvent.Applicable event = new MobEffectEvent.Applicable(this, potioneffect);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == Event.Result.ALLOW;
		}

		return super.canBeAffected(potioneffect);
	}

	private void summonSpider(ServerLevel serverlevel, LivingEntity attacker, LivingEntity target, RandomSource random, int count)
	{
		for (int i = 0; i < count; ++i)
		{
			double d1 = attacker.getX() + (random.nextDouble() - random.nextDouble()) * 1.5D;
			double d2 = attacker.getY() + 0.5D;
			double d3 = attacker.getZ() + (random.nextDouble() - random.nextDouble()) * 1.5D;

			CaveSpider cavespider = EntityType.CAVE_SPIDER.create(attacker.level);
			cavespider.moveTo(d1, d2, d3, random.nextFloat() * 360.0F, 0.0F);
			cavespider.finalizeSpawn(serverlevel, attacker.level.getCurrentDifficultyAt(attacker.blockPosition()), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
			serverlevel.addFreshEntityWithPassengers(cavespider);

			if (target != null && target.isAlive())
			{
				cavespider.setTarget(target);
			}

			cavespider.getPersistentData().putBoolean(ModUtils.WITH_SPAWN_PARTICLE_KEY, true);
			attacker.level.gameEvent(cavespider, GameEvent.ENTITY_PLACE, cavespider.blockPosition());
		}

		attacker.level.levelEvent(2004, attacker.blockPosition(), 0);
		attacker.playSound(ModSoundEvents.SPIDER_NEST_SUMMON.get(), 1.0F, 1.0F);
	}

	public static boolean checkSpiderNestSpawnRules(EntityType<? extends SpiderNestEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random)
	{
		return Monster.checkMonsterSpawnRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || pos.getY() <= ModConfigs.cachedServer.SPIDER_NEST_SPAWN_MAX_HEIGHT);
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 2;
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size)
	{
		return 1.1F;
	}

	@Override
	public int getMaxHeadXRot()
	{
		return 30;
	}

	@Override
	public int getMaxHeadYRot()
	{
		return 30;
	}

	public boolean isCharging()
	{
		return this.getSummonDelay() <= 20;
	}

	public int getSummonDelay()
	{
		return this.entityData.get(SUMMON_DELAY);
	}

	private void setSummonDelay(int value)
	{
		this.entityData.set(SUMMON_DELAY, value);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Delay", this.getSummonDelay());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setSummonDelay(compound.getShort("Delay"));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.SPIDER_NEST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.SPIDER_NEST_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.SPIDER_NEST_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class MeleeAttackAndSummonGoal extends MeleeAttackGoal2
	{
		private final int summonInterval;
		private final float summonRadius;
		private final int maxSpawn;
		private final TargetingConditions caveSpiderCountTargeting = TargetingConditions.forNonCombat().range(12.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

		public MeleeAttackAndSummonGoal(SpiderNestEntity mob, double speedIn, boolean useLongMemory, int intervalIn, float radiusIn, int maxSpawnIn)
		{
			super(mob, speedIn, useLongMemory, 0.625F);
			this.summonInterval = intervalIn;
			this.summonRadius = radiusIn;
			this.maxSpawn = maxSpawnIn;
		}

		@Override
		public void stop()
		{
			super.stop();
			this.resetTimer(SpiderNestEntity.this);
		}

		@Override
		public void tick()
		{
			if (ModConfigs.cachedServer.SPIDER_NEST_SUMMON_CAVE_SPIDER)
			{
				SpiderNestEntity attacker = SpiderNestEntity.this;
				LivingEntity target = attacker.getTarget();
				double d0 = attacker.distanceToSqr(target.getX(), target.getY(), target.getZ());

				if (d0 <= this.summonRadius * this.summonRadius && attacker.hasLineOfSight(target))
				{
					if (attacker.getSummonDelay() == -1)
					{
						this.resetTimer(attacker);
					}

					if (attacker.getSummonDelay() > 0)
					{
						attacker.setSummonDelay(attacker.getSummonDelay() - 1);
					}
					else
					{
						if (attacker.level.getNearbyEntities(CaveSpider.class, this.caveSpiderCountTargeting, attacker, attacker.getBoundingBox().inflate(12.0D)).size() >= 6)
						{
							attacker.setSummonDelay(20);
						}
						else
						{
							attacker.summonSpider((ServerLevel)attacker.level, attacker, target, attacker.getRandom(), 1 + attacker.getRandom().nextInt(this.maxSpawn));
							this.resetTimer(attacker);
						}
					}
				}
			}

			super.tick();
		}

		private void resetTimer(SpiderNestEntity attackerIn)
		{
			attackerIn.setSummonDelay(this.summonInterval + attackerIn.getRandom().nextInt(this.summonInterval / 2));
		}
	}
}