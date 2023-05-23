package de.trienow.trienowtweaks.utils;

import net.minecraft.ResourceLocationException;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Predicate;

/**
 * @author (c) trienow 2019 - 2023
 */
public interface IPredicateObjects
{
	@SuppressWarnings("unused") //Is used as an example.
	Predicate<Object> LOWERCASE_STRING = (o) -> o instanceof String s && s.length() > 0 && s.toLowerCase().equals(o);

	Predicate<Object> RESOURCE_LOCATION = (o) -> {
		if (o instanceof String s)
		{
			try
			{
				new ResourceLocation(s);
				return true;
			}
			catch (ResourceLocationException ignored)
			{
			}
		}
		return false;
	};
}
