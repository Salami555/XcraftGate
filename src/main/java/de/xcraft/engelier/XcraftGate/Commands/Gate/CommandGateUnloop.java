package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.DataGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateUnloop extends CommandHelperGate {

	public CommandGateUnloop(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String gateName, List<String> args) {
		String gateTarget = (args.size() > 0 ? args.get(0) : null);
		
		if (gateName == null || gateTarget == null) {
			error(sender, "No gate(s) given.");
			reply(sender, "Usage: /gate unloop <gatename> <target_gatename>");
		} else if (!gateExists(gateName)) {
			reply(sender, "Gate not found: " + gateName);
		} else if (!gateExists(gateTarget)) {
			reply(sender, "Gate not found: " + gateTarget);
		} else {
			DataGate loop1 = getGate(gateName);
			DataGate loop2 = getGate(gateTarget);
			
			if (!loop1.getTarget().equals(loop2) || !loop2.getTarget().equals(loop1)) {
				reply(sender, "Gates " + gateName + " and " + gateTarget + " aren't linked together");
			} else {
				loop1.unlink();
				loop2.unlink();
				reply(sender, "removed gate loop " + gateName + " <=> " + gateTarget);
			}
		}
	}

}
