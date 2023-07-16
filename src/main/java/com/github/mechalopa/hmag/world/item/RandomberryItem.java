package com.github.mechalopa.hmag.world.item;

import java.util.List;

import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class RandomberryItem extends Item
{
	private static List<MobEffect> RANDOMBERRY_EFFECTS;

	public RandomberryItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity)
	{
		ItemStack stack1 = super.finishUsingItem(stack, level, livingEntity);

		if (!level.isClientSide)
		{
			if (RANDOMBERRY_EFFECTS != null && !RANDOMBERRY_EFFECTS.isEmpty())
			{
				MobEffect effect = RANDOMBERRY_EFFECTS.get(livingEntity.getRandom().nextInt(RANDOMBERRY_EFFECTS.size()));

				if (effect != null)
				{
					livingEntity.addEffect(new MobEffectInstance(effect, (livingEntity.getRandom().nextInt(4) + 1) * 5 * 20, 0));
				}
			}
		}

		return stack1;
	}

	public static void refreshEffectList()
	{
		RANDOMBERRY_EFFECTS = ForgeRegistries.MOB_EFFECTS.getValues().stream().filter((p) -> {
			return ModTags.checkTagContains(p, ModTags.MobEffectTags.RANDOMBERRY_GIVES);
		}).toList();
	}
}