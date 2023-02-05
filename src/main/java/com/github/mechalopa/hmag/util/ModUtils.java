package com.github.mechalopa.hmag.util;

import java.util.function.Predicate;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.HMaG;
import com.mojang.serialization.Codec;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ModUtils
{
	public static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	public static final String LIVING_UPDATE_CHECKING_KEY = HMaG.MODID + ".checking";
	public static final String LIVING_UPDATE_CHECKED_KEY = HMaG.MODID + ".checked";
	public static final String LIVING_NOT_REPLACED_KEY = HMaG.MODID + ".notReplaced";
	public static final String WITH_SPAWN_PARTICLE_KEY = HMaG.MODID + ".withSpawnParticle";
	public static final Codec<HolderSet<Structure>> STRUCTURE_LIST_CODEC = RegistryCodecs.homogeneousList(Registries.STRUCTURE, Structure.DIRECT_CODEC);

	public static void burnInDay(@Nonnull LivingEntity livingEntity, RandomSource rand, Boolean isSunBurnTick, int seconds)
	{
		burnInDay(livingEntity, rand, isSunBurnTick, true, seconds);
	}

	public static void burnInDay(@Nonnull LivingEntity livingEntity, RandomSource rand, Boolean isSunBurnTick, Boolean shouldBurn, int seconds)
	{
		if (livingEntity != null && livingEntity.level != null && !livingEntity.level.isClientSide && livingEntity.isAlive())
		{
			boolean flag = isSunBurnTick && shouldBurn && !livingEntity.isInWaterOrRain();

			if (flag)
			{
				ItemStack stack = livingEntity.getItemBySlot(EquipmentSlot.HEAD);

				if (!stack.isEmpty())
				{
					if (stack.isDamageableItem())
					{
						stack.setDamageValue(stack.getDamageValue() + rand.nextInt(2));

						if (stack.getDamageValue() >= stack.getMaxDamage())
						{
							livingEntity.broadcastBreakEvent(EquipmentSlot.HEAD);
							livingEntity.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
						}
					}

					flag = false;
				}

				if (flag)
				{
					livingEntity.setSecondsOnFire(seconds);
				}
			}
		}
	}

	public static void catchFire(@Nonnull LivingEntity livingEntity, @Nonnull Entity target, RandomSource rand)
	{
		float f = livingEntity.level.getCurrentDifficultyAt(livingEntity.blockPosition()).getEffectiveDifficulty();

		if (livingEntity.getMainHandItem().isEmpty() && livingEntity.isOnFire() && rand.nextFloat() < f * 0.3F)
		{
			target.setSecondsOnFire(2 * (int)f);
		}
	}

	public static boolean canReach(@Nonnull LivingEntity livingEntity, Vec3 vec3, int count)
	{
		AABB axisalignedbb = livingEntity.getBoundingBox();

		for (int i = 1; i < count; ++i)
		{
			axisalignedbb = axisalignedbb.move(vec3);

			if (!livingEntity.level.noCollision(livingEntity, axisalignedbb))
			{
				return false;
			}
		}

		return true;
	}

	public static boolean closerThan(@Nonnull Entity entity, BlockPos pos, int distance)
	{
		return pos.closerThan(entity.blockPosition(), distance);
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor levelAccessor, BlockPos pos, RandomSource random)
	{
		if (levelAccessor.getBrightness(LightLayer.SKY, pos) > random.nextInt(32))
		{
			return false;
		}
		else
		{
			DimensionType dimensiontype = levelAccessor.dimensionType();
			int i = dimensiontype.monsterSpawnBlockLightLimit();

			if (i < 15 && levelAccessor.getBrightness(LightLayer.BLOCK, pos) > i)
			{
				return false;
			}
			else
			{
				int j = levelAccessor.getLevel().isThundering() ? levelAccessor.getMaxLocalRawBrightness(pos, 10) : levelAccessor.getMaxLocalRawBrightness(pos);
				return j <= dimensiontype.monsterSpawnLightTest().sample(random);
			}
		}
	}

	public static boolean isThornsDamage(@Nonnull DamageSource source)
	{
		return source == DamageSource.CACTUS || source == DamageSource.SWEET_BERRY_BUSH || (source instanceof EntityDamageSource && ((EntityDamageSource)source).isThorns());
	}

	public static boolean isStalagmiteDamage(@Nonnull DamageSource source)
	{
		return source == DamageSource.STALAGMITE || source.getMsgId().equals("fallingStalactite");
	}

	public static float rotlerp(float f, float f1, float f2, boolean flag)
	{
		float f3 = Mth.wrapDegrees(f1 - f);

		if (f3 > f2)
		{
			f3 = f2;
		}

		if (f3 < -f2)
		{
			f3 = -f2;
		}

		float f4 = f + f3;

		if (!flag)
		{
			return f4;
		}

		if (f4 < 0.0F)
		{
			f4 += 360.0F;
		}
		else if (f4 > 360.0F)
		{
			f4 -= 360.0F;
		}

		return f4;
	}

	public static float rotlerpRad(float f, float f1, float f2)
	{
		float f3 = (f2 - f1) % ((float)Math.PI * 2.0F);

		if (f3 < -(float)Math.PI)
		{
			f3 += ((float)Math.PI * 2.0F);
		}

		if (f3 >= (float)Math.PI)
		{
			f3 -= ((float)Math.PI * 2.0F);
		}

		return f1 + f * f3;
	}

	public static float rotlerp2(float f, float f1, float f2)
	{
		while (f1 - f < -180.0F)
		{
			f -= 360.0F;
		}

		while (f1 - f >= 180.0F)
		{
			f += 360.0F;
		}

		return Mth.lerp(f2, f, f1);
	}

	public static ItemStack getPlayerInventoryItem(@Nonnull Player player, Predicate<ItemStack> predicate)
	{
		ItemStack stack = getHeldItem(player, predicate);

		if (!stack.isEmpty())
		{
			return stack;
		}
		else
		{
			for (int i = 0; i < player.getInventory().getContainerSize(); ++i)
			{
				ItemStack stack1 = player.getInventory().getItem(i);

				if (predicate.test(stack1))
				{
					return stack1;
				}
			}

			return ItemStack.EMPTY;
		}
	}

	public static ItemStack getHeldItem(@Nonnull LivingEntity livingEntity, @Nonnull Item item)
	{
		return getHeldItem(livingEntity, Ingredient.of(item));
	}

	public static ItemStack getHeldItem(@Nonnull LivingEntity livingEntity, Predicate<ItemStack> predicate)
	{
		if (predicate.test(livingEntity.getItemInHand(InteractionHand.OFF_HAND)))
		{
			return livingEntity.getItemInHand(InteractionHand.OFF_HAND);
		}
		else
		{
			return predicate.test(livingEntity.getItemInHand(InteractionHand.MAIN_HAND)) ? livingEntity.getItemInHand(InteractionHand.MAIN_HAND) : ItemStack.EMPTY;
		}
	}

	public static boolean isBow(ItemStack stack)
	{
		if (!stack.isEmpty())
			return isBow(stack.getItem());
		return false;
	}

	public static boolean isBow(Item item)
	{
		if (item != null && (item == Items.BOW || item instanceof BowItem))
			return true;
		return false;
	}

	public static boolean isCrossbow(ItemStack stack)
	{
		if (!stack.isEmpty())
			return isCrossbow(stack.getItem());
		return false;
	}

	public static boolean isCrossbow(Item item)
	{
		if (item != null && (item == Items.CROSSBOW || item instanceof CrossbowItem))
			return true;
		return false;
	}

	public static boolean isTrident(ItemStack stack)
	{
		if (!stack.isEmpty())
			return isTrident(stack.getItem());
		return false;
	}

	public static boolean isTrident(Item item)
	{
		if (item != null && (item == Items.TRIDENT || item instanceof TridentItem))
			return true;
		return false;
	}

	public static ItemStack getPotionStack(Potion potion)
	{
		return getPotionStack(potion, Items.POTION);
	}

	public static ItemStack getPotionStack(Potion potion, Item containerItem)
	{
		return PotionUtils.setPotion(new ItemStack(containerItem), potion);
	}
}