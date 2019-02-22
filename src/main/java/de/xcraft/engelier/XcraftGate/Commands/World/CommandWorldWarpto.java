package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorldWarpto extends CommandHelperWorld {

	public CommandWorldWarpto(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld warpto <worldname>");
		} else if (!hasWorld(worldName)) {
			reply(sender, "World not found: " + worldName);
		} else {
			if (!getWorld(worldName).isLoaded()) {
				getWorld(worldName).load();
			}
			
			Location loc = getWorld(worldName).getWorld().getSpawnLocation();
			if (loc != null)
				((Player) sender).teleport(loc);
			else
				error(sender, "Couldn't find a safe spot at your destination");
		}
	}
}
