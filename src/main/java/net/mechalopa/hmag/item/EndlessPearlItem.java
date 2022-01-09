package net.mechalopa.hmag.item;

import net.minecraft.entity.item.EnderPearlEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SimpleFoiledItem;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class EndlessPearlItem extends SimpleFoiledItem
{
	public EndlessPearlItem(Item.Properties builder)
	{
		super(builder);
	}

	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn)
	{
		ItemStack itemstack = playerIn.getItemInHand(handIn);
		worldIn.playSound((PlayerEntity)null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
		playerIn.getCooldowns().addCooldown(this, 20);

		if (!worldIn.isClientSide)
		{
			EnderPearlEntity enderpearlentity = new EnderPearlEntity(worldIn, playerIn);
			enderpearlentity.setItem(new ItemStack(Items.ENDER_PEARL));
			enderpearlentity.shootFromRotation(playerIn, playerIn.xRot, playerIn.yRot, 0.0F, 1.5F, 1.0F);

			if (playerIn.isCrouching())
			{
				enderpearlentity.setDeltaMovement(enderpearlentity.getDeltaMovement().scale(2.0F));
			}

			worldIn.addFreshEntity(enderpearlentity);
		}

		playerIn.awardStat(Stats.ITEM_USED.get(this));

		return ActionResult.sidedSuccess(itemstack, worldIn.isClientSide());
	}
}