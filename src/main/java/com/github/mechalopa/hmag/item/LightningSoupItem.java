package com.github.mechalopa.hmag.item;

import com.mojang.math.Vector3d;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LightningSoupItem extends SoupItem
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
	public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity livingEntity)
	{
		ItemStack stack1 = super.finishUsingItem(stack, worldIn, livingEntity);

		if (worldIn instanceof ServerWorld)
		{
			BlockPos blockpos = livingEntity.blockPosition();

			LightningBolt lightningboltentity = EntityType.LIGHTNING_BOLT.create(worldIn);
			lightningboltentity.moveTo(Vector3d.atBottomCenterOf(blockpos));
			lightningboltentity.setCause(livingEntity instanceof ServerPlayerEntity ? (ServerPlayerEntity)livingEntity : null);
			worldIn.addFreshEntity(lightningboltentity);
			livingEntity.playSound(SoundEvents.TRIDENT_THUNDER, 5.0F, 1.0F);
		}

		return stack1;
	}
}