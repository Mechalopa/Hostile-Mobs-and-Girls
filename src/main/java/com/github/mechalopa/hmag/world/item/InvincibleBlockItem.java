package com.github.mechalopa.hmag.world.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class InvincibleBlockItem extends BlockItem
{
	public InvincibleBlockItem(Block blockIn, Item.Properties builder)
	{
		super(blockIn, builder);
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return !damageSource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) ? false : super.canBeHurtBy(damageSource);
	}
}