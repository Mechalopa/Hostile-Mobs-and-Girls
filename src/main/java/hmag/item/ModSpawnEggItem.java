package hmag.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ModSpawnEggItem extends SpawnEggItem
{
	protected static final List<ModSpawnEggItem> UNADDED_EGGS = new ArrayList<>();
	private final Lazy<? extends EntityType<?>> entityTypeSupplier;

	public ModSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn, int secondaryColorIn, Item.Properties builder)
	{
		super(null, primaryColorIn, secondaryColorIn, builder);
		this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
		UNADDED_EGGS.add(this);
	}

	public static void initSpawnEggs()
	{
		final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(SpawnEggItem.class, null, "field_195987_b");

		DefaultDispenseItemBehavior dispenseItemBehavior = new DefaultDispenseItemBehavior()
		{
			@Override
			protected ItemStack execute(IBlockSource source, ItemStack stack)
			{
				Direction direction = source.getBlockState().getValue(DispenserBlock.FACING);
				EntityType<?> entityType = ((SpawnEggItem)stack.getItem()).getType(stack.getTag());
				entityType.spawn(source.getLevel(), stack, null, source.getPos().relative(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
				stack.shrink(1);
				return stack;
			}
		};

		for (final SpawnEggItem spawnEggItem : UNADDED_EGGS)
		{
			EGGS.put(spawnEggItem.getType(null), spawnEggItem);
			DispenserBlock.registerBehavior(spawnEggItem, dispenseItemBehavior);
		}

		UNADDED_EGGS.clear();
	}

	@Override
	public EntityType<?> getType(CompoundNBT nbt)
	{
		return this.entityTypeSupplier.get();
	}
}