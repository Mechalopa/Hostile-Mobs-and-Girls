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

	public static final RegistryObject<SoundEvent> GIRL_MOB_AMBIENT = createSoundEvent("entity.girls.ambient");
	public static final RegistryObject<SoundEvent> GIRL_MOB_HURT = createSoundEvent("entity.girls.hurt");
	public static final RegistryObject<SoundEvent> GIRL_MOB_DEATH = createSoundEvent("entity.girls.death");
	public static final RegistryObject<SoundEvent> GHOST_AMBIENT = createSoundEvent("entity.ghost.ambient");
	public static final RegistryObject<SoundEvent> GHOST_HURT = createSoundEvent("entity.ghost.hurt");
	public static final RegistryObject<SoundEvent> GHOST_DEATH = createSoundEvent("entity.ghost.death");
	public static final RegistryObject<SoundEvent> OGRE_AMBIENT = createSoundEvent("entity.ogre.ambient");
	public static final RegistryObject<SoundEvent> OGRE_HURT = createSoundEvent("entity.ogre.hurt");
	public static final RegistryObject<SoundEvent> OGRE_DEATH = createSoundEvent("entity.ogre.death");
	public static final RegistryObject<SoundEvent> DOLL_AMBIENT = createSoundEvent("entity.doll.ambient");
	public static final RegistryObject<SoundEvent> DOLL_HURT = createSoundEvent("entity.doll.hurt");
	public static final RegistryObject<SoundEvent> DOLL_DEATH = createSoundEvent("entity.doll.death");
	public static final RegistryObject<SoundEvent> SPIDER_NEST_AMBIENT = createSoundEvent("entity.spider_nest.ambient");
	public static final RegistryObject<SoundEvent> SPIDER_NEST_HURT = createSoundEvent("entity.spider_nest.hurt");
	public static final RegistryObject<SoundEvent> SPIDER_NEST_DEATH = createSoundEvent("entity.spider_nest.death");
	public static final RegistryObject<SoundEvent> SPIDER_NEST_SUMMON = createSoundEvent("entity.spider_nest.summon");
	public static final RegistryObject<SoundEvent> HORNET_AMBIENT = createSoundEvent("entity.hornet.ambient");
	public static final RegistryObject<SoundEvent> CATOBLEPAS_AMBIENT = createSoundEvent("entity.catoblepas.ambient");
	public static final RegistryObject<SoundEvent> CATOBLEPAS_HURT = createSoundEvent("entity.catoblepas.hurt");
	public static final RegistryObject<SoundEvent> CATOBLEPAS_DEATH = createSoundEvent("entity.catoblepas.death");
	public static final RegistryObject<SoundEvent> KASHA_AMBIENT = createSoundEvent("entity.kasha.ambient");
	public static final RegistryObject<SoundEvent> KASHA_HURT = createSoundEvent("entity.kasha.hurt");
	public static final RegistryObject<SoundEvent> KASHA_DEATH = createSoundEvent("entity.kasha.death");
	public static final RegistryObject<SoundEvent> DYSSOMNIA_AMBIENT = createSoundEvent("entity.dyssomnia.ambient");
	public static final RegistryObject<SoundEvent> DYSSOMNIA_HURT = createSoundEvent("entity.dyssomnia.hurt");
	public static final RegistryObject<SoundEvent> DYSSOMNIA_DEATH = createSoundEvent("entity.dyssomnia.death");
	public static final RegistryObject<SoundEvent> DYSSOMNIA_SUMMON = createSoundEvent("entity.dyssomnia.summon");
	public static final RegistryObject<SoundEvent> JIANGSHI_AMBIENT = createSoundEvent("entity.jiangshi.ambient");
	public static final RegistryObject<SoundEvent> JIANGSHI_HURT = createSoundEvent("entity.jiangshi.hurt");
	public static final RegistryObject<SoundEvent> JIANGSHI_DEATH = createSoundEvent("entity.jiangshi.death");
	public static final RegistryObject<SoundEvent> JIANGSHI_JUMP = createSoundEvent("entity.jiangshi.jump");
	public static final RegistryObject<SoundEvent> GIANT_MUMMY_AMBIENT = createSoundEvent("entity.giant_mummy.ambient");
	public static final RegistryObject<SoundEvent> GIANT_MUMMY_HURT = createSoundEvent("entity.giant_mummy.hurt");
	public static final RegistryObject<SoundEvent> GIANT_MUMMY_DEATH = createSoundEvent("entity.giant_mummy.death");

	@SubscribeEvent
	public static void register(IEventBus eventBus)
	{
		REGISTRY.register(eventBus);
	}

	private static RegistryObject<SoundEvent> createSoundEvent(String name)
	{
		return REGISTRY.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(HMaG.MODID, name)));
	}
}