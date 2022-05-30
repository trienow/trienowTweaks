package de.trienow.trienowtweaks.config;

import de.trienow.trienowtweaks.utils.IPredicateObjects;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author (c) trienow 2019 - 2022
 */
public class ServerConfig implements IConfigCache
{
	private static final String FLIGHT_DISABLED_COMMENT = "In these Dimensions (must be integers wrapped in strings), the player will be forced to the ground (not really being able to fly)";
	private static final List<String> FLIGHT_DISABLED = new ArrayList<>()
	{
		{
			add("minecraft:the_end");
			add("examplemod:dimension_name");
		}
	};

	private static final String EXACT_SPAWNPOINT_COMMENT = "Move the player to the exact spawn point of the world";
	public final ConfigValue<Boolean> exactSpawnpoint;

	public final ConfigValue<List<? extends String>> flightDisabled;
	@SuppressWarnings({ "FieldCanBeLocal", "MismatchedReadAndWriteOfArray" }) private String[] flightDisabledCached = null;

	public ServerConfig(ForgeConfigSpec.Builder builder)
	{
		builder.comment("Misc settings").push("general");
		flightDisabled = builder.comment(FLIGHT_DISABLED_COMMENT)
				.defineList("flightDisabledDimensions", FLIGHT_DISABLED, IPredicateObjects.INTEGER);

		exactSpawnpoint = builder.comment(EXACT_SPAWNPOINT_COMMENT)
				.define("forceExactSpawnPoint", false);
	}

	@Override
	public void cacheLoadedValues()
	{
		// --- FLIGHT DISABLED DIMENSIONS ---
		List<? extends String> flightDisabled = this.flightDisabled.get();
		flightDisabledCached = new String[flightDisabled.size()];
		for (int i = 0; i < flightDisabledCached.length; i++)
		{
			flightDisabledCached[i] = flightDisabled.get(i);
		}
	}
}
