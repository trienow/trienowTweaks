package de.trienow.trienowtweaks.capabilities;

import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author (c) trienow 2022
 */
public class PlayerCapabilityProvider implements ICapabilityProvider, ICapabilitySerializable<Tag>
{
	final IPlayerCapability playerCap;
	final LazyOptional<IPlayerCapability> loPlayerCap;

	public PlayerCapabilityProvider()
	{
		playerCap = new PlayerCapability();
		loPlayerCap = LazyOptional.of(() -> playerCap);
	}

	/**
	 * Retrieves the Optional handler for the capability requested on the specific side.
	 * The return value <strong>CAN</strong> be the same for multiple faces.
	 * Modders are encouraged to cache this value, using the listener capabilities of the Optional to
	 * be notified if the requested capability get lost.
	 *
	 * @param cap  The capability to check
	 * @param side The Side to check from,
	 *             <strong>CAN BE NULL</strong>. Null is defined to represent 'internal' or 'self'
	 * @return The requested an optional holding the requested capability.
	 */
	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
	{
		return IPlayerCapability.PLAYER_CAP.orEmpty(cap, loPlayerCap);
	}

	@Override
	public Tag serializeNBT()
	{
		return playerCap.serializeNBT();
	}

	@Override
	public void deserializeNBT(Tag nbt)
	{
		playerCap.deserializeNBT(nbt);
	}
}
