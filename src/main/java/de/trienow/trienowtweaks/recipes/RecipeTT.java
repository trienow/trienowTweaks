package de.trienow.trienowtweaks.recipes;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.HashMap;
import java.util.Map;

class RecipeTT
{
	final int width;
	final int height;
	final Ingredient[] ingredients;
	final ItemLike result;
	final int resultCount;
	private final Map<Item, Integer> damageableItems = new HashMap<>();

	RecipeTT(int width, int height, ItemLike result, int resultCount)
	{
		this.width = width;
		this.height = height;
		this.result = result;
		this.ingredients = new Ingredient[width * height];
		this.resultCount = resultCount;
	}

	RecipeTT(int width, int height, ItemLike result)
	{
		this(width, height, result, 1);
	}

	/**
	 * Add a row of ingredients to the recipe.
	 *
	 * @param row The zero-indexed row number for the recipe
	 * @param i1  The 1st ingredient in the row
	 * @param i2  The 2nd ingredient in the row, or any other value if it exceeds the width
	 * @param i3  The 3rd ingredient in the row, or any other value if it exceeds the width
	 */
	public RecipeTT addIngredients(int row, Ingredient i1, Ingredient i2, Ingredient i3)
	{
		ingredients[row * width] = i1;

		if (width > 1)
		{
			ingredients[row * width + 1] = i2;
		}

		if (width > 2)
		{
			ingredients[row * width + 2] = i3;
		}

		return this;
	}

	public RecipeTT addDamageable(ItemLike itemLike, int damageToAdd)
	{
		damageableItems.put(itemLike.asItem(), damageToAdd);
		return this;
	}

	public int shouldDamage(ItemStack stack)
	{
		return damageableItems.getOrDefault(stack.getItem(), 0);
	}
}
