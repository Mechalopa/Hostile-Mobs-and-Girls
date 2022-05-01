package com.github.mechalopa.hmag.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.loot.ILootSerializer;
import net.minecraft.loot.LootConditionType;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraftforge.fml.ModList;

public class ModLoadedCondition implements ILootCondition
{
	public static final LootConditionType TYPE = new LootConditionType(new ModLoadedCondition.Serializer());
	private final String modid;

	private ModLoadedCondition(String modid)
	{
		this.modid = modid;
	}

	@Override
	public LootConditionType getType()
	{
		return TYPE;
	}

	@Override
	public boolean test(LootContext lootContext)
	{
		return ModList.get().isLoaded(modid);
	}

	public static Builder builder(final String modid)
	{
		return new Builder(modid);
	}

	public static class Builder implements ILootCondition.IBuilder
	{
		private final String modid;

		public Builder(String modid)
		{
			if (modid == null)
			{
				throw new IllegalArgumentException("Target modid must not be null");
			}

			this.modid = modid;
		}

		@Override
		public ILootCondition build()
		{
			return new ModLoadedCondition(this.modid);
		}
	}

	public static class Serializer implements ILootSerializer<ModLoadedCondition>
	{
		@Override
		public void serialize(JsonObject object, ModLoadedCondition instance, JsonSerializationContext ctx)
		{
			object.addProperty("modid", instance.modid);
		}

		@Override
		public ModLoadedCondition deserialize(JsonObject object, JsonDeserializationContext ctx)
		{
			return new ModLoadedCondition(JSONUtils.getAsString(object, "modid"));
		}
	}
}