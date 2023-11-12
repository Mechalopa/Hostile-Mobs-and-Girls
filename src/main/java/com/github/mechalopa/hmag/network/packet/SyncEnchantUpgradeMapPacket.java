package com.github.mechalopa.hmag.network.packet;

import java.util.HashMap;
import java.util.function.Supplier;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeItemManager;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeItemManager.EnchantmentUpgradeItemProp;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SyncEnchantUpgradeMapPacket
{
	private final HashMap<EnchantmentUpgradeItemProp, Integer> propMap;

	public SyncEnchantUpgradeMapPacket(HashMap<EnchantmentUpgradeItemProp, Integer> map)
	{
		this.propMap = map;
	}

	public static void encode(SyncEnchantUpgradeMapPacket msg, FriendlyByteBuf buf)
	{
		HashMap<EnchantmentUpgradeItemProp, Integer> map = msg.getPropMap();
		EnchantmentUpgradeItemManager.encodeMap(map, buf);
	}

	public static SyncEnchantUpgradeMapPacket decode(FriendlyByteBuf buf)
	{
		HashMap<EnchantmentUpgradeItemProp, Integer> map = EnchantmentUpgradeItemManager.decodeMap(buf);
		return new SyncEnchantUpgradeMapPacket(map);
	}

	public static void handle(SyncEnchantUpgradeMapPacket msg, Supplier<NetworkEvent.Context> context)
	{
		context.get().enqueueWork(() -> EnchantmentUpgradeItemManager.INSTANCE.updateFromServer(msg.getPropMap()));
		context.get().setPacketHandled(true);
		HMaG.LOGGER.debug("hogehogehogehoge");
	}

	private HashMap<EnchantmentUpgradeItemProp, Integer> getPropMap()
	{
		return this.propMap;
	}
}