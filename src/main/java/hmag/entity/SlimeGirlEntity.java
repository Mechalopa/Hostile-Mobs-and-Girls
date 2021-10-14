package hmag.entity;

import java.util.Arrays;
import java.util.Comparator;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import hmag.ModConfigs;
import hmag.entity.goal.LeapAtTargetGoal2;
import hmag.registry.ModEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class SlimeGirlEntity extends MonsterEntity implements IModMob
{
	private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(SlimeGirlEntity.class, DataSerializers.INT);
	public float targetSquish;
	public float squish;
	public float oSquish;
	private boolean wasOnGround;

	public SlimeGirlEntity(EntityType<? extends SlimeGirlEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
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
		this.goalSelector.addGoal(3, new SlimeGirlEntity.LeapGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
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

		if (this.isOnGround() && !this.wasOnGround)
		{
			if (this.level.isClientSide)
			{
				this.spawnParticle(12);
			}

			this.targetSquish = -0.5F;
			flag = true;
		}
		else if (!this.onGround && this.wasOnGround)
		{
			this.targetSquish = 1.0F;
		}

		if (this.level.isClientSide && !flag && this.tickCount % 8 == 0 && this.getRandom().nextInt(3) == 0)
		{
			this.spawnParticle(1);
		}

		this.wasOnGround = this.isOnGround();
		this.targetSquish *= 0.6F;
	}

	@OnlyIn(Dist.CLIENT)
	private void spawnParticle(int count)
	{
		for (int j = 0; j < count; ++j)
		{
			float f = this.getRandom().nextFloat() * ((float)Math.PI * 2.0F);
			float f1 = this.getRandom().nextFloat() * 0.5F + 0.5F;
			float f2 = MathHelper.sin(f) * 0.6F * f1;
			float f3 = MathHelper.cos(f) * 0.6F * f1;
			this.level.addParticle(ParticleTypes.WITCH, this.getX() + (double)f2, this.getY(), this.getZ() + (double)f3, 0.0D, 0.0D, 0.0D);
		}
	}

	@Override
	protected void customServerAiStep()
	{
		super.customServerAiStep();

		if (this.isAlive() && ModConfigs.cachedServer.SLIME_GIRL_REGEN)
		{
			if (this.isInWaterOrRain() && !this.hasEffect(Effects.REGENERATION))
			{
				this.addEffect(new EffectInstance(Effects.REGENERATION, 10 * 20, 2));
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		if (super.doHurtTarget(entityIn))
		{
			this.playSound(SoundEvents.SLIME_ATTACK, 1.0F, (this.getRandom().nextFloat() - this.getRandom().nextFloat()) * 0.2F + 1.0F);

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
					((LivingEntity)entityIn).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, i * 20, 1));
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void remove(boolean keepData)
	{
		if (!this.level.isClientSide && ModConfigs.cachedServer.MAGICAL_SLIME_SPAWNS_ON_SLIME_GIRL_DEATH && this.isDeadOrDying() && !this.removed)
		{
			ITextComponent itextcomponent = this.getCustomName();
			boolean flag = this.isNoAi();
			int i = 2;
			float f = (float)i / 4.0F;
			int j = i / 2;
			int k = 2 + this.getRandom().nextInt(3);

			for (int l = 0; l < k; ++l)
			{
				float f1 = ((float)(l % 2) - 0.5F) * f;
				float f2 = ((float)(l / 2) - 0.5F) * f;
				MagicalSlimeEntity slimeentity = ModEntityTypes.MAGICAL_SLIME.get().create(this.level);

				if (this.isPersistenceRequired())
				{
					slimeentity.setPersistenceRequired();
				}

				slimeentity.setCustomName(itextcomponent);
				slimeentity.setNoAi(flag);
				slimeentity.setInvulnerable(this.isInvulnerable());
				slimeentity.setSize(j, true);

				if (this.getRandom().nextInt(4) != 0)
				{
					slimeentity.setVariant(this.getVariant());
				}

				slimeentity.moveTo(this.getX() + (double)f1, this.getY() + 0.5D, this.getZ() + (double)f2, this.getRandom().nextFloat() * 360.0F, 0.0F);
				this.level.addFreshEntity(slimeentity);
			}
		}

		super.remove(keepData);
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isMagic() || source == DamageSource.FALL)
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		this.setVariant(this.getRandom().nextInt(ColorVariant.values().length));

		return spawnDataIn;
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

	@OnlyIn(Dist.CLIENT)
	public float[] getColor()
	{
		return ColorVariant.byId(this.getVariant()).getColors();
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	private void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= ColorVariant.values().length)
		{
			typeIn = this.getRandom().nextInt(ColorVariant.values().length);
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant());
		compound.putBoolean("wasOnGround", this.wasOnGround);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
		this.wasOnGround = compound.getBoolean("wasOnGround");
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.SLIME_SQUISH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.SLIME_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.SLIME_DEATH;
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.SLIME_BLOCK_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@OnlyIn(Dist.CLIENT)
	public static enum ColorVariant
	{
		PINK1(0, 16678066),
		PINK2(1, 16608633),
		PINK3(2, 16143733),
		PINK4(3, 16732339),
		LIGHT_PINK1(4, 16225469),
		LIGHT_PINK2(5, 16751005),
		DARK_PINK1(6, 16005289),
		DARK_PINK2(7, 14830776),
		PURPLE1(8, 13393626),
		PURPLE2(9, 13269976),
		LAVENDER(10, 12360685),
		WHITE(11, 16766945),
		RED1(12, 16216690),
		RED2(13, 16605268),
		SLIME(14, 9489279),
		MINT(15, 7077059),
		BLACK(16, 3946552);

		private final int id;
		private final float[] colors;
		private static final ColorVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(ColorVariant::getId)).toArray((p) -> {
			return new ColorVariant[p];
		});

		private ColorVariant(int idIn, int colorIn)
		{
			this.id = idIn;
			int i = (colorIn & 16711680) >> 16;
			int j = (colorIn & '\uff00') >> 8;
			int k = (colorIn & 255) >> 0;
			this.colors = new float[]{(float)i / 255.0F, (float)j / 255.0F, (float)k / 255.0F};
		}

		public int getId()
		{
			return this.id;
		}

		public float[] getColors()
		{
			return this.colors;
		}

		public static ColorVariant byId(int idIn)
		{
			if (idIn < 0 || idIn >= BY_ID.length)
			{
				idIn = 0;
			}

			return BY_ID[idIn];
		}
	}

	private class LeapGoal extends LeapAtTargetGoal2
	{
		private final MobEntity mob;

		public LeapGoal(MobEntity mob)
		{
			super(mob, 0.25F, 0.3F, 6.0F, 6);
			this.mob = mob;
		}

		@Override
		public void start()
		{
			super.start();
			this.mob.playSound(SoundEvents.SLIME_JUMP, 1.0F, ((this.mob.getRandom().nextFloat() - this.mob.getRandom().nextFloat()) * 0.2F + 1.0F) * 1.4F);
		}
	}
}