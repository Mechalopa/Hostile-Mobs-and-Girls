package hmag.registry;

import hmag.HMaG;
import hmag.entity.ArurauneEntity;
import hmag.entity.BansheeEntity;
import hmag.entity.CatoblepasEntity;
import hmag.entity.CreeperGirlEntity;
import hmag.entity.CrimsonSlaughtererEntity;
import hmag.entity.CursedDollEntity;
import hmag.entity.DoguEntity;
import hmag.entity.DrownedGirlEntity;
import hmag.entity.DullahanEntity;
import hmag.entity.DyssomniaEntity;
import hmag.entity.EnderExecutorEntity;
import hmag.entity.FortressKeeperEntity;
import hmag.entity.GhastlySeekerEntity;
import hmag.entity.GhostEntity;
import hmag.entity.HarpyEntity;
import hmag.entity.HornetEntity;
import hmag.entity.HuskGirlEntity;
import hmag.entity.JackFrostEntity;
import hmag.entity.KashaEntity;
import hmag.entity.KoboldEntity;
import hmag.entity.LichEntity;
import hmag.entity.MagicalSlimeEntity;
import hmag.entity.MeltyMonsterEntity;
import hmag.entity.MonolithEntity;
import hmag.entity.NecroticReaperEntity;
import hmag.entity.OgreEntity;
import hmag.entity.RedcapEntity;
import hmag.entity.SavagefangEntity;
import hmag.entity.ScorpionEntity;
import hmag.entity.SkeletonGirlEntity;
import hmag.entity.SlimeGirlEntity;
import hmag.entity.SnowCanineEntity;
import hmag.entity.SpiderNestEntity;
import hmag.entity.StoneularEntity;
import hmag.entity.StrayGirlEntity;
import hmag.entity.WitherGhostEntity;
import hmag.entity.WitherSkeletonGirlEntity;
import hmag.entity.ZombieGirlEntity;
import hmag.entity.projectile.FireBottleEntity;
import hmag.entity.projectile.HardSnowballEntity;
import hmag.entity.projectile.LightningBottleEntity;
import hmag.entity.projectile.MagicBulletEntity;
import hmag.entity.projectile.MagmaBulletEntity;
import hmag.entity.projectile.PoisonSeedEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes
{
	private static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, HMaG.MODID);

	public static final RegistryObject<EntityType<ZombieGirlEntity>> ZOMBIE_GIRL = REGISTRY.register("zombie_girl", () -> EntityType.Builder
			.of(ZombieGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "zombie_girl").toString()));
	public static final RegistryObject<EntityType<HuskGirlEntity>> HUSK_GIRL = REGISTRY.register("husk_girl", () -> EntityType.Builder
			.of(HuskGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "husk_girl").toString()));
	public static final RegistryObject<EntityType<DrownedGirlEntity>> DROWNED_GIRL = REGISTRY.register("drowned_girl", () -> EntityType.Builder
			.of(DrownedGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "drowned_girl").toString()));
	public static final RegistryObject<EntityType<SkeletonGirlEntity>> SKELETON_GIRL = REGISTRY.register("skeleton_girl", () -> EntityType.Builder
			.of(SkeletonGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "skeleton_girl").toString()));
	public static final RegistryObject<EntityType<WitherSkeletonGirlEntity>> WITHER_SKELETON_GIRL = REGISTRY.register("wither_skeleton_girl", () -> EntityType.Builder
			.of(WitherSkeletonGirlEntity::new, EntityClassification.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.7F, 2.4F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "wither_skeleton_girl").toString()));
	public static final RegistryObject<EntityType<StrayGirlEntity>> STRAY_GIRL = REGISTRY.register("stray_girl", () -> EntityType.Builder
			.of(StrayGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "stray_girl").toString()));
	public static final RegistryObject<EntityType<CreeperGirlEntity>> CREEPER_GIRL = REGISTRY.register("creeper_girl", () -> EntityType.Builder
			.of(CreeperGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "creeper_girl").toString()));
	public static final RegistryObject<EntityType<GhostEntity>> GHOST = REGISTRY.register("ghost", () -> EntityType.Builder
			.of(GhostEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ghost").toString()));
	public static final RegistryObject<EntityType<WitherGhostEntity>> WITHER_GHOST = REGISTRY.register("wither_ghost", () -> EntityType.Builder
			.of(WitherGhostEntity::new, EntityClassification.MONSTER).fireImmune().immuneTo(Blocks.WITHER_ROSE).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "wither_ghost").toString()));
	public static final RegistryObject<EntityType<EnderExecutorEntity>> ENDER_EXECUTOR = REGISTRY.register("ender_executor", () -> EntityType.Builder
			.of(EnderExecutorEntity::new, EntityClassification.MONSTER).sized(0.6F, 2.9F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ender_executor").toString()));
	public static final RegistryObject<EntityType<KoboldEntity>> KOBOLD = REGISTRY.register("kobold", () -> EntityType.Builder
			.of(KoboldEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "kobold").toString()));
	public static final RegistryObject<EntityType<LichEntity>> LICH = REGISTRY.register("lich", () -> EntityType.Builder
			.of(LichEntity::new, EntityClassification.MONSTER).sized(0.6F, 2.3F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "lich").toString()));
	public static final RegistryObject<EntityType<OgreEntity>> OGRE = REGISTRY.register("ogre", () -> EntityType.Builder
			.of(OgreEntity::new, EntityClassification.MONSTER).sized(0.9F, 2.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ogre").toString()));
	public static final RegistryObject<EntityType<SpiderNestEntity>> SPIDER_NEST = REGISTRY.register("spider_nest", () -> EntityType.Builder
			.of(SpiderNestEntity::new, EntityClassification.MONSTER).sized(0.9F, 1.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "spider_nest").toString()));
	public static final RegistryObject<EntityType<MeltyMonsterEntity>> MELTY_MONSTER = REGISTRY.register("melty_monster", () -> EntityType.Builder
			.of(MeltyMonsterEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "melty_monster").toString()));
	public static final RegistryObject<EntityType<CursedDollEntity>> CURSED_DOLL = REGISTRY.register("cursed_doll", () -> EntityType.Builder
			.of(CursedDollEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "cursed_doll").toString()));
	public static final RegistryObject<EntityType<JackFrostEntity>> JACK_FROST = REGISTRY.register("jack_frost", () -> EntityType.Builder
			.of(JackFrostEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "jack_frost").toString()));
	public static final RegistryObject<EntityType<HornetEntity>> HORNET = REGISTRY.register("hornet", () -> EntityType.Builder
			.of(HornetEntity::new, EntityClassification.MONSTER).sized(0.5F, 1.55F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "hornet").toString()));
	public static final RegistryObject<EntityType<DullahanEntity>> DULLAHAN = REGISTRY.register("dullahan", () -> EntityType.Builder
			.of(DullahanEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.75F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dullahan").toString()));
	public static final RegistryObject<EntityType<BansheeEntity>> BANSHEE = REGISTRY.register("banshee", () -> EntityType.Builder
			.of(BansheeEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "banshee").toString()));
	public static final RegistryObject<EntityType<ArurauneEntity>> ARURAUNE = REGISTRY.register("aruraune", () -> EntityType.Builder
			.of(ArurauneEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "aruraune").toString()));
	public static final RegistryObject<EntityType<CatoblepasEntity>> CATOBLEPAS = REGISTRY.register("catoblepas", () -> EntityType.Builder
			.of(CatoblepasEntity::new, EntityClassification.MONSTER).sized(0.9F, 1.4F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "catoblepas").toString()));
	public static final RegistryObject<EntityType<ScorpionEntity>> SCORPION = REGISTRY.register("scorpion", () -> EntityType.Builder
			.of(ScorpionEntity::new, EntityClassification.MONSTER).sized(1.2F, 0.8F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "scorpion").toString()));
	public static final RegistryObject<EntityType<KashaEntity>> KASHA = REGISTRY.register("kasha", () -> EntityType.Builder
			.of(KashaEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.6F, 0.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "kasha").toString()));
	public static final RegistryObject<EntityType<DoguEntity>> DOGU = REGISTRY.register("dogu", () -> EntityType.Builder
			.of(DoguEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.99F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dogu").toString()));
	public static final RegistryObject<EntityType<GhastlySeekerEntity>> GHSATLY_SHEEKER = REGISTRY.register("ghastly_seeker", () -> EntityType.Builder
			.of(GhastlySeekerEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.9F, 2.9F).setTrackingRange(8).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "ghastly_seeker").toString()));
	public static final RegistryObject<EntityType<RedcapEntity>> REDCAP = REGISTRY.register("redcap", () -> EntityType.Builder
			.of(RedcapEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "redcap").toString()));
	public static final RegistryObject<EntityType<SlimeGirlEntity>> SLIME_GIRL = REGISTRY.register("slime_girl", () -> EntityType.Builder
			.of(SlimeGirlEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "slime_girl").toString()));
	public static final RegistryObject<EntityType<MagicalSlimeEntity>> MAGICAL_SLIME = REGISTRY.register("magical_slime", () -> EntityType.Builder
			.of(MagicalSlimeEntity::new, EntityClassification.MONSTER).sized(2.04F, 2.04F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "magical_slime").toString()));
	public static final RegistryObject<EntityType<MonolithEntity>> MONOLITH = REGISTRY.register("monolith", () -> EntityType.Builder
			.of(MonolithEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.75F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "monolith").toString()));
	public static final RegistryObject<EntityType<CrimsonSlaughtererEntity>> CRIMSON_SLAUGHTERER = REGISTRY.register("crimson_slaughterer", () -> EntityType.Builder
			.of(CrimsonSlaughtererEntity::new, EntityClassification.MONSTER).fireImmune().sized(0.75F, 2.45F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "crimson_slaughterer").toString()));
	public static final RegistryObject<EntityType<DyssomniaEntity>> DYSSOMNIA = REGISTRY.register("dyssomnia", () -> EntityType.Builder
			.of(DyssomniaEntity::new, EntityClassification.MONSTER).sized(2.3F, 1.1F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "dyssomnia").toString()));
	public static final RegistryObject<EntityType<SnowCanineEntity>> SNOW_CANINE = REGISTRY.register("snow_canine", () -> EntityType.Builder
			.of(SnowCanineEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "snow_canine").toString()));
	public static final RegistryObject<EntityType<StoneularEntity>> STONEULAR = REGISTRY.register("stoneular", () -> EntityType.Builder
			.of(StoneularEntity::new, EntityClassification.MONSTER).sized(0.9F, 1.7F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "stoneular").toString()));
	public static final RegistryObject<EntityType<HarpyEntity>> HARPY = REGISTRY.register("harpy", () -> EntityType.Builder
			.of(HarpyEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "harpy").toString()));
	public static final RegistryObject<EntityType<SavagefangEntity>> SAVAGEFANG = REGISTRY.register("savagefang", () -> EntityType.Builder
			.of(SavagefangEntity::new, EntityClassification.MONSTER).sized(0.72F, 0.55F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "savagefang").toString()));
	public static final RegistryObject<EntityType<FortressKeeperEntity>> FORTRESS_KEEPER = REGISTRY.register("fortress_keeper", () -> EntityType.Builder
			.of(FortressKeeperEntity::new, EntityClassification.MONSTER).fireImmune().sized(1.2F, 2.15F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "fortress_keeper").toString()));
	public static final RegistryObject<EntityType<NecroticReaperEntity>> NECROTIC_REAPER = REGISTRY.register("necrotic_reaper", () -> EntityType.Builder
			.of(NecroticReaperEntity::new, EntityClassification.MONSTER).sized(0.6F, 1.95F).setTrackingRange(6).setUpdateInterval(3).setShouldReceiveVelocityUpdates(false)
			.build(new ResourceLocation(HMaG.MODID, "necrotic_reaper").toString()));

	public static final RegistryObject<EntityType<MagicBulletEntity>> MAGIC_BULLET = REGISTRY.register("magic_bullet", () -> EntityType.Builder
			.<MagicBulletEntity>of(MagicBulletEntity::new, EntityClassification.MISC).sized(0.3215F, 0.3215F).setTrackingRange(4).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "magic_bullet").toString()));
	public static final RegistryObject<EntityType<HardSnowballEntity>> HARD_SNOWBALL = REGISTRY.register("hard_snowball", () -> EntityType.Builder
			.<HardSnowballEntity>of(HardSnowballEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "hard_snowball").toString()));
	public static final RegistryObject<EntityType<PoisonSeedEntity>> POISON_SEED = REGISTRY.register("poison_seed", () -> EntityType.Builder
			.<PoisonSeedEntity>of(PoisonSeedEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "poison_seed").toString()));
	public static final RegistryObject<EntityType<FireBottleEntity>> FIRE_BOTTLE = REGISTRY.register("fire_bottle", () -> EntityType.Builder
			.<FireBottleEntity>of(FireBottleEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "fire_bottle").toString()));
	public static final RegistryObject<EntityType<LightningBottleEntity>> LIGHTNING_BOTTLE = REGISTRY.register("lightning_bottle", () -> EntityType.Builder
			.<LightningBottleEntity>of(LightningBottleEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).setTrackingRange(4).setUpdateInterval(10).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "lightning_bottle").toString()));
	public static final RegistryObject<EntityType<MagmaBulletEntity>> MAGMA_BULLET = REGISTRY.register("magma_bullet", () -> EntityType.Builder
			.<MagmaBulletEntity>of(MagmaBulletEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).setTrackingRange(4).setUpdateInterval(1).setShouldReceiveVelocityUpdates(true)
			.build(new ResourceLocation(HMaG.MODID, "magma_bullet").toString()));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}