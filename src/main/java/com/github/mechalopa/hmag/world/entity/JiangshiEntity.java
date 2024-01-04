package com.github.mechalopa.hmag.world.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModUtils;
import com.github.mechalopa.hmag.world.entity.ai.goal.LeapAtTargetGoal2;
import com.github.mechalopa.hmag.world.entity.ai.goal.MeleeAttackGoal2;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class JiangshiEntity extends Monster
{
	private static final UUID SPEED_MODIFIER_BY_DAMAGE_UUID = UUID.fromString("A25E5B12-7881-3AE6-D9F5-418CB7D9E02E");
	private static final EntityDataAccessor<Integer> DATA_SPEED_BONUS = SynchedEntityData.defineId(JiangshiEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(JiangshiEntity.class, EntityDataSerializers.INT);
	public static final int SPEED_BONUS_MAX = 7;
	private int animationTick;
	private int animationTickO;

	public JiangshiEntity(EntityType<? extends JiangshiEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 12;
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_SPEED_BONUS, 0);
		this.entityData.define(DATA_VARIANT_ID, 0);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(2, new JiangshiEntity.LeapGoal(this));
		this.goalSelector.addGoal(3, new MeleeAttackGoal2(this, 1.0D, false).useRaiseArm());
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true).setUnseenMemoryTicks(120));
		if (ModConfigs.cachedServer.JIANGSHI_ATTACK_VILLAGERS)
		{
			this.goalSelector.addGoal(4, new MoveThroughVillageGoal(this, 1.0D, true, 4, () -> false));
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		}
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		if (ModConfigs.cachedServer.JIANGSHI_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.19D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 0.5D)
				.add(Attributes.ARMOR, 4.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 24.0D);
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
			this.animationTickO = this.animationTick;
		}

		super.tick();

		if (this.level.isClientSide)
		{
			if (!this.isOnGround() && !this.isInWaterOrBubble() && !this.isPassenger())
			{
				if (this.animationTick < 10)
				{
					this.animationTick = Math.min(this.animationTick + 1, 10);
				}
			}
			else if (this.animationTick > 0)
			{
				this.animationTick = Math.max(this.animationTick - 5, 0);
			}
		}
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide)
		{
			if (this.tickCount % (Math.max(8 - this.getSpeedBonus(), 1)) == 0)
			{
				this.level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, this.getRandomX(0.75D), this.getRandomY() - 0.25D, this.getRandomZ(0.75D), 0.0D, 0.0D, 0.0D);
			}
		}
		else
		{
			if (this.isOnFire() || !(this.getTarget() != null && this.getTarget().isAlive() && this.hasLineOfSight(this.getTarget()) && this.distanceToSqr(this.getTarget()) <= 8.0D * 8.0D))
			{
				if (this.getSpeedBonus() > 0 && this.tickCount % 20 == 0 && this.getRandom().nextInt(4) == 0)
				{
					this.setSpeedBonus(this.getSpeedBonus() - 1);
				}
			}
		}

		ModUtils.burnInDay(this, this.getRandom(), this.isSunBurnTick());
		super.aiStep();
	}

	@Override
	public boolean doHurtTarget(Entity entity)
	{
		if (super.doHurtTarget(entity))
		{
			ModUtils.catchFire(this, entity, this.getRandom());

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

		if (!super.hurt(source, amount))
		{
			return false;
		}
		else if (this.isNoAi() || source.isNoAggro() || source.isFire() || amount <= 0.0F || !(source.getEntity() != null && source.getEntity() instanceof LivingEntity))
		{
			return true;
		}
		else
		{
			this.setSpeedBonus(this.getSpeedBonus() + 1);
			return true;
		}
	}

	@Override
	public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt)
	{
		super.thunderHit(serverLevel, lightningBolt);

		if (!this.isNoAi())
		{
			this.setSpeedBonus(0);
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag)
	{
		spawnData = super.finalizeSpawn(levelAccessor, difficulty, spawnType, spawnData, dataTag);
		RandomSource randomsource = levelAccessor.getRandom();
		this.setVariant(randomsource.nextInt(4) == 0 ? 1 : 0);

		if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty())
		{
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);

			if (j == 10 && i == 31 && randomsource.nextFloat() < 0.25F)
			{
				this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(randomsource.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
			}
		}

		return spawnData;
	}

	public int getSpeedBonus()
	{
		return this.entityData.get(DATA_SPEED_BONUS);
	}

	private void setSpeedBonus(int value)
	{
		value = Mth.clamp(value, 0, SPEED_BONUS_MAX);

		if (value != this.getSpeedBonus())
		{
			if (!this.level.isClientSide)
			{
				this.getAttribute(Attributes.MOVEMENT_SPEED).removeModifier(SPEED_MODIFIER_BY_DAMAGE_UUID);

				if (value > 0)
				{
					this.getAttribute(Attributes.MOVEMENT_SPEED).addPermanentModifier(new AttributeModifier(SPEED_MODIFIER_BY_DAMAGE_UUID, "Speed bonus by damage", 0.6D * ((double)value / (double)SPEED_BONUS_MAX), AttributeModifier.Operation.MULTIPLY_TOTAL));
				}
			}

			this.entityData.set(DATA_SPEED_BONUS, value);
		}
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	private void setVariant(int type)
	{
		if (type < 0 || type >= 2)
		{
			type = 0;
		}

		this.entityData.set(DATA_VARIANT_ID, type);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setSpeedBonus(compound.getInt("SpeedBonus"));
		this.setVariant(compound.getInt("Variant"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("SpeedBonus", this.getSpeedBonus());
		compound.putInt("Variant", this.getVariant());
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

	@OnlyIn(Dist.CLIENT)
	public float getAnimationScale(float f)
	{
		return Mth.lerp(f, this.animationTickO / 10.0F, this.animationTick / 10.0F);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.JIANGSHI_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.JIANGSHI_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.JIANGSHI_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.HUSK_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class LeapGoal extends LeapAtTargetGoal2
	{
		private final JiangshiEntity mob;

		public LeapGoal(JiangshiEntity mob)
		{
			super(mob, 0.4F, 0.2F, 8.0F, 12);
			this.mob = mob;
		}

		@Override
		public boolean canUse()
		{
			return super.canUse() && this.mob.hasLineOfSight(this.mob.getTarget());
		}

		@Override
		public void start()
		{
			super.start();
			this.mob.playSound(ModSoundEvents.JIANGSHI_JUMP.get(), 0.8F, 1.0F);
		}

		@Override
		public float getMaxAttackDistance()
		{
			return super.getMaxAttackDistance() - 3.0F * ((float)mob.getSpeedBonus() / (float)JiangshiEntity.SPEED_BONUS_MAX);
		}

		@Override
		public double getXZD()
		{
			return super.getXZD() + 0.3D * ((double)mob.getSpeedBonus() / (double)JiangshiEntity.SPEED_BONUS_MAX);
		}
	}
}