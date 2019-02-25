package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;

public class CommandWorldSetGameMode extends CommandHelperWorld {

    public CommandWorldSetGameMode(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            reply(sender, "Usage: /gworld setgamemode <worldname> <survival|creative|adventure|spectator>");
        } else if (!hasWorld(worldName)) {
            reply(sender, "World not found: " + worldName);
        } else {
            GameMode newGM = null;

            for (GameMode thisGM : GameMode.values()) {
                if (thisGM.toString().equalsIgnoreCase(args.get(0))) {
                    newGM = thisGM;
                }
            }

            if (newGM == null) {
                error(sender, "Unknown gamemode.");
                return;
            }

            getWorld(worldName).setGameMode(newGM.getValue());
            reply(sender, "GameMode for world " + worldName + " set to " + newGM.toString());
        }
    }

}
