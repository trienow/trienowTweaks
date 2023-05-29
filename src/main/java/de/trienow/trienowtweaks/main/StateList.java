package de.trienow.trienowtweaks.main;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;

import java.util.Arrays;
import java.util.List;

public class StateList
{
	private final List<IBlockState> states;

	public StateList(IBlockState[] states)
	{
		this.states = Arrays.asList(states);
	}

	public Boolean contains(IBlockState state)
	{
		return states.contains(state);
	}

	public <T extends Comparable<T>, V extends T> Boolean containsStateProperty(IBlockState state, IProperty<T> property, V value)
	{
		for (IBlockState ibs : states)
		{
			if (ibs.withProperty(property, value) == state)
				return true;
		}
		return false;
	}
}
