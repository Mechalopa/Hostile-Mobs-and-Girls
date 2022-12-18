package com.github.mechalopa.hmag.world.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;

import javax.annotation.Nonnull;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Stray;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkHooks;

public class StrayGirlEntity extends Stray implements IModMob
{
	public StrayGirlEntity(EntityType<? extends StrayGirlEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.xpReward = 8;
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ATTACK_DAMAGE, 3.25D)
				.add(Attributes.ARMOR, 1.0D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		super.populateDefaultEquipmentSlots(difficulty);

		if (this.random.nextFloat() < 0.05F)
		{
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_AXE));
		}

		LocalDate localdate = LocalDate.now();
		int i = localdate.get(ChronoField.DAY_OF_MONTH);
		int j = localdate.get(ChronoField.MONTH_OF_YEAR);

		if (j == 4 && i == 1 && this.random.nextFloat() < 0.5F)
		{
			this.setItemSlot(EquipmentSlot.OFFHAND, new ItemStack(Items.WOODEN_HOE));
		}
	}

	public static boolean checkStrayGirlSpawnRules(EntityType<StrayGirlEntity> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random)
	{
		BlockPos blockpos = pos;

		do
		{
			blockpos = blockpos.above();
		}
		while(levelAccessor.getBlockState(blockpos).is(Blocks.POWDER_SNOW));

		return Monster.checkMonsterSpawnRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.canSeeSky(pos.below()));
	}

	@Nonnull
	@Override
	public Packet<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}