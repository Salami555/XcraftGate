package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.DataGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateListsolo extends CommandHelperGate {

	public CommandGateListsolo(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String gateName, List<String> args) {
		for (DataGate thisGate : plugin.getGates()) {
			if (!thisGate.hasTarget()) {
				boolean hasSource = false;
				
				for (DataGate sourceGate : plugin.getGates()) {
					if (sourceGate.getTarget() != null && sourceGate.getTarget().equals(thisGate)) { 
						hasSource = true;
					}
				}
				
				if (!hasSource)
					reply(sender, "Found orphan: " + thisGate.getName());
			}
		}
		
	}

}
