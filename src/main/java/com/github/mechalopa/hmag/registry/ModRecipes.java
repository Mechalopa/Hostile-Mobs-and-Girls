package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.recipe.EnchantmentUpgradeRecipe;
import com.github.mechalopa.hmag.recipe.ItemTagShapedRecipe;
import com.github.mechalopa.hmag.recipe.RemoveCurseRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes
{
	private static final DeferredRegister<IRecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, HMaG.MODID);

	public static final RegistryObject<IRecipeSerializer<ShapedRecipe>> CRAFTING_ITEM_TAG_SHAPED = REGISTRY.register("crafting_item_tag_shaped", () -> new ItemTagShapedRecipe.Serializer());
	public static final RegistryObject<IRecipeSerializer<RemoveCurseRecipe>> REMOVE_CURSE = REGISTRY.register("remove_curse", () -> new RemoveCurseRecipe.Serializer());
	public static final RegistryObject<IRecipeSerializer<EnchantmentUpgradeRecipe>> ENCHANTMENT_UPGRADE = REGISTRY.register("enchantment_upgrade", () -> new EnchantmentUpgradeRecipe.Serializer());

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}