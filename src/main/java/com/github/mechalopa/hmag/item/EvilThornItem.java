package com.github.mechalopa.hmag.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;

public class EvilThornItem extends EnchantmentUpgradeItem
{
	public EvilThornItem(Item.Properties builder)
	{
		super(builder, new EnchantmentUpgradeItem.Properties().enchantment(Enchantments.THORNS, 0, 9));
	}

	@Override
	public ActionResultType interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, Hand hand)
	{
		if (!player.level.isClientSide)
		{
			if (entity.hurt(DamageSource.thorns(player), 1.0F))
			{
				player.crit(entity);
				player.level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.GENERIC_HURT, SoundCategory.NEUTRAL, 0.5F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
				stack.shrink(1);
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}
}