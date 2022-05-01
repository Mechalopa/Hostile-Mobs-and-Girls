package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.enchantment.HealthBoostEnchantment;
import com.github.mechalopa.hmag.enchantment.WaterAspectEnchantment;
import com.github.mechalopa.hmag.util.ModUtils;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEnchantments
{
	private static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, HMaG.MODID);

	public static final RegistryObject<Enchantment> HEALTH_BOOST = REGISTRY.register("health_boost", () -> new HealthBoostEnchantment(Enchantment.Rarity.UNCOMMON, ModUtils.ARMOR_SLOTS));
	public static final RegistryObject<Enchantment> WATER_ASPECT = REGISTRY.register("water_aspect", () -> new WaterAspectEnchantment(Enchantment.Rarity.RARE, EquipmentSlotType.MAINHAND));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}