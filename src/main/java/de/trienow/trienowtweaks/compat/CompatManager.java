package de.trienow.trienowtweaks.compat;

import de.trienow.trienowtweaks.compat.curios.DummyCuriosProxy;
import de.trienow.trienowtweaks.compat.curios.ICuriosProxy;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;

import java.util.Optional;

/**
 * A main class to handle the access to different mods.
 *
 * @author (c) trienow 2020
 */
@SuppressWarnings("WeakerAccess")
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CompatManager
{
	public static ICuriosProxy curiosProxy;
	public static final ICompatProxy[] proxies = new ICompatProxy[1];
	private static int compatProxyId = 0;

	@SubscribeEvent
	public static void onCommonSetup(final FMLCommonSetupEvent evt)
	{
		try
		{
			Optional<? extends ModContainer> curiosOpt = ModList.get().getModContainerById("curios");
			if (curiosOpt.isPresent())
			{
				TrienowTweaks.LOG.info("Compats: Curios is present.");
				curiosProxy = Class.forName("de.trienow.trienowtweaks.compat.curios.CuriosProxy").asSubclass(ICuriosProxy.class).newInstance();
			}
			else
			{
				TrienowTweaks.LOG.info("Compats: Curios is not present.");
				curiosProxy = new DummyCuriosProxy();
			}
		}
		catch (InstantiationException | IllegalAccessException | ClassNotFoundException e)
		{
			final String errorText = "TrienowTweaks could not find a class it should contain. This should not happen and suggests, that the mod was not updated correctly. Please contact the author.";
			TrienowTweaks.LOG.error(errorText, e);
			curiosProxy = new DummyCuriosProxy();
		}

		AddCompatProxy(curiosProxy);
	}

	@SubscribeEvent
	public static void onInterModEnqueue(final InterModEnqueueEvent evt)
	{
		for (ICompatProxy proxy : proxies)
		{
			proxy.onEnqueueIMC(evt);
		}
	}

	private static void AddCompatProxy(ICompatProxy compatProxy)
	{
		proxies[compatProxyId++] = compatProxy;
	}
}
