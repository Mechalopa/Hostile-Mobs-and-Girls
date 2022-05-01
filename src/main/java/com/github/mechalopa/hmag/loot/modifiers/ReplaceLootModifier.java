package com.github.mechalopa.hmag.loot.modifiers;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ReplaceLootModifier extends LootModifier
{
	private final Item original;
	private final Item replacement;

	public ReplaceLootModifier(ILootCondition[] conditionsIn, Item original, Item replacement)
	{
		super(conditionsIn);
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

	public static class Serializer extends GlobalLootModifierSerializer<ReplaceLootModifier>
	{
		@Override
		public ReplaceLootModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn)
		{
			Item original = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getAsString(object, "original"))));
			Item replacement = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getAsString(object, "replacement"))));
			return new ReplaceLootModifier(conditionsIn, original, replacement);
		}

		@Override
		public JsonObject write(ReplaceLootModifier instance)
		{
			JsonObject json = makeConditions(instance.conditions);
			json.addProperty("original", ForgeRegistries.ITEMS.getKey(instance.original).toString());
			json.addProperty("replacement", ForgeRegistries.ITEMS.getKey(instance.replacement).toString());
			return json;
		}
	}
}