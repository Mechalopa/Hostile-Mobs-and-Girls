package com.github.mechalopa.hmag.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class CreeperGirlEntity extends CreeperEntity implements IModMob
{
	private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(CreeperGirlEntity.class, DataSerializers.INT);

	public CreeperGirlEntity(EntityType<? extends CreeperEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 8;
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, 0);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.25D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		super.populateDefaultEquipmentSlots(difficulty);

		if (this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty())
		{
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);

			if (j == 4 && i == 1 && this.getRandom().nextFloat() < 0.5F)
			{
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_HOE));
			}
		}
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);

		this.setVariant(this.getRandom().nextInt(3));
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
		if (typeIn < 0 || typeIn >= 3)
		{
			typeIn = this.getRandom().nextInt(3);
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