package de.trienow.trienowtweaks.recipes;

import de.trienow.trienowtweaks.atom.AtomRecipes;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.item.crafting.display.ShapedCraftingRecipeDisplay;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.Level;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author (c) trienow 2022 - 2023
 */
public class RecipeTT implements CraftingRecipe
{
	final int width;
	final int height;
	final Ingredient[] ingredients;
	final ItemStack result;
	final Map<Holder<Item>, Integer> damageableItems = new HashMap<>();
	CraftingBookCategory category = CraftingBookCategory.MISC;

	RecipeTT(int width, int height, Ingredient[] ingredients, ItemStack result)
	{
		this.width = width;
		this.height = height;
		this.ingredients = ingredients;
		this.result = result;
	}

	public CraftingBookCategory getCategory()
	{
		return category;
	}

	public Map<Holder<Item>, Integer> getDamageableItems()
	{
		return damageableItems;
	}

	public int getHeight()
	{
		return height;
	}

	public List<Ingredient> getIngredients()
	{
		return List.of(ingredients);
	}

	public int getWidth()
	{
		return width;
	}

	public ItemStack getResult()
	{
		return result;
	}

	@Override public boolean matches(CraftingInput craftingInput, Level level)
	{
		boolean match = false;
		if (craftingInput.size() >= ingredients.length)
		{
			match = true;
			for (int i = 0; i < ingredients.length && match; i++)
			{
				match = ingredients[i].test(craftingInput.getItem(i));
			}
		}
		return match;
	}

	@Override public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider)
	{
		if (this.matches(craftingInput, null))
		{
			return new ItemStack(result.getItem(), result.getCount());
		}
		else
		{
			return ItemStack.EMPTY;
		}
	}

	@Override public RecipeSerializer<? extends CraftingRecipe> getSerializer()
	{
		return AtomRecipes.RECIPE_TT.get();
	}

	@Override public PlacementInfo placementInfo()
	{
		return PlacementInfo.create(List.of(ingredients));
	}

	@Override public CraftingBookCategory category()
	{
		return this.category;
	}

	@Override public List<RecipeDisplay> display()
	{
		return List.of(
				new ShapedCraftingRecipeDisplay(
						this.width,
						this.height,
						Arrays.stream(this.ingredients)
								.map(ingredient ->
										ingredient == null ? SlotDisplay.Empty.INSTANCE : ingredient.display())
								.toList(),
						new SlotDisplay.ItemStackSlotDisplay(this.result),
						new SlotDisplay.ItemSlotDisplay(Items.CRAFTING_TABLE))
		);
	}

	@Override public NonNullList<ItemStack> getRemainingItems(CraftingInput input)
	{
		NonNullList<ItemStack> remainingItems = NonNullList.withSize(input.size(), ItemStack.EMPTY);

		for (int i = 0; i < input.size(); i++)
		{
			ItemStack stack = input.getItem(i);
			if (stack.isDamageableItem())
			{
				int toDamage = damageableItems.getOrDefault(stack.getItemHolder(), 0);
				if (toDamage > 0)
				{
					int newDamage = stack.getDamageValue() + toDamage;
					if (newDamage < stack.getMaxDamage())
					{
						ItemStack damagedItem = stack.copy();
						damagedItem.setDamageValue(newDamage);
						remainingItems.set(i, damagedItem);
					}
				}
			}
		}

		return remainingItems;
	}

	@Override public boolean isSpecial()
	{
		return false;
	}

	public void setCategory(CraftingBookCategory category)
	{
		this.category = category;
	}

	public void addDamageable(Holder<Item> itemHolder, int damageToAdd)
	{
		damageableItems.putIfAbsent(itemHolder, damageToAdd);
	}
}
