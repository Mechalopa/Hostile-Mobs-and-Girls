package com.github.mechalopa.hmag.item;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;

public class EvilThornItem extends EnchantmentUpgradeItem
{
	public EvilThornItem(Item.Properties builder)
	{
		super(builder, new EnchantmentUpgradeItem.Properties().enchantment(Enchantments.THORNS, 0, 9));
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, PlayerEntity player, LivingEntity entity, Hand hand)
	{
		if (!player.level.isClientSide)
		{
			if (entity.hurt(DamageSource.thorns(player), 1.0F))
			{
				player.crit(entity);
				player.level.playSound((PlayerEntity)null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_HURT, SoundCategory.NEUTRAL, 0.5F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
				stack.shrink(1);
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}
}