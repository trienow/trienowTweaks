package de.trienow.trienowtweaks.entity.layer;

import com.google.common.reflect.TypeToken;
import de.trienow.trienowtweaks.atom.AtomAttachments;
import de.trienow.trienowtweaks.entity.model.ModelAmogus;
import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import de.trienow.trienowtweaks.entity.model.ModelKnight;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;

/**
 * @author (c) trienow 2022 - 2026
 * Thanks to Gigaherz for your gist explaining how to use layers!
 */
@EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.CLIENT)
public class RenderSetup
{
	public static final ResourceLocation KNIGHT_LAYER_TEXTURE = ResourceLocation.fromNamespaceAndPath(
			TrienowTweaks.MODID,
			"textures/models/armor/knight_layer_1.png");
	public static final ModelLayerLocation KNIGHT_LAYER_LOCATION = new ModelLayerLocation(KNIGHT_LAYER_TEXTURE, "knight");

	public static final ResourceLocation DRTOAST_LAYER_TEXTURE = ResourceLocation.fromNamespaceAndPath(
			TrienowTweaks.MODID,
			"textures/models/armor/drtoast_layer_1.png");
	public static final ModelLayerLocation DRTOAST_LAYER_LOCATION = new ModelLayerLocation(DRTOAST_LAYER_TEXTURE, "drToast");

	public static final ResourceLocation AMOGUS_LAYER_TEXTURE = ResourceLocation.fromNamespaceAndPath(
			TrienowTweaks.MODID,
			"textures/models/armor/amogus.png");
	public static final ModelLayerLocation AMOGUS_LAYER_LOCATION = new ModelLayerLocation(DRTOAST_LAYER_TEXTURE, "amogus");

	public static final ContextKey<LayerTtType> LAYER_TYPE_CTX = new ContextKey<>(
			ResourceLocation.fromNamespaceAndPath(TrienowTweaks.MODID, "layer_type_ctx")
	);

	@SubscribeEvent
	public static void onRegisterRenderStateModifiers(RegisterRenderStateModifiersEvent evt)
	{
		evt.registerEntityModifier(new TypeToken<LivingEntityRenderer<LivingEntity, LivingEntityRenderState, ?>>()
								   {
								   },
				(entity, state) -> state.setRenderData(LAYER_TYPE_CTX, LayerTtType.fromId(entity.getData(AtomAttachments.LAYER_TT)))
		);
	}

	@SubscribeEvent
	public static void onRegisterLayerDefinition(final EntityRenderersEvent.RegisterLayerDefinitions evt)
	{
		evt.registerLayerDefinition(KNIGHT_LAYER_LOCATION, ModelKnight::createLayer);
		evt.registerLayerDefinition(DRTOAST_LAYER_LOCATION, ModelDrToast::createLayer);
		evt.registerLayerDefinition(AMOGUS_LAYER_LOCATION, ModelAmogus::createLayer);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SubscribeEvent
	public static void onEntityAddLayers(final EntityRenderersEvent.AddLayers evt)
	{
		for (PlayerSkin.Model skin : evt.getSkins())
		{
			if (evt.getSkin(skin) instanceof LivingEntityRenderer livingEntityRenderer)
			{
				livingEntityRenderer.addLayer(new LayerTT<>(livingEntityRenderer));
			}
		}
	}
}
