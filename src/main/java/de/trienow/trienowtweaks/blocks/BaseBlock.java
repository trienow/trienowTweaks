package de.trienow.trienowtweaks.blocks;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author (c) trienow 2016 - 2022
 */
public abstract class BaseBlock extends Block
{
	int tooltipCount = 1;

	/**
	 * Constructs an object of type BaseBlock.java
	 */
	@SuppressWarnings("SameParameterValue")
	BaseBlock(Properties props)
	{
		super(props);
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable BlockGetter pLevel, List<Component> pTooltip, TooltipFlag pFlag)
	{
		for (int i = 0; i < tooltipCount; i++)
		{
			pTooltip.add(new TranslatableComponent(this.getDescriptionId() + ".tooltip" + i));
		}
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
	}

	static Properties defaultProperties(Material materialIn)
	{
		return Properties.of(materialIn).strength(3.5f, 5f); //Stone is 1.5f, 6f
	}
}
