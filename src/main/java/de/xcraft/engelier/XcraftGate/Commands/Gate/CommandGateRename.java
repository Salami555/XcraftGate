package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.DataGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateRename extends CommandHelperGate {

    public CommandGateRename(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        if (gateName == null || args.isEmpty()) {
            error(sender, "No gate given.");
            reply(sender, "Usage: /gate rename <gatename> <new_gatename>");
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else {
            DataGate thisGate = getGate(gateName);
            plugin.getGates().remove(thisGate);
            thisGate.setName(args.get(0));
            plugin.getGates().add(thisGate, true);

            reply(sender, "Gate " + gateName + " renamed to " + thisGate.getName());
        }
    }

}
