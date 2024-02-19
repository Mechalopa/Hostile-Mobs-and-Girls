package com.github.mechalopa.hmag.world.entity;

import java.util.Arrays;
import java.util.function.IntFunction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.registry.ModEntityTypes;
import com.github.mechalopa.hmag.util.ModTags;
import com.github.mechalopa.hmag.world.entity.ai.goal.LeapAtTargetGoal2;
import com.mojang.serialization.Codec;

import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.VariantHolder;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;

public class SlimeGirlEntity extends Monster implements VariantHolder<SlimeGirlEntity.ColorVariant>
{
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(SlimeGirlEntity.class, EntityDataSerializers.INT);
	public float targetSquish;
	public float squish;
	public float oSquish;
	private boolean wasOnGround;

	public SlimeGirlEntity(EntityType<? extends SlimeGirlEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 15;
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, SlimeGirlEntity.ColorVariant.PINK_1.getId());
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(3, new SlimeGirlEntity.LeapGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.19D)
				.add(Attributes.ATTACK_DAMAGE, 7.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
	}

	@Override
	public void tick()
	{
		this.squish += (this.targetSquish - this.squish) * 0.5F;
		this.oSquish = this.squish;

		super.tick();

		boolean flag = false;

		if (this.onGround() && !this.wasOnGround)
		{
			if (this.level().isClientSide())
			{
				this.spawnParticle(12);
			}

			this.targetSquish = -0.5F;
			flag = true;
		}
		else if (!this.onGround() && this.wasOnGround)
		{
			this.targetSquish = 1.0F;
		}

		if (this.level().isClientSide() && !flag && this.tickCount % 8 == 0 && this.getRandom().nextInt(3) == 0)
		{
			this.spawnParticle(1);
		}

		this.wasOnGround = this.onGround();
		this.targetSquish *= 0.6F;
	}

