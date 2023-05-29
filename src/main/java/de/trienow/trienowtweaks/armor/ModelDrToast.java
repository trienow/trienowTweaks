package de.trienow.trienowtweaks.armor;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ModelDrToast extends ModelBiped
{
	// Layer03
	final ModelRenderer Layer03Shape20;
	final ModelRenderer Layer03Shape21;
	final ModelRenderer Layer03Shape22;
	final ModelRenderer Layer03Shape23;
	final ModelRenderer Layer03Shape24;
	final ModelRenderer Layer03Shape25;
	final ModelRenderer Layer03Shape16;
	final ModelRenderer Layer03Shape27;
	final ModelRenderer Layer03Shape28;
	final ModelRenderer Layer03Shape29;
	final ModelRenderer Layer03Shape210;

	// Layer02
	final ModelRenderer Layer02Shape211;
	final ModelRenderer Layer02Shape112;
	final ModelRenderer Layer02Shape113;
	final ModelRenderer Layer02Shape114;
	final ModelRenderer Layer02Shape115;
	final ModelRenderer Layer02Shape116;
	final ModelRenderer Layer02Shape117;
	final ModelRenderer Layer02Shape118;
	final ModelRenderer Layer02Shape119;
	final ModelRenderer Layer02Shape120;
	final ModelRenderer Layer02Shape121;
	final ModelRenderer Layer02Shape122;
	final ModelRenderer Layer02Shape123;

	// Layer04
	final ModelRenderer Layer04Shape124;
	final ModelRenderer Layer04Shape225;

	// Layer01
	final ModelRenderer Layer01Shape226;
	final ModelRenderer Layer01Shape127;
	final ModelRenderer Layer01Shape128;
	final ModelRenderer Layer01Shape129;

	// Layer05
	final ModelRenderer Layer05Shape130;
	final ModelRenderer Layer05Shape231;
	final ModelRenderer Layer05Shape232;
	final ModelRenderer Layer05Shape233;
	final ModelRenderer Layer05Shape234;
	final ModelRenderer Layer05Shape235;
	final ModelRenderer Layer05Shape236;
	final ModelRenderer Layer05Shape237;
	final ModelRenderer Layer05Shape238;
	final ModelRenderer Layer05Shape239;
	final ModelRenderer Layer05Shape240;
	final ModelRenderer Layer05Shape241;

	// Layer06
	final ModelRenderer Layer06Shape242;
	final ModelRenderer Layer06Shape243;
	final ModelRenderer Layer06Shape244;
	final ModelRenderer Layer06Shape245;
	final ModelRenderer Layer06Shape246;
	final ModelRenderer Layer06Shape247;
	final ModelRenderer Layer06Shape248;

	// Layer07
	final ModelRenderer Layer07Shape249;
	final ModelRenderer Layer07Shape250;
	final ModelRenderer Layer07Shape251;
	final ModelRenderer Layer07Shape252;
	final ModelRenderer Layer07Shape253;
	final ModelRenderer Layer07Shape254;
	final ModelRenderer Layer07Shape255;

	// Layer08
	final ModelRenderer Layer08Shape256;
	final ModelRenderer Layer08Shape257;
	final ModelRenderer Layer08Shape258;
	final ModelRenderer Layer08Shape259;

	// Layer09
	final ModelRenderer Layer09Shape260;
	final ModelRenderer Layer09Shape261;
	final ModelRenderer Layer09Shape262;
	final ModelRenderer Layer09Shape263;
	final ModelRenderer Layer09Shape264;
	final ModelRenderer Layer09Shape265;
	final ModelRenderer Layer09Shape266;
	final ModelRenderer Layer09Shape267;
	final ModelRenderer Layer09Shape268;
	final ModelRenderer Layer09Shape269;
	final ModelRenderer Layer09Shape270;

	// Layer10
	final ModelRenderer Layer10Shape271;
	final ModelRenderer Layer10Shape272;
	final ModelRenderer Layer10Shape273;
	final ModelRenderer Layer10Shape274;
	final ModelRenderer Layer10Shape275;
	final ModelRenderer Layer10Shape276;
	final ModelRenderer Layer10Shape277;
	final ModelRenderer Layer10Shape278;
	final ModelRenderer Layer10Shape279;
	final ModelRenderer Layer10Shape280;
	final ModelRenderer Layer10Shape281;
	final ModelRenderer Layer10Shape282;
	final ModelRenderer Layer10Shape283;
	final ModelRenderer Layer10Shape284;

	// Layer11
	final ModelRenderer Layer11Shape285;
	final ModelRenderer Layer11Shape286;
	final ModelRenderer Layer11Shape287;
	final ModelRenderer Layer11Shape288;
	final ModelRenderer Layer11Shape289;
	final ModelRenderer Layer11Shape290;
	final ModelRenderer Layer11Shape291;
	final ModelRenderer Layer11Shape292;
	final ModelRenderer Layer11Shape293;

	// Layer12
	final ModelRenderer Layer12Shape294;
	final ModelRenderer Layer12Shape295;
	final ModelRenderer Layer12Shape296;
	final ModelRenderer Layer12Shape297;
	final ModelRenderer Layer12Shape298;
	final ModelRenderer Layer12Shape299;
	final ModelRenderer Layer12Shape2100;

	// Layer13
	final ModelRenderer Layer13Shape2101;
	final ModelRenderer Layer13Shape2102;
	final ModelRenderer Layer13Shape2103;
	final ModelRenderer Layer13Shape2104;
	final ModelRenderer Layer13Shape2105;
	final ModelRenderer Layer13Shape2106;
	final ModelRenderer Layer13Shape2107;
	final ModelRenderer Layer13Shape2108;
	final ModelRenderer Layer13Shape2109;

	// Layer14
	final ModelRenderer Layer14Shape2110;
	final ModelRenderer Layer14Shape2111;
	final ModelRenderer Layer14Shape2112;
	final ModelRenderer Layer14Shape2113;
	final ModelRenderer Layer14Shape2114;
	final ModelRenderer Layer14Shape2115;
	final ModelRenderer Layer14Shape2116;
	final ModelRenderer Layer14Shape2117;

	// Layer15
	final ModelRenderer Layer15Shape2118;
	final ModelRenderer Layer15Shape2119;
	final ModelRenderer Layer15Shape2120;

	final EntityEquipmentSlot slot;

	public ModelDrToast(EntityEquipmentSlot slot)
	{
		textureWidth = 128;
		textureHeight = 64;

		this.slot = slot;

		// Layer03
		Layer03Shape20 = new ModelRenderer(this, 56, 4);
		Layer03Shape20.addBox(5F, 1F, -4F, 1, 1, 1);
		Layer03Shape20.setRotationPoint(0F, 0F, 0F);
		Layer03Shape20.setTextureSize(128, 64);

		Layer03Shape21 = new ModelRenderer(this, 56, 16);
		Layer03Shape21.addBox(0F, 1F, -3F, 2, 1, 1);
		Layer03Shape21.setRotationPoint(0F, 0F, 0F);
		Layer03Shape21.setTextureSize(128, 64);

		Layer03Shape22 = new ModelRenderer(this, 56, 4);
		Layer03Shape22.addBox(-6F, 2F, -4F, 2, 1, 1);
		Layer03Shape22.setRotationPoint(0F, 0F, 0F);
		Layer03Shape22.setTextureSize(128, 64);

		Layer03Shape23 = new ModelRenderer(this, 56, 16);
		Layer03Shape23.addBox(7F, 1F, -1F, 1, 1, 1);
		Layer03Shape23.setRotationPoint(0F, 0F, 0F);
		Layer03Shape23.setTextureSize(128, 64);

		Layer03Shape24 = new ModelRenderer(this, 56, 16);
		Layer03Shape24.addBox(-6F, 0F, -2F, 1, 2, 1);
		Layer03Shape24.setRotationPoint(0F, 0F, 0F);
		Layer03Shape24.setTextureSize(128, 64);

		Layer03Shape25 = new ModelRenderer(this, 56, 16);
		Layer03Shape25.addBox(-7F, -4F, -3F, 1, 2, 1);
		Layer03Shape25.setRotationPoint(0F, 0F, 0F);
		Layer03Shape25.setTextureSize(128, 64);

		Layer03Shape16 = new ModelRenderer(this, 56, 4);
		Layer03Shape16.addBox(-4F, 3F, -4F, 4, 1, 1);
		Layer03Shape16.setRotationPoint(0F, 0F, 0F);
		Layer03Shape16.setTextureSize(128, 64);

		Layer03Shape27 = new ModelRenderer(this, 56, 4);
		Layer03Shape27.addBox(6F, 0F, -4F, 1, 1, 2);
		Layer03Shape27.setRotationPoint(0F, 0F, 0F);
		Layer03Shape27.setTextureSize(128, 64);

		Layer03Shape28 = new ModelRenderer(this, 56, 16);
		Layer03Shape28.addBox(6F, 1F, -2F, 1, 1, 1);
		Layer03Shape28.setRotationPoint(0F, 0F, 0F);
		Layer03Shape28.setTextureSize(128, 64);

		Layer03Shape29 = new ModelRenderer(this, 56, 16);
		Layer03Shape29.addBox(-5F, 0F, -3F, 1, 2, 1);
		Layer03Shape29.setRotationPoint(0F, 0F, 0F);
		Layer03Shape29.setTextureSize(128, 64);

		Layer03Shape210 = new ModelRenderer(this, 56, 4);
		Layer03Shape210.addBox(-7F, -1F, -4F, 2, 3, 1);
		Layer03Shape210.setRotationPoint(0F, 0F, 0F);
		Layer03Shape210.setTextureSize(128, 64);

		// Layer02
		Layer02Shape211 = new ModelRenderer(this, 56, 4);
		Layer02Shape211.addBox(-7F, -3F, -4F, 1, 2, 1);
		Layer02Shape211.setRotationPoint(0F, 0F, 0F);
		Layer02Shape211.setTextureSize(128, 64);

		Layer02Shape112 = new ModelRenderer(this, 124, 20);
		Layer02Shape112.addBox(6F, -5F, -5F, 1, 8, 1);
		Layer02Shape112.setRotationPoint(0F, 0F, 0F);
		Layer02Shape112.setTextureSize(128, 64);

		Layer02Shape113 = new ModelRenderer(this, 56, 20);
		Layer02Shape113.addBox(-6F, 0F, -5F, 12, 3, 1);
		Layer02Shape113.setRotationPoint(0F, 0F, 0F);
		Layer02Shape113.setTextureSize(128, 64);

		Layer02Shape114 = new ModelRenderer(this, 124, 20);
		Layer02Shape114.addBox(-7F, -5F, -5F, 1, 8, 1);
		Layer02Shape114.setRotationPoint(0F, 0F, 0F);
		Layer02Shape114.setTextureSize(128, 64);

		Layer02Shape115 = new ModelRenderer(this, 124, 20);
		Layer02Shape115.addBox(6F, -5F, -1F, 1, 8, 1);
		Layer02Shape115.setRotationPoint(0F, 0F, 0F);
		Layer02Shape115.setTextureSize(128, 64);

		Layer02Shape116 = new ModelRenderer(this, 124, 20);
		Layer02Shape116.addBox(-7F, -5F, -1F, 1, 8, 1);
		Layer02Shape116.setRotationPoint(0F, 0F, 0F);
		Layer02Shape116.setTextureSize(128, 64);

		Layer02Shape117 = new ModelRenderer(this, 56, 20);
		Layer02Shape117.addBox(-6F, -5F, -1F, 12, 8, 1);
		Layer02Shape117.setRotationPoint(0F, 0F, 0F);
		Layer02Shape117.setTextureSize(128, 64);

		Layer02Shape118 = new ModelRenderer(this, 56, 4);
		Layer02Shape118.addBox(0F, 2F, -4F, 5, 1, 1);
		Layer02Shape118.setRotationPoint(0F, 0F, 0F);
		Layer02Shape118.setTextureSize(128, 64);

		Layer02Shape119 = new ModelRenderer(this, 56, 16);
		Layer02Shape119.addBox(-6F, 2F, -2F, 2, 1, 1);
		Layer02Shape119.setRotationPoint(0F, 0F, 0F);
		Layer02Shape119.setTextureSize(128, 64);

		Layer02Shape120 = new ModelRenderer(this, 56, 8);
		Layer02Shape120.addBox(-2F, 2F, -2F, 2, 1, 1);
		Layer02Shape120.setRotationPoint(0F, 0F, 0F);
		Layer02Shape120.setTextureSize(128, 64);

		Layer02Shape121 = new ModelRenderer(this, 56, 16);
		Layer02Shape121.addBox(-4F, 2F, -3F, 4, 1, 1);
		Layer02Shape121.setRotationPoint(0F, 0F, 0F);
		Layer02Shape121.setTextureSize(128, 64);

		Layer02Shape122 = new ModelRenderer(this, 56, 16);
		Layer02Shape122.addBox(0F, 2F, -2F, 6, 1, 1);
		Layer02Shape122.setRotationPoint(0F, 0F, 0F);
		Layer02Shape122.setTextureSize(128, 64);

		Layer02Shape123 = new ModelRenderer(this, 56, 4);
		Layer02Shape123.addBox(2F, 2F, -3F, 1, 1, 1);
		Layer02Shape123.setRotationPoint(0F, 0F, 0F);
		Layer02Shape123.setTextureSize(128, 64);

		// Layer04
		Layer04Shape124 = new ModelRenderer(this, 56, 4);
		Layer04Shape124.addBox(5F, 2F, -3F, 2, 1, 1);
		Layer04Shape124.setRotationPoint(0F, 0F, 0F);
		Layer04Shape124.setTextureSize(128, 64);

		Layer04Shape225 = new ModelRenderer(this, 56, 4);
		Layer04Shape225.addBox(6F, 1F, -3F, 1, 1, 1);
		Layer04Shape225.setRotationPoint(0F, 0F, 0F);
		Layer04Shape225.setTextureSize(128, 64);

		// Layer01
		Layer01Shape226 = new ModelRenderer(this, 56, 16);
		Layer01Shape226.addBox(5F, 0F, -2F, 1, 1, 1);
		Layer01Shape226.setRotationPoint(0F, 0F, 0F);
		Layer01Shape226.setTextureSize(128, 64);

		Layer01Shape127 = new ModelRenderer(this, 56, 0);
		Layer01Shape127.addBox(-7F, 3F, -5F, 14, 1, 1);
		Layer01Shape127.setRotationPoint(0F, 0F, 0F);
		Layer01Shape127.setTextureSize(128, 64);

		Layer01Shape128 = new ModelRenderer(this, 56, 4);
		Layer01Shape128.addBox(3F, 3F, -2F, 3, 1, 1);
		Layer01Shape128.setRotationPoint(0F, 0F, 0F);
		Layer01Shape128.setTextureSize(128, 64);

		Layer01Shape129 = new ModelRenderer(this, 56, 0);
		Layer01Shape129.addBox(-7F, 3F, -1F, 14, 1, 1);
		Layer01Shape129.setRotationPoint(0F, 0F, 0F);
		Layer01Shape129.setTextureSize(128, 64);

		// Layer05
		Layer05Shape130 = new ModelRenderer(this, 56, 4);
		Layer05Shape130.addBox(-4F, 3F, -2F, 2, 1, 1);
		Layer05Shape130.setRotationPoint(0F, 0F, 0F);
		Layer05Shape130.setTextureSize(128, 64);

		Layer05Shape231 = new ModelRenderer(this, 56, 0);
		Layer05Shape231.addBox(2F, -2F, -5F, 1, 1, 1);
		Layer05Shape231.setRotationPoint(0F, 0F, 0F);
		Layer05Shape231.setTextureSize(128, 64);

		Layer05Shape232 = new ModelRenderer(this, 56, 16);
		Layer05Shape232.addBox(-6F, -2F, -3F, 1, 2, 1);
		Layer05Shape232.setRotationPoint(0F, 0F, 0F);
		Layer05Shape232.setTextureSize(128, 64);

		Layer05Shape233 = new ModelRenderer(this, 56, 16);
		Layer05Shape233.addBox(-7F, -2F, -2F, 1, 2, 1);
		Layer05Shape233.setRotationPoint(0F, 0F, 0F);
		Layer05Shape233.setTextureSize(128, 64);

		Layer05Shape234 = new ModelRenderer(this, 56, 16);
		Layer05Shape234.addBox(5F, -1F, -3F, 1, 1, 1);
		Layer05Shape234.setRotationPoint(0F, 0F, 0F);
		Layer05Shape234.setTextureSize(128, 64);

		Layer05Shape235 = new ModelRenderer(this, 56, 4);
		Layer05Shape235.addBox(6F, -5F, -4F, 2, 2, 1);
		Layer05Shape235.setRotationPoint(0F, 0F, 0F);
		Layer05Shape235.setTextureSize(128, 64);

		Layer05Shape236 = new ModelRenderer(this, 56, 20);
		Layer05Shape236.addBox(-6F, -1F, -5F, 5, 1, 1);
		Layer05Shape236.setRotationPoint(0F, 0F, 0F);
		Layer05Shape236.setTextureSize(128, 64);

		Layer05Shape237 = new ModelRenderer(this, 56, 20);
		Layer05Shape237.addBox(2F, -1F, -5F, 4, 1, 1);
		Layer05Shape237.setRotationPoint(0F, 0F, 0F);
		Layer05Shape237.setTextureSize(128, 64);

		Layer05Shape238 = new ModelRenderer(this, 56, 16);
		Layer05Shape238.addBox(7F, -2F, -2F, 1, 1, 1);
		Layer05Shape238.setRotationPoint(0F, 0F, 0F);
		Layer05Shape238.setTextureSize(128, 64);

		Layer05Shape239 = new ModelRenderer(this, 56, 16);
		Layer05Shape239.addBox(6F, -1F, -2F, 1, 1, 1);
		Layer05Shape239.setRotationPoint(0F, 0F, 0F);
		Layer05Shape239.setTextureSize(128, 64);

		Layer05Shape240 = new ModelRenderer(this, 56, 0);
		Layer05Shape240.addBox(-1F, -1F, -5F, 3, 1, 1);
		Layer05Shape240.setRotationPoint(0F, 0F, 0F);
		Layer05Shape240.setTextureSize(128, 64);

		Layer05Shape241 = new ModelRenderer(this, 56, 20);
		Layer05Shape241.addBox(-1F, -2F, -5F, 3, 1, 1);
		Layer05Shape241.setRotationPoint(0F, 0F, 0F);
		Layer05Shape241.setTextureSize(128, 64);

		// Layer06
		Layer06Shape242 = new ModelRenderer(this, 56, 4);
		Layer06Shape242.addBox(6F, -3F, -4F, 2, 3, 1);
		Layer06Shape242.setRotationPoint(0F, 0F, 0F);
		Layer06Shape242.setTextureSize(128, 64);

		Layer06Shape243 = new ModelRenderer(this, 56, 16);
		Layer06Shape243.addBox(-8F, -2F, -1F, 1, 1, 1);
		Layer06Shape243.setRotationPoint(0F, 0F, 0F);
		Layer06Shape243.setTextureSize(128, 64);

		Layer06Shape244 = new ModelRenderer(this, 56, 16);
		Layer06Shape244.addBox(6F, -3F, -3F, 1, 2, 1);
		Layer06Shape244.setRotationPoint(0F, 0F, 0F);
		Layer06Shape244.setTextureSize(128, 64);

		Layer06Shape245 = new ModelRenderer(this, 56, 0);
		Layer06Shape245.addBox(-4F, -2F, -5F, 1, 1, 1);
		Layer06Shape245.setRotationPoint(0F, 0F, 0F);
		Layer06Shape245.setTextureSize(128, 64);

		Layer06Shape246 = new ModelRenderer(this, 56, 0);
		Layer06Shape246.addBox(-2F, -2F, -5F, 1, 1, 1);
		Layer06Shape246.setRotationPoint(0F, 0F, 0F);
		Layer06Shape246.setTextureSize(128, 64);

		Layer06Shape247 = new ModelRenderer(this, 56, 20);
		Layer06Shape247.addBox(3F, -2F, -5F, 3, 1, 1);
		Layer06Shape247.setRotationPoint(0F, 0F, 0F);
		Layer06Shape247.setTextureSize(128, 64);

		Layer06Shape248 = new ModelRenderer(this, 56, 20);
		Layer06Shape248.addBox(-3F, -2F, -5F, 1, 1, 1);
		Layer06Shape248.setRotationPoint(0F, 0F, 0F);
		Layer06Shape248.setTextureSize(128, 64);

		// Layer07
		Layer07Shape249 = new ModelRenderer(this, 56, 20);
		Layer07Shape249.addBox(-6F, -2F, -5F, 2, 1, 1);
		Layer07Shape249.setRotationPoint(0F, 0F, 0F);
		Layer07Shape249.setTextureSize(128, 64);

		Layer07Shape250 = new ModelRenderer(this, 56, 8);
		Layer07Shape250.addBox(-7F, -3F, -2F, 1, 1, 1);
		Layer07Shape250.setRotationPoint(0F, 0F, 0F);
		Layer07Shape250.setTextureSize(128, 64);

		Layer07Shape251 = new ModelRenderer(this, 56, 20);
		Layer07Shape251.addBox(-2F, -3F, -5F, 5, 1, 1);
		Layer07Shape251.setRotationPoint(0F, 0F, 0F);
		Layer07Shape251.setTextureSize(128, 64);

		Layer07Shape252 = new ModelRenderer(this, 56, 20);
		Layer07Shape252.addBox(-6F, -3F, -5F, 3, 1, 1);
		Layer07Shape252.setRotationPoint(0F, 0F, 0F);
		Layer07Shape252.setTextureSize(128, 64);

		Layer07Shape253 = new ModelRenderer(this, 56, 20);
		Layer07Shape253.addBox(4F, -3F, -5F, 2, 1, 1);
		Layer07Shape253.setRotationPoint(0F, 0F, 0F);
		Layer07Shape253.setTextureSize(128, 64);

		Layer07Shape254 = new ModelRenderer(this, 56, 0);
		Layer07Shape254.addBox(3F, -3F, -5F, 1, 1, 1);
		Layer07Shape254.setRotationPoint(0F, 0F, 0F);
		Layer07Shape254.setTextureSize(128, 64);

		Layer07Shape255 = new ModelRenderer(this, 56, 0);
		Layer07Shape255.addBox(-3F, -3F, -5F, 1, 1, 1);
		Layer07Shape255.setRotationPoint(0F, 0F, 0F);
		Layer07Shape255.setTextureSize(128, 64);

		// Layer08
		Layer08Shape256 = new ModelRenderer(this, 56, 8);
		Layer08Shape256.addBox(6F, -5F, -2F, 2, 3, 1);
		Layer08Shape256.setRotationPoint(0F, 0F, 0F);
		Layer08Shape256.setTextureSize(128, 64);

		Layer08Shape257 = new ModelRenderer(this, 56, 20);
		Layer08Shape257.addBox(-6F, -4F, -5F, 12, 1, 1);
		Layer08Shape257.setRotationPoint(0F, 0F, 0F);
		Layer08Shape257.setTextureSize(128, 64);

		Layer08Shape258 = new ModelRenderer(this, 56, 4);
		Layer08Shape258.addBox(-6F, -4F, -4F, 1, 1, 1);
		Layer08Shape258.setRotationPoint(0F, 0F, 0F);
		Layer08Shape258.setTextureSize(128, 64);

		Layer08Shape259 = new ModelRenderer(this, 56, 16);
		Layer08Shape259.addBox(6F, -4F, -3F, 2, 1, 1);
		Layer08Shape259.setRotationPoint(0F, 0F, 0F);
		Layer08Shape259.setTextureSize(128, 64);

		// Layer09
		Layer09Shape260 = new ModelRenderer(this, 56, 8);
		Layer09Shape260.addBox(-8F, -5F, -2F, 2, 2, 1);
		Layer09Shape260.setRotationPoint(0F, 0F, 0F);
		Layer09Shape260.setTextureSize(128, 64);

		Layer09Shape261 = new ModelRenderer(this, 56, 16);
		Layer09Shape261.addBox(6F, -6F, -2F, 2, 1, 2);
		Layer09Shape261.setRotationPoint(0F, 0F, 0F);
		Layer09Shape261.setTextureSize(128, 64);

		Layer09Shape262 = new ModelRenderer(this, 56, 16);
		Layer09Shape262.addBox(-6F, -5F, -3F, 12, 1, 1);
		Layer09Shape262.setRotationPoint(0F, 0F, 0F);
		Layer09Shape262.setTextureSize(128, 64);

		Layer09Shape263 = new ModelRenderer(this, 56, 20);
		Layer09Shape263.addBox(-1F, -7F, -5F, 3, 3, 1);
		Layer09Shape263.setRotationPoint(0F, 0F, 0F);
		Layer09Shape263.setTextureSize(128, 64);

		Layer09Shape264 = new ModelRenderer(this, 56, 20);
		Layer09Shape264.addBox(5F, -5F, -5F, 1, 1, 1);
		Layer09Shape264.setRotationPoint(0F, 0F, 0F);
		Layer09Shape264.setTextureSize(128, 64);

		Layer09Shape265 = new ModelRenderer(this, 56, 12);
		Layer09Shape265.addBox(2F, -5F, -5F, 3, 1, 1);
		Layer09Shape265.setRotationPoint(0F, 0F, 0F);
		Layer09Shape265.setTextureSize(128, 64);

		Layer09Shape266 = new ModelRenderer(this, 56, 12);
		Layer09Shape266.addBox(-4F, -5F, -5F, 3, 1, 1);
		Layer09Shape266.setRotationPoint(0F, 0F, 0F);
		Layer09Shape266.setTextureSize(128, 64);

		Layer09Shape267 = new ModelRenderer(this, 56, 16);
		Layer09Shape267.addBox(-7F, -5F, -4F, 1, 1, 1);
		Layer09Shape267.setRotationPoint(0F, 0F, 0F);
		Layer09Shape267.setTextureSize(128, 64);

		Layer09Shape268 = new ModelRenderer(this, 56, 8);
		Layer09Shape268.addBox(-6F, -6F, -2F, 1, 1, 1);
		Layer09Shape268.setRotationPoint(0F, 0F, 0F);
		Layer09Shape268.setTextureSize(128, 64);

		Layer09Shape269 = new ModelRenderer(this, 56, 0);
		Layer09Shape269.addBox(-6F, -6F, -5F, 1, 1, 1);
		Layer09Shape269.setRotationPoint(0F, 0F, 0F);
		Layer09Shape269.setTextureSize(128, 64);

		Layer09Shape270 = new ModelRenderer(this, 56, 4);
		Layer09Shape270.addBox(6F, -8F, -4F, 1, 3, 1);
		Layer09Shape270.setRotationPoint(0F, 0F, 0F);
		Layer09Shape270.setTextureSize(128, 64);

		// Layer10
		Layer10Shape271 = new ModelRenderer(this, 56, 20);
		Layer10Shape271.addBox(-6F, -5F, -5F, 2, 1, 1);
		Layer10Shape271.setRotationPoint(0F, 0F, 0F);
		Layer10Shape271.setTextureSize(128, 64);

		Layer10Shape272 = new ModelRenderer(this, 124, 12);
		Layer10Shape272.addBox(2F, -6F, -5F, 1, 1, 1);
		Layer10Shape272.setRotationPoint(0F, 0F, 0F);
		Layer10Shape272.setTextureSize(128, 64);

		Layer10Shape273 = new ModelRenderer(this, 56, 0);
		Layer10Shape273.addBox(-7F, -8F, -5F, 1, 2, 1);
		Layer10Shape273.setRotationPoint(0F, 0F, 0F);
		Layer10Shape273.setTextureSize(128, 64);

		Layer10Shape274 = new ModelRenderer(this, 56, 0);
		Layer10Shape274.addBox(-6F, -6F, -1F, 1, 1, 1);
		Layer10Shape274.setRotationPoint(0F, 0F, 0F);
		Layer10Shape274.setTextureSize(128, 64);

		Layer10Shape275 = new ModelRenderer(this, 56, 20);
		Layer10Shape275.addBox(-5F, -6F, -1F, 10, 1, 1);
		Layer10Shape275.setRotationPoint(0F, 0F, 0F);
		Layer10Shape275.setTextureSize(128, 64);

		Layer10Shape276 = new ModelRenderer(this, 56, 12);
		Layer10Shape276.addBox(3F, -6F, -5F, 2, 1, 1);
		Layer10Shape276.setRotationPoint(0F, 0F, 0F);
		Layer10Shape276.setTextureSize(128, 64);

		Layer10Shape277 = new ModelRenderer(this, 56, 20);
		Layer10Shape277.addBox(-5F, -6F, -5F, 1, 1, 1);
		Layer10Shape277.setRotationPoint(0F, 0F, 0F);
		Layer10Shape277.setTextureSize(128, 64);

		Layer10Shape278 = new ModelRenderer(this, 124, 12);
		Layer10Shape278.addBox(-2F, -6F, -5F, 1, 1, 1);
		Layer10Shape278.setRotationPoint(0F, 0F, 0F);
		Layer10Shape278.setTextureSize(128, 64);

		Layer10Shape279 = new ModelRenderer(this, 56, 12);
		Layer10Shape279.addBox(-4F, -6F, -5F, 2, 1, 1);
		Layer10Shape279.setRotationPoint(0F, 0F, 0F);
		Layer10Shape279.setTextureSize(128, 64);

		Layer10Shape280 = new ModelRenderer(this, 56, 16);
		Layer10Shape280.addBox(-5F, -6F, -3F, 12, 1, 1);
		Layer10Shape280.setRotationPoint(0F, 0F, 0F);
		Layer10Shape280.setTextureSize(128, 64);

		Layer10Shape281 = new ModelRenderer(this, 56, 16);
		Layer10Shape281.addBox(-8F, -6F, -4F, 3, 1, 1);
		Layer10Shape281.setRotationPoint(0F, 0F, 0F);
		Layer10Shape281.setTextureSize(128, 64);

		Layer10Shape282 = new ModelRenderer(this, 56, 0);
		Layer10Shape282.addBox(5F, -6F, -5F, 1, 1, 1);
		Layer10Shape282.setRotationPoint(0F, 0F, 0F);
		Layer10Shape282.setTextureSize(128, 64);

		Layer10Shape283 = new ModelRenderer(this, 56, 0);
		Layer10Shape283.addBox(5F, -6F, -1F, 1, 1, 1);
		Layer10Shape283.setRotationPoint(0F, 0F, 0F);
		Layer10Shape283.setTextureSize(128, 64);

		Layer10Shape284 = new ModelRenderer(this, 56, 0);
		Layer10Shape284.addBox(6F, -8F, -1F, 1, 2, 1);
		Layer10Shape284.setRotationPoint(0F, 0F, 0F);
		Layer10Shape284.setTextureSize(128, 64);

		// Layer11
		Layer11Shape285 = new ModelRenderer(this, 56, 0);
		Layer11Shape285.addBox(6F, -8F, -5F, 1, 2, 1);
		Layer11Shape285.setRotationPoint(0F, 0F, 0F);
		Layer11Shape285.setTextureSize(128, 64);

		Layer11Shape286 = new ModelRenderer(this, 56, 0);
		Layer11Shape286.addBox(-7F, -8F, -1F, 1, 2, 1);
		Layer11Shape286.setRotationPoint(0F, 0F, 0F);
		Layer11Shape286.setTextureSize(128, 64);

		Layer11Shape287 = new ModelRenderer(this, 56, 20);
		Layer11Shape287.addBox(-6F, -7F, -5F, 2, 1, 1);
		Layer11Shape287.setRotationPoint(0F, 0F, 0F);
		Layer11Shape287.setTextureSize(128, 64);

		Layer11Shape288 = new ModelRenderer(this, 56, 12);
		Layer11Shape288.addBox(2F, -7F, -5F, 3, 1, 1);
		Layer11Shape288.setRotationPoint(0F, 0F, 0F);
		Layer11Shape288.setTextureSize(128, 64);

		Layer11Shape289 = new ModelRenderer(this, 56, 12);
		Layer11Shape289.addBox(-4F, -7F, -5F, 3, 1, 1);
		Layer11Shape289.setRotationPoint(0F, 0F, 0F);
		Layer11Shape289.setTextureSize(128, 64);

		Layer11Shape290 = new ModelRenderer(this, 56, 20);
		Layer11Shape290.addBox(5F, -7F, -5F, 1, 1, 1);
		Layer11Shape290.setRotationPoint(0F, 0F, 0F);
		Layer11Shape290.setTextureSize(128, 64);

		Layer11Shape291 = new ModelRenderer(this, 56, 20);
		Layer11Shape291.addBox(-6F, -8F, -1F, 12, 2, 1);
		Layer11Shape291.setRotationPoint(0F, 0F, 0F);
		Layer11Shape291.setTextureSize(128, 64);

		Layer11Shape292 = new ModelRenderer(this, 56, 16);
		Layer11Shape292.addBox(-6F, -7F, -4F, 2, 1, 3);
		Layer11Shape292.setRotationPoint(0F, 0F, 0F);
		Layer11Shape292.setTextureSize(128, 64);

		Layer11Shape293 = new ModelRenderer(this, 56, 8);
		Layer11Shape293.addBox(6F, -7F, -2F, 1, 1, 1);
		Layer11Shape293.setRotationPoint(0F, 0F, 0F);
		Layer11Shape293.setTextureSize(128, 64);

		// Layer12
		Layer12Shape294 = new ModelRenderer(this, 56, 16);
		Layer12Shape294.addBox(-3F, -9F, -3F, 9, 3, 1);
		Layer12Shape294.setRotationPoint(0F, 0F, 0F);
		Layer12Shape294.setTextureSize(128, 64);

		Layer12Shape295 = new ModelRenderer(this, 56, 20);
		Layer12Shape295.addBox(-6F, -8F, -5F, 12, 1, 1);
		Layer12Shape295.setRotationPoint(0F, 0F, 0F);
		Layer12Shape295.setTextureSize(128, 64);

		Layer12Shape296 = new ModelRenderer(this, 56, 8);
		Layer12Shape296.addBox(5F, -9F, -2F, 1, 2, 1);
		Layer12Shape296.setRotationPoint(0F, 0F, 0F);
		Layer12Shape296.setTextureSize(128, 64);

		Layer12Shape297 = new ModelRenderer(this, 56, 16);
		Layer12Shape297.addBox(-7F, -8F, -4F, 2, 1, 2);
		Layer12Shape297.setRotationPoint(0F, 0F, 0F);
		Layer12Shape297.setTextureSize(128, 64);

		Layer12Shape298 = new ModelRenderer(this, 56, 4);
		Layer12Shape298.addBox(-4F, -10F, -4F, 1, 3, 1);
		Layer12Shape298.setRotationPoint(0F, 0F, 0F);
		Layer12Shape298.setTextureSize(128, 64);

		Layer12Shape299 = new ModelRenderer(this, 56, 8);
		Layer12Shape299.addBox(-3F, -8F, -2F, 2, 1, 1);
		Layer12Shape299.setRotationPoint(0F, 0F, 0F);
		Layer12Shape299.setTextureSize(128, 64);

		Layer12Shape2100 = new ModelRenderer(this, 56, 16);
		Layer12Shape2100.addBox(-6F, -9F, -2F, 3, 2, 1);
		Layer12Shape2100.setRotationPoint(0F, 0F, 0F);
		Layer12Shape2100.setTextureSize(128, 64);

		// Layer13
		Layer13Shape2101 = new ModelRenderer(this, 56, 16);
		Layer13Shape2101.addBox(-4F, -8F, -3F, 1, 1, 1);
		Layer13Shape2101.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2101.setTextureSize(128, 64);

		Layer13Shape2102 = new ModelRenderer(this, 56, 20);
		Layer13Shape2102.addBox(-5F, -9F, -5F, 10, 1, 1);
		Layer13Shape2102.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2102.setTextureSize(128, 64);

		Layer13Shape2103 = new ModelRenderer(this, 56, 20);
		Layer13Shape2103.addBox(-5F, -9F, -1F, 10, 1, 1);
		Layer13Shape2103.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2103.setTextureSize(128, 64);

		Layer13Shape2104 = new ModelRenderer(this, 56, 0);
		Layer13Shape2104.addBox(5F, -9F, -5F, 1, 1, 1);
		Layer13Shape2104.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2104.setTextureSize(128, 64);

		Layer13Shape2105 = new ModelRenderer(this, 56, 0);
		Layer13Shape2105.addBox(-6F, -9F, -5F, 1, 1, 1);
		Layer13Shape2105.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2105.setTextureSize(128, 64);

		Layer13Shape2106 = new ModelRenderer(this, 56, 0);
		Layer13Shape2106.addBox(-6F, -9F, -1F, 1, 1, 1);
		Layer13Shape2106.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2106.setTextureSize(128, 64);

		Layer13Shape2107 = new ModelRenderer(this, 56, 0);
		Layer13Shape2107.addBox(5F, -9F, -1F, 1, 1, 1);
		Layer13Shape2107.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2107.setTextureSize(128, 64);

		Layer13Shape2108 = new ModelRenderer(this, 56, 8);
		Layer13Shape2108.addBox(-1F, -9F, -2F, 1, 1, 1);
		Layer13Shape2108.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2108.setTextureSize(128, 64);

		Layer13Shape2109 = new ModelRenderer(this, 56, 4);
		Layer13Shape2109.addBox(3F, -9F, -4F, 1, 1, 1);
		Layer13Shape2109.setRotationPoint(0F, 0F, 0F);
		Layer13Shape2109.setTextureSize(128, 64);

		// Layer14
		Layer14Shape2110 = new ModelRenderer(this, 56, 4);
		Layer14Shape2110.addBox(5F, -9F, -4F, 1, 1, 1);
		Layer14Shape2110.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2110.setTextureSize(128, 64);

		Layer14Shape2111 = new ModelRenderer(this, 56, 0);
		Layer14Shape2111.addBox(-5F, -10F, -1F, 10, 1, 1);
		Layer14Shape2111.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2111.setTextureSize(128, 64);

		Layer14Shape2112 = new ModelRenderer(this, 56, 0);
		Layer14Shape2112.addBox(-5F, -10F, -5F, 10, 1, 1);
		Layer14Shape2112.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2112.setTextureSize(128, 64);

		Layer14Shape2113 = new ModelRenderer(this, 56, 16);
		Layer14Shape2113.addBox(5F, -11F, -1F, 1, 2, 1);
		Layer14Shape2113.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2113.setTextureSize(128, 64);

		Layer14Shape2114 = new ModelRenderer(this, 56, 16);
		Layer14Shape2114.addBox(-2F, -10F, -3F, 3, 1, 1);
		Layer14Shape2114.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2114.setTextureSize(128, 64);

		Layer14Shape2115 = new ModelRenderer(this, 56, 8);
		Layer14Shape2115.addBox(0F, -11F, -2F, 5, 2, 1);
		Layer14Shape2115.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2115.setTextureSize(128, 64);

		Layer14Shape2116 = new ModelRenderer(this, 56, 16);
		Layer14Shape2116.addBox(3F, -10F, -3F, 2, 1, 1);
		Layer14Shape2116.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2116.setTextureSize(128, 64);

		Layer14Shape2117 = new ModelRenderer(this, 56, 4);
		Layer14Shape2117.addBox(1F, -10F, -4F, 2, 1, 1);
		Layer14Shape2117.setRotationPoint(0F, 0F, 0F);
		Layer14Shape2117.setTextureSize(128, 64);

		// Layer15
		Layer15Shape2118 = new ModelRenderer(this, 56, 4);
		Layer15Shape2118.addBox(4F, -10F, -4F, 1, 1, 1);
		Layer15Shape2118.setRotationPoint(0F, 0F, 0F);
		Layer15Shape2118.setTextureSize(128, 64);

		Layer15Shape2119 = new ModelRenderer(this, 56, 16);
		Layer15Shape2119.addBox(0F, -11F, -4F, 1, 1, 2);
		Layer15Shape2119.setRotationPoint(0F, 0F, 0F);
		Layer15Shape2119.setTextureSize(128, 64);

		Layer15Shape2120 = new ModelRenderer(this, 56, 4);
		Layer15Shape2120.addBox(-3F, -11F, -4F, 3, 1, 1);
		Layer15Shape2120.setRotationPoint(0F, 0F, 0F);
		Layer15Shape2120.setTextureSize(128, 64);

		// Layer03
		Layer03Shape20.addChild(Layer03Shape21);
		Layer03Shape20.addChild(Layer03Shape22);
		Layer03Shape20.addChild(Layer03Shape23);
		Layer03Shape20.addChild(Layer03Shape24);
		Layer03Shape20.addChild(Layer03Shape25);
		Layer03Shape20.addChild(Layer03Shape16);
		Layer03Shape20.addChild(Layer03Shape27);
		Layer03Shape20.addChild(Layer03Shape28);
		Layer03Shape20.addChild(Layer03Shape29);
		Layer03Shape20.addChild(Layer03Shape210);

		// Layer02
		Layer03Shape20.addChild(Layer02Shape211);
		Layer03Shape20.addChild(Layer02Shape112);
		Layer03Shape20.addChild(Layer02Shape113);
		Layer03Shape20.addChild(Layer02Shape114);
		Layer03Shape20.addChild(Layer02Shape115);
		Layer03Shape20.addChild(Layer02Shape116);
		Layer03Shape20.addChild(Layer02Shape117);
		Layer03Shape20.addChild(Layer02Shape118);
		Layer03Shape20.addChild(Layer02Shape119);
		Layer03Shape20.addChild(Layer02Shape120);
		Layer03Shape20.addChild(Layer02Shape121);
		Layer03Shape20.addChild(Layer02Shape122);
		Layer03Shape20.addChild(Layer02Shape123);

		// Layer04
		Layer03Shape20.addChild(Layer04Shape124);
		Layer03Shape20.addChild(Layer04Shape225);

		// Layer01
		Layer03Shape20.addChild(Layer01Shape226);
		Layer03Shape20.addChild(Layer01Shape127);
		Layer03Shape20.addChild(Layer01Shape128);
		Layer03Shape20.addChild(Layer01Shape129);

		// Layer05
		Layer03Shape20.addChild(Layer05Shape130);
		Layer03Shape20.addChild(Layer05Shape231);
		Layer03Shape20.addChild(Layer05Shape232);
		Layer03Shape20.addChild(Layer05Shape233);
		Layer03Shape20.addChild(Layer05Shape234);
		Layer03Shape20.addChild(Layer05Shape235);
		Layer03Shape20.addChild(Layer05Shape236);
		Layer03Shape20.addChild(Layer05Shape237);
		Layer03Shape20.addChild(Layer05Shape238);
		Layer03Shape20.addChild(Layer05Shape239);
		Layer03Shape20.addChild(Layer05Shape240);
		Layer03Shape20.addChild(Layer05Shape241);

		// Layer06
		Layer03Shape20.addChild(Layer06Shape242);
		Layer03Shape20.addChild(Layer06Shape243);
		Layer03Shape20.addChild(Layer06Shape244);
		Layer03Shape20.addChild(Layer06Shape245);
		Layer03Shape20.addChild(Layer06Shape246);
		Layer03Shape20.addChild(Layer06Shape247);
		Layer03Shape20.addChild(Layer06Shape248);

		// Layer07
		Layer03Shape20.addChild(Layer07Shape249);
		Layer03Shape20.addChild(Layer07Shape250);
		Layer03Shape20.addChild(Layer07Shape251);
		Layer03Shape20.addChild(Layer07Shape252);
		Layer03Shape20.addChild(Layer07Shape253);
		Layer03Shape20.addChild(Layer07Shape254);
		Layer03Shape20.addChild(Layer07Shape255);

		// Layer08
		Layer03Shape20.addChild(Layer08Shape256);
		Layer03Shape20.addChild(Layer08Shape257);
		Layer03Shape20.addChild(Layer08Shape258);
		Layer03Shape20.addChild(Layer08Shape259);

		// Layer09
		Layer03Shape20.addChild(Layer09Shape260);
		Layer03Shape20.addChild(Layer09Shape261);
		Layer03Shape20.addChild(Layer09Shape262);
		Layer03Shape20.addChild(Layer09Shape263);
		Layer03Shape20.addChild(Layer09Shape264);
		Layer03Shape20.addChild(Layer09Shape265);
		Layer03Shape20.addChild(Layer09Shape266);
		Layer03Shape20.addChild(Layer09Shape267);
		Layer03Shape20.addChild(Layer09Shape268);
		Layer03Shape20.addChild(Layer09Shape269);
		Layer03Shape20.addChild(Layer09Shape270);

		// Layer10
		Layer03Shape20.addChild(Layer10Shape271);
		Layer03Shape20.addChild(Layer10Shape272);
		Layer03Shape20.addChild(Layer10Shape273);
		Layer03Shape20.addChild(Layer10Shape274);
		Layer03Shape20.addChild(Layer10Shape275);
		Layer03Shape20.addChild(Layer10Shape276);
		Layer03Shape20.addChild(Layer10Shape277);
		Layer03Shape20.addChild(Layer10Shape278);
		Layer03Shape20.addChild(Layer10Shape279);
		Layer03Shape20.addChild(Layer10Shape280);
		Layer03Shape20.addChild(Layer10Shape281);
		Layer03Shape20.addChild(Layer10Shape282);
		Layer03Shape20.addChild(Layer10Shape283);
		Layer03Shape20.addChild(Layer10Shape284);

		// Layer11
		Layer03Shape20.addChild(Layer11Shape285);
		Layer03Shape20.addChild(Layer11Shape286);
		Layer03Shape20.addChild(Layer11Shape287);
		Layer03Shape20.addChild(Layer11Shape288);
		Layer03Shape20.addChild(Layer11Shape289);
		Layer03Shape20.addChild(Layer11Shape290);
		Layer03Shape20.addChild(Layer11Shape291);
		Layer03Shape20.addChild(Layer11Shape292);
		Layer03Shape20.addChild(Layer11Shape293);

		// Layer12
		Layer03Shape20.addChild(Layer12Shape294);
		Layer03Shape20.addChild(Layer12Shape295);
		Layer03Shape20.addChild(Layer12Shape296);
		Layer03Shape20.addChild(Layer12Shape297);
		Layer03Shape20.addChild(Layer12Shape298);
		Layer03Shape20.addChild(Layer12Shape299);
		Layer03Shape20.addChild(Layer12Shape2100);

		// Layer13
		Layer03Shape20.addChild(Layer13Shape2101);
		Layer03Shape20.addChild(Layer13Shape2102);
		Layer03Shape20.addChild(Layer13Shape2103);
		Layer03Shape20.addChild(Layer13Shape2104);
		Layer03Shape20.addChild(Layer13Shape2105);
		Layer03Shape20.addChild(Layer13Shape2106);
		Layer03Shape20.addChild(Layer13Shape2107);
		Layer03Shape20.addChild(Layer13Shape2108);
		Layer03Shape20.addChild(Layer13Shape2109);

		// Layer14
		Layer03Shape20.addChild(Layer14Shape2110);
		Layer03Shape20.addChild(Layer14Shape2111);
		Layer03Shape20.addChild(Layer14Shape2112);
		Layer03Shape20.addChild(Layer14Shape2113);
		Layer03Shape20.addChild(Layer14Shape2114);
		Layer03Shape20.addChild(Layer14Shape2115);
		Layer03Shape20.addChild(Layer14Shape2116);
		Layer03Shape20.addChild(Layer14Shape2117);

		// Layer15
		Layer03Shape20.addChild(Layer15Shape2118);
		Layer03Shape20.addChild(Layer15Shape2119);
		Layer03Shape20.addChild(Layer15Shape2120);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		if (entity instanceof EntityArmorStand)
		{
			f = 0.0f;
			f1 = 0.0f;
			f2 = 0.0f;
			f3 = 0.0f;
			f4 = 0.0f;
		}

		bipedHeadwear.showModel = false;
		bipedHead = Layer03Shape20;
		Layer03Shape20.showModel = (EntityEquipmentSlot.HEAD == slot);

		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		super.render(entity, f, f1, f2, f3, f4, f5);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
