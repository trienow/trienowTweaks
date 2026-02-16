package de.trienow.trienowtweaks.config;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import static de.trienow.trienowtweaks.main.TrienowTweaks.LOG;

/**
 * @author (c) trienow 2019 - 2023
 */
@EventBusSubscriber(modid = TrienowTweaks.MODID)
public class Config
{
	private static ServerConfig SERVER;

	public static void init()
	{
		final Pair<ServerConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(ServerConfig::new);
		final ModConfigSpec serverSpec = specPair.getRight();
		SERVER = specPair.getLeft();
		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, serverSpec);
	}

	@SubscribeEvent
	public static void onLoad(final ModConfigEvent cfgEvt)
	{
		getServerConfig().cacheLoadedValues();
		LOG.info("Config loaded: {}", cfgEvt.getConfig().getModId());
	}

	public static ServerConfig getServerConfig()
	{
		return SERVER;
	}
}
