package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.recipes.RecipeTTCrafting;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * @author (c) trienow 2022 - 2023
 */
public class AtomRecipes
{
	private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(Registries.RECIPE_SERIALIZER, TrienowTweaks.MODID);

	public static final DeferredHolder<RecipeSerializer<?>, CustomRecipe.Serializer<RecipeTTCrafting>> RECIPE_TT =
			RECIPES.register("crafting_tt", () -> new CustomRecipe.Serializer<>(craftingBookCategory -> new RecipeTTCrafting(craftingBookCategory)));

	public static void init(IEventBus modEventBus)
	{
		RECIPES.register(modEventBus);
	}
}
