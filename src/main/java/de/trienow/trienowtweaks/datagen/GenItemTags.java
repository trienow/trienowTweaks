package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.atom.AtomTags;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagEntry;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

/**
 * @author (c) trienow 2022 - 2023
 */
public class GenItemTags extends ItemTagsProvider
{
	public GenItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider)
	{
		super(output, lookupProvider, TrienowTweaks.MODID);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider)
	{
		this.tag(AtomTags.Items.MEAT_RAW)
				.add(Items.ROTTEN_FLESH)
				.add(Items.BEEF)
				.add(Items.PORKCHOP)
				.add(Items.CHICKEN)
				.add(Items.MUTTON)
				.add(Items.RABBIT_FOOT)
				.add(Items.RABBIT);

		this.tag(AtomTags.Items.COPPER_CUT)
				.add(TagEntry.optionalTag(AtomTags.Blocks.COPPER_CUT.location()));
		this.tag(AtomTags.Items.RAILROAD_TRUSS)
				.add(TagEntry.optionalTag(AtomTags.Blocks.RAILROAD_TRUSS.location()));

		this.tag(AtomTags.Items.SKULLS)
				.add(Items.CREEPER_HEAD,
						Items.DRAGON_HEAD,
						Items.PIGLIN_HEAD,
						Items.PLAYER_HEAD,
						Items.ZOMBIE_HEAD,
						Items.SKELETON_SKULL,
						Items.WITHER_SKELETON_SKULL);
	}
}
