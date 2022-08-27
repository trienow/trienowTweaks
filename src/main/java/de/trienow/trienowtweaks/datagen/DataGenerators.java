package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
			gen.addProvider(true, blockTagsProvider);
			gen.addProvider(true, new GenItemTags(gen, blockTagsProvider, evt.getExistingFileHelper()));
			gen.addProvider(true, new GenRecipes(gen));
		}
	}
}
