package net.mechalopa.hmag.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SoupItem;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

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

			LightningBoltEntity lightningboltentity = EntityType.LIGHTNING_BOLT.create(worldIn);
			lightningboltentity.moveTo(Vector3d.atBottomCenterOf(blockpos));
			lightningboltentity.setCause(livingEntity instanceof ServerPlayerEntity ? (ServerPlayerEntity)livingEntity : null);
			worldIn.addFreshEntity(lightningboltentity);
			livingEntity.playSound(SoundEvents.TRIDENT_THUNDER, 5.0F, 1.0F);
		}

		return stack1;
	}
}