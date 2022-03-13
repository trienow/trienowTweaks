package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents
{
	/*private static final AtomicBoolean ADDED_PLAYER_RENDERER = new AtomicBoolean(false);

	@SubscribeEvent
	public static void onRenderPlayerEvent(final RenderPlayerEvent.Pre evt)
	{
		PlayerRenderer renderer = evt.getRenderer();

		synchronized (ADDED_PLAYER_RENDERER)
		{
			if (!ADDED_PLAYER_RENDERER.get())
			{
				ADDED_PLAYER_RENDERER.set(true);
				renderer.addLayer(new LayerTT(renderer));
			}
		}
	}*/
}
