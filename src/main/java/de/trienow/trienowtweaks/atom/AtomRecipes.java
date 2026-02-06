package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.recipes.RecipeTTCrafting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.ForgeRegistries;
import net.neoforged.neoforge.registries.RegistryObject;

/**
 * @author (c) trienow 2022 - 2023
 */
public class AtomRecipes
{
	private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TrienowTweaks.MODID);

	public static final RegistryObject<SimpleCraftingRecipeSerializer<RecipeTTCrafting>> RECIPE_TT =
			RECIPES.register("crafting_crafting_tt", () -> new SimpleCraftingRecipeSerializer<>((pCategory) -> new RecipeTTCrafting(new ResourceLocation(TrienowTweaks.MODID, "crafting_crafting_tt"), pCategory)));

	public static void init(IEventBus modEventBus)
	{
		RECIPES.register(modEventBus);
	}
}
