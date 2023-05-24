package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * @author (c) trienow 2022 - 2023
 */
public class GenItemTags extends ItemTagsProvider
{
	public GenItemTags(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, String modId, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(p_275343_, p_275729_, p_275322_, modId, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider pProvider)
	{
		copy(AtomTags.Blocks.COPPER_CUT, AtomTags.Items.COPPER_CUT);
		copy(AtomTags.Blocks.RAILROAD_TRUSS, AtomTags.Items.RAILROAD_TRUSS);

		tag(AtomTags.Items.MEAT_RAW)
				.add(Items.ROTTEN_FLESH)
				.add(Items.BEEF)
				.add(Items.PORKCHOP)
				.add(Items.CHICKEN)
				.add(Items.MUTTON)
				.add(Items.RABBIT_FOOT)
				.add(Items.RABBIT);
	}
}
