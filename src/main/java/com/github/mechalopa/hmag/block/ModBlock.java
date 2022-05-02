package com.github.mechalopa.hmag.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class ModBlock extends Block
{
	private final float enchantPowerBonus;
	private final boolean isConduitFrame;

	public ModBlock(BlockBehaviour.Properties properties, ModBlock.Properties properties1)
	{
		super(properties);
		this.enchantPowerBonus = properties1.enchantPowerBonus;
		this.isConduitFrame = properties1.isConduitFrame;
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, LevelReader level, BlockPos pos)
	{
		return this.enchantPowerBonus;
	}

	@Override
	public boolean isConduitFrame(BlockState state, LevelReader level, BlockPos pos, BlockPos conduit)
	{
		return this.isConduitFrame;
	}

	public static class Properties
	{
		private float enchantPowerBonus = 0.0F;
		private boolean isConduitFrame = false;

		public ModBlock.Properties enchantPowerBonus(float enchantPowerBonusIn)
		{
			this.enchantPowerBonus = enchantPowerBonusIn;
			return this;
		}

		public ModBlock.Properties setConduitFrame()
		{
			this.isConduitFrame = true;
			return this;
		}
	}
}