package de.trienow.trienowtweaks.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static de.trienow.trienowtweaks.main.TrienowTweaks.MODID;

/**
 * @author (c) trienow 2022 - 2023
 */
@EventBusSubscriber(modid = MODID)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherDataServer(GatherDataEvent.Server evt)
	{
		evt.createProvider(GenBlockTags::new);
		evt.createProvider(GenItemTags::new);
		DataGenerator gen = evt.getGenerator();
		final GenBlockTags genBlockTags = gen.addProvider(evt.includeServer(), (DataProvider.Factory<GenBlockTags>) (output) ->
				new GenBlockTags(output, evt.getLookupProvider(), MODID, evt.getExistingFileHelper())
		);
		gen.addProvider(evt.includeServer(), (DataProvider.Factory<GenItemTags>) (output) ->
				new GenItemTags(output, evt.getLookupProvider(), genBlockTags.contentsGetter(), MODID, evt.getExistingFileHelper())
		);
		gen.addProvider(evt.includeServer(), (DataProvider.Factory<GenRecipes>) (output) ->
				new GenRecipes(output)
		);
	}
}
