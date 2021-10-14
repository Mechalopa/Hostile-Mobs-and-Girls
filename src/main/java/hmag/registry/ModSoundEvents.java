package hmag.registry;

import hmag.HMaG;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents
{
	private static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HMaG.MODID);

	public static final RegistryObject<SoundEvent> GIRL_MOB_IDLE = REGISTRY.register("entity.girls.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.girls.idle")));
	public static final RegistryObject<SoundEvent> GIRL_MOB_HURT = REGISTRY.register("entity.girls.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.girls.hurt")));
	public static final RegistryObject<SoundEvent> GIRL_MOB_DEATH = REGISTRY.register("entity.girls.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.girls.death")));
	public static final RegistryObject<SoundEvent> GHOST_IDLE = REGISTRY.register("entity.ghost.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ghost.idle")));
	public static final RegistryObject<SoundEvent> GHOST_HURT = REGISTRY.register("entity.ghost.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ghost.hurt")));
	public static final RegistryObject<SoundEvent> GHOST_DEATH = REGISTRY.register("entity.ghost.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ghost.death")));
	public static final RegistryObject<SoundEvent> OGRE_IDLE = REGISTRY.register("entity.ogre.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ogre.idle")));
	public static final RegistryObject<SoundEvent> OGRE_HURT = REGISTRY.register("entity.ogre.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ogre.hurt")));
	public static final RegistryObject<SoundEvent> OGRE_DEATH = REGISTRY.register("entity.ogre.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ogre.death")));
	public static final RegistryObject<SoundEvent> DOLL_IDLE = REGISTRY.register("entity.doll.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.doll.idle")));
	public static final RegistryObject<SoundEvent> DOLL_HURT = REGISTRY.register("entity.doll.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.doll.hurt")));
	public static final RegistryObject<SoundEvent> DOLL_DEATH = REGISTRY.register("entity.doll.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.doll.death")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_IDLE = REGISTRY.register("entity.spider_nest.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.idle")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_HURT = REGISTRY.register("entity.spider_nest.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.hurt")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_DEATH = REGISTRY.register("entity.spider_nest.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.death")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_SUMMON = REGISTRY.register("entity.spider_nest.summon", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.summon")));
	public static final RegistryObject<SoundEvent> CATOBLEPAS_IDLE = REGISTRY.register("entity.catoblepas.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.catoblepas.idle")));
	public static final RegistryObject<SoundEvent> CATOBLEPAS_HURT = REGISTRY.register("entity.catoblepas.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.catoblepas.hurt")));
	public static final RegistryObject<SoundEvent> CATOBLEPAS_DEATH = REGISTRY.register("entity.catoblepas.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.catoblepas.death")));
	public static final RegistryObject<SoundEvent> KASHA_IDLE = REGISTRY.register("entity.kasha.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.kasha.idle")));
	public static final RegistryObject<SoundEvent> KASHA_HURT = REGISTRY.register("entity.kasha.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.kasha.hurt")));
	public static final RegistryObject<SoundEvent> KASHA_DEATH = REGISTRY.register("entity.kasha.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.kasha.death")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_IDLE = REGISTRY.register("entity.dyssomnia.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.idle")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_HURT = REGISTRY.register("entity.dyssomnia.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.hurt")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_DEATH = REGISTRY.register("entity.dyssomnia.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.death")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_SUMMON = REGISTRY.register("entity.dyssomnia.summon", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.summon")));
	public static final RegistryObject<SoundEvent> STONEULAR_IDLE = REGISTRY.register("entity.stoneular.idle", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.stoneular.idle")));
	public static final RegistryObject<SoundEvent> STONEULAR_HURT = REGISTRY.register("entity.stoneular.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.stoneular.hurt")));
	public static final RegistryObject<SoundEvent> STONEULAR_DEATH = REGISTRY.register("entity.stoneular.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.stoneular.death")));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}
