package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomItems;
import de.trienow.trienowtweaks.atom.AtomRecipes;
import de.trienow.trienowtweaks.atom.AtomTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * @author trienow 2023
 * @author super_zzo 2022
 */
public class GenRecipes extends RecipeProvider {
    public GenRecipes(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        final Item invisibleLight = AtomItemBlocks.GENERIC_LIGHT.get();

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, AtomItemBlocks.COMPACT_CRAFTER.get())
                .unlockedBy(getHasName(Items.STICKY_PISTON), has(Items.STICKY_PISTON))
                .define('B', Items.POLISHED_BLACKSTONE_BRICKS)
                .define('P', Items.STICKY_PISTON)
                .define('H', Items.HOPPER)
                .define('C', Items.CRAFTING_TABLE)
                .pattern("BPB")
                .pattern("HCH")
                .pattern("BPB")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, AtomItemBlocks.ENTITY_PROHIBITATOR.get(), 5)
                .unlockedBy(getHasName(Items.WITHER_SKELETON_SKULL), has(Items.WITHER_SKELETON_SKULL))
                .define('H', Tags.Items.HEADS)
                .define('L', Items.DEEPSLATE_LAPIS_ORE)
                .define('W', Items.DEEPSLATE_BRICK_WALL)
                .define('B', Items.DEEPSLATE_BRICKS)
                .pattern("LBL")
                .pattern("WHW")
                .pattern("BLB")
                .save(pWriter);

        SimpleCookingRecipeBuilder.blasting(Ingredient.of(Items.MAGMA_BLOCK), RecipeCategory.DECORATIONS,
                        AtomItemBlocks.FAKE_FIRE.get(),
                        0.05F,
                        150)
                .unlockedBy(getHasName(Items.MAGMA_BLOCK), has(Items.MAGMA_BLOCK))
                .save(pWriter);

        SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
                .save(pWriter, recipeId(AtomItemBlocks.GENERIC_LIGHT));

        SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
                .save(pWriter, recipeId(AtomItemBlocks.INVISIBLE_WALL));

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, AtomItemBlocks.ITEM_DETECTOR.get())
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
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, AtomItemBlocks.MINECART_KILLER.get())
                .unlockedBy(getHasName(Items.MINECART), has(Items.MINECART))
                .define('B', ItemTags.STONE_BRICKS)
                .define('R', Items.REPEATER)
                .define('S', Items.DIAMOND_SWORD)
                .define('O', Items.OBSERVER)
                .define('P', Items.STICKY_PISTON)
                .pattern("BSO")
                .pattern("BPR")
                .pattern("BBB")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_WOODEN.get(), 3)
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .define('S', Items.SPRUCE_PLANKS)
                .define('F', ItemTags.WOODEN_FENCES)
                .pattern("SSS")
                .pattern("FFF")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_BRIGHT.get(), 3)
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .define('S', Items.SPRUCE_PLANKS)
                .define('F', ItemTags.WOODEN_FENCES)
                .define('W', Tags.Items.DYES_WHITE)
                .pattern("SSS")
                .pattern("FFF")
                .pattern(" W ")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 6)
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .define('S', Items.SPRUCE_PLANKS)
                .define('C', Items.CRIMSON_FENCE)
                .define('W', Items.WARPED_FENCE)
                .pattern("SSS")
                .pattern("CWC")
                .save(pWriter, recipeLoc(AtomItemBlocks.RAILROAD_TRUSS_PURPLE, 1));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_PURPLE.get(), 3)
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .define('S', Items.SPRUCE_PLANKS)
                .define('F', ItemTags.WOODEN_FENCES)
                .define('P', Tags.Items.DYES_PURPLE)
                .pattern("SSS")
                .pattern("FFF")
                .pattern(" P ")
                .save(pWriter, recipeLoc(AtomItemBlocks.RAILROAD_TRUSS_PURPLE, 2));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtomItemBlocks.RAILROAD_TRUSS_BLACK.get(), 3)
                .unlockedBy(getHasName(Items.RAIL), has(Items.RAIL))
                .define('S', Items.SPRUCE_PLANKS)
                .define('F', ItemTags.WOODEN_FENCES)
                .define('B', Tags.Items.DYES_BLACK)
                .pattern("SSS")
                .pattern("FFF")
                .pattern(" B ")
                .save(pWriter);

        SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
                .save(pWriter, recipeId(AtomItemBlocks.STREETLAMP_FIRE));
        SpecialRecipeBuilder.special(AtomRecipes.RECIPE_TT.get())
                .save(pWriter, recipeId(AtomItemBlocks.STREETLAMP_FLESH));

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtomItemBlocks.STREETLAMP_GLOWSTONE.get())
                .unlockedBy(getHasName(Items.GLOWSTONE), has(Items.GLOWSTONE))
                .define('I', Tags.Items.INGOTS_IRON)
                .define('P', Items.HEAVY_WEIGHTED_PRESSURE_PLATE)
                .define('G', Tags.Items.GLASS_COLORLESS)
                .define('S', Items.GLOWSTONE)
                .pattern("IPI")
                .pattern("GSG")
                .pattern("III")
                .save(pWriter);

        //Fun Idea: Make a crafting recipe for all burnable ItemBlocks...
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, AtomItemBlocks.TORCH_SQUARED.get(), 8)
                .unlockedBy(getHasName(Items.COAL_BLOCK), has(AtomTags.Items.COAL_BLOCK))
                .unlockedBy("has_log_any", has(ItemTags.LOGS))
                .define('C', AtomTags.Items.COAL_BLOCK)
                .define('L', ItemTags.LOGS)
                .pattern("C")
                .pattern("L")
                .save(pWriter, recipeVariant(AtomItemBlocks.TORCH_SQUARED, "coal"));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AtomItems.AUTO_FOOD.get())
                .unlockedBy(getHasName(Items.MELON_SLICE), has(Items.MELON_SLICE))
                .define('M', Items.MELON)
                .define('D', Items.DISPENSER)
                .pattern("MMM")
                .pattern("MDM")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AtomItems.AUTO_LIGHT.get())
                .unlockedBy(getHasName(Items.SHROOMLIGHT), has(Items.SHROOMLIGHT))
                .define('G', Items.GOLD_INGOT)
                .define('S', Items.SHROOMLIGHT)
                .pattern("GGG")
                .pattern("G G")
                .pattern(" S ")
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, AtomItems.WE_WAND.get())
                .unlockedBy(getHasName(invisibleLight), has(invisibleLight))
                .define('L', invisibleLight)
                .define('E', Items.ENDER_PEARL)
                .define('I', Tags.Items.INGOTS_IRON)
                .pattern("LEL")
                .pattern(" I ")
                .pattern(" I ")
                .save(pWriter);

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.GOLDEN_HELMET),
                        Ingredient.of((Items.BREAD)),
                        RecipeCategory.COMBAT,
                        AtomItems.DRTOAST_HEAD.get())
                .unlocks(getHasName(Items.BREAD), has(Items.BREAD))
                .unlocks(getHasName(Items.GOLDEN_HELMET), has(Items.GOLDEN_HELMET))
                .save(pWriter, recipeIdSmithing(AtomItems.DRTOAST_HEAD));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.NETHERITE_HELMET),
                        Ingredient.of(Items.RED_WOOL),
                        RecipeCategory.COMBAT, AtomItems.KNIGHT_HEAD.get())
                .unlocks(getHasName(Items.NETHERITE_HELMET), has(Items.NETHERITE_HELMET))
                .unlocks(getHasName(Items.RED_WOOL), has(Items.RED_WOOL))
                .save(pWriter, recipeIdSmithing(AtomItems.KNIGHT_HEAD));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.NETHERITE_CHESTPLATE),
                        Ingredient.of(Items.LEATHER), RecipeCategory.COMBAT,
                        AtomItems.KNIGHT_CHEST.get())
                .unlocks(getHasName(Items.NETHERITE_CHESTPLATE), has(Items.NETHERITE_CHESTPLATE))
                .unlocks(getHasName(Items.LEATHER), has(Items.LEATHER))
                .save(pWriter, recipeIdSmithing(AtomItems.KNIGHT_CHEST));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.NETHERITE_LEGGINGS),
                        Ingredient.of(Items.QUARTZ),
                        RecipeCategory.COMBAT,
                        AtomItems.KNIGHT_LEGS.get())
                .unlocks(getHasName(Items.NETHERITE_LEGGINGS), has(Items.NETHERITE_LEGGINGS))
                .unlocks(getHasName(Items.QUARTZ), has(Items.QUARTZ))
                .save(pWriter, recipeIdSmithing(AtomItems.KNIGHT_LEGS));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(Items.NETHERITE_BOOTS),
                        Ingredient.of(Items.GOLD_INGOT),
                        RecipeCategory.COMBAT,
                        AtomItems.KNIGHT_FEET.get())
                .unlocks(getHasName(Items.NETHERITE_BOOTS), has(Items.NETHERITE_BOOTS))
                .unlocks(getHasName(Items.GOLD_INGOT), has(Items.GOLD_INGOT))
                .save(pWriter, recipeIdSmithing(AtomItems.KNIGHT_FEET));
    }

    private static String recipeIdSmithing(@Nonnull RegistryObject<?> ro) {
        return ro.getId().toString() + "_smithing";
    }

    private static String recipeId(@Nonnull RegistryObject<?> ro) {
        return ro.getId().toString();
    }

    @SuppressWarnings("SameParameterValue")
    private static ResourceLocation recipeLoc(@Nonnull RegistryObject<?> ro, int index) {
        return new ResourceLocation(ro.getId().getNamespace(), ro.getId().getPath() + "_" + index);
    }

    @SuppressWarnings("SameParameterValue")
    private static ResourceLocation recipeVariant(@Nonnull RegistryObject<?> ro, String variant) {
        return new ResourceLocation(ro.getId().getNamespace(), ro.getId().getPath() + "_" + variant);
    }
}
