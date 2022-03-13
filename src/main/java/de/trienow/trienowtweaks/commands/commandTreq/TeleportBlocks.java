package de.trienow.trienowtweaks.commands.commandTreq;

import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author (c) trienow 2019 - 2022
 */
class TeleportBlocks
{
	/**
	 * Structure: MapKey -------- MapValue<List>
	 * -----------Player A blocks Player B
	 * ---------------------------Player C
	 * ---------------------------Player D
	 * -----------Player B blocks Player A
	 * ---------------------------Player D
	 */
	private final Map<String, List<String>> blocks = new HashMap<>();

	private static String getName(Player player)
	{
		return player.getName().getString();
	}

	boolean hasABlockedB(Player playerA, Player playerB)
	{
		String a = getName(playerA);
		String b = getName(playerB);

		synchronized (blocks)
		{
			return blocks.containsKey(a) && blocks.get(a).contains(b);
		}
	}

	void nowABlocksB(Player playerA, Player playerB)
	{
		String a = getName(playerA);
		String b = getName(playerB);

		synchronized (blocks)
		{
			if (blocks.containsKey(a))
			{
				List<String> blockList = blocks.get(a);
				if (!blockList.contains(b))
				{
					blockList.add(b);
				}
			}
			else
			{
				List<String> newBlockList = new ArrayList<>();
				newBlockList.add(b);
				blocks.put(a, newBlockList);
			}
		}
	}

	void nowAUnblocksB(Player playerA, Player playerB)
	{
		String a = getName(playerA);
		String b = getName(playerB);

		synchronized (blocks)
		{
			if (blocks.containsKey(a))
			{
				List<String> blockList = blocks.get(a);
				if (blockList.size() == 1)
				{
					blocks.remove(a);
				}
				else
				{
					blockList.remove(b);
				}
			}
		}
	}
}
