package com.github.mechalopa.hmag.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.DrownedEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.network.NetworkHooks;

public class DrownedGirlEntity extends DrownedEntity implements IModMob
{
	public DrownedGirlEntity(EntityType<? extends DrownedGirlEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 8;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return ZombieEntity.createAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.245D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ARMOR, 3.0D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		super.populateDefaultEquipmentSlots(difficulty);

		if (this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty())
		{
			if (this.getRandom().nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F))
			{
				int i = this.getRandom().nextInt(3);

				if (i == 0)
				{
					this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
				}
				else
				{
					this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.GOLDEN_SHOVEL));
				}
			}
			else
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
	}

	@SuppressWarnings("deprecation")
	public static boolean checkDrownedGirlSpawnRules(EntityType<DrownedGirlEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		Optional<RegistryKey<Biome>> optional = worldIn.getBiomeName(pos);
		boolean flag = worldIn.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(worldIn, pos, randomIn) && (reason == SpawnReason.SPAWNER || worldIn.getFluidState(pos).is(FluidTags.WATER));

		if (!Objects.equals(optional, Optional.of(Biomes.RIVER)) && !Objects.equals(optional, Optional.of(Biomes.FROZEN_RIVER)))
		{
			return randomIn.nextInt(40) == 0 && (pos.getY() < worldIn.getSeaLevel() - 5) && flag;
		}
		else
		{
			return randomIn.nextInt(15) == 0 && flag;
		}
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}