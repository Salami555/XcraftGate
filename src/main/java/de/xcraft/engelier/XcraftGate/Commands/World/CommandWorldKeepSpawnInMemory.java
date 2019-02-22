package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldKeepSpawnInMemory extends CommandHelperWorld {

	public CommandWorldKeepSpawnInMemory(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName,
			List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld keepspawninmemory <worldname> <true|false>");
		} else if (!hasWorld(worldName)) {
			reply(sender, "World not found: " + worldName);
		} else {
			Boolean keep;
			
			keep = (args.isEmpty() || !args.get(0).equalsIgnoreCase("false"));

			getWorld(worldName).setKeepSpawnInMemory(keep);
			reply(sender, "Spawnarea on " + worldName + (keep ? " stays in memory." : " will get unloaded normally."));
		}		
	}

}
