package de.trienow.trienowtweaks.item;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemWeWand extends BaseItem
{
	public static final TextFormatting[] FORMATTERS = TextFormatting.values();

	public ItemWeWand()
	{
		super("we_wand");
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		String text = "Has no real use. Is just decorative";
		StringBuilder formatted = new StringBuilder();

		for (char ch : text.toCharArray())
		{
			formatted.append(FORMATTERS[TrienowTweaks.rnd.nextInt(FORMATTERS.length)].toString()).append(ch);
		}

		tooltip.add(formatted.toString());
	}
}