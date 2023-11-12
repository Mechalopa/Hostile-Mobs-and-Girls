package com.github.mechalopa.hmag;

import com.github.mechalopa.hmag.network.ModPacketHandler;
import com.github.mechalopa.hmag.network.packet.SyncEnchantmentUpgradeMapPacket;
import com.github.mechalopa.hmag.world.item.RandomberryItem;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeManager;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.OnDatapackSyncEvent;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModDatapackEvents
{
	@SubscribeEvent
	public static void addListener(final AddReloadListenerEvent event)
	{
		event.addListener(EnchantmentUpgradeManager.INSTANCE);
	}

	@SubscribeEvent
	public static void onDatapackSync(OnDatapackSyncEvent event)
	{
		if (event.getPlayer() != null)
		{
			ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> event.getPlayer()), new SyncEnchantmentUpgradeMapPacket(EnchantmentUpgradeManager.getPropMap()));
		}
		else
		{
			for (ServerPlayer player : event.getPlayerList().getPlayers())
			{
				ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncEnchantmentUpgradeMapPacket(EnchantmentUpgradeManager.getPropMap()));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event)
	{
		if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof ServerPlayer)
		{
			ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), new SyncEnchantmentUpgradeMapPacket(EnchantmentUpgradeManager.getPropMap()));
		}
	}

	@SubscribeEvent
	public static void onTagsUpdated(final TagsUpdatedEvent event)
	{
		if (event.shouldUpdateStaticData())
		{
			RandomberryItem.refreshEffectList();
		}
	}
}