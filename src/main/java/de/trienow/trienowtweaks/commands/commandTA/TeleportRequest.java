package de.trienow.trienowtweaks.commands.commandTA;

public class TeleportRequest
{
	public final String destination;
	public int time;
	public boolean hasAccepted;

	public TeleportRequest(String dest, boolean confirmed)
	{
		destination = dest;
		if (confirmed)
			time = -1;
		else
			time = -12;
	}

	public void confirm()
	{
		time = -1;
		hasAccepted = true;
	}
}
