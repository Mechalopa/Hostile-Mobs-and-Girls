package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeRecipe;
import com.github.mechalopa.hmag.world.item.crafting.RemoveCurseRecipe;
import com.github.mechalopa.hmag.world.item.crafting.SuspiciousStewUpgradeRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes
{
	private static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HMaG.MODID);

	public static final RegistryObject<RecipeSerializer<RemoveCurseRecipe>> REMOVE_CURSE = REGISTRY.register("remove_curse", () -> new SimpleRecipeSerializer<>(RemoveCurseRecipe::new));
	public static final RegistryObject<RecipeSerializer<EnchantmentUpgradeRecipe>> ENCHANTMENT_UPGRADE = REGISTRY.register("enchantment_upgrade", () -> new SimpleRecipeSerializer<>(EnchantmentUpgradeRecipe::new));
	public static final RegistryObject<RecipeSerializer<SuspiciousStewUpgradeRecipe>> SUSPICIOUS_STEW_UPGRADE = REGISTRY.register("suspicious_stew_upgrade", () -> new SimpleRecipeSerializer<>(SuspiciousStewUpgradeRecipe::new));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}