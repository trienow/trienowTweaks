package de.trienow.trienowtweaks.entity.layer;

import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import de.trienow.trienowtweaks.entity.model.ModelKnight;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
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

	@SubscribeEvent
	public static void onRegisterLayerDefinition(final EntityRenderersEvent.RegisterLayerDefinitions evt)
	{
		evt.registerLayerDefinition(KNIGHT_LAYER_LOCATION, ModelKnight::createLayer);
		evt.registerLayerDefinition(DRTOAST_LAYER_LOCATION, ModelDrToast::createLayer);
	}

	@SubscribeEvent
	public static void onEntityAddLayers(final EntityRenderersEvent.AddLayers evt)
	{
		for (String skin : evt.getSkins())
		{
			final LivingEntityRenderer<Player, EntityModel<Player>> renderer = evt.getSkin(skin);
			renderer.addLayer(new LayerTT<>(renderer));
		}
	}
}
