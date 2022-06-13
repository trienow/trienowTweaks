package de.trienow.trienowtweaks.utils;

import org.jetbrains.annotations.NotNull;

/**
 * @param <C> The type of object to clone
 * @author (c) trienow 2022
 */
public interface ICloneable<C>
{
	/**
	 * Clones this object's properties into the destination
	 *
	 * @param dest The destination object
	 */
	void clone(@NotNull C dest);
}
