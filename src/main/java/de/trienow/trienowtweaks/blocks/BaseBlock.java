package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

public abstract class BaseBlock extends Block
{
	public BaseBlock(String name, Material materialIn, boolean showInCreativeTab)
	{
		super(materialIn);
		setUnlocalizedName(TrienowTweaks.MODID + "." + name);
		setRegistryName(name);

		if (showInCreativeTab)
			setCreativeTab(TrienowTweaks.trienowTab);
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return 3.5f;
	}

	@Override
	public Block setResistance(float resistance)
	{
		return super.setResistance(5.0f);
	}

	@SideOnly(Side.CLIENT)
	public void initModel()
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this), 0, new ModelResourceLocation(Objects.requireNonNull(getRegistryName()), "inventory"));
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return true;
	}

	@Override
	public boolean canBeConnectedTo(IBlockAccess world, BlockPos pos, EnumFacing facing)
	{
		return isSideSolid(getDefaultState(), world, pos, facing);
	}

	@Override
	public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
	{
		return canBeConnectedTo(p_193383_1_, p_193383_3_, p_193383_4_) ? BlockFaceShape.SOLID : BlockFaceShape.UNDEFINED;
	}
}
