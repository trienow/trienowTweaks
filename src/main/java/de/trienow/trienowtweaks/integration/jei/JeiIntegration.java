package de.trienow.trienowtweaks.integration.jei;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiIntegration implements IModPlugin
{
	@Override
	public void register(IModRegistry registry)
	{
		final IIngredientBlacklist ingredientBlacklist = registry.getJeiHelpers().getIngredientBlacklist();
		ingredientBlacklist.addIngredientToBlacklist(new ItemStack(AtomBlocks.blockTEGenericLight));
	}
}
