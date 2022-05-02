package com.github.mechalopa.hmag.loot.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;

import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.fml.ModList;

public class ModLoadedCondition implements LootItemCondition
{
	public static final LootItemConditionType TYPE = new LootItemConditionType(new ModLoadedCondition.ConditionSerializer());
	private final String modid;

	private ModLoadedCondition(String modid)
	{
		this.modid = modid;
	}

	@Override
	public LootItemConditionType getType()
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

	public static class Builder implements LootItemCondition.Builder
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
		public LootItemCondition build()
		{
			return new ModLoadedCondition(this.modid);
		}
	}

	public static class ConditionSerializer implements Serializer<ModLoadedCondition>
	{
		@Override
		public void serialize(JsonObject object, ModLoadedCondition instance, JsonSerializationContext ctx)
		{
			object.addProperty("modid", instance.modid);
		}

		@Override
		public ModLoadedCondition deserialize(JsonObject json, JsonDeserializationContext ctx)
		{
			return new ModLoadedCondition(json.get("modid").getAsString());
		}
	}
}