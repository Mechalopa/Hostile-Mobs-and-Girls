package com.github.mechalopa.hmag.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class DullahanEntity extends MonsterEntity implements IModMob
{
	public DullahanEntity(EntityType<? extends DullahanEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true).setUnseenMemoryTicks(120));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.31D)
				.add(Attributes.ATTACK_DAMAGE, 6.0D)
				.add(Attributes.ARMOR, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 20.0D);
	}

	@Override
	public void aiStep()
	{
		ModUtils.burnInDay(this, this.getRandom(), this.isSunBurnTick(), 8);

		super.aiStep();
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
					((LivingEntity)entityIn).addEffect(new EffectInstance(Effects.WEAKNESS, i * 20, 0));
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
		if (source.isMagic())
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		float f = difficulty.getSpecialMultiplier();

		if (this.getRandom().nextFloat() < 0.25F * f + 0.25F)
		{
			this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.IRON_SWORD));
		}
		else
		{
			this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.STONE_SWORD));
		}
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		this.populateDefaultEquipmentSlots(difficultyIn);
		this.populateDefaultEquipmentEnchantments(difficultyIn);

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

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GIRL_MOB_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.GIRL_MOB_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GIRL_MOB_DEATH.get();
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