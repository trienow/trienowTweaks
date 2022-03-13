package de.trienow.trienowtweaks.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelData;

/**
 * @author (c) trienow 2022
 */
public class LevelUtils
{
	/**
	 * A helper method, to easily get the level's spawn point
	 *
	 * @param level A way to access the level's meta info
	 * @return The spawn point as a block pos
	 */
	public static BlockPos getSpawn(Level level)
	{
		LevelData levelData = level.getLevelData();
		return new BlockPos(levelData.getXSpawn(), levelData.getYSpawn(), levelData.getZSpawn());
	}
}
