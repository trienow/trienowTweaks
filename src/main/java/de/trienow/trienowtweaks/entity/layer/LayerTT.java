package de.trienow.trienowtweaks.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import de.trienow.trienowtweaks.entity.model.ModelKnight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;

/**
 * @author (c) trienow 2022 - 2026
 */
public class LayerTT<T extends HumanoidRenderState, M extends HumanoidModel<T>> extends RenderLayer<T, M>
{
	private final ModelKnight<T> MODEL_KNIGHT;

	private final ModelDrToast<T> MODEL_DRTOAST;

	public LayerTT(RenderLayerParent<T, M> entityRendererOwner)
	{
		super(entityRendererOwner);

		final ModelPart bakedKnight = Minecraft.getInstance().getEntityModels().bakeLayer(RenderSetup.KNIGHT_LAYER_LOCATION);
		this.MODEL_KNIGHT = new ModelKnight<>(null, bakedKnight);

		final ModelPart bakedDrToast = Minecraft.getInstance().getEntityModels().bakeLayer(RenderSetup.DRTOAST_LAYER_LOCATION);
		this.MODEL_DRTOAST = new ModelDrToast<>(EquipmentSlot.HEAD, bakedDrToast);
	}

	@Override public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, T t, float v, float v1)
	{
		if (!t.isInvisible)
		{
			LayerTtType layerType = t.getRenderDataOrDefault(RenderSetup.LAYER_TYPE_CTX, LayerTtType.NONE);
			if (layerType == LayerTtType.KNIGHT)
			{
				poseStack.pushPose();

				this.getParentModel().copyPropertiesTo(this.MODEL_KNIGHT);
				coloredCutoutModelCopyLayerRender(MODEL_KNIGHT, RenderSetup.KNIGHT_LAYER_TEXTURE, poseStack, multiBufferSource, packedLight, t, -1);

				poseStack.popPose();
			}
			else if (layerType == LayerTtType.TOAST)
			{
				poseStack.pushPose();

				VertexConsumer consumer = multiBufferSource.getBuffer(RenderType.entitySolid(RenderSetup.DRTOAST_LAYER_TEXTURE));
				int i = LivingEntityRenderer.getOverlayCoords(t, 0.0F);
				this.getParentModel().getHead().translateAndRotate(poseStack);
				MODEL_DRTOAST.getHeadPart().render(poseStack, consumer, packedLight, i);

				poseStack.popPose();
			}
		}
	}
}
