package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.level.storage.loot.modifiers.AddItemForEntityLootModifier;
import com.github.mechalopa.hmag.world.level.storage.loot.modifiers.ReplaceItemModifier;

import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers
{
	private static final DeferredRegister<GlobalLootModifierSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.LOOT_MODIFIER_SERIALIZERS, HMaG.MODID);

	public static final RegistryObject<GlobalLootModifierSerializer<?>> ADD_ITEM_FOR_ENTITY = REGISTRY.register("add_item_for_entity", () -> new AddItemForEntityLootModifier.Serializer());
	public static final RegistryObject<GlobalLootModifierSerializer<?>> REPLACE_ITEM = REGISTRY.register("replace_item", () -> new ReplaceItemModifier.Serializer());

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}