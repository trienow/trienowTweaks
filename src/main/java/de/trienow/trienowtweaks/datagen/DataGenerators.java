package de.trienow.trienowtweaks.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static de.trienow.trienowtweaks.main.TrienowTweaks.MODID;

/**
 * @author (c) trienow 2022 - 2023
 */
@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent evt)
	{
		DataGenerator gen = evt.getGenerator();
		final GenBlockTags genBlockTags = gen.addProvider(evt.includeServer(), (DataProvider.Factory<GenBlockTags>) (output) ->
				new GenBlockTags(output, evt.getLookupProvider(), MODID, evt.getExistingFileHelper())
		);
		gen.addProvider(evt.includeServer(), (DataProvider.Factory<GenItemTags>) (output) ->
				new GenItemTags(output, evt.getLookupProvider(), genBlockTags.contentsGetter(), MODID, evt.getExistingFileHelper())
		);
	}
}