	@OnlyIn(Dist.CLIENT)
	private void spawnParticle(int count)
	{
		for (int j = 0; j < count; ++j)
		{
			float f = this.getRandom().nextFloat() * ((float)Math.PI * 2.0F);
			float f1 = this.getRandom().nextFloat() * 0.5F + 0.5F;
			float f2 = Mth.sin(f) * 0.6F * f1;
			float f3 = Mth.cos(f) * 0.6F * f1;
			this.level().addParticle(ParticleTypes.WITCH, this.getX() + f2, this.getY(), this.getZ() + f3, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void customServerAiStep()
	{
		super.customServerAiStep();

		if (this.isAlive() && ModConfigs.cachedServer.SLIME_GIRL_REGEN)
		{
			if (this.isInWaterRainOrBubble() && !this.hasEffect(MobEffects.REGENERATION))
			{
				this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 2));
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entity)
	{
		if (super.doHurtTarget(entity))
		{
			this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);

			if (entity instanceof LivingEntity)
			{
				int i = 0;

				if (this.level().getDifficulty() == Difficulty.NORMAL)
				{
					i = 7;
				}
				else if (this.level().getDifficulty() == Difficulty.HARD)
				{
					i = 15;
				}

				if (i > 0)
				{
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
		if (!this.level().isClientSide() && ModConfigs.cachedServer.MAGICAL_SLIME_SPAWNS_ON_SLIME_GIRL_DEATH && this.isDeadOrDying())
		{
			Component component = this.getCustomName();
			boolean flag = this.isNoAi();
			int i = 2;
			float f = i / 4.0F;
			int j = i / 2;
			int k = 2 + this.getRandom().nextInt(3);

			for (int l = 0; l < k; ++l)
			{
				float f1 = (l % 2 - 0.5F) * f;
				float f2 = (l / 2 - 0.5F) * f;
				MagicalSlimeEntity slime = ModEntityTypes.MAGICAL_SLIME.get().create(this.level());

				if (this.isPersistenceRequired())
				{
					slime.setPersistenceRequired();
				}

				slime.setCustomName(component);
				slime.setNoAi(flag);
				slime.setInvulnerable(this.isInvulnerable());
				slime.setSize(j, true);

				if (this.getRandom().nextInt(8) != 0)
				{
					slime.setVariant(this.getVariant());
				}

				slime.moveTo(this.getX() + f1, this.getY() + 0.5D, this.getZ() + f2, this.getRandom().nextFloat() * 360.0F, 0.0F);
				this.level().addFreshEntity(slime);
			}
		}

		super.remove(reason);
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.is(ModTags.DamageTypeTags.SLIME_GIRL_RESISTANT_TO))
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@SuppressWarnings("deprecation")
	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor levelAccessor, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag dataTag)
	{
		spawnData = super.finalizeSpawn(levelAccessor, difficulty, spawnType, spawnData, dataTag);
		this.setVariant(SlimeGirlEntity.ColorVariant.getSpawnVariant(levelAccessor.getRandom()));
		return spawnData;
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
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions size)
	{
		return 1.74F;
	}

	@OnlyIn(Dist.CLIENT)
	public float[] getColors()
	{
		return this.getVariant().getColors();
	}

	@Override
	public SlimeGirlEntity.ColorVariant getVariant()
	{
		return SlimeGirlEntity.ColorVariant.byId(this.entityData.get(DATA_VARIANT_ID));
	}

	@Override
	public void setVariant(SlimeGirlEntity.ColorVariant variant)
	{
		this.entityData.set(DATA_VARIANT_ID, variant.getId());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(SlimeGirlEntity.ColorVariant.byId(compound.getInt("Variant")));
		this.wasOnGround = compound.getBoolean("wasOnGround");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant().getId());
		compound.putBoolean("wasOnGround", this.wasOnGround);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.SLIME_SQUISH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSource)
	{
		return SoundEvents.SLIME_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.SLIME_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState block)
	{
		this.playSound(SoundEvents.SLIME_BLOCK_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	public static enum ColorVariant implements StringRepresentable
	{
		PINK_1(0, "pink_1", 16678066),
		PINK_2(1, "pink_2", 16608633),
		PINK_3(2, "pink_3", 16143733),
		PINK_4(3, "pink_4", 16732339),
		LIGHT_PINK_1(4, "light_pink_1", 16225469),
		LIGHT_PINK_2(5, "light_pink_2", 16751005),
		DARK_PINK_1(6, "dark_pink_1", 16005289),
		DARK_PINK_2(7, "dark_pink_2", 14830776),
		PURPLE_1(8, "purple_1", 13393626),
		PURPLE_2(9, "purple_2", 13269976),
		LAVENDER(10, "lavender", 12360685),
		WHITE_1(11, "white_1", 16766945),
		RED_1(12, "red_1", 16216690),
		RED_2(13, "red_2", 16605268),
		SLIME(14, "slime", 9489279),
		MINT(15, "mint", 7077059),
		BLACK(16, "black", 3946552),
		SLATEBLUE(17, "slateblue", 6970061),
		WHITE_2(18, "white_2", 16768685),
		DARK_RED_1(19, "dark_red_1", 9109504),
		DARK_RED_2(20, "dark_red_2", 14423100),
		ORANGE(21, "orange", 16747520),
		BROWN(22, "brown", 9127187),
		GOLD(23, "gold", 16766720),
		DARK_KHAKI(24, "dark_khaki", 12433259),
		GREEN_YELLOW(25, "green_yellow", 11403055),
		GREEN_1(26, "green_1", 7048739),
		GREEN_2(27, "green_2", 3050327),
		GREEN_3(28, "green_3", 6452328),
		BLUE(29, "blue", 8900331),
		TURQUOISE(30, "turquoise", 4251856),
		GRAY(31, "gray", 7833753);

		private static final IntFunction<SlimeGirlEntity.ColorVariant> BY_ID = ByIdMap.continuous(SlimeGirlEntity.ColorVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
		public static final Codec<SlimeGirlEntity.ColorVariant> CODEC = StringRepresentable.fromEnum(SlimeGirlEntity.ColorVariant::values);
		private final int id;
		private final String name;
		private final float[] colors;

		private ColorVariant(int id, String name, int color)
		{
			this.id = id;
			this.name = name;
			int i = (color & 16711680) >> 16;
			int j = (color & '\uff00') >> 8;
			int k = (color & 255) >> 0;
			this.colors = new float[]{i / 255.0F, j / 255.0F, k / 255.0F};
		}

		@Override
		public String getSerializedName()
		{
			return this.name;
		}

		public int getId()
		{
			return this.id;
		}

		public float[] getColors()
		{
			return this.colors;
		}

		public static SlimeGirlEntity.ColorVariant byId(int id)
		{
			return BY_ID.apply(id);
		}

		public static SlimeGirlEntity.ColorVariant getSpawnVariant(RandomSource random)
		{
			SlimeGirlEntity.ColorVariant[] variants = Arrays.stream(values()).toArray(p -> {
				return new SlimeGirlEntity.ColorVariant[p];
			});
			return Util.getRandom(variants, random);
		}
	}

	private class LeapGoal extends LeapAtTargetGoal2
	{
		private final Mob mob;

		public LeapGoal(Mob mob)
		{
			super(mob, 0.25F, 0.3F, 6.0F, 6);
			this.mob = mob;
		}

		@Override
		public void start()
		{
			super.start();
			this.mob.playSound(SoundEvents.SLIME_JUMP, 1.0F, ((this.mob.getRandom().nextFloat() - this.mob.getRandom().nextFloat()) * 0.2F + 1.0F) * 0.8F);
		}
	}
}