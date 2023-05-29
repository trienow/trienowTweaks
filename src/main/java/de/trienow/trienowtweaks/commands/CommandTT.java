package de.trienow.trienowtweaks.commands;

import de.trienow.trienowtweaks.main.Generic;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.IChunkGenerator;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author (c) trienow 2016 - 2022
 */
public class CommandTT extends CommandBase
{
	private static final Map<String, String> subCommands = new TreeMap<String, String>()
	{
		{
			put("addMotion", "<PlayerName> <x> <y> <z> <AbsorptionHearts>");
			put("heal", "[value]");
			put("help", "<subCommand>");
			put("random", "[PlayerName]");
			put("setAbsorption", "[PlayerName] <AbsorptionHearts>");
			put("spawn", "[PlayerName]");
		}
	};

	@Override
	public String getName()
	{
		return "tt";
	}

	@Override
	public String getUsage(ICommandSender sender)
	{
		return new TextComponentString(TextFormatting.GREEN + "/tt" + TextFormatting.YELLOW + " [subCommand]").getFormattedText();
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args)
	{
		if (args.length > 0)
		{
			int offset = 0;
			EntityPlayer player = null;

			if (sender instanceof EntityPlayer)
				player = (EntityPlayer) sender;

			if (args.length > 1 && !NumberUtils.isCreatable(args[1]))
			{
				if (args[1].startsWith("@"))
				{
					String selector = args[1].toLowerCase();
					if (selector.endsWith("p"))
					{
						BlockPos pos = sender.getPosition();
						player = server.getEntityWorld().getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 32, false);
						if (player != null)
						{
							args[1] = player.getDisplayNameString();
						}
					}
					else
					{
						return;
					}
				}
				else
				{
					Map<String, String> players = new HashMap<>();
					for (String string : server.getPlayerList().getOnlinePlayerNames())
					{
						players.put(string.toLowerCase(), string);
					}

					if (players.get(args[1].toLowerCase()) != null)
					{
						player = server.getPlayerList().getPlayerByUsername(players.get(args[1].toLowerCase()));
						offset = 1;
					}
				}
			}

			String subCommandUser = args[0].trim().toLowerCase();

			if (player != null)
				TrienowTweaks.logger.info("[TT] Attempting to execute <" + subCommandUser + "> from <" + sender.getName() + "> on <" + player.getDisplayNameString() + ">");
			else
				TrienowTweaks.logger.warn("[TT] Attempting to execute command with <null> param");

			boolean executedCommand = false;
			if (subCommandUser.equals("help"))
			{
				executeHelp(sender, args);
				executedCommand = true;
			}

			if (!executedCommand && player != null)
			{
				switch (subCommandUser)
				{
					case "addmotion":
						executeAddMotion(sender, args, player);
						break;
					case "heal":
						executeHeal(sender, args, offset, player);
						break;
					case "random":
						executeRandom(sender, player);
						break;
					case "spawn":
						executeSpawn(sender, player);
						break;
					case "setabsorption":
						executeSetAbsorption(sender, args, offset, player);
						break;
					default:
						subCommandOptions(sender);
						executedCommand = true;
						break;
				}
			}

			if (!executedCommand && player == null)
				subCommandOptions(sender);
		}
		else
		{
			subCommandOptions(sender);
		}

	}

	/******************************************************************************************************************************************/

	protected void executeAddMotion(ICommandSender sender, String[] args, EntityPlayer player)
	{
		try
		{
			if (args.length < 6)
				throw new IllegalArgumentException();

			double x = Double.parseDouble(args[2]);
			double y = Double.parseDouble(args[3]);
			double z = Double.parseDouble(args[4]);
			float a = Float.parseFloat(args[5]);

			player.setAbsorptionAmount(player.getAbsorptionAmount() + a);
			player.addVelocity(x, y, z);
			player.velocityChanged = true;
			sendIM(sender, new TextComponentString("New Velocity of " + player.getDisplayNameString() + " to {" + player.motionX + ", " + player.motionY + ", " + player.motionZ + "}"));
		}
		catch (Exception e)
		{
			sendIM(sender, new TextComponentString(TextFormatting.RED + "Invalid arguments."));
			subCommandOptions(sender, "addMotion");
			sendIM(sender, new TextComponentString(TextFormatting.RED + "Format: <String> <Double> <Double> <Double> <Float>"));
		}
	}

	private void executeHelp(ICommandSender sender, String[] args)
	{
		if (args.length > 1)
		{
			switch (args[1])
			{
				case "addMotion":
					sendIM(sender, new TextComponentString("Sets the " + TextFormatting.YELLOW + "<player>" + TextFormatting.RESET + " in Motion by Vector v(" + TextFormatting.YELLOW + "<x> <y> <z>" + TextFormatting.RESET + ")\n    and applies an absorption-buff with an amount of " + TextFormatting.YELLOW + "<AbsorptionHearts>"));
					break;

				case "heal":
					sendIM(sender, new TextComponentString("Sets the players health and Saturation to max or to " + TextFormatting.YELLOW + "<value>"));
					break;

				case "help":
					sendIM(sender, new TextComponentString("Displays more information about the given command."));
					break;

				case "random":
					sendIM(sender, new TextComponentString("Ports the player to a random location inside the world border. Will try and find a safe place."));
					break;

				case "spawn":
					sendIM(sender, new TextComponentString("Ports the executing or given" + TextFormatting.YELLOW + "[player]" + TextFormatting.RESET + " to Spawn."));
					break;

				default:
					subCommandOptions(sender);
					break;
			}
		}
		else
		{
			subCommandOptions(sender);
		}
	}

	protected void executeHeal(ICommandSender sender, String[] args, int offset, EntityPlayer player)
	{
		try
		{
			if (args.length > (1 + offset))
			{
				float health = (float) Math.max(Math.min(player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue(), Double.parseDouble(args[(1 + offset)])), 0.0);
				player.setHealth(health);
				sendIM(sender, new TextComponentString("Health set: " + health));
			}
			else
			{
				player.setHealth((float) player.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getBaseValue());

				while (player.getFoodStats().needFood())
				{
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel() + 1);
				}
			}
		}
		catch (Exception e)
		{
			sendIM(sender, new TextComponentString(TextFormatting.RED + "Invalid arguments."));
			subCommandOptions(sender, "heal");
			sendIM(sender, new TextComponentString(TextFormatting.RED + "Format: <float>"));
		}
	}

	protected void executeRandom(ICommandSender sender, EntityPlayer player)
	{
		sendIM(player, new TextComponentString("" + TextFormatting.LIGHT_PURPLE + "Searching..."));

		World world = player.world;
		MinecraftServer minecraftServer = world.getMinecraftServer();
		if (minecraftServer == null)
		{
			throw new NullPointerException("The associated world could not return an instance of MinecraftServer.");
		}

		IChunkGenerator cgen = minecraftServer.getWorld(world.provider.getDimension()).getChunkProvider().chunkGenerator;
		WorldBorder border = world.getWorldBorder();
		int iterations = 0;

		try
		{
			while (true)
			{
				if (iterations > 2000)
				{
					String reason;

					switch (world.rand.nextInt(5))
					{
						case 1:
							reason = "only found hazardous lava";
							break;
						case 2:
							reason = "only found water, which you'll probably drown in";
							break;
						case 3:
							reason = "had to give up after 500 checks";
							break;
						case 4:
							reason = "ran into too many monsters";
							break;
						default:
							reason = "simply found nothing";
							break;
					}

					sendIM(player, new TextComponentString("" + TextFormatting.YELLOW + "I tried to find a place, but I " + reason + ". Please try again."));
					break;
				}
				else
				{
					iterations++;
				}

				int x = Generic.getRandomNumberInRange(world.rand, (int) border.minX() + 1, (int) border.maxX());
				int z = Generic.getRandomNumberInRange(world.rand, (int) border.minZ() + 1, (int) border.maxZ());

				if (world.isSpawnChunk(x, z))
					continue;

				BlockPos xz = new BlockPos(x, 0, z);

				Chunk chunk = world.getChunkFromBlockCoords(xz);

				if (!chunk.isLoaded())
				{
					chunk.onLoad();
					chunk.onTick(false);
				}

				if (!chunk.isPopulated())
				{
					chunk.populate(world.getChunkProvider(), cgen);
				}

				BlockPos pos = world.getTopSolidOrLiquidBlock(xz);

				if (pos.getY() < 10 || world.containsAnyLiquid(new AxisAlignedBB(pos).expand(0, 1, 0)))
					continue;

				if (!world.isSideSolid(pos.down(), EnumFacing.UP))
					continue;

				// Is air present at the position, where the player should be ported to?
				BlockPos testPos = pos;
				boolean noAir = false;
				for (int i = 0; i < 5; i++)
				{
					testPos = testPos.up();
					if (!world.isAirBlock(testPos))
					{
						noAir = true;
						break;
					}
				}

				if (noAir)
					continue;

				sendIM(player, new TextComponentString("" + TextFormatting.AQUA + pos + " looks quite nice!"));
				TrienowTweaks.logger.info("[TT][RANDOM] Porting " + player.getDisplayNameString() + " to " + pos);
				player.setPositionAndUpdate(pos.getX() + 0.5, pos.getY() + 3, pos.getZ() + 0.5);
				break;
			}
		}
		catch (IllegalArgumentException e)
		{
			sendIM(sender, new TextComponentString("" + TextFormatting.RED + e.getLocalizedMessage()));
		}
	}

	protected void executeSetAbsorption(ICommandSender sender, String[] args, int offset, EntityPlayer player)
	{
		try
		{
			player.setAbsorptionAmount(Float.parseFloat(args[1 + offset]));
			sendIM(sender, new TextComponentString(TextFormatting.DARK_AQUA + player.getDisplayNameString() + TextFormatting.RESET + " now has " + TextFormatting.AQUA + player.getAbsorptionAmount() + TextFormatting.RESET + " Absorption Hearts."));
		}
		catch (NumberFormatException e)
		{
			sendIM(sender, new TextComponentString(TextFormatting.RED + "Invalid arguments."));
			subCommandOptions(sender, "setAbsorption");
			sendIM(sender, new TextComponentString(TextFormatting.RED + "Format: [String] <Float>"));
		}
	}

	protected void executeSpawn(ICommandSender sender, EntityPlayer player)
	{
		BlockPos pos = player.world.getSpawnPoint();
		player.setPositionAndUpdate(pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d);
		sendIM(sender, new TextComponentString("Ported back to Spawn at " + TextFormatting.DARK_AQUA + pos));
	}

	/******************************************************************************************************************************************/

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender)
	{
		if (sender.getCommandSenderEntity() == null)
			return true;
		else
			return super.checkPermission(server, sender);
	}

	@Override
	public int getRequiredPermissionLevel()
	{
		return 4;
	}

	private void subCommandOptions(ICommandSender sender, String key)
	{
		sendIM(sender, new TextComponentString("/tt " + TextFormatting.GREEN + key + " " + TextFormatting.YELLOW + subCommands.get(key)));
	}

	private void subCommandOptions(ICommandSender sender)
	{
		sendIM(sender, new TextComponentString(TextFormatting.DARK_GREEN + "-----" + TextFormatting.RESET + " Subcommand Options: " + TextFormatting.DARK_GREEN + "-----"));

		for (String key : subCommands.keySet())
		{
			subCommandOptions(sender, key);
		}
	}

	protected void sendIM(ICommandSender sender, TextComponentString text)
	{
		sender.sendMessage(new TextComponentString(text.getFormattedText() + TextFormatting.RESET));
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
	{
		if (args.length > 1)
		{
			switch (args[0].toLowerCase())
			{
				case "absorption":
				case "addmotion":
				case "heal":
				case "random":
				case "setprefix":
				case "spawn":
					return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());

				case "help":
					return getListOfStringsMatchingLastWord(args, subCommands.keySet());

				default:
					return super.getTabCompletions(server, sender, args, pos);
			}
		}
		else
		{
			return getListOfStringsMatchingLastWord(args, subCommands.keySet());
		}
	}
}
