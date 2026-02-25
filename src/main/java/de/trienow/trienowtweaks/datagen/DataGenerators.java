package de.trienow.trienowtweaks.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static de.trienow.trienowtweaks.main.TrienowTweaks.MODID;

/**
 * @author (c) trienow 2022 - 2026
 */
@EventBusSubscriber(modid = MODID)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherDataServer(GatherDataEvent.Server evt)
	{
		evt.createProvider(GenBlockTags::new);
		evt.createProvider(GenItemTags::new);
		evt.createProvider(GenRecipes.Runner::new);
	}
}
