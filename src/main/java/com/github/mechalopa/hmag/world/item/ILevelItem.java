package com.github.mechalopa.hmag.world.item;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface ILevelItem
{
	public static final String LEVEL_KEY = HMaG.MODID + ".level";

	public default int getMinLevel()
	{
		return 0;
	}

	public int getMaxLevel();

	public static int getItemLevel(ItemStack stack)
	{
		if (stack == null || stack.isEmpty())
		{
			return 0;
		}
		else
		{
			CompoundTag compoundnbt = stack.getTag();
			return compoundnbt != null && compoundnbt.contains(LEVEL_KEY) ? (int)compoundnbt.getByte(LEVEL_KEY) : 0;
		}
	}

	public static void removeItemLevelTag(ItemStack stack)
	{
		CompoundTag compoundnbt = stack.getTag();

		if (compoundnbt != null && compoundnbt.contains(LEVEL_KEY))
		{
			compoundnbt.remove(LEVEL_KEY);
		}
	}
}