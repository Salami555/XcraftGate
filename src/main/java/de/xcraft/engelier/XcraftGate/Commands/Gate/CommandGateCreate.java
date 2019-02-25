package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.DataGate;
import de.xcraft.engelier.XcraftGate.Util;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGateCreate extends CommandHelperGate {

    public CommandGateCreate(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        if (gateName == null) {
            error(sender, "No gate given.");
            reply(sender, "Usage: /gate create <gatename>");
        } else if (gateExists(gateName)) {
            reply(sender, "Gate already exists: " + gateName);
        } else {
            Location loc = ((Player) sender).getLocation();

            if (gateExists(loc)) {
                reply(sender, "There is already a gate at this location: " + getGateByLocation(loc).getName());
            } else {
                DataGate newGate = new DataGate(plugin, gateName);
                newGate.setLocation(Util.getSaneLocation(loc));
                plugin.getGates().add(newGate, true);
                reply(sender, "Gate " + gateName + " created at " + Util.getLocationString(newGate.getLocation()));
            }
        }
    }
}
