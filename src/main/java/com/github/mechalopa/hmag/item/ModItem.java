package com.github.mechalopa.hmag.item;

import javax.annotation.Nullable;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.DamageSource;

public class ModItem extends Item
{
	private final boolean isFoil;
	private final ResistanceType resistanceType;
	private final int burnTime;

	public ModItem(Item.Properties builder, ModItem.Properties builder1)
	{
		super(builder);
		this.isFoil = builder1.isFoil;
		this.resistanceType = builder1.resistanceType;
		this.burnTime = builder1.burnTime;
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

	public static class Properties
	{
		private boolean isFoil = false;
		private ResistanceType resistanceType = ResistanceType.NORMAL;
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
	}

	public enum ResistanceType
	{
		NORMAL,
		NETHER_STAR,
		INVINCIBLE;
	}
}