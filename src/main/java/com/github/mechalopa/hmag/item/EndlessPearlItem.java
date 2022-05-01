package com.github.mechalopa.hmag.item;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SimpleFoiledItem;

public class EndlessPearlItem extends SimpleFoiledItem
{
	public EndlessPearlItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, Player playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		worldIn.playSound((Player)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerIn.getCooldowns().addCooldown(this, 20);

		if (!worldIn.isClientSide)
		{
			ThrownEnderpearl enderpearlentity = new ThrownEnderpearl(worldIn, playerIn);
			enderpearlentity.setItem(new ItemStack(Items.ENDER_PEARL));
			enderpearlentity.shootFromRotation(playerIn, playerIn.getXRot(), playerIn.getYRot(), 0.0F, 1.5F, 1.0F);

			if (playerIn.isCrouching())
			{
				enderpearlentity.setDeltaMovement(enderpearlentity.getDeltaMovement().scale(2.0F));
			}

			worldIn.addFreshEntity(enderpearlentity);
		}

		playerIn.awardStat(Stats.ITEM_USED.get(this));

		return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
	}
}