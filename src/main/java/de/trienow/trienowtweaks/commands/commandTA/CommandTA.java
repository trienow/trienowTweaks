package de.trienow.trienowtweaks.commands.commandTA;

import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.*;

@Mod.EventBusSubscriber
public class CommandTA extends CommandBase
{
	static byte activeTick = -100;
	final String[] allowKeywords = { "ja", "yes", "erlauben", "allow", "ok", "y", "j", "accept", "a" };
	static final Map<String, TeleportRequest> playerRequests = new HashMap<>(); // from, dest
	static final Map<String, Response> playerResponses = new HashMap<>();// dest, Response
	static Date lastDate;

	public CommandTA()
	{
		lastDate = new Date();
	}

	@Override
	public String getName()
	{
		return "ta";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return "<playername>|<accept|deny|reset> [all]";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if (sender instanceof EntityPlayerMP)
		{
			if (args.length > 0)
			{
				EntityPlayerMP player = getCommandSenderAsPlayer(sender);
				EntityPlayerMP refPlayer = parsePlayer(server, sender, args[0]);
				if (refPlayer != null)
				{
					String fromPlayer = player.getName();
					String destPlayer = refPlayer.getName();
					if (postCommandChecks(server, sender, player, refPlayer, fromPlayer, destPlayer))
					{
						if (playerResponses.get(destPlayer) == Response.allowAll)
						{
							playerRequests.put(fromPlayer, new TeleportRequest(destPlayer, true));
							sendIM(refPlayer, fromPlayer + TextFormatting.GOLD + " is teleporting to you.");
							sendIM(sender, destPlayer + TextFormatting.GREEN + " has accepted your request. Please stand by...");
						}
						else
						{
							playerRequests.put(fromPlayer, new TeleportRequest(destPlayer, false));
							sendIM(refPlayer, fromPlayer + TextFormatting.GOLD + " wants to teleport to you.\n" + TextFormatting.GOLD + "You can accept with " + TextFormatting.GREEN + "/ta accept" + TextFormatting.GOLD + ".\n" + TextFormatting.GOLD + "If you wish to accept all requests, simply type " + TextFormatting.GREEN + "/ta accept all\n" + TextFormatting.GOLD + "or to deny " + TextFormatting.RED + "/ta deny all" + TextFormatting.GOLD + ".\n" + TextFormatting.GOLD + "You have 60 sec.");
							sendIM(sender, TextFormatting.GOLD + "Your request has been sent. " + TextFormatting.WHITE + destPlayer + TextFormatting.GOLD + " has roughly 60 sec. to accept");
						}
					}
				}
				else
				{
					String playerName = player.getName();
					boolean all = false;

					try
					{
						all = args[1].trim().equalsIgnoreCase("all");
					}
					catch (Exception ignored)
					{
					}

					boolean maySingleExecute = false;
					for (String string : playerRequests.keySet())
					{
						if (playerRequests.get(string).destination.equals(playerName))
						{
							maySingleExecute = true;
							break;
						}
					}

					args[0] = args[0].trim().toLowerCase();
					if (containsString(allowKeywords, args[0]))
					{
						if (maySingleExecute || all)
							playerResponses.put(playerName, (all ? Response.allowAll : Response.allow));
						else
						{
							sendIM(sender, TextFormatting.RED + "Teleportation policy not set. You can't accept nonexistent requests. #ForeverAlone");
							return;
						}
					}
					else if (args[0].equals("reset"))
					{
						playerResponses.remove(playerName);
					}
					else
					{
						if (maySingleExecute || all)
							playerResponses.put(playerName, (all ? Response.denyAll : Response.deny));
						else
						{
							sendIM(sender, TextFormatting.RED + "Teleportation policy not set. You can't deny nonexistent requests. Do you hate people that much?");
							return;
						}
					}

					sendIM(sender, TextFormatting.GREEN + "Teleportation policy set!");
					if (all)
						sendIM(sender, TextFormatting.GOLD + "To reset your teleportation policy type " + TextFormatting.BLUE + "/ta reset" + TextFormatting.GOLD + "!");
				}
			}
			else
				sendIM(sender, TextFormatting.RED + "ERROR: Not enough arguments <playername>|<accept|deny> [all]");
		}
		else
			sendIM(sender, TextFormatting.RED + "ERROR: How in the world should I teleport you, if you aren't even a player, let alone a Minecraft entity!?");
	}

