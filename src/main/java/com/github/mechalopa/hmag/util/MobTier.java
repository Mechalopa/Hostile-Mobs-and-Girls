package com.github.mechalopa.hmag.util;

public enum MobTier
{
//	IRON(1, ModTags.IRON_TIER, true),
//	GOLD(2, ModTags.GOLD_TIER, true),
//	SILVER(2, ModTags.SILVER_TIER, true),
//	EMERALD(2, ModTags.EMERALD_TIER, true),
//	DIAMOND(3, ModTags.DIAMOND_TIER, true),
//	RUBY(3, ModTags.RUBY_TIER, true),
//	SAPPHIRE(3, ModTags.SAPPHIRE_TIER, true),
//	NONE(0, null, false);
//
//	private final int tier;
//	private final ITag<EntityType<?>> tag;
//	private final boolean hasReward;
//
//	private MobTier(int tierIn, ITag<EntityType<?>> tagIn, boolean hasRewardIn)
//	{
//		this.tier = tierIn;
//		this.tag = tagIn;
//		this.hasReward = hasRewardIn;
//	}
//
//	public int getTier()
//	{
//		return this.tier;
//	}
//
//	@Nullable
//	private ITag<EntityType<?>> getTag()
//	{
//		return this.tag;
//	}
//
//	public boolean hasReward()
//	{
//		return this.hasReward;
//	}
//
//	@Nullable
//	public Item getDropItem(Random rand)
//	{
//		return getItem(this, rand);
//	}
//
//	@Nullable
//	private static Item getItem(MobTier mobTier, Random rand)
//	{
//		switch (mobTier)
//		{
//		case IRON:
//			return (rand.nextBoolean() && ModTags.getItem(ModTags.FORGE_COPPER_INGOTS) != null) ? ModItems.COPPER_NUGGET.get() : Items.IRON_NUGGET;
//		case GOLD:
//			return rand.nextInt(5) == 0 ? Items.IRON_NUGGET : Items.GOLD_NUGGET;
//		case SILVER:
//			return  rand.nextInt(5) == 0 ? Items.IRON_NUGGET : (ModTags.getItem(ModTags.FORGE_SILVER_INGOTS) != null ? ModItems.SILVER_NUGGET.get() : Items.GOLD_NUGGET);
//		case EMERALD:
//			return  rand.nextInt(4) == 0 ? Items.GOLD_NUGGET : ModItems.EMERALD_FRAGMENT.get();
//		case DIAMOND:
//			return ModItems.DIAMOND_FRAGMENT.get();
//		case RUBY:
//			return ModTags.getItem(ModTags.FORGE_RUBY_GEMS) != null ? (rand.nextInt(4) == 0 ? ModItems.DIAMOND_FRAGMENT.get() : ModItems.RUBY_FRAGMENT.get()) : (rand.nextInt(4) == 0 ? ModItems.EMERALD_FRAGMENT.get() : ModItems.DIAMOND_FRAGMENT.get());
//		case SAPPHIRE:
//			return ModTags.getItem(ModTags.FORGE_SAPPHIRE_GEMS) != null ? (rand.nextInt(4) == 0 ? ModItems.DIAMOND_FRAGMENT.get() : ModItems.SAPPHIRE_FRAGMENT.get()) : (rand.nextInt(4) == 0 ? ModItems.EMERALD_FRAGMENT.get() : ModItems.DIAMOND_FRAGMENT.get());
//		default:
//			return null;
//		}
//	}
//
//	public static MobTier getMobTier(EntityType<?> type)
//	{
//		if (type != null)
//		{
//			for (MobTier tier : MobTier.values())
//			{
//				if (ModTags.checkTagContains(tier.getTag(), type))
//				{
//					return tier;
//				}
//			}
//		}
//
//		return NONE;
//	}
}