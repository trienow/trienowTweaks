package de.trienow.trienowtweaks.atom;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class AtomRecipes
{
	public static void registerSmeltingRecipes()
	{
		GameRegistry.addSmelting(new ItemStack(Blocks.MAGMA), new ItemStack(AtomBlocks.blockFakeFire), 0.3f);
	}
}
