package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.config.Globals;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

/**
 * @author (c) trienow 2016 - 2023
 */
public class ItemWeWand extends Item
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

	public ItemWeWand(ResourceLocation registryName)
	{
		super(new Properties().stacksTo(1).setId(ResourceKey.create(Registries.ITEM, registryName)));
	}

	@Override public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag)
	{
		if (I18n.exists(TOOLTIP_KEY))
		{
			renderCounter--;
			if (renderCounter <= 0)
			{
				int newColorIndex = colorIndex;

				if (context.level() != null)
				{
					newColorIndex = context.level().random.nextInt(FORMATTERS.length);
				}

				if (newColorIndex == colorIndex)
				{
					newColorIndex = (colorIndex + 1) % FORMATTERS.length;
				}
				colorIndex = newColorIndex;
				renderCounter = 15;
			}
			tooltipAdder.accept(Component.literal(FORMATTERS[colorIndex] + I18n.get(TOOLTIP_KEY)));
		}
	}

	@Override public InteractionResult use(Level level, Player player, InteractionHand hand)
	{
		if (level.isClientSide())
		{
			Globals.setShowInvisibleBlocks(!Globals.showInvisibleBlocks());
		}
		return InteractionResult.SUCCESS;
	}
}