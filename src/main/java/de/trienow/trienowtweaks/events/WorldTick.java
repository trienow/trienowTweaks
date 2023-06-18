package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.commands.commandTreq.CommandTreq;
import de.trienow.trienowtweaks.config.Config;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.util.List;

/**
 * @author trienow 2017 - 2023
 */
@Mod.EventBusSubscriber(modid = TrienowTweaks.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class WorldTick
{
	private static int antiFlyingTick = 0;

	@SubscribeEvent
	public static void onWorldTickEvent(TickEvent.LevelTickEvent evt)
	{
		if (!evt.level.isClientSide())
		{
			CommandTreq.onLevelTick(evt);
			antiFlying(evt);
		}
	}

	private static void antiFlying(TickEvent.LevelTickEvent evt)
	{
		if (antiFlyingTick > 100)
		{
			antiFlyingTick = 0;

			List<? extends String> flightDisabledDims = Config.getServerConfig().flightDisabled.get();
			if (flightDisabledDims != null)
			{

				for (Player player : evt.level.getServer().getPlayerList().getPlayers())
				{
					try (Level level = player.level())
					{
						String dimKey = level.dimension().location().toString();
						if (!player.isCreative() && !player.isSpectator() && flightDisabledDims.contains(dimKey) && !player.onGround() && player.getDeltaMovement().y == 0)
						{
							Vec3 playerPos = player.position();
							for (int i = (int) (playerPos.y); i >= 1; i--)
							{
								BlockPos bPos = new BlockPos((int) playerPos.x, i, (int) playerPos.z);
								if (level.getBlockState(bPos).isFaceSturdy(evt.level, bPos, Direction.UP))
								{
									player.setPos(playerPos.x, i + 1, playerPos.z);
									player.getAbilities().mayfly = false;
									player.getAbilities().flying = false;
									player.onUpdateAbilities();

									player.hurt(player.damageSources().fellOutOfWorld(), 2);

									CommandUtils.sendIm(player, "feature.trienowtweaks.anti_flying.message");
									break;
								}
							}
						}
					}
					catch (IOException ex)
					{
						TrienowTweaks.LOG.error("ANTI FLYING: The level could not be accessed.", ex);
					}
				}

			}
		}
		else
		{
			antiFlyingTick++;
		}
	}
}
