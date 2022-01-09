package net.mechalopa.hmag.item;

import javax.annotation.Nullable;

import net.mechalopa.hmag.util.ModTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tags.ITag;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class ModItem extends Item
{
	private final boolean isFoil;
	private final ResistanceType resistanceType;
	private final boolean isCreativeTabChecking;
	@Nullable
	private final ResourceLocation tagId;
	private final int burnTime;

	public ModItem(Item.Properties builder, ModItem.Properties builder1)
	{
		super(builder);
		this.isFoil = builder1.isFoil;
		this.resistanceType = builder1.resistanceType;
		this.tagId = builder1.tagId;
		this.burnTime = builder1.burnTime;
		this.isCreativeTabChecking = builder1.isCreativeTabChecking;
	}

	@Override
	public boolean isFoil(ItemStack stack)
	{
		return this.isFoil;
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return ((this.resistanceType == ResistanceType.NETHER_STAR && damageSource.isExplosion()) || (this.resistanceType == ResistanceType.INVINCIBLE && damageSource != DamageSource.OUT_OF_WORLD)) ? false : super.canBeHurtBy(damageSource);
	}

	@Override
	public int getBurnTime(ItemStack stack, @Nullable IRecipeType<?> recipeType)
	{
		return this.burnTime > 0 ? this.burnTime : -1;
	}

	@Override
	protected boolean allowdedIn(ItemGroup group)
	{
		if (super.allowdedIn(group))
		{
			if (this.isCreativeTabChecking)
			{
				if (this.tagId != null)
				{
					ITag<Item> tag = ModTags.getItemTag(this.tagId);

					if (tag != null && !tag.getValues().isEmpty())
					{
						return true;
					}
				}

				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}

	public static class Properties
	{
		private boolean isFoil = false;
		private ResistanceType resistanceType = ResistanceType.NORMAL;
		private boolean isCreativeTabChecking = false;
		@Nullable
		private ResourceLocation tagId = null;
		private int burnTime = -1;

		public ModItem.Properties foil()
		{
			this.isFoil = true;
			return this;
		}

		public ModItem.Properties setResistanceType(ResistanceType resistanceTypeIn)
		{
			this.resistanceType = resistanceTypeIn;
			return this;
		}

		public ModItem.Properties burnTime(int burnTimeIn)
		{
			this.burnTime = burnTimeIn;
			return this;
		}

		public ModItem.Properties setCheckingItemTagId(ResourceLocation tagIdIn)
		{
			this.isCreativeTabChecking = true;
			this.tagId = tagIdIn;
			return this;
		}
	}

	public enum ResistanceType
	{
		NORMAL,
		NETHER_STAR,
		INVINCIBLE;
	}
}