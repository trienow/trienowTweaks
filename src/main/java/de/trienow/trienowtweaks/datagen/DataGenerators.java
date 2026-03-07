package de.trienow.trienowtweaks.datagen;

import de.trienow.trienowtweaks.main.TrienowTweaks;
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
		TrienowTweaks.LOG.info("Setting up data gathering...");
		evt.createProvider(GenBlockTags::new);
		evt.createProvider(GenItemTags::new);
		evt.createProvider(GenRecipes.Runner::new);
	}

	@SubscribeEvent
	public static void gatherDataClient(GatherDataEvent.Server evt)
	{
		evt.createProvider((packOutput, completableFuture) ->
				new ClientItemDefinitionsProvider(packOutput));
	}
}
