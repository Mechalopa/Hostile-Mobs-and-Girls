package com.github.mechalopa.hmag.world.item.crafting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.github.mechalopa.hmag.HMaG;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.Mth;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.registries.ForgeRegistries;

public class EnchantmentUpgradeItemManager extends SimpleJsonResourceReloadListener
{
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
	private static final String DIRECTORY = HMaG.MODID + "/enchantment_upgrade";
	public static final EnchantmentUpgradeItemManager INSTANCE = new EnchantmentUpgradeItemManager();
	private static HashMap<EnchantmentUpgradeItemProp, Integer> PROP_MAP = new HashMap<>();

	public static record ValuesCodec(List<PropCodec> values)
	{
		public static final Codec<ValuesCodec> CODEC = RecordCodecBuilder.create((p -> {
			return p.group(PropCodec.CODEC.listOf().fieldOf("values").forGetter(ValuesCodec::values)).apply(p, ValuesCodec::new);
		}));
	}

	public static record PropCodec(Item addition, Item template, String enchantment, int min, int max)
	{
		public static final Codec<PropCodec> CODEC = RecordCodecBuilder.create((p -> {
			return p.group(ForgeRegistries.ITEMS.getCodec().optionalFieldOf("addition", Items.BARRIER).forGetter(PropCodec::addition),
					ForgeRegistries.ITEMS.getCodec().optionalFieldOf("template", Items.BARRIER).forGetter(PropCodec::template),
					Codec.STRING.fieldOf("enchantment").forGetter(PropCodec::enchantment),
					Codec.INT.optionalFieldOf("minLevel", 0).forGetter(PropCodec::min),
					Codec.INT.optionalFieldOf("maxLevel", 0).forGetter(PropCodec::max)
					).apply(p, PropCodec::new);
		}));
	}

	public EnchantmentUpgradeItemManager()
	{
		super(GSON, DIRECTORY);
	}

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> map, ResourceManager manager, ProfilerFiller filler)
	{
		PROP_MAP.clear();
		map.forEach((r, jsonElement) -> {
			try
			{
				DataResult<ValuesCodec> dataResult = ValuesCodec.CODEC.parse(JsonOps.INSTANCE, jsonElement);
				dataResult.resultOrPartial(result -> {}).ifPresent(this::addToPropMap);
			}
			catch (Exception e)
			{
				HMaG.LOGGER.error("Failed to parse enchantment upgrade file {}", r, e);
			}
		});
	}

	private void addToPropMap(ValuesCodec values)
	{
		values.values().forEach(entry -> PROP_MAP.put(new EnchantmentUpgradeItemProp(entry.addition, entry.template, getEnchantmentSupplier(entry.enchantment), Mth.clamp(entry.min, 0, 255), Mth.clamp(entry.max, entry.min, 255)), 0));
	}

	public void updateFromServer(HashMap<EnchantmentUpgradeItemProp, Integer> map)
	{
		PROP_MAP = map;
	}

	public static void encodeMap(HashMap<EnchantmentUpgradeItemProp, Integer> map, FriendlyByteBuf buf)
	{
		buf.writeVarInt(map.size());

		for (EnchantmentUpgradeItemProp prop : map.keySet())
		{
			buf.writeUtf(ForgeRegistries.ITEMS.getKey(prop.getAddition()).toString());
			buf.writeUtf(ForgeRegistries.ITEMS.getKey(prop.getTemplate()).toString());
			buf.writeUtf(ForgeRegistries.ENCHANTMENTS.getKey(prop.getEnchantment()).toString());
			buf.writeVarInt(prop.getMinLevel());
			buf.writeVarInt(prop.getMaxLevel());
			HMaG.LOGGER.debug("B");
		}
	}

	public static HashMap<EnchantmentUpgradeItemProp, Integer> decodeMap(FriendlyByteBuf buf)
	{
		HashMap<EnchantmentUpgradeItemProp, Integer> map = new HashMap<>();
		int size = buf.readVarInt();

		for (int i = 0; i < size; ++i)
		{
			Item addition = EnchantmentUpgradeItemManager.getItemSupplier(buf.readUtf()).get();
			Item template = EnchantmentUpgradeItemManager.getItemSupplier(buf.readUtf()).get();
			Supplier<Enchantment> enchantmentSupplier = EnchantmentUpgradeItemManager.getEnchantmentSupplier(buf.readUtf());
			int minLevel = buf.readVarInt();
			int maxLevel = buf.readVarInt();
			map.put(new EnchantmentUpgradeItemProp(addition, template, enchantmentSupplier, minLevel, maxLevel), i);
			HMaG.LOGGER.debug("A");
		}

		return map;
	}

	private static Supplier<Item> getItemSupplier(String name)
	{
		return ForgeRegistries.ITEMS.getHolder(new ResourceLocation(name)).orElseThrow();
	}

	private static Supplier<Enchantment> getEnchantmentSupplier(String name)
	{
		return ForgeRegistries.ENCHANTMENTS.getHolder(new ResourceLocation(name)).orElseThrow();
	}

	public static HashMap<EnchantmentUpgradeItemProp, Integer> getPropMap()
	{
		return PROP_MAP;
	}

	public static class EnchantmentUpgradeItemProp
	{
		private final Item addition;
		private final Item template;
		private final Supplier<Enchantment> enchantmentSupplier;
		private final int minLevel;
		private final int maxLevel;

		public EnchantmentUpgradeItemProp(Item addition, Item template, Supplier<Enchantment> enchantmentSupplier, int min, int max)
		{
			this.addition = addition;
			this.template = template;
			this.enchantmentSupplier = enchantmentSupplier;
			this.minLevel = min;
			this.maxLevel = max;
		}

		public Item getAddition()
		{
			return this.addition;
		}

		public Item getTemplate()
		{
			return this.template;
		}

		@Nullable
		public Enchantment getEnchantment()
		{
			return this.enchantmentSupplier.get();
		}

		public int getMinLevel()
		{
			return this.minLevel;
		}

		public int getMaxLevel()
		{
			return this.maxLevel;
		}
	}
}