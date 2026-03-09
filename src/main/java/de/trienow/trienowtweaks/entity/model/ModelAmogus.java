package de.trienow.trienowtweaks.entity.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;

import static net.minecraft.client.model.geom.PartNames.*;

public class ModelAmogus<T extends HumanoidRenderState> extends HumanoidModel<T>
{
	public ModelAmogus(ModelPart root)
	{
		super(root);
	}

	public static LayerDefinition createLayer()
	{
		return LayerDefinition.create(createMesh(), 64, 32);
	}

	private static MeshDefinition createMesh()
	{
		MeshDefinition meshdefinition = HumanoidModel.createMesh(CubeDeformation.NONE, 0F);
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild(HEAD, CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(1.0F))
				.texOffs(39, 1).addBox(-4.5F, -7.0F, -6.0F, 9.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild(BODY, CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(1.01F))
				.texOffs(42, 17).addBox(-4.0F, 2.0F, 3.0F, 8.0F, 9.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild(RIGHT_ARM, CubeListBuilder.create().texOffs(0, 16)
				.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild(LEFT_ARM, CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
				.mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild(RIGHT_LEG, CubeListBuilder.create().texOffs(0, 16)
				.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild(LEFT_LEG, CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(1.0F))
				.mirror(false), PartPose.offset(1.9F, 12.0F, 0.0F));

		return meshdefinition;
	}
}
