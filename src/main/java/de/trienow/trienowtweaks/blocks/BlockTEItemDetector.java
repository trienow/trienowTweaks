package de.trienow.trienowtweaks.blocks;

import de.trienow.trienowtweaks.tiles.TEItemDetector;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockTEItemDetector extends BaseBlock implements ITileEntityProvider
{
	public static final PropertyBool ACTIVE = PropertyBool.create("active");

	public BlockTEItemDetector()
	{
		super("item_detector", Material.ROCK, true);
		setDefaultState(blockState.getBaseState().withProperty(ACTIVE, Boolean.FALSE));
	}

	@Override
	public int tickRate(World worldIn)
	{
		return 20;
	}

	@Override
	public boolean canProvidePower(IBlockState state)
	{
		return true;
	}

	@Override
	public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return blockState.getValue(ACTIVE) ? 15 : 0;
	}

	@Override
	public int getStrongPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		return blockState.getValue(ACTIVE) ? 15 : 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(ACTIVE, meta == 1);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(ACTIVE) ? 1 : 0;
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, ACTIVE);
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
	{
		return Item.getItemFromBlock(this);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	{
		tooltip.add(TextFormatting.GREEN + "Right-click: increase detection amount by 1");
		tooltip.add(TextFormatting.RED + "Sneak-right-click: decrease detection amount by 10");
		tooltip.add(TextFormatting.BLUE + "Will kill half of the items, when more than 10.000 Item Entities detected!" + TextFormatting.RESET);
	}

	// MECHANICS
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
	{
		worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
		super.onBlockAdded(worldIn, pos, state);
	}

	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand)
	{
		if (!worldIn.isRemote)
		{
			AxisAlignedBB checkBox = FULL_BLOCK_AABB.offset(new BlockPos(pos.getX(), pos.getY() + 1, pos.getZ()));
			List<Entity> ents = worldIn.getEntitiesWithinAABB(EntityItem.class, checkBox);
			int entityItemCount = ents.size();

			if (entityItemCount > 10000)
			{
				for (int i = 0; i < entityItemCount - 10000; i++)
				{
					ents.get(i).setDead();
				}
			}

			TileEntity teWorld = worldIn.getTileEntity(pos);
			if (teWorld instanceof TEItemDetector)
			{
				worldIn.scheduleUpdate(pos, this, tickRate(worldIn));
				TEItemDetector te = (TEItemDetector) teWorld;
				if (te.amt > 0)
				{
					Boolean initialState = state.getValue(ACTIVE);
					boolean newState = (entityItemCount >= te.amt);

					if (newState != initialState)
					{
						state = state.withProperty(ACTIVE, newState);

						int teAmt = te.amt;
						worldIn.setBlockState(pos, state, 2);
						worldIn.setTileEntity(pos, new TEItemDetector(teAmt));

						worldIn.notifyNeighborsOfStateChange(pos, this, true);
						worldIn.markBlockRangeForRenderUpdate(pos, pos);
					}
				}
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntity teWorld = worldIn.getTileEntity(pos);
		if (!worldIn.isRemote && teWorld instanceof TEItemDetector)
		{
			TEItemDetector te = (TEItemDetector) teWorld;
			te.amt += playerIn.isSneaking() ? -10 : 1;

			if (te.amt > 100)
				te.amt = 100;
			if (te.amt < 1)
				te.amt = 1;

			Entity senderE = playerIn.getCommandSenderEntity();
			if (senderE != null)
				((ICommandSender) senderE).sendMessage(new TextComponentString("Item Entities to detect: " + te.amt));
		}

		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new TEItemDetector(1);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
	{
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}
}
