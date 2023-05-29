package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.commands.commandTA.CommandTA;
import de.trienow.trienowtweaks.main.Config;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.WorldTickEvent;

/**
 * @author (c) trienow 2017
 */
@Mod.EventBusSubscriber
public class WorldTick
{
	private static int worldTick = 0;
	private static int twentyFourHCmdTimer = 0;
	private static final int TICKS30M = 36000;

	@SubscribeEvent
	public static void onWorldTick(WorldTickEvent evt)
	{
		CommandTA.onWorldTickEvt(evt);

		if (!evt.world.isRemote)
		{
			antiFlying(evt);
			twentyFourHourCmd(evt);
		}
	}

	private static void antiFlying(WorldTickEvent evt)
	{
		if (Config.noFlyingInDimension != null)
		{
			if (worldTick > 100)
			{
				worldTick = 0;

				MinecraftServer minecraftServer = evt.world.getMinecraftServer();
				if (minecraftServer != null)
				{
					for (EntityPlayer player : minecraftServer.getPlayerList().getPlayers())
					{
						if (!player.isCreative() && !player.isSpectator() && Config.noFlyingInDimension.contains(player.dimension) && !player.onGround && player.motionY == 0)
						{
							World world = DimensionManager.getWorld(player.dimension);
							for (int i = (int) (player.posY); i >= 1; i--)
							{
								BlockPos bPos = new BlockPos((int) player.posX, i, (int) player.posZ);
								if (world.getBlockState(bPos).isSideSolid(evt.world, bPos, EnumFacing.UP))
								{
									player.setPositionAndUpdate(player.posX, i + 1, player.posZ);
									player.capabilities.isFlying = false;
									player.capabilities.allowFlying = false;
									player.sendPlayerAbilities();

									player.attackEntityFrom(DamageSource.OUT_OF_WORLD, 2);

									player.sendMessage(new TextComponentString("" + TextFormatting.RED + TextFormatting.ITALIC + "Flying is not allowed in this Dimension. LOL xD"));
									break;
								}
							}
						}
					}
				}
			}
			else
			{
				worldTick++;
			}
		}
	}

	private static void twentyFourHourCmd(WorldTickEvent evt)
	{
		if (twentyFourHCmdTimer > TICKS30M)
		{
			twentyFourHCmdTimer = 0;
			if (Config.mayExecute24hCmds())
			{
				TrienowTweaks.logger.info("Running 24h Cmds");
				MinecraftServer minecraftServer = evt.world.getMinecraftServer();
				if (minecraftServer != null)
				{
					for (String command : Config.commandsToExecute)
					{
						minecraftServer.commandManager.executeCommand(minecraftServer, command);
					}
				}
			}
		}
		twentyFourHCmdTimer++;
	}
}
