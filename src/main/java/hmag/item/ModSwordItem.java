package hmag.item;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableMultimap.Builder;
import com.google.common.collect.Multimap;

import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.math.MathHelper;

public class ModSwordItem extends SwordItem
{
	private final float attackDamage;
	private final float attackSpeed;
	private final int maxDamage;
	private final int enchantability;
	private final Multimap<Attribute, AttributeModifier> attributeModifiers;

	public ModSwordItem(IItemTier tier, Item.Properties builderIn)
	{
		this(tier, 3.0F, -2.4F, builderIn);
	}

	public ModSwordItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builderIn)
	{
		this(tier, attackDamageIn, attackSpeedIn, tier.getUses(), tier.getEnchantmentValue(), builderIn);
	}

	public ModSwordItem(IItemTier tier, float attackDamageIn, float attackSpeedIn, int maxDamageIn, int enchantabilityIn, Item.Properties builderIn)
	{
		super(tier, MathHelper.floor(attackDamageIn), attackSpeedIn, builderIn);
		this.attackDamage = attackDamageIn + tier.getAttackDamageBonus();
		this.attackSpeed = attackSpeedIn;
		Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
		builder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", (double)this.attackDamage, AttributeModifier.Operation.ADDITION));
		builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (double)this.attackSpeed, AttributeModifier.Operation.ADDITION));
		this.attributeModifiers = builder.build();
		this.maxDamage = maxDamageIn;
		this.enchantability = enchantabilityIn;
	}

	@Override
	public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlotType equipmentSlot)
	{
		return equipmentSlot == EquipmentSlotType.MAINHAND ? this.attributeModifiers : super.getDefaultAttributeModifiers(equipmentSlot);
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
	public int getBurnTime(ItemStack stack, @Nullable IRecipeType<?> recipeType)
	{
		return this.getTier() == ItemTier.WOOD ? 200 : super.getBurnTime(stack, recipeType);
	}
}