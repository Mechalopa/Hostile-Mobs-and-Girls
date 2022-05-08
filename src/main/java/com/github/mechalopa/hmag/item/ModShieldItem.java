package com.github.mechalopa.hmag.item;

import java.util.function.Consumer;

import com.github.mechalopa.hmag.client.renderer.ModBlockEntityWithoutLevelRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraftforge.client.IItemRenderProperties;

public abstract class ModShieldItem extends ShieldItem
{
	public ModShieldItem(Item.Properties builder)
	{
		super(builder);
//		super(builder.setISTER(() -> ModItemStackTileEntityRenderer::new));
	}

	@Override
	public String getDescriptionId(ItemStack stack)
	{
		return this.getDescriptionId();
	}

//	@Override
//	public boolean isShield(ItemStack stack, @Nullable LivingEntity entity)
//	{
//		return true;
//	}

	@Override
	public int getEnchantmentValue()
	{
		return 1;
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer)
	{
		consumer.accept(new IItemRenderProperties()
		{
			private BlockEntityWithoutLevelRenderer renderer;

			@Override
			public BlockEntityWithoutLevelRenderer getItemStackRenderer()
			{
				if (Minecraft.getInstance().getBlockEntityRenderDispatcher() != null && this.renderer == null)
				{
					this.renderer = new ModBlockEntityWithoutLevelRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
				}

				return this.renderer;
			}
		});
	}
}