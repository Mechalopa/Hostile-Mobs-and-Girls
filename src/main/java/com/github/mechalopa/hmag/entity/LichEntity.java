package com.github.mechalopa.hmag.entity;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.ModConfigs;
import com.github.mechalopa.hmag.entity.goal.RangedAttackGoal2;
import com.github.mechalopa.hmag.entity.projectile.MagicBulletEntity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPredicate;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.VexEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

public class LichEntity extends MonsterEntity implements IModMob, IRangedAttackMob
{
	private static final EntityPredicate VEX_COUNT_TARGETING = (new EntityPredicate()).range(16.0D).allowUnseeable().ignoreInvisibilityTesting().allowInvulnerable().allowSameTeam();

	public LichEntity(EntityType<? extends LichEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 25;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(4, new RangedAttackGoal2(this, 1.3D, 20, 40, 15.0F, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		if (ModConfigs.cachedServer.LICH_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ARMOR, 4.0D);
	}

	@Override
	public CreatureAttribute getMobType()
	{
		return CreatureAttribute.UNDEAD;
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
	public void performRangedAttack(LivingEntity target, float distanceFactor)
	{
		if (ModConfigs.cachedServer.LICH_SUMMON_VEX && distanceFactor < 0.5F && this.getRandom().nextInt(4) > this.level.getNearbyEntities(VexEntity.class, VEX_COUNT_TARGETING, this, this.getBoundingBox().inflate(16.0D)).size())
		{
			ServerWorld serverworld = (ServerWorld)this.level;

			for (int i = 0; i < 3; ++i)
			{
				BlockPos blockpos = this.blockPosition().offset(-2 + this.getRandom().nextInt(5), 1, -2 + this.getRandom().nextInt(5));
				VexEntity vexentity = EntityType.VEX.create(this.level);
				vexentity.moveTo(blockpos, 0.0F, 0.0F);
				vexentity.finalizeSpawn(serverworld, this.level.getCurrentDifficultyAt(blockpos), SpawnReason.MOB_SUMMONED, (ILivingEntityData)null, (CompoundNBT)null);
				vexentity.setOwner(this);
				vexentity.setBoundOrigin(blockpos);
				vexentity.setLimitedLife(20 * (30 + this.getRandom().nextInt(90)));
				serverworld.addFreshEntityWithPassengers(vexentity);
			}

			this.playSound(SoundEvents.EVOKER_CAST_SPELL, 1.0F, 1.0F);
			this.swing(Hand.MAIN_HAND);
		}
		else
		{
			double d1 = target.getX() - this.getX();
			double d2 = target.getY() + (double)target.getEyeHeight() * 0.5D - this.getY(0.5D);
			double d3 = target.getZ() - this.getZ();
			float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.05F;
			MagicBulletEntity magicbulletentity = new MagicBulletEntity(this.level, this, d1 + this.getRandom().nextGaussian() * (double)f, d2, d3 + this.getRandom().nextGaussian() * (double)f);
			magicbulletentity.setPos(magicbulletentity.getX(), this.getY(0.5D) + 0.25D, magicbulletentity.getZ());
			magicbulletentity.setDamage(6.0F);
			magicbulletentity.setEffectLevel((byte)1);
			magicbulletentity.setVariant(0);
			this.level.addFreshEntity(magicbulletentity);
			this.playSound(SoundEvents.BLAZE_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
			this.swing(Hand.MAIN_HAND);
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
		else if (entityIn instanceof VexEntity)
		{
			return this.isAlliedTo(((VexEntity)entityIn).getOwner());
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
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
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
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}