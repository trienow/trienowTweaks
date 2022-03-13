package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class GenBlockTags extends BlockTagsProvider
{
	public GenBlockTags(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(pGenerator, TrienowTweaks.MODID, existingFileHelper);
	}

	@Override
	protected void addTags()
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
	}
}
