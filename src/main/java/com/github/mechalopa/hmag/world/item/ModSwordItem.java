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

	public ModSwordItem(Tier tier, Item.Properties builder)
	{
		this(tier, 3.0F, -2.4F, builder);
	}

	public ModSwordItem(Tier tier, float attackDamage, float attackSpeed, Item.Properties builder)
	{
		this(tier, attackDamage, attackSpeed, tier.getUses(), tier.getEnchantmentValue(), builder);
	}

	public ModSwordItem(Tier tier, float attackDamage, float attackSpeed, int maxDamage, int enchantability, Item.Properties builder)
	{
		super(tier, Mth.floor(attackDamage), attackSpeed, builder);
		this.attackDamage = attackDamage + tier.getAttackDamageBonus();
		this.attackSpeed = attackSpeed;
		Builder<Attribute, AttributeModifier> builder1 = ImmutableMultimap.builder();
		builder1.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
		builder1.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder1.build();
		this.maxDamage = maxDamage;
		this.enchantability = enchantability;
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