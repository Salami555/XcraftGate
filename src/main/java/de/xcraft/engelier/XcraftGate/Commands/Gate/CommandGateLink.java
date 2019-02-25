package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateLink extends CommandHelperGate {

    public CommandGateLink(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        String gateTarget = (args.size() > 0 ? args.get(0) : null);

        if (gateName == null || gateTarget == null) {
            error(sender, "No gate(s) given.");
            reply(sender, "Usage: /gate link <gatename> <target_gatename>");
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else if (!gateExists(gateTarget)) {
            reply(sender, "Gate not found: " + gateTarget);
        } else {
            getGate(gateName).linkTo(gateTarget);
            reply(sender, "Linked Gate " + gateName + " to " + gateTarget);
        }
    }
}
