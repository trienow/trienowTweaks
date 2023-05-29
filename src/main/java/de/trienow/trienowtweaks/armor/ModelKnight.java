// Date: 14.05.2016 14:14:27

package de.trienow.trienowtweaks.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ModelKnight extends ModelBiped
{
	private final ModelRenderer headInner;
	public final ModelRenderer bodyInner;
	private final ModelRenderer armLInner;
	private final ModelRenderer armRInner;
	private final ModelRenderer legLInner;
	private final ModelRenderer legRInner;

	public ModelKnight(EntityEquipmentSlot slot)
	{
		this.textureWidth = 128;
		this.textureHeight = 64;

		this.headInner = new ModelRenderer(this, 96, 0);
		this.headInner.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.headInner.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.bodyInner = new ModelRenderer(this, 104, 0);
		this.bodyInner.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.bodyInner.setRotationPoint(0.0F, 0.0F, 0.0F);

		this.armLInner = new ModelRenderer(this, 112, 0);
		this.armLInner.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.armLInner.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.armRInner = new ModelRenderer(this, 112, 0);
		this.armRInner.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.armRInner.setRotationPoint(-5.0F, 2.0F, 0.0F);

		this.legLInner = new ModelRenderer(this, 112, 0);
		this.legLInner.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.legLInner.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.legRInner = new ModelRenderer(this, 112, 0);
		this.legRInner.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.legRInner.setRotationPoint(-1.9F, 12.0F, 0.0F);

		if (slot == EntityEquipmentSlot.HEAD)
		{
			ModelRenderer headOuter = new ModelRenderer(this, 96, 0);
			headOuter.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
			headOuter.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer helmetBack = new ModelRenderer(this, 31, 0);
			helmetBack.addBox(-5.1F, -8.6F, 4.0F, 10, 9, 1, 0.0F);
			helmetBack.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer helmetForehead = new ModelRenderer(this, 0, 20);
			helmetForehead.addBox(-5.1F, -8.6F, -5.3F, 10, 3, 1, 0.0F);
			helmetForehead.setRotationPoint(0.0F, 0.0F, -0.0F);
			ModelRenderer helmetNose = new ModelRenderer(this, 2, 1);
			helmetNose.addBox(-1.0F, -5.7F, -5.8F, 2, 2, 1, 0.0F);
			helmetNose.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer helmetLeft = new ModelRenderer(this, 0, 0);
			helmetLeft.addBox(3.9F, -8.6F, -5.3F, 1, 9, 10, 0.0F);
			helmetLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer helmetRight = new ModelRenderer(this, 0, 0);
			helmetRight.addBox(-5.1F, -8.6F, -5.3F, 1, 9, 10, 0.0F);
			helmetRight.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer helmetPlume0 = new ModelRenderer(this, 22, 7);
			helmetPlume0.addBox(-1.5F, -11.7F, -3.0F, 3, 3, 3, 0.0F);
			helmetPlume0.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer helmetPlume1 = new ModelRenderer(this, 34, 11);
			helmetPlume1.addBox(-1.5F, -10.7F, 0.0F, 3, 6, 7, 0.0F);
			helmetPlume1.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer helmetPlume2 = new ModelRenderer(this, 12, 3);
			helmetPlume2.addBox(-1.5F, -4.8F, 5.0F, 3, 3, 3, 0.0F);
			helmetPlume2.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer helmetTopLeft = new ModelRenderer(this, 13, 15);
			helmetTopLeft.addBox(-1.0F, -9.03F, -5.0F, 5, 1, 10, 0.0F);
			helmetTopLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer helmetTopRight = new ModelRenderer(this, 13, 15);
			helmetTopRight.addBox(-4.19F, -9.06F, -5.0F, 5, 1, 10, 0.0F);
			helmetTopRight.setRotationPoint(0.0F, 0.0F, 0.0F);

			this.setRotation(helmetNose, -0.091106186954104F, 0.0F, 0.0F);
			this.setRotation(helmetTopLeft, 0.0F, 0.0F, 0.09808750396208132F);
			this.setRotation(helmetTopRight, 0.0F, 0.0F, -0.10157816246606997F);

			this.headInner.addChild(helmetBack);
			this.headInner.addChild(helmetForehead);
			this.headInner.addChild(helmetLeft);
			this.headInner.addChild(helmetNose);
			this.headInner.addChild(helmetPlume0);
			this.headInner.addChild(helmetPlume1);
			this.headInner.addChild(helmetPlume2);
			this.headInner.addChild(helmetRight);
			this.headInner.addChild(helmetTopLeft);
			this.headInner.addChild(helmetTopRight);
		}

		if (slot == EntityEquipmentSlot.CHEST)
		{
			ModelRenderer bodyOuter = new ModelRenderer(this, 104, 0);
			bodyOuter.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
			bodyOuter.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer armLOuter = new ModelRenderer(this, 112, 0);
			armLOuter.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
			armLOuter.setRotationPoint(5.0F, 2.0F, 0.0F);
			ModelRenderer armROuter = new ModelRenderer(this, 112, 0);
			armROuter.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
			armROuter.setRotationPoint(-5.0F, 2.0F, 0.0F);

			ModelRenderer bHBackL = new ModelRenderer(this, 0, 45);
			bHBackL.addBox(-4.2F, 11.1F, -0.4F, 9, 1, 3, 0.0F);
			bHBackL.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bHFrontL = new ModelRenderer(this, 0, 50);
			bHFrontL.addBox(-4.2F, 11.1F, -2.6F, 9, 1, 3, 0.0F);
			bHFrontL.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer bPlate = new ModelRenderer(this, 34, 48);
			bPlate.addBox(-4.5F, 0.5F, -2.5F, 9, 11, 5, 0.0F);
			bPlate.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer bbBack = new ModelRenderer(this, 0, 37);
			bbBack.addBox(-2.1F, 1.5F, 1.6F, 10, 1, 1, 0.0F);
			bbBack.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bbFront = new ModelRenderer(this, 0, 37);
			bbFront.addBox(-2.1F, 1.5F, -2.6F, 10, 1, 1, 0.0F);
			bbFront.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer bbHBackL = new ModelRenderer(this, 0, 32);
			bbHBackL.addBox(-4.2F, 6.2F, -0.4F, 9, 1, 3, 0.0F);
			bbHBackL.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bbHBackR = new ModelRenderer(this, 0, 27);
			bbHBackR.addBox(-4.6F, 6.2F, -0.4F, 1, 1, 3, 0.0F);
			bbHBackR.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer bbHFrontR_1 = new ModelRenderer(this, 0, 27);
			bbHFrontR_1.addBox(-4.6F, 11.1F, -2.6F, 1, 1, 3, 0.0F);
			bbHFrontR_1.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bbHBackR_1 = new ModelRenderer(this, 0, 27);
			bbHBackR_1.addBox(-4.6F, 11.1F, -0.4F, 1, 1, 3, 0.0F);
			bbHBackR_1.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer bbHFrontL = new ModelRenderer(this, 0, 40);
			bbHFrontL.addBox(-4.2F, 6.2F, -2.6F, 9, 1, 3, 0.0F);
			bbHFrontL.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bbHFrontR = new ModelRenderer(this, 0, 27);
			bbHFrontR.addBox(-4.6F, 6.2F, -2.6F, 1, 1, 3, 0.0F);
			bbHFrontR.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer neckGuardUpper = new ModelRenderer(this, 22, 27);
			neckGuardUpper.addBox(-3.5F, -0.5F, -3.0F, 7, 1, 6, 0.0F);
			neckGuardUpper.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer sPadLMain = new ModelRenderer(this, 0, 55);
			sPadLMain.addBox(-1.5F, -2.4F, -2.49F, 5, 4, 5, 0.0F);
			sPadLMain.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer sPadLOuter = new ModelRenderer(this, 20, 55);
			sPadLOuter.addBox(2.8F, -1.9F, -2.49F, 1, 4, 5, 0.0F);
			sPadLOuter.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer sPadRMain = new ModelRenderer(this, 0, 55);
			sPadRMain.addBox(-3.4F, -2.4F, -2.49F, 5, 4, 5, 0.0F);
			sPadRMain.setRotationPoint(0.0F, 0.0F, 0.0F);

			ModelRenderer sPadROuter = new ModelRenderer(this, 20, 55);
			sPadROuter.addBox(-3.7F, -1.9F, -2.49F, 1, 4, 5, 0.0F);
			sPadROuter.setRotationPoint(0.0F, 0.0F, 0.0F);

			this.setRotation(bbBack, 0.0F, 0.0F, 0.013422170021240082F);
			this.setRotation(bbFront, 0.0F, 0.0F, 0.7487462491055673F);
			this.setRotation(bbHFrontL, 0.0F, 0.0F, 0.005235987755982988F);

			this.setRotation(sPadLOuter, 0.0F, 0.0F, -0.136659280431156F);
			this.setRotation(sPadROuter, 0.0F, 0.0F, 0.136659280431156F);

			this.armLInner.addChild(sPadLMain);
			this.armLInner.addChild(sPadLOuter);
			this.armRInner.addChild(sPadRMain);
			this.armRInner.addChild(sPadROuter);

			bHFrontL.addChild(bHBackL);
			bHFrontL.addChild(bbHBackR_1);
			bHFrontL.addChild(bbHFrontR_1);
			bbFront.addChild(bbBack);
			bbHFrontL.addChild(bbHBackL);
			bbHFrontL.addChild(bbHBackR);
			bbHFrontL.addChild(bbHFrontR);
			this.bodyInner.addChild(bHFrontL);
			this.bodyInner.addChild(bPlate);
			this.bodyInner.addChild(bbFront);
			this.bodyInner.addChild(bbHFrontL);
			this.bodyInner.addChild(neckGuardUpper);

		}

		if (slot == EntityEquipmentSlot.LEGS)
		{
			ModelRenderer legLOuter = new ModelRenderer(this, 112, 0);
			legLOuter.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
			legLOuter.setRotationPoint(1.9F, 12.0F, 0.0F);
			ModelRenderer legROuter = new ModelRenderer(this, 112, 0);
			legROuter.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
			legROuter.setRotationPoint(-1.9F, 12.0F, 0.0F);

			ModelRenderer legLProtecc = new ModelRenderer(this, 44, 33);
			legLProtecc.addBox(-2.5F, 0.0F, -2.5F, 5, 9, 5, 0.0F);
			legLProtecc.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer legRProtecc = new ModelRenderer(this, 44, 33);
			legRProtecc.addBox(-2.5F, 0.0F, -2.49F, 5, 9, 5, 0.0F);
			legRProtecc.setRotationPoint(0.0F, 0.0F, 0.0F);

			this.legLInner.addChild(legLProtecc);
			this.legRInner.addChild(legRProtecc);
		}

		if (slot == EntityEquipmentSlot.FEET)
		{
			ModelRenderer bootL = new ModelRenderer(this, 24, 39);
			bootL.addBox(-2.5F, 9.0F, -2.5F, 5, 3, 5, 0.0F);
			bootL.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bootLBuckle = new ModelRenderer(this, 23, 2);
			bootLBuckle.addBox(-1.0F, 8.0F, -2.6F, 2, 2, 1, 0.0F);
			bootLBuckle.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bootR = new ModelRenderer(this, 24, 39);
			bootR.addBox(-2.5F, 9.0F, -2.49F, 5, 3, 5, 0.0F);
			bootR.setRotationPoint(0.0F, 0.0F, 0.0F);
			ModelRenderer bootRBuckle = new ModelRenderer(this, 23, 2);
			bootRBuckle.addBox(-1.0F, 8.0F, -2.59F, 2, 2, 1, 0.0F);
			bootRBuckle.setRotationPoint(0.0F, 0.0F, 0.0F);

			bootL.addChild(bootLBuckle);
			bootR.addChild(bootRBuckle);
			this.legLInner.addChild(bootL);
			this.legRInner.addChild(bootR);
		}
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		if (entity instanceof EntityArmorStand)
		{
			f = 0;
			f1 = 0;
			f2 = 0;
			f3 = 0;
			f4 = 43;
		}

		bipedHead.showModel = true;
		bipedHeadwear.showModel = true;

		bipedHead = headInner;
		bipedBody = bodyInner;
		bipedLeftArm = armLInner;
		bipedRightArm = armRInner;
		bipedLeftLeg = legLInner;
		bipedRightLeg = legRInner;

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	@SuppressWarnings("SameParameterValue")
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

}
