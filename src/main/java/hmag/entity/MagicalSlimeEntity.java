package hmag.entity;

import javax.annotation.Nonnull;

import hmag.entity.SlimeGirlEntity.ColorVariant;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.SlimeEntity;
import net.minecraft.loot.LootTables;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

public class MagicalSlimeEntity extends SlimeEntity implements IModMob
{
	private static final DataParameter<Integer> DATA_VARIANT_ID = EntityDataManager.defineId(MagicalSlimeEntity.class, DataSerializers.INT);

	public MagicalSlimeEntity(EntityType<? extends SlimeEntity> type, World worldIn)
	{
		super(type, worldIn);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(DATA_VARIANT_ID, 0);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.3D);
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isMagic() || source == DamageSource.FALL)
		{
			amount = amount * 0.5F;
		}

		return super.hurt(source, amount);
	}

	@Override
	protected void setSize(int size, boolean resetHealth)
	{
		super.setSize(size, resetHealth);
		this.setVariant(this.getRandom().nextInt(ColorVariant.values().length));
		this.getAttribute(Attributes.ARMOR).setBaseValue((double)(size * 2));
	}

	@Override
	protected ResourceLocation getDefaultLootTable()
	{
		return this.getSize() == 1 ? this.getType().getDefaultLootTable() : LootTables.EMPTY;
	}

	@Override
	protected int getJumpDelay()
	{
		return (int)((float)super.getJumpDelay() * 0.75F);
	}

	@Override
	protected boolean isDealsDamage()
	{
		return this.isEffectiveAi();
	}

	@Override
	protected float getAttackDamage()
	{
		return super.getAttackDamage() + 2.0F;
	}

	@Override
	protected IParticleData getParticleType()
	{
		return ParticleTypes.WITCH;
	}

	@OnlyIn(Dist.CLIENT)
	public float[] getColor()
	{
		return ColorVariant.byId(this.getVariant()).getColors();
	}

	public int getVariant()
	{
		return this.entityData.get(DATA_VARIANT_ID);
	}

	public void setVariant(int typeIn)
	{
		if (typeIn < 0 || typeIn >= ColorVariant.values().length)
		{
			typeIn = this.getRandom().nextInt(ColorVariant.values().length);
		}

		this.entityData.set(DATA_VARIANT_ID, typeIn);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound)
	{
		super.addAdditionalSaveData(compound);
		compound.putInt("Variant", this.getVariant());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound)
	{
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getInt("Variant"));
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}