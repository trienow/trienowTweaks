package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.blocks.BaseItemBlock;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

/**
 * @author trienow 2017
 */
public class AtomItemBlock extends ItemBlock
{
	final BaseItemBlock baseItemBlock;

	/**
	 * Constructs an object of type AtomItemBlock.java
	 */
	public AtomItemBlock(BaseItemBlock block)
	{
		super(block);
		this.baseItemBlock = block;
		setHasSubtypes(true);
		setRegistryName(Objects.requireNonNull(block.getRegistryName()));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(ItemStack stack)
	{
		return baseItemBlock.getDisplayName(stack);
	}
}
