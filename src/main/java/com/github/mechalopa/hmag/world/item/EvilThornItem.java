package com.github.mechalopa.hmag.world.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class EvilThornItem extends EnchantmentUpgradeItem
{
	public EvilThornItem(Item.Properties builder)
	{
		super(builder, new EnchantmentUpgradeItem.Properties().enchantment(() ->Enchantments.THORNS, 0, 6));
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand)
	{
		if (!player.level.isClientSide)
		{
			if (entity.hurt(player.damageSources().thorns(player), 1.0F))
			{
				player.crit(entity);
				player.level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_HURT, SoundSource.NEUTRAL, 0.5F, (player.level.getRandom().nextFloat() - player.level.getRandom().nextFloat()) * 0.2F + 1.0F);
				stack.shrink(1);
				return InteractionResult.SUCCESS;
			}
		}

		return InteractionResult.PASS;
	}
}