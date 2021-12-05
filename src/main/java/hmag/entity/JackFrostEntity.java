package hmag.entity;

import javax.annotation.Nonnull;

import hmag.ModConfigs;
import hmag.entity.goal.RangedAttackGoal2;
import hmag.entity.projectile.HardSnowballEntity;
import hmag.registry.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.fml.network.NetworkHooks;

public class JackFrostEntity extends MonsterEntity implements IModMob, IRangedAttackMob
{
	public JackFrostEntity(EntityType<? extends JackFrostEntity> type, World worldIn)
	{
		super(type, worldIn);
		this.xpReward = 15;
	}

	@Override
	protected void registerGoals()
	{
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(4, new RangedAttackGoal2(this, 1.25D, 20, 30, 15.0F, true));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 3.0F, 1.0F));
		this.goalSelector.addGoal(7, new LookAtGoal(this, MobEntity.class, 8.0F));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute createAttributes()
	{
		return MonsterEntity.createMonsterAttributes()
				.add(Attributes.MAX_HEALTH, 60.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.24D)
				.add(Attributes.ARMOR, 2.0D);
	}

	@Override
	public void aiStep()
	{
		super.aiStep();

		if (!this.level.isClientSide)
		{
			int i = MathHelper.floor(this.getX());
			int j = MathHelper.floor(this.getY());
			int k = MathHelper.floor(this.getZ());

			if (this.level.getBiome(new BlockPos(i, 0, k)).getTemperature(new BlockPos(i, j, k)) > 1.0F)
			{
				this.hurt(DamageSource.ON_FIRE, 1.0F);
			}
			else if (ModConfigs.cachedServer.JACK_FROST_FREEZES_WATER && ForgeEventFactory.getMobGriefingEvent(this.level, this))
			{
				if (this.isOnGround())
				{
					BlockState blockstate = Blocks.FROSTED_ICE.defaultBlockState();
					float f = 2.0F;
					BlockPos blockpos = this.blockPosition();
					BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

					for (BlockPos blockpos1 : BlockPos.betweenClosed(blockpos.offset((double)(-f), -1.0D, (double)(-f)), blockpos.offset((double)f, -1.0D, (double)f)))
					{
						if (blockpos1.closerThan(this.position(), (double)f))
						{
							blockpos$mutable.set(blockpos1.getX(), blockpos1.getY() + 1, blockpos1.getZ());

							if (this.level.isEmptyBlock(blockpos$mutable))
							{
								BlockState blockstate1 = level.getBlockState(blockpos1);
								boolean isFull = blockstate1.getBlock() == Blocks.WATER && blockstate1.getValue(FlowingFluidBlock.LEVEL) == 0;

								if (blockstate1.getMaterial() == Material.WATER && isFull && blockstate.canSurvive(level, blockpos1) && level.isUnobstructed(blockstate, blockpos1, ISelectionContext.empty()) && !ForgeEventFactory.onBlockPlace(this, BlockSnapshot.create(level.dimension(), level, blockpos1), Direction.UP))
								{
									this.level.setBlockAndUpdate(blockpos1, blockstate);
									this.level.getBlockTicks().scheduleTick(blockpos1, Blocks.FROSTED_ICE, MathHelper.nextInt(this.getRandom(), 60, 120));
								}
							}
						}
					}
				}
			}
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
	public void performRangedAttack(LivingEntity target, float distanceFactor)
	{
		int c = this.getRandom().nextInt(3) + 3;

		for (int i = 0; i < c; ++i)
		{
			HardSnowballEntity snowballentity = new HardSnowballEntity(this.level, this);
			double d0 = (double)target.getEyeHeight() - (double)1.1F;
			double d1 = target.getX() - this.getX();
			double d2 = d0 - snowballentity.getY();
			double d3 = target.getZ() - this.getZ();
			float f = MathHelper.sqrt(d1 * d1 + d3 * d3) * 0.2F;
			snowballentity.shoot(d1, d2 + (double)f, d3, 1.5F, 10.0F);
			snowballentity.setDamage(3.0F);
			this.level.addFreshEntity(snowballentity);
		}

		this.playSound(SoundEvents.SNOW_GOLEM_SHOOT, 1.0F, 1.0F / (this.getRandom().nextFloat() * 0.4F + 0.8F));
	}

	@Override
	public int getMaxSpawnClusterSize()
	{
		return 2;
	}

	@Override
	public double getMyRidingOffset()
	{
		return -0.45D;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn)
	{
		return 1.74F;
	}

	@Override
	protected SoundEvent getAmbientSound()
	{
		return ModSoundEvents.GIRL_MOB_IDLE.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn)
	{
		return ModSoundEvents.GIRL_MOB_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound()
	{
		return ModSoundEvents.GIRL_MOB_DEATH.get();
	}

	@Override
	protected void playStepSound(BlockPos pos, BlockState blockIn)
	{
		this.playSound(SoundEvents.ZOMBIE_STEP, 0.15F, 1.0F);
	}

	@Nonnull
	@Override
	public IPacket<?> getAddEntityPacket()
	{
		return NetworkHooks.getEntitySpawningPacket(this);
	}
}