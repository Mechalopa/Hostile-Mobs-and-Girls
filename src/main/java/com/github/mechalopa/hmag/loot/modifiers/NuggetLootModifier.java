package com.github.mechalopa.hmag.loot.modifiers;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.ArrayUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.Deserializers;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class NuggetLootModifier extends LootModifier
{
	private static final Gson GSON = Deserializers.createFunctionSerializer().create();
	private final LootItemFunction[] functions;

	public NuggetLootModifier(LootItemCondition[] conditionsIn, LootItemFunction[] functions)
	{
		super(conditionsIn);
        this.functions = functions;
	}

	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
	{
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<NuggetLootModifier>
	{
		@Override
		public NuggetLootModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditionsIn)
		{
			LootItemFunction[] functions = object.has("functions") ? GSON.fromJson(object.get("functions"), LootItemFunction[].class) : new LootItemFunction[0];
			return new NuggetLootModifier(conditionsIn, functions);
		}

		@Override
		public JsonObject write(NuggetLootModifier instance)
		{
			JsonObject json = makeConditions(instance.conditions);

			if (!ArrayUtils.isEmpty(instance.functions))
			{
				json.add("functions", GSON.toJsonTree(instance.functions));
			}

			return json;
		}
	}
}