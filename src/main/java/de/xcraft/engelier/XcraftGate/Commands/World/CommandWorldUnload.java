package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldUnload extends CommandHelperWorld {

    public CommandWorldUnload(XcraftGate instance) {
        super(instance);
    }

    @Override
    public void execute(CommandSender sender, String worldName,
            List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            reply(sender, "Usage: /gworld unload <worldname>");
        } else if (!hasWorld(worldName)) {
            reply(sender, "Unknown world: " + worldName);
        } else if (!getWorld(worldName).isLoaded()) {
            reply(sender, "World " + worldName + " is not loaded.");
        } else if (plugin.getServer().getWorld(worldName).getPlayers().size() > 0) {
            error(sender, "Unable to unload world with active players.");
        } else {
            getWorld(worldName).unload();
            reply(sender, "Unloaded world " + worldName);
        }
    }

}
