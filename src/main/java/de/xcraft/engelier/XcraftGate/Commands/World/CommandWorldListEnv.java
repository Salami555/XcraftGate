package de.xcraft.engelier.XcraftGate.Commands.World;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperWorld;
import de.xcraft.engelier.XcraftGate.Generator.Generator;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.command.CommandSender;

public class CommandWorldListEnv extends CommandHelperWorld {

    public CommandWorldListEnv(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void replyUsage(CommandSender sender) {
    }

    @Override
    public void execute(CommandSender sender, String worldName, List<String> args) {
        reply(sender, "Environments provided by Bukkit:");
        for (Environment thisEnv : World.Environment.values()) {
            sender.sendMessage(thisEnv.toString());
        }

        reply(sender, "Environments provided by XcraftGate:");
        for (Generator thisEnv : Generator.values()) {
            if (thisEnv.getId() != 0) {
                sender.sendMessage(thisEnv.toString());
            }
        }
    }
}
