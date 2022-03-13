package de.trienow.trienowtweaks.blocks.flavors;

import net.minecraft.util.StringRepresentable;

/**
 * @author (c) trienow 2019
 */
public enum FlavorRailroadTruss implements StringRepresentable
{
	BLACK("black", 2),
	BRIGHT("bright", 1),
	PURPLE("purple", 1),
	WOODEN("wooden", 1);

	private final String name;
	private final int tooltipCount;

	/**
	 * Constructs an object of type FlavorRailroadTruss.java
	 */
	FlavorRailroadTruss(String name, int tooltipCount)
	{
		this.name = name;
		this.tooltipCount = tooltipCount;
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

	/**
	 * @return The amount of tooltip-rows
	 */
	public int getTooltipCount()
	{
		return tooltipCount;
	}
}
