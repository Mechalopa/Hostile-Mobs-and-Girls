package com.github.mechalopa.hmag.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.level.Level;

public class EndlessPearlItem extends SimpleFoiledItem
{
	public EndlessPearlItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
	{
		ItemStack itemstack = player.getItemInHand(hand);
		level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
		player.getCooldowns().addCooldown(this, 20);

		if (!level.isClientSide)
		{
			ThrownEnderpearl enderpearl = new ThrownEnderpearl(level, player);
			enderpearl.setItem(new ItemStack(Items.ENDER_PEARL));
			enderpearl.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);

			if (player.isCrouching())
			{
				enderpearl.setDeltaMovement(enderpearl.getDeltaMovement().scale(2.0F));
			}

			level.addFreshEntity(enderpearl);
		}

		player.awardStat(Stats.ITEM_USED.get(this));

		return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
	}
}