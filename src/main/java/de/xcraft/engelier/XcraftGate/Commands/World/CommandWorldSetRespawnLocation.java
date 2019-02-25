package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.DataWorld;
import de.xcraft.engelier.XcraftGate.DataWorld.RespawnLocation;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetRespawnLocation extends CommandHelperWorld {

    public CommandWorldSetRespawnLocation(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gworld setrespawnlocation <worldname> <worldspawn|bedspawn|world <worldname>>");
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            replyUsage(sender);
        } else if (!hasWorld(worldName)) {
            reply(sender, "Unknown world: " + worldName);
        } else if (args.size() < 1) {
            error(sender, "No location given.");
            replyUsage(sender);
        } else {
            String rsLoc = args.get(0);
            RespawnLocation newRSLoc = null;

            for (RespawnLocation thisRLoc : DataWorld.RespawnLocation.values()) {
                if (thisRLoc.toString().equalsIgnoreCase(rsLoc)) {
                    newRSLoc = thisRLoc;
                }
            }

            if (newRSLoc == null) {
                reply(sender, "Unknown respawn location: " + rsLoc);
                reply(sender, "Usage: /gworld setrespawnlocation <worldname> <worldspawn|bedspawn|world <worldname>>");
            }

            if (newRSLoc == RespawnLocation.WORLD) {
                if (args.size() < 2) {
                    error(sender, "No respawn world given.");
                    reply(sender, "Usage: /gworld setrespawnlocation <worldname> <worldspawn|bedspawn|world <worldname>>");
                    return;
                } else if (!hasWorld(args.get(1))) {
                    reply(sender, "Unknown respawn world: " + args.get(1));
                    return;
                } else {
                    getWorld(worldName).setRespawnWorldName(args.get(1));
                }
            }

            getWorld(worldName).setRespawnLocation(newRSLoc);
            reply(sender, "RespawnLocation for world " + worldName + " set to " + newRSLoc.toString() + (newRSLoc == RespawnLocation.WORLD ? ": " + args.get(1) : ""));
        }
    }

}
