package de.trienow.trienowtweaks.compat;

import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

/**
 * An interface to help with loose compatibilities.
 *
 * @author (c) trienow 2020
 */
public interface ICompatProxy
{
	/**
	 * @return Returns true, when the proxied mod has been loaded.
	 */
	boolean isModLoaded();

	/**
	 * Called during the InterModEnqueueEvent.
	 *
	 * @param evt The InterModEnqueueEvent object
	 */
	default void onEnqueueIMC(final InterModEnqueueEvent evt)
	{
	}
}
