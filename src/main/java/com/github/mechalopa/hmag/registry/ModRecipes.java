package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeRecipe;
import com.github.mechalopa.hmag.world.item.crafting.NewerEnchantmentUpgradeRecipe;
import com.github.mechalopa.hmag.world.item.crafting.NewerRemoveCurseRecipe;
import com.github.mechalopa.hmag.world.item.crafting.NewerSimpleUpgradeRecipeSerializer;
import com.github.mechalopa.hmag.world.item.crafting.RemoveCurseRecipe;
import com.github.mechalopa.hmag.world.item.crafting.SimpleUpgradeRecipeSerializer;
import com.github.mechalopa.hmag.world.item.crafting.SuspiciousStewUpgradeRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("removal")
public class ModRecipes
{
	private static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HMaG.MODID);

	public static final RegistryObject<RecipeSerializer<RemoveCurseRecipe>> REMOVE_CURSE = REGISTRY.register("remove_curse", () -> new SimpleUpgradeRecipeSerializer<>(RemoveCurseRecipe::new));
	public static final RegistryObject<RecipeSerializer<EnchantmentUpgradeRecipe>> ENCHANTMENT_UPGRADE = REGISTRY.register("enchantment_upgrade", () -> new SimpleUpgradeRecipeSerializer<>(EnchantmentUpgradeRecipe::new));
	public static final RegistryObject<RecipeSerializer<NewerRemoveCurseRecipe>> NEWER_REMOVE_CURSE = REGISTRY.register("newer_remove_curse", () -> new NewerSimpleUpgradeRecipeSerializer<>(NewerRemoveCurseRecipe::new));
	public static final RegistryObject<RecipeSerializer<NewerEnchantmentUpgradeRecipe>> NEWER_ENCHANTMENT_UPGRADE = REGISTRY.register("newer_enchantment_upgrade", () -> new NewerSimpleUpgradeRecipeSerializer<>(NewerEnchantmentUpgradeRecipe::new));
	public static final RegistryObject<RecipeSerializer<SuspiciousStewUpgradeRecipe>> SUSPICIOUS_STEW_UPGRADE = REGISTRY.register("suspicious_stew_upgrade", () -> new SimpleCraftingRecipeSerializer<>(SuspiciousStewUpgradeRecipe::new));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}