package de.trienow.trienowtweaks.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class BlockMinecartKiller extends BaseBlock
{
	public BlockMinecartKiller()
	{
		super("minecart_killer", Material.ROCK, true);
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		AxisAlignedBB checkBox = FULL_BLOCK_AABB.offset(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));
		if (!worldIn.isRemote && worldIn.isBlockIndirectlyGettingPowered(pos) > 0)
		{
			List<Entity> ents = worldIn.getEntitiesWithinAABB(EntityMinecart.class, checkBox);

			for (Entity entity : ents)
			{
				entity.attackEntityFrom(DamageSource.GENERIC, Float.MAX_VALUE);
			}
		}
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.DARK_AQUA + "Will kill the Minecart above it,");
		tooltip.add(TextFormatting.DARK_AQUA + "when it receives a " + TextFormatting.RED + TextFormatting.ITALIC + "redstone " + TextFormatting.RESET + TextFormatting.DARK_AQUA + "signal!");
	}
}
