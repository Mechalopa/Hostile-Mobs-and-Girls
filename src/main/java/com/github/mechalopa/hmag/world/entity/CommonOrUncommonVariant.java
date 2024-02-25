package com.github.mechalopa.hmag.world.entity;

import java.util.function.IntFunction;

import com.mojang.serialization.Codec;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;

public enum CommonOrUncommonVariant implements StringRepresentable
{
	COMMON(0, "common"),
	UNCOMMON(1, "uncommon");

	private static final IntFunction<CommonOrUncommonVariant> BY_ID = ByIdMap.continuous(CommonOrUncommonVariant::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
	public static final Codec<CommonOrUncommonVariant> CODEC = StringRepresentable.fromEnum(CommonOrUncommonVariant::values);
	private final int id;
	private final String name;

	private CommonOrUncommonVariant(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	@Override
	public String getSerializedName()
	{
		return this.name;
	}

	public int getId()
	{
		return this.id;
	}

	public static CommonOrUncommonVariant byId(int id)
	{
		return BY_ID.apply(id);
	}

	public static CommonOrUncommonVariant getSpawnVariant(RandomSource random)
	{
		return random.nextInt(4) == 0 ? UNCOMMON : COMMON;
	}
}