package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldAllowPvP extends CommandHelperWorld {

	public CommandWorldAllowPvP(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName,	List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld allowpvp <worldname> <true|false>");
		} else if (!hasWorld(worldName)) {
			reply(sender, "World not found: " + worldName);
		} else {
			Boolean allowed;
			
			allowed = (args.isEmpty() || !args.get(0).equalsIgnoreCase("false"));

			getWorld(worldName).setAllowPvP(allowed);
			reply(sender, "PvP on " + worldName + (allowed ? " enabled." : " disabled."));
		}
	}

}
