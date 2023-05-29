package de.trienow.trienowtweaks.proxy;

import de.trienow.trienowtweaks.atom.*;
import de.trienow.trienowtweaks.blocks.BaseBlock;
import de.trienow.trienowtweaks.blocks.BaseItemBlock;
import de.trienow.trienowtweaks.main.Config;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

import java.io.File;
import java.util.Objects;

@Mod.EventBusSubscriber
public class ServerProxy
{
	public static Configuration config;

	public void preInit(FMLPreInitializationEvent evt)
	{
		File directory = evt.getModConfigurationDirectory();
		config = new Configuration(new File(directory.getPath(), "trienowTweaks.cfg"));
		Config.readConfig();
	}

	public void init(FMLInitializationEvent evt)
	{
		AtomRecipes.registerSmeltingRecipes();
	}

	public void postInit(FMLPostInitializationEvent evt)
	{
		Config.saveConfig();
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> evt)
	{
		IForgeRegistry<Block> reg = evt.getRegistry();
		reg.registerAll(AtomBlocks.blockList);
		reg.registerAll(AtomBlocks.subtypeBlockList);

		/*
		if (Loader.isModLoaded("botania"))
			reg.registerAll(AtomBlocks.blockListBotania);
		 */
		AtomTileEntities.registerTileEntities();
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt)
	{
		IForgeRegistry<Item> reg = evt.getRegistry();

		for (BaseBlock block : AtomBlocks.blockList)
		{
			reg.register(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
		}

/*
		for (BaseBlock block : AtomBlocks.blockListBotania)
		{
			reg.register(new ItemBlock(block).setRegistryName(Objects.requireNonNull(block.getRegistryName())));
		}
 */

		for (BaseItemBlock block : AtomBlocks.subtypeBlockList)
		{
			reg.register(new AtomItemBlock(block));
		}

		reg.registerAll(AtomItems.itemList);
		reg.registerAll(AtomItemArmor.itemArmorList);
	}
}
