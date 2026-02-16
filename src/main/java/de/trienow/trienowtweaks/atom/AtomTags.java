package de.trienow.trienowtweaks.atom;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/**
 * @author (c) trienow 2023
 */
public class AtomTags
{
	public static class Blocks
	{
		public static final TagKey<Block> COPPER_CUT = forge("blocks/copper_cut");
		public static final TagKey<Block> RAILROAD_TRUSS = forge("blocks/railroad_truss");

		private static TagKey<Block> forge(String name)
		{
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
		}
	}

	public static class Items
	{
		public static final TagKey<Item> COPPER_CUT = forge("blocks/copper_cut");
		public static final TagKey<Item> RAILROAD_TRUSS = forge("blocks/railroad_truss");

		public static final TagKey<Item> MEAT_RAW = forge("items/meat_raw");

		public static final TagKey<Item> COAL_BLOCK = forge("storage_blocks/coal");

		private static TagKey<Item> forge(String name)
		{
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath("forge", name));
		}
	}
}
