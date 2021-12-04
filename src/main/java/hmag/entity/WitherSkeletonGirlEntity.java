package hmag.entity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class WitherSkeletonGirlEntity extends WitherSkeletonEntity implements IModMob
{
	public WitherSkeletonGirlEntity(EntityType<? extends WitherSkeletonEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 10;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 36.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.26D)
				.add(Attributes.ATTACK_DAMAGE, 4.5D)
				.add(Attributes.ARMOR, 4.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.25D);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		super.populateDefaultEquipmentSlots(difficulty);

		if (this.getRandom().nextFloat() < 0.05F)
		{
			this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(Items.BOW));
		}
	}

	@Nullable
	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		ILivingEntityData ilivingentitydata = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.5D);
		return ilivingentitydata;
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}