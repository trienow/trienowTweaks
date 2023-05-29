package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.tiles.TEFluidHolder;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * @author (c) trienow 2018
 */
public class BlockTEApothecaryRefiller extends BaseBlock implements ITileEntityProvider
{
	/**
	 * Constructs an object of type BlockTEApothecaryRefiller.java
	 */
	public BlockTEApothecaryRefiller()
	{
		super("apothecary_refiller", Material.ROCK, true);
	}

	@Override
	public int tickRate(World worldIn)
	{
		return 200;
	}

	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		super.onBlockAdded(worldIn, pos, state);
		worldIn.setTileEntity(pos, this.createNewTileEntity(worldIn, 0));
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TEFluidHolder();
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced)
	{
		tooltip.add("" + TextFormatting.AQUA + "Refills the Petal Apothecary from Botania with water.");
		tooltip.add("" + TextFormatting.DARK_AQUA + "Will accept water from pipes, ducts and trucks!");
		tooltip.add("" + TextFormatting.BLUE + TextFormatting.ITALIC + "Waila " + TextFormatting.BLUE + "will tell you how much it has stored in it's buffer.");
		super.addInformation(stack, player, tooltip, advanced);
	}
}
