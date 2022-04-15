package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomItems;
import de.trienow.trienowtweaks.atom.AtomRecipes;
import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

/**
 * @author (c) trienow 2022
 * @author (c) super_zzo 2022
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
		final Item INVISIBLE_LIGHT = AtomItemBlocks.GENERIC_LIGHT.get();

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

		ShapedRecipeBuilder.shaped(AtomItemBlocks.ENTITY_PROHIBITATOR.get(), 5)
				.unlockedBy(getHasName(Items.WITHER_SKELETON_SKULL), has(Items.WITHER_SKELETON_SKULL))
				.define('H', Tags.Items.HEADS)
				.define('L', Items.DEEPSLATE_LAPIS_ORE)
				.define('W', Items.DEEPSLATE_BRICK_WALL)
				.define('B', Items.DEEPSLATE_BRICKS)
				.pattern("LBL")
				.pattern("WHW")
				.pattern("BLB")
				.save(pFinishedRecipeConsumer);

		SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.MAGMA_BLOCK),
						AtomItemBlocks.FAKE_FIRE.get(),
						0.05F,
						150)
				.unlockedBy(getHasName(Items.MAGMA_BLOCK), has(Items.MAGMA_BLOCK))
				.save(pFinishedRecipeConsumer);

		SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
				.save(pFinishedRecipeConsumer, recipeId("generic_light"));

		SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
				.save(pFinishedRecipeConsumer, recipeId("invisible_wall"));

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

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.pattern("SSS")
				.pattern("FFF")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('W', Tags.Items.DYES_WHITE)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" W ")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 6)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('C', Items.CRIMSON_FENCE)
				.define('W', Items.WARPED_FENCE)
				.pattern("SSS")
				.pattern("CWC")
				.save(pFinishedRecipeConsumer, recipeLoc(getSimpleRecipeName(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get()) + "_01"));

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('P', Tags.Items.DYES_PURPLE)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" P ")
				.save(pFinishedRecipeConsumer, recipeLoc(getSimpleRecipeName(AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get()) + "_02"));

		ShapedRecipeBuilder.shaped(AtomItemBlocks.RAILROAD_TRUSS_BLACK.get(), 3)
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.define('S', Items.SPRUCE_PLANKS)
				.define('F', ItemTags.WOODEN_FENCES)
				.define('B', Tags.Items.DYES_BLACK)
				.pattern("SSS")
				.pattern("FFF")
				.pattern(" B ")
				.save(pFinishedRecipeConsumer);

		/*SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_BLACK.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, new ResourceLocation(TrienowTweaks.MODID, "stonecutting_railroad_truss_any_to_railroad_truss_black"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, new ResourceLocation(TrienowTweaks.MODID, "stonecutting_railroad_truss_any_to_railroad_truss_bright"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, new ResourceLocation(TrienowTweaks.MODID, "stonecutting_railroad_truss_any_to_railroad_truss_purple"));
		SingleItemRecipeBuilder.stonecutting(Ingredient.of(AtomTags.Items.RAILROAD_TRUSS), AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get())
				.unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
				.save(pFinishedRecipeConsumer, new ResourceLocation(TrienowTweaks.MODID, "stonecutting_railroad_truss_any_to_railroad_truss_wooden"));*/

		SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
				.save(pFinishedRecipeConsumer, recipeId("streetlamp_fire"));
		SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
				.save(pFinishedRecipeConsumer, recipeId("streetlamp_flesh"));

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

		//Fun Idea: Make a crafting recipe for all burnable ItemBlocks...
		ShapedRecipeBuilder.shaped(AtomItemBlocks.TORCH_SOLAMNIA.get(), 4)
				.unlockedBy(getHasName(Items.COAL_BLOCK), has(Items.COAL_BLOCK))
				.unlockedBy("has_log_any", has(ItemTags.LOGS))
				.define('C', Items.COAL_BLOCK)
				.define('L', ItemTags.LOGS)
				.pattern("C")
				.pattern("L")
				.save(pFinishedRecipeConsumer);

		ShapelessRecipeBuilder.shapeless(AtomItems.DRTOAST_HEAD.get())
				.unlockedBy(getHasName(Items.BREAD), has(Items.BREAD))
				.requires(Items.BREAD)
				.requires(Items.KELP)
				.requires(Items.BEETROOT)
				.requires(Items.COOKED_BEEF)
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItems.AUTO_FOOD.get())
				.unlockedBy(getHasName(Items.MELON_SLICE), has(Items.MELON_SLICE))
				.define('M', Items.MELON)
				.define('D', Items.DISPENSER)
				.pattern("MMM")
				.pattern("MDM")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItems.AUTO_LIGHT.get())
				.unlockedBy(getHasName(Items.SHROOMLIGHT), has(Items.SHROOMLIGHT))
				.define('G', Items.GOLD_INGOT)
				.define('S', Items.SHROOMLIGHT)
				.pattern("GGG")
				.pattern("G G")
				.pattern(" S ")
				.save(pFinishedRecipeConsumer);

		ShapedRecipeBuilder.shaped(AtomItems.WE_WAND.get())
				.unlockedBy(getHasName(INVISIBLE_LIGHT), has(INVISIBLE_LIGHT))
				.define('L', INVISIBLE_LIGHT)
				.define('E', Items.ENDER_PEARL)
				.define('I', Tags.Items.INGOTS_IRON)
				.pattern("LEL")
				.pattern(" I ")
				.pattern(" I ")
				.save(pFinishedRecipeConsumer);
	}

	private static String recipeId(String name)
	{
		return recipeLoc(name).toString();
	}

	private static ResourceLocation recipeLoc(String name)
	{
		return new ResourceLocation(TrienowTweaks.MODID, name);
	}
}
