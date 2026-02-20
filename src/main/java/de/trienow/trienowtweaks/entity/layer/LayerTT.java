package de.trienow.trienowtweaks.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import de.trienow.trienowtweaks.entity.model.ModelKnight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;

/**
 * @author (c) trienow 2022 - 2026
 */
public class LayerTT<T extends HumanoidRenderState, M extends HumanoidModel<T>> extends RenderLayer<T, M>
{
	private final ModelKnight<T> MODEL_KNIGHT_HEAD;
	private final ModelKnight<T> MODEL_KNIGHT_CHEST;
	private final ModelKnight<T> MODEL_KNIGHT_LEGS;
	private final ModelKnight<T> MODEL_KNIGHT_FEET;

	private final ModelDrToast<T> MODEL_DRTOAST;

	public LayerTT(RenderLayerParent<T, M> entityRendererOwner)
	{
		super(entityRendererOwner);

		final ModelPart bakedKnight = Minecraft.getInstance().getEntityModels().bakeLayer(RenderSetup.KNIGHT_LAYER_LOCATION);
		this.MODEL_KNIGHT_HEAD = new ModelKnight<>(EquipmentSlot.HEAD, bakedKnight);
		this.MODEL_KNIGHT_CHEST = new ModelKnight<>(EquipmentSlot.CHEST, bakedKnight);
		this.MODEL_KNIGHT_LEGS = new ModelKnight<>(EquipmentSlot.LEGS, bakedKnight);
		this.MODEL_KNIGHT_FEET = new ModelKnight<>(EquipmentSlot.FEET, bakedKnight);

		final ModelPart bakedDrToast = Minecraft.getInstance().getEntityModels().bakeLayer(RenderSetup.DRTOAST_LAYER_LOCATION);
		this.MODEL_DRTOAST = new ModelDrToast<>(EquipmentSlot.HEAD, bakedDrToast);
	}

	@Override public void render(PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, T t, float v, float v1)
	{
		LayerTtType layerType = t.getRenderDataOrDefault(RenderSetup.LAYER_TYPE_CTX, LayerTtType.NONE);
		if (layerType == LayerTtType.NONE)
		{
			poseStack.pushPose();
			M parentModel = this.getParentModel();

			parentModel.copyPropertiesTo(MODEL_KNIGHT_HEAD);
			coloredCutoutModelCopyLayerRender(parentModel, RenderSetup.KNIGHT_LAYER_TEXTURE, poseStack, multiBufferSource, packedLight, t, -1);

			parentModel.copyPropertiesTo(MODEL_KNIGHT_CHEST);
			coloredCutoutModelCopyLayerRender(parentModel, RenderSetup.KNIGHT_LAYER_TEXTURE, poseStack, multiBufferSource, packedLight, t, -1);

			parentModel.copyPropertiesTo(MODEL_KNIGHT_LEGS);
			coloredCutoutModelCopyLayerRender(parentModel, RenderSetup.KNIGHT_LAYER_TEXTURE, poseStack, multiBufferSource, packedLight, t, -1);

			parentModel.copyPropertiesTo(MODEL_KNIGHT_FEET);
			coloredCutoutModelCopyLayerRender(parentModel, RenderSetup.KNIGHT_LAYER_TEXTURE, poseStack, multiBufferSource, packedLight, t, -1);

			poseStack.popPose();
		}
		else if (layerType == LayerTtType.TOAST)
		{
			poseStack.pushPose();

			M parentModel = this.getParentModel();

			parentModel.copyPropertiesTo(MODEL_DRTOAST);

			coloredCutoutModelCopyLayerRender(parentModel, RenderSetup.DRTOAST_LAYER_TEXTURE, poseStack, multiBufferSource, packedLight, t, -1);

			poseStack.popPose();
		}
	}
}
