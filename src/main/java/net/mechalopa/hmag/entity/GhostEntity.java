package net.mechalopa.hmag.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import javax.annotation.Nullable;

import net.mechalopa.hmag.registry.ModSoundEvents;
import net.mechalopa.hmag.util.ModUtils;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class GhostEntity extends AbstractFlyingMonsterEntity implements IModMob
{
	private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(GhostEntity.class, DataSerializers.INT);

	public GhostEntity(EntityType<? extends GhostEntity> type, World worldIn)
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
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(4, new AbstractFlyingMonsterEntity.ChargeAttackGoal());
		this.goalSelector.addGoal(8, new AbstractFlyingMonsterEntity.MoveRandomGoal());
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 18.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.2D)
				.add(Attributes.FOLLOW_RANGE, 32.0D);
	}

	@Override
	public CreatureAttribute getMobType()
	{
		return CreatureAttribute.UNDEAD;
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
	public boolean doHurtTarget(Entity entityIn)
	{
		boolean flag = super.doHurtTarget(entityIn);

		if (flag)
		{
			float f = this.level.getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();

			if (this.getMainHandItem().isEmpty() && this.isOnFire() && this.getRandom().nextFloat() < f * 0.3F)
			{
				entityIn.setSecondsOnFire(2 * (int)f);
			}
		}

		return flag;
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		if (this.getRandom().nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F))
		{
			switch (this.getRandom().nextInt(3))
			{
			case 0:
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
				break;
			case 1:
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
				break;
			case 2:
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_SHOVEL));
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
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_HOE));
			}
		}
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		this.setCanPickUpLoot(this.getRandom().nextFloat() < 0.55F * difficultyIn.getSpecialMultiplier());
		this.setVariant(this.getRandom().nextInt(5));
		this.populateDefaultEquipmentSlots(difficultyIn);
		this.populateDefaultEquipmentEnchantments(difficultyIn);

		if (this.getItemBySlot(EquipmentSlotType.HEAD).isEmpty())
		{
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);

			if (j == 10 && i == 31 && this.getRandom().nextFloat() < 0.25F)
			{
				this.setItemSlot(EquipmentSlotType.HEAD, new ItemStack(this.getRandom().nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.armorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
			}
		}
		return spawnDataIn;
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	private void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= 5)
		{
			typeIn = this.getRandom().nextInt(5);
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
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