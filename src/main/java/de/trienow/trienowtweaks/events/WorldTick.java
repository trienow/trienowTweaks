package de.trienow.trienowtweaks.events;

import de.trienow.trienowtweaks.commands.CommandUtils;
import de.trienow.trienowtweaks.commands.commandTreq.CommandTreq;
import de.trienow.trienowtweaks.config.ServerConfig;
import de.trienow.trienowtweaks.main.TrienowTweaks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent;

import java.util.List;

/**
 * @author trienow 2017 - 2023
 */
@EventBusSubscriber(modid = TrienowTweaks.MODID, value = Dist.DEDICATED_SERVER)
public class WorldTick
{
	private static int antiFlyingTick = 0;

	@SubscribeEvent
	public static void onWorldTickEvent(LevelTickEvent.Post evt)
	{
		if (!evt.getLevel().isClientSide())
		{
			CommandTreq.onLevelTick(evt);
			antiFlying(evt);
		}
	}

	private static void antiFlying(LevelTickEvent evt)
	{
		if (antiFlyingTick > 100)
		{
			antiFlyingTick = 0;

			List<? extends String> flightDisabledDims = ServerConfig.CONFIG.flightDisabled.get();
			if (flightDisabledDims != null)
			{
				Level level = evt.getLevel();
				for (Player player : evt.getLevel().players())
				{
					if (player instanceof ServerPlayer serverPlayer)
					{
						String dimKey = level.dimension().location().toString();
						if (!player.isCreative() && !player.isSpectator() && flightDisabledDims.contains(dimKey) && !player.onGround() && player.getDeltaMovement().y == 0)
						{
							Vec3 playerPos = player.position();
							for (int i = (int) (playerPos.y); i >= 1; i--)
							{
								BlockPos bPos = new BlockPos((int) playerPos.x, i, (int) playerPos.z);
								if (level.getBlockState(bPos).isFaceSturdy(level, bPos, Direction.UP))
								{
									player.setPos(playerPos.x, i + 1, playerPos.z);
									player.getAbilities().mayfly = false;
									player.getAbilities().flying = false;
									player.onUpdateAbilities();

									player.hurt(player.damageSources().fellOutOfWorld(), 2);

									CommandUtils.sendIm(serverPlayer, "feature.trienowtweaks.anti_flying.message");
									break;
								}
							}
						}
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
