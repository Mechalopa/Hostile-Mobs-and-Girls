package com.github.mechalopa.hmag.world.item;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ExperienceBerryItem extends Item
{
	public ExperienceBerryItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity)
	{
		ItemStack stack1 = super.finishUsingItem(stack, level, livingEntity);

		if (!level.isClientSide && livingEntity instanceof Player)
		{
			Player player = (Player)livingEntity;
            level.addFreshEntity(new ExperienceOrb(level, player.getX(), player.getY() - 1.0D, player.getZ(), level.getRandom().nextInt(9) + 12));
		}

		return stack1;
	}
}