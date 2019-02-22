package de.xcraft.engelier.XcraftGate.Commands;

import de.xcraft.engelier.XcraftGate.DataWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandWorldList extends CommandHelperWorld {

	public CommandWorldList(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName, List<String> args) {
		this.sender = sender;
		
		String worlds = "";
		for (DataWorld thisWorld : plugin.getWorlds()) {
			worlds += ", " + thisWorld.getName();
			if (thisWorld.isLoaded()) {
				worlds += "(*)";
			}
		}
		reply("Worlds: " + ChatColor.WHITE + worlds.substring(2));
		reply("World marked with (*) are currently active on the server.");
	}

}
