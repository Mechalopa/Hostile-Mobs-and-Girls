package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.level.storage.loot.modifiers.AddItemForEntityLootModifier;
import com.github.mechalopa.hmag.world.level.storage.loot.modifiers.AddTableLootModifier;
import com.github.mechalopa.hmag.world.level.storage.loot.modifiers.ReplaceItemModifier;
import com.mojang.serialization.Codec;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers
{
	private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, HMaG.MODID);

	public static final RegistryObject<Codec<AddItemForEntityLootModifier>> ADD_ITEM_FOR_ENTITY = REGISTRY.register("add_item_for_entity", AddItemForEntityLootModifier.CODEC);
	public static final RegistryObject<Codec<AddTableLootModifier>> ADD_TABLE = REGISTRY.register("add_table", AddTableLootModifier.CODEC);
	public static final RegistryObject<Codec<ReplaceItemModifier>> REPLACE_ITEM = REGISTRY.register("replace_item", ReplaceItemModifier.CODEC);

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}