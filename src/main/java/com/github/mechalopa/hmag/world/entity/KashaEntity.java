package com.github.mechalopa.hmag.world.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.OcelotAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.network.NetworkHooks;

public class KashaEntity extends Monster
{
	private static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.fromString("7DDA541D-ABAA-BBF4-D099-AAC57D64ADA9");
	private static final AttributeModifier ATTACK_DAMAGE_MODIFIER = new AttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "Soul variant attack damage bonus", 1.0D, AttributeModifier.Operation.ADDITION);
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(KashaEntity.class, EntityDataSerializers.INT);

	public KashaEntity(EntityType<? extends KashaEntity> type, Level level)
	{
		super(type, level);
		this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
		this.setPathfindingMalus(BlockPathTypes.LAVA, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
		this.xpReward = 12;
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, 0);
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.3F));
		this.goalSelector.addGoal(4, new OcelotAttackGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, LivingEntity.class, 10, true, false, (p) -> {
			return p.getType().is(ModTags.KASHA_TARGETS) && !(p.getType().is(ModTags.KASHA_TARGETS_BABY_ONLY) && !p.isBaby());
		}));
		if (ModConfigs.cachedServer.KASHA_ATTACK_VILLAGERS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		if (ModConfigs.cachedServer.KASHA_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, false, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.325D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1.0D)
				.add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 2.0D);
	}

	@Override
	public void aiStep()
	{
		if (this.level.isClientSide)
		{
			if (this.tickCount % 10 == 0)
			{
				this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY() + 0.25D, this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}

			if (this.tickCount % 2 == 0)
			{
				this.level.addParticle(this.getVariant() == KashaEntity.Variant.SOUL ? ParticleTypes.SOUL_FIRE_FLAME : ParticleTypes.FLAME, this.getRandomX(0.75D), this.getRandomY() - 0.1D, this.getRandomZ(0.75D), 0.0D, 0.0D, 0.0D);
			}
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
					i = 5;
				}
				else if (this.level.getDifficulty() == Difficulty.HARD)
				{
					i = 10;
				}

				if (i > 0)
				{
					entity.setSecondsOnFire(i);
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
	public boolean isSensitiveToWater()
	{
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag)
	{
		spawnData = super.finalizeSpawn(levelAccessor, difficulty, spawnType, spawnData, dataTag);

		if (this.level.getBlockState(this.blockPosition().below()).is(BlockTags.SOUL_FIRE_BASE_BLOCKS))
		{
			this.setVariant(KashaEntity.Variant.SOUL);
		}
		else
		{
			this.setVariant(KashaEntity.Variant.NORMAL);
		}

		return spawnData;
	}

	public KashaEntity.Variant getVariant()
	{
		return KashaEntity.Variant.byId(this.entityData.get(DATA_VARIANT_ID));
	}

	private void setVariant(KashaEntity.Variant variant)
	{
		if (!this.level.isClientSide)
		{
			this.getAttribute(Attributes.ATTACK_DAMAGE).removeModifier(ATTACK_DAMAGE_MODIFIER);

			if (variant == KashaEntity.Variant.SOUL)
			{
				this.getAttribute(Attributes.ATTACK_DAMAGE).addPermanentModifier(ATTACK_DAMAGE_MODIFIER);
			}
		}

		this.entityData.set(DATA_VARIANT_ID, variant.getId());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(KashaEntity.Variant.byId(compound.getInt("Variant")));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant().getId());
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 3;
	}

	@Override
	public float getLightLevelDependentMagicValue()
	{
		return 1.0F;
	}

	@Override
	public double getMyRidingOffset()
	{
		return 0.14D;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.KASHA_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return ModSoundEvents.KASHA_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.KASHA_DEATH.get();
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum Variant
	{
		NORMAL(0),
		SOUL(1);

		private final int id;
		private static final KashaEntity.Variant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(KashaEntity.Variant::getId)).toArray((p) -> {
			return new KashaEntity.Variant[p];
		});

		private Variant(int idIn)
		{
			this.id = idIn;
		}

		public int getId()
		{
			return this.id;
		}

		public static KashaEntity.Variant byId(int idIn)
		{
			if (idIn < 0 || idIn >= BY_ID.length)
			{
				idIn = 0;
			}

			return BY_ID[idIn];
		}
	}
}