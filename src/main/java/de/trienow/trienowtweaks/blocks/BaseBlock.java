package de.trienow.trienowtweaks.blocks;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author trienow 2016 - 2023
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
			pTooltip.add(Component.translatable(this.getDescriptionId() + ".tooltip" + i));
		}
		super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
	}

	static Properties defaultProperties()
	{
		return BlockBehaviour.Properties.of().strength(3.5f, 5f); //Stone is 1.5f, 6f
	}
}
