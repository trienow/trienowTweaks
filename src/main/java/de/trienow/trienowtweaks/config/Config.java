package de.trienow.trienowtweaks.config;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.commons.lang3.tuple.Pair;

import static de.trienow.trienowtweaks.main.TrienowTweaks.LOG;

/**
 * @author (c) trienow 2019 - 2022
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config
{
	private static ServerConfig SERVER;

	public static void init()
	{
		final Pair<ServerConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
		final ForgeConfigSpec serverSpec = specPair.getRight();
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
