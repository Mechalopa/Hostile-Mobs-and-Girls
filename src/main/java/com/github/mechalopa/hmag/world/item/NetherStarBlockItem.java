package com.github.mechalopa.hmag.world.item;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class NetherStarBlockItem extends BlockItem
{
	public NetherStarBlockItem(Block block, Item.Properties builder)
	{
		super(block, builder);
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return damageSource.is(DamageTypeTags.IS_EXPLOSION) ? false : super.canBeHurtBy(damageSource);
	}
}