package com.github.mechalopa.hmag.world.item;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.RecipeType;

public class ModSwordItem extends SwordItem
{
	private final float attackDamage;
	private final float attackSpeed;
	private final int maxDamage;
	private final int enchantability;
	private final Multimap<Attribute, AttributeModifier> attributeModifiers;

	public ModSwordItem(Tier tier, Item.Properties builderIn)
	{
		this(tier, 3.0F, -2.4F, builderIn);
	}

	public ModSwordItem(Tier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builderIn)
	{
		this(tier, attackDamageIn, attackSpeedIn, tier.getUses(), tier.getEnchantmentValue(), builderIn);
	}

	public ModSwordItem(Tier tier, float attackDamageIn, float attackSpeedIn, int maxDamageIn, int enchantabilityIn, Item.Properties builderIn)
	{
		super(tier, Mth.floor(attackDamageIn), attackSpeedIn, builderIn);
		this.attackDamage = attackDamageIn + tier.getAttackDamageBonus();
		this.attackSpeed = attackSpeedIn;
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
		this.maxDamage = maxDamageIn;
		this.enchantability = enchantabilityIn;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot equipmentSlot)
	{
		return equipmentSlot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
	}

	@Override
	public float getDamage()
	{
		return this.attackDamage;
	}

	@Override
	public int getMaxDamage(ItemStack stack)
	{
		return this.maxDamage;
	}

	@Override
	public int getEnchantmentValue()
	{
		return this.enchantability;
	}

	@Override
	public int getBurnTime(ItemStack stack, @Nullable RecipeType<?> recipeType)
	{
		return this.getTier() == Tiers.WOOD ? 200 : super.getBurnTime(stack, recipeType);
	}
}