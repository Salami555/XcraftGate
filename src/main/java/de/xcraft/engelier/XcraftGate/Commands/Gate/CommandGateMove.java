package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.DataGate;
import de.xcraft.engelier.XcraftGate.Util;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGateMove extends CommandHelperGate {

	public CommandGateMove(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String gateName, List<String> args) {
		if (gateName == null) {
			error(sender, "No gate given.");
			reply(sender, "Usage: /gate move <gatename>");
		} else if (!gateExists(gateName)) {
			reply(sender, "Gate not found: " + gateName);
		} else {
			DataGate thisGate = getGate(gateName);
			plugin.getGates().remove(thisGate);
			thisGate.setLocation(((Player) sender).getLocation());
			plugin.getGates().add(thisGate, true);

			plugin.justTeleported.put(((Player) sender).getName(), thisGate.getLocation());
			plugin.justTeleportedFrom.put(((Player) sender).getName(), thisGate.getLocation());
			reply(sender, "Gate " + gateName + " moved to " + Util.getLocationString(thisGate.getLocation()));
		}
	}

}
