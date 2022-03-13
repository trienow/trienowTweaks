package de.trienow.trienowtweaks.entity.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.Nullable;

/**
 * @author (c) trienow 2016 - 2022
 */
public class ModelDrToast<T extends LivingEntity> extends HumanoidModel<T>
{
	private static final Iterable<ModelPart> EMPTY_LIST = ImmutableList.of();
	private static final int TEXTURE_WIDTH = 128;
	private static final int TEXTURE_HEIGHT = 64;

	private static final String HEAD = "head";
	private final Iterable<ModelPart> headParts;

	public ModelDrToast(EquipmentSlot slot, ModelPart part)
	{
		super(part);
		if (slot == EquipmentSlot.HEAD)
		{
			ModelPart head = part.getChild(HEAD);
			headParts = ImmutableList.of(head);
		}
		else
		{
			headParts = EMPTY_LIST;
		}
	}

	@Override
	protected Iterable<ModelPart> headParts()
	{
		return headParts; //Just for performance
	}

	@Override
	protected Iterable<ModelPart> bodyParts()
	{
		return EMPTY_LIST; //Just for performance
	}

	public static ModelDrToast<LivingEntity> makeModel(EquipmentSlot slot)
	{
		MeshDefinition meshDef = createMesh(slot);
		ModelPart modelPart = meshDef.getRoot().bake(TEXTURE_WIDTH, TEXTURE_HEIGHT);
		return new ModelDrToast<>(slot, modelPart);
	}

	public static LayerDefinition createLayer()
	{
		return LayerDefinition.create(createMesh(null), TEXTURE_WIDTH, TEXTURE_HEIGHT);
	}

