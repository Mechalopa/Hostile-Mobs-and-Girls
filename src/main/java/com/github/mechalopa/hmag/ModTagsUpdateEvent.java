package com.github.mechalopa.hmag;

import com.github.mechalopa.hmag.world.item.RandomberryItem;

import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModTagsUpdateEvent
{
	@SubscribeEvent
	public static void onTagsUpdated(TagsUpdatedEvent event)
	{
		if (event.shouldUpdateStaticData())
		{
			RandomberryItem.updateEffectList();
		}
	}
}