package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

/**
 * @author (c) trienow 2022
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent evt)
	{
		DataGenerator gen = evt.getGenerator();

		if (evt.includeServer())
		{
			final GenBlockTags blockTagsProvider = new GenBlockTags(gen, evt.getExistingFileHelper());
			gen.addProvider(blockTagsProvider);
			gen.addProvider(new GenItemTags(gen, blockTagsProvider, evt.getExistingFileHelper()));
			gen.addProvider(new GenRecipes(gen));
		}
	}
}
