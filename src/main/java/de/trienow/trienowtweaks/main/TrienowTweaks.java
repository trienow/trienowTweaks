package de.trienow.trienowtweaks.main;

import de.trienow.trienowtweaks.atom.*;
import de.trienow.trienowtweaks.config.Config;
import de.trienow.trienowtweaks.network.PacketHandler;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author (c) trienow 2016 - 2023
 */
@Mod(TrienowTweaks.MODID)
public class TrienowTweaks
{
	public static final String MODID = "trienowtweaks";
	public static final Logger LOG = LogManager.getLogger();

	/**
	 * Constructs an object of type TrienowTweaks.java
	 */
	public TrienowTweaks()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		Config.init();
		AtomBlocks.init(modEventBus);
		AtomItems.init(modEventBus);
		AtomItemBlocks.init(modEventBus);
		AtomTiles.init(modEventBus);
		AtomRecipes.init(modEventBus);

		PacketHandler.register();
	}
}
