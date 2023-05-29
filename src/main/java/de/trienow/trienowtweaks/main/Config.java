package de.trienow.trienowtweaks.main;

import de.trienow.trienowtweaks.proxy.ServerProxy;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author (c) trienow 2017
 */
public class Config
{
	private static final String CATEGORY_GENERAL = "general";
	private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

	public static List<Integer> noFlyingInDimension = null;

	private static Property twentyFourHourCmds = null;
	public static String[] commandsToExecute = null;

	public static void readConfig()
	{
		try
		{
			ServerProxy.config.load();
			initGeneralConfig(ServerProxy.config);
		}
		catch (Exception ex)
		{
			TrienowTweaks.logger.error("Problem loading config file!", ex);
		}
		finally
		{
			saveConfig();
		}
	}

	/**
	 * Saves the config, if it has been changed
	 */
	public static void saveConfig()
	{
		if (ServerProxy.config.hasChanged())
			ServerProxy.config.save();
	}

	/**
	 * Determines, whether the 24h cmds are allowed to run.
	 * If it has been 24h since the "first" player logged in, this will...
	 *
	 * @return ...return true.
	 */
	public static boolean mayExecute24hCmds()
	{
		String playerJoinValue = twentyFourHourCmds.getString();
		boolean returnValue = false;

		if (playerJoinValue.length() > 0)
		{
			try
			{
				LocalDateTime playerSeenDate = LocalDateTime.parse(playerJoinValue, dtf).plusHours(24);
				returnValue = LocalDateTime.now().isAfter(playerSeenDate);

				if (returnValue)
				{
					twentyFourHourCmds.set("");
					saveConfig();
				}
			}
			catch (DateTimeParseException e)
			{
				returnValue = false;
			}
		}

		return returnValue;
	}

	/**
	 * Is called, when the player joins / leaves the server ONCE every 24h
	 */
	public static void updateExecutionTime()
	{
		// Read value from config
		String playerJoinValue = twentyFourHourCmds.getString();

		try
		{
			// If succeeds, a player has already triggered the update
			LocalDateTime.parse(playerJoinValue, dtf);
		}
		catch (DateTimeParseException e)
		{
			// If fails, the player will be the first player of the next 24h
			twentyFourHourCmds.set(dtf.format(LocalDateTime.now()));
			saveConfig();
		}
	}

	/**
	 * Initializes the config with the default values
	 *
	 * @param config Forge's {@link Configuration} object
	 */
	private static void initGeneralConfig(Configuration config)
	{
		config.addCustomCategoryComment(CATEGORY_GENERAL, "trienowTweaks Tweaking :P");

		String[] noFlyingInDimensionString = config
				.getStringList("noFlyingInDim", CATEGORY_GENERAL, new String[] { "1", "5", "30" }, "In these Dimensions, the player will be forced to the ground (not really being able to fly)");

		cfgGeneralNoFlyingInDim(noFlyingInDimensionString);

		twentyFourHourCmds = config
				.get(CATEGORY_GENERAL, "twentyFourHourCmds", "", "Defines the date, when the 'commandsToExecute' will be executed. Will update after every run.");

		commandsToExecute = config.getStringList("commandsToExecute", CATEGORY_GENERAL, new String[] {
				"/say 24h have passed after seeing the first player!" }, "Defines the commands, which will execute every 24h, BUT ONLY if a player has been present, in the last 24h");
	}

	private static void cfgGeneralNoFlyingInDim(String[] noFlyingInDimensionString)
	{
		if (noFlyingInDimensionString == null || noFlyingInDimensionString.length == 0)
		{
			noFlyingInDimension = null;
			TrienowTweaks.logger.info("[TT] No Dimensions with forbidden flying!");
		}
		else
		{
			noFlyingInDimension = new ArrayList<>();
			for (String stringDimensionId : noFlyingInDimensionString)
			{
				Integer intValue;
				try
				{
					intValue = Integer.parseInt(stringDimensionId);

					if (!noFlyingInDimension.contains(intValue))
						noFlyingInDimension.add(intValue);
				}
				catch (NumberFormatException e)
				{
					TrienowTweaks.logger.warn("[TT] Invalid dimension id in config: \"" + stringDimensionId + "\"");
				}
			}

			if (noFlyingInDimension.size() == 0)
			{
				noFlyingInDimension = null;
				TrienowTweaks.logger.info("[TT] No Dimensions with forbidden flying!");
			}
			else
				TrienowTweaks.logger.info("[TT] Dimensions with forbidden flying: " + noFlyingInDimension);
		}
	}
}
