package de.trienow.trienowtweaks.blocks;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

/**
 * @author trienow 2016 - 2026
 */
public abstract class BaseBlock extends Block
{
	int tooltipCount = 1;

	/**
	 * Constructs an object of type BaseBlock.java
	 */
	@SuppressWarnings("SameParameterValue") BaseBlock(Properties props, ResourceLocation registryName)
	{
		super(props.setId(ResourceKey.create(Registries.BLOCK, registryName)));
	}

	static Properties defaultProperties()
	{
		return BlockBehaviour.Properties.of().strength(3.5f, 5f); //Stone is 1.5f, 6f
	}
}
