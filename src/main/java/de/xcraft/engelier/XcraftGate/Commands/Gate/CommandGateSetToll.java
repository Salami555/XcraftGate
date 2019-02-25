package de.xcraft.engelier.XcraftGate.Commands.Gate;

import de.xcraft.engelier.XcraftGate.Commands.CommandHelperGate;
import de.xcraft.engelier.XcraftGate.XcraftGate;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandGateSetToll extends CommandHelperGate {

    public CommandGateSetToll(XcraftGate plugin) {
        super(plugin);
    }

    @Override
    public void execute(CommandSender sender, String gateName, List<String> args) {
        if (gateName == null) {
            error(sender, "No gate given.");
            reply(sender, "Usage: /gate settoll <gatename> <amount>");
        } else if (!gateExists(gateName)) {
            reply(sender, "Gate not found: " + gateName);
        } else {
            if (plugin.getPluginManager().getEconomy() == null) {
                sender.sendMessage(ChatColor.RED + "ERROR: No economy plugin was found, so this setting has no effect.");
                return;
            }

            double toll = 0.00;

            try {
                toll = Double.parseDouble(args.get(0));
            } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                sender.sendMessage(ChatColor.RED + "ERROR: invalid currency amount");
                return;
            }

            getGate(gateName).setToll(toll);
            reply(sender, "Gate " + gateName + (toll > 0 ? " now collecting " + plugin.getPluginManager().getEconomy().format(toll) + " toll." : " doesn't collect tolls"));
        }
    }

}
