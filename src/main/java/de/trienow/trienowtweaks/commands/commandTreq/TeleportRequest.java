package de.trienow.trienowtweaks.commands.commandTreq;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author (c) trienow 2019 - 2022
 */
class TeleportRequest
{
	private final String destination;
	private Instant nextEvent;
	private boolean hasAccepted = false;

	/**
	 * Constructs a new TeleportRequest
	 */
	public TeleportRequest(String destination)
	{
		nextEvent = Instant.now().plus(30, ChronoUnit.SECONDS);
		this.destination = destination;
	}

	/**
	 * Sets the accepted-flag to <code>true</code> and the timer to the teleport time
	 */
	public void acceptRequest()
	{
		nextEvent = Instant.now().plus(5, ChronoUnit.SECONDS);
		hasAccepted = true;
	}

	/**
	 * @return The destination player name
	 */
	public String getDestination()
	{
		return destination;
	}

	/**
	 * @return <code>true</code> when the request should be removed.
	 */
	public boolean isTimeUp()
	{
		return Instant.now().isAfter(nextEvent);
	}

	/**
	 * @return <code>true</code> when the teleport-request should be executed
	 */
	public boolean shouldExecuteTeleport()
	{
		return hasAccepted;
	}
}
