package com.github.mechalopa.hmag.world.item;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class InsomniaSwordItem extends ModSwordItem implements ILevelItem
{
	public static final UUID INSOMNIA_SWORD_ATTACK_DAMAGE_UUID = UUID.fromString("04C38766-C1A5-31D4-410B-C6B4BE3B7DD2");

	public InsomniaSwordItem(Tier tier, Properties builder)
	{
		super(tier, 1.0F, -2.4F, 1750, 15, builder);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean isSelected)
	{
		if (!level.isClientSide)
		{
			if (entity instanceof ServerPlayer)
			{
				CompoundTag compoundnbt = stack.getOrCreateTag();
				int itemLevel = !compoundnbt.contains(ILevelItem.LEVEL_KEY) ? 0 : (int)compoundnbt.getByte(ILevelItem.LEVEL_KEY);
				ServerStatsCounter serverstatisticsmanager = ((ServerPlayer)entity).getStats();
				final int i = 24000;
				final int j = Math.max(serverstatisticsmanager.getValue(Stats.CUSTOM.get(Stats.TIME_SINCE_REST)), 1);
				int k = j / i;
				k = Mth.clamp(k - 2, 0, 5);

				if (itemLevel != k)
				{
					compoundnbt.putByte(ILevelItem.LEVEL_KEY, (byte)k);
				}
			}
		}
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int count)
	{
		if (livingEntity instanceof Player)
		{
			if (this.getUseDuration(stack) - count < 8)
			{
				return;
			}

			Player player = (Player)livingEntity;

			if (!level.isClientSide)
			{
				player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 10 * 20, 0));
			}

			level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1.0F, level.getRandom().nextFloat() * 0.2F + 0.9F);
			player.awardStat(Stats.ITEM_USED.get(this));
		}
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack)
	{
		return UseAnim.BOW;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		boolean flag = ILevelItem.getItemLevel(stack) > 0 || player.isCreative();

		if (flag && player.isCrouching())
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
		return 72000;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag)
	{
		final int i = ILevelItem.getItemLevel(stack);
		list.add(Component.translatable("text.hmag.level", i + 1).withStyle(i >= 5 ? ChatFormatting.LIGHT_PURPLE : (i >= 4 ? ChatFormatting.AQUA : (i >= 2 ? ChatFormatting.YELLOW : (i <= 0 ? ChatFormatting.RED : ChatFormatting.GRAY)))));
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list)
	{
		if (this.allowedIn(tab))
		{
			ItemStack stack = new ItemStack(this);
			CompoundTag compoundnbt = stack.getOrCreateTag();
			compoundnbt.putByte(ILevelItem.LEVEL_KEY, (byte)this.getMaxLevel());
			list.add(stack);
		}
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack stack1)
	{
		return stack1.is(ModTags.ItemTags.INSOMNIA_SWORD_REPAIR_ITEMS);
	}

	@Override
	public int getMaxLevel()
	{
		return 5;
	}
}