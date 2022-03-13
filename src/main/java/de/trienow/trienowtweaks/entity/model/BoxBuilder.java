package de.trienow.trienowtweaks.entity.model;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;

/**
 * A helper class to create a new box for models.
 * Provides a layer of abstraction to be able to better handle Mojang's changes to the model classes.
 *
 * @author (c) trienow 2022
 */
public class BoxBuilder
{
	private int textureOffsetX;
	private int textureOffsetY;

	private float offsetX;
	private float offsetY;
	private float offsetZ;
	private float width;
	private float height;
	private float depth;

	private float rotationPointX;
	private float rotationPointY;
	private float rotationPointZ;

	private float rotationX;
	private float rotationY;
	private float rotationZ;

	public BoxBuilder setTextureOffset(int textureOffsetX, int textureOffsetY)
	{
		this.textureOffsetX = textureOffsetX;
		this.textureOffsetY = textureOffsetY;
		return this;
	}

	public BoxBuilder setRotationPoint(float rotationPointX, float rotationPointY, float rotationPointZ)
	{
		this.rotationPointX = rotationPointX;
		this.rotationPointY = rotationPointY;
		this.rotationPointZ = rotationPointZ;
		return this;
	}

	public BoxBuilder setRotation(float rotationX, float rotationY, float rotationZ)
	{
		this.rotationX = rotationX;
		this.rotationY = rotationY;
		this.rotationZ = rotationZ;
		return this;
	}

	public BoxBuilder setBoxOffset(float offsetX, float offsetY, float offsetZ)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.offsetZ = offsetZ;
		return this;
	}

	public BoxBuilder setBox(float width, float height, float depth)
	{
		this.width = width;
		this.height = height;
		this.depth = depth;
		return this;
	}

	public PartDefinition build(PartDefinition partDef, String name)
	{
		PartPose partPose = PartPose.offsetAndRotation(rotationPointX, rotationPointY, rotationPointZ, rotationX, rotationY, rotationZ);
		CubeListBuilder cubeListBuilder = CubeListBuilder.create()
				.texOffs(textureOffsetX, textureOffsetY)
				.addBox(offsetX, offsetY, offsetZ, width, height, depth);
		return partDef.addOrReplaceChild(name, cubeListBuilder, partPose);
	}
}
