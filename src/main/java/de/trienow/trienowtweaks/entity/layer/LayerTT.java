package de.trienow.trienowtweaks.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import de.trienow.trienowtweaks.entity.model.ModelDrToast;
import de.trienow.trienowtweaks.entity.model.ModelKnight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

/**
 * @param <T> The type of entity to
 * @param <M> the type parameter
 * @author (c) trienow 2022
 */
public class LayerTT<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M>
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

	@Override
	public void render(PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch)
	{
		final String entityName = pLivingEntity.getName().getString();
		if (RenderSetup.TRIENOW.equals(entityName))
		{
			pMatrixStack.pushPose();
			//setMatrix(pMatrixStack, -1f, 1.3f);
			//MODEL_HEAD.prepareMobModel(pLivingEntity, pLimbSwing, pLimbSwingAmount, pPartialTicks);
			//MODEL_HEAD.setupAnim(pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch);

			M parentModel = this.getParentModel();
			coloredCutoutModelCopyLayerRender(parentModel, MODEL_KNIGHT_HEAD, RenderSetup.KNIGHT_LAYER_TEXTURE, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch, pPartialTicks, 1, 1, 1);
			coloredCutoutModelCopyLayerRender(parentModel, MODEL_KNIGHT_CHEST, RenderSetup.KNIGHT_LAYER_TEXTURE, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch, pPartialTicks, 1, 1, 1);
			coloredCutoutModelCopyLayerRender(parentModel, MODEL_KNIGHT_LEGS, RenderSetup.KNIGHT_LAYER_TEXTURE, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch, pPartialTicks, 1, 1, 1);
			coloredCutoutModelCopyLayerRender(parentModel, MODEL_KNIGHT_FEET, RenderSetup.KNIGHT_LAYER_TEXTURE, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch, pPartialTicks, 1, 1, 1);
			//renderColoredCutoutModel(MODEL_HEAD, TEXTURE, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, 1, 1, 1);
			pMatrixStack.popPose();
		}
		else if (RenderSetup.TOASTY.equals(entityName))
		{
			pMatrixStack.pushPose();

			M parentModel = this.getParentModel();
			coloredCutoutModelCopyLayerRender(parentModel, MODEL_DRTOAST, RenderSetup.DRTOAST_LAYER_TEXTURE, pMatrixStack, pBuffer, pPackedLight, pLivingEntity, pLimbSwing, pLimbSwingAmount, pAgeInTicks, pNetHeadYaw, pHeadPitch, pPartialTicks, 1, 1, 1);

			pMatrixStack.popPose();
		}
	}

	private static void setMatrix(PoseStack pMatrixStack, float yTranslation, float scaleFactor)
	{
		pMatrixStack.translate(0f, yTranslation, 0f);
		pMatrixStack.scale(scaleFactor, scaleFactor, scaleFactor);
	}

}
