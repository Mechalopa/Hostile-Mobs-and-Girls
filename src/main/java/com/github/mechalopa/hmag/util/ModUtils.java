package com.github.mechalopa.hmag.util;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.TridentItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public class ModUtils
{
	public static final EquipmentSlotType[] ARMOR_SLOTS = new EquipmentSlotType[]{EquipmentSlotType.HEAD, EquipmentSlotType.CHEST, EquipmentSlotType.LEGS, EquipmentSlotType.FEET};
	public static final String LIVING_UPDATE_CHECKING_KEY = HMaG.MODID + ".checking";
	public static final String LIVING_UPDATE_CHECKED_KEY = HMaG.MODID + ".checked";
	public static final String LIVING_NOT_REPLACED_KEY = HMaG.MODID + ".notReplaced";
	public static final String WITH_SPAWN_PARTICLE_KEY = HMaG.MODID + ".withSpawnParticle";
	public static final String LEVEL_KEY = HMaG.MODID + ".level";

	public static void burnInDay(@Nonnull LivingEntity livingEntityIn, Random rand, Boolean isInDaylight, int seconds)
	{
		burnInDay(livingEntityIn, rand, isInDaylight, true, seconds);
	}

	public static void burnInDay(@Nonnull LivingEntity livingEntityIn, Random rand, Boolean isInDaylight, Boolean shouldBurn, int seconds)
	{
		if (livingEntityIn != null && livingEntityIn.level != null && !livingEntityIn.level.isClientSide && livingEntityIn.isAlive())
		{
			boolean flag = isInDaylight && shouldBurn && !livingEntityIn.isInWaterOrRain();

			if (flag)
			{
				ItemStack itemstack = livingEntityIn.getItemBySlot(EquipmentSlotType.HEAD);

				if (!itemstack.isEmpty())
				{
					if (itemstack.isDamageableItem())
					{
						itemstack.setDamageValue(itemstack.getDamageValue() + rand.nextInt(2));

						if (itemstack.getDamageValue() >= itemstack.getMaxDamage())
						{
							livingEntityIn.broadcastBreakEvent(EquipmentSlotType.HEAD);
							livingEntityIn.setItemSlot(EquipmentSlotType.HEAD, ItemStack.EMPTY);
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

	public static boolean canReach(@Nonnull LivingEntity livingEntityIn, Vector3d vector3dIn, int countIn)
	{
		AxisAlignedBB axisalignedbb = livingEntityIn.getBoundingBox();

		for (int i = 1; i < countIn; ++i)
		{
			axisalignedbb = axisalignedbb.move(vector3dIn);

			if (!livingEntityIn.level.noCollision(livingEntityIn, axisalignedbb))
			{
				return false;
			}
		}

		return true;
	}

	public static boolean closerThan(Entity entity, BlockPos pos, int distance)
	{
		return pos.closerThan(entity.blockPosition(), (double)distance);
	}

	public static boolean isDarkEnoughToSpawn(IServerWorld worldIn, BlockPos pos, Random randomIn)
	{
		if (worldIn.getBrightness(LightType.SKY, pos) > randomIn.nextInt(32))
		{
			return false;
		}
		else
		{
			final int i = worldIn.getLevel().isThundering() ? worldIn.getMaxLocalRawBrightness(pos, 10) : worldIn.getMaxLocalRawBrightness(pos);
			return i <= randomIn.nextInt(8);
		}
	}

	public static boolean isThornsDamage(DamageSource source)
	{
		return source.getMsgId() == "thorns" || source == DamageSource.CACTUS || source == DamageSource.SWEET_BERRY_BUSH;
	}

	public static float rotlerp(float f, float f1, float f2, boolean flag)
	{
		float f3 = MathHelper.wrapDegrees(f1 - f);

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

		return MathHelper.lerp(f2, f, f1);
	}

	public static boolean matchItemBothHands(LivingEntity livingEntity, Item item)
	{
		for (Hand hand : Hand.values())
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

	public static int getLevel(ItemStack stack)
	{
		CompoundNBT compoundnbt = stack.getTag();
		return compoundnbt != null && compoundnbt.contains(LEVEL_KEY) ? (int)compoundnbt.getByte(LEVEL_KEY) : 0;
	}

	public static void removeLevelTag(ItemStack stack)
	{
		CompoundNBT compoundnbt = stack.getTag();

		if (compoundnbt != null && compoundnbt.contains(LEVEL_KEY))
		{
			compoundnbt.remove(LEVEL_KEY);
		}
	}

	public static boolean checkBiomeList(World worldIn, BlockPos pos, List<? extends String> list)
	{
		if (worldIn != null)
		{
			Biome biome = worldIn.getBiome(pos);

			if (biome != null)
			{
				return checkList(biome.getRegistryName(), list);
			}
		}

		return false;
	}

	public static boolean checkDimensionList(World worldIn, List<? extends String> list)
	{
		if (worldIn != null)
			return checkDimensionList(worldIn.dimension(), list);
		return false;
	}

	public static boolean checkDimensionList(RegistryKey<World> key, List<? extends String> list)
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