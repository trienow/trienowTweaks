package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.config.Globals;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author (c) trienow 2016 - 2022
 */
public class ItemWeWand extends BaseItem
{
	private static final String TOOLTIP_KEY = "item.trienowtweaks.we_wand.tooltip0";
	private static final String[] FORMATTERS = new String[] {
			ChatFormatting.AQUA.toString(),
			ChatFormatting.BLACK.toString(),
			ChatFormatting.BLUE.toString(),
			ChatFormatting.DARK_AQUA.toString(),
			ChatFormatting.DARK_BLUE.toString(),
			ChatFormatting.DARK_GRAY.toString(),
			ChatFormatting.DARK_GREEN.toString(),
			ChatFormatting.DARK_PURPLE.toString(),
			ChatFormatting.DARK_RED.toString(),
			ChatFormatting.GOLD.toString(),
			ChatFormatting.GRAY.toString(),
			ChatFormatting.GREEN.toString(),
			ChatFormatting.LIGHT_PURPLE.toString(),
			ChatFormatting.RED.toString(),
			ChatFormatting.WHITE.toString(),
			ChatFormatting.YELLOW.toString()
	};

	private int colorIndex = 0;
	private int renderCounter = 0;

	public ItemWeWand()
	{
		super(new Properties().stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
	{
		if (I18n.exists(TOOLTIP_KEY))
		{
			renderCounter--;
			if (renderCounter <= 0)
			{
				int newColorIndex = colorIndex;

				if (pLevel != null)
				{
					newColorIndex = pLevel.random.nextInt(FORMATTERS.length);
				}

				if (newColorIndex == colorIndex)
				{
					newColorIndex = (colorIndex + 1) % FORMATTERS.length;
				}
				colorIndex = newColorIndex;
				renderCounter = 15;
			}
			pTooltipComponents.add(Component.literal(FORMATTERS[colorIndex] + I18n.get(TOOLTIP_KEY)));
		}
	}

	@Override
	public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected)
	{
		if (pLevel.isClientSide())
		{
			Globals.setShowInvisibleBlocks(pIsSelected);
		}
	}
}