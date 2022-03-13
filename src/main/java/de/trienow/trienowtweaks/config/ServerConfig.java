package de.trienow.trienowtweaks.config;

import de.trienow.trienowtweaks.utils.IPredicateObjects;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author (c) trienow 2019 - 2022
 */
public class ServerConfig implements IConfigCache
{
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ss");

	private static final String TWENTY_FOUR_HOUR_TIME_COMMENT =
			"Defines the date, when the 'twentyFourHourCmds' will be executed. Will update after every run. This value does not need to be configured; it is done automatically.";
	private static final String TWENTY_FOUR_HOUR_TIME = "";
	private final ConfigValue<String> twentyFourHourTime;
	private LocalDateTime twentyFourHourTimeCached = null;

	private static final String TWENTY_FOUR_HOUR_CMDS_COMMENT = "Defines the commands, which will execute every 24h, BUT ONLY if a player has been present, in the last 24h";

	private static final boolean PERSONENSPEZIFISCHER_BUG = false;

	private static final String FLIGHT_DISABLED_COMMENT = "In these Dimensions (must be integers wrapped in strings), the player will be forced to the ground (not really being able to fly)";
	private static final List<String> FLIGHT_DISABLED = new ArrayList<>()
	{
		{
			add("minecraft:the_end");
			add("examplemod:dimensionname");
		}
	};
	public final ConfigValue<List<? extends String>> flightDisabled;
	@SuppressWarnings({ "FieldCanBeLocal", "MismatchedReadAndWriteOfArray" }) private String[] flightDisabledCached = null;

	public ServerConfig(ForgeConfigSpec.Builder builder)
	{
		builder.comment("24h command executor settings.").push("twentyFourHour");
		twentyFourHourTime =
				builder.comment(TWENTY_FOUR_HOUR_TIME_COMMENT)
						.define("twentyFourHourTime", TWENTY_FOUR_HOUR_TIME);

		builder.pop().comment("general settings.").push("general");
		flightDisabled = builder.comment(FLIGHT_DISABLED_COMMENT)
				.defineList("flightDisabledDimensions", FLIGHT_DISABLED, IPredicateObjects.INTEGER);
		builder.comment("Hier ist nichts zu sehen...").define("personenspezifischerBug", PERSONENSPEZIFISCHER_BUG);
	}

	@Override
	public void cacheLoadedValues()
	{
		// --- 24H ---
		String lastValue = twentyFourHourTime.get();
		try
		{
			// If succeeds, a player has already triggered the scheduling
			twentyFourHourTimeCached = LocalDateTime.parse(lastValue, DTF);
		}
		catch (DateTimeParseException ignored)
		{
		}

		// --- FLIGHT DISABLED DIMENSIONS ---
		List<? extends String> flightDisabled = this.flightDisabled.get();
		flightDisabledCached = new String[flightDisabled.size()];
		for (int i = 0; i < flightDisabledCached.length; i++)
		{
			flightDisabledCached[i] = flightDisabled.get(i);
		}
	}

	/**
	 * Is called, when the player joins / leaves the server ONCE every 24h
	 */
	public void update24hExecutionTime()
	{
		if (twentyFourHourTimeCached == null)
		{
			// The player will be the first player of the next 24h
			twentyFourHourTimeCached = LocalDateTime.now().plusHours(24);
			twentyFourHourTime.set(DTF.format(twentyFourHourTimeCached));
		}
	}

	/**
	 * Determines, whether or not the 24h cmds are allowed to run.
	 *
	 * @return <code>true</code>, if it has been 24h since the "first" player logged in.
	 */
	public boolean mayRun24hCmds()
	{
		boolean mayRun = twentyFourHourTimeCached != null && LocalDateTime.now().isAfter(twentyFourHourTimeCached);

		if (mayRun)
		{
			twentyFourHourTimeCached = null;
			twentyFourHourTime.set(TWENTY_FOUR_HOUR_TIME);
		}

		return mayRun;
	}
}
