package hmag.item;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;

public class InvincibleBlockItem extends BlockItem
{
	public InvincibleBlockItem(Block blockIn, Item.Properties builder)
	{
		super(blockIn, builder);
	}

	@Override
	public boolean canBeHurtBy(DamageSource damageSource)
	{
		return damageSource != DamageSource.OUT_OF_WORLD ? false : super.canBeHurtBy(damageSource);
	}
}