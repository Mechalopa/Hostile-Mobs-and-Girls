package hmag.entity;

import javax.annotation.Nullable;

import hmag.registry.ModItems;
import hmag.registry.ModSoundEvents;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class HornetEntity extends AbstractFlyingMonsterEntity implements IModMob
{
	public HornetEntity(EntityType<? extends HornetEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(4, new AbstractFlyingMonsterEntity.ChargeAttackGoal(0.5D, 1.5F, 6));
		this.goalSelector.addGoal(8, new AbstractFlyingMonsterEntity.MoveRandomGoal());
		this.goalSelector.addGoal(9, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(10, new LookAtGoal(this, MobEntity.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this).setAlertOthers());
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.28D)
				.add(Attributes.ATTACK_DAMAGE, 5.0D)
				.add(Attributes.FOLLOW_RANGE, 24.0D);
	}

	@Override
	public CreatureAttribute getMobType()
	{
		return CreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean doHurtTarget(Entity entityIn)
	{
		if (super.doHurtTarget(entityIn))
		{
			if (entityIn instanceof LivingEntity)
			{
				int i = 0;

				if (this.level.getDifficulty() == Difficulty.NORMAL)
				{
					i = 5;
				}
				else if (this.level.getDifficulty() == Difficulty.HARD)
				{
					i = 10;
				}

				if (i > 0)
				{
					((LivingEntity)entityIn).addEffect(new EffectInstance(Effects.POISON, i * 20, 1));
				}
			}

			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount)
	{
		if (source.isFire())
		{
			amount = amount * 2.0F;
		}

		return super.hurt(source, amount);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance difficulty)
	{
		this.setItemSlot(EquipmentSlotType.MAINHAND, new ItemStack(ModItems.IRON_SPEAR.get()));
	}

	@Override
	@Nullable
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag)
	{
		spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
		this.populateDefaultEquipmentSlots(difficultyIn);
		this.populateDefaultEquipmentEnchantments(difficultyIn);

		return spawnDataIn;
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 2;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 1.52F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.HORNET_IDLE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return SoundEvents.BEE_HURT;
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return SoundEvents.BEE_DEATH;
	}
}