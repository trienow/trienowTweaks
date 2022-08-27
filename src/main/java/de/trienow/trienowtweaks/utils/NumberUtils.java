package de.trienow.trienowtweaks.utils;

import net.minecraft.util.RandomSource;

/**
 * @author (c) trienow 2019 - 2022
 */
public class NumberUtils
{
	/**
	 * Gets a random number in the range of <strong>min</strong> and <strong>max</strong>
	 *
	 * @param rnd An Instance of a random number generator
	 * @param min The minimum value to expect
	 * @param max The maximum value to expect
	 * @return A random number between <strong>min</strong> and <strong>max</strong>
	 * @throws IllegalArgumentException When min >= max
	 */
	public static int getRandomNumberInRange(RandomSource rnd, int min, int max) throws IllegalArgumentException
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("Max must be greater than Min");
		}

		return rnd.nextInt((max - min) + 1) + min;
	}
}
