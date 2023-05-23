package de.trienow.trienowtweaks.main;

import de.trienow.trienowtweaks.atom.AtomBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup
{
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent evt)
	{
		ItemBlockRenderTypes.setRenderLayer(AtomBlocks.STREETLAMP_FIRE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtomBlocks.STREETLAMP_FLESH.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtomBlocks.STREETLAMP_GLOWSTONE.get(), RenderType.cutout());

		//ItemBlockRenderTypes.setRenderLayer(AtomBlocks.FAKE_FIRE.get(), RenderType.cutout());

		ItemBlockRenderTypes.setRenderLayer(AtomBlocks.RAILROAD_TRUSS_BLACK.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtomBlocks.RAILROAD_TRUSS_BRIGHT.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtomBlocks.RAILROAD_TRUSS_PURPLE.get(), RenderType.cutout());
		ItemBlockRenderTypes.setRenderLayer(AtomBlocks.RAILROAD_TRUSS_WOODEN.get(), RenderType.cutoutMipped());
	}
}
