package com.github.mechalopa.hmag.item;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SimpleFoiledItem;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class PurificationClothItem extends SimpleFoiledItem
{
	public PurificationClothItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stackIn, World worldIn, LivingEntity livingEntityIn)
	{
		if (!worldIn.isClientSide)
		{
			Iterator<EffectInstance> itr = livingEntityIn.getActiveEffects().iterator();
			Set<EffectInstance> set = Sets.newHashSet();

			while (itr.hasNext())
			{
				EffectInstance effect = itr.next();

				if (effect.getEffect() != null && !effect.getEffect().isBeneficial())
				{
					set.add(effect);
				}
			}

			if (!set.isEmpty())
			{
				for (EffectInstance effect : set)
				{
					livingEntityIn.removeEffect(effect.getEffect());
				}
			}

			worldIn.playSound((PlayerEntity)null, livingEntityIn.getX(), livingEntityIn.getY(), livingEntityIn.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL, 0.25F, random.nextFloat() * 0.1F + 0.9F);
		}

		if (livingEntityIn instanceof ServerPlayerEntity)
		{
			ServerPlayerEntity serverplayerentity = (ServerPlayerEntity)livingEntityIn;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stackIn);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (livingEntityIn instanceof PlayerEntity && !((PlayerEntity)livingEntityIn).abilities.instabuild)
		{
			stackIn.shrink(1);
		}

		return stackIn;
	}

	@Override
	public UseAction getUseAnimation(ItemStack stack)
	{
		return UseAction.BOW;
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand hand)
	{
		boolean flag = false;
		ItemStack stack = playerIn.getItemInHand(hand);
		Iterator<EffectInstance> itr = playerIn.getActiveEffects().iterator();

		while (itr.hasNext())
		{
			EffectInstance effect = itr.next();

			if (effect.getEffect() != null && !effect.getEffect().isBeneficial())
			{
				flag = true;
				break;
			}
		}

		if (flag)
		{
			playerIn.startUsingItem(hand);
			return ActionResult.consume(stack);
		}
		else
		{
			return ActionResult.fail(stack);
		}
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 16;
	}
}