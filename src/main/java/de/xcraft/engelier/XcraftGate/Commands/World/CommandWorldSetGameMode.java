package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.Util;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;

public class CommandWorldSetGameMode extends CommandHelperWorld {
    
    public CommandWorldSetGameMode(XcraftGate plugin) {
        super(plugin);
    }
    
    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gworld setgamemode <worldname> <survival|creative|adventure|spectator>");
    }
    
    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            replyUsage(sender);
        } else if (!hasWorld(worldName)) {
            reply(sender, "World not found: " + worldName);
        } else {
            GameMode newGM = Util.castGameMode(args, null);
            
            if (newGM == null) {
                error(sender, "Unknown gamemode.");
                return;
            }
            
            getWorld(worldName).setGameMode(newGM);
            reply(sender, "GameMode for world " + worldName + " set to " + newGM.toString());
        }
    }
    
}
