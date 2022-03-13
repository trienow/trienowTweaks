package de.trienow.trienowtweaks.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.function.Consumer;

public class GenRecipes extends RecipeProvider
{
	public GenRecipes(DataGenerator pGenerator)
	{
		super(pGenerator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer)
	{
		//TODO Recipes
	}
}
