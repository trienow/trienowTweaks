package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * @author trienow 2017
 */
public class BlockStreetlamp extends BaseItemBlock
{
	public static final PropertyEnum<ENasgo> NASGO = PropertyEnum.create("nasgo", BlockStreetlamp.ENasgo.class);

	/**
	 * Constructs an object of type BlockStreetlamp.java
	 */
	public BlockStreetlamp()
	{
		super("streetlamp", Material.ANVIL, true);
		setDefaultState(blockState.getBaseState().withProperty(NASGO, ENasgo.TOP_FIRE));
		lightOpacity = 0;
		lightValue = 15;
	}

	// Properties
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}

	@Override
	public BlockRenderLayer getBlockLayer()
	{
		return BlockRenderLayer.CUTOUT_MIPPED;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side)
	{
		return false;
	}

	@Override
	public float getBlockHardness(IBlockState blockState, World worldIn, BlockPos pos)
	{
		return 5.0f;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return new AxisAlignedBB(0.375, 0.0, 0.375, 0.625, 1.0, 0.625);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return new AxisAlignedBB(0.1875, 0.0, 0.1875, 0.8125, 1.0, 0.8125);
	}

	// Placement and Removal
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
	{
		ENasgo nasgo = state.getValue(NASGO);
		//@fm:off
		if (nasgo.isTopPart() && fromPos.getY() < pos.getY() ||
			!nasgo.isBottomPart() && !nasgo.isTopPart() && (fromPos.getY() > pos.getY() || fromPos.getY() < pos.getY()) ||
			nasgo.isBottomPart() && fromPos.getY() > pos.getY())
		{
			if (worldIn.getBlockState(fromPos).getBlock() != this)
			{
				worldIn.setBlockToAir(pos);

				if (!worldIn.isRemote && nasgo.isTopPart())
					worldIn.spawnEntity(new EntityItem(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(blockIn, 1, nasgo.getMetadata())));
			}
		}
		//@fm:on
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		if (state.getValue(NASGO).isTopPart())
			return Item.getItemFromBlock(this);
		else
			return null;
	}

	@Override
	public int damageDropped(IBlockState state)
	{
		ENasgo nasgo = state.getValue(NASGO);
		int meta;
		if (TrienowTweaks.rnd.nextDouble() < 0.01)
			meta = ENasgo.TOP_FLESH.getMetadata();
		else
			meta = nasgo.getMetadata();

		return meta;
	}

	@Override
	public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand)
	{
		IBlockState top;
		IBlockState middle = getDefaultState().withProperty(NASGO, ENasgo.MIDDLE_METAL);
		IBlockState bottom = getDefaultState().withProperty(NASGO, ENasgo.BOTTOM_METAL);

		meta = placer.getHeldItem(hand).getMetadata();

		if (meta == ENasgo.TOP_FIRE.getMetadata())
		{
			top = getDefaultState().withProperty(NASGO, ENasgo.TOP_FIRE);
		}
		else if (meta == ENasgo.TOP_GLOWSTONE.getMetadata())
		{
			top = getDefaultState().withProperty(NASGO, ENasgo.TOP_GLOWSTONE);
		}
		else if (meta == ENasgo.TOP_FLESH.getMetadata())
		{
			top = getDefaultState().withProperty(NASGO, ENasgo.TOP_FLESH);
			middle = getDefaultState().withProperty(NASGO, ENasgo.MIDDLE_FLESH);
			bottom = getDefaultState().withProperty(NASGO, ENasgo.BOTTOM_FLESH);
		}
		else
			return super.getStateForPlacement(world, pos, facing, hitX, hitY, hitZ, meta, placer, hand);

		world.setBlockState(pos.add(0, 1, 0), middle);
		world.setBlockState(pos.add(0, 2, 0), top);

		return bottom;
	}

	// Sub Blocks
	@Override
	public String getDisplayName(ItemStack stack)
	{
		int meta = stack.getMetadata();
		String unlocalizedName = stack.getUnlocalizedName();

		unlocalizedName += "." + ENasgo.byMetadata(meta).getName() + ".name";

		return I18n.format(unlocalizedName);
	}

	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced)
	{
		ENasgo part = ENasgo.byMetadata(stack.getMetadata());
		String localized = null;

		if (part.isTopPart())
		{
			if (part == ENasgo.TOP_FLESH)
				localized = TextFormatting.LIGHT_PURPLE + "Gives off " + TextFormatting.ITALIC + "fleshy" + TextFormatting.RESET + TextFormatting.LIGHT_PURPLE + " light ;P";
			else if (part == ENasgo.TOP_FIRE)
				localized = TextFormatting.GOLD + "Gives off light";
			else if (part == ENasgo.TOP_GLOWSTONE)
				localized = TextFormatting.YELLOW + "Gives off light";
		}

		if (localized != null)
			tooltip.add(localized);
	}

	@Override
	public void initModel()
	{
		initModel(ENasgo.TOP_FIRE.getMetadata(), "top_fire");
		initModel(ENasgo.TOP_FLESH.getMetadata(), "top_flesh");
		initModel(ENasgo.TOP_GLOWSTONE.getMetadata(), "top_glowstone");
	}

	@Override
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
	{
		// super.getSubBlocks(itemIn, items);
		items.add(new ItemStack(this, 1, ENasgo.TOP_FIRE.getMetadata()));
		items.add(new ItemStack(this, 1, ENasgo.TOP_GLOWSTONE.getMetadata()));
		items.add(new ItemStack(this, 1, ENasgo.TOP_FLESH.getMetadata()));
	}

	// Blockstates
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(NASGO, ENasgo.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(NASGO).getMetadata();
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, NASGO);
	}

	// The Enum
	public enum ENasgo implements IStringSerializable
	{
		TOP_FIRE(0, "top_fire"),
		TOP_GLOWSTONE(1, "top_glowstone"),
		TOP_FLESH(2, "top_flesh"),
		MIDDLE_METAL(3, "middle_metal"),
		BOTTOM_METAL(4, "bottom_metal"),
		MIDDLE_FLESH(5, "middle_flesh"),
		BOTTOM_FLESH(6, "bottom_flesh");

		private static final BlockStreetlamp.ENasgo[] META_LOOKUP = new BlockStreetlamp.ENasgo[values().length];
		private static final BlockStreetlamp.ENasgo[] TOP_PARTS = { TOP_FIRE, TOP_FLESH, TOP_GLOWSTONE };
		private static final BlockStreetlamp.ENasgo[] BOTTOM_PARTS = { BOTTOM_METAL, BOTTOM_FLESH };
		private final int meta;
		private final String name;

		ENasgo(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}

		@Override
		public String getName()
		{
			return name;
		}

		public int getMetadata()
		{
			return meta;
		}

		public static BlockStreetlamp.ENasgo byMetadata(int meta)
		{
			if (meta < 0 || meta >= META_LOOKUP.length)
			{
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public boolean isTopPart()
		{
			for (BlockStreetlamp.ENasgo part : TOP_PARTS)
			{
				if (part == this)
					return true;
			}

			return false;
		}

		public boolean isBottomPart()
		{
			for (BlockStreetlamp.ENasgo part : BOTTOM_PARTS)
			{
				if (part == this)
					return true;
			}

			return false;
		}

		@Override
		public String toString()
		{
			return name;
		}

		static
		{
			for (BlockStreetlamp.ENasgo brted : values())
				META_LOOKUP[brted.getMetadata()] = brted;
		}
	}

}
