package de.xcraft.engelier.XcraftGate.Commands;

import java.util.List;

import org.bukkit.command.CommandSender;

import de.xcraft.engelier.XcraftGate.XcraftGate;
import de.xcraft.engelier.XcraftGate.DataWorld;
import de.xcraft.engelier.XcraftGate.DataWorld.DayTime;

public class CommandWorldSetTime extends CommandHelperWorld {

	public CommandWorldSetTime(XcraftGate plugin) {
		super(plugin);
	}

	@Override
	public void execute(CommandSender sender, String worldName,	List<String> args) {
		this.sender = sender;
		
		if (worldName == null) {
			error("No world given.");
			reply("Usage: /gworld settime <worldname> <sunrise|noon|sunset|midnight>");
		} else if (args.size() == 0) {
			error("No time given.");
			reply("Usage: /gworld settime <worldname> <sunrise|noon|sunset|midnight>");
		} else if (!hasWorld(worldName)) {
			reply("World not found: " + worldName);
		} else {
			for (DayTime thisTime : DataWorld.DayTime.values()) {
				if (thisTime.toString().equalsIgnoreCase(args.get(0))) {
					getWorld(worldName).setDayTime(thisTime);
					reply("Time of world " + worldName + " changed to " + args.get(0) + ".");
					return;
				}
			}

			reply("Unknown time: " + args.get(0) + ". Use \"sunrise\", \"noon\", \"sunset\" or \"midnight\"");
		}
	}

}
