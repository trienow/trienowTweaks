package de.trienow.trienowtweaks.config;

/**
 * @author (c) trienow 2022
 */
public class Globals
{
	private static boolean showInvisibleBlocks;

	public static boolean showInvisibleBlocks()
	{
		return showInvisibleBlocks;
	}

	public static void setShowInvisibleBlocks(boolean showInvisibleBlocks)
	{
		Globals.showInvisibleBlocks = showInvisibleBlocks;
	}
}
