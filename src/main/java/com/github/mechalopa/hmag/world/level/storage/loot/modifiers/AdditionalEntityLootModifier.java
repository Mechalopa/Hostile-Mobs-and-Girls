package com.github.mechalopa.hmag.world.level.storage.loot.modifiers;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Suppliers;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootModifierManager;
import net.minecraftforge.registries.ForgeRegistries;

public class AdditionalEntityLootModifier extends LootModifier
{
	private static final Codec<LootItemFunction[]> LOOT_FUNCTIONS_CODEC = Codec.PASSTHROUGH.flatXmap(d -> {
				try
				{
					LootItemFunction[] functions = LootModifierManager.GSON_INSTANCE.fromJson(IGlobalLootModifier.getJson(d), LootItemFunction[].class);
					return DataResult.success(functions);
				}
				catch (JsonSyntaxException e)
				{
					LootModifierManager.LOGGER.warn("Unable to decode loot functions", e);
					return DataResult.error(e.getMessage());
				}
			},
			functions -> {
				try
				{
					JsonElement element = LootModifierManager.GSON_INSTANCE.toJsonTree(functions);
					return DataResult.success(new Dynamic<>(JsonOps.INSTANCE, element));
				}
				catch (JsonSyntaxException e)
				{
					LootModifierManager.LOGGER.warn("Unable to encode loot functions", e);
					return DataResult.error(e.getMessage());
				}
			});

	public static final Supplier<Codec<AdditionalEntityLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).and(inst.group(LOOT_FUNCTIONS_CODEC.optionalFieldOf("functions", new LootItemFunction[0]).forGetter(m -> m.functions),ForgeRegistries.ITEMS.getCodec().optionalFieldOf("addition", Items.BARRIER).forGetter(m -> m.addition))).apply(inst, AdditionalEntityLootModifier::new)));
	private final LootItemFunction[] functions;
	private final Item addition;

	public AdditionalEntityLootModifier(LootItemCondition[] conditionsIn, LootItemFunction[] functions, Item addition)
	{
		super(conditionsIn);
		this.functions = functions;
		this.addition = addition;
	}

	@Nonnull
	@Override
	public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
	{
		Entity entity = context.getParamOrNull(LootContextParams.THIS_ENTITY);

		if (entity != null && entity instanceof LivingEntity && ((LivingEntity)entity).getLootTable() != null && context.getQueriedLootTableId().equals(((LivingEntity)entity).getLootTable()) && this.addition != null && !this.addition.equals(Items.BARRIER))
		{
			ItemStack stack = this.addition.getDefaultInstance();

			for (LootItemFunction function : this.functions)
			{
				stack = function.apply(stack, context);
			}

			generatedLoot.add(stack);
		}

		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec()
	{
		return CODEC.get();
	}
}