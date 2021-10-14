package hmag.entity;

import javax.annotation.Nonnull;

import hmag.ModConfigs;
import hmag.registry.ModSoundEvents;
import hmag.util.ModTags;
import hmag.util.ModUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

public class OgreEntity extends MonsterEntity implements IModMob
{
	private static final DataParameter<Boolean> IS_ARM_SWING = EntityDataManager.defineId(OgreEntity.class, DataSerializers.BOOLEAN);
	private int armSwingTimer;
	private float attackAnimation;
	private float attackAnimationO;

	public OgreEntity(EntityType<? extends OgreEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 25;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackAndDestroyGoal(this, 1.0D, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		if (ModConfigs.cachedServer.OGRE_ATTACK_VILLAGERS)
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
		if (ModConfigs.cachedServer.OGRE_ATTACK_BABY_TURTLES)
			this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(IS_ARM_SWING, false);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.285D)
				.add(Attributes.ATTACK_DAMAGE, 12.0D)
				.add(Attributes.ATTACK_KNOCKBACK, 2.0D)
				.add(Attributes.ARMOR, 8.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);
	}

	@Override
	public void tick()
	{
		if (this.level.isClientSide)
		{
			this.attackAnimationO = this.attackAnimation;
		}

		super.tick();

		if (this.level.isClientSide)
		{
			if (this.isArmSwinging())
			{
				this.attackAnimation = Math.min(1.0F, this.attackAnimation + 0.45F);
			}
			else
			{
				this.attackAnimation = Math.max(0.0F, this.attackAnimation - 0.08F);
			}
		}
	}

	@Override
	public void aiStep()
	{
		if (!this.level.isClientSide)
		{
			boolean flag = false;

			if (this.armSwingTimer > 0)
			{
				flag = true;
			}

			if (this.isArmSwinging() != flag)
			{
				this.setArmSwinging(flag);
			}
		}

		super.aiStep();

		if (!this.level.isClientSide)
		{
			if (this.armSwingTimer > 0)
			{
				--this.armSwingTimer;
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		this.armSwingTimer = 3;

		if (super.doHurtTarget(entityIn))
		{
			this.playArmSwingSound();

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
					((LivingEntity)entityIn).addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, i * 20, 3));
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
		if (source == DamageSource.IN_WALL || source == DamageSource.CRAMMING || ModUtils.isThornsDamage(source))
		{
			amount = amount * 0.25F;

			if (!this.isNoAi() && this.getRandom().nextInt(8) == 0)
			{
				this.destroyBlock();
			}
		}
		else if (source.isProjectile() || source.isExplosion() || source.isFire() || source == DamageSource.FALL || source == DamageSource.FALLING_BLOCK)
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
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
		return 2.5F;
	}

	public boolean isArmSwinging()
	{
		return this.entityData.get(IS_ARM_SWING);
	}

	public void setArmSwinging(boolean flag)
	{
		this.entityData.set(IS_ARM_SWING, flag);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAnimationScale(float f)
	{
		return MathHelper.lerp(f, this.attackAnimationO, this.attackAnimation);
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.OGRE_IDLE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.OGRE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.OGRE_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.COW_STEP, 0.25F, 1.0F);
	}

	protected void playArmSwingSound()
	{
		this.playSound(SoundEvents.ZOMBIE_BREAK_WOODEN_DOOR, 0.5F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	protected boolean destroyBlock()
	{
		if (ModConfigs.cachedServer.OGRE_DESTROY_BLOCKS && this.level.getDifficulty().getId() > 1 && ForgeEventFactory.getMobGriefingEvent(this.level, this))
		{
			int i1 = MathHelper.floor(this.getY());
			int l1 = MathHelper.floor(this.getX());
			int i2 = MathHelper.floor(this.getZ());
			boolean flag = false;

			for (int k2 = -1; k2 <= 1; ++k2)
			{
				for (int l2 = -1; l2 <= 1; ++l2)
				{
					for (int j = 0; j <= 3; ++j)
					{
						int i3 = l1 + k2;
						int k = i1 + j;
						int l = i2 + l2;
						BlockPos blockpos = new BlockPos(i3, k, l);
						BlockState blockstate = this.level.getBlockState(blockpos);

						if (this.canDestroyBlock(blockstate.getBlock(), blockstate, this.level, blockpos, this, 5.0F))
						{
							flag = this.level.destroyBlock(blockpos, true, this) || flag;
						}
					}
				}
			}

			if (flag)
			{
				this.armSwingTimer = 5;
				this.playArmSwingSound();
			}

			return flag;
		}
		else
		{
			return false;
		}
	}

	private boolean canDestroyBlock(Block blockIn, BlockState stateIn, World worldIn, BlockPos pos, LivingEntity entityIn, float maxHardness)
	{
		if (ModTags.checkTagContains(ModTags.OGRE_IMMUNE, blockIn) || blockIn.isAir(stateIn, worldIn, pos) || stateIn.getMaterial().isLiquid() || !blockIn.canEntityDestroy(stateIn, worldIn, pos, entityIn))
		{
			return false;
		}
		else
		{
			float f = stateIn.getDestroySpeed(worldIn, pos);

			return f >= 0.0F && f <= maxHardness && f / maxHardness <= this.getRandom().nextFloat() + 0.05F && this.getRandom().nextBoolean();
		}
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	private class MeleeAttackAndDestroyGoal extends MeleeAttackGoal
	{
		public MeleeAttackAndDestroyGoal(OgreEntity mob, double speedIn, boolean useLongMemory)
		{
			super(mob, speedIn, useLongMemory);
		}

		@Override
		protected void checkAndPerformAttack(LivingEntity attackTarget, double distToEnemySqr)
		{
			double d0 = this.getAttackReachSqr(attackTarget);

			if (this.getTicksUntilNextAttack() <= 0)
			{
				if (distToEnemySqr <= d0)
				{
					this.resetAttackCooldown();
					this.mob.swing(Hand.MAIN_HAND);
					this.mob.doHurtTarget(attackTarget);
				}
				else if (((distToEnemySqr < 10.0D * 10.0D && !this.mob.getSensing().canSee(attackTarget)) || distToEnemySqr < 3.0D * 3.0D) && this.mob.getRandom().nextInt(12) == 0)
				{
					OgreEntity.this.destroyBlock();
				}
			}
		}
	}
}