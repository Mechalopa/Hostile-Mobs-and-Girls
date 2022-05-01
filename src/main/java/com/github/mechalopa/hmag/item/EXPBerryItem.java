package com.github.mechalopa.hmag.item;

import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EXPBerryItem extends Item
{
	public EXPBerryItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity livingEntity)
	{
		ItemStack stack1 = super.finishUsingItem(stack, worldIn, livingEntity);

		if (!worldIn.isClientSide && livingEntity instanceof Player)
		{
			Player player = (Player)livingEntity;
            worldIn.addFreshEntity(new ExperienceOrb(worldIn, player.getX(), player.getY() - 1.0D, player.getZ(), random.nextInt(9) + 12));
		}

		return stack1;
	}
}