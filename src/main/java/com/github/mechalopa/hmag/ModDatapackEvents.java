package com.github.mechalopa.hmag;

import java.util.List;

import com.github.mechalopa.hmag.network.ModPacketHandler;
import com.github.mechalopa.hmag.network.packet.SyncEnchantmentUpgradeMapPacket;
import com.github.mechalopa.hmag.world.item.RandomberryItem;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeItemManager;
import com.google.common.collect.ImmutableList;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.LootTableLoadEvent;
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
		event.addListener(EnchantmentUpgradeItemManager.INSTANCE);
	}

	@SubscribeEvent
	public static void onDatapackSync(OnDatapackSyncEvent event)
	{
		if (event.getPlayer() != null)
		{
			ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> event.getPlayer()), new SyncEnchantmentUpgradeMapPacket(EnchantmentUpgradeItemManager.getPropMap()));
		}
		else
		{
			for (ServerPlayer player : event.getPlayerList().getPlayers())
			{
				ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new SyncEnchantmentUpgradeMapPacket(EnchantmentUpgradeItemManager.getPropMap()));
			}
		}
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent event)
	{
		if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof ServerPlayer)
		{
			ModPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer)event.getEntity()), new SyncEnchantmentUpgradeMapPacket(EnchantmentUpgradeItemManager.getPropMap()));
		}
	}

	private static final List<String> CHEST_TABLES = ImmutableList.of(
			"abandoned_mineshaft",
			"ancient_city",
			"ancient_city_ice_box",
			"bastion_hoglin_stable",
			"bastion_other",
			"bastion_treasure",
			"buried_treasure",
			"desert_pyramid",
			"end_city_treasure",
			"igloo_chest",
			"jungle_temple_dispenser",
			"jungle_temple",
			"nether_bridge",
			"pillager_outpost",
			"ruined_portal",
			"simple_dungeon",
			"stronghold_corridor",
			"stronghold_crossing",
			"stronghold_library",
			"underwater_ruin_big",
			"woodland_mansion",
			"village/village_armorer",
			"village/village_temple",
			"village/village_toolsmith",
			"village/village_weaponsmith");

	@SubscribeEvent
	public static void onLootTableLoad(LootTableLoadEvent event)
	{
		if (event.getName() != null && event.getTable() != null)
		{
			if (ModConfigs.cachedServer.ADDITIONAL_CHEST_LOOTS)
			{
				String prefix = "minecraft:chests/";
				String name = event.getName().toString();

				if (name.startsWith(prefix) && CHEST_TABLES.contains(name.substring(prefix.length())))
				{
					String file = name.substring(("minecraft:").length());
					event.getTable().addPool(getInjectPool(file));
				}
			}
		}
	}

	private static LootPool getInjectPool(String name)
	{
		return LootPool.lootPool().add(getInjectEntry(name)).name("hmag_inject").build();
	}

	private static LootPoolEntryContainer.Builder<?> getInjectEntry(String name)
	{
		ResourceLocation r = new ResourceLocation(HMaG.MODID, "inject/" + name);
		return LootTableReference.lootTableReference(r).setWeight(1);
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