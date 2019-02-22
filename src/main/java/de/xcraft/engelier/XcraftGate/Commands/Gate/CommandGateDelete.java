package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.DataGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateDelete extends CommandHelperGate {

	public CommandGateDelete(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String gateName, List<String> args) {
		if (gateName == null) {
			error(sender, "No gate given.");
			reply(sender, "Usage: /gate delete <gatename>");
		} else if (!gateExists(gateName)) {
			reply(sender, "Gate not found: " + gateName);
		} else {
			DataGate thisGate = getGate(gateName);
			
			for (DataGate checkGate : plugin.getGates()) {
				if (checkGate.hasTarget() && checkGate.getTarget().equals(thisGate)) {
					checkGate.unlink();
				}
			}

			plugin.getGates().remove(thisGate);
			reply(sender, "Gate " + gateName + " removed.");
		}
	}

}