	private static MeshDefinition createMesh(@Nullable EquipmentSlot slot)
	{
		final int texMainX = 8;
		final int texBorderX = 60;

		final MeshDefinition meshDef = HumanoidModel.createMesh(CubeDeformation.NONE, 0.0F);
		final PartDefinition partDef = meshDef.getRoot();

		if (slot == null || slot == EquipmentSlot.HEAD)
		{
			PartDefinition headInner = new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(5F, 1F, -4F)
					.setBox(1, 1, 1)
					.build(partDef, HEAD);

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(0F, 1F, -3F)
					.setBox(2, 1, 1)
					.build(headInner, "layer03Shape21");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-6F, 2F, -4F)
					.setBox(2, 1, 1)
					.build(headInner, "layer03Shape22");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(7F, 1F, -1F)
					.setBox(1, 1, 1)
					.build(headInner, "layer03Shape23");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-6F, 0F, -2F)
					.setBox(1, 2, 1)
					.build(headInner, "layer03Shape24");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-7F, -4F, -3F)
					.setBox(1, 2, 1)
					.build(headInner, "layer03Shape25");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-4F, 3F, -4F)
					.setBox(4, 1, 1)
					.build(headInner, "layer03Shape16");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(6F, 0F, -4F)
					.setBox(1, 1, 2)
					.build(headInner, "layer03Shape27");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(6F, 1F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer03Shape28");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-5F, 0F, -3F)
					.setBox(1, 2, 1)
					.build(headInner, "layer03Shape29");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-7F, -1F, -4F)
					.setBox(2, 3, 1)
					.build(headInner, "layer03Shape210");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-7F, -3F, -4F)
					.setBox(1, 2, 1)
					.build(headInner, "layer02Shape211");

			new BoxBuilder()
					.setTextureOffset(texBorderX, 20)
					.setBoxOffset(6F, -5F, -5F)
					.setBox(1, 8, 1)
					.build(headInner, "layer02Shape112");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, 0F, -5F)
					.setBox(12, 3, 1)
					.build(headInner, "layer02Shape113");

			new BoxBuilder()
					.setTextureOffset(texBorderX, 20)
					.setBoxOffset(-7F, -5F, -5F)
					.setBox(1, 8, 1)
					.build(headInner, "layer02Shape114");

			new BoxBuilder()
					.setTextureOffset(texBorderX, 20)
					.setBoxOffset(6F, -5F, -1F)
					.setBox(1, 8, 1)
					.build(headInner, "layer02Shape115");

			new BoxBuilder()
					.setTextureOffset(texBorderX, 20)
					.setBoxOffset(-7F, -5F, -1F)
					.setBox(1, 8, 1)
					.build(headInner, "layer02Shape116");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -5F, -1F)
					.setBox(12, 8, 1)
					.build(headInner, "layer02Shape117");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(0F, 2F, -4F)
					.setBox(5, 1, 1)
					.build(headInner, "layer02Shape118");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-6F, 2F, -2F)
					.setBox(2, 1, 1)
					.build(headInner, "layer02Shape119");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(-2F, 2F, -2F)
					.setBox(2, 1, 1)
					.build(headInner, "layer02Shape120");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-4F, 2F, -3F)
					.setBox(4, 1, 1)
					.build(headInner, "layer02Shape121");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(0F, 2F, -2F)
					.setBox(6, 1, 1)
					.build(headInner, "layer02Shape122");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(2F, 2F, -3F)
					.setBox(1, 1, 1)
					.build(headInner, "layer02Shape123");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(5F, 2F, -3F)
					.setBox(2, 1, 1)
					.build(headInner, "layer04Shape124");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(6F, 1F, -3F)
					.setBox(1, 1, 1)
					.build(headInner, "layer04Shape225");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(5F, 0F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer01Shape226");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-7F, 3F, -5F)
					.setBox(14, 1, 1)
					.build(headInner, "layer01Shape127");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(3F, 3F, -2F)
					.setBox(3, 1, 1)
					.build(headInner, "layer01Shape128");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-7F, 3F, -1F)
					.setBox(14, 1, 1)
					.build(headInner, "layer01Shape129");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-4F, 3F, -2F)
					.setBox(2, 1, 1)
					.build(headInner, "layer05Shape130");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(2F, -2F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer05Shape231");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-6F, -2F, -3F)
					.setBox(1, 2, 1)
					.build(headInner, "layer05Shape232");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-7F, -2F, -2F)
					.setBox(1, 2, 1)
					.build(headInner, "layer05Shape233");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(5F, -1F, -3F)
					.setBox(1, 1, 1)
					.build(headInner, "layer05Shape234");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(6F, -5F, -4F)
					.setBox(2, 2, 1)
					.build(headInner, "layer05Shape235");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -1F, -5F)
					.setBox(5, 1, 1)
					.build(headInner, "layer05Shape236");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(2F, -1F, -5F)
					.setBox(4, 1, 1)
					.build(headInner, "layer05Shape237");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(7F, -2F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer05Shape238");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(6F, -1F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer05Shape239");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-1F, -1F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer05Shape240");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-1F, -2F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer05Shape241");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(6F, -3F, -4F)
					.setBox(2, 3, 1)
					.build(headInner, "layer06Shape242");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-8F, -2F, -1F)
					.setBox(1, 1, 1)
					.build(headInner, "layer06Shape243");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(6F, -3F, -3F)
					.setBox(1, 2, 1)
					.build(headInner, "layer06Shape244");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-4F, -2F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer06Shape245");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-2F, -2F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer06Shape246");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(3F, -2F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer06Shape247");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-3F, -2F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer06Shape248");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -2F, -5F)
					.setBox(2, 1, 1)
					.build(headInner, "layer07Shape249");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(-7F, -3F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer07Shape250");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-2F, -3F, -5F)
					.setBox(5, 1, 1)
					.build(headInner, "layer07Shape251");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -3F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer07Shape252");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(4F, -3F, -5F)
					.setBox(2, 1, 1)
					.build(headInner, "layer07Shape253");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(3F, -3F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer07Shape254");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-3F, -3F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer07Shape255");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(6F, -5F, -2F)
					.setBox(2, 3, 1)
					.build(headInner, "layer08Shape256");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -4F, -5F)
					.setBox(12, 1, 1)
					.build(headInner, "layer08Shape257");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-6F, -4F, -4F)
					.setBox(1, 1, 1)
					.build(headInner, "layer08Shape258");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(6F, -4F, -3F)
					.setBox(2, 1, 1)
					.build(headInner, "layer08Shape259");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(-8F, -5F, -2F)
					.setBox(2, 2, 1)
					.build(headInner, "layer09Shape260");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(6F, -6F, -2F)
					.setBox(2, 1, 2)
					.build(headInner, "layer09Shape261");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-6F, -5F, -3F)
					.setBox(12, 1, 1)
					.build(headInner, "layer09Shape262");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-1F, -7F, -5F)
					.setBox(3, 3, 1)
					.build(headInner, "layer09Shape263");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(5F, -5F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer09Shape264");

			new BoxBuilder()
					.setTextureOffset(texMainX, 12)
					.setBoxOffset(2F, -5F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer09Shape265");

			new BoxBuilder()
					.setTextureOffset(texMainX, 12)
					.setBoxOffset(-4F, -5F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer09Shape266");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-7F, -5F, -4F)
					.setBox(1, 1, 1)
					.build(headInner, "layer09Shape267");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(-6F, -6F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer09Shape268");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-6F, -6F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer09Shape269");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(6F, -8F, -4F)
					.setBox(1, 3, 1)
					.build(headInner, "layer09Shape270");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -5F, -5F)
					.setBox(2, 1, 1)
					.build(headInner, "layer10Shape271");

			new BoxBuilder()
					.setTextureOffset(texBorderX, 12)
					.setBoxOffset(2F, -6F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer10Shape272");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-7F, -8F, -5F)
					.setBox(1, 2, 1)
					.build(headInner, "layer10Shape273");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-6F, -6F, -1F)
					.setBox(1, 1, 1)
					.build(headInner, "layer10Shape274");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-5F, -6F, -1F)
					.setBox(10, 1, 1)
					.build(headInner, "layer10Shape275");

			new BoxBuilder()
					.setTextureOffset(texMainX, 12)
					.setBoxOffset(3F, -6F, -5F)
					.setBox(2, 1, 1)
					.build(headInner, "layer10Shape276");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-5F, -6F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer10Shape277");

			new BoxBuilder()
					.setTextureOffset(texBorderX, 12)
					.setBoxOffset(-2F, -6F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer10Shape278");

			new BoxBuilder()
					.setTextureOffset(texMainX, 12)
					.setBoxOffset(-4F, -6F, -5F)
					.setBox(2, 1, 1)
					.build(headInner, "layer10Shape279");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-5F, -6F, -3F)
					.setBox(12, 1, 1)
					.build(headInner, "layer10Shape280");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-8F, -6F, -4F)
					.setBox(3, 1, 1)
					.build(headInner, "layer10Shape281");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(5F, -6F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer10Shape282");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(5F, -6F, -1F)
					.setBox(1, 1, 1)
					.build(headInner, "layer10Shape283");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(6F, -8F, -1F)
					.setBox(1, 2, 1)
					.build(headInner, "layer10Shape284");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(6F, -8F, -5F)
					.setBox(1, 2, 1)
					.build(headInner, "layer11Shape285");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-7F, -8F, -1F)
					.setBox(1, 2, 1)
					.build(headInner, "layer11Shape286");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -7F, -5F)
					.setBox(2, 1, 1)
					.build(headInner, "layer11Shape287");

			new BoxBuilder()
					.setTextureOffset(texMainX, 12)
					.setBoxOffset(2F, -7F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer11Shape288");

			new BoxBuilder()
					.setTextureOffset(texMainX, 12)
					.setBoxOffset(-4F, -7F, -5F)
					.setBox(3, 1, 1)
					.build(headInner, "layer11Shape289");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(5F, -7F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer11Shape290");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -8F, -1F)
					.setBox(12, 2, 1)
					.build(headInner, "layer11Shape291");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-6F, -7F, -4F)
					.setBox(2, 1, 3)
					.build(headInner, "layer11Shape292");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(6F, -7F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer11Shape293");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-3F, -9F, -3F)
					.setBox(9, 3, 1)
					.build(headInner, "layer12Shape294");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-6F, -8F, -5F)
					.setBox(12, 1, 1)
					.build(headInner, "layer12Shape295");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(5F, -9F, -2F)
					.setBox(1, 2, 1)
					.build(headInner, "layer12Shape296");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-7F, -8F, -4F)
					.setBox(2, 1, 2)
					.build(headInner, "layer12Shape297");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-4F, -10F, -4F)
					.setBox(1, 3, 1)
					.build(headInner, "layer12Shape298");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(-3F, -8F, -2F)
					.setBox(2, 1, 1)
					.build(headInner, "layer12Shape299");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-6F, -9F, -2F)
					.setBox(3, 2, 1)
					.build(headInner, "layer12Shape2100");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-4F, -8F, -3F)
					.setBox(1, 1, 1)
					.build(headInner, "layer13Shape2101");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-5F, -9F, -5F)
					.setBox(10, 1, 1)
					.build(headInner, "layer13Shape2102");

			new BoxBuilder()
					.setTextureOffset(texMainX, 20)
					.setBoxOffset(-5F, -9F, -1F)
					.setBox(10, 1, 1)
					.build(headInner, "layer13Shape2103");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(5F, -9F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer13Shape2104");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-6F, -9F, -5F)
					.setBox(1, 1, 1)
					.build(headInner, "layer13Shape2105");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-6F, -9F, -1F)
					.setBox(1, 1, 1)
					.build(headInner, "layer13Shape2106");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(5F, -9F, -1F)
					.setBox(1, 1, 1)
					.build(headInner, "layer13Shape2107");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(-1F, -9F, -2F)
					.setBox(1, 1, 1)
					.build(headInner, "layer13Shape2108");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(3F, -9F, -4F)
					.setBox(1, 1, 1)
					.build(headInner, "layer13Shape2109");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(5F, -9F, -4F)
					.setBox(1, 1, 1)
					.build(headInner, "layer14Shape2110");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-5F, -10F, -1F)
					.setBox(10, 1, 1)
					.build(headInner, "layer14Shape2111");

			new BoxBuilder()
					.setTextureOffset(texMainX, 0)
					.setBoxOffset(-5F, -10F, -5F)
					.setBox(10, 1, 1)
					.build(headInner, "layer14Shape2112");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(5F, -11F, -1F)
					.setBox(1, 2, 1)
					.build(headInner, "layer14Shape2113");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(-2F, -10F, -3F)
					.setBox(3, 1, 1)
					.build(headInner, "layer14Shape2114");

			new BoxBuilder()
					.setTextureOffset(texMainX, 8)
					.setBoxOffset(0F, -11F, -2F)
					.setBox(5, 2, 1)
					.build(headInner, "layer14Shape2115");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(3F, -10F, -3F)
					.setBox(2, 1, 1)
					.build(headInner, "layer14Shape2116");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(1F, -10F, -4F)
					.setBox(2, 1, 1)
					.build(headInner, "layer14Shape2117");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(4F, -10F, -4F)
					.setBox(1, 1, 1)
					.build(headInner, "layer15Shape2118");

			new BoxBuilder()
					.setTextureOffset(texMainX, 16)
					.setBoxOffset(0F, -11F, -4F)
					.setBox(1, 1, 2)
					.build(headInner, "layer15Shape2119");

			new BoxBuilder()
					.setTextureOffset(texMainX, 4)
					.setBoxOffset(-3F, -11F, -4F)
					.setBox(3, 1, 1)
					.build(headInner, "layer15Shape2120");
		}

		return meshDef;
	}
}
