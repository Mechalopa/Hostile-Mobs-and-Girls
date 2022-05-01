package com.github.mechalopa.hmag.item;

import java.util.function.Supplier;

import com.github.mechalopa.hmag.HMaG;
import com.github.mechalopa.hmag.registry.ModItems;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public enum ModArmorMaterial implements IArmorMaterial
{
	ANCIENT(HMaG.MODID + ":ancient", 34, new int[]{3, 6, 8, 3}, 15, SoundEvents.ARMOR_EQUIP_IRON, 2.5F, 0.05F, () -> {
		return Ingredient.of(ModItems.ANCIENT_STONE.get());
	}),
	NECROTIC_CHAIN(HMaG.MODID + ":necrotic_chainmail", 19, new int[]{2, 5, 6, 2}, 15, SoundEvents.ARMOR_EQUIP_CHAIN, 2.0F, 0.0F, () -> {
		return Ingredient.of(ModItems.NECROFIBER.get());
	});

	private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
	private final String name;
	private final int durabilityMultiplier;
	private final int[] slotProtections;
	private final int enchantmentValue;
	private final SoundEvent sound;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairIngredient;

	private ModArmorMaterial(String nameIn, int durabilityMultiplierIn, int[] slotProtectionsIn, int enchantmentIn, SoundEvent sound, float toughnessIn, float knockbackResistanceIn, Supplier<Ingredient> repairItems)
	{
		this.name = nameIn;
		this.durabilityMultiplier = durabilityMultiplierIn;
		this.slotProtections = slotProtectionsIn;
		this.enchantmentValue = enchantmentIn;
		this.sound = sound;
		this.toughness = toughnessIn;
		this.knockbackResistance = knockbackResistanceIn;
		this.repairIngredient = new LazyValue<>(repairItems);
	}

	@Override
	public int getDurabilityForSlot(EquipmentSlotType slot)
	{
		return HEALTH_PER_SLOT[slot.getIndex()] * this.durabilityMultiplier;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlotType slot)
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