package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.DataWorld;
import de.xcraft.engelier.XcraftGate.DataWorld.Weather;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetWeather extends CommandHelperWorld {

    public CommandWorldSetWeather(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No gate given.");
            reply(sender, "Usage: /gworld setweather <worldname> <sun|storm>");
        } else if (args.isEmpty()) {
            error(sender, "No weather given.");
            reply(sender, "Usage: /gworld setweather <worldname> <sun|storm>");
        } else if (!hasWorld(worldName)) {
            reply(sender, "World not found: " + worldName);
        } else {
            for (Weather thisWeather : DataWorld.Weather.values()) {
                if (thisWeather.toString().equalsIgnoreCase(args.get(0))) {
                    getWorld(worldName).setWeather(thisWeather);
                    reply(sender, "Weather of world " + worldName + " changed to " + args.get(0) + ".");
                    return;
                }
            }

            reply(sender, "Unknown weather type: " + args.get(0) + ". Use \"sun\" or \"storm\"");
        }
    }

}
