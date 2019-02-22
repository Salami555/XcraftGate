package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.DataWorld;
import de.xcraft.engelier.XcraftGate.Generator.Generator;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;

public class CommandWorldCreate extends CommandHelperWorld {

	public CommandWorldCreate(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		if (worldName == null) {
			error(sender, "No world given.");
			reply(sender, "Usage: /gworld create <worldname> [<environment> [seed]]");
		} else if (hasWorld(worldName)) {
			reply(sender, "World already exists: " + worldName);
		} else {
			String env = args.size() < 1 ? "normal" : args.get(0);
			
			Environment worldEnv = null;
			Generator worldGen = null;

			for (Environment thisEnv : World.Environment.values()) {
				if (thisEnv.toString().equalsIgnoreCase(env)) {
					worldEnv = thisEnv;
				}
			}
			
			for (Generator thisGen : Generator.values()) {
				if (thisGen.toString().equalsIgnoreCase(env)) {
					worldGen = thisGen;
					worldEnv = World.Environment.NORMAL;
				}
			}
			
			if (worldEnv == null) {
				reply(sender, "Unknown environment: " + env);
				return;
			}

			DataWorld thisWorld = new DataWorld(plugin, worldName, worldEnv, worldGen);
			plugin.getWorlds().add(thisWorld);

			if (args.size() < 2) {
				thisWorld.load();
			} else {
				Long seed = 0L;
				try {
					seed = Long.parseLong(args.get(1));
				} catch (Exception ex) {
					seed = (long)args.get(1).hashCode();
				}
				
				thisWorld.load(seed);
			}
			
			reply(sender, "World " + worldName + " created with environment " + env + ".");
		}
	}
}
