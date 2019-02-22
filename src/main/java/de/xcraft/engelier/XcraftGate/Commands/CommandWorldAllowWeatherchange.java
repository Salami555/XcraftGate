package de.xcraft.engelier.XcraftGate.Commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import de.xcraft.engelier.XcraftGate.XcraftGate;

public class CommandWorldAllowWeatherchange extends CommandHelperWorld {

	public CommandWorldAllowWeatherchange(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		this.sender = sender;
		
		if (worldName == null) {
			error("No world given.");
			reply("Usage: /gworld allowweatherchange <worldname> <true|false>");
		} else if (!hasWorld(worldName)) {
			reply("World not found: " + worldName);
		} else {
			Boolean allowed;
			
			allowed = (args.size() == 0 || !args.get(0).equalsIgnoreCase("false")) ? true : false;

			getWorld(worldName).setAllowWeatherChange(allowed);
			reply("Weather changes on " + worldName + (allowed ? " enabled." : " disabled."));
		}
	}
}
