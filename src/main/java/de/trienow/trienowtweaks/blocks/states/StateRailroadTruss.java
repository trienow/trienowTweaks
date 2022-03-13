package de.trienow.trienowtweaks.blocks.states;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;

/**
 * @author (c) trienow 2019
 */
public enum StateRailroadTruss implements StringRepresentable
{
	TOP_NS("top_ns", Direction.NORTH, StateRailroadTrussType.TOP),
	TOP_EW("top_ew", Direction.EAST, StateRailroadTrussType.TOP),
	MIDDLE_NS("middle_ns", Direction.NORTH, StateRailroadTrussType.MIDDLE),
	MIDDLE_EW("middle_ew", Direction.EAST, StateRailroadTrussType.MIDDLE),
	SLANTED_N("slanted_n", Direction.NORTH, StateRailroadTrussType.SLANT),
	SLANTED_E("slanted_e", Direction.EAST, StateRailroadTrussType.SLANT),
	SLANTED_S("slanted_s", Direction.SOUTH, StateRailroadTrussType.SLANT),
	SLANTED_W("slanted_w", Direction.WEST, StateRailroadTrussType.SLANT);

	private final String name;
	private final Direction face;
	private final StateRailroadTrussType type;

	StateRailroadTruss(String name, Direction face, StateRailroadTrussType type)
	{
		this.name = name;
		this.face = face;
		this.type = type;
	}

	@Override
	public String getSerializedName()
	{
		return name;
	}

	public Direction getFacing()
	{
		return face;
	}

	public StateRailroadTrussType getType()
	{
		return type;
	}
}