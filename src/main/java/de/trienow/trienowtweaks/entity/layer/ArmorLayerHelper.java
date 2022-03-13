package de.trienow.trienowtweaks.entity.layer;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.function.Consumer;

/**
 * A hacky class, to hide the armor from players
 *
 * @author (c) trienow 2022
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ArmorLayerHelper
{
	private static Consumer<PlayerRenderer> parkedArmorRenderer;

	@SubscribeEvent(priority = EventPriority.LOW)
	public static void onRenderPlayerPre(final RenderPlayerEvent.Pre evt)
	{
		final String playerName = evt.getPlayer().getName().getString();
		if (RenderSetup.TRIENOW.equals(playerName) || RenderSetup.TOASTY.equals(playerName))
		{
			removeArmorLayer(evt.getRenderer().layers);
		}
	}

	@SuppressWarnings("unchecked")
	private static <E extends LivingEntity, M extends EntityModel<E>> void removeArmorLayer(List<RenderLayer<E, M>> renderLayerList)
	{
		for (RenderLayer<E, M> renderLayer : renderLayerList)
		{
			if (renderLayer instanceof HumanoidArmorLayer<?, ?, ?> armorLayer)
			{
				parkedArmorRenderer = playerRenderer -> playerRenderer.layers.add(1, (RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>>) armorLayer);
				renderLayerList.remove(renderLayer);
				break;
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onRenderPlayerPost(final RenderPlayerEvent.Post evt)
	{
		if (parkedArmorRenderer != null)
		{
			parkedArmorRenderer.accept(evt.getRenderer());
			parkedArmorRenderer = null;
		}
	}
}
