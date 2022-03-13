package de.trienow.trienowtweaks.blocks.flavors;

import net.minecraft.util.StringRepresentable;

/**
 * @author (c) trienow 2019
 */
public enum FlavorStreetlamp implements StringRepresentable
{
	FIRE("fire"),
	GLOWSTONE("glowstone"),
	FLESH("flesh");

	private final String name;

	/**
	 * Constructs an object of type StateStreetlampFlavor.java
	 */
	FlavorStreetlamp(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return name;
	}

	@Override
	public String getSerializedName()
	{
		return name;
	}
}
