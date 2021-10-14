package hmag.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class ModBlock extends Block
{
	private final float enchantPowerBonus;

	public ModBlock(AbstractBlock.Properties properties, float enchantPowerBonusIn)
	{
		super(properties);
		this.enchantPowerBonus = enchantPowerBonusIn;
	}

	@Override
	public float getEnchantPowerBonus(BlockState state, IWorldReader world, BlockPos pos)
	{
		return this.enchantPowerBonus;
	}
}