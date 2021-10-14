package hmag.registry;

import hmag.HMaG;
import hmag.loot.loot_modifiers.AdditionalEntityLootModifier;
import hmag.loot.loot_modifiers.NuggetLootModifier;
import hmag.loot.loot_modifiers.ReplaceLootModifier;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModLootModifiers
{
	private static final DeferredRegister<GlobalLootModifierSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, HMaG.MODID);

	public static final RegistryObject<GlobalLootModifierSerializer<?>> ADDITIONAL_ENTITY_LOOT = REGISTRY.register("additional_entity_loot", () -> new AdditionalEntityLootModifier.Serializer());
	public static final RegistryObject<GlobalLootModifierSerializer<?>> REPLACE_LOOT = REGISTRY.register("replace_loot", () -> new ReplaceLootModifier.Serializer());
	public static final RegistryObject<GlobalLootModifierSerializer<?>> NUGGET_LOOT = REGISTRY.register("nugget_loot", () -> new NuggetLootModifier.Serializer());

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}