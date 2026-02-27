package de.trienow.trienowtweaks.entity.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.client.model.geom.PartNames.*;

/**
 * @author (c) trienow 2016 - 2022
 */
public class ModelKnight<T extends HumanoidRenderState> extends HumanoidModel<T>
{
	public static final int TEXTURE_WIDTH = 128;
	public static final int TEXTURE_HEIGHT = 64;

	// The Part Names must be the same names as HumanoidModel uses (except for boots, as they don't exist). That's why we design them here
	private static final String BOOT_L = "bootL";
	private static final String BOOT_R = "bootR";

	public ModelKnight(@Nullable EquipmentSlot modelType, ModelPart part)
	{
		super(part);
	}

	public static LayerDefinition createLayer()
	{
		return LayerDefinition.create(createMesh(null), TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	private static MeshDefinition createMesh(@Nullable EquipmentSlot slot)
	{
		MeshDefinition meshDef = HumanoidModel.createMesh(CubeDeformation.NONE, 0F);
		PartDefinition partDef = meshDef.getRoot();

		PartDefinition headInner = new BoxBuilder()
				.setTextureOffset(96, 0)
				.setBoxOffset(-4.0F, -8.0F, -4.0F)
				.setBox(8, 8, 8)
				.setRotationPoint(0.0F, 0.0F, 0.0F)
				.build(partDef, HEAD);

		PartDefinition bodyInner = new BoxBuilder()
				.setTextureOffset(104, 0)
				.setBoxOffset(-4.0F, 0.0F, -2.0F)
				.setBox(8, 12, 4)
				.setRotationPoint(0.0F, 0.0F, 0.0F)
				.build(partDef, BODY);

		PartDefinition armLInner = new BoxBuilder()
				.setTextureOffset(112, 0)
				.setBoxOffset(-1.0F, -2.0F, -2.0F)
				.setBox(4, 12, 4)
				.setRotationPoint(5.0F, 2.0F, 0.0F)
				.build(partDef, LEFT_ARM);
		PartDefinition armRInner = new BoxBuilder()
				.setTextureOffset(112, 0)
				.setBoxOffset(-3.0F, -2.0F, -2.0F)
				.setBox(4, 12, 4)
				.setRotationPoint(-5.0F, 2.0F, 0.0F)
				.build(partDef, RIGHT_ARM);

		PartDefinition legLInner = new BoxBuilder()
				.setTextureOffset(112, 0)
				.setBoxOffset(-2.0F, 0.0F, -2.0F)
				.setBox(4, 12, 4)
				.setRotationPoint(1.9F, 12.0F, 0.0F)
				.build(partDef, LEFT_LEG);
		PartDefinition legRInner = new BoxBuilder()
				.setTextureOffset(112, 0)
				.setBoxOffset(-2.0F, 0.0F, -2.0F)
				.setBox(4, 12, 4)
				.setRotationPoint(-1.9F, 12.0F, 0.0F)
				.build(partDef, RIGHT_LEG);

		if (slot == null || slot == EquipmentSlot.HEAD)
		{
			partDef.getChild(HEAD).clearChild(HAT);

			new BoxBuilder()
					.setTextureOffset(96, 0)
					.setBoxOffset(-4.0F, -8.0F, -4.0F)
					.setBox(8, 8, 8)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(headInner, "headOuter");

			new BoxBuilder()
					.setTextureOffset(31, 0)
					.setBoxOffset(-5.1F, -8.6F, 4.0F)
					.setBox(10, 9, 1)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(headInner, "helmetBack");

			new BoxBuilder()
					.setTextureOffset(0, 20)
					.setBoxOffset(-5.1F, -8.6F, -5.3F)
					.setBox(10, 3, 1)
					.setRotationPoint(0.0F, 0.0F, -0.0F)
					.build(headInner, "helmetForehead");
			new BoxBuilder()
					.setTextureOffset(2, 1)
					.setBoxOffset(-1.0F, -5.7F, -5.8F)
					.setBox(2, 2, 1)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(-0.091106186954104F, 0.0F, 0.0F)
					.build(headInner, "helmetNose");

			new BoxBuilder()
					.setTextureOffset(0, 0)
					.setBoxOffset(3.9F, -8.6F, -5.3F)
					.setBox(1, 9, 10)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(headInner, "helmetLeft");
			new BoxBuilder()
					.setTextureOffset(0, 0)
					.setBoxOffset(-5.1F, -8.6F, -5.3F)
					.setBox(1, 9, 10)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(headInner, "helmetRight");

			new BoxBuilder()
					.setTextureOffset(22, 7)
					.setBoxOffset(-1.5F, -11.7F, -3.0F)
					.setBox(3, 3, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(headInner, "helmetPlume0");
			new BoxBuilder()
					.setTextureOffset(34, 11)
					.setBoxOffset(-1.5F, -10.7F, 0.0F)
					.setBox(3, 6, 7)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(headInner, "helmetPlume1");
			new BoxBuilder()
					.setTextureOffset(12, 3)
					.setBoxOffset(-1.5F, -4.8F, 5.0F)
					.setBox(3, 3, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(headInner, "helmetPlume2");

			new BoxBuilder()
					.setTextureOffset(13, 15)
					.setBoxOffset(-1.0F, -9.03F, -5.0F)
					.setBox(5, 1, 10)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(0.0F, 0.0F, 0.09808750396208132F)
					.build(headInner, "helmetTopLeft");
			new BoxBuilder()
					.setTextureOffset(13, 15)
					.setBoxOffset(-4.19F, -9.06F, -5.0F)
					.setBox(5, 1, 10)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(0.0F, 0.0F, -0.10157816246606997F)
					.build(headInner, "helmetTopRight");

		}

		if (slot == null || slot == EquipmentSlot.CHEST)
		{
			new BoxBuilder()
					.setTextureOffset(104, 0)
					.setBoxOffset(-4.0F, 0.0F, -2.0F)
					.setBox(8, 12, 4)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bodyInner, "bodyOuter");

			new BoxBuilder()
					.setTextureOffset(112, 0)
					.setBoxOffset(-1.0F, -2.0F, -2.0F)
					.setBox(4, 12, 4)
					.setRotationPoint(5.0F, 2.0F, 0.0F)
					.build(bodyInner, "armLOuter");
			new BoxBuilder()
					.setTextureOffset(112, 0)
					.setBoxOffset(-3.0F, -2.0F, -2.0F)
					.setBox(4, 12, 4)
					.setRotationPoint(-5.0F, 2.0F, 0.0F)
					.build(bodyInner, "armROuter");

			PartDefinition bHFrontL = new BoxBuilder()
					.setTextureOffset(0, 50)
					.setBoxOffset(-4.2F, 11.1F, -2.6F)
					.setBox(9, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bodyInner, "bHFrontL");
			new BoxBuilder()
					.setTextureOffset(0, 45)
					.setBoxOffset(-4.2F, 11.1F, -0.4F)
					.setBox(9, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bHFrontL, "bHBackL");

			new BoxBuilder()
					.setTextureOffset(34, 48)
					.setBoxOffset(-4.5F, 0.5F, -2.5F)
					.setBox(9, 11, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bodyInner, "bPlate");

			PartDefinition bbFront = new BoxBuilder()
					.setTextureOffset(0, 37)
					.setBoxOffset(-2.1F, 1.5F, -2.6F)
					.setBox(10, 1, 1)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(0.0F, 0.0F, 0.7487462491055673F)
					.build(bodyInner, "bbFront");
			new BoxBuilder()
					.setTextureOffset(0, 37)
					.setBoxOffset(-2.1F, 1.5F, 1.6F)
					.setBox(10, 1, 1)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(0.0F, 0.0F, 0.013422170021240082F)
					.build(bbFront, "bbBack");
			new BoxBuilder()
					.setTextureOffset(0, 27)
					.setBoxOffset(-4.6F, 11.1F, -2.6F)
					.setBox(1, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bHFrontL, "bbHFrontR_1");
			new BoxBuilder()
					.setTextureOffset(0, 27)
					.setBoxOffset(-4.6F, 11.1F, -0.4F)
					.setBox(1, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bHFrontL, "bbHBackR_1");

			PartDefinition bbHFrontL = new BoxBuilder()
					.setTextureOffset(0, 40)
					.setBoxOffset(-4.2F, 6.2F, -2.6F)
					.setBox(9, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(0.0F, 0.0F, 0.005235987755982988F)
					.build(bodyInner, "bbHFrontL");
			new BoxBuilder()
					.setTextureOffset(0, 32)
					.setBoxOffset(-4.2F, 6.2F, -0.4F)
					.setBox(9, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bbHFrontL, "bbHBackL");
			new BoxBuilder()
					.setTextureOffset(0, 27)
					.setBoxOffset(-4.6F, 6.2F, -0.4F)
					.setBox(1, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bbHFrontL, "bbHBackR");
			new BoxBuilder()
					.setTextureOffset(0, 27)
					.setBoxOffset(-4.6F, 6.2F, -2.6F)
					.setBox(1, 1, 3)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bbHFrontL, "bbHFrontR");

			new BoxBuilder()
					.setTextureOffset(22, 27)
					.setBoxOffset(-3.5F, -0.5F, -3.0F)
					.setBox(7, 1, 6)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bodyInner, "neckGuardUpper");

			new BoxBuilder()
					.setTextureOffset(0, 55)
					.setBoxOffset(-1.5F, -2.4F, -2.49F)
					.setBox(5, 4, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(armLInner, "sPadLMain");

			new BoxBuilder()
					.setTextureOffset(20, 55)
					.setBoxOffset(2.8F, -1.9F, -2.49F)
					.setBox(1, 4, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(0.0F, 0.0F, -0.136659280431156F)
					.build(armLInner, "sPadLOuter");

			new BoxBuilder()
					.setTextureOffset(0, 55)
					.setBoxOffset(-3.4F, -2.4F, -2.49F)
					.setBox(5, 4, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(armRInner, "sPadRMain");

			new BoxBuilder()
					.setTextureOffset(20, 55)
					.setBoxOffset(-3.7F, -1.9F, -2.49F)
					.setBox(1, 4, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.setRotation(0.0F, 0.0F, 0.136659280431156F)
					.build(armRInner, "sPadROuter");

		}

		if (slot == null || slot == EquipmentSlot.LEGS)
		{

				/*SKIP THESE TWO PARTS
				new BoxBuilder()
						.setTextureOffset(112, 0)
						.setBoxOffset(-2.0F, 0.0F, -2.0F)
						.setBox(4, 12, 4)
						.setRotationPoint(1.9F, 12.0F, 0.0F)
						.build(legLInner, "legLOuter");
				new BoxBuilder()
						.setTextureOffset(112, 0)
						.setBoxOffset(-2.0F, 0.0F, -2.0F)
						.setBox(4, 12, 4)
						.setRotationPoint(-1.9F, 12.0F, 0.0F)
						.build(legRInner, "legROuter");*/

			new BoxBuilder()
					.setTextureOffset(44, 33)
					.setBoxOffset(-2.5F, 0.0F, -2.5F)
					.setBox(5, 9, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(legLInner, "legLProtecc");
			new BoxBuilder()
					.setTextureOffset(44, 33)
					.setBoxOffset(-2.5F, 0.0F, -2.49F)
					.setBox(5, 9, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(legRInner, "legRProtecc");

		}

		if (slot == null || slot == EquipmentSlot.FEET)
		{
			PartDefinition bootL = new BoxBuilder()
					.setTextureOffset(24, 39)
					.setBoxOffset(-2.5F, 9.0F, -2.5F)
					.setBox(5, 3, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(legLInner, BOOT_L);
			new BoxBuilder()
					.setTextureOffset(23, 2)
					.setBoxOffset(-1.0F, 8.0F, -2.6F)
					.setBox(2, 2, 1)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bootL, "bootLBuckle");
			PartDefinition bootR = new BoxBuilder()
					.setTextureOffset(24, 39)
					.setBoxOffset(-2.5F, 9.0F, -2.49F)
					.setBox(5, 3, 5)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(legRInner, BOOT_R);
			new BoxBuilder()
					.setTextureOffset(23, 2)
					.setBoxOffset(-1.0F, 8.0F, -2.59F)
					.setBox(2, 2, 1)
					.setRotationPoint(0.0F, 0.0F, 0.0F)
					.build(bootR, "bootRBuckle");
		}

		return meshDef;
	}
}
