package de.trienow.trienowtweaks.main;

import java.util.Random;

/**
 * @author (c) trienow 2018
 */
public abstract class Generic
{

	/**
	 * Gets a random number in the range of <strong>min</strong> and <strong>max</strong>
	 *
	 * @param rnd An Instance of the {@link Random} object
	 * @param min The minimum value to expect
	 * @param max The maximum value to expect
	 * @return A random number between <strong>min</strong> and <strong>max</strong>
	 * @throws IllegalArgumentException When min >= max
	 */
	public static int getRandomNumberInRange(Random rnd, int min, int max) throws IllegalArgumentException
	{
		if (min >= max)
		{
			throw new IllegalArgumentException("Max must be greater than Min");
		}

		return rnd.nextInt((max - min) + 1) + min;
	}
}
