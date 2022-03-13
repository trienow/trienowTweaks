package de.trienow.trienowtweaks.utils;

import java.util.function.Predicate;

/**
 * @author (c) trienow 2019
 */
public interface IPredicateObjects
{
	Predicate<Object> TRUE = (o) -> true;

	Predicate<Object> LOWERCASE_STRING = (o) -> o instanceof String && ((String) o).length() > 0 && ((String) o).toLowerCase().equals(o);

	Predicate<Object> INTEGER = (o) -> {
		if (o != null)
		{
			try
			{
				Integer.parseInt(o.toString());
				return true;
			}
			catch (NumberFormatException ignored)
			{
			}
		}
		return false;
	};
}
