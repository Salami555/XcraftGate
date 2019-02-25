package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorldListPlayers extends CommandHelperWorld {

    public CommandWorldListPlayers(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void replyUsage(CommandSender sender) {
        reply(sender, "Usage: /gworld listplayers <worldname>");
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        if (worldName == null) {
            error(sender, "No world given.");
            replyUsage(sender);
        } else if (!hasWorld(worldName)) {
            reply(sender, "World not found: " + worldName);
        } else {
            String players = "";
            for (Player player : plugin.getServer().getWorld(worldName).getPlayers()) {
                players += ", " + player.getName();
            }

            if (players.length() > 0) {
                reply(sender, "Players in world " + worldName + ": " + players.substring(2));
            } else {
                reply(sender, "No players in world " + worldName + ".");
            }
        }
    }

}
