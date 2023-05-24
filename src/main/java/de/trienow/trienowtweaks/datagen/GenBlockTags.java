package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author (c) trienow 2022 - 2023
 */
public class GenBlockTags extends BlockTagsProvider
{
	public GenBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, String modId, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, TrienowTweaks.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider)
	{
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
				.add(AtomBlocks.ENTITY_PROHIBITATOR.get())
				.add(AtomBlocks.ITEM_DETECTOR.get())
				.add(AtomBlocks.COMPACT_CRAFTER.get())
				.add(AtomBlocks.MINECART_KILLER.get())
				.add(AtomBlocks.INVISIBLE_WALL.get())
				.add(AtomBlocks.STREETLAMP_FIRE.get())
				.add(AtomBlocks.STREETLAMP_GLOWSTONE.get());

		tag(BlockTags.MINEABLE_WITH_AXE)
				.add(AtomBlocks.RAILROAD_TRUSS_BLACK.get())
				.add(AtomBlocks.RAILROAD_TRUSS_BRIGHT.get())
				.add(AtomBlocks.RAILROAD_TRUSS_PURPLE.get())
				.add(AtomBlocks.RAILROAD_TRUSS_WOODEN.get());

		tag(AtomTags.Blocks.COPPER_CUT)
				.add(Blocks.CUT_COPPER)
				.add(Blocks.EXPOSED_CUT_COPPER)
				.add(Blocks.OXIDIZED_CUT_COPPER)
				.add(Blocks.WAXED_CUT_COPPER)
				.add(Blocks.WAXED_WEATHERED_CUT_COPPER)
				.add(Blocks.WAXED_OXIDIZED_CUT_COPPER);

		tag(AtomTags.Blocks.RAILROAD_TRUSS)
				.add(AtomBlocks.RAILROAD_TRUSS_BLACK.get())
				.add(AtomBlocks.RAILROAD_TRUSS_BRIGHT.get())
				.add(AtomBlocks.RAILROAD_TRUSS_PURPLE.get())
				.add(AtomBlocks.RAILROAD_TRUSS_WOODEN.get());
	}
}
