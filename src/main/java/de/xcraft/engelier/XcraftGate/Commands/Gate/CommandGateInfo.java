package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateInfo extends CommandHelperGate {

    public CommandGateInfo(XcraftGate instance) {
        super(instance);
    }

    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gate info <gatename>");
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        if (gateName == null) {
            error(sender, "No gate given.");
            replyUsage(sender);
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else {
            reply(sender, "Info for gate " + gateName);
            getGate(gateName).sendInfo(sender);
        }
    }

}
