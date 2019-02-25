package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetInventoryGroup extends CommandHelperWorld {

    public CommandWorldSetInventoryGroup(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gworld setinventorygroup <worldname> <groupname>");
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            replyUsage(sender);
        } else if (!hasWorld(worldName)) {
            reply(sender, "World not found: " + worldName);
        } else if (args.size() != 1) {
            error(sender, "Wrong argument count.");
            replyUsage(sender);
        } else {
            getWorld(worldName).setInventoryGroup(args.get(0));
            reply(sender, "Inventory group for world " + worldName + " set to " + args.get(0));
        }
    }

}
