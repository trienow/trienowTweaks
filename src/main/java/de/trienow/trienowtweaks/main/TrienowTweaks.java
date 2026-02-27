package de.trienow.trienowtweaks.main;

import de.trienow.trienowtweaks.atom.*;
import de.trienow.trienowtweaks.config.Config;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author (c) trienow 2016 - 2026
 */
@Mod(TrienowTweaks.MODID)
public class TrienowTweaks
{
	public static final String MODID = "trienowtweaks";
	public static final Logger LOG = LogManager.getLogger(MODID);

	/**
	 * Constructs an object of type TrienowTweaks.java
	 */
	public TrienowTweaks(ModContainer container)
	{
		final IEventBus modEventBus = container.getEventBus();

		Config.init(container);
		AtomDataComponents.init(modEventBus);
		AtomAttachments.init(modEventBus);

		AtomBlocks.init(modEventBus);
		AtomItems.init(modEventBus);
		AtomItemBlocks.init(modEventBus);
		AtomCreativeTab.init(modEventBus);
		AtomTiles.init(modEventBus);
		AtomRecipes.init(modEventBus);
	}
}
