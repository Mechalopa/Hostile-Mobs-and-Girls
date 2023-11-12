package com.github.mechalopa.hmag.network.packet;

import java.util.HashMap;
import java.util.function.Supplier;

import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeManager;
import com.github.mechalopa.hmag.world.item.crafting.EnchantmentUpgradeManager.EnchantmentUpgradeProp;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SyncEnchantmentUpgradeMapPacket
{
	private final HashMap<EnchantmentUpgradeProp, Integer> propMap;

	public SyncEnchantmentUpgradeMapPacket(HashMap<EnchantmentUpgradeProp, Integer> map)
	{
		this.propMap = map;
	}

	public static void encode(SyncEnchantmentUpgradeMapPacket msg, FriendlyByteBuf buf)
	{
		HashMap<EnchantmentUpgradeProp, Integer> map = msg.getPropMap();
		EnchantmentUpgradeManager.encodeMap(map, buf);
	}

	public static SyncEnchantmentUpgradeMapPacket decode(FriendlyByteBuf buf)
	{
		HashMap<EnchantmentUpgradeProp, Integer> map = EnchantmentUpgradeManager.decodeMap(buf);
		return new SyncEnchantmentUpgradeMapPacket(map);
	}

	public static void handle(SyncEnchantmentUpgradeMapPacket msg, Supplier<NetworkEvent.Context> context)
	{
		context.get().enqueueWork(() -> EnchantmentUpgradeManager.INSTANCE.updateFromServer(msg.getPropMap()));
		context.get().setPacketHandled(true);
	}

	private HashMap<EnchantmentUpgradeProp, Integer> getPropMap()
	{
		return this.propMap;
	}
}