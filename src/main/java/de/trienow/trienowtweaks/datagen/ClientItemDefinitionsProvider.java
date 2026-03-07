package de.trienow.trienowtweaks.datagen;

import com.google.gson.JsonObject;
import de.trienow.trienowtweaks.atom.AtomItemBlocks;
import de.trienow.trienowtweaks.atom.AtomItems;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;

import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ClientItemDefinitionsProvider implements DataProvider
{
	private final PackOutput packOutput;

	public ClientItemDefinitionsProvider(PackOutput packOutput)
	{
		this.packOutput = packOutput;
	}

	@Override public CompletableFuture<?> run(CachedOutput cachedOutput)
	{
		PackOutput.PathProvider pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "items");

		CompletableFuture<?>[] futures = Stream.concat(AtomItems.ITEMSLIST.stream(), AtomItemBlocks.ITEMSLIST.stream()).map(item -> {
			ResourceLocation itemLoc = item.getId();
			ResourceLocation modelLoc;
			if (item.get() instanceof BlockItem blockItem)
			{
				ResourceLocation blockLoc = BuiltInRegistries.BLOCK.getKey(blockItem.getBlock());
				modelLoc = ResourceLocation.fromNamespaceAndPath(TrienowTweaks.MODID, "block/" + blockLoc.getPath());
			}
			else
			{
				modelLoc = ResourceLocation.fromNamespaceAndPath(TrienowTweaks.MODID, "item/" + itemLoc.getPath());
			}

			JsonObject root = new JsonObject();
			JsonObject model = new JsonObject();
			model.addProperty("type", "minecraft:model");
			model.addProperty("model", modelLoc.toString());
			root.add("model", model);

			Path outputPath = pathProvider.json(itemLoc);
			return DataProvider.saveStable(cachedOutput, root, outputPath);
		}).toArray(CompletableFuture[]::new);

		return CompletableFuture.allOf(futures);
	}

	@Override public String getName()
	{
		return TrienowTweaks.MODID + " Item Definitions";
	}
}
