package com.github.mechalopa.hmag.world.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.world.entity.ai.goal.RangedAttackGoal2;
import com.github.mechalopa.hmag.world.entity.projectile.MagicBulletEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
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
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkHooks;

public class LichEntity extends Monster implements IModMob, RangedAttackMob
{
	private static final TargetingConditions VEX_COUNT_TARGETING = TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

	public LichEntity(EntityType<? extends LichEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.xpReward = 25;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(4, new RangedAttackGoal2(this, 1.1D, 20, 40, 15.0F, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
		if (ModConfigs.cachedServer.LICH_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ARMOR, 4.0D);
	}

	@Override
	public MobType getMobType()
	{
		return MobType.UNDEAD;
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isMagic() || source.isFire())
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distance)
	{
		if (ModConfigs.cachedServer.LICH_SUMMON_VEX && distance < 0.5F && this.getRandom().nextInt(4) > this.level.getNearbyEntities(Vex.class, VEX_COUNT_TARGETING, this, this.getBoundingBox().inflate(16.0D)).size())
		{
			ServerLevel serverlevel = (ServerLevel)this.level;

			for(int i = 0; i < 3; ++i)
			{
				BlockPos blockpos = this.blockPosition().offset(-2 + this.getRandom().nextInt(5), 1, -2 + this.getRandom().nextInt(5));
				Vex vex = EntityType.VEX.create(this.level);
				vex.moveTo(blockpos, 0.0F, 0.0F);
				vex.finalizeSpawn(serverlevel, this.level.getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
				vex.setOwner(this);
				vex.setBoundOrigin(blockpos);
				vex.setLimitedLife(20 * (30 + this.random.nextInt(90)));
				serverlevel.addFreshEntityWithPassengers(vex);
			}

			this.playSound(SoundEvents.EVOKER_CAST_SPELL, 1.0F, 1.0F);
			this.swing(InteractionHand.MAIN_HAND);
		}
		else
		{
			double d1 = target.getX() - this.getX();
			double d2 = target.getY() + target.getEyeHeight() * 0.5D - this.getY(0.5D);
			double d3 = target.getZ() - this.getZ();
			double d4 = Math.sqrt(d1 * d1 + d3 * d3) * 0.05D;
			MagicBulletEntity bullet= new MagicBulletEntity(this.level, this, d1 + this.getRandom().nextGaussian() * d4, d2, d3 + this.getRandom().nextGaussian() * d4);
			bullet.setPos(bullet.getX(), this.getY(0.5D) + 0.25D, bullet.getZ());
			bullet.setDamage(6.0F);
			bullet.setEffectLevel((byte)1);
			bullet.setVariant(0);
			this.level.addFreshEntity(bullet);
			this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.swing(InteractionHand.MAIN_HAND);
		}
	}

	@Override
	public boolean isAlliedTo(Entity entityIn)
	{
		if (entityIn == null)
		{
			return false;
		}
		else if (entityIn == this)
		{
			return true;
		}
		else if (super.isAlliedTo(entityIn))
		{
			return true;
		}
		else if (entityIn instanceof Vex)
		{
			return this.isAlliedTo(((Vex)entityIn).getOwner());
		}
		else
		{
			return false;
		}
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 1;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.6D;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn)
	{
		return 2.0F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return SoundEvents.SKELETON_AMBIENT;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.SKELETON_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.SKELETON_DEATH;
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
}