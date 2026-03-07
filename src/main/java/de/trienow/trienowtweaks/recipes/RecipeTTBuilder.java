package de.trienow.trienowtweaks.recipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class RecipeTTBuilder implements RecipeBuilder
{
	protected final RecipeTT r;
	protected final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
	protected RecipeCategory recipeCategory = RecipeCategory.BREWING;

	public RecipeTTBuilder(int width, int height, ItemLike result, int resultCount)
	{
		r = new RecipeTT(width, height, new Ingredient[width * height], new ItemStack(result, resultCount));
	}

	/**
	 * Add a row of ingredients to the recipe.
	 *
	 * @param row The zero-indexed row number for the recipe
	 * @param i1  The 1st ingredient in the row
	 * @param i2  The 2nd ingredient in the row, or any other value if it exceeds the width
	 * @param i3  The 3rd ingredient in the row, or any other value if it exceeds the width
	 */
	public RecipeTTBuilder addIngredients(int row, Ingredient i1, Ingredient i2, Ingredient i3)
	{
		r.ingredients[row * r.width] = i1;

		if (r.width > 1)
		{
			r.ingredients[row * r.width + 1] = i2;
		}

		if (r.width > 2)
		{
			r.ingredients[row * r.width + 2] = i3;
		}

		return this;
	}

	public RecipeTTBuilder setCategories(CraftingBookCategory category, RecipeCategory recipeCategory)
	{
		r.setCategory(category);
		this.recipeCategory = recipeCategory;
		return this;
	}

	public RecipeTTBuilder addDamageable(ItemLike itemLike, int damageToAdd)
	{
		r.addDamageable(itemLike.asItem().builtInRegistryHolder(), damageToAdd);
		return this;
	}

	@Override public RecipeBuilder unlockedBy(String name, Criterion<?> criterion)
	{
		this.criteria.put(name, criterion);
		return this;
	}

	@Override public RecipeBuilder group(@Nullable String s)
	{
		return this;
	}

	@Override public Item getResult()
	{
		return r.result.getItem();
	}

	@Override public void save(RecipeOutput recipeOutput, ResourceKey<Recipe<?>> resourceKey)
	{
		Advancement.Builder advancementBuilder = recipeOutput
				.advancement()
				.addCriterion("has_the_recipe",
						RecipeUnlockedTrigger.unlocked(resourceKey))
				.rewards(AdvancementRewards.Builder.recipe(resourceKey))
				.requirements(AdvancementRequirements.Strategy.OR);
		this.criteria.forEach(advancementBuilder::addCriterion);

		recipeOutput.accept(resourceKey, r, advancementBuilder.build(resourceKey.location().withPrefix("recipes/" + this.recipeCategory.getFolderName() + "/")));
	}
}
