package de.trienow.trienowtweaks.blocks.states;

import net.minecraft.util.StringRepresentable;

/**
 * @author (c) trienow 2019
 */
public enum StateStreetlamp implements StringRepresentable
{
	TOP("top"),
	MIDDLE("middle"),
	BOTTOM("bottom");

	private final String name;

	/**
	 * Constructs an object of type StateStreetlamp.java
	 */
	StateStreetlamp(String name)
	{
		this.name = name;
	}

	@Override
	public String getSerializedName()
	{
		return name;
	}

	@Override
	public String toString()
	{
		return name;
	}
}
