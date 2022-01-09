package net.mechalopa.hmag.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;

import javax.annotation.Nonnull;

import net.mechalopa.hmag.registry.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.HuskEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.IPacket;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class HuskGirlEntity extends HuskEntity implements IModMob
{
	public HuskGirlEntity(EntityType<? extends HuskGirlEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 8;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return ZombieEntity.createAttributes()
				.add(Attributes.MAX_HEALTH, 30.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.28D)
				.add(Attributes.ATTACK_DAMAGE, 4.0D)
				.add(Attributes.ARMOR, 5.0D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		super.populateDefaultEquipmentSlots(difficulty);

		if (this.getItemBySlot(EquipmentSlotType.MAINHAND).isEmpty())
		{
			LocalDate localdate = LocalDate.now();
			int i = localdate.get(ChronoField.DAY_OF_MONTH);
			int j = localdate.get(ChronoField.MONTH_OF_YEAR);

			if (j == 4 && i == 1 && this.getRandom().nextFloat() < 0.5F)
			{
				this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.WOODEN_HOE));
			}
		}
	}

	@Override
	protected void doUnderWaterConversion()
	{
		this.convertToZombieType(ModEntityTypes.ZOMBIE_GIRL.get());

		if (!this.isSilent())
		{
			this.level.levelEvent((PlayerEntity)null, 1041, this.blockPosition(), 0);
		}
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}