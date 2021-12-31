package hmag.entity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.UUID;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import hmag.ModConfigs;
import hmag.registry.ModSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.OcelotAttackGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.HoglinEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class KashaEntity extends MonsterEntity implements IModMob
{
	private static final UUID ATTACK_DAMAGE_MODIFIER_UUID = UUID.fromString("7DDA541D-ABAA-BBF4-D099-AAC57D64ADA9");
	private static final AttributeModifier ATTACK_DAMAGE_MODIFIER = new AttributeModifier(ATTACK_DAMAGE_MODIFIER_UUID, "Soul variant attack damage bonus", 1.0D, AttributeModifier.Operation.ADDITION);
	private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(KashaEntity.class, DataSerializers.INT);

	public KashaEntity(EntityType<? extends KashaEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
		this.setPathfindingMalus(PathNodeType.LAVA, 8.0F);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
		this.maxUpStep = 2.0F;
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
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(3, new LeapAtTargetGoal(this, 0.3F));
		this.goalSelector.addGoal(4, new OcelotAttackGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.8D, 0.0F));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		if (ModConfigs.cachedServer.KASHA_ATTACK_CHICKENS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ChickenEntity.class, false));
		if (ModConfigs.cachedServer.KASHA_ATTACK_BABY_HOGLINS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, HoglinEntity.class, 10, false, false, (p) -> {
				return p.isBaby();
			}));
		if (ModConfigs.cachedServer.KASHA_ATTACK_VILLAGERS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		if (ModConfigs.cachedServer.KASHA_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, false, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 40.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.325D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 1.0D);
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
	public boolean causeFallDamage(float distance, float damageMultiplier)
	{
		return false;
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
					i = 5;
				}
				else if (this.level.getDifficulty() == Difficulty.HARD)
				{
					i = 10;
				}

				if (i > 0)
				{
					entityIn.setSecondsOnFire(i);
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

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		if (this.level.getBlockState(this.blockPosition().below()).is(BlockTags.SOUL_FIRE_BASE_BLOCKS))
		{
			this.setVariant(KashaEntity.Variant.SOUL);
		}
		else
		{
			this.setVariant(KashaEntity.Variant.NORMAL);
		}

		return spawnDataIn;
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
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(KashaEntity.Variant.byId(compound.getInt("Variant")));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
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
	public float getBrightness()
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
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
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
	public IPacket<?> getAddEntityPacket()
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