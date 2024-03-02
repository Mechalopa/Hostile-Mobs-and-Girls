package com.github.mechalopa.hmag.world.level.storage.loot.modifiers;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class AddTableLootModifier extends LootModifier
{
	public static final Supplier<Codec<AddTableLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).and(ResourceLocation.CODEC.fieldOf("table").forGetter(m -> m.lootTable)).apply(inst, AddTableLootModifier::new)));
	private final ResourceLocation lootTable;

	public AddTableLootModifier(LootItemCondition[] conditions, ResourceLocation lootTable)
	{
		super(conditions);
		this.lootTable = lootTable;
	}

	@SuppressWarnings("deprecation")
	@Nonnull
	@Override
	public ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context)
	{
		LootTable table = context.getResolver().getLootTable(this.lootTable);
		table.getRandomItemsRaw(context, LootTable.createStackSplitter(context.getLevel(), generatedLoot::add));
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec()
	{
		return CODEC.get();
	}
}