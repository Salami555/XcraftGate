package de.xcraft.engelier.XcraftGate;

import java.util.logging.Level;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;

public class PluginManager {
	private final XcraftGate core;
	private final org.bukkit.plugin.PluginManager pm;
	
	private Plugin vault = null;
	
	private Permission permission = null;
	private Economy economy = null;
	
	public PluginManager(XcraftGate core) {
		this.core = core;
		this.pm = core.getServer().getPluginManager();
	}
	
	public void registerEvents(Listener listener) {
		pm.registerEvents(listener, core);
	}

	public Permission getPermissions() {
		return permission;
	}
	
	public Economy getEconomy() {
		return economy;
	}
	
	public void checkPluginVault() {
		if (vault != null)
			return;
		
		Plugin vaultCheck = pm.getPlugin("Vault");
		if (vaultCheck != null && vaultCheck.isEnabled()) {
			vault = vaultCheck;
			core.getLogger().log(Level.INFO, "{0}found Vault plugin.", core.getNameBrackets());
			
	        RegisteredServiceProvider<Permission> permissionProvider = core.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);
	        if (permissionProvider != null) {
	            permission = permissionProvider.getProvider();
	            core.getLogger().log(Level.INFO, "{0}Reported permission provider: {1}", new Object[]{core.getNameBrackets(), permission.getName()});
	        }

	        RegisteredServiceProvider<Economy> economyProvider = core.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
	        if (economyProvider != null) {
	            economy = economyProvider.getProvider();
	            core.getLogger().log(Level.INFO, "{0}Reported economy provider: {1}", new Object[]{core.getNameBrackets(), economy.getName()});
	        }

		}
	}
	
	public void checkDisabledPlugin(Plugin plugin) {
		if (plugin.getDescription().getName().equals("Vault")) {
			permission = null;
			economy = null;
			vault = null;
			core.getLogger().log(Level.INFO, "{0}lost Vault plugin", core.getNameBrackets());
		}

	}
}
