package hmag.loot;

import java.util.List;

import com.google.common.collect.ImmutableList;

import hmag.HMaG;
import hmag.ModConfigs;
import hmag.util.ModUtils;
import net.minecraft.loot.LootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.TableLootEntry;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HMaG.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class AddLootTables
{
	private static final List<String> CHEST_TABLES = ImmutableList.of(
			"abandoned_mineshaft",
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
	public static void loadLootTable(LootTableLoadEvent event)
	{
		if (event.getName() != null && event.getTable() != null)
		{
			String prefix = ModUtils.MINECRAFT_ID + ":chests/";
			String name = event.getName().toString();

			if (ModConfigs.cachedServer.ADDITIONAL_CHEST_LOOTS)
			{
				if (name.startsWith(prefix) && CHEST_TABLES.contains(name.substring(prefix.length())))
				{
					String file = name.substring((ModUtils.MINECRAFT_ID + ":").length());
					event.getTable().addPool(getInjectPool(file));
				}
			}
		}
	}

	private static LootPool getInjectPool(String name)
	{
		return LootPool.lootPool()
				.add(getInjectEntry(name))
				.name("hmag_inject")
				.build();
	}

	private static LootEntry.Builder<?> getInjectEntry(String name)
	{
		ResourceLocation r = new ResourceLocation(HMaG.MODID, "inject/" + name);
		return TableLootEntry.lootTableReference(r).setWeight(1);
	}
}