package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.DataGate;
import de.xcraft.engelier.XcraftGate.Util;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandGateListnear extends CommandHelperGate {

    public CommandGateListnear(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        int radius = 10;

        if (gateName != null) {
            try {
                radius = Integer.parseInt(gateName);
            } catch (NumberFormatException ex) {
                error(sender, "Invalid radius number: " + gateName);
                return;
            }
        }

        Location now = ((Player) sender).getLocation();
        double xx = now.getX();
        double yy = now.getY();
        double zz = now.getZ();
        boolean fail = true;

        for (int x = -radius; x <= radius; x++) {
            for (int y = (yy - radius > 0 ? -radius : (int) -yy); y <= (y + radius > 127 ? 128 - y : radius); y++) {
                for (int z = -radius; z <= radius; z++) {
                    DataGate thisGate = plugin.getGates().getByLocation(new Location(now.getWorld(), x + xx, y + yy, z + zz));
                    if (thisGate != null) {
                        reply(sender, "Found " + thisGate.getName() + " at " + Util.getLocationString(thisGate.getLocation()));
                        fail = false;
                    }
                }
            }
        }

        if (fail) {
            reply(sender, "No gates found.");
        }
    }
}
