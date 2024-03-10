package com.github.mechalopa.hmag.world.level.storage.loot.modifiers;

import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

public class ReplaceItemModifier extends LootModifier
{
	public static final Supplier<Codec<ReplaceItemModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).and(inst.group(ForgeRegistries.ITEMS.getCodec().optionalFieldOf("original", Items.BARRIER).forGetter(m -> m.original), ForgeRegistries.ITEMS.getCodec().optionalFieldOf("replacement", Items.BARRIER).forGetter(m -> m.replacement))).apply(inst, ReplaceItemModifier::new)));
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
	public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
	{
		if (this.original == null || this.original.equals(Items.BARRIER) || this.replacement == null || this.replacement.equals(Items.BARRIER))
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
			}).collect(Collectors.toCollection(ObjectArrayList::new));
		}
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec()
	{
		return CODEC.get();
	}
}