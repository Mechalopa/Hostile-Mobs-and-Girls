package com.github.mechalopa.hmag.world.item;

import java.util.EnumMap;
import java.util.function.Supplier;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public enum ModArmorMaterial implements StringRepresentable, ArmorMaterial
{
	ANCIENT(HMaG.MODID + ":ancient", 34, Util.make(new EnumMap<>(ArmorItem.Type.class), (p) -> {
		p.put(ArmorItem.Type.BOOTS, 3);
		p.put(ArmorItem.Type.LEGGINGS, 6);
		p.put(ArmorItem.Type.CHESTPLATE, 8);
		p.put(ArmorItem.Type.HELMET, 3);
	}), 15, SoundEvents.ARMOR_EQUIP_IRON, 2.5F, 0.05F, () -> {
		return Ingredient.of(ModTags.ItemTags.ANCIENT_ARMOR_REPAIR_ITEMS);
	}),
	NECROTIC_CHAIN(HMaG.MODID + ":necrotic_chainmail", 19, Util.make(new EnumMap<>(ArmorItem.Type.class), (p) -> {
		p.put(ArmorItem.Type.BOOTS, 2);
		p.put(ArmorItem.Type.LEGGINGS, 5);
		p.put(ArmorItem.Type.CHESTPLATE, 6);
		p.put(ArmorItem.Type.HELMET, 2);
	}), 15, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> {
		return Ingredient.of(ModTags.ItemTags.NECROTIC_CHAINMAIL_ARMOR_REPAIR_ITEMS);
	});

	private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
		p_266653_.put(ArmorItem.Type.BOOTS, 13);
		p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
		p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
		p_266653_.put(ArmorItem.Type.HELMET, 11);
	});
	private final String name;
	private final int durabilityMultiplier;
	private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	private ModArmorMaterial(String nameIn, int durabilityMultiplierIn, EnumMap<ArmorItem.Type, Integer> protectionFunctionForTypeIn, int enchantmentIn, SoundEvent sound, float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairItems)
	{
		this.name = nameIn;
		this.durabilityMultiplier = durabilityMultiplierIn;
		this.protectionFunctionForType = protectionFunctionForTypeIn;
		this.enchantmentValue = enchantmentIn;
		this.sound = sound;
		this.toughness = toughnessIn;
		this.knockbackResistance = knockbackResistanceIn;
		this.repairIngredient = new LazyLoadedValue<>(repairItems);
	}

	@Override
	public int getDurabilityForType(Type type)
	{
		return HEALTH_FUNCTION_FOR_TYPE.get(type) * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForType(Type type)
	{
		return this.protectionFunctionForType.get(type);
	}

	@Override
	public int getEnchantmentValue()
	{
		return this.enchantmentValue;
	}

	@Override
	public SoundEvent getEquipSound()
	{
		return this.sound;
	}

	@Override
	public Ingredient getRepairIngredient()
	{
		return this.repairIngredient.get();
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public String getName()
	{
		return this.name;
	}

	@Override
	public float getToughness()
	{
		return this.toughness;
	}

	@Override
	public float getKnockbackResistance()
	{
		return this.knockbackResistance;
	}

	@Override
	public String getSerializedName()
	{
		return this.name;
	}
}