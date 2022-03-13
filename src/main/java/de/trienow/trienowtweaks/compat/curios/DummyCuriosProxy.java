package de.trienow.trienowtweaks.compat.curios;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * A dummy class to avoid mod-/dependency conflicts
 *
 * @author (c) trienow 2020
 */
public class DummyCuriosProxy implements ICuriosProxy
{
	@Override
	public boolean isModLoaded()
	{
		return false;
	}

	@Override
	public boolean trySetStackInSlot(String identifier, LivingEntity wearer, ItemStack stack)
	{
		return false;
	}
}
