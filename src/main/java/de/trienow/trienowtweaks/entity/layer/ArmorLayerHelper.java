package de.trienow.trienowtweaks.entity.layer;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * A hacky class, to hide the armor from players
 *
 * @author (c) trienow 2022
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorLayerHelper
{
	private static final Map<Entity, Consumer<PlayerRenderer>> parkedArmorRenderers = new ConcurrentHashMap<>();

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onRenderPlayerPre(final RenderPlayerEvent.Pre evt)
	{
		final Player player = evt.getPlayer();
		final String playerName = player.getName().getString();
		if (RenderSetup.TRIENOW.equals(playerName) || RenderSetup.TOASTY.equals(playerName))
		{
			if (RenderSetup.shouldRenderLayer(player) == LayerTtRenderMode.SHOW)
			{
				removeArmorLayer(player, evt.getRenderer().layers);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static <E extends LivingEntity, M extends EntityModel<E>> void removeArmorLayer(Entity renderedEntity, List<RenderLayer<E, M>> renderLayerList)
	{
		for (RenderLayer<E, M> renderLayer : renderLayerList)
		{
			if (renderLayer instanceof HumanoidArmorLayer<?, ?, ?> armorLayer)
			{
				parkedArmorRenderers.putIfAbsent(renderedEntity,
						(playerRenderer) -> playerRenderer.layers.add(1, (RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>) armorLayer));
				renderLayerList.remove(renderLayer);
				break;
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onRenderPlayerPost(final RenderPlayerEvent.Post evt)
	{
		final Entity renderedEntity = evt.getEntity();
		final Consumer<PlayerRenderer> parkedArmorRenderer = parkedArmorRenderers.get(renderedEntity);
		if (parkedArmorRenderer instanceof Consumer<PlayerRenderer>)
		{
			parkedArmorRenderer.accept(evt.getRenderer());
			parkedArmorRenderers.remove(renderedEntity);
		}
	}
}
