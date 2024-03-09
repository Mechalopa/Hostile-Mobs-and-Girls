package com.github.mechalopa.hmag.world.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.registry.ModSoundEvents;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class GhostEntity extends AbstractFlyingMonsterEntity implements IModMob
{
	private static final EntityDataAccessor<Integer> DATA_VARIANT_ID = SynchedEntityData.defineId(GhostEntity.class, EntityDataSerializers.INT);

	public GhostEntity(EntityType<? extends GhostEntity> type, Level worldIn)
	{
		super(type, worldIn);
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
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(4, new AbstractFlyingMonsterEntity.ChargeAttackGoal(0.325F, 1.75F));
		this.goalSelector.addGoal(8, new AbstractFlyingMonsterEntity.MoveRandomGoal());
		this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
		this.goalSelector.addGoal(11, new RandomLookAroundGoal(this));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 18.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.FOLLOW_RANGE, 32.0D);
	}

	@Override
	public MobType getMobType()
	{
		return MobType.UNDEAD;
	}

	@Override
	public void aiStep()
	{
		ModUtils.burnInDay(this, this.getRandom(), this.isSunBurnTick(), this.shouldBurnInDay(), 8);

		super.aiStep();
	}

	protected boolean shouldBurnInDay()
	{
    	return true;
	}

	@Override
	public boolean doHurtTarget(Entity entity)
	{
		if (super.doHurtTarget(entity))
		{
			ModUtils.catchFire(this, entity, this.getRandom());
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		if (this.getRandom().nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F))
		{
			switch (this.getRandom().nextInt(3))
			{
			case 0:
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
				break;
			case 1:
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
				break;
			case 2:
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SHOVEL));
				break;
			}
		}
		else
		{
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);

			if (j == 4 && i == 1 && this.getRandom().nextFloat() < 0.5F)
			{
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_HOE));
			}
		}
	}

	@Override
	@Nullable
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType spawnType, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, spawnType, spawnDataIn, dataTag);
		this.setCanPickUpLoot(this.getRandom().nextFloat() < 0.55F * difficultyIn.getSpecialMultiplier());
		this.setVariant(this.getRandom().nextInt(5));
		this.populateDefaultEquipmentSlots(difficultyIn);
		this.populateDefaultEquipmentEnchantments(difficultyIn);

		if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty())
		{
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);

			if (j == 10 && i == 31 && this.getRandom().nextFloat() < 0.25F)
			{
				this.setItemSlot(EquipmentSlot.HEAD, new ItemStack(this.getRandom().nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlot.HEAD.getIndex()] = 0.0F;
			}
		}

		return spawnDataIn;
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	protected void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= 5)
		{
			typeIn = this.getRandom().nextInt(5);
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant());
	}

	@Override
	public float getBrightness()
	{
		return 1.0F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GHOST_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.GHOST_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GHOST_DEATH.get();
	}
}