package com.github.mechalopa.hmag.world.item;

import java.util.List;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.registry.ModEffects;

import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.ServerStatsCounter;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class InsomniaFruitItem extends Item implements ILevelItem
{
	public InsomniaFruitItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public boolean isFoil(ItemStack stack)
	{
		return ILevelItem.getItemLevel(stack) >= 5 || super.isFoil(stack);
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
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity)
	{
		int i = ILevelItem.getItemLevel(stack);
		ItemStack stack1 = stack.copy();
		ItemStack stack2 = super.finishUsingItem(stack, level, livingEntity);

		if (!level.isClientSide)
		{
			if (livingEntity instanceof ServerPlayer)
			{
				int j = 1800;

				if (i <= 0)
				{
					livingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, 180 * 20, 1));
					livingEntity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 180 * 20, 0));
					livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 180 * 20, 1));
					livingEntity.addEffect(new MobEffectInstance(ModEffects.COMBUSTION.get(), 180 * 20, 0));
					level.playSound((Player)null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 0.8F, 0.8F + level.getRandom().nextFloat() * 0.4F);
				}
				else
				{
					if (i >= 5)
					{
						livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 20, 1));
						livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 300 * 20, 0));
						livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300 * 20, 0));
						livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 120 * 20, 3));
						j = 1200;
					}
					else if (i >= 4)
					{
						livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 15 * 20, 0));
						livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 150 * 20, 0));
						livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 120 * 20, 1));
						j = 1400;
					}
					else if (i >= 3)
					{
						livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 10 * 20, 0));
						livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 30 * 20, 0));
						livingEntity.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 120 * 20, 0));
						j = 1600;
					}
					else if (i >= 2)
					{
						livingEntity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 5 * 20, 0));
					}
				}

				((Player)livingEntity).getCooldowns().addCooldown(this, j);
			}
		}

		return i > 0 ? stack1 : stack2;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag)
	{
		final int i = ILevelItem.getItemLevel(stack);
		TranslatableComponent component = new TranslatableComponent("text.hmag.level", i + 1);
		component.withStyle(i >= 5 ? ChatFormatting.LIGHT_PURPLE : (i >= 4 ? ChatFormatting.AQUA : (i >= 2 ? ChatFormatting.YELLOW : (i <= 0 ? ChatFormatting.RED : ChatFormatting.GRAY))));
		list.add(component);
	}

	@Override
	public void fillItemCategory(CreativeModeTab tab, NonNullList<ItemStack> list)
	{
		if (this.allowdedIn(tab))
		{
			ItemStack stack = new ItemStack(this);
			CompoundTag compoundnbt = stack.getOrCreateTag();
			compoundnbt.putByte(ILevelItem.LEVEL_KEY, (byte)this.getMaxLevel());
			list.add(stack);
		}
	}

	@Override
	public int getMaxLevel()
	{
		return 5;
	}
}