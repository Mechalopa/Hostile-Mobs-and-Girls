package com.github.mechalopa.hmag.item;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class EvilFlameItem extends EnchantmentUpgradeItem
{
	public EvilFlameItem(Item.Properties builder)
	{
		super(builder, new EnchantmentUpgradeItem.Properties().enchantment(Enchantments.FIRE_ASPECT, 0, 1).enchantment(Enchantments.FLAMING_ARROWS, 0, 0));
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
	{
		if (!player.level.isClientSide)
		{
			entity.setSecondsOnFire(3);
			player.crit(entity);
			player.level.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundCategory.NEUTRAL, 1.0F, random.nextFloat() * 0.4F + 0.8F);
			stack.shrink(1);
			return ActionResultType.SUCCESS;
		}

		return ActionResultType.PASS;
	}
}