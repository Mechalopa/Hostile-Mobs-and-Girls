package com.github.mechalopa.hmag.world.item;

import java.util.function.Supplier;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.util.ModTags;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings("deprecation")
public enum ModArmorMaterial implements ArmorMaterial
{
	ANCIENT(HMaG.MODID + ":ancient", 34, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_IRON, 2.5F, 0.05F, () -> {
		return Ingredient.of(ModTags.ItemTags.ANCIENT_ARMOR_REPAIR_ITEMS);
	}),
	NECROTIC_CHAIN(HMaG.MODID + ":necrotic_chainmail", 19, new int[]{2, 5, 6, 2}, 15, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> {
		return Ingredient.of(ModTags.ItemTags.NECROTIC_CHAINMAIL_ARMOR_REPAIR_ITEMS);
	});

	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyLoadedValue<Ingredient> repairIngredient;

	private ModArmorMaterial(String nameIn, int durabilityMultiplierIn, int[] slotProtectionsIn, int enchantmentIn, SoundEvent sound, float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairItems)
	{
		this.name = nameIn;
		this.durabilityMultiplier = durabilityMultiplierIn;
		this.slotProtections = slotProtectionsIn;
		this.enchantmentValue = enchantmentIn;
		this.sound = sound;
		this.toughness = toughnessIn;
		this.knockbackResistance = knockbackResistanceIn;
		this.repairIngredient = new LazyLoadedValue<>(repairItems);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlot slot)
	{
		return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlot slot)
	{
		return this.slotProtections[slot.getIndex()];
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
}