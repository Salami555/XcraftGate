package de.xcraft.engelier.XcraftGate.Commands;

import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.command.CommandSender;

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
		} else if (args == null) {
			getWorld(worldName).setLoginMessage("none");
			reply("Reset login message on world " + worldName + ".");
		} else {
			String newMessage = null;
			newMessage = args.stream().collect(Collectors.joining()); //use multiple arguments, hope it works
			
			getWorld(worldName).setLoginMessage(newMessage);
			reply("Login message on world " + worldName + " set to " + newMessage);
		}
	}

}
