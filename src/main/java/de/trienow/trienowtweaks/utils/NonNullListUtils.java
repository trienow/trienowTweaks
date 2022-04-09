package de.trienow.trienowtweaks.utils;

import net.minecraft.core.NonNullList;

/**
 * @author (c) trienow 2022
 */
public class NonNullListUtils
{
	public static <T> NonNullList<T> FromArray(T[] array)
	{
		if (array == null)
		{
			return NonNullList.create();
		}

		NonNullList<T> list = NonNullList.createWithCapacity(array.length);

		for (int i = 0; i < array.length; i++)
		{
			list.add(i, array[i]);
		}

		return list;
	}
}
