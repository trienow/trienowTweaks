package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.recipes.RecipeTT;
import de.trienow.trienowtweaks.recipes.RecipeTTSerializer;
import net.minecraft.core.registries.Registries;
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

	public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<RecipeTT>> RECIPE_TT =
			RECIPES.register("recipe_tt", RecipeTTSerializer::new);

	public static void init(IEventBus modEventBus)
	{
		RECIPES.register(modEventBus);
	}
}
