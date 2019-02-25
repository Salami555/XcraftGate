package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldLoad extends CommandHelperWorld {

    public CommandWorldLoad(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            reply(sender, "Usage: /gworld load <worldname>");
        } else if (!hasWorld(worldName)) {
            reply(sender, "Unknown world: " + worldName);
        } else if (getWorld(worldName).isLoaded()) {
            reply(sender, "World " + worldName + " already loaded.");
        } else {
            getWorld(worldName).load();
            reply(sender, "Loaded world " + worldName);
        }
    }

}
