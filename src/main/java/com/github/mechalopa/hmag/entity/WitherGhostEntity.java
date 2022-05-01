package com.github.mechalopa.hmag.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;

public class WitherGhostEntity extends GhostEntity
{
	public WitherGhostEntity(EntityType<? extends GhostEntity> type, Level worldIn)
	{
		super(type, worldIn);
		this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return GhostEntity.createAttributes()
				.add(Attributes.MAX_HEALTH, 20.0D);
	}

	@Override
    protected boolean shouldBurnInDay()
	{
		return false;
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		if (!super.doHurtTarget(entityIn))
		{
			return false;
		}
		else
		{
			if (entityIn instanceof LivingEntity)
			{
				((LivingEntity)entityIn).addEffect(new MobEffectInstance(MobEffects.WITHER, 10 * 20));
			}

			return true;
		}
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		if (this.getRandom().nextFloat() < (this.level.getDifficulty() == Difficulty.HARD ? 0.05F : 0.01F))
		{
			this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_SWORD));
		}
		else
		{
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);

			if (j == 4 && i == 1 && this.getRandom().nextFloat() < 0.5F)
			{
				this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.STONE_HOE));
			}
		}
	}

	@Override
	public boolean canBeAffected(MobEffectInstance potioneffectIn)
	{
		if (potioneffectIn.getEffect() == MobEffects.WITHER)
		{
			PotionEvent.PotionApplicableEvent event = new PotionEvent.PotionApplicableEvent(this, potioneffectIn);
			MinecraftForge.EVENT_BUS.post(event);
			return event.getResult() == Event.Result.ALLOW;
		}

		return super.canBeAffected(potioneffectIn);
	}
}