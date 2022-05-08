package com.github.mechalopa.hmag.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LightningSoupItem extends BowlFoodItem
{
	public LightningSoupItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public boolean isFoil(ItemStack stack)
	{
		return true;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity)
	{
		ItemStack stack1 = super.finishUsingItem(stack, level, livingEntity);

		if (level instanceof ServerLevel)
		{
			BlockPos blockpos = livingEntity.blockPosition();

			LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(level);
			lightningboltentity.moveTo(Vec3.atBottomCenterOf(blockpos));
			lightningboltentity.setCause(livingEntity instanceof ServerPlayer ? (ServerPlayer)livingEntity : null);
			level.addFreshEntity(lightningboltentity);
			livingEntity.playSound(SoundEvents.TRIDENT_THUNDER, 5.0F, 1.0F);
		}

		return stack1;
	}
}