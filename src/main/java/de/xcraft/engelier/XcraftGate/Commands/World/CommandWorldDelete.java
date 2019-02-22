package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldDelete extends CommandHelperWorld {

	public CommandWorldDelete(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld delete <worldname>");
		} else if (!hasWorld(worldName)) {
			reply(sender, "World not found: " + worldName);
		} else {
			if (getWorld(worldName).isLoaded()) {
				if (plugin.getServer().getWorld(worldName).getPlayers().size() > 0) {
					error(sender, "Unable to unload world with active players.");
					return;
				} else {
					getWorld(worldName).unload();
				}
			}
			
			plugin.getWorlds().remove(worldName);
			reply(sender, "World " + worldName + " removed.");
		}
	}
}
