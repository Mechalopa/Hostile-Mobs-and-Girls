package com.github.mechalopa.hmag.world.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class RandomberryItem extends Item
{
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
			final int i = livingEntity.getRandom().nextInt(3);
			MobEffect effect = null;

			switch (livingEntity.getRandom().nextInt(12))
			{
			case 0:
				effect = MobEffects.REGENERATION;
				break;
			case 1:
				effect = MobEffects.DAMAGE_RESISTANCE;
				break;
			case 2:
				effect = MobEffects.MOVEMENT_SPEED;
				break;
			case 3:
				effect = MobEffects.DIG_SPEED;
				break;
			case 4:
				effect = MobEffects.DOLPHINS_GRACE;
				break;
			case 5:
				effect = MobEffects.INVISIBILITY;
				break;
			case 6:
				effect = MobEffects.WEAKNESS;
				break;
			case 7:
				effect = MobEffects.HUNGER;
				break;
			case 8:
				effect = MobEffects.JUMP;
				break;
			case 9:
				effect = MobEffects.MOVEMENT_SLOWDOWN;
				break;
			case 10:
				effect = MobEffects.DIG_SLOWDOWN;
				break;
			case 11:
				effect = MobEffects.SLOW_FALLING;
				break;
			}

			if (effect != null)
			{
				livingEntity.addEffect(new MobEffectInstance(effect, (i + 1) * 5 * 20, 0));
			}
		}

		return stack1;
	}
}