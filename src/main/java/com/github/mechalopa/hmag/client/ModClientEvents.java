package com.github.mechalopa.hmag.client;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.item.ModBowItem;
import com.github.mechalopa.hmag.world.item.crafting.SuspiciousStewUpgradeRecipe;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ModClientEvents
{
	private static final Component UPGRADED_SUSPICIOUS_STEW_TOOLTIP = Component.translatable("text.hmag.upgraded_suspicious_stew").withStyle(ChatFormatting.LIGHT_PURPLE);

	@SubscribeEvent
	public void onFOVModifier(ComputeFovModifierEvent event)
	{
		if (event.getPlayer() != null)
		{
			Player player = event.getPlayer();

			if (player.isUsingItem() && player.getUseItem() != null && !player.getUseItem().isEmpty() && player.getUseItem().getItem() instanceof ModBowItem)
			{
				float f = event.getFovModifier();
				ModBowItem bow = (ModBowItem)player.getUseItem().getItem();
				int i = player.getTicksUsingItem();
				float f1 = (float)i / 20.0F;

				if (f1 > 1.0F)
				{
					f1 = 1.0F;
				}
				else
				{
					f1 = f1 * f1;
				}

				f *= 1.0F - f1 * bow.getBowFOV(player.getUseItem(), player);

				event.setNewFovModifier(f);
			}
		}
	}

	@SubscribeEvent
	public void addItemTooltip(final ItemTooltipEvent event)
	{
		if (!event.getToolTip().isEmpty() && event.getItemStack() != null && event.getItemStack().is(Items.SUSPICIOUS_STEW) && event.getItemStack().hasTag() && event.getItemStack().getTag().getBoolean(SuspiciousStewUpgradeRecipe.UPGRADED_KEY))
		{
			event.getToolTip().add(UPGRADED_SUSPICIOUS_STEW_TOOLTIP);
		}
	}
}