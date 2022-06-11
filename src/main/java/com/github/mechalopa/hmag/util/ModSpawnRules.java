package com.github.mechalopa.hmag.util;

import java.util.Random;

import com.github.mechalopa.hmag.ModConfigs;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.ServerLevelAccessor;

public class ModSpawnRules
{
	public static boolean checkMonsterSpawnCanSeeSkyRules(EntityType<? extends Monster> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random)
	{
		return Monster.checkMonsterSpawnRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || levelAccessor.canSeeSky(pos));
	}

	public static boolean checkMonsterSpawnCanNotSeeSkyRules(EntityType<? extends Monster> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random)
	{
		return Monster.checkMonsterSpawnRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || !levelAccessor.canSeeSky(pos));
	}

	public static boolean checkSurfaceMonsterSpawnRules(EntityType<? extends Monster> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random)
	{
		return Monster.checkMonsterSpawnRules(type, levelAccessor, spawnType, pos, random) && (spawnType == MobSpawnType.SPAWNER || pos.getY() >= ModConfigs.cachedServer.SURFACE_MOB_SPAWN_MIN_HEIGHT);
	}

	public static boolean checkMobSpawnInLightRules(EntityType<? extends Mob> type, ServerLevelAccessor levelAccessor, MobSpawnType spawnType, BlockPos pos, Random random)
	{
		return levelAccessor.getDifficulty() != Difficulty.PEACEFUL && ModUtils.isDarkEnoughToSpawn(levelAccessor, pos, random) && Mob.checkMobSpawnRules(type, levelAccessor, spawnType, pos, random);
	}
}