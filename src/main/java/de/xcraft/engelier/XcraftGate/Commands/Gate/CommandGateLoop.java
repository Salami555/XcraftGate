package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateLoop extends CommandHelperGate {

    public CommandGateLoop(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gate loop <gatename> <target_gatename>");
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        String gateTarget = (args.size() > 0 ? args.get(0) : null);

        if (gateName == null || gateTarget == null) {
            error(sender, "No gate(s) given.");
            replyUsage(sender);
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else if (!gateExists(gateTarget)) {
            reply(sender, "Gate not found: " + gateTarget);
        } else {
            getGate(gateName).linkTo(gateTarget, false);
            getGate(gateTarget).linkTo(gateName);
            reply(sender, "Looped Gates " + gateName + " <=> " + gateTarget);
        }
    }

}
