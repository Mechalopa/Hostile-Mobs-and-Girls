package com.github.mechalopa.hmag.client;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.item.ModBowItem;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ModClientEvents
{
	@SubscribeEvent
	public void onFOVUpdate(FOVUpdateEvent event)
	{
		if (event.getEntity() != null)
		{
			PlayerEntity player = event.getEntity();

			if (player.isUsingItem() && player.getUseItem() != null && !player.getUseItem().isEmpty() && player.getUseItem().getItem() instanceof ModBowItem)
			{
				float f = event.getFov();
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

				event.setNewfov(f);
			}
		}
	}
}