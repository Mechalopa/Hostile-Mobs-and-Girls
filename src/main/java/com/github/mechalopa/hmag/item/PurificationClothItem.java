package com.github.mechalopa.hmag.item;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SimpleFoiledItem;

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
			Iterator<MobEffectInstance> itr = livingEntityIn.getActiveEffects().iterator();
			Set<MobEffectInstance> set = Sets.newHashSet();

			while (itr.hasNext())
			{
				MobEffectInstance effect = itr.next();

				if (effect.getEffect() != null && !effect.getEffect().isBeneficial())
				{
					set.add(effect);
				}
			}

			if (!set.isEmpty())
			{
				for (MobEffectInstance effect : set)
				{
					livingEntityIn.removeEffect(effect.getEffect());
				}
			}

			worldIn.playSound((Player)null, livingEntityIn.getX(), livingEntityIn.getY(), livingEntityIn.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundCategory.NEUTRAL, 0.25F, random.nextFloat() * 0.1F + 0.9F);
		}

		if (livingEntityIn instanceof ServerPlayer)
		{
			ServerPlayer serverplayerentity = (ServerPlayer)livingEntityIn;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stackIn);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (livingEntityIn instanceof Player && !((Player)livingEntityIn).getAbilities().instabuild)
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
	public ActionResult<ItemStack> use(World worldIn, Player playerIn, Hand hand)
	{
		boolean flag = false;
		ItemStack stack = playerIn.getItemInHand(hand);
		Iterator<MobEffectInstance> itr = playerIn.getActiveEffects().iterator();

		while (itr.hasNext())
		{
			MobEffectInstance effect = itr.next();

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