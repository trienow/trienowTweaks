package de.trienow.trienowtweaks.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockFakeFire extends BaseBlock
{
	public BlockFakeFire()
	{
		super("fake_fire", new MaterialTransparent(MapColor.YELLOW), true);
		lightOpacity = 0;
		lightValue = 15;
		blockSoundType = SoundType.PLANT;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return new AxisAlignedBB(0.01D, 0D, 0.01D, 0.99999999D, 0.01D, 0.99D);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	public boolean isReplaceable(IBlockAccess worldIn, BlockPos pos)
	{
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
	{
		if (rand.nextInt(50) == 1)
			worldIn.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 0.5f, rand.nextFloat(), true);

		if (rand.nextInt(10) == 1)
		{
			double rnd1 = rand.nextDouble();
			double rnd4 = rand.nextDouble();
			double rnd5 = rand.nextDouble();
			rnd1 = rnd1 < 0.2D ? rnd1 : 0.1D;
			worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, false, (pos.getX() + rnd4), pos.getY(), (pos.getZ() + rnd5), 0, rnd1, 0, 0);
		}
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return 0.1f;
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GOLD + "Fire without it's properties...");
	}
}
