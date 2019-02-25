package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.DataWorld;
import de.xcraft.engelier.XcraftGate.DataWorld.DayTime;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetTime extends CommandHelperWorld {

    public CommandWorldSetTime(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            reply(sender, "Usage: /gworld settime <worldname> <sunrise|noon|sunset|midnight>");
        } else if (args.isEmpty()) {
            error(sender, "No time given.");
            reply(sender, "Usage: /gworld settime <worldname> <sunrise|noon|sunset|midnight>");
        } else if (!hasWorld(worldName)) {
            reply(sender, "World not found: " + worldName);
        } else {
            for (DayTime thisTime : DataWorld.DayTime.values()) {
                if (thisTime.toString().equalsIgnoreCase(args.get(0))) {
                    getWorld(worldName).setDayTime(thisTime);
                    reply(sender, "Time of world " + worldName + " changed to " + args.get(0) + ".");
                    return;
                }
            }

            reply(sender, "Unknown time: " + args.get(0) + ". Use \"sunrise\", \"noon\", \"sunset\" or \"midnight\"");
        }
    }

}
