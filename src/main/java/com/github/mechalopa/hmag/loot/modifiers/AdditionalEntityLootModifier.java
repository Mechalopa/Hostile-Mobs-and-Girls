package com.github.mechalopa.hmag.loot.modifiers;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.ArrayUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.LootParameters;
import net.minecraft.loot.LootSerializers;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.functions.ILootFunction;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class AdditionalEntityLootModifier extends LootModifier
{
	private static final Gson GSON = LootSerializers.createFunctionSerializer().create();
	private final ILootFunction[] functions;
	private final Item addition;

	public AdditionalEntityLootModifier(ILootCondition[] conditionsIn, ILootFunction[] functions, Item addition)
	{
		super(conditionsIn);
        this.functions = functions;
		this.addition = addition;
	}

	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
	{
		Entity entity = context.getParamOrNull(LootParameters.THIS_ENTITY);

		if (entity != null && entity instanceof LivingEntity && this.addition != null)
		{
			if (((LivingEntity)entity).getLootTable() != null && context.getQueriedLootTableId().equals(((LivingEntity)entity).getLootTable()))
			{
				ItemStack stack = this.addition.getDefaultInstance();

				for (ILootFunction function : this.functions)
				{
					stack = function.apply(stack, context);
				}

				generatedLoot.add(stack);
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<AdditionalEntityLootModifier>
	{
		@Override
		public AdditionalEntityLootModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn)
		{
	        ILootFunction[] functions = object.has("functions") ? GSON.fromJson(object.get("functions"), ILootFunction[].class) : new ILootFunction[0];
			Item addition = ForgeRegistries.ITEMS.getValue(new ResourceLocation((JSONUtils.getAsString(object, "addition"))));
			return new AdditionalEntityLootModifier(conditionsIn, functions, addition);
		}

		@Override
		public JsonObject write(AdditionalEntityLootModifier instance)
		{
			JsonObject json = makeConditions(instance.conditions);

			if (!ArrayUtils.isEmpty(instance.functions))
			{
				json.add("functions", GSON.toJsonTree(instance.functions));
			}

			json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
			return json;
		}
	}
}