package com.github.mechalopa.hmag.util;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class ModUtils
{
	public static final EquipmentSlot[] ARMOR_SLOTS = new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET};
	public static final String LIVING_UPDATE_CHECKING_KEY = HMaG.MODID + ".checking";
	public static final String LIVING_UPDATE_CHECKED_KEY = HMaG.MODID + ".checked";
	public static final String LIVING_NOT_REPLACED_KEY = HMaG.MODID + ".notReplaced";
	public static final String WITH_SPAWN_PARTICLE_KEY = HMaG.MODID + ".withSpawnParticle";

	public static void burnInDay(@Nonnull LivingEntity livingEntityIn, Random rand, Boolean isSunBurnTick, int seconds)
	{
		burnInDay(livingEntityIn, rand, isSunBurnTick, true, seconds);
	}

	public static void burnInDay(@Nonnull LivingEntity livingEntityIn, Random rand, Boolean isSunBurnTick, Boolean shouldBurn, int seconds)
	{
		if (livingEntityIn != null && livingEntityIn.level != null && !livingEntityIn.level.isClientSide && livingEntityIn.isAlive())
		{
			boolean flag = isSunBurnTick && shouldBurn && !livingEntityIn.isInWaterOrRain();

			if (flag)
			{
				ItemStack itemstack = livingEntityIn.getItemBySlot(EquipmentSlot.HEAD);

				if (!itemstack.isEmpty())
				{
					if (itemstack.isDamageableItem())
					{
						itemstack.setDamageValue(itemstack.getDamageValue() + rand.nextInt(2));

						if (itemstack.getDamageValue() >= itemstack.getMaxDamage())
						{
							livingEntityIn.broadcastBreakEvent(EquipmentSlot.HEAD);
							livingEntityIn.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
						}
					}

					flag = false;
				}

				if (flag)
				{
					livingEntityIn.setSecondsOnFire(seconds);
				}
			}
		}
	}

	public static boolean canReach(@Nonnull LivingEntity livingEntityIn, Vec3 vec3In, int countIn)
	{
		AABB axisalignedbb = livingEntityIn.getBoundingBox();

		for (int i = 1; i < countIn; ++i)
		{
			axisalignedbb = axisalignedbb.move(vec3In);

			if (!livingEntityIn.level.noCollision(livingEntityIn, axisalignedbb))
			{
				return false;
			}
		}

		return true;
	}

	public static boolean closerThan(Entity entity, BlockPos pos, int distance)
	{
		return pos.closerThan(entity.blockPosition(), distance);
	}

	public static boolean isDarkEnoughToSpawn(ServerLevelAccessor levelAccessor, BlockPos pos, Random randomIn)
	{
		if (levelAccessor.getBrightness(LightLayer.SKY, pos) > randomIn.nextInt(32))
		{
			return false;
		}
		else if (levelAccessor.getBrightness(LightLayer.BLOCK, pos) > 0)
		{
			return false;
		}
		else
		{
			int i = levelAccessor.getLevel().isThundering() ? levelAccessor.getMaxLocalRawBrightness(pos, 10) : levelAccessor.getMaxLocalRawBrightness(pos);
			return i <= randomIn.nextInt(8);
		}
	}

	public static boolean isThornsDamage(DamageSource source)
	{
		return source == DamageSource.CACTUS || source == DamageSource.SWEET_BERRY_BUSH || (source instanceof EntityDamageSource && ((EntityDamageSource)source).isThorns());
	}

	public static boolean isStalagmiteDamage(DamageSource source)
	{
		return source == DamageSource.STALAGMITE || source == DamageSource.FALLING_STALACTITE;
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

	public static boolean matchItemBothHands(LivingEntity livingEntity, Item item)
	{
		for (InteractionHand hand : InteractionHand.values())
		{
			ItemStack stack = livingEntity.getItemInHand(hand);

			if (!stack.isEmpty() && stack.getItem() == item)
			{
				return true;
			}
		}

		return false;
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

	public static boolean checkBiomeList(Level worldIn, BlockPos pos, List<? extends String> list)
	{
		if (worldIn != null)
		{
			Holder<Biome> biome = worldIn.getBiome(pos);

			if (biome != null)
			{
				return checkList(biome.value().getRegistryName(), list);
			}
		}

		return false;
	}

	public static boolean checkDimensionList(Level worldIn, List<? extends String> list)
	{
		if (worldIn != null)
			return checkDimensionList(worldIn.dimension(), list);
		return false;
	}

	public static boolean checkDimensionList(ResourceKey<Level> key, List<? extends String> list)
	{
		if (key != null)
			return checkList(key.location(), list);
		return false;
	}

	public static boolean checkList(ResourceLocation r, List<? extends String> list)
	{
		if (r != null && list != null && !list.isEmpty() && list.contains(r.toString()))
			return true;
		return false;
	}
}