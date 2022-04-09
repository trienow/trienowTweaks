package de.trienow.trienowtweaks.main;

import de.trienow.trienowtweaks.atom.*;
import de.trienow.trienowtweaks.config.Config;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.StartupMessageManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * @author (c) trienow 2016 - 2022
 */
@Mod(TrienowTweaks.MODID)
public class TrienowTweaks
{
	public static final String MODID = "trienowtweaks";

	public static final Logger LOG = LogManager.getLogger();

	public static final Random rnd = new Random();

	public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab("trienowtweaks")
	{
		@Override
		public ItemStack makeIcon()
		{
			return new ItemStack(AtomItems.WE_WAND.get());
		}
	};

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

		StartupMessageManager.addModMessage("Hello from trienowTweaks!");
	}
}
