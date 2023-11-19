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

public class EvilFlameItem extends EnchantmentUpgradeItem
{
	public EvilFlameItem(Item.Properties builder, ModItem.Properties builder2)
	{
		super(builder, builder2, new EnchantmentUpgradeItem.Properties().enchantment(Enchantments.FIRE_ASPECT, 0, 1).enchantment(Enchantments.FLAMING_ARROWS, 0, 0));
	}

	@Override
	public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand)
	{
		if (!player.level.isClientSide)
		{
			entity.setSecondsOnFire(3);
			player.crit(entity);
			player.level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.NEUTRAL, 1.0F, player.level.getRandom().nextFloat() * 0.4F + 0.8F);
			stack.shrink(1);
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}
}