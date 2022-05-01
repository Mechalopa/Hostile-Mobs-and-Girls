package com.github.mechalopa.hmag.loot.modifiers;

import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.ArrayUtils;

import com.github.mechalopa.hmag.util.MobTier;
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
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;

public class NuggetLootModifier extends LootModifier
{
	private static final Gson GSON = LootSerializers.createFunctionSerializer().create();
	private final ILootFunction[] functions;

	public NuggetLootModifier(ILootCondition[] conditionsIn, ILootFunction[] functions)
	{
		super(conditionsIn);
        this.functions = functions;
	}

	@Nonnull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context)
	{
		Entity entity = context.getParamOrNull(LootParameters.THIS_ENTITY);

		if (entity != null && entity instanceof LivingEntity)
		{
			if (((LivingEntity)entity).getLootTable() != null && ((LivingEntity)entity).getLootTable().equals(context.getQueriedLootTableId()))
			{
				final MobTier tier = MobTier.getMobTier(entity.getType());

				if (tier.hasReward())
				{
					final Item item = tier.getDropItem(context.getRandom());

					if (item != null)
					{
						ItemStack stack = new ItemStack(item, 1);

						for (ILootFunction function : this.functions)
						{
							stack = function.apply(stack, context);
						}

						generatedLoot.add(stack);
					}
				}
			}
		}

		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<NuggetLootModifier>
	{
		@Override
		public NuggetLootModifier read(ResourceLocation name, JsonObject object, ILootCondition[] conditionsIn)
		{
	        ILootFunction[] functions = object.has("functions") ? GSON.fromJson(object.get("functions"), ILootFunction[].class) : new ILootFunction[0];
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