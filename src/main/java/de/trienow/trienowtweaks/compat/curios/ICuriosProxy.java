package de.trienow.trienowtweaks.compat.curios;

import de.trienow.trienowtweaks.compat.ICompatProxy;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

/**
 * An interface to help with loose compatibilities between this and the Curios mod, so that it does not need to be present for this mod to work.
 *
 * @author (c) trienow 2020 - 2022
 */
public interface ICuriosProxy extends ICompatProxy
{
	/**
	 * Curios' modid
	 */
	String CURIOS = "curios";

	/**
	 * The slot identifier for the necklace slot.
	 */
	String ID_NECKLACE = "necklace";

	/**
	 * The slot identifier for the helmet slot.
	 */
	String ID_HELMET = "head";

	/**
	 * Attempts to put the stack into a Curios slot on the wearer.
	 *
	 * @param identifier The identifier The Curios slot-type identifier to insert the stack into.
	 * @param wearer     The wearer The owner of the Curios slot.
	 * @param stack      The stack to put into the curios slot.
	 * @return The boolean Returns true, if the stack could be inserted.
	 */
	boolean trySetStackInSlot(String identifier, LivingEntity wearer, ItemStack stack);
}
