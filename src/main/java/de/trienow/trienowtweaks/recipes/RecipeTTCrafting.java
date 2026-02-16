package de.trienow.trienowtweaks.recipes;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomRecipes;
import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

import java.util.Map;

/**
 * @author (c) trienow 2022 - 2026
 */
public class RecipeTTCrafting extends CustomRecipe
{
	private static final Map<ResourceLocation, RecipeTT> RECIPES = genRecipes();

	private final RecipeTT recipe = RECIPES.get(ResourceLocation.fromNamespaceAndPath(TrienowTweaks.MODID, "crafting_tt"));

	public RecipeTTCrafting(CraftingBookCategory category)
	{
		super(category);
	}

	@Override public RecipeSerializer<? extends CustomRecipe> getSerializer()
	{
		return AtomRecipes.RECIPE_TT.get();
	}

	@Override public NonNullList<ItemStack> getRemainingItems(CraftingInput input)
	{
		NonNullList<ItemStack> remainingItems = NonNullList.withSize(input.size(), ItemStack.EMPTY);

		for (int i = 0; i < input.size(); i++)
		{
			ItemStack stack = input.getItem(i);
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

	@Override public boolean matches(CraftingInput craftingInput, Level level)
	{
		int recipeLength = recipe.ingredients.length;
		for (int i = 0; i < craftingInput.size(); i++)
		{
			if (i >= recipeLength || !recipe.ingredients[i].test(craftingInput.getItem(i)))
			{
				return false;
			}
		}
		return true;
	}

	@Override public ItemStack assemble(CraftingInput craftingInput, HolderLookup.Provider provider)
	{
		if (matches(craftingInput, null))
		{
			return new ItemStack(recipe.result, recipe.resultCount);
		}
		else
		{
			return ItemStack.EMPTY;
		}
	}

	private static Map<ResourceLocation, RecipeTT> genRecipes()
	{
		ItemStack potionStack = new ItemStack(Items.POTION);
		potionStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.INVISIBILITY));

		final HolderSet.Named<Item> GLASS_PANES_COLORLESS = BuiltInRegistries.ITEM.getOrThrow(Tags.Items.GLASS_PANES_COLORLESS);
		final HolderSet.Named<Item> INGOTS = BuiltInRegistries.ITEM.getOrThrow(Tags.Items.INGOTS);
		final HolderSet.Named<Item> MEAT_RAW = BuiltInRegistries.ITEM.getOrThrow(AtomTags.Items.MEAT_RAW);
		final HolderSet.Named<Item> WALLS = BuiltInRegistries.ITEM.getOrThrow(ItemTags.WALLS);

		final Ingredient crimsonPressurePlate = Ingredient.of(Items.CRIMSON_PRESSURE_PLATE);
		final Ingredient flintAndSteel = Ingredient.of(Items.FLINT_AND_STEEL);
		final Ingredient glassColorless = Ingredient.of(GLASS_PANES_COLORLESS);
		final Ingredient glowstone = Ingredient.of(Items.GLOWSTONE);
		final Ingredient heavyWeightedPressurePlate = Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
		final Ingredient ingotsIron = Ingredient.of(INGOTS);
		final Ingredient netherrack = Ingredient.of(Items.NETHERRACK);
		final Ingredient meatRaw = Ingredient.of(MEAT_RAW);
		final Ingredient potionInvisibility = Ingredient.of(Items.POTION);
		final Ingredient walls = Ingredient.of(WALLS);

		final RecipeTT genericLight = new RecipeTT(3, 3, AtomItemBlocks.GENERIC_LIGHT.get(), 16)
				.addIngredients(0, glowstone, glowstone, glowstone)
				.addIngredients(1, glowstone, potionInvisibility, glowstone)
				.addIngredients(2, glowstone, glowstone, glowstone)
				.setCategory(CraftingBookCategory.BUILDING);

		final RecipeTT invisibleWall = new RecipeTT(3, 3, AtomItemBlocks.INVISIBLE_WALL.get(), 8)
				.addIngredients(0, walls, walls, walls)
				.addIngredients(1, walls, potionInvisibility, walls)
				.addIngredients(2, walls, walls, walls)
				.setCategory(CraftingBookCategory.BUILDING);

		final RecipeTT streetlampFire = new RecipeTT(3, 3, AtomItemBlocks.STREETLAMP_FIRE.get())
				.addIngredients(0, ingotsIron, heavyWeightedPressurePlate, ingotsIron)
				.addIngredients(1, glassColorless, flintAndSteel, glassColorless)
				.addIngredients(2, ingotsIron, netherrack, ingotsIron)
				.addDamageable(Items.FLINT_AND_STEEL, 1)
				.setCategory(CraftingBookCategory.BUILDING);

		final RecipeTT streetlampFlesh = new RecipeTT(3, 3, AtomBlocks.STREETLAMP_FLESH.get())
				.addIngredients(0, meatRaw, crimsonPressurePlate, meatRaw)
				.addIngredients(1, glassColorless, flintAndSteel, glassColorless)
				.addIngredients(2, meatRaw, netherrack, meatRaw)
				.addDamageable(Items.FLINT_AND_STEEL, 1)
				.setCategory(CraftingBookCategory.BUILDING);

		return Map.ofEntries(
				addRecipe("generic_light", genericLight),
				addRecipe("invisible_wall", invisibleWall),
				addRecipe("streetlamp_fire", streetlampFire),
				addRecipe("streetlamp_flesh", streetlampFlesh)
		);
	}

	private static Map.Entry<ResourceLocation, RecipeTT> addRecipe(String name, RecipeTT recipe)
	{
		return Map.entry(ResourceLocation.fromNamespaceAndPath(TrienowTweaks.MODID, name), recipe);
	}
}
