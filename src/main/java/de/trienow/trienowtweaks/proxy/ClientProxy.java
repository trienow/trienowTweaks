package de.trienow.trienowtweaks.proxy;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import de.trienow.trienowtweaks.atom.AtomItemArmor;
import de.trienow.trienowtweaks.atom.AtomItems;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends ServerProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent evt)
	{
		super.preInit(evt);
	}

	@Override
	public void init(FMLInitializationEvent evt)
	{
		super.init(evt);
	}

	@Override
	public void postInit(FMLPostInitializationEvent evt)
	{
		super.postInit(evt);
	}

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent evt)
	{
		AtomBlocks.initModels();
		AtomItems.initModels();
		AtomItemArmor.initModels();
	}
}