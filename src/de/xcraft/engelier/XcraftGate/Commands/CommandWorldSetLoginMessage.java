package de.xcraft.engelier.XcraftGate.Commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import de.xcraft.engelier.XcraftGate.XcraftGate;

public class CommandWorldSetLoginMessage extends CommandHelperWorld {

	public CommandWorldSetLoginMessage(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		this.sender = sender;
		
		if (worldName == null) {
			error("No world given.");
			reply("Usage: /gworld setloginmessage <worldname> [message]");
		} else if (!hasWorld(worldName)) {
			reply("World not found: " + worldName);
		} else {
			Message newMessage = null;
			
			newMessage = args.toString(); //use multiple arguments, hope it works
			
			getWorld(worldName).setLoginMessage(newMessage);
			if (newMessage == null) {
			  reply("Reset login message on world " + worldName + ".");
			}
			else {
			  reply("Login message on world " + worldName + " set to " + newMessage);
			}
		}
	}

}
