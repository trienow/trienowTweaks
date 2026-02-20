package de.trienow.trienowtweaks.config;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;

import static de.trienow.trienowtweaks.main.TrienowTweaks.LOG;

/**
 * @author (c) trienow 2019 - 2026
 */
@EventBusSubscriber(modid = TrienowTweaks.MODID)
public class Config
{

	public static void init(ModContainer container)
	{
		container.registerConfig(ModConfig.Type.SERVER, ServerConfig.CONFIG_SPEC);
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent.Loading cfgEvt)
	{
		ServerConfig.CONFIG.cacheLoadedValues();
		LOG.info("Config loaded: {}", cfgEvt.getConfig().getModId());
	}
}
