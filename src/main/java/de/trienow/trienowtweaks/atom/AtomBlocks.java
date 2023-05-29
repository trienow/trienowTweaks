package de.trienow.trienowtweaks.atom;

import de.trienow.trienowtweaks.blocks.*;
import de.trienow.trienowtweaks.blocks.compactCrafter.BlockCompactCrafter;
import de.trienow.trienowtweaks.main.StateList;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * @author trienow
 */
public class AtomBlocks
{
	@GameRegistry.ObjectHolder("trienowtweaks:fake_fire")
	public static final BlockFakeFire blockFakeFire = new BlockFakeFire();
	@GameRegistry.ObjectHolder("trienowtweaks:torch_solamnia")
	public static final BlockTorchSolamnia blockTorchSolamnia = new BlockTorchSolamnia();
	@GameRegistry.ObjectHolder("trienowtweaks:generic_light")
	public static final BlockGenericLight blockGenericLight = new BlockGenericLight();
	@GameRegistry.ObjectHolder("trienowtweaks:invisible_wall")
	public static final BlockInvisibleWall blockInvisibleWall = new BlockInvisibleWall();

	@GameRegistry.ObjectHolder("trienowtweaks:railroad_truss_wooden")
	public static final BlockRailroadTrussWooden blockRailroadTrussWooden = new BlockRailroadTrussWooden();
	@GameRegistry.ObjectHolder("trienowtweaks:railroad_truss_black")
	public static final BlockRailroadTrussBlack blockRailroadTrussBlack = new BlockRailroadTrussBlack();
	@GameRegistry.ObjectHolder("trienowtweaks:railroad_truss_bright")
	public static final BlockRailroadTrussBright blockRailroadTrussBright = new BlockRailroadTrussBright();
	@GameRegistry.ObjectHolder("trienowtweaks:railroad_truss_purple")
	public static final BlockRailroadTrussPurple blockRailroadTrussPurple = new BlockRailroadTrussPurple();

	@GameRegistry.ObjectHolder("trienowtweaks:item_detector")
	public static final BlockTEItemDetector blockTEItemDetector = new BlockTEItemDetector();
	@GameRegistry.ObjectHolder("trienowtweaks:compact_crafter")
	public static final BlockCompactCrafter blockCompactCrafter = new BlockCompactCrafter();

	/* Not available for now
	@GameRegistry.ObjectHolder("trienowtweaks:apo_refiller")
	public static final BlockTEApothecaryRefiller blockApothecaryRefiller = new BlockTEApothecaryRefiller();
	*/

	@GameRegistry.ObjectHolder("trienowtweaks:minecart_killer")
	public static final BlockMinecartKiller blockTEMinecartKiller = new BlockMinecartKiller();
	@GameRegistry.ObjectHolder("trienowtweaks:entity_prohibitator")
	public static final BlockEntityProhibitator blockEntityProhibitator = new BlockEntityProhibitator();

	@GameRegistry.ObjectHolder("trienowtweaks:streetlamp")
	public static final BlockStreetlamp blockStreetlamp = new BlockStreetlamp();

	@GameRegistry.ObjectHolder("trienowtweaks:generic_light_te")
	public static final BlockTEGenericLight blockTEGenericLight = new BlockTEGenericLight();

	/**
	 * On register, these Blocks will register themselves and their respective single Item
	 */
	public static final BaseBlock[] blockList = {
			blockCompactCrafter,
			blockEntityProhibitator,
			blockFakeFire,
			blockGenericLight,
			blockInvisibleWall,
			blockRailroadTrussBlack,
			blockRailroadTrussBright,
			blockRailroadTrussPurple,
			blockRailroadTrussWooden,
			blockTEGenericLight,
			blockTorchSolamnia,
			blockTEItemDetector,
			blockTEMinecartKiller
	};

	/**
	 * On register, these Blocks will register their Items with a flag, that subtypes are present
	 */
	public static final BaseItemBlock[] subtypeBlockList = {
			blockStreetlamp
	};

	public static final StateList TRUSS =
			new StateList(new IBlockState[] { blockRailroadTrussBlack.getDefaultState(), blockRailroadTrussBright.getDefaultState(), blockRailroadTrussPurple.getDefaultState(),
					blockRailroadTrussWooden.getDefaultState() });

	/*
	public static final BaseBlock[] blockListBotania = {
			blockApothecaryRefiller
	};
	 */

	/**
	 * Initializes the Block Models
	 */
	public static void initModels()
	{
		for (BaseBlock baseBlock : blockList)
		{
			baseBlock.initModel();
		}

		/*
		for (BaseBlock baseBlock : blockListBotania)
		{
			baseBlock.initModel();
		}
		 */

		for (BaseBlock baseBlock : subtypeBlockList)
		{
			baseBlock.initModel();
		}
	}
}
