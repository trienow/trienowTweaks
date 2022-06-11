package de.trienow.trienowtweaks.entity.layer;

import de.trienow.trienowtweaks.capabilities.IPlayerCapability;
import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import de.trienow.trienowtweaks.entity.model.ModelKnight;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author (c) trienow 2022
 * Thanks to Gigaherz for your gist explaining how to use layers!
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RenderSetup
{
	public static final String TRIENOW = "trienow";
	public static final String TOASTY = "Sandwichmania";

	public static final ResourceLocation KNIGHT_LAYER_TEXTURE = new ResourceLocation(TrienowTweaks.MODID, "textures/models/armor/knight_layer_1.png");
	public static final ModelLayerLocation KNIGHT_LAYER_LOCATION = new ModelLayerLocation(KNIGHT_LAYER_TEXTURE, "knight");

	public static final ResourceLocation DRTOAST_LAYER_TEXTURE = new ResourceLocation(TrienowTweaks.MODID, "textures/models/armor/drtoast_layer_1.png");
	public static final ModelLayerLocation DRTOAST_LAYER_LOCATION = new ModelLayerLocation(DRTOAST_LAYER_TEXTURE, "drToast");

	private static final Map<UUID, LazyOptional<IPlayerCapability>> MAP = new TreeMap<>();

	public static void clearPcapCache()
	{
		MAP.clear();
	}

	/**
	 * Returns the way the layer should be rendered
	 *
	 * @param entity The entity on which the layer should be rendered
	 * @return The way the layer should be rendered
	 */
	public static LayerTtRenderMode shouldRenderLayer(Entity entity)
	{
		LayerTtRenderMode result = LayerTtRenderMode.SHOW;
		UUID entityUuid = entity.getUUID();
		LazyOptional<IPlayerCapability> loPcap = MAP.get(entityUuid);

		if (loPcap == null)
		{
			loPcap = entity.getCapability(IPlayerCapability.PLAYER_CAP);
			loPcap.addListener((deadLo) -> MAP.remove(entityUuid));
			MAP.put(entityUuid, loPcap);
		}

		IPlayerCapability pcap = loPcap.orElse(null);
		if (pcap instanceof IPlayerCapability)
		{
			result = pcap.getLayerTtRenderMode();
		}
		else
		{
			MAP.remove(entityUuid);
		}

		return result;
	}

	@SubscribeEvent
	public static void onRegisterLayerDefinition(final EntityRenderersEvent.RegisterLayerDefinitions evt)
	{
		evt.registerLayerDefinition(KNIGHT_LAYER_LOCATION, ModelKnight::createLayer);
		evt.registerLayerDefinition(DRTOAST_LAYER_LOCATION, ModelDrToast::createLayer);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SubscribeEvent
	public static void onEntityAddLayers(final EntityRenderersEvent.AddLayers evt)
	{
		for (String skin : evt.getSkins())
		{
			final LivingEntityRenderer livingEntityRenderer = evt.getSkin(skin);
			if (livingEntityRenderer instanceof LivingEntityRenderer)
			{
				livingEntityRenderer.addLayer(new LayerTT<>(livingEntityRenderer));
			}
		}
	}
}
