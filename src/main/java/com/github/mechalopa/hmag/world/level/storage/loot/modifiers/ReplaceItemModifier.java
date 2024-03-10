package com.github.mechalopa.hmag.world.level.storage.loot.modifiers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ReplaceItemModifier extends LootModifier
{
	private final Item original;
	private final Item replacement;

	public ReplaceItemModifier(LootItemCondition[] conditions, Item original, Item replacement)
	{
		super(conditions);
		this.original = original;
		this.replacement = replacement;
	}

	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
	{
		if (this.original == null || this.replacement == null)
		{
			return generatedLoot;
		}
		else
		{
			return generatedLoot.stream().map(stack -> {
				if (stack.getItem() == this.original)
				{
					return new ItemStack(this.replacement, stack.getCount());
				}
				else
				{
					return stack;
				}
			}).collect(Collectors.toList());
		}
	}

	public static class Serializer extends GlobalLootModifierSerializer<ReplaceItemModifier>
	{
		@Override
		public ReplaceItemModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditions)
		{
			Item original = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "original"))));
			Item replacement = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "replacement"))));
			return new ReplaceItemModifier(conditions, original, replacement);
		}

		@Override
		public JsonObject write(ReplaceItemModifier instance)
		{
			JsonObject json = makeConditions(instance.conditions);
			json.addProperty("original", ForgeRegistries.ITEMS.getKey(instance.original).toString());
			json.addProperty("replacement", ForgeRegistries.ITEMS.getKey(instance.replacement).toString());
			return json;
		}
	}
}