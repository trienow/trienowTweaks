package de.trienow.trienowtweaks.recipes;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomRecipes;
import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.utils.NonNullListUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.IShapedRecipe;
import net.minecraftforge.common.crafting.PartialNBTIngredient;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * @author (c) trienow 2022 - 2023
 */
public class RecipeTTCrafting implements CraftingRecipe, IShapedRecipe<CraftingContainer>
{
	private static final Map<ResourceLocation, RecipeTT> RECIPES = genRecipes();

	private final ResourceLocation id;
	private final RecipeTT recipe;

	public RecipeTTCrafting(ResourceLocation pId, CraftingBookCategory pCategory)
	{
		this.id = pId;
		this.recipe = RECIPES.get(pId);
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
	public boolean matches(CraftingContainer pContainer, @Nullable Level pLevel)
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
	public ItemStack assemble(CraftingContainer pContainer, RegistryAccess p_267165_)
	{
		if (matches(pContainer, null))
		{
			return new ItemStack(recipe.result, recipe.resultCount);
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
	public ItemStack getResultItem(RegistryAccess p_267052_)
	{
		return new ItemStack(recipe.result, recipe.resultCount);
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
		return AtomRecipes.RECIPE_TT.get();
	}

	@Override
	public NonNullList<Ingredient> getIngredients()
	{
		return NonNullListUtils.FromArray(recipe.ingredients);
	}

	@Override
	public CraftingBookCategory category()
	{
		return null;
	}

	private static Map<ResourceLocation, RecipeTT> genRecipes()
	{
		CompoundTag potionTag = new CompoundTag();
		potionTag.putString("Potion", ForgeRegistries.POTIONS.getKey(Potions.INVISIBILITY).toString());

		final Ingredient crimsonPressurePlate = Ingredient.of(Items.CRIMSON_PRESSURE_PLATE);
		final Ingredient flintAndSteel = Ingredient.of(Items.FLINT_AND_STEEL);
		final Ingredient glassColorless = Ingredient.of(Tags.Items.GLASS_COLORLESS);
		final Ingredient glowstone = Ingredient.of(Items.GLOWSTONE);
		final Ingredient heavyWeightedPressurePlate = Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
		final Ingredient ingotsIron = Ingredient.of(Tags.Items.INGOTS_IRON);
		final Ingredient netherrack = Ingredient.of(Items.NETHERRACK);
		final Ingredient meatRaw = Ingredient.of(AtomTags.Items.MEAT_RAW);
		final Ingredient potionInvisibility = PartialNBTIngredient.of(Items.POTION, potionTag);
		final Ingredient walls = Ingredient.of(ItemTags.WALLS);

		final RecipeTT genericLight = new RecipeTT(3, 3, AtomItemBlocks.GENERIC_LIGHT.get(), 16)
				.addIngredients(0, glowstone, glowstone, glowstone)
				.addIngredients(1, glowstone, potionInvisibility, glowstone)
				.addIngredients(2, glowstone, glowstone, glowstone);

		final RecipeTT invisibleWall = new RecipeTT(3, 3, AtomItemBlocks.INVISIBLE_WALL.get(), 8)
				.addIngredients(0, walls, walls, walls)
				.addIngredients(1, walls, potionInvisibility, walls)
				.addIngredients(2, walls, walls, walls);

		final RecipeTT streetlampFire = new RecipeTT(3, 3, AtomItemBlocks.STREETLAMP_FIRE.get())
				.addIngredients(0, ingotsIron, heavyWeightedPressurePlate, ingotsIron)
				.addIngredients(1, glassColorless, flintAndSteel, glassColorless)
				.addIngredients(2, ingotsIron, netherrack, ingotsIron)
				.addDamageable(Items.FLINT_AND_STEEL, 1);

		final RecipeTT streetlampFlesh = new RecipeTT(3, 3, AtomBlocks.STREETLAMP_FLESH.get())
				.addIngredients(0, meatRaw, crimsonPressurePlate, meatRaw)
				.addIngredients(1, glassColorless, flintAndSteel, glassColorless)
				.addIngredients(2, meatRaw, netherrack, meatRaw)
				.addDamageable(Items.FLINT_AND_STEEL, 1);

		return Map.ofEntries(
				addRecipe("generic_light", genericLight),
				addRecipe("invisible_wall", invisibleWall),
				addRecipe("streetlamp_fire", streetlampFire),
				addRecipe("streetlamp_flesh", streetlampFlesh)
		);
	}

	private static Map.Entry<ResourceLocation, RecipeTT> addRecipe(String name, RecipeTT recipe)
	{
		return Map.entry(new ResourceLocation(TrienowTweaks.MODID, name), recipe);
	}
}