	private boolean postCommandChecks(MinecraftServer server, ICommandSender sender, EntityPlayerMP player, EntityPlayerMP refPlayer, String fromPlayer, String destPlayer)
	{
		if (fromPlayer.equals(destPlayer))
		{
			sendIM(sender, TextFormatting.RED + "ERROR: You can not teleport to yourself. LOL. N00BS. Come @ me bro! You ain't got shit!!!11!!!!!1");
			return false;
		}

		if (player.dimension != refPlayer.dimension)
		{
			sendIM(sender, TextFormatting.RED + "ERROR: " + fromPlayer + " is in a different dimension than " + destPlayer);
			return false;
		}

		if (playerResponses.get(destPlayer) == Response.denyAll)
		{
			sendIM(sender, TextFormatting.RED + "ERROR: " + destPlayer + " has denied all teleportation requests.");
			return false;
		}

		if (playerRequests.get(fromPlayer) != null && playerRequests.get(fromPlayer).destination.equals(destPlayer))
		{
			sendIM(sender, TextFormatting.RED + "ERROR: Stop Spamming me with the same request over and over. IT WON'T MAKE THE PROCESS GO ANY FASTER!!!!");
			return false;
		}

		for (String string : playerRequests.keySet())
		{
			if (playerRequests.get(string).destination.equals(destPlayer))
			{
				sendIM(sender, TextFormatting.RED + "ERROR: " + destPlayer + " is currently teleporting somewhere. Please ask again later.");
				return false;
			}
		}

		if (playerRequests.containsKey(fromPlayer))
		{
			sendIM(sender, TextFormatting.YELLOW + "WARNING: Canceling request to " + playerRequests.get(fromPlayer).destination + ".");
			EntityPlayerMP prevPlayer = parsePlayer(server, sender, playerRequests.get(fromPlayer).destination);
			if (prevPlayer != null)
				sendIM(prevPlayer, TextFormatting.GRAY + "INFO: " + fromPlayer + " has canceled their request.");
			playerRequests.remove(fromPlayer);
		}
		return true;
	}

	public static void onWorldTickEvt(TickEvent.WorldTickEvent evt)
	{
		if (!evt.world.isRemote)
		{
			if (activeTick >= 100)
			{
				activeTick = -100;

				if (((new Date().getTime()) - lastDate.getTime()) >= 5000)
				{
					lastDate = new Date();

					List<String> toBeRemoved = new ArrayList<>();
					for (String name : playerRequests.keySet())
					{
						TeleportRequest tr = playerRequests.get(name);
						tr.time++;

						if (tr.hasAccepted && tr.time >= 0)
						{
							teleport(toBeRemoved, name, tr, evt.world.getMinecraftServer());
						}

						switch (playerResponses.getOrDefault(tr.destination, Response.none))
						{
							case allow:
								playerResponses.remove(tr.destination);
							case allowAll:
								preTeleport(name, tr, evt.world.getMinecraftServer());
								break;

							case deny:
								playerResponses.remove(tr.destination);
							case denyAll:
								denyRequest(toBeRemoved, name, tr, evt.world.getMinecraftServer());
								break;
							default:
								break;
						}

						if (tr.time >= 0 && !toBeRemoved.contains(name))
						{
							denyRequest(toBeRemoved, name, tr, evt.world.getMinecraftServer());
						}
					}

					for (String string : toBeRemoved)
					{
						playerRequests.remove(string);
					}
				}
			}
			else
				activeTick++;
		}
	}

