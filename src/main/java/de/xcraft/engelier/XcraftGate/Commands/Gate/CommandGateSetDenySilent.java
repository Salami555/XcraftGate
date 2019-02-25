package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateSetDenySilent extends CommandHelperGate {

    public CommandGateSetDenySilent(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gate setdenysilent <gatename> <true|false>");
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        if (gateName == null) {
            error(sender, "No gate given.");
            replyUsage(sender);
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else {
            Boolean denysilent;

            denysilent = (args.isEmpty() || args.get(0).equalsIgnoreCase("true"));

            getGate(gateName).setDenySilent(denysilent);
            reply(sender, "Gate " + gateName + " denys usage " + (denysilent ? "silently." : "loudly."));
        }
    }

}
