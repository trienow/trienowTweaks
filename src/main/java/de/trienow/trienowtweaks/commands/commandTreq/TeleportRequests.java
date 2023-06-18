package de.trienow.trienowtweaks.commands.commandTreq;

import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.*;

/**
 * @author trienow 2019 - 2023
 */
class TeleportRequests
{
	private static final String TRANSLATION_KEY = "cmd." + TrienowTweaks.MODID + ".treq.";
	private final Map<String, TeleportRequest> PLAYER_REQUESTS = new HashMap<>(); // from, dest
	private final TeleportBlocks BLOCKS = new TeleportBlocks();

	private static String getPlayerName(Player player)
	{
		return player.getName().getString();
	}

	private boolean newRequestChecks(Player fromPlayer, Player toPlayer)
	{
		boolean checksOk = true;
		final String translation_key = TRANSLATION_KEY + "to.fail.";
		final String toPlayerName = getPlayerName(toPlayer);

		if (fromPlayer.equals(toPlayer))
		{
			CommandUtils.sendIm(fromPlayer, translation_key + "self");
			checksOk = false;
		}

		if (checksOk && BLOCKS.hasABlockedB(toPlayer, fromPlayer))
		{
			CommandUtils.sendIm(fromPlayer, translation_key + "blocked", toPlayerName);
			checksOk = false;
		}

		if (checksOk && PLAYER_REQUESTS.containsKey(getPlayerName(fromPlayer)))
		{
			String currentlyPortingTo = PLAYER_REQUESTS.get(getPlayerName(fromPlayer)).getDestination();
			CommandUtils.sendIm(fromPlayer, translation_key + "duplicate", currentlyPortingTo);
			checksOk = false;
		}

		checksOk = checksOk && preTeleportChecks(fromPlayer, toPlayer, toPlayerName);

		if (checksOk)
		{
			for (String fromName : PLAYER_REQUESTS.keySet())
			{
				if (fromName.equals(toPlayerName))
				{
					CommandUtils.sendIm(fromPlayer, translation_key + "in_progress", toPlayerName);
					checksOk = false;
					break;
				}
			}
		}

		return checksOk;
	}

	/**
	 * @param fromPlayer The command executor, from which the teleport request is issued
	 * @param toPlayer   The destination player
	 * @return 1, because success?
	 */
	synchronized int newRequest(Player fromPlayer, Player toPlayer)
	{
		int result = 0;

		if (newRequestChecks(fromPlayer, toPlayer))
		{
			String fromPlayerName = getPlayerName(fromPlayer);
			String toPlayerName = getPlayerName(toPlayer);
			TeleportRequest request = new TeleportRequest(toPlayerName);

			PLAYER_REQUESTS.put(getPlayerName(fromPlayer), request);
			CommandUtils.sendIm(toPlayer, TRANSLATION_KEY + "to.requesting", fromPlayerName);
			CommandUtils.sendIm(fromPlayer, TRANSLATION_KEY + "to.requested", toPlayerName);
			result = 1;
		}

		return result;
	}

	/**
	 * @param acceptor          The command executor
	 * @param acceptFromPlayers The players from which teleport requests are allowed
	 * @return 1, because success?
	 */
	synchronized int accept(Player acceptor, Collection<? extends Player> acceptFromPlayers)
	{
		String acceptorName = getPlayerName(acceptor);
		boolean acceptedAtLeastOne = false;
		for (Player acceptFromPlayer : acceptFromPlayers)
		{
			String acceptFromPlayerName = getPlayerName(acceptFromPlayer);
			if (PLAYER_REQUESTS.containsKey(acceptFromPlayerName))
			{
				TeleportRequest request = PLAYER_REQUESTS.get(acceptFromPlayerName);
				if (request.getDestination().equals(acceptorName))
				{
					request.acceptRequest();
					acceptedAtLeastOne = true;
					CommandUtils.sendIm(acceptor, TRANSLATION_KEY + "accept.acceptor", acceptFromPlayerName);
					CommandUtils.sendIm(acceptFromPlayer, TRANSLATION_KEY + "accept.accept_from", acceptorName);
				}
			}
		}

		if (!acceptedAtLeastOne)
		{
			CommandUtils.sendIm(acceptor, TRANSLATION_KEY + "accept.none");
		}

		return 1;
	}

	private boolean preTeleportChecks(@Nullable Player fromPlayer, @Nullable Player toPlayer, String toPlayerName)
	{
		boolean checksOk = true;
		final String translation_key = TRANSLATION_KEY + "teleport.fail.";

		if (fromPlayer == null)
		{
			checksOk = false;
		}

		if (checksOk && toPlayer == null)
		{
			CommandUtils.sendIm(fromPlayer, translation_key + "missing_destination", toPlayerName);
			checksOk = false;
		}

		//For when dimensional teleporting is not fixed yet
		//.if (checksOk && !fromPlayer.level.equals(toPlayer.level))
		//.{
		//.CommandUtils.sendIm(fromPlayer, translation_key + "dimension", toPlayerName);
		//.checksOk = false;
		//.}

		return checksOk;
	}

	synchronized void cleanUpRequests(PlayerList onlinePlayers)
	{
		List<String> removeRequests = new ArrayList<>();
		for (String fromPlayerName : PLAYER_REQUESTS.keySet())
		{
			TeleportRequest request = PLAYER_REQUESTS.get(fromPlayerName);
			if (request.isTimeUp())
			{
				// Check and see if the player can be teleported
				String toPlayerName = request.getDestination();
				Player fromPlayer = onlinePlayers.getPlayerByName(fromPlayerName);
				if (request.shouldExecuteTeleport())
				{
					// Let's try to teleport...
					ServerPlayer toPlayer = onlinePlayers.getPlayerByName(toPlayerName);

					if (preTeleportChecks(fromPlayer, toPlayer, toPlayerName))
					{
						CommandUtils.sendIm(fromPlayer, TRANSLATION_KEY + "teleport.teleporting");
						CommandUtils.sendIm(toPlayer, TRANSLATION_KEY + "teleport.teleporting");
						Vec3 pos = toPlayer.position();

						fromPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 60, 255, true, false));
						if (fromPlayer.level().equals(toPlayer.level()))
						{
							fromPlayer.teleportTo(pos.x, pos.y + 0.5, pos.z);
						}
						else
						{
							fromPlayer.changeDimension(toPlayer.serverLevel(), new XDimTeleporter(pos));
						}
					}
				}
				else if (fromPlayer != null)
				{
					// Notification of ignorance
					CommandUtils.sendIm(fromPlayer, TRANSLATION_KEY + "ignored", toPlayerName);
				}
				removeRequests.add(fromPlayerName);
			}
		}

		for (String fromPlayerName : removeRequests)
		{
			PLAYER_REQUESTS.remove(fromPlayerName);
		}
	}

	/**
	 * @param sender  The command executor
	 * @param targets Players to block
	 * @return Something
	 */
	int block(Player sender, Collection<? extends Player> targets)
	{
		for (Player target : targets)
		{
			CommandUtils.sendIm(sender, TRANSLATION_KEY + "block", getPlayerName(target));
			BLOCKS.nowABlocksB(sender, target);
		}

		return 1;
	}

	/**
	 * @param sender  The command executor
	 * @param targets Players to unblock
	 * @return Something
	 */
	int unblock(Player sender, Collection<? extends Player> targets)
	{
		for (Player target : targets)
		{
			if (target != sender)
			{
				CommandUtils.sendIm(sender, TRANSLATION_KEY + "unblock", getPlayerName(target));
				BLOCKS.nowAUnblocksB(sender, target);
			}
		}
		return 1;
	}
}
