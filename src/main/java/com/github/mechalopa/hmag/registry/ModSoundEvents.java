package com.github.mechalopa.hmag.registry;

import com.github.mechalopa.hmag.HMaG;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSoundEvents
{
	private static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, HMaG.MODID);

	public static final RegistryObject<SoundEvent> GIRL_MOB_AMBIENT = REGISTRY.register("entity.girls.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.girls.ambient")));
	public static final RegistryObject<SoundEvent> GIRL_MOB_HURT = REGISTRY.register("entity.girls.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.girls.hurt")));
	public static final RegistryObject<SoundEvent> GIRL_MOB_DEATH = REGISTRY.register("entity.girls.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.girls.death")));
	public static final RegistryObject<SoundEvent> GHOST_AMBIENT = REGISTRY.register("entity.ghost.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ghost.ambient")));
	public static final RegistryObject<SoundEvent> GHOST_HURT = REGISTRY.register("entity.ghost.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ghost.hurt")));
	public static final RegistryObject<SoundEvent> GHOST_DEATH = REGISTRY.register("entity.ghost.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ghost.death")));
	public static final RegistryObject<SoundEvent> OGRE_AMBIENT = REGISTRY.register("entity.ogre.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ogre.ambient")));
	public static final RegistryObject<SoundEvent> OGRE_HURT = REGISTRY.register("entity.ogre.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ogre.hurt")));
	public static final RegistryObject<SoundEvent> OGRE_DEATH = REGISTRY.register("entity.ogre.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.ogre.death")));
	public static final RegistryObject<SoundEvent> DOLL_AMBIENT = REGISTRY.register("entity.doll.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.doll.ambient")));
	public static final RegistryObject<SoundEvent> DOLL_HURT = REGISTRY.register("entity.doll.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.doll.hurt")));
	public static final RegistryObject<SoundEvent> DOLL_DEATH = REGISTRY.register("entity.doll.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.doll.death")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_AMBIENT = REGISTRY.register("entity.spider_nest.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.ambient")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_HURT = REGISTRY.register("entity.spider_nest.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.hurt")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_DEATH = REGISTRY.register("entity.spider_nest.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.death")));
	public static final RegistryObject<SoundEvent> SPIDER_NEST_SUMMON = REGISTRY.register("entity.spider_nest.summon", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.spider_nest.summon")));
	public static final RegistryObject<SoundEvent> HORNET_AMBIENT = REGISTRY.register("entity.hornet.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.hornet.ambient")));
	public static final RegistryObject<SoundEvent> CATOBLEPAS_AMBIENT = REGISTRY.register("entity.catoblepas.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.catoblepas.ambient")));
	public static final RegistryObject<SoundEvent> CATOBLEPAS_HURT = REGISTRY.register("entity.catoblepas.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.catoblepas.hurt")));
	public static final RegistryObject<SoundEvent> CATOBLEPAS_DEATH = REGISTRY.register("entity.catoblepas.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.catoblepas.death")));
	public static final RegistryObject<SoundEvent> KASHA_AMBIENT = REGISTRY.register("entity.kasha.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.kasha.ambient")));
	public static final RegistryObject<SoundEvent> KASHA_HURT = REGISTRY.register("entity.kasha.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.kasha.hurt")));
	public static final RegistryObject<SoundEvent> KASHA_DEATH = REGISTRY.register("entity.kasha.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.kasha.death")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_AMBIENT = REGISTRY.register("entity.dyssomnia.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.ambient")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_HURT = REGISTRY.register("entity.dyssomnia.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.hurt")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_DEATH = REGISTRY.register("entity.dyssomnia.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.death")));
	public static final RegistryObject<SoundEvent> DYSSOMNIA_SUMMON = REGISTRY.register("entity.dyssomnia.summon", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.dyssomnia.summon")));
	public static final RegistryObject<SoundEvent> JIANGSHI_AMBIENT = REGISTRY.register("entity.jiangshi.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.jiangshi.ambient")));
	public static final RegistryObject<SoundEvent> JIANGSHI_HURT = REGISTRY.register("entity.jiangshi.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.jiangshi.hurt")));
	public static final RegistryObject<SoundEvent> JIANGSHI_DEATH = REGISTRY.register("entity.jiangshi.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.jiangshi.death")));
	public static final RegistryObject<SoundEvent> JIANGSHI_JUMP = REGISTRY.register("entity.jiangshi.jump", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.jiangshi.jump")));
	public static final RegistryObject<SoundEvent> GIANT_MUMMY_AMBIENT = REGISTRY.register("entity.giant_mummy.ambient", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.giant_mummy.ambient")));
	public static final RegistryObject<SoundEvent> GIANT_MUMMY_HURT = REGISTRY.register("entity.giant_mummy.hurt", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.giant_mummy.hurt")));
	public static final RegistryObject<SoundEvent> GIANT_MUMMY_DEATH = REGISTRY.register("entity.giant_mummy.death", () -> new SoundEvent(new ResourceLocation(HMaG.MODID, "entity.giant_mummy.death")));

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}
}