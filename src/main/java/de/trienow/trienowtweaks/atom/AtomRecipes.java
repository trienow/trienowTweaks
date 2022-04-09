package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.recipes.RecipeTTCrafting;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * @author (c) trienow 2022
 */
public class AtomRecipes
{
	private static final DeferredRegister<RecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TrienowTweaks.MODID);

	public static RegistryObject<SimpleRecipeSerializer<RecipeTTCrafting>> REMAINING =
			RECIPES.register("crafting_crafting_tt", () -> new SimpleRecipeSerializer<>(RecipeTTCrafting::new));

	public static void init(IEventBus modEventBus)
	{
		RECIPES.register(modEventBus);
	}
}
