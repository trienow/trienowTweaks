package de.trienow.trienowtweaks.entity.layer;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * A hacky class, to hide the armor from players
 *
 * @author (c) trienow 2023
 */
@EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.CLIENT)
public class ArmorLayerHelper
{
	private static final Map<Integer, Consumer<PlayerRenderer>> parkedArmorRenderers = new ConcurrentHashMap<>();

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onRenderPlayerPre(final RenderPlayerEvent.Pre evt)
	{
		LayerTtType renderDataOrDefault = evt.getRenderState().getRenderDataOrDefault(RenderSetup.LAYER_TYPE_CTX, LayerTtType.NONE);
		if (renderDataOrDefault != LayerTtType.NONE)
		{
			for (RenderLayer<PlayerRenderState, PlayerModel> renderLayer : evt.getRenderer().layers)
			{
				if (renderLayer instanceof HumanoidArmorLayer<?, ?, ?> armorLayer)
				{
					parkedArmorRenderers.putIfAbsent(0, (playerRenderer) -> playerRenderer.layers.add(1, renderLayer));
					evt.getRenderer().layers.remove(renderLayer);
					break;
				}
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onRenderPlayerPost(final RenderPlayerEvent.Post evt)
	{
		if (parkedArmorRenderers.containsKey(0))
		{
			parkedArmorRenderers.get(0).accept(evt.getRenderer());
			parkedArmorRenderers.remove(0);
		}
	}
}
