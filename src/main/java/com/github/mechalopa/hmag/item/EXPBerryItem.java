package com.github.mechalopa.hmag.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EXPBerryItem extends Item
{
	public EXPBerryItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity livingEntity)
	{
		ItemStack stack1 = super.finishUsingItem(stack, worldIn, livingEntity);

		if (!worldIn.isClientSide && livingEntity instanceof PlayerEntity)
		{
			PlayerEntity player = (PlayerEntity)livingEntity;
            worldIn.addFreshEntity(new ExperienceOrbEntity(worldIn, player.getX(), player.getY() - 1.0D, player.getZ(), random.nextInt(9) + 12));
		}

		return stack1;
	}
}