	private static boolean preTeleportChecks(String name, TeleportRequest tr, MinecraftServer server)
	{
		EntityPlayerMP fromPlayer = parsePlayer(server, server, name);
		EntityPlayerMP destPlayer = parsePlayer(server, server, tr.destination);

		if (fromPlayer == null || destPlayer == null)
		{
			if (fromPlayer != null)
				sendIM(fromPlayer, TextFormatting.RED + "ERROR: " + tr.destination + " does not exist anymore.");

			if (destPlayer != null)
				sendIM(destPlayer, TextFormatting.GRAY + "INFO: " + name + " will not teleport to you, since they do not exist anymore.");
			return false;
		}

		if (fromPlayer.dimension != destPlayer.dimension)
		{
			sendIM(fromPlayer, TextFormatting.RED + "ERROR: You are in a different dimension than " + destPlayer + "Teleportation canceled");
			sendIM(destPlayer, TextFormatting.GRAY + "INFO: " + fromPlayer + " is in a different dimension than you. They will not teleport to you.");
			return false;
		}
		return true;
	}

	private static void teleport(List<String> toBeRemoved, String name, TeleportRequest tr, MinecraftServer server)
	{
		toBeRemoved.add(name);
		if (!preTeleportChecks(name, tr, server))
			return;

		EntityPlayerMP fromPlayer = parsePlayer(server, server, name);
		EntityPlayerMP destPlayer = parsePlayer(server, server, tr.destination);

		if (fromPlayer != null && destPlayer != null)
		{
			TrienowTweaks.logger.info("[TA] Ready to teleport " + name + " to " + tr.destination);
			fromPlayer.setPositionAndUpdate(destPlayer.posX, destPlayer.posY, destPlayer.posZ);
			sendIM(fromPlayer, TextFormatting.GREEN + "Whoooosh!");
			sendIM(destPlayer, TextFormatting.GREEN + "Whoooosh!");
		}
	}

	private static void preTeleport(String name, TeleportRequest tr, MinecraftServer server)
	{
		if (preTeleportChecks(name, tr, server))
		{
			EntityPlayerMP fromPlayer = parsePlayer(server, server, name);
			EntityPlayerMP destPlayer = parsePlayer(server, server, tr.destination);

			tr.confirm();

			sendIM(fromPlayer, TextFormatting.GOLD + "Please wait 5 seconds.");
			sendIM(destPlayer, TextFormatting.GOLD + "Please wait 5 seconds.");
		}
	}

	private static void denyRequest(List<String> toBeRemoved, String name, TeleportRequest tr, MinecraftServer server)
	{
		toBeRemoved.add(name);
		EntityPlayerMP fromPlayer = parsePlayer(server, server, name);
		EntityPlayerMP destPlayer = parsePlayer(server, server, tr.destination);

		if (fromPlayer != null)
			sendIM(fromPlayer, TextFormatting.RED + "ERROR: " + tr.destination + " either denied your request or did not answer.");

		if (destPlayer != null)
			sendIM(destPlayer, TextFormatting.GRAY + "INFO: Since you did not answer or denied the request, " + name + " will not teleport to you.");
	}

	private static EntityPlayerMP parsePlayer(MinecraftServer server, ICommandSender sender, String name)
	{
		try
		{
			return getPlayer(server, sender, name);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	private static void sendIM(ICommandSender sender, String text)
	{
		sendIM(sender, new TextComponentString(text));
	}

	private static void sendIM(ICommandSender sender, TextComponentString text)
	{
		sender.sendMessage(new TextComponentString(text.getFormattedText() + TextFormatting.RESET));
	}

	private boolean containsString(String[] array, String search)
	{
		for (String string : array)
		{
			if (string.equals(search))
				return true;
		}
		return false;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
	{
		return true;
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
	{
		List<String> tabList = new ArrayList<>();
		tabList.addAll(Arrays.asList("accept", "deny", "reset"));
		tabList.addAll(Arrays.asList(server.getOnlinePlayerNames()));
		return getListOfStringsMatchingLastWord(args, tabList);
	}
}
