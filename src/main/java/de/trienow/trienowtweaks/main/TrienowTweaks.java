package de.trienow.trienowtweaks.main;

import de.trienow.trienowtweaks.atom.AtomCommands;
import de.trienow.trienowtweaks.atom.AtomItems;
import de.trienow.trienowtweaks.proxy.ServerProxy;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@Mod(modid = TrienowTweaks.MODID, version = TrienowTweaks.VERSION)
public class TrienowTweaks
{
	public static final String MODID = "trienowtweaks";
	public static final String VERSION = "@VERSION@";

	@SidedProxy(clientSide = "de.trienow.trienowtweaks.proxy.ClientProxy", serverSide = "de.trienow.trienowtweaks.proxy.ServerProxy")
	public static ServerProxy proxy;

	public static final CreativeTabs trienowTab = new CreativeTabs("trienowCreativeTab")
	{
		@Override
		public ItemStack getTabIconItem()
		{
			return AtomItems.itemWeWand.getDefaultInstance();
		}
	};

	public static Logger logger;
	public static Random rnd;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent evt)
	{
		logger = evt.getModLog();
		logger.debug("[TT] Starting preInit");
		rnd = new Random();
		proxy.preInit(evt);
		logger.debug("[TT] Finished preInit");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent evt)
	{
		logger.debug("[TT] Started init");
		proxy.init(evt);
		logger.debug("[TT] Finished init");
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent evt)
	{
		logger.debug("[TT] Started postInit");
		proxy.postInit(evt);
		logger.debug("[TT] Finished postInit");
	}

	@Mod.EventHandler
	public void registerCommands(FMLServerStartingEvent event)
	{
		logger.debug("[TT] Registering Commands");
		AtomCommands.registerCommands((ServerCommandManager) event.getServer().getCommandManager());
		logger.debug("[TT] Registered Commands");
	}
}
