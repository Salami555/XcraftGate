package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSuppressHealthregain extends CommandHelperWorld {

	public CommandWorldSuppressHealthregain(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName,	List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld suppresshealthregain <worldname> <true|false>");
		} else if (!hasWorld(worldName)) {
			reply(sender, "World not found: " + worldName);
		} else {
			Boolean suppressed;
			
			suppressed = (args.isEmpty() || !args.get(0).equalsIgnoreCase("false"));

			getWorld(worldName).setSuppressHealthRegain(suppressed);
			reply(sender, "Automatic health regain on " + worldName + (suppressed ? " suppressed." : " enabled."));
		}
	}

}
