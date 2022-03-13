package de.trienow.trienowtweaks.config;

/**
 * @author (c) trienow 2019
 */
interface IConfigCache
{
	/**
	 * Allows the config classes to preprocess some loaded config values, e.g. convert strings into date objects.
	 */
	void cacheLoadedValues();
}
