package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;

public class CommandWorldSetGameRule extends CommandHelperWorld {

    public CommandWorldSetGameRule(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            reply(sender, "Usage: /gworld setgamerule <worldname> <rulename> <value>");
        } else if (!hasWorld(worldName)) {
            reply(sender, "World not found: " + worldName);
        } else if (args.size() != 2) {
            error(sender, "Wrong argument count.");
            reply(sender, "Usage: /gworld setgamerule <worldname> <rulename> <value>");
        } else {
            String rule = args.get(0);
            String value = args.get(1);

            if (getWorld(worldName).getWorld().getGameRuleValue(rule) == null) {
                reply(sender, "Unknown gamerule '" + rule + "'");
            } else {
                getWorld(worldName).getWorld().setGameRuleValue(rule, value);
                reply(sender, "GameRule '" + rule + "' for world " + worldName + " set to " + value);
            }
        }
    }

}
