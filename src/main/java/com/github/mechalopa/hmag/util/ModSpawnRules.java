package com.github.mechalopa.hmag.util;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;

public class ModSpawnRules
{
	public static boolean checkMonsterSpawnOnGroundRules(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return MonsterEntity.checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn) && (reason == SpawnReason.SPAWNER || worldIn.canSeeSky(pos));
	}

	public static boolean checkMonsterSpawnOnUndergroundRules(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return MonsterEntity.checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn) && (reason == SpawnReason.SPAWNER || !worldIn.canSeeSky(pos));
	}

	public static boolean checkMonsterSpawnInLightOverY64Rules(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return MonsterEntity.checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn) && (reason == SpawnReason.SPAWNER || pos.getY() >= 64);
	}

	public static boolean checkMonsterSpawnInLightUnderY56Rules(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return MonsterEntity.checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn) && (reason == SpawnReason.SPAWNER || pos.getY() < 56);
	}

	public static boolean checkMonsterSpawnInLightUnderY32Rules(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return MonsterEntity.checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn) && (reason == SpawnReason.SPAWNER || pos.getY() < 32);
	}

	public static boolean checkMonsterSpawnInLightUnderY24Rules(EntityType<? extends MonsterEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return MonsterEntity.checkMonsterSpawnRules(type, worldIn, reason, pos, randomIn) && (reason == SpawnReason.SPAWNER || pos.getY() < 24);
	}

	public static boolean checkMobSpawnInLightRules(EntityType<? extends MobEntity> type, IServerWorld worldIn, SpawnReason reason, BlockPos pos, Random randomIn)
	{
		return worldIn.getDifficulty() != Difficulty.PEACEFUL && ModUtils.isDarkEnoughToSpawn(worldIn, pos, randomIn) && MobEntity.checkMobSpawnRules(type, worldIn, reason, pos, randomIn);
	}
}