package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.config.Globals;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author (c) trienow 2016 - 2022
 */
public class ItemWeWand extends BaseItem
{
	private static final ChatFormatting[] FORMATTERS = ChatFormatting.values();
	private static final LazyOptional<char[]> RAW_HOVER_TEXT = LazyOptional.of(() -> {
		TranslatableComponent translatableComponent = new TranslatableComponent("item.trienowtweaks.we_wand.tooltip0");
		return translatableComponent.getString().toCharArray();
	});

	public ItemWeWand()
	{
		super(new Properties().stacksTo(1));
	}

	@Override
	public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced)
	{
		StringBuilder formatted = new StringBuilder();
		for (char ch : RAW_HOVER_TEXT.orElse(this.getClass().toString().toCharArray()))
		{
			formatted.append(FORMATTERS[TrienowTweaks.rnd.nextInt(FORMATTERS.length)].toString()).append(ch);
		}

		pTooltipComponents.add(new TextComponent(formatted.toString()));
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