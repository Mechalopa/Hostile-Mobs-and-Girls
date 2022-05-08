package com.github.mechalopa.hmag.world.item;

import java.util.Iterator;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SimpleFoiledItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class PurificationClothItem extends SimpleFoiledItem
{
	public PurificationClothItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity)
	{
		if (!level.isClientSide)
		{
			Iterator<MobEffectInstance> itr = livingEntity.getActiveEffects().iterator();
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
					livingEntity.removeEffect(effect.getEffect());
				}
			}

			level.playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.ZOMBIE_VILLAGER_CURE, SoundSource.NEUTRAL, 0.25F, level.getRandom().nextFloat() * 0.1F + 0.9F);
		}

		if (livingEntity instanceof ServerPlayer)
		{
			ServerPlayer serverplayerentity = (ServerPlayer)livingEntity;
			CriteriaTriggers.CONSUME_ITEM.trigger(serverplayerentity, stack);
			serverplayerentity.awardStat(Stats.ITEM_USED.get(this));
		}

		if (livingEntity instanceof Player && !((Player)livingEntity).getAbilities().instabuild)
		{
			stack.shrink(1);
		}

		return stack;
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack)
	{
		return UseAnim.BOW;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
	{
		boolean flag = false;
		ItemStack stack = player.getItemInHand(hand);
		Iterator<MobEffectInstance> itr = player.getActiveEffects().iterator();

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
			player.startUsingItem(hand);
			return InteractionResultHolder.consume(stack);
		}
		else
		{
			return InteractionResultHolder.fail(stack);
		}
	}

	@Override
	public int getUseDuration(ItemStack stack)
	{
		return 16;
	}
}