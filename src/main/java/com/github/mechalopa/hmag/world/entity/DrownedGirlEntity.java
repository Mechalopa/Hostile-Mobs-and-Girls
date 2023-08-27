package com.github.mechalopa.hmag.world.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.network.NetworkHooks;

public class DrownedGirlEntity extends Drowned
{
	public DrownedGirlEntity(EntityType<? extends DrownedGirlEntity> type, Level level)
	{
		super(type, level);
		this.xpReward = 8;
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Zombie.createAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.245D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ARMOR, 3.0D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(RandomSource rand, DifficultyInstance difficulty)
	{
		super.populateDefaultEquipmentSlots(rand, difficulty);

		if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty())
		{
			if (rand.nextFloat() < (this.level().getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F))
			{
				int i = rand.nextInt(3);

				if (i == 0)
				{
					this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
				}
				else
				{
					this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SHOVEL));
				}
			}
			else
			{
				LocalDate localdate = LocalDate.now();
				int i = localdate.get(ChronoField.DAY_OF_MONTH);
				int j = localdate.get(ChronoField.MONTH_OF_YEAR);

				if (j == 4 && i == 1 && rand.nextFloat() < 0.5F)
				{
					this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.WOODEN_HOE));
				}
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean checkDrownedGirlSpawnRules(EntityType<DrownedGirlEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, RandomSource random)
	{
		if (!levelAccessor.getFluidState(pos.below()).is(FluidTags.WATER))
		{
			return false;
		}
		else
		{
			Holder<Biome> holder = levelAccessor.getBiome(pos);
			boolean flag = levelAccessor.getDifficulty() != Difficulty.PEACEFUL && isDarkEnoughToSpawn(levelAccessor, pos, random) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.getFluidState(pos).is(FluidTags.WATER));

			if (holder.is(BiomeTags.MORE_FREQUENT_DROWNED_SPAWNS))
			{
				return random.nextInt(40) == 0 && (pos.getY() < levelAccessor.getSeaLevel() - 5) && flag;
			}
			else
			{
				return random.nextInt(15) == 0 && flag;
			}
		}
	}

	@Nonnull
	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}