package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * @author (c) trienow 2022
 */
public class GenItemTags extends ItemTagsProvider
{
	public GenItemTags(DataGenerator pGenerator, BlockTagsProvider pBlockTagsProvider, ExistingFileHelper existingFileHelper)
	{
		super(pGenerator, pBlockTagsProvider, TrienowTweaks.MODID, existingFileHelper);
	}

	@Override
	protected void addTags()
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
