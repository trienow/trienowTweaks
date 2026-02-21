package de.trienow.trienowtweaks.blocks;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class BaseBlockItem extends BlockItem
{
	BaseBlock block;

	public BaseBlockItem(BaseBlock block, Item.Properties properties)
	{
		super(block, properties);
		this.block = block;
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		for (int i = 0; i < block.tooltipCount; i++)
		{
			tooltipAdder.accept(Component.translatable(this.getDescriptionId() + ".tooltip" + i));
		}
		super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
	}
}
