package de.trienow.trienowtweaks.blocks.states;

import net.minecraft.core.Vec3i;
import net.minecraft.util.StringRepresentable;

/**
 * @author (c) trienow 2019
 */
public enum StateGenericLight implements StringRepresentable
{
	NONE("none", new Vec3i(0, 0, 0)),
	NORTH("n", new Vec3i(0, 2, -8)),
	NORTH_EAST("ne", new Vec3i(4, 2, -4)),
	EAST("e", new Vec3i(8, 2, 0)),
	SOUTH_EAST("se", new Vec3i(4, 2, 4)),
	SOUTH("s", new Vec3i(0, 2, 8)),
	SOUTH_WEST("sw", new Vec3i(-4, 2, 4)),
	WEST("w", new Vec3i(-8, 2, 0)),
	NORTH_WEST("nw", new Vec3i(-4, 2, -4));

	private final String name;
	private final Vec3i anchorOffset;

	/**
	 * Constructs an object of type StateGenericLight.java
	 */
	StateGenericLight(String name, Vec3i anchorOffset)
	{
		this.name = name;
		this.anchorOffset = anchorOffset;
	}

	@Override
	public String getSerializedName()
	{
		return name;
	}

	/**
	 * @return The position-offset from this block, that allows this block to be alive
	 */
	public Vec3i getAnchorOffset()
	{
		return anchorOffset;
	}
}
