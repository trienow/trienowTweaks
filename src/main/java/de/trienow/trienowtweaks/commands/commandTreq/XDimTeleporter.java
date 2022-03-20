package de.trienow.trienowtweaks.commands.commandTreq;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

/**
 * @author (c) trienow 2022
 */
public record XDimTeleporter(Vec3 pos) implements ITeleporter
{
	@Override
	public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity)
	{
		entity = repositionEntity.apply(false); //Prevents portal creation
		entity.teleportTo(pos.x, pos.y + 0.5, pos.z);
		return entity;
	}
}
