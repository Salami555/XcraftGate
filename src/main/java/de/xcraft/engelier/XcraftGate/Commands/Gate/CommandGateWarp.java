package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGateWarp extends CommandHelperGate {
    
    public CommandGateWarp(XcraftGate plugin) {
        super(plugin);
    }
    
    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gate warp <gatename>");
    }
    
    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        if (gateName == null) {
            error(sender, "No gate given.");
            replyUsage(sender);
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else {
            plugin.justTeleportedFrom.put(((Player) sender).getName(), getGate(gateName).getLocation());
            getGate(gateName).portHere((Player) sender);
        }
    }
}
