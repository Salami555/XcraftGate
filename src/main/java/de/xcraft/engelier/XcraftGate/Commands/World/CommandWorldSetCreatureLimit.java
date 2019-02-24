package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetCreatureLimit extends CommandHelperWorld {

	public CommandWorldSetCreatureLimit(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld setcreaturelimit <worldname> <#limit>");
		} else if (!hasWorld(worldName)) {
			reply(sender, "World not found: " + worldName);
		} else {
			int limit;

			try {
				limit = Integer.parseInt(args.get(0));
			} catch (IndexOutOfBoundsException | NumberFormatException ex) {
				reply(sender, "Invalid number: " + (args.size() > 0 ? args.get(0) : "<null>"));
				reply(sender, "Usage: /gworld setborder <worldname> <#limit>");
				return;
			}

			if (limit <= 0) {
				getWorld(worldName).setCreatureLimit(0);
				reply(sender, "Creature limit of world " + worldName + " removed.");
			} else {
				getWorld(worldName).setCreatureLimit(limit);
				reply(sender, "Creature limit of world " + worldName + " set to " + limit + ".");
			}
		}
	}

}
