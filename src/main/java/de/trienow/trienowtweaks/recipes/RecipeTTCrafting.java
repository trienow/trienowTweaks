package de.trienow.trienowtweaks.recipes;

import de.trienow.trienowtweaks.atom.AtomRecipes;
import de.trienow.trienowtweaks.utils.NonNullListUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.IShapedRecipe;

/**
 * @author (c) trienow 2022
 */
public class RecipeTTCrafting implements CraftingRecipe, IShapedRecipe<CraftingContainer>
{
	private static final int width = 3;
	private static final int height = 3;
	private static final Ingredient[] ingredients = {
			Ingredient.EMPTY, Ingredient.EMPTY, Ingredient.of(Items.GLOWSTONE),
			Ingredient.EMPTY, Ingredient.EMPTY, Ingredient.of(Items.FLINT_AND_STEEL),
			Ingredient.EMPTY, Ingredient.EMPTY, Ingredient.of(Items.REDSTONE) };
	private static final ItemStack result = new ItemStack(Items.REDSTONE_LAMP);
	private final ResourceLocation id;

	public RecipeTTCrafting(final ResourceLocation id)
	{
		this.id = id;
	}

	@Override
	public ResourceLocation getId()
	{
		return id;
	}

	/**
	 * If true, this recipe does not appear in the recipe book and does not respect recipe unlocking (and the
	 * doLimitedCrafting gamerule)
	 */
	@Override
	public boolean isSpecial()
	{
		return true;
	}

	@Override
	public int getRecipeWidth()
	{
		return width;
	}

	@Override
	public int getRecipeHeight()
	{
		return height;
	}

	/**
	 * Used to check if a recipe matches current crafting inventory
	 *
	 * @param pContainer TODO
	 * @param pLevel     TODO
	 */
	@Override
	public boolean matches(CraftingContainer pContainer, Level pLevel)
	{
		for (int i = 0; i < pContainer.getContainerSize(); i++)
		{
			if (!ingredients[i].test(pContainer.getItem(i)))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * Returns an Item that is the result of this recipe
	 *
	 * @param pContainer TODO
	 */
	@Override
	public ItemStack assemble(CraftingContainer pContainer)
	{
		if (matches(pContainer, null))
		{
			return result.copy();
		}
		else
		{
			return ItemStack.EMPTY;
		}
	}

	/**
	 * Used to determine if this recipe can fit in a grid of the given width/height
	 *
	 * @param pWidth  TODO
	 * @param pHeight TODO
	 */
	@Override
	public boolean canCraftInDimensions(int pWidth, int pHeight)
	{
		return pWidth >= width && pHeight >= height;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer pContainer)
	{
		NonNullList<ItemStack> remainingItems = NonNullList.withSize(pContainer.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < pContainer.getContainerSize(); i++)
		{
			ItemStack ingredient = pContainer.getItem(i);
			if (ingredient.is(Items.FLINT_AND_STEEL) && ingredient.getDamageValue() + 10 < ingredient.getMaxDamage())
			{
				ItemStack damagedItem = ingredient.copy();
				damagedItem.setDamageValue(damagedItem.getDamageValue() + 10);
				remainingItems.set(i, damagedItem);
			}
		}

		return remainingItems;
	}

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return AtomRecipes.REMAINING.get();
	}

	@Override
	public ItemStack getResultItem()
	{
		return result;
	}

	@Override
	public NonNullList<Ingredient> getIngredients()
	{
		return NonNullListUtils.FromArray(ingredients);
	}
}
