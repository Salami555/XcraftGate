package de.xcraft.engelier.XcraftGate.Commands;

import de.xcraft.engelier.XcraftGate.XcraftGate;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class CommandHelper {
	protected final XcraftGate plugin;

	public CommandHelper(XcraftGate instance) {
		plugin = instance;
	}

	public void reply(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.LIGHT_PURPLE + plugin.getNameBrackets() + ChatColor.DARK_AQUA + message);
	}

	public void error(CommandSender sender, String message) {
		sender.sendMessage(ChatColor.RED + "Error: " + message);
	}

	public boolean isPermitted(Player player, String command, String subcommand) {
		if (player == null) {
			return true;
		}
		
		if (plugin.getPluginManager().getPermissions() != null) {
			if (subcommand != null) {
				return plugin.getPluginManager().getPermissions().has(player, "XcraftGate." + command
						+ "." + subcommand);
			} else {
				return plugin.getPluginManager().getPermissions().has(player, "XcraftGate." + command);
			}
		} else {
			if (subcommand != null) {
				return player.hasPermission("XcraftGate." + command + "." + subcommand);
			} else {
				return player.hasPermission("XcraftGate." + command);
			}
		}
	}
}
