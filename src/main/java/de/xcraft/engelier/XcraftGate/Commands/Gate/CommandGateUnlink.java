package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandGateUnlink extends CommandHelperGate {

    public CommandGateUnlink(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        if (gateName == null) {
            error(sender, "No gate given.");
            reply(sender, "Usage: /gate unlink <gatename>");
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else {
            getGate(gateName).unlink();
            reply(sender, "removed link from gate " + gateName);
        }
    }

}
