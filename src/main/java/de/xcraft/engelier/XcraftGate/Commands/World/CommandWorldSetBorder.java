package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetBorder extends CommandHelperWorld {

	public CommandWorldSetBorder(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld setborder <worldname> <#border>");
		} else if (!hasWorld(worldName)) {
			reply(sender, "World not found: " + worldName);
		} else {
			int border;

			try {
				border = Integer.parseInt(args.get(0));
			} catch (IndexOutOfBoundsException | NumberFormatException ex) {
				reply(sender, "Invalid number: " + (args.size() > 0 ? args.get(0) : "<null>"));
				reply(sender, "Usage: /gworld setborder <worldname> <#border>");
				return;
			}

			if (border <= 0) {
				getWorld(worldName).setBorder(0);
				reply(sender, "Border of world " + worldName + " removed.");
			} else {
				getWorld(worldName).setBorder(border);
				reply(sender, "Border of world " + worldName + " set to " + border + ".");
			}
		}
	}

}
