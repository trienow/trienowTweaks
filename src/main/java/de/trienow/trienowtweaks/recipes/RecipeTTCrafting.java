package de.trienow.trienowtweaks.recipes;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomRecipes;
import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
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
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.IShapedRecipe;

import java.util.Map;

/**
 * @author (c) trienow 2022
 */
public class RecipeTTCrafting implements CraftingRecipe, IShapedRecipe<CraftingContainer>
{
	private static final Map<ResourceLocation, RecipeTT> RECIPES = Map.ofEntries(
			addRecipe("streetlamp_fire", new RecipeTT(3, 3, AtomItemBlocks.STREETLAMP_FIRE.get())
					.addIngredients(0, Ingredient.of(Tags.Items.INGOTS_IRON), Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE), Ingredient.of(Tags.Items.INGOTS_IRON))
					.addIngredients(1, Ingredient.of(Tags.Items.GLASS_COLORLESS), Ingredient.of(Items.FLINT_AND_STEEL), Ingredient.of(Tags.Items.GLASS_COLORLESS))
					.addIngredients(2, Ingredient.of(Tags.Items.INGOTS_IRON), Ingredient.of(Items.NETHERRACK), Ingredient.of(Tags.Items.INGOTS_IRON))
					.addDamageable(Items.FLINT_AND_STEEL, 1)),
			addRecipe("streetlamp_flesh", new RecipeTT(3, 3, AtomBlocks.STREETLAMP_FLESH.get())
					.addIngredients(0, Ingredient.of(AtomTags.Items.MEAT_RAW), Ingredient.of(Items.CRIMSON_PRESSURE_PLATE), Ingredient.of(AtomTags.Items.MEAT_RAW))
					.addIngredients(1, Ingredient.of(Tags.Items.GLASS_COLORLESS), Ingredient.of(Items.FLINT_AND_STEEL), Ingredient.of(Tags.Items.GLASS_COLORLESS))
					.addIngredients(2, Ingredient.of(AtomTags.Items.MEAT_RAW), Ingredient.of(Items.NETHERRACK), Ingredient.of(AtomTags.Items.MEAT_RAW))
					.addDamageable(Items.FLINT_AND_STEEL, 1))
	);

	private static Map.Entry<ResourceLocation, RecipeTT> addRecipe(String name, RecipeTT recipe)
	{
		return Map.entry(new ResourceLocation(TrienowTweaks.MODID, name), recipe);
	}

	private final ResourceLocation id;
	private final RecipeTT recipe;

	public RecipeTTCrafting(final ResourceLocation id)
	{
		this.id = id;
		this.recipe = RECIPES.get(id);
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
		return recipe.width;
	}

	@Override
	public int getRecipeHeight()
	{
		return recipe.height;
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
			if (!recipe.ingredients[i].test(pContainer.getItem(i)))
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
			return new ItemStack(recipe.result);
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
		return pWidth >= recipe.width && pHeight >= recipe.height;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer pContainer)
	{
		NonNullList<ItemStack> remainingItems = NonNullList.withSize(pContainer.getContainerSize(), ItemStack.EMPTY);

		for (int i = 0; i < pContainer.getContainerSize(); i++)
		{
			ItemStack stack = pContainer.getItem(i);
			if (stack.isDamageableItem())
			{
				int toDamage = recipe.shouldDamage(stack);
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

	@Override
	public RecipeSerializer<?> getSerializer()
	{
		return AtomRecipes.REMAINING.get();
	}

	@Override
	public ItemStack getResultItem()
	{
		return new ItemStack(recipe.result);
	}

	@Override
	public NonNullList<Ingredient> getIngredients()
	{
		return NonNullListUtils.FromArray(recipe.ingredients);
	}
}
