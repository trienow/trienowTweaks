package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomItems;
import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import de.trienow.trienowtweaks.recipes.RecipeTTBuilder;
import net.minecraft.advancements.critereon.DataComponentMatchers;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.PotionsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.crafting.DataComponentIngredient;
import net.neoforged.neoforge.registries.DeferredItem;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * @author trienow 2023 - 2026
 * @author super_zzo 2022
 */
public class GenRecipes extends RecipeProvider
{
	protected GenRecipes(HolderLookup.Provider registries, RecipeOutput output)
	{
		super(registries, output);
	}

	@Override protected void buildRecipes()
	{
		final HolderGetter<Item> items = this.registries.lookupOrThrow(Registries.ITEM);
		final HolderGetter<Potion> potions = this.registries.lookupOrThrow(Registries.POTION);

		final Item invisibleLight = AtomItemBlocks.GENERIC_LIGHT.get();
		final ItemStack potionStack = new ItemStack(Items.POTION);
		potionStack.set(DataComponents.POTION_CONTENTS, new PotionContents(Potions.INVISIBILITY));

		final HolderSet.Named<Item> GLASS_PANES_COLORLESS = items.getOrThrow(Tags.Items.GLASS_PANES_COLORLESS);
		final HolderSet.Named<Item> INGOTS = items.getOrThrow(Tags.Items.INGOTS);
		final HolderSet.Named<Item> MEAT_RAW = items.getOrThrow(AtomTags.Items.MEAT_RAW);
		final HolderSet.Named<Item> WALLS = items.getOrThrow(ItemTags.WALLS);

		final Ingredient crimsonPressurePlate = Ingredient.of(Items.CRIMSON_PRESSURE_PLATE);
		final Ingredient flintAndSteel = Ingredient.of(Items.FLINT_AND_STEEL);
		final Ingredient glassColorless = Ingredient.of(GLASS_PANES_COLORLESS);
		final Ingredient glowstone = Ingredient.of(Items.GLOWSTONE);
		final Ingredient heavyWeightedPressurePlate = Ingredient.of(Items.HEAVY_WEIGHTED_PRESSURE_PLATE);
		final Ingredient ingotsIron = Ingredient.of(INGOTS);
		final Ingredient netherrack = Ingredient.of(Items.NETHERRACK);
		final Ingredient meatRaw = Ingredient.of(MEAT_RAW);
		final Ingredient potionInvisibility = DataComponentIngredient.of(false, potionStack);
		final Ingredient walls = Ingredient.of(WALLS);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.REDSTONE, AtomItemBlocks.ENTITY_PROHIBITATOR.get(), 5)
				.unlockedBy(getHasName(Items.WITHER_SKELETON_SKULL), has(Items.WITHER_SKELETON_SKULL))
				.define('H', AtomTags.Items.SKULLS)
				.define('L', Items.DEEPSLATE_LAPIS_ORE)
				.define('W', Items.DEEPSLATE_BRICK_WALL)
				.define('B', Items.DEEPSLATE_BRICKS)
				.pattern("LBL")
				.pattern("WHW")
				.pattern("BLB")
				.save(output);

		SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.MAGMA_BLOCK), RecipeCategory.DECORATIONS,
						AtomItemBlocks.FAKE_FIRE.get(),
						0.05F,
						150)
				.unlockedBy(getHasName(Items.MAGMA_BLOCK), has(Items.MAGMA_BLOCK))
				.save(output);

		new RecipeTTBuilder(3, 3, AtomItemBlocks.GENERIC_LIGHT.get(), 16)
				.addIngredients(0, glowstone, glowstone, glowstone)
				.addIngredients(1, glowstone, potionInvisibility, glowstone)
				.addIngredients(2, glowstone, glowstone, glowstone)
				.setCategories(CraftingBookCategory.BUILDING, RecipeCategory.DECORATIONS)
				.unlockedBy("has_potion_invisibility", invisibilityPotionCriterion(items, potions))
				.save(output);

		new RecipeTTBuilder(3, 3, AtomItemBlocks.INVISIBLE_WALL.get(), 8)
				.addIngredients(0, walls, walls, walls)
				.addIngredients(1, walls, potionInvisibility, walls)
				.addIngredients(2, walls, walls, walls)
				.setCategories(CraftingBookCategory.BUILDING, RecipeCategory.DECORATIONS)
				.unlockedBy("has_potion_invisibility", invisibilityPotionCriterion(items, potions))
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.REDSTONE, AtomItemBlocks.ITEM_DETECTOR.get())
				.unlockedBy(getHasName(Items.COPPER_INGOT), has(Items.COPPER_INGOT))
				.define('C', AtomTags.Items.COPPER_CUT)
				.define('P', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
				.define('O', Items.OBSERVER)
				.define('T', Items.TNT)
				.define('H', Items.HOPPER)
				.define('R', Items.REDSTONE)
				.pattern("COP")
				.pattern("RHC")
				.pattern("CTR")
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.REDSTONE, AtomItemBlocks.MINECART_KILLER.get())
				.unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
				.define('B', ItemTags.STONE_BRICKS)
				.define('R', Items.REPEATER)
				.define('S', Items.DIAMOND_SWORD)
				.define('O', Items.OBSERVER)
				.define('P', Items.STICKY_PISTON)
				.pattern("BSO")
				.pattern("BPR")
				.pattern("BBB")
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.pattern("SSS")
				.pattern("FFF")
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('W', Tags.Items.DYES_WHITE)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" W ")
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 6)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('C', Items.CRIMSON_FENCE)
				.define('W', Items.WARPED_FENCE)
				.pattern("SSS")
				.pattern("CWC")
				.save(output, recipeLoc(AtomItemBlocks.RAILROAD_TRUSS_PURPLE, 1));

		ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('P', Tags.Items.DYES_PURPLE)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" P ")
				.save(output, recipeLoc(AtomItemBlocks.RAILROAD_TRUSS_PURPLE, 2));

		ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_BLACK.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('B', Tags.Items.DYES_BLACK)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" B ")
				.save(output);

		new RecipeTTBuilder(3, 3, AtomItemBlocks.STREETLAMP_FIRE.get(), 1)
				.addIngredients(0, ingotsIron, heavyWeightedPressurePlate, ingotsIron)
				.addIngredients(1, glassColorless, flintAndSteel, glassColorless)
				.addIngredients(2, ingotsIron, netherrack, ingotsIron)
				.addDamageable(Items.FLINT_AND_STEEL, 1)
				.setCategories(CraftingBookCategory.BUILDING, RecipeCategory.DECORATIONS)
				.unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
				.save(output);

		new RecipeTTBuilder(3, 3, AtomBlocks.STREETLAMP_FLESH.get(), 1)
				.addIngredients(0, meatRaw, crimsonPressurePlate, meatRaw)
				.addIngredients(1, glassColorless, flintAndSteel, glassColorless)
				.addIngredients(2, meatRaw, netherrack, meatRaw)
				.addDamageable(Items.FLINT_AND_STEEL, 1)
				.setCategories(CraftingBookCategory.BUILDING, RecipeCategory.DECORATIONS)
				.unlockedBy(getHasName(Items.NETHERRACK), has(Items.NETHERRACK))
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, AtomItemBlocks.STREETLAMP_GLOWSTONE.get())
				.unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
				.define('I', Tags.Items.INGOTS_IRON)
				.define('P', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
				.define('G', Tags.Items.GLASS_PANES_COLORLESS)
				.define('S', Items.GLOWSTONE)
				.pattern("IPI")
				.pattern("GSG")
				.pattern("III")
				.save(output);

		//Fun idea: Make a crafting recipe for all burnable ItemBlocks...
		ShapedRecipeBuilder.shaped(items, RecipeCategory.DECORATIONS, AtomItemBlocks.TORCH_SQUARED.get(), 8)
				.unlockedBy(getHasName(Items.COAL_BLOCK), has(AtomTags.Items.COAL_BLOCK))
				.unlockedBy("has_log_any", has(ItemTags.LOGS))
				.define('C', AtomTags.Items.COAL_BLOCK)
				.define('L', ItemTags.LOGS)
				.pattern("C")
				.pattern("L")
				.save(output, recipeVariant(AtomItemBlocks.TORCH_SQUARED, "coal"));

		ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, AtomItems.AUTO_FOOD.get())
				.unlockedBy(getHasName(Items.MELON_SLICE), has(Items.MELON_SLICE))
				.define('M', Items.MELON)
				.define('D', Items.DISPENSER)
				.pattern("MMM")
				.pattern("MDM")
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, AtomItems.AUTO_LIGHT.get())
				.unlockedBy(getHasName(Items.SHROOMLIGHT), has(Items.SHROOMLIGHT))
				.define('G', Items.GOLD_INGOT)
				.define('S', Items.SHROOMLIGHT)
				.pattern("GGG")
				.pattern("G G")
				.pattern(" S ")
				.save(output);

		ShapedRecipeBuilder.shaped(items, RecipeCategory.TOOLS, AtomItems.WE_WAND.get())
				.unlockedBy(getHasName(invisibleLight), has(invisibleLight))
				.define('L', invisibleLight)
				.define('E', Items.ENDER_PEARL)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("LEL")
				.pattern(" I ")
				.pattern(" I ")
				.save(output);
	}

	@SuppressWarnings("SameParameterValue")
	private static ResourceKey<Recipe<?>> recipeLoc(@Nonnull DeferredItem<?> ro, int index)
	{
		return ResourceKey.create(
				Registries.RECIPE,
				ResourceLocation.fromNamespaceAndPath(ro.getId().getNamespace(), ro.getId().getPath() + "_" + index));
	}

	@SuppressWarnings("SameParameterValue")
	private static ResourceKey<Recipe<?>> recipeVariant(@Nonnull DeferredItem<?> ro, String variant)
	{
		return ResourceKey.create(
				Registries.RECIPE,
				ResourceLocation.fromNamespaceAndPath(ro.getId().getNamespace(), ro.getId().getPath() + "_" + variant));
	}

	private net.minecraft.advancements.Criterion<net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance> invisibilityPotionCriterion(HolderGetter<Item> items, HolderGetter<Potion> potions)
	{
		return inventoryTrigger(
				ItemPredicate.Builder.item()
						.of(items, Items.POTION)
						.withComponents(
								DataComponentMatchers.Builder.components()
										.partial(
												DataComponentPredicates.POTIONS,
												(PotionsPredicate) PotionsPredicate.potions(HolderSet.direct(Potions.INVISIBILITY))
										)
										.build()
						)
						.build()
		);
	}

	public static class Runner extends RecipeProvider.Runner
	{
		protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries)
		{
			super(packOutput, registries);
		}

		@Override protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput)
		{
			return new GenRecipes(provider, recipeOutput);
		}

		@Override public String getName()
		{
			return TrienowTweaks.MODID + "_RECIPE_RUNNER";
		}
	}
}
