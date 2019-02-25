package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetSticky extends CommandHelperWorld {

    public CommandWorldSetSticky(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            reply(sender, "Usage: /gworld setsticky <worldname> <true|false>");
        } else if (!hasWorld(worldName)) {
            reply(sender, "Unknown world: " + worldName);
        } else {
            Boolean sticky;

            sticky = (args.isEmpty() || !args.get(0).equalsIgnoreCase("false"));

            getWorld(worldName).setSticky(sticky);
            reply(sender, (sticky ? "Sticked" : "Unsticked") + " world " + worldName + ".");
        }
    }

}
