package de.trienow.trienowtweaks.compat.jei;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.recipes.RecipeTTCrafting;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.category.extensions.IExtendableRecipeCategory;
import mezz.jei.api.recipe.category.extensions.vanilla.crafting.ICraftingCategoryExtension;
import mezz.jei.api.registration.IVanillaCategoryExtensionRegistration;
import mezz.jei.common.plugins.vanilla.crafting.CraftingCategoryExtension;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.CraftingRecipe;

/**
 * @author (c) trienow 2022
 */
@JeiPlugin
public class JeiCompat implements IModPlugin
{
	/**
	 * The unique ID for this mod plugin.
	 * The namespace should be your mod's modId.
	 */
	@Override
	public ResourceLocation getPluginUid()
	{
		return new ResourceLocation(TrienowTweaks.MODID, "jei_compat");
	}

	/**
	 * Register modded extensions to the vanilla crafting recipe category.
	 * Custom crafting recipes for your mod should use this to tell JEI how they work.
	 */
	@Override
	public void registerVanillaCategoryExtensions(IVanillaCategoryExtensionRegistration registration)
	{
		final IExtendableRecipeCategory<CraftingRecipe, ICraftingCategoryExtension> craftingCategory = registration.getCraftingCategory();
		craftingCategory.addCategoryExtension(RecipeTTCrafting.class, CraftingCategoryExtension::new);
	}
}
