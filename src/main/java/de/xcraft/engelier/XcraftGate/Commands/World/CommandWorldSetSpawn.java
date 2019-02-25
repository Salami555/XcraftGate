package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandWorldSetSpawn extends CommandHelperWorld {

    public CommandWorldSetSpawn(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void replyUsage(CommandSender sender) {
    }
    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        Location loc = ((Player) sender).getLocation();
        loc.getWorld().setSpawnLocation((int) Math.floor(loc.getX()), (int) Math.floor(loc.getY()), (int) Math.floor(loc.getZ()));
        reply(sender, "Set spawn location of " + loc.getWorld().getName() + " to your current position.");
    }

}
