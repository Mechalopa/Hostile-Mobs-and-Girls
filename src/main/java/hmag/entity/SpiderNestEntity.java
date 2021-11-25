package hmag.entity;

import java.util.Random;

import javax.annotation.Nonnull;

import hmag.ModConfigs;
import hmag.entity.goal.MeleeAttackGoal2;
import hmag.registry.ModSoundEvents;
import hmag.util.ModUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CaveSpiderEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.network.NetworkHooks;

public class SpiderNestEntity extends MonsterEntity implements IModMob
{
	private static final DataParameter<Integer> SUMMON_DELAY = EntityDataManager.defineId(SpiderNestEntity.class, DataSerializers.INT);

	public SpiderNestEntity(EntityType<? extends SpiderNestEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new SpiderNestEntity.MeleeAttackAndSummonGoal(this));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(SUMMON_DELAY, 60);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.175D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
	}

	@Override
	public CreatureAttribute getMobType()
	{
		return CreatureAttribute.ARTHROPOD;
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
					((LivingEntity)entityIn).addEffect(new EffectInstance(Effects.POISON, i * 20, 0));
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
		if (!this.level.isClientSide && ModConfigs.cachedServer.SPIDER_NEST_SUMMON_CAVE_SPIDER && !this.isNoAi() && this.isDeadOrDying() && !this.removed)
		{
			this.summonSpider((ServerWorld)this.level, this, this.getTarget(), this.getRandom(), 2 + this.getRandom().nextInt(3));
		}

		super.remove(keepData);
	}

	@Override
	public void makeStuckInBlock(BlockState state, Vector3d motionMultiplierIn)
	{
		if (!state.is(Blocks.COBWEB))
		{
			super.makeStuckInBlock(state, motionMultiplierIn);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isProjectile() || source.isExplosion() || ModUtils.isThornsDamage(source))
		{
			amount = amount * 0.5F;
		}
		else if (source.isFire())
		{
			amount = amount * 1.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public boolean canBeAffected(EffectInstance potioneffectIn)
	{
		if (potioneffectIn.getEffect() == Effects.POISON)
		{
			PotionEvent.PotionApplicableEvent event = new PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == Event.Result.ALLOW;
		}

		return super.canBeAffected(potioneffectIn);
	}

	private void summonSpider(ServerWorld serverworld, LivingEntity attacker, LivingEntity target, Random random, int count)
	{
		for (int i = 0; i < count; ++i)
		{
			double d1 = attacker.getX() + (random.nextDouble() - random.nextDouble()) * 1.5D;
			double d2 = attacker.getY() + 0.5D;
			double d3 = attacker.getZ() + (random.nextDouble() - random.nextDouble()) * 1.5D;

			CaveSpiderEntity cavespiderentity = EntityType.CAVE_SPIDER.create(attacker.level);
			cavespiderentity.moveTo(d1, d2, d3, random.nextFloat() * 360.0F, 0.0F);
			cavespiderentity.finalizeSpawn(serverworld, attacker.level.getCurrentDifficultyAt(attacker.blockPosition()), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);
			serverworld.addFreshEntityWithPassengers(cavespiderentity);

			if (target != null && target.isAlive())
			{
				cavespiderentity.setTarget(target);
			}

			cavespiderentity.getPersistentData().putBoolean(ModUtils.WITH_SPAWN_PARTICLE_KEY, true);
		}

		attacker.level.levelEvent(2004, attacker.blockPosition(), 0);
		attacker.playSound(ModSoundEvents.SPIDER_NEST_SUMMON.get(), 1.0F, 1.0F);
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 2;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
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
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Delay", this.getSummonDelay());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setSummonDelay(compound.getShort("Delay"));
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.SPIDER_NEST_IDLE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.SPIDER_NEST_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.SPIDER_NEST_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.SHEEP_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class MeleeAttackAndSummonGoal extends MeleeAttackGoal2
	{
		private final int summonInterval;
		private final float summonRadius;
		private final int maxSpawn;
		private final EntityPredicate caveSpiderCountTargeting = (new EntityPredicate()).range(16.0D).allowUnseeable().ignoreInvisibilityTesting().allowInvulnerable();

		public MeleeAttackAndSummonGoal(SpiderNestEntity mob)
		{
			this(mob, 1.0D, false, 120, 10.0F, 2);
		}

		public MeleeAttackAndSummonGoal(SpiderNestEntity mob, double speedIn, boolean useLongMemory, int intervalIn, float radiusIn, int maxSpawnIn)
		{
			super(mob, speedIn, useLongMemory, 8.0F / 9.0F);
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

				if (d0 <= this.summonRadius * this.summonRadius && attacker.canSee(target))
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
						if (attacker.level.getNearbyEntities(CaveSpiderEntity.class, this.caveSpiderCountTargeting, attacker, attacker.getBoundingBox().inflate(16.0D)).size() >= 6)
						{
							attacker.setSummonDelay(20);
						}
						else
						{
							attacker.summonSpider((ServerWorld)attacker.level, attacker, target, attacker.getRandom(), 1 + attacker.getRandom().nextInt(this.maxSpawn));
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