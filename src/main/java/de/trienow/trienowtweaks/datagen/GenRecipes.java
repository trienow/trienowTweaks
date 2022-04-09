package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author (c) trienow 2022
 */
public class GenRecipes extends RecipeProvider
{
	public GenRecipes(DataGenerator pGenerator)
	{
		super(pGenerator);
	}

	@Override
	protected void buildCraftingRecipes(Consumer<FinishedRecipe> pFinishedRecipeConsumer)
	{
		ItemStack POTION_SPLASH_INVISIBILITY = new ItemStack(Items.SPLASH_POTION);
		POTION_SPLASH_INVISIBILITY.getOrCreateTag().putString("Potion", Potions.LONG_INVISIBILITY.getRegistryName().toString());

		ShapedRecipeBuilder.shaped(AtomItemBlocks.COMPACT_CRAFTER.get())
				.unlockedBy(getHasName(Items.STICKY_PISTON), has(Items.STICKY_PISTON))
				.define('B', Items.POLISHED_BLACKSTONE_BRICKS)
				.define('P', Items.STICKY_PISTON)
				.define('H', Items.HOPPER)
				.define('C', Items.CRAFTING_TABLE)
				.pattern("BPB")
				.pattern("HCH")
				.pattern("BPB")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.ENTITY_PROHIBITATOR.get())
				.unlockedBy(getHasName(Items.WITHER_SKELETON_SKULL), has(Items.WITHER_SKELETON_SKULL))
				.define('H', Tags.Items.HEADS)
				.define('L', Items.DEEPSLATE_LAPIS_ORE)
				.define('W', Items.RED_NETHER_BRICK_WALL)
				.define('N', Items.CHISELED_NETHER_BRICKS)
				.pattern("NLN")
				.pattern("WHW")
				.pattern("LNL")
				.save(pFinishedRecipeConsumer);

		SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.MAGMA_BLOCK),
				AtomItemBlocks.FAKE_FIRE.get(),
				0.05F,
				150);

		ShapelessRecipeBuilder.shapeless(AtomItemBlocks.GENERIC_LIGHT.get(), 8)
				.unlockedBy(getHasName(Items.FERMENTED_SPIDER_EYE), has(Items.FERMENTED_SPIDER_EYE))
				.requires(Ingredient.of(POTION_SPLASH_INVISIBILITY), 1)
				.requires(Items.GLOWSTONE, 8)
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.ITEM_DETECTOR.get())
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
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.MINECART_KILLER.get())
				.unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
				.define('B', ItemTags.STONE_BRICKS)
				.define('R', Items.REPEATER)
				.define('S', Items.DIAMOND_SWORD)
				.define('O', Items.OBSERVER)
				.define('P', Items.STICKY_PISTON)
				.pattern("BSO")
				.pattern("BPR")
				.pattern("BBB")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get(), 2)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.pattern("SSS")
				.pattern("FFF")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get(), 2)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('W', Tags.Items.DYES_WHITE)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" W ")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 2)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('C', Items.CRIMSON_FENCE)
				.define('W', Items.WARPED_FENCE)
				.pattern("SSS")
				.pattern("CWC")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 2)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('P', Tags.Items.DYES_PURPLE)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" P ")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_BLACK.get(), 2)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('B', Tags.Items.DYES_BLACK)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" B ")
				.save(pFinishedRecipeConsumer);

		SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_BLACK.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, "stonecutting_railroad_truss_any_to_railroad_truss_black");
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, "stonecutting_railroad_truss_any_to_railroad_truss_bright");
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, "stonecutting_railroad_truss_any_to_railroad_truss_purple");
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, "stonecutting_railroad_truss_any_to_railroad_truss_wooden");

		ShapedRecipeBuilder.shaped(AtomItemBlocks.STREETLAMP_FIRE.get())
				.unlockedBy(getHasName(Items.FLINT_AND_STEEL), has(Items.FLINT_AND_STEEL))
				.define('I', Tags.Items.INGOTS_IRON)
				.define('P', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
				.define('G', Tags.Items.GLASS_COLORLESS)
				.define('F', Items.FLINT_AND_STEEL)
				.define('N', Tags.Items.NETHERRACK)
				.pattern("IPI")
				.pattern("GFG")
				.pattern("INI")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.STREETLAMP_GLOWSTONE.get())
				.unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
				.define('I', Tags.Items.INGOTS_IRON)
				.define('P', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
				.define('G', Tags.Items.GLASS_COLORLESS)
				.define('S', Items.GLOWSTONE)
				.pattern("IPI")
				.pattern("GSG")
				.pattern("III")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.STREETLAMP_FLESH.get())
				.unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
				.define('I', Tags.Items.INGOTS_IRON)
				.define('P', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
				.define('G', Tags.Items.GLASS_COLORLESS)
				.define('S', Items.CAKE)
				.define('N', Tags.Items.NETHERRACK)
				.pattern("IPI")
				.pattern("GSG")
				.pattern("INI")
				.save(pFinishedRecipeConsumer);

	}
}
