package de.trienow.trienowtweaks.config;

import de.trienow.trienowtweaks.utils.IPredicateObjects;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author (c) trienow 2019 - 2023
 */
public class ServerConfig
{
	private static final String FLIGHT_DISABLED_COMMENT = "In these Dimensions, the player will be forced to the ground (not really being able to fly)";
	private static final List<String> FLIGHT_DISABLED = new ArrayList<>()
	{
		{
			add("minecraft:the_end");
			add("examplemod:dimension_name");
		}
	};

	private static final String EXACT_SPAWNPOINT_COMMENT = "Move the player to the exact spawn point of the world";
	public final ModConfigSpec.ConfigValue<Boolean> exactSpawnpoint;

	public final ModConfigSpec.ConfigValue<List<? extends String>> flightDisabled;
	@SuppressWarnings({ "FieldCanBeLocal", "MismatchedReadAndWriteOfArray" }) private String[] flightDisabledCached = null;

	public ServerConfig(ModConfigSpec.Builder builder)
	{
		builder.comment("Misc settings").push("general");
		flightDisabled = builder.comment(FLIGHT_DISABLED_COMMENT)
				.defineList("flightDisabledDimensions", FLIGHT_DISABLED, IPredicateObjects.RESOURCE_LOCATION);

		exactSpawnpoint = builder.comment(EXACT_SPAWNPOINT_COMMENT)
				.define("forceExactSpawnPoint", false);
	}

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
